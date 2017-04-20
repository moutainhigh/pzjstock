/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.SeatChartModel;
import com.pzj.core.stock.seat.AddSeatChartInitSeatEngine;
import com.pzj.core.stock.seat.CreateSeatChartEngine;
import com.pzj.core.stock.seat.SeatChartQueryEngine;
import com.pzj.core.stock.service.SeatChartService;
import com.pzj.core.stock.validator.seat.QuerySeatChartByAreaScreRelValidator;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: SeatChartServiceImpl.java, v 0.1 2016年8月11日 上午10:50:21 Administrator Exp $
 */
@Service("seatChartService")
public class SeatChartServiceImpl implements SeatChartService {
	private static final Logger logger = LoggerFactory.getLogger(SeatChartServiceImpl.class);
	@Resource(name = "createSeatChartValidator")
	private ObjectConverter<SeatChartModel, ServiceContext, Boolean> createSeatChartValidator;
	@Resource(name = "querySeatChartByAreaScreRelValidator")
	private QuerySeatChartByAreaScreRelValidator querySeatChartByAreaScreRelValidator;

	@Resource(name = "createSeatChartEngine")
	private CreateSeatChartEngine createSeatChartEngine;
	@Resource(name = "seatChartQueryEngine")
	private SeatChartQueryEngine seatChartQueryEngine;
	@Resource(name = "addSeatChartInitSeatEngine")
	private AddSeatChartInitSeatEngine addSeatChartInitSeatEngine;

	/** 
	 * @see com.pzj.core.stock.service.SeatChartService#addSeatChart(com.pzj.core.stock.model.SeatChartModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Boolean> addSeatChart(SeatChartModel seatChartModel, ServiceContext context) {
		Result<Boolean> result = new Result<Boolean>(Boolean.FALSE);
		boolean legality = createSeatChartValidator.convert(seatChartModel, context);
		if (!legality) {
			logger.error("create seatChart fail, Illegal params. request: {}, context: {}.", seatChartModel, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("create seatChart. request: {}, context: {}.", seatChartModel, context);
		}

		try {
			createSeatChartEngine.createSeatChart(seatChartModel);
			result.setData(Boolean.TRUE);
		} catch (Throwable e) {
			logger.error("create seatChart fail. request: " + seatChartModel + ", context: " + context, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("create seatChart success. result: {}.", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.SeatChartService#querySeatChartByScenicAndArea(com.pzj.core.stock.model.SeatChartModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ArrayList<SeatChartModel>> querySeatChartByScenicAndAreaRel(SeatChartModel seatChartModel,
			ServiceContext context) {
		Result<ArrayList<SeatChartModel>> result = new Result<ArrayList<SeatChartModel>>();
		ParamModel paramModel = querySeatChartByAreaScreRelValidator.convert(seatChartModel, context);
		if (!paramModel.paramIsOk()) {
			logger.error("query seatChart by scenic and area fail, Illegal params. request: {}, context: {}.",
					seatChartModel, context);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query seatChart by scenic and area. request: {}, context: {}.", seatChartModel, context);
		}

		try {
			ArrayList<SeatChartModel> chartModelList = seatChartQueryEngine
					.querySeatChartByScenicAndArea(seatChartModel);
			result.setData(chartModelList);

		} catch (Throwable e) {
			logger.error("query seatChart by scenic and area. request: " + seatChartModel + ", context: " + context, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query seatChart by scenic and area. result: {}", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.SeatChartService#addSeatChartInitSeat(com.pzj.core.stock.model.SeatChartModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Boolean> addSeatChartInitSeat(SeatChartModel seatChartModel, ServiceContext serviceContext) {
		Result<Boolean> result = new Result<Boolean>(Boolean.FALSE);
		boolean legality = createSeatChartValidator.convert(seatChartModel, null);
		if (!legality) {
			logger.error("add seatChart init seat fail, Illegal params. request: {}, context: {}.", seatChartModel,
					serviceContext);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("add seatChart init seat. request: {}, context: {}.", seatChartModel, serviceContext);
		}

		try {
			boolean flag = addSeatChartInitSeatEngine.addSeatChartInitSeat(seatChartModel);
			result.setData(flag);
		} catch (Throwable e) {
			logger.error("add seatChart init seat fail. request: " + seatChartModel + ", context: " + serviceContext, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("add seatChart init seat success. result: {}.", JSONConverter.toJson(result));
		}

		return result;
	}
}
