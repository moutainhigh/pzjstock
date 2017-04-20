/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.common.utils.MFBeanCopier;
import com.pzj.core.stock.converter.StocksConverter;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.event.QueryRuleByShow;
import com.pzj.core.stock.event.QueryRuleRelAllAvaiStockTime;
import com.pzj.core.stock.model.CheckStockModel;
import com.pzj.core.stock.model.CheckStockModel.QueryStockType;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.QueryStockByShowReqModel;
import com.pzj.core.stock.model.StockDateListQueryModel;
import com.pzj.core.stock.model.StockModel;
import com.pzj.core.stock.model.StockQueryRequestModel;
import com.pzj.core.stock.model.StockRealTimeModel;
import com.pzj.core.stock.model.StockRealTimeQueryModel;
import com.pzj.core.stock.model.StockRulesDateReqModel;
import com.pzj.core.stock.model.SupplierLockStockModel;
import com.pzj.core.stock.model.SupplierLockStockQueryModel;
import com.pzj.core.stock.model.UserOccupyStockModel;
import com.pzj.core.stock.service.StockQueryService;
import com.pzj.core.stock.stockquery.QueryStockByDateListEngine;
import com.pzj.core.stock.stockquery.QueryStockByRuleEngine;
import com.pzj.core.stock.stockquery.QueryStockBySupBatchOpeEngine;
import com.pzj.core.stock.stockquery.QuerySupplierLockStockEngine;
import com.pzj.core.stock.stockquery.QuerySupplierRealTimeStockEngine;
import com.pzj.core.stock.stockquery.QueryUserBatchOccupyStockEngine;
import com.pzj.core.stock.stockquery.StockQueryByDateEngine;
import com.pzj.core.stock.stockquery.StockQueryByIdEngine;
import com.pzj.core.stock.stockquery.StockQueryParamEngine;
import com.pzj.core.stock.stockquery.StockQueryRangeEngine;
import com.pzj.core.stock.validator.stock.CheckStockIsEnoughValidator;
import com.pzj.core.stock.validator.stock.QueryStockByDateListValidator;
import com.pzj.core.stock.validator.stock.QueryStockByDateValidator;
import com.pzj.core.stock.validator.stock.QueryStockByIdValidator;
import com.pzj.core.stock.validator.stock.QueryStockByMonthValidator;
import com.pzj.core.stock.validator.stock.QueryStockByRuleValidator;
import com.pzj.core.stock.validator.stock.QueryStockRangeValidator;
import com.pzj.core.stock.validator.stock.QueryStocksValidator;
import com.pzj.core.stock.validator.stock.QuerySupplierLockStockValidator;
import com.pzj.core.stock.validator.stock.QuerySupplierRealTimeStockValidator;
import com.pzj.core.stock.validator.stock.QueryUserBatchOccupyStockValidator;
import com.pzj.core.util.DateUtil;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 库存查询服务接口实现
 * 
 * @author dongchunfu
 * @version $Id: StockQueryServiceImpl.java, v 0.1 2016年7月25日 下午3:40:58 dongchunfu Exp $
 */
@Service(value = "stockQueryService")
public class StockQueryServiceImpl implements StockQueryService {

	private static final Logger logger = LoggerFactory.getLogger(StockQueryServiceImpl.class);

	@Resource(name = "stockQueryParamEngine")
	private StockQueryParamEngine stockQueryParamEngine;
	@Resource(name = "stockQueryByIdEngine")
	private StockQueryByIdEngine stockQueryByIdEngine;
	@Resource(name = "stockQueryByDateEngine")
	private StockQueryByDateEngine stockQueryByDateEngine;
	@Resource(name = "stockQueryRangeEngine")
	private StockQueryRangeEngine stockQueryRangeEngine;
	@Resource(name = "queryUserBatchOccupyStockEngine")
	private QueryUserBatchOccupyStockEngine queryUserBatchOccupyStockEngine;
	@Resource(name = "queryStockByDateListEngine")
	private QueryStockByDateListEngine queryStockByDateListEngine;
	@Resource(name = "queryStockByRuleEngine")
	private QueryStockByRuleEngine queryStockByRuleEngine;
	@Resource(name = "queryStockBySupBatchOpeEngine")
	private QueryStockBySupBatchOpeEngine queryStockBySupBatchOpeEngine;
	@Resource(name = "querySupplierRealTimeStockEngine")
	private QuerySupplierRealTimeStockEngine querySupplierRealTimeStockEngine;
	@Resource(name = "querySupplierLockStockEngine")
	private QuerySupplierLockStockEngine querySupplierLockStockEngine;

