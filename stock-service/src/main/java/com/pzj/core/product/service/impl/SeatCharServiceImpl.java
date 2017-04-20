package com.pzj.core.product.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.model.seat.CreateSeatCharReqModel;
import com.pzj.core.product.model.seat.ModifySeatCharReqModel;
import com.pzj.core.product.model.seat.SeatReqModel;
import com.pzj.core.product.model.seat.SeatRespModel;
import com.pzj.core.product.model.seat.TheaterSeatChartRespModel;
import com.pzj.core.product.seatchar.QuerySeatchartEngine;
import com.pzj.core.product.seatchar.SeatCharWriteEngine;
import com.pzj.core.product.service.SeatCharService;
import com.pzj.core.util.LogUtil;
import com.pzj.core.util.RpcCaller;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

@Service("seatCharService")
public class SeatCharServiceImpl implements SeatCharService {

	private final Logger logger = LoggerFactory.getLogger(SeatCharServiceImpl.class);

	@Resource(name = "querySeatchartEngine")
	private QuerySeatchartEngine querySeatchartEngine;
	@Resource
	private SeatCharWriteEngine seatCharWriteEngine;

	@Override
	public Result<Boolean> createSeatChar(final CreateSeatCharReqModel createSeatCharReqModel) {
		LogUtil.loggerPrintInfo(logger, "修改场次，入参：{}", createSeatCharReqModel);

		Result<Boolean> result = new RpcCaller<Boolean>() {
			@Override
			public Boolean call() {
				return seatCharWriteEngine.createSeatChar(createSeatCharReqModel);
			}
		}.run();

		LogUtil.loggerPrintInfo(logger, "修改场次，出参：{}", result);

		return result;
	}

	@Override
	public Result<Boolean> modifySeatChar(final ModifySeatCharReqModel modifySeatCharReqModel) {
		LogUtil.loggerPrintInfo(logger, "修改场次，入参：{}", modifySeatCharReqModel);

		Result<Boolean> result = new RpcCaller<Boolean>() {
			@Override
			public Boolean call() {
				return seatCharWriteEngine.modifySeatChar(modifySeatCharReqModel);
			}
		}.run();

		LogUtil.loggerPrintInfo(logger, "修改场次，出参：{}", result);

		return result;
	}

	@Override
	public Result<ArrayList<SeatRespModel>> queryNewestTheaterSeatchart(SeatReqModel reqModel,
			ServiceContext serviceContext) {
		Result<ArrayList<SeatRespModel>> result = new Result<ArrayList<SeatRespModel>>();
		if (!checkNewestSeatChartParam(reqModel, Boolean.FALSE)) {
			logger.error("query newest theater seatchart,illegal request param:{}", JSONConverter.toJson(reqModel));
			CommonUtils.setParamErr(result);
			return result;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query newest theater seatchart,request param:{}", JSONConverter.toJson(reqModel));
		}
		try {
			ArrayList<SeatRespModel> seatResp = querySeatchartEngine.queryNewestTheaterSeatChart(reqModel,
					serviceContext);
			result.setData(seatResp);
		} catch (Throwable e) {
			logger.error("query newest theater seatchart fail ,request:{},context:{}.", JSONConverter.toJson(reqModel),
					serviceContext, e);
			CommonUtils.convertException(e, result);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query newest theater seatchart.result:{}", JSONConverter.toJson(result));
		}
		return result;
	}

	private Boolean checkNewestSeatChartParam(SeatReqModel reqModel, Boolean isArea) {
		if (reqModel == null) {
			return Boolean.FALSE;
		}
		if (reqModel.getShowTime() == null || reqModel.getScenicId() == null || reqModel.getScreeingId() == null) {
			return Boolean.FALSE;
		}
		if (isArea && reqModel.getAreaId() == null) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@Override
	public Result<ArrayList<SeatRespModel>> queryNewestAreaSeatchart(SeatReqModel reqModel,
			ServiceContext serviceContext) {
		Result<ArrayList<SeatRespModel>> result = new Result<ArrayList<SeatRespModel>>();
		if (!checkNewestSeatChartParam(reqModel, Boolean.TRUE)) {
			logger.error("query newest area seatchart,illegal request param:{}", JSONConverter.toJson(reqModel));
			CommonUtils.setParamErr(result);
			return result;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query newest area seatchart,request param:{}", JSONConverter.toJson(reqModel));
		}
		try {
			ArrayList<SeatRespModel> seatResp = querySeatchartEngine.queryNewestAreaSeatChart(reqModel, serviceContext);
			result.setData(seatResp);
		} catch (Throwable e) {
			logger.error("query newest area seatchart fail ,request:{},context:{}.", JSONConverter.toJson(reqModel),
					serviceContext, e);
			CommonUtils.convertException(e, result);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query newest area seatchart.result:{}", JSONConverter.toJson(result));
		}
		return result;
	}

	@Override
	public Result<ArrayList<TheaterSeatChartRespModel>> queryTheaterSeatchart(Long theaterId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Integer> queryAreaSeatTotal(Long areaId, ServiceContext serviceContext) {
		Result<Integer> result = new Result<Integer>();

		if (CommonUtils.checkLongIsNull(areaId, Boolean.TRUE)) {
			logger.error("query area seat total,illegal request param:{}", areaId);
			CommonUtils.setParamErr(result);
			return result;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query area seat total,request param:{}", areaId);
		}
		try {
			result.setData(querySeatchartEngine.totalAreaSeat(areaId));
		} catch (Throwable e) {
			logger.error("query newest area seatchart fail ,request:{},context:{}.", areaId, serviceContext, e);
			CommonUtils.convertException(e, result);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query area seat total.result:{}", JSONConverter.toJson(result));
		}
		return result;
	}

}
