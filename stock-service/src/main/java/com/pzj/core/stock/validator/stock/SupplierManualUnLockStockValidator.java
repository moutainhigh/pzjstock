/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.stock;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.SupplierManualUnlockModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: SupplierManualUnLockStockValidator.java, v 0.1 2016年10月17日 下午5:44:20 Administrator Exp $
 */
@Component("supplierManualUnLockStockValidator")
public class SupplierManualUnLockStockValidator implements ObjectConverter<SupplierManualUnlockModel, ServiceContext, ParamModel> {

    /** 
     * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
     */
    @Override
    public ParamModel convert(SupplierManualUnlockModel model, ServiceContext context) {
        ParamModel paramModel = ParamModel.getInstance();
        if (CommonUtils.checkObjectIsNull(model)) {
            paramModel.setErrorModel("供应商手动解锁库存传入SupplierManualUnlockModel对象为空!");
            return paramModel;
        }
        if (CommonUtils.checkLongIsNull(model.getStockId(), true)) {
            paramModel.setErrorModel("供应商手动解锁库存传入stockId库存ID为空!");
            return paramModel;
        }
        if (CommonUtils.checkLongIsNull(model.getSupplierId(), true)) {
            paramModel.setErrorModel("供应商手动解锁库存传入supplierId供应商ID为空!");
            return paramModel;
        }
        if (CommonUtils.checkIntegerIsNull(model.getUnlockNum(), true)) {
            paramModel.setErrorModel("供应商手动解锁库存传入unlockNum解锁库存数为空!");
            return paramModel;
        }
        return paramModel;
    }

}
