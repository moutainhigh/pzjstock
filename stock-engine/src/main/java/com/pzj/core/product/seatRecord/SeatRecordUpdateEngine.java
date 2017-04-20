package com.pzj.core.product.seatRecord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.commons.utils.CheckUtils;
import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.common.exception.TheaterExceptionCode;
import com.pzj.core.product.entity.SeatRecord;
import com.pzj.core.product.entity.SeatRecordUpdate;
import com.pzj.core.product.enums.RecordState;
import com.pzj.core.product.model.ReleaseStockReqModel;
import com.pzj.core.product.model.ReleaseStockReqsModel;
import com.pzj.core.product.model.seat.SeatInfoModel;
import com.pzj.core.product.model.seatRecord.SeatRecordUpdateReqModel;
import com.pzj.core.product.read.SeatRecordReadMapper;
import com.pzj.core.product.write.SeatRecordWriteMapper;
import com.pzj.core.stock.enums.OperateStockBussinessType;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.model.OccupyStockRequestModel;
import com.pzj.core.stock.stockupdate.CommonTradeStockEngine;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

/**
 *
 * @author Administrator
 * @version $Id: SeatRecordCreateEngine.java, v 0.1 2017年3月17日 上午11:43:03 Administrator Exp $
 */
@Component("seatRecordUpdateEngine")
@Transactional(value = "stock.transactionManager")
public class SeatRecordUpdateEngine {
	private static final Logger logger = LoggerFactory.getLogger(SeatRecordUpdateEngine.class);
	@Resource
	private SeatRecordWriteMapper seatRecordWriteMapper;
	@Resource
	private SeatRecordQueryEngine seatRecordQueryEngine;
	@Resource
	private SeatRecordReadMapper seatRecordReadMapper;
	@Resource
	private SeatStockEngine seatStockEngine;
	@Resource
	private CommonTradeStockEngine commonTradeStockEngine;
	@Resource
	private SeatRecordWriteEngine seatRecordWriteEngine;

	/**
	 * 取消锁定座位释放库存
	 * 
	 * @param seatRecordQueryReqModel
	 * @param serviceContext
	 * @return
	 */
	public Boolean releaseLockSeat(SeatRecordUpdateReqModel seatRecordQueryReqModel, ServiceContext serviceContext) {
		if (logger.isDebugEnabled()) {
			logger.debug("The parameter to modify the seat record state is {} ",
					JSONConverter.toJson(seatRecordQueryReqModel));
		}
		SeatRecord updaetLockSeat = convertLockSeat(seatRecordQueryReqModel);
		List<SeatRecord> seatRecords = seatRecordQueryEngine.querySeatRecordByModel(updaetLockSeat);
		//修改占座记录状态
		updateSeatStateAsInvalid(seatRecords, seatRecordQueryReqModel.getOperateUserId());

		if (logger.isInfoEnabled()) {
			logger.info("修改占座记录状态完成,开始释放库存");
		}
		Boolean releaseResult = seatStockEngine.operationStock(seatRecords, seatRecordQueryReqModel.getScreeingId(),
				seatRecordQueryReqModel.getOperateUserId(), seatRecordQueryReqModel.getTheaterDate(),
				OperateStockBussinessType.BATCH_RELEASE_STOCK, serviceContext);

		if (logger.isInfoEnabled()) {
			logger.info("释放库存完成");
		}
		return releaseResult;
	}

	private void updateSeatStateAsInvalid(List<SeatRecord> seatRecords, Long operId) {
		List<Long> updateIds = new ArrayList<>();
		//判断锁定的操作人是否是当前操作人
		Iterator<SeatRecord> seatIt = seatRecords.iterator();
		while (seatIt.hasNext()) {
			SeatRecord seatRecord = seatIt.next();
			if (CheckUtils.isNotNull(operId) && operId != seatRecord.getOperatorId()) {
				seatIt.remove();
			} else {
				updateIds.add(seatRecord.getRecordId());
			}
		}
		if (CheckUtils.isNull(updateIds)) {
			return;
		}
		List<SeatRecord> modiftRecords = getModifyRecord(updateIds);
		Integer updateNum = seatRecordWriteMapper.updateSeatRecords(modiftRecords);
		if (updateNum < 1) {
			throw new StockException(StockExceptionCode.RELEASE_SEAT_RECORD_ERR_MSG);
		}
	}

	private SeatRecord convertLockSeat(SeatRecordUpdateReqModel seatRecordQueryReqModel) {
		SeatRecord seatRecord = new SeatRecord();
		seatRecord.setTransactionId(seatRecordQueryReqModel.getTransactionId());
		seatRecord.setTravelDate(seatRecordQueryReqModel.getTheaterDate());
		seatRecord.setScreeningId(seatRecordQueryReqModel.getScreeingId());
		List<Long> seatIds = new ArrayList<Long>();
		if (CheckUtils.isNotNull(seatRecordQueryReqModel.getSeatInfos())) {
			for (SeatInfoModel seatInfoModel : seatRecordQueryReqModel.getSeatInfos()) {
				seatIds.add(seatInfoModel.getSeatId());
			}
		}
		seatRecord.setSeatIds(seatIds);
		return seatRecord;
	}