	@Resource(name = "queryStockByIdValidator")
	private QueryStockByIdValidator queryStockByIdValidator;
	@Resource(name = "queryStockRangeValidator")
	private QueryStockRangeValidator queryStockRangeValidator;
	@Resource(name = "queryStockByMonthValidator")
	private QueryStockByMonthValidator queryStockByMonthValidator;
	@Resource(name = "queryStockByDateValidator")
	private QueryStockByDateValidator queryStockByDateValidator;
	@Resource(name = "queryStocksValidator")
	private QueryStocksValidator queryStocksValidator;
	@Resource(name = "checkStockIsEnoughValidator")
	private CheckStockIsEnoughValidator checkStockIsEnoughValidator;
	@Resource(name = "queryUserBatchOccupyStockValidator")
	private QueryUserBatchOccupyStockValidator queryUserBatchOccupyStockValidator;
	@Resource(name = "queryStockByDateListValidator")
	private QueryStockByDateListValidator queryStockByDateListValidator;
	@Resource(name = "queryStockByRuleValidator")
	private QueryStockByRuleValidator queryStockByRuleValidator;
	@Resource(name = "querySupplierRealTimeStockValidator")
	private QuerySupplierRealTimeStockValidator querySupplierRealTimeStockValidator;
	@Resource(name = "querySupplierLockStockValidator")
	private QuerySupplierLockStockValidator querySupplierLockStockValidator;

	@Resource(name = "stocksConverter")
	private StocksConverter stocksConverter;

	@Resource(name = "dateUtil")
	private DateUtil dateUtil;

	@Resource(name = "queryRuleRelAllAvaiStockTime")
	private QueryRuleRelAllAvaiStockTime queryRuleRelAllAvaiStockTime;
	@Resource(name = "queryRuleByShow")
	private QueryRuleByShow queryRuleByShow;

