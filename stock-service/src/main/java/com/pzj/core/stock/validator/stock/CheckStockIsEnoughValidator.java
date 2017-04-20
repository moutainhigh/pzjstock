/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.stock;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.CheckStockModel;
import com.pzj.core.stock.model.CheckStockModel.QueryStockType;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: CheckStockIsEnoughValidator.java, v 0.1 2016年8月26日 上午9:42:27 Administrator Exp $
 */
@Component("checkStockIsEnoughValidator")
public class CheckStockIsEnoughValidator implements ObjectConverter<CheckStockModel, ServiceContext, ParamModel> {

    /** 
     * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
     */
    @Override
    public ParamModel convert(CheckStockModel checkStockModel, ServiceContext serviceContext) {
        ParamModel paramModel = ParamModel.getInstance();
        if (CommonUtils.checkObjectIsNull(checkStockModel)) {
            paramModel.setErrorModel("检查库存是否足够传入CheckStockModel对象为空!");
            return paramModel;
        }
        if (CommonUtils.checkIntegerIsNull(checkStockModel.getOccupyNum(), true)) {
            paramModel.setErrorModel("检查库存是否足够传入occupyNum占用库存数量为空!");
            return paramModel;
        }

        boolean stockFlag = true, stockRuleFlag = true;
        if (!CommonUtils.checkLongIsNull(checkStockModel.getStockRuleId(), true)) {
            stockRuleFlag = false;
            checkStockModel.setQueryType(QueryStockType.STOCK_RULE_ID.key);
        }
        if (!CommonUtils.checkLongIsNull(checkStockModel.getStockId(), true)) {
            stockFlag = false;
            checkStockModel.setQueryType(QueryStockType.STOCK_ID.key);
        }
        if (stockFlag && stockRuleFlag) {
            paramModel.setErrorModel("检查库存是否足够传入库存规则ID和库存ID两个参数必须传入一个!");
            return paramModel;
        }
        return paramModel;
    }

}
