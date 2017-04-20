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
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.converter.SkuRuleRelConverter;
import com.pzj.core.stock.entity.SkuRuleRel;
import com.pzj.core.stock.event.CallSkuSrvFilterRuleIdsEvent;
import com.pzj.core.stock.event.FilterStockRuleDateTimer;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.exception.errcode.StockRuleExceptionCode;
import com.pzj.core.stock.model.AddStockModel;
import com.pzj.core.stock.model.result.SkuStockRelationResult;
import com.pzj.core.stock.rulequery.FilterDailyRuleEngine;
import com.pzj.core.stock.rulequery.GetAllSkuRelsEngine;
import com.pzj.core.stock.ruleupdate.ClearSkuAndRuleRelEngine;
import com.pzj.core.stock.ruleupdate.SyncSkuAndRuleRelEngine;
import com.pzj.core.stock.service.ISkuStockService;
import com.pzj.core.stock.service.StockBottomService;
import com.pzj.core.stock.stockquery.StockQureyInIdsAndStockTimeEngine;
import com.pzj.core.stock.stockupdate.AutoCreateDailyStockEngine;
import com.pzj.core.util.DateUtil;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author dongchunfu
 * @version $Id: StockBottomServiceImpl.java, v 0.1 2016年7月29日 下午3:31:35 dongchunfu Exp $
 */
@Service(value = "stockBottomService")
public class StockBottomServiceImpl implements StockBottomService {
	private static final Logger logger = LoggerFactory.getLogger(StockBottomServiceImpl.class);

	@Resource(name = "skuStockService")
	private ISkuStockService skuStockService;

	@Resource(name = "filterDailyRuleEngine")
	private FilterDailyRuleEngine filterDailyRuleEngine;
	@Resource(name = "stockQureyInIdsAndStockTimeEngine")
	private StockQureyInIdsAndStockTimeEngine stockQureyInIdsAndStockTimeEngine;
	@Resource(name = "clearSkuAndRuleRelEngine")
	private ClearSkuAndRuleRelEngine clearSkuAndRuleRelEngine;
	@Resource(name = "syncSkuAndRuleRelEngine")
	private SyncSkuAndRuleRelEngine syncSkuAndRuleRelEngine;
	@Resource(name = "getAllSkuRelsEngine")
	private GetAllSkuRelsEngine getAllSkuRelsEngine;
	@Resource(name = "autoCreateDailyStockEngine")
	private AutoCreateDailyStockEngine autoCreateDailyStockEngine;

	@Resource(name = "callSkuSrvFilterRuleIdsEvent")
	private CallSkuSrvFilterRuleIdsEvent callSkuSrvFilterRuleIdsEvent;
	@Resource(name = "filterStockRuleTimer")
	private FilterStockRuleDateTimer filterStockRuleTimer;

	@Resource(name = "skuRuleRelConverter")
	private SkuRuleRelConverter skuRuleRelConverter;

	@Resource(name = "dateUtil")
	private DateUtil dateUtil;

