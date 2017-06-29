/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import com.pzj.commons.utils.CheckUtils;
import com.pzj.core.common.utils.StockGlobalDict;
import com.pzj.core.dock.service.DockService;
import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.enums.RecordCategory;
import com.pzj.core.product.model.OccupyStockReqsModel;
import com.pzj.core.product.model.OccupyStockResponse;
import com.pzj.core.product.model.QuerySeatRecordResponse;
import com.pzj.core.product.model.QueryValidSeatRecordResponse;
import com.pzj.core.product.model.ReleaseStockReqsModel;
import com.pzj.core.product.model.seat.SeatInfoModel;
import com.pzj.core.product.model.seatRecord.SeatRecordCreateReqModel;
import com.pzj.core.product.model.seatRecord.SeatRecordUpdateReqModel;
import com.pzj.core.product.seatRecord.SeatRecordCreateEngine;
import com.pzj.core.product.seatRecord.SeatRecordQueryEngine;
import com.pzj.core.product.seatRecord.SeatRecordUpdateEngine;
import com.pzj.core.product.seatRecord.SeatRecordWriteEngine;
import com.pzj.core.product.service.SeatRecordService;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.util.RpcCaller;
import com.pzj.core.work.Event;
import com.pzj.core.work.UnitOfWork;
import com.pzj.core.work.support.ThreadUnitOfWork;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

/**
 * 占座锁座操作实现类
 * @author Administrator
 * @version $Id: SeatRecordServiceImpl.java, v 0.1 2017年3月15日 上午10:16:16 Administrator Exp $
 */
@Service(value = "seatRecordService")
public class SeatRecordServiceImpl implements SeatRecordService {
	private static final Logger logger = LoggerFactory.getLogger(SeatRecordServiceImpl.class);

	@Resource
	private SeatRecordCreateEngine seatRecordCreateEngine;
	@Resource
	private SeatRecordUpdateEngine seatRecordUpdateEngine;
	@Resource
	private SeatRecordWriteEngine seatRecordWriteEngine;
	@Resource
	private SeatRecordQueryEngine seatRecordQueryEngine;
	@Resource
	private DockService dockService;

	@Resource
	private PlatformTransactionManager platformTransactionManager;

	@Override
	public Result<OccupyStockResponse> occupyStock(final OccupyStockReqsModel occupyStockReqsModel) {
		logger.info("占库存入参：{}", JSONConverter.toJson(occupyStockReqsModel));

		Result<OccupyStockResponse> result = new RpcCaller<OccupyStockResponse>() {

			@Override
			public OccupyStockResponse call() {
				UnitOfWork unitOfWork = ThreadUnitOfWork.getOrCreateThreadUnitOfWork();
				unitOfWork.clear();
				unitOfWork.enable();

				seatRecordWriteEngine.occupyStock(occupyStockReqsModel);

				List<Event> events = unitOfWork.commit();

				logger.info("工作单元所有事件：{}", JSONConverter.toJson(events));

				dockService.handleSeatEvent(events);

				return null;
			}

		}.args(occupyStockReqsModel).runWithTransaction(platformTransactionManager);

		logger.info("占库存出参：{}", JSONConverter.toJson(result));
		return result;
	}

	@Override
	public Result<Boolean> occupyStockForDock(final OccupyStockReqsModel occupyStockReqsModel) {
		logger.info("占库存入参（Dock）：{}", JSONConverter.toJson(occupyStockReqsModel));

		Result<Boolean> result = new RpcCaller<Boolean>() {

			@Override
			public Boolean call() {
				UnitOfWork unitOfWork = ThreadUnitOfWork.getOrCreateThreadUnitOfWork();
				unitOfWork.clear();
				unitOfWork.disable();

				return seatRecordWriteEngine.occupyStock(occupyStockReqsModel);
			}

		}.args(occupyStockReqsModel).runWithTransaction(platformTransactionManager);

		logger.info("占库存出参（Dock）：{}", JSONConverter.toJson(result));

		return result;
	}

	@Override
	public Result<Boolean> releaseStock(final ReleaseStockReqsModel releaseStockReqsModel) {
		return releaaseStock(releaseStockReqsModel, null);
	}

