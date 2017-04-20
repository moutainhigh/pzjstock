/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.rule;

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
@Component(value = "queryStockRuleByIdValidator")
public class QueryStockRuleByIdValidator implements ObjectConverter<StockRuleQueryRequestModel, ServiceContext, Boolean> {

    @Override
    public Boolean convert(StockRuleQueryRequestModel request, ServiceContext context) {
        if (Check.NuNObject(request)) {
            return Boolean.FALSE;
        }
        Long ruleId = request.getRuleId();
        if (CommonUtils.checkLongIsNull(ruleId, true)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
