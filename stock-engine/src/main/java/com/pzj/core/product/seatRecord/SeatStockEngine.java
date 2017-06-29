/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.seatRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.commons.utils.CheckUtils;
import com.pzj.commons.utils.DateUtils;
import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.common.exception.TheaterExceptionCode;
import com.pzj.core.product.entity.SeatRecord;
import com.pzj.core.product.model.OccupyStockReqModel;
import com.pzj.core.product.model.OccupyStockResponse;
import com.pzj.core.product.seatRecord.events.OccupySeatEvent;
import com.pzj.core.product.seatRecord.events.ReleaseSeatEvent;
import com.pzj.core.product.seatRecord.exception.SeatRecordOccupyStockException;
import com.pzj.core.product.write.SeatRecordWriteMapper;
import com.pzj.core.stock.common.CommonTradeOpeStockModel;
import com.pzj.core.stock.enums.OperateStockBussinessType;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.model.OccupyStockRequestModel;
import com.pzj.core.stock.model.QueryStockByShowReqModel;
import com.pzj.core.stock.model.StockBatchLockModel;
import com.pzj.core.stock.model.StockModel;
import com.pzj.core.stock.service.StockQueryService;
import com.pzj.core.stock.service.StockService;
import com.pzj.core.stock.stockupdate.CommonTradeStockEngine;
import com.pzj.core.stock.stockupdate.OccupyStockException;
import com.pzj.core.work.UnitOfWork;
import com.pzj.core.work.support.ThreadUnitOfWork;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

/**
 * @author Administrator
 * @version $Id: SeatStockEngine.java, v 0.1 2017年3月17日 下午4:36:43 Administrator Exp $
 */
@Component("seatStockEngine")
@Transactional(value = "stock.transactionManager")
public class SeatStockEngine {
	private static final Logger logger = LoggerFactory.getLogger(SeatStockEngine.class);
	@Resource
	private StockQueryService stockQueryService;
	@Resource
	private StockService stockService;
	@Resource
	private CommonTradeStockEngine commonTradeStockEngine;
	@Resource
	private SeatRecordWriteMapper seatRecordWriteMapper;

	/**
	 * 根据参数类型去占库存或者释放库存
	 * <p>
	 * 主动操作，场次id，操作人，游玩时间都是确定的。
	 *
	 * @param seatRecords               座位记录
	 * @param screeingId                场次id
	 * @param operateUserId             操作人id
	 * @param operateStockBussinessType
	 * @param serviceContext
	 * @return
	 */
	public Boolean operationStock(List<SeatRecord> seatRecords, Long screeingId, Long operateUserId, Date theaterDate,
			OperateStockBussinessType operateStockBussinessType, ServiceContext serviceContext) {
		//区域座位map  key 区域id，value 座位数量
		Map<Long, Integer> areaSeatNum = new HashMap<Long, Integer>();
		for (SeatRecord seatRecord : seatRecords) {
			if (areaSeatNum.get(seatRecord.getAreaId()) == null) {
				areaSeatNum.put(seatRecord.getAreaId(), 1);
			} else {
				areaSeatNum.put(seatRecord.getAreaId(), areaSeatNum.get(seatRecord.getAreaId()) + 1);
			}
			if (CheckUtils.isNull(screeingId)) {
				screeingId = seatRecord.getScreeningId();
			}
		}

		//区域对应的库存集合
		List<StockModel> stocks = new ArrayList<>();
		//库存map key 库存id， value 数量
		Map<Long, Integer> stockMap = new HashMap<Long, Integer>();
		//根据演艺，场次，演出时间查询库存信息
		QueryStockByShowReqModel stockByShowReqModel = new QueryStockByShowReqModel();
		stockByShowReqModel.setScreeingId(screeingId);
		stockByShowReqModel.setShowTime(DateUtils.getDateByType(theaterDate, "yyyy-MM-dd"));
		Iterator<Long> areaIt = areaSeatNum.keySet().iterator();
		while (areaIt.hasNext()) {
			Long areaId = areaIt.next();
			stockByShowReqModel.setAreaId(areaId);
			Result<StockModel> queryStockResult = stockQueryService.queryStockByShow(stockByShowReqModel,
					serviceContext);
			if (!queryStockResult.isOk()) {
				throw new StockException(queryStockResult.getErrorMsg());
			}
			StockModel sModel = queryStockResult.getData();
			if (CheckUtils.isNull(sModel)) {
				throw new StockException(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG);
			}
			stockMap.put(sModel.getId(), areaSeatNum.get(areaId) == null ? 0 : areaSeatNum.get(areaId));
			stocks.add(sModel);

		}
		if (logger.isDebugEnabled()) {
			logger.debug("库存id及要操作的库存数量:{}", JSONConverter.toJson(stockMap));
		}
		//操作库存
		StockBatchLockModel batchLockModel = new StockBatchLockModel();
		batchLockModel.setUserId(operateUserId);
		batchLockModel.setOperateType(operateStockBussinessType.key);

		for (StockModel stockModel : stocks) {
			Map<Long, Integer> releaseMap = new HashMap<>();
			releaseMap.put(stockModel.getId(), stockMap.get(stockModel.getId()));
			batchLockModel.setOperateStockMap(releaseMap);
			Result<Boolean> releaseResult = stockService.stockBatchLock(batchLockModel, serviceContext);
			if (!releaseResult.isOk()) {
				if (logger.isDebugEnabled()) {
					logger.debug(operateStockBussinessType.value + "失败,失败的参数为:{}", JSONConverter.toJson(batchLockModel));
				}
				throw new StockException(releaseResult.getErrorMsg());
			}
		}
		return true;

	}