	@Override
	public Result<Boolean> releaaseStock(final ReleaseStockReqsModel releaseStockReqsModel, final ServiceContext context) {
		logger.info("释放库存入参:{}", JSONConverter.toJson(releaseStockReqsModel));

		Result<Boolean> result = new RpcCaller<Boolean>() {

			@Override
			public Boolean call() {
				UnitOfWork unitOfWork = ThreadUnitOfWork.getOrCreateThreadUnitOfWork();
				unitOfWork.clear();
				unitOfWork.enable();

				seatRecordUpdateEngine.releaseNormalSeat(releaseStockReqsModel);

				if (needCallDock(releaseStockReqsModel)) {
					List<Event> events = unitOfWork.commit();

					logger.info("工作单元所有事件:{}", JSONConverter.toJson(events));

					dockService.handleSeatEvent(events);
				}

				return true;
			}

		}.args(releaseStockReqsModel).runWithTransaction(platformTransactionManager);

		logger.info("释放库存出参：{}", JSONConverter.toJson(result));

		return result;
	}

	private boolean needCallDock(ReleaseStockReqsModel releaseStockReqsModel) {
		if (releaseStockReqsModel != null && releaseStockReqsModel.getReleaseFlag() != null
				&& releaseStockReqsModel.getReleaseFlag().equals(StockGlobalDict.ReleaseFlag.cancelOrder)) {
			return true;
		}
		return false;
	}

	@Override
	public Result<Boolean> releaseStockForDock(final ReleaseStockReqsModel releaseStockReqsModel) {
		return releaaseStockForDock(releaseStockReqsModel);
	}

	@Override
	public Result<Boolean> releaaseStockForDock(final ReleaseStockReqsModel releaseStockReqsModel) {
		logger.info("释放库存入参:{}", JSONConverter.toJson(releaseStockReqsModel));

		Result<Boolean> result = new RpcCaller<Boolean>() {

			@Override
			public Boolean call() {
				UnitOfWork unitOfWork = ThreadUnitOfWork.getOrCreateThreadUnitOfWork();
				unitOfWork.clear();
				unitOfWork.disable();

				return seatRecordUpdateEngine.releaseNormalSeat(releaseStockReqsModel);
			}

		}.args(releaseStockReqsModel).runWithTransaction(platformTransactionManager);

		logger.info("释放库存出参:{}", JSONConverter.toJson(releaseStockReqsModel));

		return result;
	}

	/**
	 * 解锁座位释放库存
	 */
	@Override
	public Result<Boolean> releaseSeat(final SeatRecordUpdateReqModel model, final ServiceContext serviceContext) {
		logger.info("解锁座位释放库存入参:{}", JSONConverter.toJson(model));

		Result<Boolean> result = new RpcCaller<Boolean>() {

			@Override
			public Boolean call() {
				releaseCheckParams(model, serviceContext);

				//修改占座记录为无效
				Boolean releaseLockSeat = seatRecordUpdateEngine.releaseLockSeat(model, serviceContext);
				return releaseLockSeat;
			}

		}.args(model).runWithTransaction(platformTransactionManager);

		logger.info("解锁座位释放库存出参：{}", JSONConverter.toJson(result));

		return result;
	}

	/**
	 * 解锁座位释放库存 参数校验
	 * @param model
	 * @param serviceContext
	 */
	private void releaseCheckParams(SeatRecordUpdateReqModel model, ServiceContext serviceContext) {
		if (CheckUtils.isNull(model) || CheckUtils.isNull(serviceContext)) {
			throw new TheaterException(StockExceptionCode.PARAM_ERR_CODE, StockExceptionCode.PARAM_ERR_MSG);
		}
		if (CheckUtils.isNull(model.getOperateUserId())) {
			throw new TheaterException(StockExceptionCode.PARAM_ERR_CODE, "操作人id为空");
		}
		if (CheckUtils.isNull(model.getTransactionId())
				&& (CheckUtils.isNull(model.getScreeingId()) || CheckUtils.isNull(model.getTheaterDate()))) {
			throw new TheaterException(StockExceptionCode.PARAM_ERR_CODE, "订单信息、场次信息、演出信息不合法");
		}
	}

