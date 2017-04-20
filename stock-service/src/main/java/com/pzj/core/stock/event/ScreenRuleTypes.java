/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.event;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pzj.core.stock.entity.StockRule;

/**
 * 按类型筛选类型的库存规则
 * 
 * @author dongchunfu
 * @version $Id: ScreenSingleRuleIs.java, v 0.1 2016年8月23日 下午3:00:06 dongchunfu Exp $
 */
@Component(value = "screenRuleTypes")
public class ScreenRuleTypes {

    /**
     * 根据指定的库存规则类型 type  @see StockRuleTypeEnum 筛选库存规则
     * 
     * @param rules     待筛选库存规则集合
     * @param ruleType  指定筛选的类型
     * @return          库存规则集合
     */
    public List<StockRule> screening(List<StockRule> rules, int ruleType) {

        if (null == rules || rules.size() == 0) {
            return null;
        }
        List<StockRule> result = new ArrayList<StockRule>();
        for (StockRule stockRule : rules) {
            if (stockRule.getType() == ruleType) {
                result.add(stockRule);
            }
        }
        return result.size() == 0 ? null : result;
    }
}
