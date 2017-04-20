/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service.impl;

import javax.annotation.Resource;

import com.pzj.core.product.area.AreaWriteEngine;
import com.pzj.core.util.LogUtil;
import com.pzj.core.util.RpcCaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.area.AreaCreateEngine;
import com.pzj.core.product.area.AreaUpdateEngine;
import com.pzj.core.product.model.area.AreaModel;
import com.pzj.core.product.model.area.CreateAreaReqModel;
import com.pzj.core.product.model.area.DelAreasReqModel;
import com.pzj.core.product.model.area.ModifyAreaReqModel;
import com.pzj.core.product.service.AreaService;
import com.pzj.core.product.validator.area.CreateAreaValidator;
import com.pzj.core.product.validator.area.UpdateAreaValidator;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

/**
 * 演绎区域写服务实现
 * @author dongchunfu
 * @version $Id: AreaServiceImpl.java, v 0.1 2016年8月1日 上午10:48:26 dongchunfu Exp $
 */
@Service(value = "areaService")
public class AreaServiceImpl implements AreaService {

	private static final Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

	@Resource(name = "areaCreateEngine")
	private AreaCreateEngine areaCreateEngine;
	@Resource(name = "areaUpdateEngine")
	private AreaUpdateEngine areaUpdateEngine;

	@Resource(name = "createAreaValidator")
	private CreateAreaValidator createAreaValidator;
	@Resource(name = "updateAreaValidator")
	private UpdateAreaValidator updateAreaValidator;
	@Resource
	private AreaWriteEngine areaWriteEngine;

	@Override
	public Result<Long> createArea(AreaModel model, ServiceContext context) {
		Result<Long> result = new Result<Long>();
		Boolean success = createAreaValidator.convert(model, context);
		if (!success) {
			logger.error(" create area illegal param , request:{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" creating area ,request:{},context:{}.", model, context);
		}

		try {
			Long id = areaCreateEngine.createArea(model, context);
			result.setData(id);

		} catch (Throwable e) {

			logger.error("create area fail ,request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			if (t instanceof ServiceException) {
			//				throw (ServiceException) t;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, t);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" creating area success ,result:{}.", JSONConverter.toJson(result));
		}
		return result;
	}

	@Override
	public Result<Integer> updateArea(AreaModel model, ServiceContext context) {
		Result<Integer> result = new Result<Integer>(0);
		Boolean success = updateAreaValidator.convert(model, context);
		if (!success) {
			logger.error(" update area illegal param , request:{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" updating area ,request:{},context:{}.", model, context);
		}

		try {
			Integer count = areaUpdateEngine.updateArea(model, context);
			result.setData(count);

		} catch (Throwable e) {
			logger.error("update area fail ,request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			if (t instanceof ServiceException) {
			//				throw (ServiceException) t;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, t);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" update area success ,result:{}.", JSONConverter.toJson(result));
		}
		return result;
	}

	@Override
	public Result<Boolean> deleteAreas(DelAreasReqModel reqModel, ServiceContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Boolean> createArea(final CreateAreaReqModel createAreaReqModel) {
		LogUtil.loggerPrintInfo(logger, "创建区域，入参：{}", createAreaReqModel);

		Result<Boolean> result = new RpcCaller<Boolean>() {
			@Override
			public Boolean call() {
				return areaWriteEngine.createArea(createAreaReqModel);
			}
		}.run();

		LogUtil.loggerPrintInfo(logger, "创建区域，出参：{}", result);

		return result;
	}

	@Override
	public Result<Boolean> modifyArea(final ModifyAreaReqModel modifyAreaReqModel) {
		LogUtil.loggerPrintInfo(logger, "修改区域，入参：{}", modifyAreaReqModel);

		Result<Boolean> result = new RpcCaller<Boolean>() {
			@Override
			public Boolean call() {
				return areaWriteEngine.modifyArea(modifyAreaReqModel);
			}
		}.run();

		LogUtil.loggerPrintInfo(logger, "修改区域，出参：{}", result);

		return result;
	}

}
