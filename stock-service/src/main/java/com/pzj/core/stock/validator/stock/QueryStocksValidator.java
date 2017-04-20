/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.stock;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.StockQueryRequestModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author dongchunfu
 * @version $Id: QueryStockRuleValidator.java, v 0.1 2016年8月3日 下午7:44:45 dongchunfu Exp $
 */
@Component(value = "queryStocksValidator")
public class QueryStocksValidator implements ObjectConverter<StockQueryRequestModel, ServiceContext, Boolean> {

    @Override
    public Boolean convert(StockQueryRequestModel model, ServiceContext context) {
        if (CommonUtils.checkObjectIsNull(model)) {
            return Boolean.FALSE;
        }
        Long ruleId = model.getRuleId();
        List<Long> stockIds = model.getStockIds();

        if (CommonUtils.checkLongIsNull(ruleId, Boolean.TRUE) && CommonUtils.checkCollectionIsNull(stockIds)) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

}
