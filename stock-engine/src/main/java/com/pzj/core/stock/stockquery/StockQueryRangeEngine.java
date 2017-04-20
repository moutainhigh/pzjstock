/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.stockquery;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.model.StockQueryRequestModel;
import com.pzj.core.stock.read.StockReadMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 根据ID查询库存引擎
 * 
 * @author dongchunfu
 * @version $Id: StockQueryByIdEngine.java, v 0.1 2016年8月10日 下午4:26:27 dongchunfu Exp $
 */
@Component(value = "stockQueryRangeEngine")
public class StockQueryRangeEngine {
    @Autowired
    private StockReadMapper stockReadMapper;

    public ArrayList<Stock> queryRangeStocks(StockQueryRequestModel model, ServiceContext context) {
        Stock stock = produceStock(model);
        return stockReadMapper.selectStockListByParam(stock);
    }

    private Stock produceStock(StockQueryRequestModel model) {
        Stock stock = new Stock();

        stock.setRuleId(model.getRuleId());
        stock.setBeginTime(model.getBeginStockTime());
        stock.setEndTime(model.getEndStockTime());

        return stock;
    }
}
