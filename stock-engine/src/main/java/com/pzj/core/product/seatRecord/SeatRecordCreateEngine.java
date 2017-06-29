/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.seatRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.commons.utils.CheckUtils;
import com.pzj.commons.utils.PropertyLoader;
import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.common.exception.TheaterExceptionCode;
import com.pzj.core.product.entity.SeatRecord;
import com.pzj.core.product.enums.LockSeatType;
import com.pzj.core.product.model.seat.SeatInfoModel;
import com.pzj.core.product.model.seatRecord.SeatRecordCreateReqModel;
import com.pzj.core.product.write.SeatRecordWriteMapper;
import com.pzj.core.stock.enums.OperateStockBussinessType;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.idgen.IDGenerater;

/**
 * 
 * @author Administrator
 * @version $Id: SeatRecordCreateEngine.java, v 0.1 2017年3月16日 上午11:25:03 Administrator Exp $
 */
@Component("seatRecordCreateEngine")
@Transactional(value = "stock.transactionManager")
public class SeatRecordCreateEngine {
	private static final Logger logger = LoggerFactory.getLogger(SeatRecordCreateEngine.class);
	@Resource
	private SeatRecordWriteMapper seatRecordWriteMapper;
	@Resource
	private SeatRecordQueryEngine seatRecordQueryEngine;
	@Resource
	private SeatStockEngine seatStockEngine;
	@Resource
	private SeatRecordWriteEngine seatRecordWriteEngine;
	@Resource(name = "idGenerater")
	private IDGenerater idGenerater;
	@Resource(name = "propertyLoader")
	private PropertyLoader propertyLoader;

	public Boolean createSeatRecord(SeatRecordCreateReqModel createReqModel, ServiceContext serviceContext) {
		if (logger.isDebugEnabled()) {
			logger.debug("The parameter to create the seat record is {} ", JSONConverter.toJson(createReqModel));
		}
		List<SeatInfoModel> seatInfoModels = createReqModel.getSeatInfos();
		List<Long> seatIds = new ArrayList<>();
		for (SeatInfoModel seatInfoModel : seatInfoModels) {
			seatIds.add(seatInfoModel.getSeatId());
		}
		List<SeatRecord> existSeatRecords = seatRecordQueryEngine.queryValidSeatRecord(createReqModel.getTheaterDate(),
				seatIds);
		//从新增中删除
		List<Long> existSeatIds = new ArrayList<>();
		for (SeatRecord existRecord : existSeatRecords) {
			existSeatIds.add(existRecord.getSeatId());
		}
		if (logger.isInfoEnabled()) {
			logger.info("本次选中的座位中，以下座位已经被占:{}", JSONConverter.toJson(existSeatIds));
		}
		Iterator<SeatInfoModel> seatInfoIt = seatInfoModels.iterator();
		while (seatInfoIt.hasNext()) {
			SeatInfoModel seatInfo = seatInfoIt.next();
			if (existSeatIds.contains(seatInfo.getSeatId())) {
				seatInfoIt.remove();
			}
		}
		if (CheckUtils.isNull(seatInfoModels) || seatInfoModels.size() == 0) {
			throw new StockException(TheaterExceptionCode.SEAT_RECORD_RULE_HAS_BEEN_OCCUPIED.getMsg());
		}
		createReqModel.setSeatInfos(seatInfoModels);
		List<SeatRecord> seatRecords = convertSeatRecords(createReqModel);
		seatRecordWriteMapper.insertBatchSeatRecord(seatRecords);
		if (logger.isInfoEnabled()) {
			logger.info("占座记录添加成功,开始占库存");
		}
		//操作库存
		Boolean lockResult = seatStockEngine.operationStock(seatRecords, createReqModel.getScreeingId(),
				createReqModel.getOperateUserId(), createReqModel.getTheaterDate(),
				OperateStockBussinessType.BATCH_OCCUPY_STOCK, serviceContext);
		if (logger.isInfoEnabled()) {
			logger.info("完成占用库存操作!");
		}
		return lockResult;
	}

	/**
	 * 根据参数转换创建锁座记录实体
	 * 
	 * @param createReqModel
	 * @return
	 */
	private List<SeatRecord> convertSeatRecords(SeatRecordCreateReqModel createReqModel) {
		List<SeatInfoModel> seatInfoModels = createReqModel.getSeatInfos();
		Date createTime = new Date();
		SeatRecord seatRecord = new SeatRecord();
		seatRecord.setScreeningId(createReqModel.getScreeingId());
		seatRecord
				.setTransactionId(createReqModel.getTransactionId() == null ? "0" : createReqModel.getTransactionId());
		seatRecord.setOperatorId(createReqModel.getOperateUserId());
		seatRecord.setTravelDate(createReqModel.getTheaterDate());
		seatRecord.setCreateTime(createTime);
		seatRecord.setCategory(createReqModel.getRecordCategory().getState());
		if (CheckUtils.isNotNull(createReqModel.getLockSeatType())) {
			LockSeatType lockSeatType = createReqModel.getLockSeatType();
			//如果是临时锁座,那么计算失效时间
			if (lockSeatType.getState() == LockSeatType.TMP_VALID.getState()) {
				Date expirationTime = new Date(createTime.getTime()
						+ Integer.valueOf(propertyLoader.getProperty("stock_conf", "lock_valid_time", "30")) * 60000);
				seatRecord.setExpirationTime(expirationTime);
			}
		}
		List<SeatRecord> seatRecords = new ArrayList<SeatRecord>();
		for (SeatInfoModel seatInfoModel : seatInfoModels) {
			SeatRecord createSeatRecord = new SeatRecord();
			Long recordId = idGenerater.nextId();
			try {
				PropertyUtils.copyProperties(createSeatRecord, seatRecord);
			} catch (Exception e) {
				logger.error("copy seat record model properties to acting error ", e);
				throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
			}
			createSeatRecord.setSeatId(seatInfoModel.getSeatId());
			createSeatRecord.setSeatName(seatInfoModel.getSeatName());
			createSeatRecord.setRecordId(recordId);
			createSeatRecord.setAreaId(seatInfoModel.getAreaId());
			createSeatRecord.setRecordUnique(SeatRecordUtil.recordUnique(createReqModel.getScreeingId(),
					seatInfoModel.getSeatId(), createReqModel.getTheaterDate(), null));
			seatRecords.add(createSeatRecord);
		}
		return seatRecords;
	}
}
