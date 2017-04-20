/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.enums.StockRuleTypeEnum;
import com.pzj.core.stock.event.CallSkuService4CreateStock;
import com.pzj.core.stock.event.FilterStockRuleDateInit;
import com.pzj.core.stock.event.GenerateDefaultStockTimes;
import com.pzj.core.stock.event.ScreenRuleTypes;
import com.pzj.core.stock.event.StatisticDateEvent;
import com.pzj.core.stock.exception.errcode.StockRuleExceptionCode;
import com.pzj.core.stock.exception.rule.NotFoundStockRuleException;
import com.pzj.core.stock.model.AddStockModel;
import com.pzj.core.stock.model.CreateStockModel;
import com.pzj.core.stock.model.OccupyStockRequestModel;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.QueryStockByShowReqModel;
import com.pzj.core.stock.model.QueryStockRuleByIdsModel;
import com.pzj.core.stock.model.StockBatchLockModel;
import com.pzj.core.stock.model.StockDateListQueryModel;
import com.pzj.core.stock.model.StockModel;
import com.pzj.core.stock.model.SupplierManualLockModel;
import com.pzj.core.stock.model.SupplierManualUnlockModel;
import com.pzj.core.stock.model.UpdateAreaStockReqModel;
import com.pzj.core.stock.rulequery.StockRuleQueryByIdEngine;
import com.pzj.core.stock.rulequery.StockRuleQueryByIdsEngine;
import com.pzj.core.stock.rulequery.StockRuleQueryByParamEngine;
import com.pzj.core.stock.service.StockQueryService;
import com.pzj.core.stock.service.StockService;
import com.pzj.core.stock.stockquery.QueryStockByDateListEngine;
import com.pzj.core.stock.stockupdate.CreateDailyStockEngine;
import com.pzj.core.stock.stockupdate.CreateSingleStockEngine;
import com.pzj.core.stock.stockupdate.OccupyStockEngine;
import com.pzj.core.stock.stockupdate.ReleaseStockEngine;
import com.pzj.core.stock.stockupdate.RollbackOccupyStockEngine;
import com.pzj.core.stock.stockupdate.StockBatchLockEngine;
import com.pzj.core.stock.stockupdate.StockExistEngine;
import com.pzj.core.stock.stockupdate.SupplierManualLockStockEngine;
import com.pzj.core.stock.stockupdate.SupplierManualUnLockStockEngine;
import com.pzj.core.stock.stockupdate.UpdateAreaStockEngine;
import com.pzj.core.stock.validator.stock.CreateStockValidator;
import com.pzj.core.stock.validator.stock.SupplierManualLockStockValidator;
import com.pzj.core.stock.validator.stock.SupplierManualUnLockStockValidator;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: StockServiceImpl.java, v 0.1 2016年7月26日 上午10:05:12 Administrator Exp $
 */
@Service("stockService")
public class StockServiceImpl implements StockService {
	private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

