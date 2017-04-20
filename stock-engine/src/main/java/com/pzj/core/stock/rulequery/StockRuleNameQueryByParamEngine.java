/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.rulequery;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.stock.exception.rule.StockRuleNameException;
import com.pzj.core.stock.read.StockRuleReadMapper;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author Administrator
 * @version $Id: StockRuleNameQueryByParamEngine.java, v 0.1 2016年10月26日 下午3:43:57 Administrator Exp $
 */
@Component(value = "stockRuleNameQueryByParamEngine")
public class StockRuleNameQueryByParamEngine {

    private static final Logger logger = LoggerFactory.getLogger(StockRuleNameQueryByParamEngine.class);

    @Resource
    private StockRuleReadMapper stockRuleReadMapper;

    /**
     * 
     * 检查库存规则名称中的skuid是否被修改
     * @param id
     * @param name
     * @return
     */
    public void queryStockRuleNameById(long id, String name) {
        String skuId = stockRuleReadMapper.queryStockRuleNameById(id);
        if (Check.NuNStrStrict(skuId)) {
            throw new StockRuleNameException("id没有对应的记录，或对应记录中name值为空");
        }
        if (!name.substring(name.indexOf("-") + 1).equals(skuId)) {
            throw new StockRuleNameException("name不允许改动skuId部分");
        }
    }

    /**
     * 
     * 检查库存规则名称在库存规则表中是否已经存在
     * @param id
     * @param name
     * @return
     */
    public void queryStockRuleNameRepeat(long id, String name) {
        logger.error("检查库存规则名称在库存规则表中是否已经存在");
        int repeat = stockRuleReadMapper.queryStockRuleNameRepeat(id, name);
        if (repeat > 0) {
            throw new StockRuleNameException("name在库存规则表中已经存在");
        }
    }

}
