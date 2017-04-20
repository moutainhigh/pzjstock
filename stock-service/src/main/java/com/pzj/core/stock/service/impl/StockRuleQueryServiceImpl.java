/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.common.utils.MFBeanCopier;
import com.pzj.core.stock.converter.StockRulesConverter;
import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.event.PackagResult4Category;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.QueryStockRuleByIdsModel;
import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.core.stock.model.StockRuleQueryRequestModel;
import com.pzj.core.stock.rulequery.StockRuleQueryByIdEngine;
import com.pzj.core.stock.rulequery.StockRuleQueryByIdsEngine;
import com.pzj.core.stock.rulequery.StockRuleQueryByPageEngine;
import com.pzj.core.stock.rulequery.StockRuleQueryByParamEngine;
import com.pzj.core.stock.service.StockRuleQueryService;
import com.pzj.core.stock.validator.rule.QueryStockRuleByIdValidator;
import com.pzj.core.stock.validator.rule.QueryStockRuleByIdsValidator;
import com.pzj.core.stock.validator.rule.QueryStockRulePageValidator;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.entity.QueryResult;

/**
 * 
 * @author dongchunfu
 * @version $Id: StockRuleQueryServiceImpl.java, v 0.1 2016年7月25日 下午3:35:37 dongchunfu Exp $
 */
@Service(value = "stockRuleQueryService")
public class StockRuleQueryServiceImpl implements StockRuleQueryService {
	private static final Logger logger = LoggerFactory.getLogger(StockRuleQueryServiceImpl.class);

	@Resource(name = "stockRuleQueryByPageEngine")
	private StockRuleQueryByPageEngine stockRuleQueryByPageEngine;
	@Resource(name = "stockRuleQueryByIdEngine")
	private StockRuleQueryByIdEngine stockRuleQueryByIdEngine;
	@Resource(name = "stockRuleQueryByParamEngine")
	private StockRuleQueryByParamEngine stockRuleQueryByParamEngine;
	@Resource(name = "stockRuleQueryByIdsEngine")
	private StockRuleQueryByIdsEngine stockRuleQueryByIdsEngine;

	@Resource(name = "queryStockRuleByIdValidator")
	private QueryStockRuleByIdValidator queryStockRuleByIdValidator;
	@Resource(name = "queryStockRulePageValidator")
	private QueryStockRulePageValidator queryStockRulePageValidator;
	@Resource(name = "queryStockRuleByIdsValidator")
	private QueryStockRuleByIdsValidator queryStockRuleByIdsValidator;

	@Resource(name = "stockRulesConverter")
	private StockRulesConverter stockRulesConverter;
	@Resource(name = "packagResult4Category")
	private PackagResult4Category packagResult4Category;

