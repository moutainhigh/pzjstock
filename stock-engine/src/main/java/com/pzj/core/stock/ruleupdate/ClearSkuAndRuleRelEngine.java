/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.ruleupdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.stock.write.SkuRuleRelWriteMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 清空产品与库存规则关系表
 * 
 * @author dongchunfu
 * @version $Id: EmptySkuAndRuleRelEngine.java, v 0.1 2016年8月16日 下午2:05:44 dongchunfu Exp $
 */
@Component(value = "clearSkuAndRuleRelEngine")
@Transactional(value = "stock.transactionManager")
public class ClearSkuAndRuleRelEngine {

    @Autowired
    private SkuRuleRelWriteMapper skuRuleRelWriteMapper;

    public int clearSkuAndRuleRel(ServiceContext context) {

        return skuRuleRelWriteMapper.clearSkuAndRuleRel();

    }
}
