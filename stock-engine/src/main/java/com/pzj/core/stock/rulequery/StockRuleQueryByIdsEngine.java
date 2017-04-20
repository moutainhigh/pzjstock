/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.rulequery;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.model.QueryStockRuleByIdsModel;
import com.pzj.core.stock.read.StockRuleReadMapper;

/**
 * 
 * @author Administrator
 * @version $Id: StockRuleQueryByIdsEngine.java, v 0.1 2016年10月21日 上午10:35:45 Administrator Exp $
 */
@Component("stockRuleQueryByIdsEngine")
public class StockRuleQueryByIdsEngine {
    @Resource(name = "stockRuleReadMapper")
    private StockRuleReadMapper stockRuleReadMapper;

    public List<StockRule> queryStockRuleByIds(QueryStockRuleByIdsModel model) {
        ArrayList<StockRule> stockRuleList = stockRuleReadMapper.queryStockRuleByIds(model.getStockRuleIds());
        if (!CommonUtils.checkIntegerIsNull(model.getState(), true) && !CommonUtils.checkObjectIsNull(stockRuleList)) {
            int state = model.getState();
            Iterator<StockRule> itera = stockRuleList.iterator();
            StockRule stockRule = null;
            while (itera.hasNext()) {
                stockRule = itera.next();
                if (state != stockRule.getState()) {
                    itera.remove();
                }
            }
        }
        return stockRuleList;
    }
}
