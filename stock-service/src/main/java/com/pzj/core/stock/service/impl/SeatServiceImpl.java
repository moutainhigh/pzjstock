/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.context.UserSeatResponse;
import com.pzj.core.stock.model.CheckSeatsOptionalModel;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.SeatModel;
import com.pzj.core.stock.model.SeatsOptionalResponse;
import com.pzj.core.stock.model.UserSeatModel;
import com.pzj.core.stock.seat.CheckSeatsOptionalEngine;
import com.pzj.core.stock.seat.CreateSeatEngine;
import com.pzj.core.stock.seat.UserSeatQueryEngine;
import com.pzj.core.stock.service.SeatService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: SeatServiceImpl.java, v 0.1 2016年8月4日 下午5:10:33 Administrator Exp $
 */
@Service("seatService")
public class SeatServiceImpl implements SeatService {
	private static final Logger logger = LoggerFactory.getLogger(SeatServiceImpl.class);
	@Resource(name = "seatValidator")
	private ObjectConverter<SeatModel, ServiceContext, Boolean> seatValidator;
	@Resource(name = "userSeatValidator")
	private ObjectConverter<UserSeatModel, ServiceContext, ParamModel> userSeatValidator;
	@Resource(name = "checkSeatsOptionalValidator")
	private ObjectConverter<CheckSeatsOptionalModel, ServiceContext, ParamModel> checkSeatsOptionalValidator;

	@Resource(name = "createSeatEngine")
	private CreateSeatEngine createSeatEngine;
	@Resource(name = "userSeatQueryEngine")
	private UserSeatQueryEngine userSeatQueryEngine;
	@Resource(name = "checkSeatsOptionalEngine")
	private CheckSeatsOptionalEngine checkSeatsOptionalEngine;

	@Override
	public Result<Boolean> createSeat(SeatModel seatModel, ServiceContext context) {
		Result<Boolean> result = new Result<Boolean>(Boolean.FALSE);
		boolean legality = seatValidator.convert(seatModel, context);
		if (!legality) {
			logger.error("create seat fail, Illegal params. request: {}, context: {}.", seatModel, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("create seat. request: {}, context: {}.", seatModel, context);
		}

		try {
			createSeatEngine.createSeat(seatModel);
			result.setData(Boolean.TRUE);
		} catch (Throwable e) {
			logger.error("create seat fail. request: " + seatModel + ", context: " + context, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("create seat success. result: {}.", JSONConverter.toJson(result));
		}
		return result;
	}

	@Override
	public Result<UserSeatResponse> queryAlreadyOccupySeat(UserSeatModel userSeatModel, ServiceContext context) {
		Result<UserSeatResponse> result = new Result<UserSeatResponse>();
		ParamModel paramModel = userSeatValidator.convert(userSeatModel, null);
		if (!paramModel.paramIsOk()) {
			logger.error("query already occupy seat fail, Illegal params. request: {}, context: {}.", userSeatModel,
					context);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query already occupy seat. request: {}, context: {}.", userSeatModel, context);
		}

		try {
			List<String> userOccupySeatList = userSeatQueryEngine.queryOccupySeats(userSeatModel);

			//封装返回对象
			UserSeatResponse userSeatResponse = new UserSeatResponse();
			userSeatResponse.setOccupySeat(userOccupySeatList);
			result.setData(userSeatResponse);

		} catch (Throwable e) {
			logger.error("query already occupy seat fail. request: " + userSeatModel + ", context: " + context, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query already occupy seat success. result: {}", JSONConverter.toJson(result));
		}

		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.SeatService#judgeSeatWheatherOptional(com.pzj.core.stock.model.CheckSeatsOptionalModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<SeatsOptionalResponse> judgeSeatWheatherOptional(CheckSeatsOptionalModel model, ServiceContext context) {
		Result<SeatsOptionalResponse> result = new Result<SeatsOptionalResponse>();
		ParamModel paramModel = checkSeatsOptionalValidator.convert(model, context);
		if (!paramModel.paramIsOk()) {
			logger.error("check seats wheather optional fail, Illegal params. request: {}, context: {}.", model,
					context);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("check seats wheather optional. request: {}, context: {}.", model, context);
		}

		try {
			result.setData(checkSeatsOptionalEngine.checkSeatsOptional(model));
		} catch (Throwable e) {
			logger.error("check seats wheather optional fail. request: " + model + ", context: " + context, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("check seats wheather optional success. result: {}.", JSONConverter.toJson(result));
		}

		return result;
	}
}
