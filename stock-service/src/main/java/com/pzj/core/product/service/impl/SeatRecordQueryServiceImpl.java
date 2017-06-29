/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import com.pzj.core.product.model.QueryValidSeatRecordResponse;
import com.pzj.core.util.RpcCaller;
import org.springframework.stereotype.Service;

import com.pzj.commons.utils.CheckUtils;
import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.model.seat.SeatRespModel;
import com.pzj.core.product.model.seatRecord.SeatRecordReqModel;
import com.pzj.core.product.model.statistics.AreaCollectRespModel;
import com.pzj.core.product.seatRecord.SeatRecordQueryEngine;
import com.pzj.core.product.service.SeatRecordQueryService;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 占座记录查询接口
 * @author Administrator
 * @version $Id: SeatRecordQueryServiceImpl.java, v 0.1 2017年3月20日 上午11:14:08 Administrator Exp $
 */
@Service(value = "seatRecordQueryService")
public class SeatRecordQueryServiceImpl implements SeatRecordQueryService {
	@Resource
	private SeatRecordQueryEngine seatRecordQueryEngine;

	/** 
	 *
	 */
	@Override
	public Result<ArrayList<AreaCollectRespModel>> queryAreaCollects(SeatRecordReqModel model,
			ServiceContext serviceContext) {
		Result<Boolean> checkResult = checkQueryParams(model);
		if (!checkResult.isOk()) {
			return new Result<ArrayList<AreaCollectRespModel>>(checkResult.getErrorCode(), checkResult.getErrorMsg());
		}
		ArrayList<AreaCollectRespModel> resultModels = seatRecordQueryEngine.queryAreaCollects(model, serviceContext);
		return new Result<>(resultModels);
	}

	/** 
	 * @see com.pzj.core.product.service.SeatRecordQueryService#querySeatStateByRecord(com.pzj.core.product.model.seatRecord.SeatRecordReqModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ArrayList<SeatRespModel>> querySeatStateByRecord(SeatRecordReqModel model, ServiceContext context) {
		Result<Boolean> checkResult = checkQueryParams(model);
		if (!checkResult.isOk()) {
			return new Result<ArrayList<SeatRespModel>>(checkResult.getErrorCode(), checkResult.getErrorMsg());
		}
		try {
			ArrayList<SeatRespModel> seatRespModels = seatRecordQueryEngine.querySeatStateByRecord(model, context);
			return new Result<ArrayList<SeatRespModel>>(seatRespModels);
		} catch (StockException e) {
			Result<ArrayList<SeatRespModel>> stockExptionResult = new Result<ArrayList<SeatRespModel>>(e.getErrCode(),
					e.getMessage());
			return stockExptionResult;
		} catch (Exception e) {
			Result<ArrayList<SeatRespModel>> exptionResult = new Result<ArrayList<SeatRespModel>>(
					StockExceptionCode.STOCK_ERR_CODE, e.getMessage());
			e.printStackTrace();
			return exptionResult;
		}
	}

	@Override
	public Result<QueryValidSeatRecordResponse> queryValidSeatRecordByTransactionId(final String transactionId) {
		Result<QueryValidSeatRecordResponse> result = new RpcCaller<QueryValidSeatRecordResponse>() {
			@Override
			public QueryValidSeatRecordResponse call() {
				return seatRecordQueryEngine.queryValidSeatRecordByTransactionId(transactionId);
			}
		}.args(transactionId).run();
		return result;
	}

	private Result<Boolean> checkQueryParams(SeatRecordReqModel model) {
		if (CheckUtils.isNull(model)) {
			if (CheckUtils.isNull(model.getScreeingId())) {
				return new Result<Boolean>(StockExceptionCode.PARAM_ERR_CODE, StockExceptionCode.PARAM_ERR_MSG);
			}
		}
		if (CheckUtils.isNull(model.getScreeingId())) {
			return new Result<Boolean>(StockExceptionCode.PARAM_ERR_CODE, "场次id为空");
		}
		if (CheckUtils.isNull(model.getTheaterDate())) {
			return new Result<Boolean>(StockExceptionCode.PARAM_ERR_CODE, "游玩时间为空");
		}
		return new Result<Boolean>();
	}

}
