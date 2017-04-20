/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.stock;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.OccupyStockRequestModel;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: RollbackOccupyStockValidator.java, v 0.1 2016年8月19日 下午2:04:58 Administrator Exp $
 */
@Component(value = "rollbackOccupyStockValidator")
public class RollbackOccupyStockValidator implements ObjectConverter<OccupyStockRequestModel, ServiceContext, ParamModel> {

    @Override
    public ParamModel convert(OccupyStockRequestModel stockModel, ServiceContext context) {
        ParamModel paramModel = ParamModel.getInstance();
        if (CommonUtils.checkObjectIsNull(stockModel)) {
            paramModel.setErrorModel("回滚库存传入OccupyStockRequestModel是空!");
            return paramModel;
        }
        if (CommonUtils.checkStringIsNullStrict(stockModel.getTransactionId())) {
            paramModel.setErrorModel("回滚库存传入transactionId交易ID是空!");
            return paramModel;
        }
        return paramModel;
    }
}