	public Boolean operationStock(SeatRecord seatRecord, OperateStockBussinessType operateStockBussinessType,
			ServiceContext serviceContext) {
		//演出时间
		Date theaterDate = DateUtils.getDateByType(seatRecord.getTravelDate(), "yyyy-MM-dd");
		//场次id
		Long screeingId = seatRecord.getScreeningId();
		//区域id
		Long areaId = seatRecord.getAreaId();
		//用户id
		Long operId = seatRecord.getOperatorId();
		//一个占座记录的库存数量是1
		Integer stockNum = 1;
		//获取库存信息
		//根据演艺，场次，演出时间查询库存信息
		QueryStockByShowReqModel stockByShowReqModel = new QueryStockByShowReqModel();
		stockByShowReqModel.setScreeingId(screeingId);
		stockByShowReqModel.setShowTime(theaterDate);
		stockByShowReqModel.setAreaId(areaId);

		Result<StockModel> queryStockResult = stockQueryService.queryStockByShow(stockByShowReqModel, serviceContext);
		if (!queryStockResult.isOk()) {
			throw new StockException(queryStockResult.getErrorMsg());
		}
		StockModel sModel = queryStockResult.getData();
		if (CheckUtils.isNull(sModel)) {
			throw new StockException(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG);
		}
		Long stockId = sModel.getId();
		//操作库存
		StockBatchLockModel batchLockModel = new StockBatchLockModel();
		batchLockModel.setOperateType(operateStockBussinessType.key);
		batchLockModel.setUserId(operId);

		Map<Long, Integer> releaseMap = new HashMap<>();
		releaseMap.put(stockId, stockNum);
		batchLockModel.setOperateStockMap(releaseMap);
		try {
			Result<Boolean> releaseResult = stockService.stockBatchLock(batchLockModel, serviceContext);
			if (!releaseResult.isOk()) {
				logger.error(operateStockBussinessType.value + "失败,失败的原因为：{},失败的参数为:{}", releaseResult.getErrorMsg(),
						JSONConverter.toJson(batchLockModel));
			}
		} catch (Exception e) {
			logger.error(operateStockBussinessType.value + "失败,失败的原因为：{},失败的参数为:{}", e.getMessage(),
					JSONConverter.toJson(batchLockModel));
		}

		return true;
	}

	public void occupyStock(OccupyStockReqModel occupyStockReqModel) {
		checkOccupyParam(occupyStockReqModel);

		// 占库存
		try {
			accountingForStock(occupyStockReqModel);
		} catch (OccupyStockException exception) {
			OccupyStockResponse response = new OccupyStockResponse();
			response.setProductId(occupyStockReqModel.getProductId());
			response.setTravelDate(occupyStockReqModel.getTravelDate());
			response.setStockId(exception.getStockId());
			response.setStockName(exception.getStockName());
			response.setStockRuleId(occupyStockReqModel.getStockRuleId());
			response.setStockType(exception.getStockType());
			response.setRemainNum(exception.getRemainNum());

			SeatRecordOccupyStockException e = new SeatRecordOccupyStockException(exception.getErrCode(),
					exception.getMessage(), response);
			e.setStackTrace(exception.getStackTrace());

			throw e;
		} catch (Throwable throwable) {
			throw throwable;
		}
	}