	/** 
	 * @see com.pzj.stock.service.stock.StockQueryService#queryStockById(java.lang.Long, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<StockModel> queryStockById(StockQueryRequestModel model, ServiceContext context) {
		Result<StockModel> result = new Result<StockModel>();
		Boolean success = queryStockByIdValidator.convert(model, context);
		if (!success) {
			logger.error("illegal param ,request:{}, context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query stock by id ,request:{}, context:{}.", model, context);
		}

		try {

			Stock stock = stockQueryByIdEngine.selectStockById(model.getStockId(), context);
			if (CommonUtils.checkObjectIsNull(stock)) {
				return result;
			}
			StockModel stockModel = new StockModel();
			MFBeanCopier.copyProperties(stockModel, stock);
			result.setData(stockModel);

		} catch (Throwable e) {
			logger.error("query stock by id error,request:{},context:{}.", model, context, e);

			CommonUtils.convertException(e, result);

			//			throw t instanceof ServiceException ? (ServiceException) t : new StockException(
			//					StockExceptionCode.STOCK_ERR_MSG, t);

		}
		if (logger.isDebugEnabled()) {
			logger.debug("query stock by id success ,result: {}, context: {} ", JSONConverter.toJson(result), context);
		}
		return result;
	}

	/** 
	 * @see com.pzj.stock.service.stock.StockQueryService#queryStockByDate(java.lang.Long, java.util.Date, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<StockModel> queryStockByDate(StockQueryRequestModel model, ServiceContext context) {
		Result<StockModel> result = new Result<StockModel>();
		Boolean success = queryStockByDateValidator.convert(model, context);
		if (!success) {
			logger.error("query stock by date illegal param ,request:{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query stock by date ,request: {}, context: {}.", model, context);
		}

		try {
			ArrayList<Stock> list = stockQueryByDateEngine.queryStockByDate(model, context);
			if (CommonUtils.checkCollectionIsNull(list)) {
				return result;
			}

			StockModel stockModel = new StockModel();
			MFBeanCopier.copyProperties(stockModel, list.get(0));
			result.setData(stockModel);

		} catch (Throwable e) {
			logger.error("query stock by date fail ,request:{},context:{}.", model, context, e);

			CommonUtils.convertException(e, result);

			//			throw t instanceof ServiceException ? (ServiceException) t : new StockException(
			//					StockExceptionCode.STOCK_ERR_MSG, t);

		}
		if (logger.isDebugEnabled()) {
			logger.debug("query stock by date success ,result: {}, context: {}", JSONConverter.toJson(result), context);
		}
		return result;
	}

	/** 
	 * @see com.pzj.stock.service.stock.StockQueryService#queryAllStocks(java.lang.Long, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ArrayList<StockModel>> queryAllStocks(StockQueryRequestModel model, ServiceContext context) {
		Result<ArrayList<StockModel>> result = new Result<ArrayList<StockModel>>();
		Boolean success = queryStocksValidator.convert(model, context);
		if (!success) {
			logger.error("illegal param ,request: {}, context: {}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query all stocks ,request: {}, context: {}.", model, context);
		}

		try {
			ArrayList<Stock> list = stockQueryParamEngine.queryStockByParam(model, context);
			if (!CommonUtils.checkCollectionIsNull(list)) {
				result.setData(stocksConverter.convert(list, context));
			}
		} catch (Throwable e) {
			logger.error(" query all stocks error,request:{},context:{}. ", model, context, e);
			CommonUtils.convertException(e, result);
			//			throw t instanceof ServiceException ? (ServiceException) t : new StockException(
			//					StockExceptionCode.STOCK_ERR_MSG, t);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query all stocks success ,result: {}.", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.stock.service.stock.StockQueryService#queryRangeStocks(com.pzj.stock.StockRequestModel.StockQueryRequestBean, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ArrayList<StockModel>> queryRangeStocks(StockQueryRequestModel model, ServiceContext context) {
		Result<ArrayList<StockModel>> result = new Result<ArrayList<StockModel>>();

		Boolean success = queryStockRangeValidator.convert(model, context);
		if (!success) {
			logger.error("query stock by range illegal param ,request:{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query range stocks ,request: {}, context: {}.", model, context);
		}

		try {
			ArrayList<Stock> list = stockQueryRangeEngine.queryRangeStocks(model, context);
			if (!CommonUtils.checkCollectionIsNull(list)) {
				result.setData(stocksConverter.convert(list, context));
			}

		} catch (Throwable e) {
			logger.error(" query range stocks error ,request:{},context:{}. ", model, context, e);
			CommonUtils.convertException(e, result);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query range stocks success ,result: {}.", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.stock.service.stock.StockQueryService#queryMonthStocks(com.pzj.stock.StockRequestModel.StockQueryRequestBean, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ArrayList<StockModel>> queryMonthStocks(StockQueryRequestModel model, ServiceContext context) {
		Result<ArrayList<StockModel>> result = new Result<ArrayList<StockModel>>();
		Boolean success = queryStockByMonthValidator.convert(model, context);
		Integer queryMonth = model.getQueryMonth();
		if (!success || CommonUtils.checkIntegerIsNull(queryMonth, true)) {
			logger.error("query stock by month illegal param ,request:{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query stock by month ,request:{},context:{}.", model, context);
		}

		Integer month = Integer.valueOf(String.valueOf(queryMonth).substring(4, 6));//查询月份
		Calendar calendar = Calendar.getInstance();
		int currentMonth = calendar.get(Calendar.MONTH) + 1;//当前月份
		Date currentDate = new Date();
		int beginStockTime;
		int endStockTime;

		try {
			if (month == currentMonth) {
				beginStockTime = dateUtil.getIntegerTime(currentDate);
				int lastDay = dateUtil.getLastDay(currentDate);
				endStockTime = queryMonth / 100 * 100 + lastDay;
				model.setBeginStockTime(String.valueOf(beginStockTime));
				model.setEndStockTime(String.valueOf(endStockTime));
				if (logger.isDebugEnabled()) {
					logger.debug("quering month stocks success,request: {}, context: {}.", model, context);
				}
				return queryRangeStocks(model, context);

			} else {
				model.setBeginStockTime(String.valueOf(queryMonth));
				model.setEndStockTime(String.valueOf(queryMonth / 100 * 100
						+ dateUtil.getLastDay(dateUtil.getDate(queryMonth))));
				if (logger.isDebugEnabled()) {
					logger.debug("quering month stocks success ,request: {}, context: {}.", model, context);
				}
				return queryRangeStocks(model, context);
			}
		} catch (Throwable e) {
			logger.error(" query month stocks error,request:{},context:{}. ", model, context, e);
			CommonUtils.convertException(e, result);
			//			if (t instanceof ServiceException) {
			//				throw (ServiceException) t;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, t);
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.StockQueryService#judgeStockIsEnough(com.pzj.core.stock.model.CheckStockModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Boolean> judgeStockIsEnough(CheckStockModel checkStockModel, ServiceContext context) {
		Result<Boolean> result = new Result<Boolean>(Boolean.FALSE);
		ParamModel paramModel = checkStockIsEnoughValidator.convert(checkStockModel, context);
		if (!paramModel.paramIsOk()) {
			logger.error("judge stock is enough fail, Illegal params. request: {}, context: {}.", checkStockModel,
					context);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("judge stock is enough. request: {}, context: {}.", checkStockModel, context);
		}

		try {
			Result<StockModel> stockResult = null;
			StockQueryRequestModel model = new StockQueryRequestModel();
			model.setRuleId(checkStockModel.getStockRuleId());
			model.setStockId(checkStockModel.getStockId());
			model.setStockTime(checkStockModel.getStockTime());
			if (QueryStockType.STOCK_ID.key == checkStockModel.getQueryType()) {
				stockResult = this.queryStockById(model, context);
			} else if (QueryStockType.STOCK_RULE_ID.key == checkStockModel.getQueryType()) {
				stockResult = this.queryStockByRule(model, context);
			}

			if (!Check.NuNObject(stockResult, stockResult.getData())) {
				StockModel stockModel = stockResult.getData();
				int remainNum = stockModel.getRemainNum();
				int occupyNum = checkStockModel.getOccupyNum();
				if (remainNum >= occupyNum) {
					result.setData(Boolean.TRUE);
				}
			}
		} catch (Throwable e) {
			logger.error("judge stock is enough error. request: " + checkStockModel + ", context: " + context, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("judge stock is enough success. result: {}, context: {}.", JSONConverter.toJson(result),
					context);
		}

		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.StockQueryService#queryUserBatchOccupyStock(com.pzj.core.stock.model.UserOccupyStockModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<UserOccupyStockModel> queryUserBatchOccupyStock(UserOccupyStockModel userOccupyStockModel,
			ServiceContext context) {
		Result<UserOccupyStockModel> result = new Result<UserOccupyStockModel>();
		ParamModel paramModel = queryUserBatchOccupyStockValidator.convert(userOccupyStockModel, context);
		if (!paramModel.paramIsOk()) {
			logger.error("query user batch occupy stock fail, Illegal params. request: {}, context: {}.",
					userOccupyStockModel, context);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query user batch occupy stock. request: {}, context: {}.", userOccupyStockModel, context);
		}

		try {
			queryUserBatchOccupyStockEngine.queryUserOneRuleOccupyStock(userOccupyStockModel);
			result.setData(userOccupyStockModel);

		} catch (Throwable e) {
			logger.error("query user batch occupy stock fail. request: " + userOccupyStockModel + ", context: "
					+ context, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query user batch occupy stock success. result: {} .", JSONConverter.toJson(result));
		}

		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.StockQueryService#queryStockByDateList(com.pzj.core.stock.model.StockDateListQueryModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ArrayList<StockModel>> queryStockByDateList(StockDateListQueryModel model, ServiceContext context) {
		Result<ArrayList<StockModel>> result = new Result<ArrayList<StockModel>>();
		ParamModel paramModel = queryStockByDateListValidator.convert(model, context);
		if (!paramModel.paramIsOk()) {
			logger.error("query stock by date list fail, Illegal params. request: {}, context: {}.", model, context);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query stock by date list. request: {}, context: {}.", model, context);
		}

		try {
			List<Stock> stockList = queryStockByDateListEngine.queryStockByDateList(model);
			if (!CommonUtils.checkObjectIsNull(stockList)) {
				ArrayList<StockModel> stockModelList = new ArrayList<StockModel>();
				StockModel stockModel = null;
				for (Stock stock : stockList) {
					stockModel = new StockModel();
					MFBeanCopier.copyProperties(stockModel, stock);
					stockModelList.add(stockModel);
				}
				result.setData(stockModelList);
			}

		} catch (Throwable e) {
			logger.error("query stock by date list fail. request: " + model + ", context: " + context, e);
			CommonUtils.convertException(e, result);
			//			if (e instanceof ServiceException) {
			//				throw (ServiceException) e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query stock by date list success. result: {}", JSONConverter.toJson(result));
		}

		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.StockQueryService#queryStockByRule(com.pzj.core.stock.model.StockQueryRequestModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<StockModel> queryStockByRule(StockQueryRequestModel model, ServiceContext context) {
		Result<StockModel> result = new Result<StockModel>();
		ParamModel paramModel = queryStockByRuleValidator.convert(model, context);
		if (!paramModel.paramIsOk()) {
			logger.error("query stock by rule fail, Illegal params. request: {}, context: {}.", model, context);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query stock by rule. request: {}, context: {}.", model, context);
		}

		try {
			Stock stock = queryStockByRuleEngine.queryStockByRule(model);
			if (!CommonUtils.checkObjectIsNull(stock)) {
				StockModel stockModel = new StockModel();
				MFBeanCopier.copyProperties(stockModel, stock);
				result.setData(stockModel);
			}

		} catch (Throwable e) {
			logger.error("query stock by rule fail. request: " + model + ", context: " + context, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw (ServiceException) e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query stock by rule success. result: {}", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.StockQueryService#querySupplierOperateStockList(com.pzj.core.stock.model.StockDateListQueryModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ArrayList<StockModel>> querySupplierBatchOperateStockList(StockDateListQueryModel model,
			ServiceContext context) {
		Result<ArrayList<StockModel>> result = new Result<ArrayList<StockModel>>();
		ParamModel paramModel = queryStockByDateListValidator.convert(model, context);
		if (!paramModel.paramIsOk()) {
			logger.error("query supplier batch operate stock fail, Illegal params. request: {}, context: {}.", model,
					context);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query supplier batch operate stock. request: {}, context: {}.", JSONConverter.toJson(model),
					context);
		}

		try {
			//筛选过滤查询规则可用所有时间
			queryRuleRelAllAvaiStockTime.queryRuleAllAvaiStockTime(model);

			//获取所有供应商可操作库存集合
			List<Stock> stockList = queryStockBySupBatchOpeEngine.querySupBatchStock(model);
			if (logger.isDebugEnabled()) {
				logger.debug("过滤获取供应商可操作库存集合, 参数model: {}, stocks: {}.", model, JSONConverter.toJson(stockList));
			}

			if (!CommonUtils.checkCollectionIsNull(stockList)) {
				ArrayList<StockModel> stockModelList = new ArrayList<StockModel>();
				StockModel stockModel = null;
				for (Stock stock : stockList) {
					stockModel = new StockModel();
					MFBeanCopier.copyProperties(stockModel, stock);
					stockModelList.add(stockModel);
				}
				result.setData(stockModelList);
			}

		} catch (Throwable e) {
			logger.error("query supplier batch operate stock fail. request: " + model + ", context: " + context, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw (ServiceException) e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query supplier batch operate stock success. result: {}", JSONConverter.toJson(result));
		}

		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.StockQueryService#querySupplierRealTimeStockList(com.pzj.core.stock.model.StockRealTimeQueryModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ArrayList<StockRealTimeModel>> querySupplierRealTimeStockList(StockRealTimeQueryModel model,
			ServiceContext context) {
		Result<ArrayList<StockRealTimeModel>> result = new Result<ArrayList<StockRealTimeModel>>();
		ParamModel paramModel = querySupplierRealTimeStockValidator.convert(model, context);
		if (!paramModel.paramIsOk()) {
			logger.error("query supplier real time stock list fail, Illegal params. request: {}, context: {}.", model,
					context);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query supplier real time stock list. request: {}, context: {}.", model, context);
		}

		try {
			ArrayList<StockRealTimeModel> stockRealTimeModelList = querySupplierRealTimeStockEngine
					.querySupplierRealTimeStock(model);
			if (!CommonUtils.checkCollectionIsNull(stockRealTimeModelList)) {
				result.setData(stockRealTimeModelList);
			}

		} catch (Throwable e) {
			logger.error("query supplier real time stock list fail. request: " + model + ", context: " + context, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw (ServiceException) e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query supplier real time stock list success. result: {}.", JSONConverter.toJson(result));
		}

		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.StockQueryService#querySupplierLockStock(com.pzj.core.stock.model.SupplierLockStockQueryModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<SupplierLockStockModel> querySupplierLockStock(SupplierLockStockQueryModel model,
			ServiceContext context) {
		Result<SupplierLockStockModel> result = new Result<SupplierLockStockModel>();
		ParamModel paramModel = querySupplierLockStockValidator.convert(model, context);
		if (!paramModel.paramIsOk()) {
			logger.error("query supplier lock stock fail, Illegal params. request: {}, context: {}.", model, context);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query supplier lock stock. request: {}, context: {}.", model, context);
		}

		try {
			SupplierLockStockModel supplierLockStockModel = querySupplierLockStockEngine.querySupplierLockStock(model);
			if (!CommonUtils.checkObjectIsNull(supplierLockStockModel)) {
				result.setData(supplierLockStockModel);
			}

		} catch (Throwable e) {
			logger.error("query supplier lock stock fail. request: " + model + ", context: " + context, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw (ServiceException) e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query supplier lock stock success. result: {}.", JSONConverter.toJson(result));
		}

		return result;
	}

	@Override
	public Result<StockModel> queryStockByShow(QueryStockByShowReqModel reqModel, ServiceContext context) {
		Result<StockModel> result = new Result<StockModel>();
		ParamModel paramModel = checkShowStock(reqModel);
		if (!paramModel.paramIsOk()) {
			logger.error("query stock by show area screeing illegal param :{}", JSONConverter.toJson(reqModel));
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query stock by show area screeing.request:{}", JSONConverter.toJson(reqModel));
		}

		Long ruleId = null;
		try {
			ruleId = queryRuleByShow.queryRuleByScreeingArea(reqModel);
			if (CommonUtils.checkLongIsNull(ruleId, true)) {
				return result;
			}
		} catch (Throwable e) {
			logger.error("query stock by show area screeing fail,param:{}", JSONConverter.toJson(reqModel), e);
			CommonUtils.convertException(e, result);
			return result;
		}

		try {
			StockQueryRequestModel model = new StockQueryRequestModel();
			model.setRuleId(ruleId);
			model.setStockTime(CommonUtils.getStockDateInteger(reqModel.getShowTime()).toString());
			result = this.queryStockByRule(model, context);
		} catch (Throwable e) {
			logger.error("query stock by show area screeing fail,param:{}", JSONConverter.toJson(reqModel), e);
			CommonUtils.convertException(e, result);
			return result;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query stock by show area screeing.result:{}", JSONConverter.toJson(result));
		}
		return result;
	}

	private ParamModel checkShowStock(QueryStockByShowReqModel reqModel) {
		ParamModel paramModel = ParamModel.getInstance();
		if (CommonUtils.checkObjectIsNull(reqModel)) {
			paramModel.setErrorModel("查询演艺库存传入QueryStockByShowReqModel对象为空!");
			return paramModel;
		}
		if (CommonUtils.checkLongIsNull(reqModel.getScreeingId(), Boolean.TRUE)) {
			paramModel.setErrorModel("查询演艺库存传入screeingId场次id为空!");
			return paramModel;
		}
		if (CommonUtils.checkLongIsNull(reqModel.getAreaId(), Boolean.TRUE)) {
			paramModel.setErrorModel("查询演艺库存传入areaId区域id为空!");
			return paramModel;
		}
		if (CommonUtils.checkObjectIsNull(reqModel.getShowTime())) {
			paramModel.setErrorModel("查询演艺库存传入showTime演出时间为空!");
			return paramModel;
		}
		return paramModel;
	}

	@Override
	public Result<ArrayList<StockModel>> queryStockByRulesDate(StockRulesDateReqModel reqModel, ServiceContext context) {
		Result<ArrayList<StockModel>> result = new Result<ArrayList<StockModel>>();
		if (reqModel == null || CommonUtils.checkCollectionIsNull(reqModel.getRuleIds())) {
			logger.error("query stock by rules illegal param. request:{}", JSONConverter.toJson(reqModel));
			CommonUtils.setParamErr(result);
			return result;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query stock by rules request param:{}", JSONConverter.toJson(reqModel));
		}
		try {
			List<Stock> stocks = queryStockByRuleEngine.queryStockByRules(reqModel);
			if (!CommonUtils.checkCollectionIsNull(stocks)) {
				ArrayList<StockModel> stockModels = new ArrayList<StockModel>();
				StockModel stockModel = null;
				for (Stock stock : stocks) {
					stockModel = new StockModel();
					MFBeanCopier.copyProperties(stockModel, stock);
					stockModels.add(stockModel);
				}
				result.setData(stockModels);
			}
		} catch (Throwable e) {
			logger.error("query stock by rules fail,param:{}", JSONConverter.toJson(reqModel), e);
			CommonUtils.convertException(e, result);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query stock by rules result:{}", JSONConverter.toJson(reqModel));
		}
		return result;
	}
}
