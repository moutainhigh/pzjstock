/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.stock;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.SupplierManualLockModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: SupplierManualLockStockValidator.java, v 0.1 2016年10月17日 下午5:32:04 Administrator Exp $
 */
@Component("supplierManualLockStockValidator")
public class SupplierManualLockStockValidator implements ObjectConverter<SupplierManualLockModel, ServiceContext, ParamModel> {

    /** 
     * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
     */
    @Override
    public ParamModel convert(SupplierManualLockModel model, ServiceContext context) {
        ParamModel paramModel = ParamModel.getInstance();
        if (CommonUtils.checkObjectIsNull(model)) {
            paramModel.setErrorModel("供应商手动锁定库存传入SupplierManualLockModel对象为空!");
            return paramModel;
        }
        if (CommonUtils.checkLongIsNull(model.getStockId(), true)) {
            paramModel.setErrorModel("供应商手动锁定库存传入stockId库存ID为空!");
            return paramModel;
        }
        if (CommonUtils.checkLongIsNull(model.getSupplierId(), true)) {
            paramModel.setErrorModel("供应商手动锁定库存传入supplierId供应商ID为空!");
            return paramModel;
        }
        if (CommonUtils.checkIntegerIsNull(model.getLockNum(), true)) {
            paramModel.setErrorModel("供应商手动锁定库存传入lockNum锁定库存数为空!");
            return paramModel;
        }
        return paramModel;
    }

}