	private void checkOccupyParam(OccupyStockReqModel occupyStockReqModel) {
		if (occupyStockReqModel == null) {
			throw new TheaterException(TheaterExceptionCode.PARAMETER_EMPTY);
		}
		if (occupyStockReqModel.getTransactionId() == null) {
			throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_NULL_TRANSACTION);
		}
		if (occupyStockReqModel.getTransactionId().trim().equals("")) {
			throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_NULL_TRANSACTION);
		}
		if (occupyStockReqModel.getOperator() == null || occupyStockReqModel.getOperator() == 0) {
			throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_NULL_OPERATOR);
		}
		if (occupyStockReqModel.getOutQuantity() == null) {
			throw new TheaterException(TheaterExceptionCode.OCCUPYED_STOCK_NULL_OUT_QUANTITY);
		}
		if (occupyStockReqModel.getStockRuleId() == null || occupyStockReqModel.getStockRuleId() == 0) {
			throw new TheaterException(TheaterExceptionCode.OCCUPYED_STOCK_NULL_RULE_ID);
		}

		Date travelDate = occupyStockReqModel.getTravelDate();
		if (travelDate != null) {
			travelDate = SeatRecordUtil.formatTravelDate(travelDate);
			occupyStockReqModel.setTravelDate(travelDate);
		}

		if (occupyStockReqModel.getOccupySeatIds() == null) {
			occupyStockReqModel.setOccupySeatIds(Collections.EMPTY_LIST);
		}
		if (occupyStockReqModel.getReleaseSeatIds() == null) {
			occupyStockReqModel.setReleaseSeatIds(Collections.EMPTY_LIST);
		}

	}

	private void accountingForStock(OccupyStockReqModel occupyStockReqModel) {
		OccupyStockRequestModel occupyStockRequestModel = new OccupyStockRequestModel();
		occupyStockRequestModel.setTransactionId(occupyStockReqModel.getTransactionId());
		occupyStockRequestModel.setProductId(occupyStockReqModel.getProductId());
		occupyStockRequestModel.setUserId(occupyStockReqModel.getOperator());
		occupyStockRequestModel.setStockNum(occupyStockReqModel.getOutQuantity());
		occupyStockRequestModel.setStockRuleId(occupyStockReqModel.getStockRuleId());
		occupyStockRequestModel.setShowDate(occupyStockReqModel.getTravelDate());

		List<OccupyStockRequestModel> orderStockModelList = new ArrayList();
		orderStockModelList.add(occupyStockRequestModel);

		CommonTradeOpeStockModel commonTradeOpeStockModel = commonTradeStockEngine.occupyStock(orderStockModelList);

		if (commonTradeOpeStockModel != null) {
			String type = commonTradeOpeStockModel.getType();
			String transactionId = commonTradeOpeStockModel.getTransactionId();

			Integer occupyType = null;

			SeatRecord seatRecord = new SeatRecord();
			seatRecord.setTransactionId(transactionId);
			seatRecord.setState(1);
			List<SeatRecord> seatRecords = seatRecordWriteMapper.querySeatRecordByModel(seatRecord);
			if (seatRecords != null && seatRecords.size() > 0) {
				occupyType = seatRecords.get(0).getCategory();
			}

			if (CommonTradeOpeStockModel.OCCUPY_STOCK.equals(type)) {
				publishOccupySeatEvent(transactionId, commonTradeOpeStockModel.getProductId(),
						commonTradeOpeStockModel.getOpeNum(), occupyType, occupyStockReqModel);
			} else if (CommonTradeOpeStockModel.RELEASE_STOCK.equals(type)) {
				publishReleaseSeatEvent(transactionId, commonTradeOpeStockModel.getProductId(),
						commonTradeOpeStockModel.getOpeNum(), occupyType, occupyStockReqModel);
			}
		}
	}

	private void publishReleaseSeatEvent(String transactionId, Long productId, Integer stockNum, Integer occupyType,
			OccupyStockReqModel occupyStockReqModel) {
		UnitOfWork unitOfWork = ThreadUnitOfWork.getOrCreateThreadUnitOfWork();

		ReleaseSeatEvent event = new ReleaseSeatEvent();
		event.setTransactionId(transactionId);
		event.setProductId(productId);
		event.setInQuantity(stockNum);

		event.setScreeningsId(occupyStockReqModel.getScreeningsId());
		event.setAreaId(occupyStockReqModel.getAreaId());
		event.setTravelDate(occupyStockReqModel.getTravelDate());
		event.setReleaseSeatIds(occupyStockReqModel.getReleaseSeatIds());

		event.setOccupyType(occupyType);

		unitOfWork.addEvent(event);
	}

	private void publishOccupySeatEvent(String transactionId, Long productId, Integer stockNum, Integer occupyType,
			OccupyStockReqModel occupyStockReqModel) {
		UnitOfWork unitOfWork = ThreadUnitOfWork.getOrCreateThreadUnitOfWork();

		OccupySeatEvent event = new OccupySeatEvent();
		event.setTransactionId(transactionId);
		event.setProductId(productId);
		event.setOutQuantity(stockNum);

		event.setScreeningsId(occupyStockReqModel.getScreeningsId());
		event.setAreaId(occupyStockReqModel.getAreaId());
		event.setTravelDate(occupyStockReqModel.getTravelDate());
		event.setOccupySeatIds(occupyStockReqModel.getOccupySeatIds());

		event.setOccupyType(occupyType);

		unitOfWork.addEvent(event);
	}
}
