/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service.impl;

import java.util.*;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.common.model.response.ProductScenicOutModel;
import com.pzj.core.product.common.service.IProductScenicService;
import com.pzj.core.product.converter.ScreeingsConverter;
import com.pzj.core.product.converter.ScreeingsesConverter;
import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.model.screeings.*;
import com.pzj.core.product.screeings.*;
import com.pzj.core.product.service.ScreeingsQueryService;
import com.pzj.core.product.validator.screeings.QueryScreeingsByIdValidator;
import com.pzj.core.product.validator.screeings.QueryScreeingsByParamValidator;
import com.pzj.core.sku.common.page.QueryPageBean;
import com.pzj.core.sku.common.page.QueryPageList;
import com.pzj.core.sku.common.page.QueryPageModel;
import com.pzj.core.stock.event.QueryRuleByShow;
import com.pzj.core.stock.model.result.SpuResult;
import com.pzj.core.stock.model.result.StockRuleInfoResult;
import com.pzj.core.stock.service.ISkuStockService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.entity.QueryResult;

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

	@Resource(name = "queryRuleByShow")
	private QueryRuleByShow queryRuleByShow;
	@Resource(name = "screeingOrderQueryEngine")
	private ScreeingOrderQueryEngine screeingOrderQueryEngine;
	@Resource(name = "productScenicService")
	private IProductScenicService productScenicService;
	@Resource(name = "screeingAreaQueryEngine")
	private ScreeingAreaQueryEngine screeingAreaQueryEngine;
	@Resource(name = "skuStockService")
	private ISkuStockService skuStockService;
	@Resource(name = "artSpuScreeingOrderQueryEngine")
	private ArtSpuScreeingOrderQueryEngine artSpuScreeingOrderQueryEngine;

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
	 * @see com.pzj.core.product.service.ScreeingsQueryService#queryScreeingsesByParam(com.pzj.core.product.model.screeings.ScreeingsQueryRequestModel, com.pzj.framework.context.ServiceContext)
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

	@Override
	public Result<ScreeingsModel> queryScreeingDetail(Long screeingId, ServiceContext context) {
		Result<ScreeingsModel> result = new Result<ScreeingsModel>();
		if (CommonUtils.checkLongIsNull(screeingId, true)) {
			logger.error("query screeings detail. illegal param:{}", screeingId);
			CommonUtils.setParamErr(result);
			return result;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query screeing detail. request param:{}", screeingId);
		}

		try {
			ScreeingsModel screeingsModel = screeingsQueryByIdEngine.queryScreeingDetail(screeingId);
			result.setData(screeingsModel);
		} catch (Throwable e) {
			logger.error("query screeing detail fail ,param:{},context:{}.", screeingId, context, e);
			CommonUtils.convertException(e, result);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query screeing detail. result:{}", JSONConverter.toJson(result));
		}
		return result;
	}

	@Override
	public Result<QueryResult<TheaterScreeingRespModel>> queryTheaterScreeings(TheaterScreeingReqModel reqModel,
			ServiceContext context) {
		Result<QueryResult<TheaterScreeingRespModel>> result = new Result<QueryResult<TheaterScreeingRespModel>>();
		if (reqModel == null || CommonUtils.checkLongIsNull(reqModel.getSupplierId(), true)) {
			logger.error("query theater screeings. illegal param:{}", JSONConverter.toJson(reqModel));
			CommonUtils.setParamErr(result);
			return result;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query theater screeings. request param:{}", JSONConverter.toJson(reqModel));
		}

		QueryResult<TheaterScreeingRespModel> queryResult = null;
		List<Long> scenicIds = null;
		try {
			queryResult = screeingsQueryByParamEngine.queryTheaterScreeings(reqModel);
			if (queryResult != null && !CommonUtils.checkCollectionIsNull(queryResult.getRecords())) {
				List<TheaterScreeingRespModel> records = queryResult.getRecords();
				scenicIds = new ArrayList<Long>();
				for (TheaterScreeingRespModel record : records) {
					scenicIds.add(record.getTheaterId());
				}
			}
			result.setData(queryResult);
		} catch (Throwable e) {
			logger.error("query theater screeings fail ,request:{},context:{}.", JSONConverter.toJson(reqModel),
					context, e);
			CommonUtils.convertException(e, result);
		}

		Map<Long, String> scenicNames = null;
		try {
			if (!CommonUtils.checkCollectionIsNull(scenicIds)) {
				Result<ArrayList<ProductScenicOutModel>> scenicsResult = productScenicService.getByIds(scenicIds);
				if (scenicsResult.isOk()) {
					scenicNames = new HashMap<Long, String>();
					ArrayList<ProductScenicOutModel> productScenics = scenicsResult.getData();
					for (ProductScenicOutModel productScenic : productScenics) {
						scenicNames.put(productScenic.getId(), productScenic.getName());
					}
				} else {
					CommonUtils.setResultErr(scenicsResult.getErrorCode(), scenicsResult.getErrorMsg(), result);
					return result;
				}
			}
		} catch (Throwable e) {
			logger.error("query theater screeings fail ,invoke product service exception,request:{},context:{}.",
					reqModel, context, e);
			CommonUtils.convertException(e, result);
		}
		try {
			if (queryResult != null && scenicNames != null) {
				List<TheaterScreeingRespModel> records = queryResult.getRecords();
				for (TheaterScreeingRespModel record : records) {
					record.setTheaterName(scenicNames.get(record.getTheaterId()));
				}
			}
		} catch (Throwable e) {
			logger.error("query theater screeings fail ,request:{},context:{}.", JSONConverter.toJson(reqModel),
					context, e);
			CommonUtils.convertException(e, result);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query theater screeings. result:{}", JSONConverter.toJson(result));
		}
		return result;
	}

	//	public Result<QueryResult<ArtSpuScreeingOrderModel>> queryTheaterScreeingsOrderByPage(
	//			TheaterScreeingOrderReqModel reqModel, ServiceContext context) {
	//		Result<QueryResult<ArtSpuScreeingOrderModel>> result = new Result<QueryResult<ArtSpuScreeingOrderModel>>();
	//		if (reqModel == null || CommonUtils.checkLongIsNull(reqModel.getSupplierId(), true)
	//				|| reqModel.getShowTime() == null) {
	//			logger.error("query theater screeings order illegal param:{}", JSONConverter.toJson(reqModel));
	//			CommonUtils.setParamErr(result);
	//			return result;
	//		}
	//
	//		if (logger.isDebugEnabled()) {
	//			logger.debug("query theater screeings order request param:{}", JSONConverter.toJson(reqModel));
	//		}
	//
	//		List<Screeings> screeings = null;
	//		Map<Long, Long> screeingTheaterRel = new HashMap<Long, Long>();
	//		List<Long> screeingIds = new ArrayList<Long>();
	//		try {
	//			screeings = screeingsQueryByParamEngine.queryUserScreeings(reqModel.getSupplierId());
	//			if (CommonUtils.checkObjectIsNull(screeings)) {
	//				return result;
	//			}
	//			for (Screeings screeing : screeings) {
	//				screeingIds.add(screeing.getId());
	//				screeingTheaterRel.put(screeing.getId(), screeing.getScenicId());
	//			}
	//		} catch (Throwable e) {
	//			logger.error("query theater screeings order screeing list fail ,request:{},context:{}.",
	//					JSONConverter.toJson(reqModel), context, e);
	//			CommonUtils.convertException(e, result);
	//			return result;
	//		}
	//
	//		Map<Long, List<Long>> screeingRules = null;
	//		Set<Long> theaterIds = null;
	//		try {
	//			screeingRules = queryRuleByShow.queryRuleByScreeing(screeingIds, reqModel.getShowTime());
	//			if (CommonUtils.checkMapIsNull(screeingRules)) {
	//				return result;
	//			}
	//			theaterIds = new HashSet<Long>();
	//			screeingIds = new ArrayList<Long>();
	//			Iterator<Long> itera = screeingRules.keySet().iterator();
	//			Long key, theaterId = null;
	//			while (itera.hasNext()) {
	//				key = itera.next();
	//				for (Screeings screeing : screeings) {
	//					if (screeing.getId().longValue() == key.longValue()) {
	//						theaterId = screeing.getScenicId();
	//						break;
	//					}
	//				}
	//				theaterIds.add(theaterId);
	//				screeingIds.add(key);
	//			}
	//		} catch (Throwable e) {
	//			logger.error("query theater screeings order screeing rel rule list fail ,request:{},context:{}.",
	//					JSONConverter.toJson(reqModel), context, e);
	//			CommonUtils.convertException(e, result);
	//			return result;
	//		}
	//
	//		try {
	//			List<ArtSpuScreeingOrderModel> theaterScreeingOrders = screeingOrderQueryEngine
	//					.queryTheaterScreeingsOrder(reqModel, theaterIds, screeingIds, screeingRules);
	//
	//			QueryResult<ArtSpuScreeingOrderModel> queryResult = new QueryResult<ArtSpuScreeingOrderModel>(
	//					reqModel.getCurrentPage(), reqModel.getPageSize());
	//			queryResult.setRecords(theaterScreeingOrders);
	//			queryResult.setTotal(theaterIds.size());
	//			result.setData(queryResult);
	//		} catch (Throwable e) {
	//			logger.error("query theater screeings order fail.request param:{}", JSONConverter.toJson(reqModel), e);
	//			CommonUtils.convertException(e, result);
	//			return result;
	//		}
	//
	//		if (logger.isDebugEnabled()) {
	//			logger.debug("query theater screeings order result:{}", JSONConverter.toJson(result));
	//		}
	//		return result;
	//	}

	@Override
	public Result<QueryResult<ArtSpuScreeingOrderModel>> queryArtSpuScreeingsOrderByPage(
			TheaterScreeingOrderReqModel reqModel, ServiceContext context) {
		Result<QueryResult<ArtSpuScreeingOrderModel>> result = new Result<QueryResult<ArtSpuScreeingOrderModel>>();
		if (reqModel == null || CommonUtils.checkLongIsNull(reqModel.getSupplierId(), true)
				|| reqModel.getShowTime() == null) {
			logger.error("query art spu screeings order. illegal param:{}", JSONConverter.toJson(reqModel));
			CommonUtils.setParamErr(result);
			return result;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query art spu screeings order. request param:{}", JSONConverter.toJson(reqModel));
		}

		Result<QueryPageList<SpuResult>> skuResult;
		List<Long> screeingIds = null;
		List<ArtSpuScreeingOrderModel> artSpuScreeings = null;
		try {
			QueryPageModel querySpuPage = new QueryPageModel();
			querySpuPage.setPageNo(reqModel.getCurrentPage());
			querySpuPage.setPageSize(reqModel.getPageSize());
			skuResult = skuStockService.getSpuBySupplierId(reqModel.getSupplierId(), querySpuPage);
			if (!skuResult.isOk()) {
				logger.error("query art spu page error. request param:{}", JSONConverter.toJson(reqModel));
				CommonUtils.setResultErr(skuResult.getErrorCode(), skuResult.getErrorMsg(), result);
				return result;
			}
			if (skuResult.getData() == null || CommonUtils.checkCollectionIsNull(skuResult.getData().getResultList())) {
				return result;
			}

			Set<String> uniqScreeings = new HashSet<String>();
			screeingIds = new ArrayList<Long>();
			artSpuScreeings = new ArrayList<ArtSpuScreeingOrderModel>();
			List<SpuResult> spus = skuResult.getData().getResultList();
			List<StockRuleInfoResult> stockRuleInfos = null;
			ArtSpuScreeingOrderModel artSpuScreeingOrder = null;
			String screeingId = "";
			List<ScreeingOrderRespModel> screeingOrders = null;
			ScreeingOrderRespModel screeingOrderResp = null;
			for (SpuResult spu : spus) {
				stockRuleInfos = spu.getSkus();
				artSpuScreeingOrder = new ArtSpuScreeingOrderModel();
				artSpuScreeingOrder.setSupplierId(reqModel.getSupplierId());
				artSpuScreeingOrder.setSpuId(spu.getSpuId());
				artSpuScreeingOrder.setSpuName(spu.getSpuName());
				artSpuScreeings.add(artSpuScreeingOrder);

				screeingOrders = new ArrayList<ScreeingOrderRespModel>();
				for (StockRuleInfoResult stockRuleInfo : stockRuleInfos) {
					screeingId = stockRuleInfo.getRondaId();
					if (CommonUtils.checkStringIsNullStrict(screeingId) || uniqScreeings.contains(screeingId)) {
						continue;
					}
					uniqScreeings.add(screeingId);
					screeingIds.add(Long.parseLong(screeingId));
					screeingOrderResp = new ScreeingOrderRespModel();
					screeingOrderResp.setId(Long.parseLong(screeingId));
					screeingOrders.add(screeingOrderResp);
				}
				artSpuScreeingOrder.setScreeingOrders(screeingOrders);
			}

		} catch (Throwable e) {
			logger.error("query spu page fail ,request:{},context:{}.", JSONConverter.toJson(reqModel), context, e);
			CommonUtils.convertException(e, result);
			return result;
		}

		Map<Long, List<Long>> screeingRules = null;
		try {
			screeingRules = queryRuleByShow.queryRuleByScreeing(screeingIds, reqModel.getShowTime());

		} catch (Throwable e) {
			logger.error("query spu screeings order screeing rel rule list fail ,request:{},context:{}.",
					JSONConverter.toJson(reqModel), context, e);
			CommonUtils.convertException(e, result);
			return result;
		}

		try {
			artSpuScreeingOrderQueryEngine.intArtSpuScreeingOrder(artSpuScreeings, screeingRules,
					reqModel.getShowTime());
			QueryResult<ArtSpuScreeingOrderModel> queryResult = new QueryResult<ArtSpuScreeingOrderModel>(
					reqModel.getCurrentPage(), reqModel.getPageSize());
			int total = 0;
			QueryPageBean pageBean = skuResult.getData().getPageBean();
			if (null != pageBean) {
				total = (int) pageBean.getTotalCount();
			}
			queryResult.setRecords(artSpuScreeings);
			queryResult.setTotal(total);
			result.setData(queryResult);
		} catch (Throwable e) {
			logger.error("query art spu screeings order page fail ,request:{},context:{}.",
					JSONConverter.toJson(reqModel), context, e);
			CommonUtils.convertException(e, result);
			return result;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query art spu screeings order. result:{}", JSONConverter.toJson(result));
		}
		return result;
	}

	@Override
	public Result<ScreeingAreaRespModel> queryScreeingAreaBaseInfo(ScreeingAreaReqModel reqModel, ServiceContext context) {
		Result<ScreeingAreaRespModel> result = new Result<ScreeingAreaRespModel>();
		if (reqModel == null
				|| (CommonUtils.checkCollectionIsNull(reqModel.getScreeingIds()) && CommonUtils
						.checkCollectionIsNull(reqModel.getAreaIds()))) {
			logger.error("query screeing area base info. illegal param:{}", JSONConverter.toJson(reqModel));
			CommonUtils.setParamErr(result);
			return result;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query screeing area base info. request param:{}", JSONConverter.toJson(reqModel));
		}

		try {
			ScreeingAreaRespModel screeingAreaResp = screeingAreaQueryEngine.queryScreeingAreaBaseInfo(reqModel);
			result.setData(screeingAreaResp);
		} catch (Throwable e) {
			logger.error("query screeing area base info fail.request param:{}", JSONConverter.toJson(reqModel), e);
			CommonUtils.convertException(e, result);
			return result;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query screeing area base info. result:{}", JSONConverter.toJson(result));
		}

		return result;
	}

}