	/** 
	 * @see com.pzj.core.stock.service.impl.StockBottomService#syncSkuAndRuleRel(java.util.List, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Integer> syncSkuAndRuleRel(ServiceContext context) {
		Result<Integer> result = new Result<Integer>();
		//1.参数验证
		if (null == context) {
			logger.error(" syncSkuAndRuleRel illegal param ,context:{}.", context);
			CommonUtils.setParamErr(result);
			return result;
			//            throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		//2.调用产品服务获取关系数据
		Result<ArrayList<SkuStockRelationResult>> skuStockRelResult;
		try {
			skuStockRelResult = skuStockService.getAllValidSkuRondaRelation();
		} catch (Throwable t) {
			logger.error(" call sku service fail ,context:{}.", context, t);
			CommonUtils.setResultErr(StockExceptionCode.INVOKE_OTHER_SERVICE_ERR_CODE,
					StockExceptionCode.INVOKE_OTHER_SERVICE_ERR_MSG, result);
			return result;
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, t);
		}

		if (skuStockRelResult.isOk()) {
			ArrayList<SkuStockRelationResult> data = skuStockRelResult.getData();
			if (CommonUtils.checkCollectionIsNull(data)) {
				if (logger.isDebugEnabled()) {
					logger.debug("syncSkuAndRuleRel query stock product relation is null.");
				}
				return result;
			}

			ArrayList<SkuRuleRel> skuRuleRels = skuRuleRelConverter.convert(data, context);

			try {
				//3.事先清空关系表
				if (logger.isDebugEnabled()) {
					logger.debug("clearing sku and stock rule relation,context:{}.", context);
				}

				int count = clearSkuAndRuleRelEngine.clearSkuAndRuleRel(context);

				if (logger.isDebugEnabled()) {
					logger.debug("clear sku and stock rule relation success,response:{},context:{}.", count, context);
				}

				//4.控制事务大小，每次执行200条数据的插入操作。
				if (logger.isDebugEnabled()) {
					logger.debug("syncing sku and rule relation ,context:{}.", context);
				}

				int size = skuRuleRels.size();

				for (int i = 0; i < size; i += 200) {
					List<SkuRuleRel> subList = skuRuleRels.subList(i, i + 200 >= size ? size : i + 200);
					syncSkuAndRuleRelEngine.syncSkuAndRuleRel(subList, context);
				}

			} catch (Throwable e) {
				logger.error("synchronize sku stock rule relation failed ,context:{}.", context, e);
				CommonUtils.convertException(e, result);

				//				throw t instanceof ServiceException ? (ServiceException) t : new StockException(
				//						StockExceptionCode.STOCK_ERR_MSG, t);

			}
		} else {
			logger.error("call sku service error,message:{}.", skuStockRelResult.getErrorMsg());
			CommonUtils.setResultErr(StockRuleExceptionCode.GET_STOCK_RULE_REL_PRODUCT_ERR_CODE,
					StockRuleExceptionCode.GET_STOCK_RULE_REL_PRODUCT_ERR_MSG, result);

			//            return new Result<Integer>(CallSkuErrorCode.getInstance());
			//			throw new GainProductStockRuleRelException(StockRuleExceptionCode.GET_STOCK_RULE_REL_PRODUCT_ERR_MSG);

		}

		if (logger.isDebugEnabled()) {
			logger.debug("sync sku and rule relation success ,result:{},context:{}.", result, context);
		}

		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.StockBottomService#autoCreateDailyStock(com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Integer> autoCreateDailyStock(ServiceContext context) {
		Result<Integer> result = new Result<Integer>();
		if (null == context) {//参数验证
			logger.error("illegal param,context:{}", context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		try {
			//1.获取同步后所有库存规则ID集合
			List<Long> allSkuRels = getAllSkuRelsEngine.getAllSkuRels(context);

			int size = null == allSkuRels ? 0 : allSkuRels.size();
			int count = 0;

			//控制批量操作数量
			for (int i = 0; i < size; i += 200) {

				int startIndex = i;
				int endIndex = (i + 200) <= size ? i + 200 : size;

				List<Long> subList = allSkuRels.subList(startIndex, endIndex);

				//2.过滤为日类型可用的库存规则ID
				ArrayList<Long> dailyRuleIds = filterDailyRuleEngine.filterDailyRule(subList);

				//3、过滤产品可用日期小于三个月的库存规则ID
				List<AddStockModel> addStockModelList = filterStockRuleTimer.filterStockRuleDate(dailyRuleIds);

				if (!Check.NuNCollections(addStockModelList)) {
					//合并库存规则对应时间是同一天的数据
					Integer stockTime = null;
					List<Long> ruleIds = null;
					Map<Integer, List<Long>> map = new HashMap<Integer, List<Long>>();
					for (AddStockModel addStockModel : addStockModelList) {
						stockTime = addStockModel.getStockTimeIntList().get(0);
						if (CommonUtils.checkIntegerIsNull(stockTime, true)) {
							continue;
						}
						if (map.containsKey(stockTime)) {
							map.get(stockTime).add(addStockModel.getStockRuleId());
						} else {
							ruleIds = new ArrayList<Long>();
							ruleIds.add(addStockModel.getStockRuleId());
							map.put(stockTime, ruleIds);
						}
					}

					Iterator<Entry<Integer, List<Long>>> itera = map.entrySet().iterator();
					while (itera.hasNext()) {
						Entry<Integer, List<Long>> entry = itera.next();
						stockTime = entry.getKey();
						ruleIds = entry.getValue();
						//过滤数据库中已经存在的库存
						ArrayList<Long> ruleIdsDB = stockQureyInIdsAndStockTimeEngine.queryRuleIdsByDate(ruleIds,
								stockTime, context);
						if (null != ruleIdsDB && !ruleIdsDB.isEmpty()) {
							if (logger.isDebugEnabled()) {
								logger.debug("move exit stock recored rule id.");
							}
							ruleIds.removeAll(ruleIdsDB);
						}
						//添加库存数据
						if (null != ruleIds && !ruleIds.isEmpty()) {
							if (logger.isDebugEnabled()) {
								logger.debug(" timer add stock recored count:{}.", ruleIds.size());
							}
							count += ruleIds.size();
							autoCreateDailyStockEngine.autoCreateDailyStock(ruleIds, stockTime, context);
						}
					}

				}
				if (logger.isDebugEnabled()) {
					logger.debug(" timer add stock recored total count:{}.", count);
				}
			}

		} catch (Throwable e) {
			logger.error("auto create stock failed ,context:{}.", context, e);
			CommonUtils.convertException(e, result);

			//			throw t instanceof ServiceException ? (ServiceException) t : new StockException(
			//					StockExceptionCode.STOCK_ERR_MSG, t);

		}
		if (logger.isDebugEnabled()) {
			logger.debug(" timer add stock recored result:{}.", JSONConverter.toJson(result));
		}
		return result;
	}
}
