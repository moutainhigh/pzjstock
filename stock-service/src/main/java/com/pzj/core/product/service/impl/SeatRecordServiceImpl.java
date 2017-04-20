/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.commons.utils.CheckUtils;
import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.common.exception.TheaterExceptionCode;
import com.pzj.core.product.enums.RecordCategory;
import com.pzj.core.product.model.OccupyStockReqsModel;
import com.pzj.core.product.model.ReleaseStockReqModel;
import com.pzj.core.product.model.ReleaseStockReqsModel;
import com.pzj.core.product.model.seat.SeatInfoModel;
import com.pzj.core.product.model.seatRecord.SeatRecordCreateReqModel;
import com.pzj.core.product.model.seatRecord.SeatRecordUpdateReqModel;
import com.pzj.core.product.seatRecord.SeatRecordCreateEngine;
import com.pzj.core.product.seatRecord.SeatRecordUpdateEngine;
import com.pzj.core.product.seatRecord.SeatRecordWriteEngine;
import com.pzj.core.product.service.SeatRecordService;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.util.LogUtil;
import com.pzj.core.util.RpcCaller;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

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

	@Override
	public Result<Boolean> occupyStock(final OccupyStockReqsModel occupyStockReqsModel) {
		LogUtil.loggerPrintInfo(logger, "占库存入参：{}", occupyStockReqsModel);

		Result<Boolean> result = new RpcCaller<Boolean>() {
			@Override
			public Boolean call() {
				return seatRecordWriteEngine.occupyStock(occupyStockReqsModel);
			}
		}.run();

		LogUtil.loggerPrintInfo(logger, "占库存出参：{}", result);
		return result;
	}

	@Override
	public Result<Boolean> releaaseStock(final ReleaseStockReqsModel releaseStockReqsModel, final ServiceContext context) {
		LogUtil.loggerPrintInfo(logger, "释放库存入参:{}", releaseStockReqsModel);
		Result<Boolean> result = new RpcCaller<Boolean>() {
			@Override
			public Boolean call() {
				checkReleaseStock(releaseStockReqsModel);
				return seatRecordUpdateEngine.releaseNormalSeat(releaseStockReqsModel, context);
			};

		}.run();

		LogUtil.loggerPrintInfo(logger, "释放库存出参：{}", result);

		return result;
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
		for (ReleaseStockReqModel reqModel : releaseStockReqsModel.getReleaseStockReqs()) {
			if (CheckUtils.isNull(reqModel.getProductId())) {
				throw new TheaterException(TheaterExceptionCode.STOCK_NULL_PRODUCT);
			}
		}
	}

	/**
	 * @see SeatRecordService#releaseSeat(com.pzj.core.product.model.seatRecord.SeatRecordUpdateReqModel, com.pzj.framework.context.ServiceContext)
	 * 解锁座位释放库存
	 */
	@Override
	public Result<Boolean> releaseSeat(SeatRecordUpdateReqModel model, ServiceContext serviceContext) {
		if (CheckUtils.isNull(model) || CheckUtils.isNull(serviceContext)) {
			Result<Boolean> result = new Result<Boolean>(StockExceptionCode.PARAM_ERR_CODE,
					StockExceptionCode.PARAM_ERR_MSG);
			result.setData(false);
			return result;
		}
		Result<Boolean> checkResult = releaseCheckParams(model);
		if (!checkResult.isOk()) {
			return checkResult;
		}
		try {
			//修改占座记录为无效
			Boolean updateResult = seatRecordUpdateEngine.releaseLockSeat(model, serviceContext);
			return new Result<>(updateResult);

		} catch (StockException e) {
			return new Result<Boolean>(e.getErrCode(), e.getMessage());
		} catch (Exception e) {
			return new Result<Boolean>(StockExceptionCode.STOCK_ERR_CODE, e.getMessage());
		}
	}

	private Result<Boolean> releaseCheckParams(SeatRecordUpdateReqModel model) {
		Result<Boolean> result = new Result<Boolean>(false);
		result.setErrorCode(StockExceptionCode.PARAM_ERR_CODE);
		if (CheckUtils.isNull(model.getOperateUserId())) {
			result.setErrorMsg("操作人id为空");
			return result;
		}
		if (CheckUtils.isNull(model.getTransactionId())
				&& (CheckUtils.isNull(model.getScreeingId()) || CheckUtils.isNull(model.getTheaterId()))) {
			result.setErrorMsg("订单信息、场次信息、演出信息不合法");
			return result;
		}
		if (CheckUtils.isNull(model.getTransactionId())) {
			if (CheckUtils.isNull(model.getSeatInfos())) {
				result.setErrorMsg("释放座位信息为空");
				return result;
			}
			List<SeatInfoModel> seatInfoModels = model.getSeatInfos();
			for (SeatInfoModel seatInfoModel : seatInfoModels) {
				if (CheckUtils.isNull(seatInfoModel.getAreaId()) || CheckUtils.isNull(seatInfoModel.getSeatId())) {
					result.setErrorMsg("释放座位信息为不合法");
					return result;
				}
			}
		}
		return new Result<Boolean>();
	}

	/** 
	 * @see com.pzj.core.product.service.SeatRecordService#lockingSeat(com.pzj.core.product.model.seatRecord.SeatRecordCreateReqModel, com.pzj.framework.context.ServiceContext)
	 * 锁座占库存
	 *
	 */
	@Override
	public Result<Boolean> lockingSeat(SeatRecordCreateReqModel model, ServiceContext serviceContext) {
		if (CheckUtils.isNull(model) || CheckUtils.isNull(serviceContext)) {
			Result<Boolean> result = new Result<Boolean>(StockExceptionCode.PARAM_ERR_CODE,
					StockExceptionCode.PARAM_ERR_MSG);
			result.setData(false);
			return result;
		}
		lockCheckParams(model);
		model.setRecordCategory(RecordCategory.LOCKING);
		try {
			Boolean createResult = seatRecordCreateEngine.createSeatRecord(model, serviceContext);
			return new Result<>(createResult);
		} catch (StockException e) {
			return new Result<Boolean>(e.getErrCode(), e.getMessage());
		} catch (TheaterException e) {
			return new Result<Boolean>(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return new Result<Boolean>(StockExceptionCode.STOCK_ERR_CODE, e.getMessage());
		}
	}

	private void lockCheckParams(SeatRecordCreateReqModel model) {
		Result<Boolean> result = new Result<Boolean>(false);
		result.setErrorCode(StockExceptionCode.PARAM_ERR_CODE);
		if (CheckUtils.isNull(model.getScreeingId())) {
			throw new StockException("场次id为空");
		}
		if (CheckUtils.isNull(model.getScreeingId())) {
			throw new StockException("场次id为空");
		}
		if (CheckUtils.isNull(model.getOperateUserId())) {
			throw new StockException("操作人id为空");
		}
		if (CheckUtils.isNull(model.getLockSeatType())) {
			throw new StockException("锁座类型为空");
		}
		if (CheckUtils.isNull(model.getSeatInfos())) {
			throw new StockException("锁座信息为空");
		}
		List<SeatInfoModel> seatInfoModels = model.getSeatInfos();
		for (SeatInfoModel seatInfoModel : seatInfoModels) {
			if (CheckUtils.isNull(seatInfoModel.getAreaId()) || CheckUtils.isNull(seatInfoModel.getSeatId())) {
				throw new StockException("锁座信息为不合法");
			}
		}
	}

	/** 
	 * @see com.pzj.core.product.service.SeatRecordService#checkavailableSeatRecord(com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Boolean> checkavailableSeatRecord(ServiceContext serviceContext) {
		Boolean checkResult = seatRecordUpdateEngine.checkavailableSeatRecord(serviceContext);
		return new Result<>(checkResult);
	}

}
