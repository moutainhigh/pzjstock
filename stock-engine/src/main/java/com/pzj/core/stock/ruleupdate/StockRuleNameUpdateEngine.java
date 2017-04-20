/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.ruleupdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.exception.rule.StockRuleNameException;
import com.pzj.core.stock.write.StockRuleWriteMapper;

/**
 * 
 * @author mada
 * @version $Id: StockRuleNameUpdateEngine.java, v 0.1 2016年10月26日 下午3:01:51 mada Exp $
 */
@Component(value = "stockRuleNameUpdateEngine")
@Transactional(value = "stock.transactionManager")
public class StockRuleNameUpdateEngine {

    private static final Logger  logger = LoggerFactory.getLogger(StockRuleNameUpdateEngine.class);

    @Autowired
    private StockRuleWriteMapper stockRuleWriteMapper;

    /**
     * 
     * 更新库存规则名称
     * @param id
     * @param name
     * @return
     */
    public int updateStockRuleName(long id, String name) {
        logger.error("更新库存规则名称");
        try {
            return stockRuleWriteMapper.updateStockRuleName(id, name);
        } catch (Exception e) {
            logger.error(" update StockRuleName error ", e);
            throw new StockRuleNameException(StockExceptionCode.STOCK_ERR_MSG, e);
        }
    }
}
