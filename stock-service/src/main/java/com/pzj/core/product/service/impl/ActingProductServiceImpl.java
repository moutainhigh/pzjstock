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
import com.pzj.core.product.acting.ActingQueryByParamEngine;
import com.pzj.core.product.actingproduct.ActingProductCreateEngine;
import com.pzj.core.product.actingproduct.QueryActProInfoEngine;
import com.pzj.core.product.actingproduct.QueryActProRelAllInfoEngine;
import com.pzj.core.product.actingproduct.QueryActProScreeningsEngine;
import com.pzj.core.product.actingproduct.QueryInfoForCreateActingProductEngine;
import com.pzj.core.product.actingproduct.QueryInfoForSkuProductEngine;
import com.pzj.core.product.area.AreaQueryByParamEngine;
import com.pzj.core.product.converter.ActingsConverter;
import com.pzj.core.product.converter.AreaScreeingsRelsConverter;
import com.pzj.core.product.converter.AreasConverter;
import com.pzj.core.product.converter.ScreeingsesConverter;
import com.pzj.core.product.entity.Acting;
import com.pzj.core.product.entity.Area;
import com.pzj.core.product.entity.AreaScreeingsRel;
import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.model.ActingModel;
import com.pzj.core.product.model.ActingProductModel;
import com.pzj.core.product.model.ActingProductQueryRequstModel;
import com.pzj.core.product.model.ThreeDulpe;
import com.pzj.core.product.model.area.AreaModel;
import com.pzj.core.product.model.area.AreaScreeingsRelModel;
import com.pzj.core.product.model.screeings.ProScreeningsQueryModel;
import com.pzj.core.product.model.screeings.ScreeingsModel;
import com.pzj.core.product.screeings.ScreeingsQueryByParamEngine;
import com.pzj.core.product.service.ActingProductService;
import com.pzj.core.product.validator.actingproduct.CreatActingProductValidator;
import com.pzj.core.product.validator.actingproduct.QueryActProInfoValidator;
import com.pzj.core.product.validator.actingproduct.QueryInfoValidator;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author dongchunfu
 * @version $Id: ActingProductServiceImpl.java, v 0.1 2016年8月8日 下午4:23:05 dongchunfu Exp $
 */
@Service(value = "actingProductService")
public class ActingProductServiceImpl implements ActingProductService {

	private static final Logger logger = LoggerFactory.getLogger(ActingProductServiceImpl.class);

	@Resource(name = "actingProductCreateEngine")
	private ActingProductCreateEngine actingProductCreateEngine;

	@Resource(name = "actingQueryByParamEngine")
	private ActingQueryByParamEngine actingQueryByParamEngine;
	@Resource(name = "areaQueryByParamEngine")
	private AreaQueryByParamEngine areaQueryByParamEngine;
	@Resource(name = "screeingsQueryByParamEngine")
	private ScreeingsQueryByParamEngine screeingsQueryByParamEngine;
	@Resource(name = "queryInfoForCreateActingProductEngine")
	private QueryInfoForCreateActingProductEngine queryInfoForCreateActingProductEngine;
	@Resource(name = "queryInfoForSkuProductEngine")
	private QueryInfoForSkuProductEngine queryInfoForSkuProductEngine;
	@Resource(name = "queryActProInfoEngine")
	private QueryActProInfoEngine queryActProInfoEngine;
	@Resource(name = "queryActProScreeningsEngine")
	private QueryActProScreeningsEngine queryActProScreeningsEngine;
	@Resource(name = "queryActProRelAllInfoEngine")
	private QueryActProRelAllInfoEngine queryActProRelAllInfoEngine;

	@Resource(name = "queryInfoValidator")
	private QueryInfoValidator queryInfoValidator;
	@Resource(name = "creatActingProductValidator")
	private CreatActingProductValidator creatActingProductValidator;
	@Resource(name = "queryActProInfoValidator")
	private QueryActProInfoValidator queryActProInfoValidator;

	@Resource(name = "areaScreeingsRelsConverter")
	private AreaScreeingsRelsConverter areaScreeingsRelsConverter;
	@Resource(name = "screeingsesConverter")
	private ScreeingsesConverter screeingsesConverter;
	@Resource(name = "areasConverter")
	private AreasConverter areasConverter;
	@Resource(name = "actingsConverter")
	private ActingsConverter actingsConverter;

