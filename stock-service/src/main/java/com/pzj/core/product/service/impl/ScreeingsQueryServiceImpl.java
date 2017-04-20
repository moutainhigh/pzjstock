/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.converter.ScreeingsConverter;
import com.pzj.core.product.converter.ScreeingsesConverter;
import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.core.product.model.ScreeingsQueryRequestModel;
import com.pzj.core.product.screeings.ScreeingsQueryByIdEngine;
import com.pzj.core.product.screeings.ScreeingsQueryByParamEngine;
import com.pzj.core.product.service.ScreeingsQueryService;
import com.pzj.core.product.validator.screeings.QueryScreeingsByIdValidator;
import com.pzj.core.product.validator.screeings.QueryScreeingsByParamValidator;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

/**
 * 演绎场次读服务实现
 * 
 * @author dongchunfu
 * @version $Id: ScreeingsQueryServiceImpl.java, v 0.1 2016年8月1日 下午2:45:54 dongchunfu Exp $
 */
@Service(value = "screeingsQueryService")
public class ScreeingsQueryServiceImpl implements ScreeingsQueryService {

	private static final Logger logger = LoggerFactory.getLogger(ScreeingsQueryServiceImpl.class);

	@Resource(name = "screeingsQueryByIdEngine")
	private ScreeingsQueryByIdEngine screeingsQueryByIdEngine;
	@Resource(name = "screeingsQueryByParamEngine")
	private ScreeingsQueryByParamEngine screeingsQueryByParamEngine;

	@Resource(name = "screeingsesConverter")
	private ScreeingsesConverter screeingsesConverter;
	@Resource(name = "screeingsConverter")
	private ScreeingsConverter screeingsConverter;
	@Resource(name = "queryScreeingsByIdValidator")
	private QueryScreeingsByIdValidator QueryScreeingsByIdValidator;
	@Resource(name = "queryScreeingsByParamValidator")
	private QueryScreeingsByParamValidator queryScreeingsByParamValidator;

	/** 
	 * @see com.pzj.stock.service.product.ScreeingsQueryService#queryScreeingsById(com.pzj.stock.model.AreaQueryRequestBean, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ScreeingsModel> queryScreeingsById(ScreeingsQueryRequestModel model, ServiceContext context) {
		Result<ScreeingsModel> result = new Result<ScreeingsModel>();
		if (!QueryScreeingsByIdValidator.convert(model, context)) {
			logger.error("query screeings by id error ,request：{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("quering screeings by id ,request:{},context:{}.", model, context);
		}

		try {
			Long screeingsId = model.getScreeingsId();
			Screeings screeings = screeingsQueryByIdEngine.queryScreeingsById(screeingsId);

			ScreeingsModel resultModel = screeingsConverter.convert(screeings, context);
			result.setData(resultModel);
		} catch (Throwable e) {
			logger.error("query screeings by id fail ,request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			if (t instanceof ServiceException) {
			//				throw (ServiceException) t;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, t);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" query screeings by id success , result:{}.", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.product.service.ScreeingsQueryService#queryScreeingsesByParam(com.pzj.core.product.model.ScreeingsQueryRequestModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ArrayList<ScreeingsModel>> queryScreeingsesByParam(ScreeingsQueryRequestModel model,
			ServiceContext context) {
		Result<ArrayList<ScreeingsModel>> result = new Result<ArrayList<ScreeingsModel>>();
		Boolean success = queryScreeingsByParamValidator.convert(model, context);
		if (!success) {
			logger.error("query screeings by param error ,request：{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("quering screeings by param ,request:{},context:{}.", model, context);
		}

		try {
			ArrayList<Screeings> screeingsList = screeingsQueryByParamEngine.queryScreeingsByParam(model, context);

			if (!CommonUtils.checkObjectIsNull(screeingsList)) {
				result.setData(screeingsesConverter.convert(screeingsList, context));
			}

		} catch (Throwable e) {
			logger.error("query screeings by param fail ,request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			if (t instanceof ServiceException) {
			//				throw (ServiceException) t;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, t);
		}
		if (logger.isDebugEnabled())
			logger.debug(" query screeings by param success , result:{}.", result);

		return result;
	}
}
