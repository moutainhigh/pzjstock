/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.area.AreaQueryByIdEngine;
import com.pzj.core.product.area.AreaQueryByParamEngine;
import com.pzj.core.product.area.AreasQueryByWebSeatChartEngine;
import com.pzj.core.product.converter.AreasConverter;
import com.pzj.core.product.entity.Area;
import com.pzj.core.product.model.AreaModel;
import com.pzj.core.product.model.AreaQueryRequestModel;
import com.pzj.core.product.service.AreaQueryService;
import com.pzj.core.product.validator.area.QueryAreasByIdValidator;
import com.pzj.core.product.validator.area.QueryAreasByParamValidator;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 演绎区域读服务实现
 * @author dongchunfu
 * @version $Id: AreaServiceImpl.java, v 0.1 2016年8月1日 上午10:48:26 dongchunfu Exp $
 */
@Service(value = "areaQueryService")
public class AreaQueryServiceImpl implements AreaQueryService {

	private static final Logger logger = LoggerFactory.getLogger(AreaQueryServiceImpl.class);

	@Autowired
	private AreaQueryByIdEngine areaQueryByIdEngine;
	@Autowired
	private AreaQueryByParamEngine areaQueryByParamEngine;
	@Autowired
	private AreasQueryByWebSeatChartEngine areasQueryByWebSeatChartEngine;

	@Resource(name = "areasConverter")
	private AreasConverter areasConverter;

	@Resource(name = "queryAreasByIdValidator")
	private QueryAreasByIdValidator queryAreasByIdValidator;
	@Resource(name = "queryAreasByParamValidator")
	private QueryAreasByParamValidator queryAreasByParamValidator;

	/** 
	 * @see com.pzj.stock.service.product.AreaQueryService#queryAreaById(com.pzj.stock.AreaQueryRequestModel.AreaQueryRequestBean, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<AreaModel> queryAreaById(AreaQueryRequestModel model, ServiceContext context) {
		Result<AreaModel> result = new Result<AreaModel>();
		if (!queryAreasByIdValidator.convert(model, context)) {
			logger.error("query areas by id error , request：{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("quering areas by id, request:{},context:{}.", model, context);
		}

		try {
			Area area = areaQueryByIdEngine.queryAreaById(model.getAreaId());
			if (!CommonUtils.checkObjectIsNull(area)) {
				AreaModel areaModel = new AreaModel();
				PropertyUtils.copyProperties(areaModel, area);
			}

		} catch (Throwable e) {
			logger.error("query area by id fail , request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			if (t instanceof ServiceException) {
			//				throw (ServiceException) t;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, t);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query area by id success , result:{}.", JSONConverter.toJson(result));
		}

		return result;
	}

	/** 
	 * @see com.pzj.core.product.service.AreaQueryService#queryAreasByParam(com.pzj.core.product.model.AreaQueryRequestModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ArrayList<AreaModel>> queryAreasByParam(AreaQueryRequestModel model, ServiceContext context) {
		Result<ArrayList<AreaModel>> result = new Result<ArrayList<AreaModel>>();
		if (!queryAreasByParamValidator.convert(model, context)) {
			logger.error("query areas by param error , request：{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("quering areas by param request:{},context:{}.", model, context);
		}

		try {
			List<Area> list = areaQueryByParamEngine.queryAreaByParam(model, context);
			ArrayList<AreaModel> areas = areasConverter.convert(list, context);
			result.setData(areas);
		} catch (Throwable e) {
			logger.error("query areas by param fail ,request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			if (t instanceof ServiceException) {
			//				throw (ServiceException) t;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, t);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query areas by param success ,result:{}.", JSONConverter.toJson(result));
		}

		return result;
	}

	/** 
	 * @see com.pzj.core.product.service.AreaQueryService#queryAreasByWebSeatChartParam(com.pzj.core.product.model.AreaQueryRequestModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ArrayList<AreaModel>> queryAreasByWebSeatChartParam(AreaQueryRequestModel model,
			ServiceContext context) {
		Result<ArrayList<AreaModel>> result = new Result<ArrayList<AreaModel>>();
		if (!queryAreasByParamValidator.convert(model, context)) {
			logger.error("query areas by web seat chart param error , request：{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled())
			logger.debug("query areas by web seat chart param request:{},context:{}.", model, context);

		try {

			List<Area> list = areasQueryByWebSeatChartEngine.queryAreaByScenic(model, context);
			if (!Check.NuNCollections(list)) {
				ArrayList<AreaModel> areaModelList = areasConverter.convert(list, context);
				result.setData(areaModelList);
			}

		} catch (Throwable e) {
			logger.error("query areas by web seat chart param fail ,request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			if (t instanceof ServiceException) {
			//				throw (ServiceException) t;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, t);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query areas by web seat chart param success ,result:{}.", JSONConverter.toJson(result));
		}

		return result;
	}
}
