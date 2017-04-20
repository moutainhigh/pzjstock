/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.ruleupdate;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.stock.entity.SkuRuleRel;
import com.pzj.core.stock.write.SkuRuleRelWriteMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 创建库存规则引擎
 * @author dongchunfu
 * @version $Id: CreateStockRuleEngine.java, v 0.1 2016年8月4日 下午2:03:42 dongchunfu Exp $
 */
@Component(value = "syncSkuAndRuleRelEngine")
@Transactional(value = "stock.transactionManager")
public class SyncSkuAndRuleRelEngine {
    @Autowired
    private SkuRuleRelWriteMapper skuRuleRelWriteMapper;

    private static final Logger   logger = LoggerFactory.getLogger(SyncSkuAndRuleRelEngine.class);

    public Integer syncSkuAndRuleRel(List<SkuRuleRel> stockRels, ServiceContext context) {

        if (null == stockRels || stockRels.size() == 0) {
            if (logger.isDebugEnabled()) {
                logger.debug("illegal param,stockRels:{}.", stockRels);
            }
            return null;
        }

        skuRuleRelWriteMapper.addBatchSkuRuleRel(stockRels);

        return stockRels.size();
    }
}
