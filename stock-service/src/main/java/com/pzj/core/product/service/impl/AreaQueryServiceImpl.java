/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.RpcException;
import com.pzj.commons.utils.CheckUtils;
import com.pzj.core.common.exception.StockException;
import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.area.AreaQueryByIdEngine;
import com.pzj.core.product.area.AreaQueryByParamEngine;
import com.pzj.core.product.area.AreasQueryByWebSeatChartEngine;
import com.pzj.core.product.common.model.response.ProductScenicOutModel;
import com.pzj.core.product.common.service.IProductScenicService;
import com.pzj.core.product.converter.AreasConverter;
import com.pzj.core.product.entity.Area;
import com.pzj.core.product.model.area.AreaModel;
import com.pzj.core.product.model.area.AreaQueryReqModel;
import com.pzj.core.product.model.area.AreaQueryRequestModel;
import com.pzj.core.product.model.area.TheaterAreaDetailRespModel;
import com.pzj.core.product.model.area.TheaterAreaReqModel;
import com.pzj.core.product.model.area.TheaterAreaRespModel;
import com.pzj.core.product.service.AreaQueryService;
import com.pzj.core.product.validator.area.QueryAreasByIdValidator;
import com.pzj.core.product.validator.area.QueryAreasByParamValidator;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.entity.QueryResult;
import com.pzj.framework.toolkit.Check;

/**
 * 演绎区域读服务实现
 * @author dongchunfu
 * @version $Id: AreaServiceImpl.java, v 0.1 2016年8月1日 上午10:48:26 dongchunfu Exp $
 */
@Service(value = "areaQueryService")
public class AreaQueryServiceImpl implements AreaQueryService {

	private static final Logger logger = LoggerFactory.getLogger(AreaQueryServiceImpl.class);

	@Resource
	private AreaQueryByIdEngine areaQueryByIdEngine;
	@Resource
	private AreaQueryByParamEngine areaQueryByParamEngine;
	@Resource
	private AreasQueryByWebSeatChartEngine areasQueryByWebSeatChartEngine;
	@Resource
	private IProductScenicService productScenicService;

	@Resource(name = "areasConverter")
	private AreasConverter areasConverter;

	@Resource(name = "queryAreasByIdValidator")
	private QueryAreasByIdValidator queryAreasByIdValidator;
	@Resource(name = "queryAreasByParamValidator")
	private QueryAreasByParamValidator queryAreasByParamValidator;

	/** 
	 * @see com.pzj.stock.service.product.AreaQueryService#queryAreaById(com.pzj.core.product.model.area.AreaQueryRequestModel.AreaQueryRequestBean, com.pzj.framework.context.ServiceContext)
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
	 * @see com.pzj.core.product.service.AreaQueryService#queryAreasByParam(com.pzj.core.product.model.area.AreaQueryRequestModel, com.pzj.framework.context.ServiceContext)
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
	 * @see com.pzj.core.product.service.AreaQueryService#queryAreasByWebSeatChartParam(com.pzj.core.product.model.area.AreaQueryRequestModel, com.pzj.framework.context.ServiceContext)
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

	@Override
	public Result<QueryResult<TheaterAreaRespModel>> queryAreas(AreaQueryReqModel areaQueryReqModel,
			ServiceContext serviceContext) {
		Result<QueryResult<TheaterAreaRespModel>> result = new Result<>();
		if (CheckUtils.isNull(areaQueryReqModel) || CheckUtils.isNull(areaQueryReqModel.getSupplierId())) {
			CommonUtils.setParamErr(result);
			return result;
		}
		try {
			//库存模块查询区域相关数据，
			QueryResult<TheaterAreaRespModel> thModels = areaQueryByParamEngine.queryScenicAreas(areaQueryReqModel,
					serviceContext);
			List<TheaterAreaRespModel> areaRespModels = thModels.getRecords();
			//产品模块查询剧场名称信息
			List<Long> scenicIds = new ArrayList<>();
			Map<Long, TheaterAreaRespModel> theaterMap = new HashMap<>();
			for (TheaterAreaRespModel theaterAreaRespModel : areaRespModels) {
				scenicIds.add(theaterAreaRespModel.getTheaterId());
				theaterMap.put(theaterAreaRespModel.getTheaterId(), theaterAreaRespModel);
			}
			Result<ArrayList<ProductScenicOutModel>> scenicsResult = productScenicService.getByIds(scenicIds);
			if (!scenicsResult.isOk()) {
				CommonUtils.setParamErr(result, scenicsResult.getErrorMsg());
				return result;
			}
			if (CheckUtils.isNotNull(scenicsResult.getData())) {
				for (ProductScenicOutModel prModel : scenicsResult.getData()) {
					theaterMap.get(prModel.getId()).setTheaterName(prModel.getName());
				}
			}
			result.setData(thModels);
			return result;
		} catch (StockException e) {
			result.setErrorCode(e.getErrCode());
			result.setErrorMsg(e.getMessage());
		} catch (RpcException e) {
			result.setErrorCode(e.getCode());
			result.setErrorMsg(StockExceptionCode.INVOKE_OTHER_SERVICE_ERR_MSG + ",错误信息:" + e.getMessage());
		} catch (Exception e) {
			CommonUtils.setParamErr(result, StockExceptionCode.STOCK_ERR_MSG);
		}
		return result;
	}

	@Override
	public Result<TheaterAreaDetailRespModel> queryAreaDetail(TheaterAreaReqModel reqModel, ServiceContext context) {
		Result<TheaterAreaDetailRespModel> result = new Result<TheaterAreaDetailRespModel>();
		if (reqModel == null || CommonUtils.checkObjectIsNull(reqModel.getSupplierId(), reqModel.getTheaterId())) {
			logger.error("query area deatil param error , request：{},context:{}.", JSONConverter.toJson(reqModel),
					context);
			CommonUtils.setParamErr(result);
			return result;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query area deatil request param:{}", JSONConverter.toJson(reqModel));
		}

		try {
			TheaterAreaDetailRespModel respModel = areaQueryByParamEngine.queryAreaDetail(reqModel);
			result.setData(respModel);
		} catch (Exception e) {
			logger.error("query area deatil fail ,request:{},context:{}.", JSONConverter.toJson(reqModel), context, e);
			CommonUtils.convertException(e, result);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query area deatil result:{}", JSONConverter.toJson(result));
		}
		return result;
	}
}
