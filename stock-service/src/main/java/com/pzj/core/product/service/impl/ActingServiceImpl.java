/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.acting.ActingCreateEngine;
import com.pzj.core.product.acting.ActingUpdateEngine;
import com.pzj.core.product.acting.AddActingEngine;
import com.pzj.core.product.model.ActingModel;
import com.pzj.core.product.model.AddActingModel;
import com.pzj.core.product.service.ActingService;
import com.pzj.core.product.validator.acting.AddActingValidator;
import com.pzj.core.product.validator.acting.CreateActingValidator;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 演绎写服务实现
 * @author dongchunfu
 * @version $Id: ActingServiceImpl.java, v 0.1 2016年8月1日 下午3:09:07 dongchunfu Exp $
 */
@Service(value = "actingService")
public class ActingServiceImpl implements ActingService {
	private static final Logger logger = LoggerFactory.getLogger(ActingServiceImpl.class);

	@Resource(name = "actingCreateEngine")
	private ActingCreateEngine actingCreateEngine;
	@Resource(name = "actingUpdateEngine")
	private ActingUpdateEngine actingUpdateEngine;
	@Resource(name = "addActingEngine")
	private AddActingEngine addActingEngine;

	@Resource(name = "createActingValidator")
	private CreateActingValidator createActingValidator;
	@Resource(name = "addActingValidator")
	private AddActingValidator addActingValidator;

	/** 
	 * @see com.pzj.stock.service.product.ActingService#createActing(com.pzj.stock.vo.product.ActingModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Long> createActing(ActingModel model, ServiceContext context) {
		Result<Long> result = new Result<Long>();
		Boolean success = createActingValidator.convert(model, context);
		if (!success) {
			logger.error("create acting illegal param , request：{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("creating acting ,request：{},context:{}.", model, context);
		}
		try {
			Long count = actingCreateEngine.insertActing(model, context);
			result.setData(count);
		} catch (Throwable e) {
			logger.error("create acting fail ,request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			if (t instanceof ServiceException) {
			//				throw (ServiceException) t;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, t);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("create acting success , result:{}.", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.stock.service.product.ActingService#updateActing(com.pzj.stock.vo.product.ActingModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Integer> updateActing(ActingModel model, ServiceContext context) {
		Result<Integer> result = new Result<Integer>(0);
		if (Check.NuNObject(model, context)) {
			logger.error("update acting param error, request：{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("update acting ,request：{},context:{}.", model, context);
		}

		try {
			Integer count = actingUpdateEngine.updateActing(model, context);
			result.setData(count);

		} catch (Throwable e) {
			logger.error("update acting fail ,request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			if (t instanceof ServiceException) {
			//				throw (ServiceException) t;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, t);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("update acting success , result:{}.", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.product.service.ActingService#addActing(com.pzj.core.product.model.AddActingModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Boolean> addActing(AddActingModel model, ServiceContext serviceContext) {
		Result<Boolean> result = new Result<Boolean>(Boolean.FALSE);
		boolean legality = addActingValidator.convert(model, serviceContext);
		if (!legality) {
			logger.error("add acting fail, Illegal params. request: {}, context: {}.", model, serviceContext);
			CommonUtils.setParamErr(result);
			return result;

			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("add acting. request: {}, context: {}.", model, serviceContext);
		}
		try {
			boolean flag = addActingEngine.addActing(model);
			result.setData(flag);
		} catch (Throwable e) {
			logger.error("add acting fail. request: " + model + ", context: " + serviceContext, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("add acting. result: {}.", JSONConverter.toJson(result));
		}
		return result;
	}

}
