/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.stock;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.UserOccupyStockModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: QueryUserBatchOccupyStockValidator.java, v 0.1 2016年8月26日 上午11:31:47 Administrator Exp $
 */
@Component("queryUserBatchOccupyStockValidator")
public class QueryUserBatchOccupyStockValidator implements ObjectConverter<UserOccupyStockModel, ServiceContext, ParamModel> {

    /** 
     * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
     */
    @Override
    public ParamModel convert(UserOccupyStockModel userOccupyStockModel, ServiceContext context) {
        ParamModel paramModel = ParamModel.getInstance();
        if (CommonUtils.checkObjectIsNull(userOccupyStockModel)) {
            paramModel.setErrorModel("查询用户批量占用库存传入UserOccupyStockModel对象为空!");
            return paramModel;
        }
        if (CommonUtils.checkLongIsNull(userOccupyStockModel.getUserId(), true)) {
            paramModel.setErrorModel("查询用户批量占用库存传入userId用户ID为空!");
            return paramModel;
        }
        if (CommonUtils.checkLongIsNull(userOccupyStockModel.getStockRuleId(), true)) {
            paramModel.setErrorModel("查询用户批量占用库存传入stockRuleId库存规则ID为空!");
            return paramModel;
        }
        return paramModel;
    }

}
