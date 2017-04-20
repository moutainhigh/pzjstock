/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.stockupdate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.enums.StockStateEnum;
import com.pzj.core.stock.write.StockWriteMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 创建单一库存记录操作
 * 
 * @author dongchunfu
 * @version $Id: CreateSingleStockEngine.java, v 0.1 2016年8月10日 下午2:34:41 dongchunfu Exp $
 */
@Component(value = "createSingleStockEngine")
public class CreateSingleStockEngine {

    @Autowired
    private StockWriteMapper stockWriteMapper;

    public int createSingleStock(List<StockRule> rules, ServiceContext context) {
        if (null == rules || rules.size() == 0) {
            return 0;
        }
        stockWriteMapper.insertStockBatch(convertParam(rules));
        return rules.size();
    }

    //参数转换
    private List<Stock> convertParam(List<StockRule> rules) {

        List<Stock> stocks = new ArrayList<Stock>(rules.size());
        for (StockRule rule : rules) {
            Stock stock = new Stock();
            stock.setRuleId(rule.getId());
            stock.setState(StockStateEnum.AVAILABLE.getState());
            stock.setTotalNum(rule.getUpperLimit());
            stock.setUsedNum(0);
            stock.setStockTime(0);
            stock.setRemainNum(rule.getUpperLimit());
            stocks.add(stock);
        }
        return stocks;
    }
}
