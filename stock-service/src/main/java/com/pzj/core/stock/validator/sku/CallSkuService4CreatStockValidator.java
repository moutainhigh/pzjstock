/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.sku;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.StockModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author dongchunfu
 * @version $Id: CallSkuService4CreatStockValidator.java, v 0.1 2016年8月10日 上午10:59:20 dongchunfu Exp $
 */
@Component(value = "callSkuService4CreatStockValidator")
public class CallSkuService4CreatStockValidator implements ObjectConverter<StockModel, ServiceContext, Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(CallSkuService4CreatStockValidator.class);

    @Override
    public Boolean convert(StockModel model, ServiceContext context) {

        if (Check.NuNObject(model, context)) {
            if (logger.isDebugEnabled()) {
                logger.debug(" illegal param , request:{},context:{}.", model, context);
            }
            return Boolean.FALSE;
        }
        Long ruleId = model.getRuleId();
        if (CommonUtils.checkLongIsNull(ruleId, Boolean.TRUE)) {
            if (logger.isDebugEnabled()) {
                logger.debug(" illegal param , ruleId:{},context:{}.", ruleId, context);
            }
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

}