	/**
	 * 自动取消，扫描一批记录，场次id和演出时间都是不确定的
	 * 
	 * @param serviceContext
	 * @return
	 */
	public Boolean checkavailableSeatRecord(ServiceContext serviceContext) {
		if (logger.isInfoEnabled()) {
			logger.info("开始自动释放过期的锁座记录");
		}
		List<SeatRecord> seatRecords = seatRecordQueryEngine.queryOverdueSeatRecords(serviceContext);
		if (CheckUtils.isNull(seatRecords)) {
			return true;
		}
		//修改主键ids
		List<Long> updateIds = new ArrayList<>();
		for (SeatRecord seatRecord : seatRecords) {
			updateIds.add(seatRecord.getRecordId());
		}
		List<SeatRecord> modifyRecords = getModifyRecord(updateIds);
		Integer updateNum = seatRecordWriteMapper.updateSeatRecords(modifyRecords);
		if (updateNum < 1) {
			throw new StockException(StockExceptionCode.RELEASE_SEAT_RECORD_ERR_MSG);
		}
		Boolean reselseResult = seatStockEngine.operationStock(seatRecords,
				OperateStockBussinessType.BATCH_RELEASE_STOCK, serviceContext);
		if (logger.isInfoEnabled()) {
			logger.info("自动释放过期的锁座记录完成");
		}
		return reselseResult;
	}

	public Boolean releaseNormalSeat(ReleaseStockReqsModel releaseStockReqsModel, ServiceContext context) {
		SeatRecordUpdate recordUpdate = convertNormalSeat(releaseStockReqsModel);
		//验证订单下的有效记录数量是否满足要释放的数量
		SeatRecord seatRecord = new SeatRecord();
		seatRecord.setTransactionId(releaseStockReqsModel.getTransactionId());
		seatRecord.setState(RecordState.EFFECTIVER.getState());
		List<SeatRecord> seatRecords = seatRecordQueryEngine.querySeatRecordByModel(seatRecord);

		if (CheckUtils.isNull(seatRecords) || recordUpdate.getSeatNum() > seatRecords.size()) {
			throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_DISABLE);
		}

		// 修改记录占座记录状态
		Boolean modifyResult = sortModifySeatRecord(recordUpdate, context);
		if (!modifyResult) {
			return modifyResult;
		}
		//释放库存
		List<ReleaseStockReqModel> reqModels = releaseStockReqsModel.getReleaseStockReqs();
		List<OccupyStockRequestModel> occupyodels = new ArrayList<OccupyStockRequestModel>();
		for (ReleaseStockReqModel releaseStockReqModel : reqModels) {
			if (logger.isInfoEnabled()) {
				logger.info("释放库存产品id：{},释放数量{}", releaseStockReqModel.getProductId(),
						releaseStockReqModel.getStockNum());
				OccupyStockRequestModel model = new OccupyStockRequestModel();
				model.setTransactionId(releaseStockReqsModel.getTransactionId());
				model.setStockNum(releaseStockReqModel.getStockNum());
				model.setProductId(releaseStockReqModel.getProductId());
				occupyodels.add(model);
			}
		}

		if (CheckUtils.isNotNull(occupyodels)) {
			return commonTradeStockEngine.releaseStock(occupyodels);
		}
		return true;

	}

	private SeatRecordUpdate convertNormalSeat(ReleaseStockReqsModel releaseStockReqsModel) {
		SeatRecordUpdate seatRecordUpdate = new SeatRecordUpdate();
		seatRecordUpdate.setTransactionId(releaseStockReqsModel.getTransactionId());
		seatRecordUpdate.setState(RecordState.INVALID.getState());
		for (ReleaseStockReqModel model : releaseStockReqsModel.getReleaseStockReqs()) {
			if (CheckUtils.isNotNull(model.getSeatNum())) {
				//释放的总座位数量
				seatRecordUpdate.setSeatNum(seatRecordUpdate.getSeatNum() + model.getSeatNum());
				if (model.getStockNum() == 0 && model.getSeatNum() != 0) {
					model.setStockNum(model.getSeatNum());
				}
			}
		}
		return seatRecordUpdate;
	}

	private boolean sortModifySeatRecord(SeatRecordUpdate recordUpdate, ServiceContext context) {
		try {
			seatRecordWriteMapper.sortModifySeatRecord(recordUpdate);
		} catch (Exception e) {
			throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_MODIFY_ERROR);
		}
		return true;
	}

	private List<SeatRecord> getModifyRecord(List<Long> recordIds) {
		SeatRecord record = new SeatRecord();
		record.setRecordIds(recordIds);
		List<SeatRecord> seatRecords = seatRecordQueryEngine.querySeatRecordByModel(record);
		List<SeatRecord> modfiyRecords = new ArrayList<SeatRecord>();
		if (CheckUtils.isNull(seatRecords)) {
			return modfiyRecords;
		}

		for (SeatRecord seatRecord : seatRecords) {
			if (seatRecord.getState() != RecordState.EFFECTIVER.getState()) {
				continue;
			}
			SeatRecord modify = new SeatRecord();
			modify.setRecordId(seatRecord.getRecordId());
			modify.setState(RecordState.INVALID.getState());
			modify.setRecordUnique(seatRecordWriteEngine.recordUnique(seatRecord.getScreeningId(),
					seatRecord.getAreaId(), seatRecord.getTravelDate(), seatRecord.getRecordId().toString()));
			modfiyRecords.add(modify);
		}

		return modfiyRecords;

	}
}
