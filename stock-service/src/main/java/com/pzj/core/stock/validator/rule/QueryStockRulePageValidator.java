/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.StockRuleQueryRequestModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author dongchunfu
 * @version $Id: QueryStockRuleValidator.java, v 0.1 2016年8月3日 下午7:44:45 dongchunfu Exp $
 */
@Component(value = "queryStockRulePageValidator")
public class QueryStockRulePageValidator implements ObjectConverter<StockRuleQueryRequestModel, ServiceContext, Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(QueryStockRulePageValidator.class);

    @Override
    public Boolean convert(StockRuleQueryRequestModel model, ServiceContext context) {

        if (Check.NuNObject(model)) {
            if (logger.isDebugEnabled()) {
                logger.debug("illegal param, request:{},context:{}.", model, context);
            }
            return Boolean.FALSE;
        }

        Long supplierId = model.getSupplierId();

        if (CommonUtils.checkLongIsNull(supplierId, true)) {
            if (logger.isDebugEnabled()) {
                logger.debug("illegal param, supplierId:{},context:{}.", supplierId, context);
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
