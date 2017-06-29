package com.pzj.core.product.seatRecord;

import java.util.*;

import javax.annotation.Resource;

import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.common.exception.TheaterExceptionCode;
import com.pzj.core.product.model.QuerySeatRecordResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.commons.utils.CheckUtils;
import com.pzj.core.product.entity.SeatRecord;
import com.pzj.core.product.entity.SeatRecordUpdate;
import com.pzj.core.product.enums.RecordCategory;
import com.pzj.core.product.enums.RecordState;
import com.pzj.core.product.model.ReleaseStockReqModel;
import com.pzj.core.product.model.ReleaseStockReqsModel;
import com.pzj.core.product.model.seat.SeatInfoModel;
import com.pzj.core.product.model.seatRecord.SeatRecordUpdateReqModel;
import com.pzj.core.product.seatRecord.events.ReleaseSeatEvent;
import com.pzj.core.product.write.SeatRecordWriteMapper;
import com.pzj.core.stock.enums.OperateStockBussinessType;
import com.pzj.core.stock.model.OccupyStockRequestModel;
import com.pzj.core.stock.stockupdate.CommonTradeStockEngine;
import com.pzj.core.stock.stockupdate.RollbackOccupyStockEngine;
import com.pzj.core.work.UnitOfWork;
import com.pzj.core.work.support.ThreadUnitOfWork;
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
	private SeatStockEngine seatStockEngine;
	@Resource
	private CommonTradeStockEngine commonTradeStockEngine;
	@Resource
	private RollbackOccupyStockEngine rollbackOccupyStock;

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
		if (CheckUtils.isNull(seatRecords) || seatRecords.size() == 0) {
			return Boolean.TRUE;
		}
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
			if (CheckUtils.isNotNull(operId) && !operId.equals(seatRecord.getOperatorId())) {
				seatIt.remove();
			} else {
				updateIds.add(seatRecord.getRecordId());
			}
		}
		if (CheckUtils.isNull(updateIds) || updateIds.size() == 0) {
			return;
		}
		List<SeatRecord> modiftRecords = getModifyRecord(updateIds);
		seatRecordWriteMapper.updateSeatRecords(modiftRecords);
	}

	private SeatRecord convertLockSeat(SeatRecordUpdateReqModel seatRecordQueryReqModel) {
		SeatRecord seatRecord = new SeatRecord();
		seatRecord.setTransactionId(seatRecordQueryReqModel.getTransactionId());
		seatRecord.setTravelDate(seatRecordQueryReqModel.getTheaterDate());
		seatRecord.setScreeningId(seatRecordQueryReqModel.getScreeingId());
		seatRecord.setOperatorId(seatRecordQueryReqModel.getOperateUserId());
		seatRecord.setState(RecordState.EFFECTIVER.getState());
		seatRecord.setCategory(RecordCategory.LOCKING.getState());
		seatRecord.setAreaId(seatRecordQueryReqModel.getAreaId());
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
	 * @param sr
	 * @return
	 */
	public Boolean checkavailableSeatRecord(QuerySeatRecordResponse sr, ServiceContext serviceContext) {

		List<Long> updateIds = new ArrayList<>();
		updateIds.add(sr.getRecordId());
		List<SeatRecord> modifyRecords = getModifyRecord(updateIds);

		if(CheckUtils.isNull(modifyRecords) || modifyRecords.isEmpty()){
			return true;
		}
		seatRecordWriteMapper.updateSeatRecords(modifyRecords);
		Boolean reselseResult = seatStockEngine.operationStock(convertToSeatRecord(sr),
				OperateStockBussinessType.BATCH_RELEASE_STOCK, serviceContext);
		return reselseResult;
	}

	private SeatRecord convertToSeatRecord(QuerySeatRecordResponse sr){
		SeatRecord seatRecord = new SeatRecord();
		seatRecord.setRecordId(sr.getRecordId());
		seatRecord.setScreeningId(sr.getScreeningId());
		seatRecord.setAreaId(sr.getAreaId());
		seatRecord.setOperatorId(sr.getOperatorId());
		seatRecord.setTravelDate(sr.getTravelDate());
		seatRecord.setSeatId(sr.getSeatId());
		seatRecord.setSeatName(sr.getSeatName());
		return seatRecord;
	}


	public Boolean releaseNormalSeat(ReleaseStockReqsModel releaseStockReqsModel) {
		checkReleaseStock(releaseStockReqsModel);

		String transactionId = releaseStockReqsModel.getTransactionId();

		//验证订单下的有效记录数量是否满足要释放的数量
		Integer occupyType = autoReleaseSeat(transactionId, releaseStockReqsModel);

		//释放库存的数据
		List<OccupyStockRequestModel> occupyodels = getOccupyStockRequestModels(transactionId, releaseStockReqsModel.getReleaseStockReqs());

		if(occupyodels != null && !occupyodels.isEmpty()) {
			// 按产品id及数量释放
			commonTradeStockEngine.releaseStock(occupyodels);

			// 发布释放事件到工作单元中去
			publishReleaseSeatEvent(occupyodels, occupyType);
		} else {
			// 整意单释放
			OccupyStockRequestModel requestModel = new OccupyStockRequestModel();
			requestModel.setTransactionId(transactionId);
			rollbackOccupyStock.rollbackOccupyStock(requestModel);

			// 发布释放事件到工作单元中去
			publishReleaseSeatEvent(requestModel, occupyType);
		}

		return true;
	}


	private void checkReleaseStock(ReleaseStockReqsModel releaseStockReqsModel) {
		if (CheckUtils.isNull(releaseStockReqsModel)) {
			throw new TheaterException(TheaterExceptionCode.PARAMETER_EMPTY);
		}
		if (CheckUtils.isNull(releaseStockReqsModel.getReleaseStockReqs())) {
			throw new TheaterException(TheaterExceptionCode.PARAMETER_EMPTY);
		}
		if (CheckUtils.isNull(releaseStockReqsModel.getTransactionId())) {
			throw new TheaterException(TheaterExceptionCode.SEAT_RECORD_NULL_TRANSACTION);
		}
	}

	private List<OccupyStockRequestModel> getOccupyStockRequestModels(String transactionId, List<ReleaseStockReqModel> reqModels) {
		List<OccupyStockRequestModel> occupyodels = null;

		if (reqModels != null && !reqModels.isEmpty()) {
			occupyodels = new ArrayList<>();
			for (ReleaseStockReqModel releaseStockReqModel : reqModels) {
				Long productId = releaseStockReqModel.getProductId();
				Integer stockNum = releaseStockReqModel.getStockNum();

				logger.info("释放库存，库存id：{}，产品id：{}，释放数量：{}。", transactionId, productId, stockNum);

				if (productId != null && stockNum != null) {
					OccupyStockRequestModel model = new OccupyStockRequestModel();
					model.setTransactionId(transactionId);
					model.setStockNum(stockNum);
					model.setProductId(productId);
					occupyodels.add(model);
				}
			}
		}
		return occupyodels;
	}

	private Integer autoReleaseSeat(String transactionId, ReleaseStockReqsModel releaseStockReqsModel) {
		SeatRecordUpdate recordUpdate = convertNormalSeat(releaseStockReqsModel);

		SeatRecord seatRecord = new SeatRecord();
		seatRecord.setTransactionId(transactionId);
		seatRecord.setState(RecordState.EFFECTIVER.getState());
		List<SeatRecord> seatRecords = seatRecordQueryEngine.querySeatRecordByModel(seatRecord);

		// 更新集合
		List<SeatRecord> releaseRecords = null;

		Integer occupyType = null;

		if(seatRecords != null && !seatRecords.isEmpty()){
			releaseRecords = new ArrayList<>(recordUpdate.getSeatNum());
			// 修改记录占座记录状态
			Date currentDate = new Date();

			for (int i =0; i < recordUpdate.getSeatNum(); i++){
				SeatRecord record = seatRecords.get(i);
				// 创建更新数据
				SeatRecord sr = new SeatRecord();
				sr.setRecordId(record.getRecordId());
				sr.setState(0);
				sr.setUpdateTime(currentDate);
				sr.setRecordUnique(SeatRecordUtil.recordUnique(
						record.getScreeningId(),
						record.getAreaId(),
						record.getTravelDate(),
						UUID.randomUUID().toString()));
				// 添加更新数据到更新集合中
				releaseRecords.add(sr);
			}
		}

		if (releaseRecords != null && !releaseRecords.isEmpty()) {
			occupyType = seatRecords.get(0).getCategory();
			seatRecordWriteMapper.updateSeatRecords(releaseRecords);
		}

		return occupyType;
	}

	private SeatRecordUpdate convertNormalSeat(ReleaseStockReqsModel releaseStockReqsModel) {
		SeatRecordUpdate seatRecordUpdate = new SeatRecordUpdate();
		seatRecordUpdate.setTransactionId(releaseStockReqsModel.getTransactionId());
		seatRecordUpdate.setState(RecordState.INVALID.getState());
		for (ReleaseStockReqModel model : releaseStockReqsModel.getReleaseStockReqs()) {
			seatRecordUpdate.setSeatNum(seatRecordUpdate.getSeatNum() + model.getStockNum());
		}
		return seatRecordUpdate;
	}

	private List<SeatRecord> getModifyRecord(List<Long> recordIds) {
		SeatRecord record = new SeatRecord();
		record.setRecordIds(recordIds);
		List<SeatRecord> seatRecords = seatRecordQueryEngine.querySeatRecordByModel(record);
		List<SeatRecord> modfiyRecords = new ArrayList<>();
		if (CheckUtils.isNull(seatRecords) || seatRecords.size() == 0) {
			return modfiyRecords;
		}

		for (SeatRecord seatRecord : seatRecords) {
			if (seatRecord.getState() != RecordState.EFFECTIVER.getState()) {
				continue;
			}
			SeatRecord modify = new SeatRecord();
			modify.setRecordId(seatRecord.getRecordId());
			modify.setState(RecordState.INVALID.getState());
			modify.setRecordUnique(SeatRecordUtil.recordUnique(seatRecord.getScreeningId(),
					seatRecord.getAreaId(), seatRecord.getTravelDate(), UUID.randomUUID().toString()));
			modfiyRecords.add(modify);
		}
		return modfiyRecords;

	}

	/**
	 * 发布释放事件到工作单元中去
	 * @param occupyodels
     */
	private void publishReleaseSeatEvent(List<OccupyStockRequestModel> occupyodels , Integer occupyType){
		if (occupyodels == null || occupyodels.isEmpty()){
			return;
		}

		UnitOfWork unitOfWork = ThreadUnitOfWork.getOrCreateThreadUnitOfWork();

		for (OccupyStockRequestModel occupy : occupyodels){
			ReleaseSeatEvent event = new ReleaseSeatEvent();
			event.setTransactionId(occupy.getTransactionId());
			event.setProductId(occupy.getProductId());
			event.setInQuantity(occupy.getStockNum());
			event.setOccupyType(occupyType);
			unitOfWork.addEvent(event);
		}
	}

	/**
	 * 发布释放事件到工作单元中去
	 * @param requestModel
     */
	private void publishReleaseSeatEvent(OccupyStockRequestModel requestModel, Integer occupyType){
		if (requestModel == null || requestModel.getTransactionId() == null){
			return;
		}

		UnitOfWork unitOfWork = ThreadUnitOfWork.getOrCreateThreadUnitOfWork();

		ReleaseSeatEvent event = new ReleaseSeatEvent();
		event.setTransactionId(requestModel.getTransactionId());
		event.setOccupyType(occupyType);
		unitOfWork.addEvent(event);
	}
}
