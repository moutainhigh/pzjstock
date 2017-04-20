/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.stockquery;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.enums.StockRuleTypeEnum;
import com.pzj.core.stock.exception.errcode.StockRuleExceptionCode;
import com.pzj.core.stock.exception.rule.NotFoundStockRuleException;
import com.pzj.core.stock.model.StockQueryRequestModel;
import com.pzj.core.stock.model.StockRulesDateReqModel;
import com.pzj.core.stock.read.StockReadMapper;
import com.pzj.core.stock.read.StockRuleReadMapper;

/**
 * 
 * @author Administrator
 * @version $Id: QueryStockByRuleEngine.java, v 0.1 2016年8月29日 下午3:35:10 Administrator Exp $
 */
@Component("queryStockByRuleEngine")
public class QueryStockByRuleEngine {
	@Resource(name = "stockRuleReadMapper")
	private StockRuleReadMapper stockRuleReadMapper;

	@Resource(name = "stockReadMapper")
	private StockReadMapper stockReadMapper;

	public Stock queryStockByRule(StockQueryRequestModel model) {
		//检查是否查到可用的库存规则
		StockRule stockRule = stockRuleReadMapper.queryRuleValidateStockById(model.getRuleId());
		checkStockRule(stockRule);

		Integer stockTime = 0;
		//每日库存检查日期
		if (StockRuleTypeEnum.DAILY.getRuleType() == stockRule.getType()) {
			stockTime = Integer.parseInt(CommonUtils.checkStockTime(model.getStockTime()));
		}

		Stock stock = stockReadMapper.queryStockByRuleAndTime(model.getRuleId(), stockTime);
		if (!CommonUtils.checkObjectIsNull(stock)) {
			stock.setType(stockRule.getType());
		}
		return stock;
	}

	public List<Stock> queryStockByRules(StockRulesDateReqModel reqModel) {
		List<StockRule> stockRules = stockRuleReadMapper.queryStockRuleByIds(reqModel.getRuleIds());
		checkStockRules(stockRules);

		Integer stockTime = 0;
		if (!CommonUtils.checkObjectIsNull(reqModel.getShowTime())) {
			stockTime = CommonUtils.getStockDateInteger(reqModel.getShowTime());
		}

		List<Stock> stocks = stockReadMapper.querySupplierRealTimeStock(reqModel.getRuleIds(), stockTime);

		return stocks;
	}

	private void checkStockRules(List<StockRule> stockRules) {
		if (CommonUtils.checkCollectionIsNull(stockRules)) {
			throw new NotFoundStockRuleException(StockRuleExceptionCode.NOT_FOUND_STOCK_RULE_ERR_MSG);
		}
		//		for (StockRule stockRule : stockRules) {
		//			checkStockRule(stockRule);
		//		}
	}

	/**
	 * 检查库存规则是否可用
	 * @param stockRule
	 */
	private void checkStockRule(StockRule stockRule) {
		if (null == stockRule) {
			throw new NotFoundStockRuleException(StockRuleExceptionCode.NOT_FOUND_STOCK_RULE_ERR_MSG);
		}
		//        if (stockRule.getState() != StockRuleStateEnum.AVAILABLE.getState()) {
		//            throw new StockRuleStateException(StockRuleExceptionCode.STOCK_RULE_STATE_ERR_MSG);
		//        }
	}
}
