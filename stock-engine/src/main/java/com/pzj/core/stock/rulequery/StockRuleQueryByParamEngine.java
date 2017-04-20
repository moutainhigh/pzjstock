/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.rulequery;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.enums.StockRuleStateEnum;
import com.pzj.core.stock.model.StockRuleQueryRequestModel;
import com.pzj.core.stock.read.StockRuleReadMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 库存规则分页查询
 * 
 * @author dongchunfu
 * @version $Id: StockRuleQueryByParamEngine.java, v 0.1 2016年8月17日 下午3:04:37 dongchunfu Exp $
 */
@Component(value = "stockRuleQueryByParamEngine")
public class StockRuleQueryByParamEngine {

    @Resource
    private StockRuleReadMapper stockRuleReadMapper;

    /**分页查询*/
    public List<StockRule> queryRuleByParam(StockRuleQueryRequestModel model, ServiceContext context) {

        StockRule stockRule = convertRequest(model);

        List<StockRule> result = stockRuleReadMapper.selectRuleByPage(stockRule, null, null);

        return result;
    }

    //请求参数转底层实体
    private StockRule convertRequest(StockRuleQueryRequestModel request) {
        Integer ruleState = request.getState();
        if (null == ruleState || StockRuleStateEnum.DISABLED.getState() != ruleState) {
            ruleState = StockRuleStateEnum.AVAILABLE.getState();
        }
        StockRule stockRule = new StockRule();
        stockRule.setId(request.getRuleId());
        stockRule.setSupplierId(request.getSupplierId());
        stockRule.setName(request.getName());
        stockRule.setCategory(request.getCategory());
        stockRule.setType(request.getType());
        stockRule.setState(ruleState);
        stockRule.setWhetherReturn(request.getWhetherReturn());
        stockRule.setRuleIds(request.getRuleIds());

        return stockRule;
    }

}
