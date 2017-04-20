/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.rulequery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.stock.read.SkuRuleRelReadMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 扫描产品库存规则关系表并按库存规则ID排序
 * 
 * @author dongchunfu
 * @version $Id: GetAllSkuRelsEngine.java, v 0.1 2016年8月16日 下午2:19:10 dongchunfu Exp $
 */
@Component(value = "getAllSkuRelsEngine")
public class GetAllSkuRelsEngine {

    @Autowired
    private SkuRuleRelReadMapper skuRuleRelReadMapper;

    public List<Long> getAllSkuRels(ServiceContext context) {

        List<Long> ruleIds = skuRuleRelReadMapper.selectAllRuleOrderedIds();

        return null == ruleIds || ruleIds.size() == 0 ? null : ruleIds;

    }
}
