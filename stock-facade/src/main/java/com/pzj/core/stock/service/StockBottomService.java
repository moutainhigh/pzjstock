/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.service;

import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

public interface StockBottomService {

    /**
     * 同步产品 与库存规则的关系表
     * 
     * @return 新增数量
     */
    Result<Integer> syncSkuAndRuleRel(ServiceContext context);

    /**
     * 自动创建日库存记录
     * 
     * @param context
     * @return
     */
    Result<Integer> autoCreateDailyStock(ServiceContext context);
}
