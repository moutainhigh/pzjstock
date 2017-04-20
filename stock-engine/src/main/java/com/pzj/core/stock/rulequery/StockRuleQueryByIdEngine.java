/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.rulequery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.ParameterErrorException;
import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.read.StockRuleReadMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 主键查询库存规则引擎
 * 
 * @author dongchunfu
 * @version $Id: StockRuleReadEngine.java, v 0.1 2016年7月25日 上午9:50:52 dongchunfu Exp $
 */
@Component(value = "stockRuleQueryByIdEngine")
public class StockRuleQueryByIdEngine {

    private static final Logger logger = LoggerFactory.getLogger(StockRuleQueryByIdEngine.class);

    @Autowired
    private StockRuleReadMapper stockRuleReadMapper;

    public StockRule selectStockRuleById(Long ruleId, ServiceContext context) {
        if (CommonUtils.checkLongIsNull(ruleId, Boolean.TRUE)) {
            if (logger.isDebugEnabled()) {
                logger.debug("illega param ruleId:{},context:{}", ruleId, context);
                throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
            }
        }
        return stockRuleReadMapper.selectRuleById(ruleId);
    }

}