	/** 
	 * @see com.pzj.core.product.service.ActingProductService#createActingProduct(com.pzj.core.product.model.area.AreaScreeingsRelModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Integer> createActingProduct(ActingProductModel model, ServiceContext context) {
		Result<Integer> result = new Result<Integer>(0);
		Boolean success = creatActingProductValidator.convert(model, context);
		if (!success) {
			logger.error(" illegal param , request：{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("creating acting product , request:{},context:{}.", model, context);
		}

		try {
			Integer count = actingProductCreateEngine.insertActingProduct(model, context);
			result.setData(count);
		} catch (Throwable e) {
			logger.error("create acting product fail , request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			if (t instanceof ServiceException) {
			//				throw (ServiceException) t;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, t);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("create acting product success, result:{}.", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.product.service.ActingProductService#queryActingInfoByIds(com.pzj.core.product.model.ActingQueryRequestModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ArrayList<AreaScreeingsRelModel>> queryInfoForSkuProduct(ActingProductQueryRequstModel model,
			ServiceContext context) {
		Result<ArrayList<AreaScreeingsRelModel>> result = new Result<ArrayList<AreaScreeingsRelModel>>();
		Boolean success = queryInfoValidator.convert(model, context);
		if (!success) {
			logger.error("queryInfoForSkuProduct illegal param , request：{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query acting info for sku product , request:{},context:{}.", model, context);
		}

		try {
			ArrayList<AreaScreeingsRel> list = queryInfoForSkuProductEngine.selectInfoForSkuProductEngine(model,
					context);

			ArrayList<AreaScreeingsRelModel> convert = areaScreeingsRelsConverter.convert(list, context);
			result.setData(convert);
		} catch (Throwable e) {
			logger.error("query acting info for sku product fail , request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			if (t instanceof ServiceException) {
			//				throw (ServiceException) t;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, t);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query acting info for sku product success, result:{}.", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.product.service.ActingProductService#queryInfoForCreateActingProduct(com.pzj.core.product.model.ActingProductQueryRequstModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ActingProductModel> queryInfoForCreateActingProduct(ActingProductQueryRequstModel model,
			ServiceContext context) {
		Result<ActingProductModel> result = new Result<ActingProductModel>();
		Boolean success = queryInfoValidator.convert(model, context);
		if (!success) {
			logger.warn(" illegal param , request：{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("quering info for create acting product , request:{},context:{}.", model, context);
		}

		try {
			ThreeDulpe<ArrayList<Acting>, ArrayList<Screeings>, ArrayList<Area>> proActings = queryInfoForCreateActingProductEngine
					.selectInfoForSkuProductEngine(model, context);

			if (!CommonUtils.checkObjectIsNull(proActings)) {
				ArrayList<ScreeingsModel> screeingsModels = screeingsesConverter.convert(proActings.getE(), context);
				ArrayList<ActingModel> actingModels = actingsConverter.convert(proActings.getT(), context);
				ArrayList<AreaModel> areaModels = areasConverter.convert(proActings.getF(), context);
				ActingProductModel apm = new ActingProductModel();
				apm.setActings(actingModels);
				apm.setAreas(areaModels);
				apm.setScreeingses(screeingsModels);
				result.setData(apm);
			}

		} catch (Throwable e) {
			logger.error("query info for create acting product fail , request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			throw t instanceof ServiceException ? (ServiceException) t : new StockException(
			//					StockExceptionCode.STOCK_ERR_MSG, t);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("quering info for create acting product , result:{}.", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.product.service.ActingProductService#queryActProInfo(com.pzj.core.product.model.ActingProductQueryRequstModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ActingProductModel> queryActProInfo(ActingProductQueryRequstModel model, ServiceContext context) {
		Result<ActingProductModel> result = new Result<ActingProductModel>();
		Boolean success = queryActProInfoValidator.convert(model, context);
		if (!success) {
			logger.error(" illegal param ,when query acting product,request:{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("quering acting prodct ,request:{},context:{}.", model, context);
		}

		try {
			ActingProductModel apm = queryActProInfoEngine.qureyActProInfo(model, context);
			result.setData(apm);

		} catch (Throwable e) {
			logger.error("query acting product failed , request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			throw t instanceof ServiceException ? (ServiceException) t : new StockException(
			//					StockExceptionCode.STOCK_ERR_MSG, t);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("quering acting prodct ,result:{}.", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.product.service.ActingProductService#queryScreeningsByActProductId(com.pzj.core.product.model.screeings.ProScreeningsQueryModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ScreeingsModel> queryScreeningsByActProductId(ProScreeningsQueryModel model, ServiceContext context) {
		Result<ScreeingsModel> result = new Result<ScreeingsModel>();
		if (Check.NuNObject(model) || CommonUtils.checkLongIsNull(model.getActProId(), true)) {
			logger.error("query screenings by actProductid fail, Illegal params. request: {}, context: {}.", model,
					context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query screenings by actProductid. request: {}, context: {}.", model, context);
		}

		try {
			ScreeingsModel screeingsModel = queryActProScreeningsEngine.queryActProScreenings(model);
			result.setData(screeingsModel);

		} catch (Throwable e) {
			logger.error("query screenings by actProductid fail. request: " + model + ", context: " + context, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query screenings by actProductid. result: {}.", JSONConverter.toJson(result));
		}

		return result;
	}

	/** 
	 * @see com.pzj.core.product.service.ActingProductService#queryAreaScreeRelByProAct(com.pzj.core.product.model.screeings.ProScreeningsQueryModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<AreaScreeingsRelModel> queryAreaScreeRelByProAct(ActingProductQueryRequstModel model,
			ServiceContext context) {
		Result<AreaScreeingsRelModel> result = new Result<AreaScreeingsRelModel>();
		boolean legal = queryActProInfoValidator.convert(model, context);
		if (!legal) {
			logger.error("illegal param ,when query occupy seat area scree rel product,request:{},context:{}.", model,
					context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query occupy seat area scree rel product. request: {}, context: {}.", model, context);
		}

		try {
			AreaScreeingsRelModel areaScreeRelAllInfo = queryActProRelAllInfoEngine.queryOccupySeatActProRel(model);
			result.setData(areaScreeRelAllInfo);

		} catch (Throwable e) {
			logger.error("query occupy seat area scree rel product fail. request: " + model + ", context: " + context,
					e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query occupy seat area scree rel product. result: {}.", JSONConverter.toJson(result));
		}

		return result;
	}
}
