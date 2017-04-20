/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.stockupdate;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.ParameterErrorException;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.read.StockRuleReadMapper;
import com.pzj.core.stock.write.StockWriteMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 自动创建日库存记录
 * 
 * @author dongchunfu
 * @version $Id: AutoCreateDailyStockEngine.java, v 0.1 2016年8月16日 下午4:01:34 dongchunfu Exp $
 */
@Component(value = "autoCreateDailyStockEngine")
public class AutoCreateDailyStockEngine {

    private static final Logger logger = LoggerFactory.getLogger(AutoCreateDailyStockEngine.class);

    @Autowired
    private StockWriteMapper    stockWriteMapper;
    @Autowired
    private StockRuleReadMapper stockRuleReadMapper;

    public Integer autoCreateDailyStock(List<Long> ruleIds, Integer stockTime, ServiceContext context) {

        ArrayList<StockRule> rules = stockRuleReadMapper.queryStockRuleByIds(ruleIds);

        if (ruleIds.size() != rules.size()) {
            logger.error("illegal param,ruleIds,no so much rule mapped,context:{}.", context);
            throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
        }

        List<Stock> convertList = convertList(rules, stockTime);

        stockWriteMapper.insertStockBatch(convertList);

        return convertList.size();
    }

    private List<Stock> convertList(ArrayList<StockRule> rules, Integer stockTime) {

        List<Stock> stocks = new ArrayList<Stock>(rules.size());
        for (StockRule rule : rules) {
            Stock stockNew = convertParam(rule, stockTime);
            stocks.add(stockNew);
        }
        return stocks;

    }

    //参数转换
    private Stock convertParam(StockRule rule, Integer stockTime) {
        Stock stock = new Stock();
        stock.setRuleId(rule.getId());
        stock.setState(rule.getState());
        stock.setTotalNum(rule.getUpperLimit());
        stock.setUsedNum(0);
        stock.setRemainNum(rule.getUpperLimit());
        stock.setStockTime(stockTime);
        return stock;
    }
}
