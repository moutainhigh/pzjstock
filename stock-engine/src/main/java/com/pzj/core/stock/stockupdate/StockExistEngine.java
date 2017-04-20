/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.stockupdate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.read.StockReadMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 判断单一库存记录是否已创建
 * 
 * @author dongchunfu
 * @version $Id: StockExistEngine.java, v 0.1 2016年8月22日 上午11:34:53 dongchunfu Exp $
 */
@Component("stockExistEngine")
public class StockExistEngine {

    @Autowired
    private StockReadMapper stockReadMapper;

    /**
     * 过滤出未创建库存记录的库存规则
     * 
     * @param rules     待过滤库存规则集合
     * @param context   服务上下文
     * @return          未创建库存记录的库存规则
     */
    public List<StockRule> notExistStockRule(List<StockRule> rules, ServiceContext context) {

        if (null == rules || rules.size() == 0) {
            return null;
        }

        List<Long> allIds = getIds(rules);

        //已存在库存记录的规则id
        ArrayList<Long> existIds = stockReadMapper.selectIdsInRuleIdsAndStockTime(allIds, null);

        //移除已创建库存记录的库存规则
        if (null != existIds && existIds.size() != 0) {
            allIds.removeAll(existIds);
        }

        //结果封装，返回未创建库存记录的库存规则
        return screenNotExistRule(rules, allIds);

    }

    /** 过滤所有的规则ID 集合 */
    private List<Long> getIds(List<StockRule> rules) {
        List<Long> ids = new ArrayList<Long>();
        for (StockRule rule : rules) {
            ids.add(rule.getId());
        }
        return ids;
    }

    /**
     * @param rules 待过滤的库存规则
     * @param notExistIds 不存在的库存规则ID集合
     * @return 不存记录的库存规则
     */
    private List<StockRule> screenNotExistRule(List<StockRule> rules, List<Long> notExistIds) {

        if (null == notExistIds || notExistIds.size() == 0) {
            return null;
        }
        if (null == rules || rules.size() == 0) {
            return null;
        }
        List<StockRule> result = new ArrayList<StockRule>();
        for (StockRule rule : rules) {
            if (notExistIds.contains(rule.getId())) {
                result.add(rule);
            }
        }

        return result.size() == 0 ? null : result;
    }
}