	/** 
	 * @see com.pzj.stock.service.stock.StockRuleQueryService#queryStockRuleById(java.lang.Long)
	 */
	@Override
	public Result<StockRuleModel> queryStockRuleById(StockRuleQueryRequestModel model, ServiceContext context) {
		Result<StockRuleModel> result = new Result<StockRuleModel>();
		Boolean success = queryStockRuleByIdValidator.convert(model, context);
		if (!success) {
			logger.error("illegal param ,request:{}, context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//            throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("quering stock ruel by id ,request: {}, context: {}.", model, context);
		}

		try {

			StockRule response = stockRuleQueryByIdEngine.selectStockRuleById(model.getRuleId(), context);
			if (!CommonUtils.checkObjectIsNull(response)) {
				StockRuleModel stockRuleModel = new StockRuleModel();
				PropertyUtils.copyProperties(stockRuleModel, response);
				result.setData(stockRuleModel);
			}

		} catch (Throwable e) {
			logger.error("query stock rule by id fail ,request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			throw t instanceof ServiceException ? (ServiceException) t : new StockException(
			//					StockExceptionCode.STOCK_ERR_MSG, t);

		}
		if (logger.isDebugEnabled()) {
			logger.debug("query stock rule id success ,result: {}.", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.stock.service.stock.StockRuleQueryService#queryStockRuleBySupplierId(java.lang.Long)
	 */
	@Override
	public Result<QueryResult<StockRuleModel>> queryStockRulePage(StockRuleQueryRequestModel model,
			ServiceContext context) {
		Result<QueryResult<StockRuleModel>> result = new Result<QueryResult<StockRuleModel>>();
		Boolean success = queryStockRulePageValidator.convert(model, context);
		if (!success) {
			logger.error(" query stock rule by page illegal param ,request:{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query stock rule by page ,request:{},context:{}.", model, context);
		}

		try {
			QueryResult<StockRule> qr = stockRuleQueryByPageEngine.queryRuleByPage(model);
			QueryResult<StockRuleModel> queryResult = new QueryResult<StockRuleModel>(model.getCurrentPage(),
					model.getPageSize());
			if (!CommonUtils.checkObjectIsNull(qr)) {
				List<StockRule> list = qr.getRecords();
				if (!CommonUtils.checkCollectionIsNull(list)) {
					ArrayList<StockRuleModel> response = stockRulesConverter.convert(list, context);
					queryResult.setTotal(qr.getTotal());
					queryResult.setRecords(response);
				}
			}

			result.setData(queryResult);

		} catch (Throwable e) {
			logger.error("query stock rule by page fail,request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			throw t instanceof ServiceException ? (ServiceException) t : new StockException(
			//					StockExceptionCode.STOCK_ERR_MSG, t);

		}
		if (logger.isDebugEnabled()) {
			logger.debug("query stock rule by page success,result:{}", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.StockRuleQueryService#queryStockRule4Category(com.pzj.core.stock.model.StockRuleQueryRequestModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<StockRuleModel> queryStockRule4Category(StockRuleQueryRequestModel model, ServiceContext context) {
		Result<StockRuleModel> result = new Result<StockRuleModel>();
		if (logger.isDebugEnabled()) {
			logger.debug("query stock rule for category request:{}, context:{}.", model, context);
		}
		try {
			Result<ArrayList<StockRuleModel>> queryStockRulesByParamResult = queryStockRulesByParam(model, context);
			if (queryStockRulesByParamResult.isOk()) {
				ArrayList<StockRuleModel> data = queryStockRulesByParamResult.getData();
				result.setData(packagResult4Category.packagResult(data, context));
			}

		} catch (Throwable e) {
			logger.error("query stock rule for category  failed ,request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			throw t instanceof ServiceException ? (ServiceException) t : new StockException(
			//					StockExceptionCode.STOCK_ERR_MSG, t);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query stock rule for category success, result:{}.", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.StockRuleQueryService#queryStockRulesByParam(com.pzj.core.stock.model.StockRuleQueryRequestModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ArrayList<StockRuleModel>> queryStockRulesByParam(StockRuleQueryRequestModel model,
			ServiceContext context) {
		Result<ArrayList<StockRuleModel>> result = new Result<ArrayList<StockRuleModel>>();
		if (CommonUtils.checkObjectIsNull(model)) {
			logger.error(" illegal param ,request:{}, context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("quering stock rule by param ,request:{},context:{}.", model, context);
		}
		try {

			List<StockRule> rules = stockRuleQueryByParamEngine.queryRuleByParam(model, context);
			if (!CommonUtils.checkCollectionIsNull(rules)) {
				result.setData(stockRulesConverter.convert(rules, context));
			}

		} catch (Throwable e) {
			logger.error("query stock rule by param failed ,request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			throw t instanceof ServiceException ? (ServiceException) t : new StockException(
			//					StockExceptionCode.STOCK_ERR_MSG, t);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query stock rule by param success,result:{}.", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.StockRuleQueryService#queryStockRulesByIds(com.pzj.core.stock.model.QueryStockRuleByIdsModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ArrayList<StockRuleModel>> queryStockRulesByIds(QueryStockRuleByIdsModel model, ServiceContext context) {
		Result<ArrayList<StockRuleModel>> result = new Result<ArrayList<StockRuleModel>>();
		ParamModel paramModel = queryStockRuleByIdsValidator.convert(model, context);
		if (!paramModel.paramIsOk()) {
			logger.error("query stock rule by rule ids fail, Illegal params. request: {}, context: {}.", model, context);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query stock rule by rule ids. request: {}, context: {}.", model, context);
		}

		try {
			List<StockRule> stockRuleList = stockRuleQueryByIdsEngine.queryStockRuleByIds(model);
			if (!CommonUtils.checkCollectionIsNull(stockRuleList)) {
				ArrayList<StockRuleModel> stockRuleModelList = new ArrayList<StockRuleModel>();
				StockRuleModel stockRuleModel = null;
				for (StockRule stockRule : stockRuleList) {
					stockRuleModel = new StockRuleModel();
					MFBeanCopier.copyProperties(stockRuleModel, stockRule);
					stockRuleModelList.add(stockRuleModel);
				}
				result.setData(stockRuleModelList);
			}

		} catch (Throwable e) {
			logger.error("query stock rule by rule ids fail. request: " + model + ", context: " + context, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw (ServiceException) e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query stock rule by rule ids success. result: {}", JSONConverter.toJson(result));
		}
		return result;
	}

}