	@Resource(name = "occupyStockEngine")
	private OccupyStockEngine occupyStockEngine;
	@Resource(name = "releaseStockEngine")
	private ReleaseStockEngine releaseStockEngine;
	@Resource(name = "stockBatchLockEngine")
	private StockBatchLockEngine stockBatchLockEngine;
	@Resource(name = "stockRuleQueryByIdEngine")
	private StockRuleQueryByIdEngine stockRuleQueryByIdEngine;
	@Resource(name = "stockRuleQueryByParamEngine")
	private StockRuleQueryByParamEngine stockRuleQueryByParamEngine;
	@Resource(name = "createSingleStockEngine")
	private CreateSingleStockEngine createSingleStockEngine;
	@Resource(name = "createDailyStockEngine")
	private CreateDailyStockEngine createDailyStockEngine;
	@Resource(name = "rollbackOccupyStockEngine")
	private RollbackOccupyStockEngine rollbackOccupyStockEngine;
	@Resource(name = "stockExistEngine")
	private StockExistEngine stockExistEngine;
	@Resource(name = "queryStockByDateListEngine")
	private QueryStockByDateListEngine queryStockByDateListEngine;
	@Resource(name = "supplierManualLockStockEngine")
	private SupplierManualLockStockEngine supplierManualLockStockEngine;
	@Resource(name = "supplierManualUnLockStockEngine")
	private SupplierManualUnLockStockEngine supplierManualUnLockStockEngine;
	@Resource(name = "stockRuleQueryByIdsEngine")
	private StockRuleQueryByIdsEngine stockRuleQueryByIdsEngine;
	@Resource(name = "updateAreaStockEngine")
	private UpdateAreaStockEngine updateAreaStockEngine;
	@Resource(name = "occupyStockValidator")
	private ObjectConverter<List<OccupyStockRequestModel>, ServiceContext, ParamModel> occupyStockValidator;
	@Resource(name = "releaseStockValidator")
	private ObjectConverter<List<OccupyStockRequestModel>, ServiceContext, ParamModel> releaseStockValidator;
	@Resource(name = "stockBatchLockValidator")
	private ObjectConverter<StockBatchLockModel, ServiceContext, ParamModel> stockBatchLockValidator;
	@Resource(name = "rollbackOccupyStockValidator")
	private ObjectConverter<OccupyStockRequestModel, ServiceContext, ParamModel> rollbackOccupyStockValidator;
	@Resource(name = "createStockValidator")
	private CreateStockValidator createStockValidator;
	@Resource(name = "supplierManualLockStockValidator")
	private SupplierManualLockStockValidator supplierManualLockStockValidator;
	@Resource(name = "supplierManualUnLockStockValidator")
	private SupplierManualUnLockStockValidator supplierManualUnLockStockValidator;

	@Resource(name = "callSkuService4CreateStock")
	private CallSkuService4CreateStock callSkuService4CreateStock;
	@Resource(name = "statisticDateEvent")
	private StatisticDateEvent statisticDateEvent;
	@Resource(name = "generateDefaultStockTimes")
	private GenerateDefaultStockTimes generateDefaultStockTimes;
	@Resource(name = "filterStockRuleInit")
	private FilterStockRuleDateInit filterStockRuleInit;

	@Resource(name = "screenRuleTypes")
	private ScreenRuleTypes screenRuleTypes;
	@Resource(name = "stockQueryService")
	private StockQueryService stockQueryService;

