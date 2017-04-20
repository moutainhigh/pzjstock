/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.ruleupdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.core.stock.write.StockRuleWriteMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * 
 * @author dongchunfu
 * @version $Id: DeleteStockRuleEngine.java, v 0.1 2016年8月19日 下午5:29:51 dongchunfu Exp $
 */
@Component(value = "deleteStockRuleEngine")
@Transactional(value = "stock.transactionManager")
public class DeleteStockRuleEngine {
    @Autowired
    private StockRuleWriteMapper stockRuleWriteMapper;

    public int fakeDeletStockRule(StockRuleModel model, ServiceContext context) {
        return stockRuleWriteMapper.fakeDeleteStockRule(model.getId());

    }
}
