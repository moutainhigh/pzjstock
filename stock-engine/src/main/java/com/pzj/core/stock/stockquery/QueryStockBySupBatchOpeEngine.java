/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.stockquery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.enums.StockStateEnum;
import com.pzj.core.stock.exception.errcode.StockRuleExceptionCode;
import com.pzj.core.stock.exception.rule.NotFoundStockRuleException;
import com.pzj.core.stock.model.StockDateListQueryModel;
import com.pzj.core.stock.read.StockReadMapper;
import com.pzj.core.stock.read.StockRuleReadMapper;

/**
 * 
 * @author Administrator
 * @version $Id: QueryStockBySupBatchOpeEngine.java, v 0.1 2016年9月19日 上午11:05:22 Administrator Exp $
 */
@Component("queryStockBySupBatchOpeEngine")
public class QueryStockBySupBatchOpeEngine {
    @Resource(name = "stockRuleReadMapper")
    private StockRuleReadMapper stockRuleReadMapper;
    @Autowired
    private StockReadMapper     stockReadMapper;

    public List<Stock> querySupBatchStock(StockDateListQueryModel model) {
        Long ruleId = model.getStockRuleId();
        Set<Integer> stockTimeSet = model.getAvaiStockTimeSet();
        if (CommonUtils.checkObjectIsNull(stockTimeSet)) {
            return null;
        }

        //1、获取库存规则对象
        StockRule stockRule = stockRuleReadMapper.selectRuleById(ruleId);
        checkStockRule(stockRule);

        //2、获取库中存在的库存集合
        List<Stock> stockList = stockReadMapper.queryStockByDateList(ruleId, stockTimeSet);
        List<Integer> hisStockTimeList = new ArrayList<Integer>();
        Map<Integer, Integer> noStockMap = new HashMap<Integer, Integer>();
        if (CommonUtils.checkCollectionIsNull(stockList)) {
            stockList = new ArrayList<Stock>();
        } else {
            Iterator<Stock> stockItera = stockList.iterator();
            Stock stock = null;
            while (stockItera.hasNext()) {
                stock = stockItera.next();
                if (CommonUtils.checkIntegerIsNull(stock.getRemainNum(), true)) {
                    stockItera.remove();
                    noStockMap.put(stock.getStockTime(), stock.getStockTime());
                    continue;
                }
                hisStockTimeList.add(stock.getStockTime());
            }
        }

        //3、初始化库存对象集合
        Iterator<Integer> itera = stockTimeSet.iterator();
        Integer stockTime = null;
        while (itera.hasNext()) {
            stockTime = itera.next();
            if (!hisStockTimeList.contains(stockTime) && !noStockMap.containsKey(stockTime)) {
                stockList.add(initStock(stockTime, stockRule));
            }
        }
        return stockList;
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

    /**
     * 初始化库存对象
     * @param stockTime
     * @param stockRule
     * @return Stock
     */
    private Stock initStock(Integer stockTime, StockRule stockRule) {
        Stock stock = new Stock();
        stock.setRuleId(stockRule.getId());
        stock.setStockTime(stockTime);
        stock.setState(StockStateEnum.AVAILABLE.getState());
        stock.setTotalNum(stockRule.getUpperLimit());
        stock.setRemainNum(stockRule.getUpperLimit());
        stock.setUsedNum(0);
        return stock;
    }
}
