/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.stock;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.SupplierLockStockQueryModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: QuerySupplierLockStockValidator.java, v 0.1 2016年11月9日 下午4:20:56 Administrator Exp $
 */
@Component("querySupplierLockStockValidator")
public class QuerySupplierLockStockValidator implements ObjectConverter<SupplierLockStockQueryModel, ServiceContext, ParamModel> {

    /** 
     * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
     */
    @Override
    public ParamModel convert(SupplierLockStockQueryModel model, ServiceContext context) {
        ParamModel paramModel = ParamModel.getInstance();
        if (CommonUtils.checkObjectIsNull(model)) {
            paramModel.setErrorModel("查询供应商锁定库存传入SupplierLockStockQueryModel对象为空!");
            return paramModel;
        }
        if (CommonUtils.checkLongIsNull(model.getSupplierId(), true)) {
            paramModel.setErrorModel("查询供应商锁定库存传入supplierId供应商ID为空!");
            return paramModel;
        }
        if (CommonUtils.checkLongIsNull(model.getStockId(), true)) {
            paramModel.setErrorModel("查询供应商锁定库存传入stockId库存ID为空!");
            return paramModel;
        }
        return paramModel;
    }

}
