/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.stockquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.read.StockReadMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 根据ID查询库存引擎
 * 
 * @author dongchunfu
 * @version $Id: StockQueryByIdEngine.java, v 0.1 2016年8月10日 下午4:26:27 dongchunfu Exp $
 */
@Component(value = "stockQueryByIdEngine")
public class StockQueryByIdEngine {
    @Autowired
    private StockReadMapper stockReadMapper;

    public Stock selectStockById(Long stockId, ServiceContext context) {
        return stockReadMapper.selectStockById(stockId);

    }
}