	/** 
	 * @see com.pzj.stock.service.stock.StockService#useStock(com.pzj.core.stock.model.OccupyStockRequestModel)
	 */
	@Override
	public Result<Boolean> occupyStock(ArrayList<OccupyStockRequestModel> orderStockModelList,
			ServiceContext serviceContext) {
		Result<Boolean> result = new Result<Boolean>(Boolean.FALSE);
		ParamModel paramModel = occupyStockValidator.convert(orderStockModelList, serviceContext);
		if (!paramModel.paramIsOk()) {
			logger.error("occupy stock fail, Illegal params. request: {}, context: {}.", orderStockModelList,
					serviceContext);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("occupy stock. request: {}, context: {}.", orderStockModelList, serviceContext);
		}

		try {
			occupyStockEngine.occupyStock(orderStockModelList);
			result.setData(Boolean.TRUE);
		} catch (Throwable e) {
			logger.error("occupy stock fail. request: " + orderStockModelList + ", context: " + serviceContext, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("occupy stock success. result: {}.", JSONConverter.toJson(result));
		}

		return result;
	}

	/** 
	 * @see com.pzj.stock.service.stock.StockService#releaseStock(com.pzj.core.stock.model.OccupyStockRequestModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Boolean> releaseStock(ArrayList<OccupyStockRequestModel> requestModelList,
			ServiceContext serviceContext) {
		Result<Boolean> result = new Result<Boolean>(Boolean.FALSE);
		ParamModel paramModel = releaseStockValidator.convert(requestModelList, serviceContext);
		if (!paramModel.paramIsOk()) {
			logger.error("release stock fail, Illegal params. request: {}, context: {}.", requestModelList,
					serviceContext);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("release stock. request: {}, context: {}.", requestModelList, serviceContext);
		}

		try {
			releaseStockEngine.releaseStock(requestModelList);
			result.setData(Boolean.TRUE);
		} catch (Throwable e) {
			logger.error("release stock fail. request: " + requestModelList + ", context: " + serviceContext, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("release stock success. result: {}.", JSONConverter.toJson(result));
		}

		return result;
	}

	/** 
	 * @see com.pzj.stock.service.stock.StockService#creatStock(java.lang.Long, java.lang.Long)
	 */

	@Override
	public Result<Integer> createStock(CreateStockModel model, ServiceContext context) {
		Result<Integer> result = new Result<Integer>(0);
		Boolean success = createStockValidator.convert(model, context);
		if (!success) {
			logger.error("illegal param , request:{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" creating stock , request:{},context:{}.", model, context);
		}

		try {
			List<Long> ruleIds = model.getRuleIds();
			QueryStockRuleByIdsModel ruleIdsQueryModel = new QueryStockRuleByIdsModel();
			ruleIdsQueryModel.setStockRuleIds(ruleIds);
			List<StockRule> allRules = stockRuleQueryByIdsEngine.queryStockRuleByIds(ruleIdsQueryModel);

			// 库存集合为空，或者对应的库存规则记录数量不等
			if (null == allRules || allRules.size() != ruleIds.size()) {
				logger.error(" stock rule does not exist , ruleId:{},context:{}.", ruleIds, context);
				throw new NotFoundStockRuleException(StockRuleExceptionCode.NOT_FOUND_STOCK_RULE_ERR_MSG);
			}

			List<StockRule> singleRules = screenRuleTypes.screening(allRules, StockRuleTypeEnum.SINGLE.getRuleType());
			List<StockRule> dailyRules = null;

			if (null != singleRules) {
				allRules.removeAll(singleRules);
			}
			dailyRules = allRules;
			int count = 0;
			if (null != singleRules) {
				// count == 0 , 单一库存记录已存在
				// count == 1 , 单一库存新增成功
				count += creatSingleStock(singleRules, context);
			}

			if (null != dailyRules && dailyRules.size() != 0) {
				//count == 0 ，库存记录已存在，或产品有效期不在预生成范围；
				//count >  0 ，实际新增的库存记录条数。
				count += createDailyStock(dailyRules, context);
			}
			result.setData(count);

			//			return new Result<Integer>(count);
			//库存规则类型未找到，数据维护问题。

		} catch (Throwable e) {
			logger.error(" create stock fail,request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			throw t instanceof ServiceException ? (ServiceException) t : new StockException(
			//					StockExceptionCode.STOCK_ERR_MSG, t);

		}
		if (logger.isDebugEnabled()) {
			logger.debug("create stock success. result: {}.", JSONConverter.toJson(result));
		}
		return result;
	}

	//创建单一类型库存记录
	private int creatSingleStock(List<StockRule> rules, ServiceContext context) {

		//补偿：避免客户端重复调用创建库存记录的操作，增加此判断
		List<StockRule> notExistStockRule = stockExistEngine.notExistStockRule(rules, context);
		if (null == notExistStockRule) {
			if (logger.isDebugEnabled()) {
				logger.debug("stock record is already exist,request:{},context:{}.", rules, context);
			}
			return 0;
		}

		int count = createSingleStockEngine.createSingleStock(notExistStockRule, context);

		if (logger.isDebugEnabled()) {
			logger.debug(" create single stock success , request:{},context:{},response:{}.", rules, context, count);
		}
		return 1;
	}

	//创建日类型库存记录
	private int createDailyStock(List<StockRule> rules, ServiceContext context) {
		int count = 0;
		if (CommonUtils.checkObjectIsNull(rules)) {
			return count;
		}
		Map<Long, StockRule> map = new HashMap<Long, StockRule>();
		List<Long> dailyRuleIds = new ArrayList<Long>();
		for (StockRule rule : rules) {
			dailyRuleIds.add(rule.getId());
			map.put(rule.getId(), rule);
		}

		//1、过滤库存规则对应可用三个月时间
		List<AddStockModel> addStockModelList = filterStockRuleInit.filterStockRuleDate(dailyRuleIds);
		if (!CommonUtils.checkCollectionIsNull(addStockModelList)) {

			List<Stock> stockList = null;
			for (AddStockModel addStockModel : addStockModelList) {
				ArrayList<Integer> stockTimeIntList = addStockModel.getStockTimeIntList();
				StockDateListQueryModel model = new StockDateListQueryModel();
				model.setStockRuleId(addStockModel.getStockRuleId());
				model.setStockTimeList(addStockModel.getStockTimeStrList());
				stockList = queryStockByDateListEngine.queryStockByDateList(model);
				//过滤库存规则对应时间
				if (!CommonUtils.checkObjectIsNull(stockList)) {
					Iterator<Integer> itera = stockTimeIntList.iterator();
					while (itera.hasNext()) {
						Integer stockTime = itera.next();
						for (Stock stock : stockList) {
							if (stock.getStockTime().intValue() == stockTime.intValue()) {
								itera.remove();
								break;
							}
						}
					}
				}
				//添加库存
				count += createDailyStockEngine.createDailyStock(map.get(addStockModel.getStockRuleId()),
						stockTimeIntList);
			}

		}

		if (logger.isDebugEnabled()) {
			logger.debug(" create daily stock success , request:{},context:{},response:{}.", rules, context, count);
		}
		return count;
	}

	/** 
	 * @see com.pzj.stock.service.stock.StockService#stockBatchLock(com.pzj.core.stock.model.StockBatchLockModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Boolean> stockBatchLock(StockBatchLockModel stockBatchLockModel, ServiceContext serviceContext) {
		Result<Boolean> result = new Result<Boolean>(Boolean.FALSE);
		ParamModel paramModel = this.stockBatchLockValidator.convert(stockBatchLockModel, serviceContext);
		if (!paramModel.paramIsOk()) {
			logger.error("operate stockBatchLockModel fail, Illegal params. request: {}, context: {}.",
					stockBatchLockModel, serviceContext);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("operate stockBatchLockModel . request:{}, context:{}.", stockBatchLockModel, serviceContext);
		}

		try {
			stockBatchLockEngine.batchLockAndReleaseStock(stockBatchLockModel);
			result.setData(Boolean.TRUE);
		} catch (Throwable e) {
			logger.error("operate stockBatchLockModel fail. request: " + stockBatchLockModel + ", context: "
					+ serviceContext, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("operate stockBatchLockModel success. result: {} .", JSONConverter.toJson(result));
		}

		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.StockService#rollbackOccupyStock(com.pzj.core.stock.model.StockBatchLockModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Boolean> rollbackOccupyStock(OccupyStockRequestModel orderStockModel, ServiceContext serviceContext) {
		Result<Boolean> result = new Result<Boolean>(Boolean.FALSE);
		ParamModel paramModel = rollbackOccupyStockValidator.convert(orderStockModel, serviceContext);
		if (!paramModel.paramIsOk()) {
			logger.error("rollback occupy stock fail, Illegal params. request: {}, context: {}.", orderStockModel,
					serviceContext);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("rollback occupy stock. request: {}, context: {}.", orderStockModel, serviceContext);
		}

		try {
			rollbackOccupyStockEngine.rollbackOccupyStock(orderStockModel);
			result.setData(Boolean.TRUE);
		} catch (Throwable e) {
			logger.error("rollback occupy stock fail. request: " + orderStockModel + ", context: " + serviceContext, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("rollback occupy stock success. result: {}.", JSONConverter.toJson(result));
		}

		return result;
	}

	@Override
	public Result<Boolean> supplierManualLockStock(SupplierManualLockModel manualLockModel,
			ServiceContext serviceContext) {
		Result<Boolean> result = new Result<Boolean>(Boolean.FALSE);
		ParamModel paramModel = this.supplierManualLockStockValidator.convert(manualLockModel, serviceContext);
		if (!paramModel.paramIsOk()) {
			logger.error("supplier manual lock stock fail, Illegal params. request: {}, context: {}.", manualLockModel,
					serviceContext);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("supplier manual lock stock . request:{}, context:{}.", manualLockModel, serviceContext);
		}

		try {
			supplierManualLockStockEngine.supplierManualLockStock(manualLockModel);
			result.setData(Boolean.TRUE);
		} catch (Throwable e) {
			logger.error("supplier manual lock stock fail. request: " + manualLockModel + ", context: "
					+ serviceContext, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("supplier manual lock stock success. result: {}.", JSONConverter.toJson(result));
		}

		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.StockService#supplierManualUnlockStock(com.pzj.core.stock.model.SupplierManualUnlockModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Boolean> supplierManualUnlockStock(SupplierManualUnlockModel manualUnlockModel,
			ServiceContext serviceContext) {
		Result<Boolean> result = new Result<Boolean>(Boolean.FALSE);
		ParamModel paramModel = this.supplierManualUnLockStockValidator.convert(manualUnlockModel, serviceContext);
		if (!paramModel.paramIsOk()) {
			logger.error("supplier manual unlock stock fail, Illegal params. request: {}, context: {}.",
					manualUnlockModel, serviceContext);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("supplier manual unlock stock . request:{}, context:{}.", manualUnlockModel, serviceContext);
		}

		try {
			supplierManualUnLockStockEngine.supplierManualUnlockStock(manualUnlockModel);
			result.setData(Boolean.TRUE);
		} catch (Throwable e) {
			logger.error("supplier manual unlock stock fail. request: " + manualUnlockModel + ", context: "
					+ serviceContext, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("supplier manual unlock stock success. request: {}, context: {}.", manualUnlockModel,
					serviceContext);
		}

		return result;
	}

	@Override
	public Result<Boolean> updateAreaStock(UpdateAreaStockReqModel reqModel, ServiceContext serviceContext) {
		Result<Boolean> result = new Result<Boolean>(Boolean.FALSE);
		ParamModel paramModel = checkScreeningAreaParam(reqModel);
		if (!paramModel.paramIsOk()) {
			logger.error("update area stock fail, Illegal params. request: {}, context: {}.",
					JSONConverter.toJson(reqModel), serviceContext);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("update area stock request param:{}", JSONConverter.toJson(reqModel));
		}

		try {
			QueryStockByShowReqModel stockShowModel = new QueryStockByShowReqModel();
			stockShowModel.setScreeingId(reqModel.getScreeingId());
			stockShowModel.setAreaId(reqModel.getAreaId());
			stockShowModel.setShowTime(reqModel.getShowTime());
			Result<StockModel> stockResult = stockQueryService.queryStockByShow(stockShowModel, serviceContext);
			if (!stockResult.isOk()) {
				result.setErrorCode(stockResult.getErrorCode());
				result.setErrorMsg(stockResult.getErrorMsg());
				return result;
			}
			if (stockResult.getData() != null) {
				Long stockId = stockResult.getData().getId();
				int updnum = updateAreaStockEngine.updateAreaStock(stockId, reqModel.getNewestStockNum(),
						reqModel.getAreaId());
				if (logger.isDebugEnabled()) {
					logger.debug("update area stock num :{}", updnum);
				}
				result.setData(Boolean.TRUE);
			}
		} catch (Throwable e) {
			logger.error("update area stock fail. request:{}, context: {}", JSONConverter.toJson(reqModel),
					serviceContext, e);
			CommonUtils.convertException(e, result);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("update area stock result:{}", JSONConverter.toJson(result));
		}
		return result;
	}

	private ParamModel checkScreeningAreaParam(UpdateAreaStockReqModel reqModel) {
		ParamModel paramModel = ParamModel.getInstance();
		if (reqModel == null) {
			paramModel.setErrorModel("修改区域场次指定时间库存传入UpdateAreaStockReqModel对象为空!");
			return paramModel;
		}
		if (CommonUtils.checkLongIsNull(reqModel.getScreeingId(), Boolean.TRUE)) {
			paramModel.setErrorModel("修改区域场次指定时间传入screeingId场次id为空!");
			return paramModel;
		}
		if (CommonUtils.checkLongIsNull(reqModel.getAreaId(), Boolean.TRUE)) {
			paramModel.setErrorModel("修改区域场次指定时间传入areaId区域id为空!");
			return paramModel;
		}
		if (CommonUtils.checkObjectIsNull(reqModel.getShowTime())) {
			paramModel.setErrorModel("修改区域场次指定时间传入showTime演出时间为空!");
			return paramModel;
		}
		if (CommonUtils.checkIntegerIsNull(reqModel.getNewestStockNum(), Boolean.TRUE)) {
			paramModel.setErrorModel("修改区域场次指定时间传入newestStockNum新的库存总数为空!");
			return paramModel;
		}

		return paramModel;
	}

}
