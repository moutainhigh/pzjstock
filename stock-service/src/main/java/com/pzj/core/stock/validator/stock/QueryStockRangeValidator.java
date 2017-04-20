/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.StockQueryRequestModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author dongchunfu
 * @version $Id: QueryStockRuleValidator.java, v 0.1 2016年8月3日 下午7:44:45 dongchunfu Exp $
 */
@Component(value = "queryStockRangeValidator")
public class QueryStockRangeValidator implements ObjectConverter<StockQueryRequestModel, ServiceContext, Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(QueryStockRangeValidator.class);

    @Override
    public Boolean convert(StockQueryRequestModel model, ServiceContext context) {
        if (Check.NuNObject(model)) {
            return Boolean.FALSE;
        }
        Long ruleId = model.getRuleId();
        if (CommonUtils.checkLongIsNull(ruleId, Boolean.TRUE)) {
            if (logger.isDebugEnabled()) {
                logger.debug("illegal param ,ruleId:{},context:{}.", ruleId, context);
            }
            return Boolean.FALSE;
        }

        int beginStockTime = model.getBeginStockTime();
        if (String.valueOf(beginStockTime).length() != 8) {
            if (logger.isDebugEnabled()) {
                logger.debug("illegal param ,stockTime:{},context:{}.", beginStockTime, context);
            }
            return Boolean.FALSE;
        }
        if (beginStockTime == 0) {
            if (logger.isDebugEnabled()) {
                logger.debug("illegal param ,beginStockTime:{},context:{}.", beginStockTime, context);
            }
            return Boolean.FALSE;
        }
        int endStockTime = model.getEndStockTime();
        if (String.valueOf(endStockTime).length() != 8) {
            if (logger.isDebugEnabled()) {
                logger.debug("illegal param ,stockTime:{},context:{}.", endStockTime, context);
            }
            return Boolean.FALSE;
        }
        if (beginStockTime == 0) {
            if (logger.isDebugEnabled()) {
                logger.debug("illegal param ,endStockTime:{},context:{}.", endStockTime, context);
            }
            return Boolean.FALSE;
        }
        if (endStockTime < beginStockTime) {
            if (logger.isDebugEnabled()) {
                logger.debug("illegal param ,beginStockTime:{} ,endStockTime:{},context:{}.", beginStockTime, endStockTime, context);
            }
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

}