	/**
	 * 锁座占库存
	 */
	@Override
	public Result<Boolean> lockingSeat(final SeatRecordCreateReqModel model, final ServiceContext serviceContext) {
		logger.info("锁座占库存入参:{}", JSONConverter.toJson(model));

		Result<Boolean> result = new RpcCaller<Boolean>() {

			@Override
			public Boolean call() {
				lockCheckParams(model, serviceContext);

				model.setRecordCategory(RecordCategory.LOCKING);

				Boolean createSeatRecord = seatRecordCreateEngine.createSeatRecord(model, serviceContext);
				return createSeatRecord;
			}

		}.args(model).runWithTransaction(platformTransactionManager);

		logger.info("锁座占库存出参：{}", JSONConverter.toJson(result));

		return result;
	}

	/**
	 * 锁座占库存 参数校验
	 * @param model
	 * @param serviceContext
	 */
	private void lockCheckParams(SeatRecordCreateReqModel model, ServiceContext serviceContext) {
		if (CheckUtils.isNull(model) || CheckUtils.isNull(serviceContext)) {
			throw new TheaterException(StockExceptionCode.PARAM_ERR_CODE, StockExceptionCode.PARAM_ERR_MSG);
		}
		if (CheckUtils.isNull(model.getScreeingId())) {
			throw new TheaterException(StockExceptionCode.PARAM_ERR_CODE, "场次id为空");
		}
		if (CheckUtils.isNull(model.getScreeingId())) {
			throw new TheaterException(StockExceptionCode.PARAM_ERR_CODE, "场次id为空");
		}
		if (CheckUtils.isNull(model.getOperateUserId())) {
			throw new TheaterException(StockExceptionCode.PARAM_ERR_CODE, "操作人id为空");
		}
		if (CheckUtils.isNull(model.getLockSeatType())) {
			throw new TheaterException(StockExceptionCode.PARAM_ERR_CODE, "锁座类型为空");
		}
		if (CheckUtils.isNull(model.getSeatInfos())) {
			throw new TheaterException(StockExceptionCode.PARAM_ERR_CODE, "锁座信息为空");
		}
		List<SeatInfoModel> seatInfoModels = model.getSeatInfos();
		for (SeatInfoModel seatInfoModel : seatInfoModels) {
			if (CheckUtils.isNull(seatInfoModel.getAreaId())) {
				throw new TheaterException(StockExceptionCode.PARAM_ERR_CODE, "区域id为空");
			}
			if (CheckUtils.isNull(seatInfoModel.getSeatId())) {
				throw new TheaterException(StockExceptionCode.PARAM_ERR_CODE, "座位id为空");
			}
			if (CheckUtils.isNull(seatInfoModel.getSeatName())) {
				throw new TheaterException(StockExceptionCode.PARAM_ERR_CODE, "座位名称为空");
			}
		}
	}

	/** 
	 * 自动释放座位及库存
	 */
	@Override
	public Result<Boolean> checkavailableSeatRecord(final ServiceContext serviceContext) {

		if (logger.isInfoEnabled()) {
			logger.info("开始自动释放过期的锁座记录");
		}
		QueryValidSeatRecordResponse queryValidSeatRecordResponse = seatRecordQueryEngine.queryOverdueSeatRecords();
		List<QuerySeatRecordResponse> querySeatRecordResponses = queryValidSeatRecordResponse == null ? new ArrayList<QuerySeatRecordResponse>()
				: queryValidSeatRecordResponse.getQuerySeatRecordResponses();
		if (CheckUtils.isNull(querySeatRecordResponses) || querySeatRecordResponses.isEmpty()) {
			if (logger.isInfoEnabled()) {
				logger.info("自动释放过期的锁座结束，没有发现可释放的座位记录。");
			}
			return new Result<>();
		}
		for (QuerySeatRecordResponse sr : querySeatRecordResponses) {
			try {
				seatRecordUpdateEngine.checkavailableSeatRecord(sr, new ServiceContext());
			} catch (Exception e) {
				logger.error("占座id为[" + sr.getRecordId() + "]的记录释放失败,参数为：{}", JSONConverter.toJson(sr));
			}
		}
		return new Result<>();
	}
}
