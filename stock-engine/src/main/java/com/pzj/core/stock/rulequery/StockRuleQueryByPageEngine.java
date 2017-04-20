/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.rulequery;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.enums.StockRuleStateEnum;
import com.pzj.core.stock.model.StockRuleQueryRequestModel;
import com.pzj.core.stock.read.StockRuleReadMapper;
import com.pzj.framework.entity.QueryResult;

/**
 * 分页查询库存规则引擎
 * @author dongchunfu
 * @version $Id: StockRuleQueryByPage.java, v 0.1 2016年8月4日 下午2:13:58 dongchunfu Exp $
 */
@Component(value = "stockRuleQueryByPageEngine")
public class StockRuleQueryByPageEngine {

    private static final Logger logger = LoggerFactory.getLogger(StockRuleQueryByPageEngine.class);

    @Resource
    private StockRuleReadMapper stockRuleReadMapper;

    /**分页查询*/
    public QueryResult<StockRule> queryRuleByPage(StockRuleQueryRequestModel request) {
        StockRule stockRule = convertRequest(request);
        int count = stockRuleReadMapper.countForPage(stockRule);
        if (logger.isDebugEnabled()) {
            logger.debug("request: {},amount: {}.", request, count);
        }
        QueryResult<StockRule> qr = new QueryResult<StockRule>(request.getCurrentPage(), request.getPageSize());
        if (count == 0) {
            return qr;
        }
        qr.setTotal(count);
        List<StockRule> result = stockRuleReadMapper.selectRuleByPage(stockRule, request.getPageIndex(), request.getPageSize());
        qr.setRecords(result);
        return qr;
    }

    //请求参数转底层实体
    private StockRule convertRequest(StockRuleQueryRequestModel request) {
        Integer ruleState = request.getState();
        if (null == ruleState || StockRuleStateEnum.DISABLED.getState() != ruleState) {
            ruleState = StockRuleStateEnum.AVAILABLE.getState();
        }
        StockRule stockRule = new StockRule();

        stockRule.setSupplierId(request.getSupplierId());
        stockRule.setName(request.getName());
        stockRule.setCategory(request.getCategory());
        stockRule.setType(request.getType());
        stockRule.setState(ruleState);
        stockRule.setWhetherReturn(request.getWhetherReturn());
        stockRule.setRuleIds(request.getRuleIds());
        stockRule.setVagueName(request.getVagueName());
        return stockRule;
    }
}
