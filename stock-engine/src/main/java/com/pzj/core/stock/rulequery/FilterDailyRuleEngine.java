/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.rulequery;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.stock.read.StockRuleReadMapper;

/**
 * 筛选日类型库存规则
 * 
 * @author dongchunfu
 * @version $Id: FilterDailyRuleEngine.java, v 0.1 2016年8月15日 下午2:02:24 dongchunfu Exp $
 */
@Component(value = "filterDailyRuleEngine")
public class FilterDailyRuleEngine {

    private static final Logger logger = LoggerFactory.getLogger(FilterDailyRuleEngine.class);

    @Resource
    private StockRuleReadMapper stockRuleReadMapper;

    public ArrayList<Long> filterDailyRule(List<Long> ruleIds) {
        if (null == ruleIds || ruleIds.size() == 0) {
            logger.warn("illegal param ruleIds:{}.", ruleIds);
            return null;
        }
        ArrayList<Long> dailyRuleIds = stockRuleReadMapper.filterDailyRule(ruleIds);

        return null == dailyRuleIds || dailyRuleIds.size() == 0 ? null : dailyRuleIds;
    }
}
