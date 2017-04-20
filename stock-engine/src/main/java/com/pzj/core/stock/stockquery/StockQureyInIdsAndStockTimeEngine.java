/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.stockquery;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.stock.read.StockReadMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * 
 * @author dongchunfu
 * @version $Id: StockQureyInIdsAndStockTimeEngine.java, v 0.1 2016年8月15日 下午2:58:26 dongchunfu Exp $
 */
@Component(value = "stockQureyInIdsAndStockTimeEngine")
public class StockQureyInIdsAndStockTimeEngine {

    private static final Logger logger = LoggerFactory.getLogger(StockQureyInIdsAndStockTimeEngine.class);

    @Autowired
    private StockReadMapper     stockReadMapper;

    public ArrayList<Long> queryRuleIdsByDate(List<Long> ruleIds, Integer stockTime, ServiceContext context) {

        Boolean success = validator(ruleIds, stockTime, context);
        if (!success) {
            return null;
        }

        ArrayList<Long> ids = stockReadMapper.selectIdsInRuleIdsAndStockTime(ruleIds, stockTime);

        return null == ids || ids.size() == 0 ? null : ids;
    }

    private Boolean validator(List<Long> ruleIds, Integer stockTime, ServiceContext context) {
        if (null == ruleIds || ruleIds.size() == 0) {
            if (logger.isDebugEnabled()) {
                logger.debug("illegal param,ruleIds:{},context:{}.", ruleIds, context);
            }
            return Boolean.FALSE;
        }

        if (null == stockTime || stockTime < 0) {
            if (logger.isDebugEnabled()) {
                logger.debug("illegal param,stockTime:{},context:{}.", stockTime, context);
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
