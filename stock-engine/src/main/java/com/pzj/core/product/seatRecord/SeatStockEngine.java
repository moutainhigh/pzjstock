/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.seatRecord;

import java.util.ArrayList;
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
import com.pzj.core.product.entity.SeatRecord;
import com.pzj.core.stock.enums.OperateStockBussinessType;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.model.QueryStockByShowReqModel;
import com.pzj.core.stock.model.StockBatchLockModel;
import com.pzj.core.stock.model.StockModel;
import com.pzj.core.stock.service.StockQueryService;
import com.pzj.core.stock.service.StockService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

/**
 * 
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

	/**
	 * 根据参数类型去占库存或者释放库存
	 * 
	 * 主动操作，场次id，操作人，游玩时间都是确定的。
	 * 
	 * @param seatInfoModels  座位基本信息
	 * @param screeingId	 场次id
	 * @param operateUserId 操作人id
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

	public Boolean operationStock(List<SeatRecord> seatRecords, OperateStockBussinessType operateStockBussinessType,
			ServiceContext serviceContext) {
		//库存对应数量  key 演出时间:场次id:区域id value 释放的数量
		Map<String, Integer> stockMap = new HashMap<>();
		for (SeatRecord seatRecord : seatRecords) {
			Date theaterDate = DateUtils.getDateByType(seatRecord.getTravelDate(), "yyyy-MM-dd");
			String key = theaterDate + ":" + seatRecord.getScreeningId() + ":" + seatRecord.getAreaId();
			if (CheckUtils.isNull(stockMap.get(key))) {
				stockMap.put(key, 1);
			} else {
				stockMap.put(key, stockMap.get(key) + 1);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("travelDate:screeningId:areaId-number mappings contained in this map.{}",
					JSONConverter.toJson(stockMap));
		}
		Iterator<String> stockIt = stockMap.keySet().iterator();
		while (stockIt.hasNext()) {
			String key = stockIt.next();
			String[] params = key.split(":");
			//演出时间
			Date theaterDate = DateUtils.string2Date(params[0]);
			//场次id
			Long screeingId = Long.valueOf(params[1]);
			//区域id
			Long areaId = Long.valueOf(params[2]);
			//释放库存
			Integer stockNum = stockMap.get(key);
			//库存id
			Long stockId = null;
			//获取库存信息
			//根据演艺，场次，演出时间查询库存信息
			QueryStockByShowReqModel stockByShowReqModel = new QueryStockByShowReqModel();
			stockByShowReqModel.setScreeingId(screeingId);
			stockByShowReqModel.setShowTime(DateUtils.getDateByType(theaterDate, "yyyy-MM-dd"));
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
			stockId = sModel.getId();
			//操作库存
			StockBatchLockModel batchLockModel = new StockBatchLockModel();
			batchLockModel.setOperateType(operateStockBussinessType.key);

			Map<Long, Integer> releaseMap = new HashMap<>();
			releaseMap.put(stockId, stockNum);
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
}
