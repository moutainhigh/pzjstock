/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.ruleupdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.core.stock.write.StockRuleWriteMapper;
import com.pzj.core.stock.write.StockWriteMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author dongchunfu
 * @version $Id: StockRuleUpdateStateEngine.java, v 0.1 2016年8月5日 下午4:54:54 dongchunfu Exp $
 */
@Component(value = "stockRuleUpdateYetBindEngine")
@Transactional(value = "stock.transactionManager")
public class StockRuleUpdateYetBindEngine {

    @Autowired
    private StockRuleWriteMapper stockRuleWriteMapper;
    @Autowired
    private StockWriteMapper     stockWriteMapper;

    public int updateStockRuleYetBind(StockRuleModel model, ServiceContext context) {
        //仅获取可更改的参数

        StockRule rule = convertParam(model);

        //        int count = stockRuleWriteMapper.updateStockRuleById(rule);
        //
        //        if (count == 0) {
        //            throw new NotFoundStockRuleException(StockRuleExceptionCode.CATEGORY_NOTEXIST_ERR_MSG);
        //        }

        //        if (!CommonUtils.checkIntegerIsNull(model.getUpperLimit(), true)) {
        //            count += stockWriteMapper.updateStockUpperLimitByStockRuleId(model.getId(), model.getUpperLimit());
        //        }
        return stockRuleWriteMapper.updateStockRuleById(rule);
    }

    private StockRule convertParam(StockRuleModel model) {
        StockRule rule = new StockRule();

        rule.setId(model.getId());
        rule.setName(model.getName());
        //        rule.setUpperLimit(model.getUpperLimit());

        return rule;
    }
}
