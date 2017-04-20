/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.stockquery;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.entity.LockRecord;
import com.pzj.core.stock.enums.OperateStockBussinessType;
import com.pzj.core.stock.model.SupplierLockStockModel;
import com.pzj.core.stock.model.SupplierLockStockQueryModel;
import com.pzj.core.stock.read.LockRecordReadMapper;

/**
 * 
 * @author Administrator
 * @version $Id: QuerySupplierLockStockEngine.java, v 0.1 2016年11月9日 下午4:27:21 Administrator Exp $
 */
@Component("querySupplierLockStockEngine")
public class QuerySupplierLockStockEngine {
    @Resource(name = "lockRecordReadMapper")
    private LockRecordReadMapper lockRecordReadMapper;

    public SupplierLockStockModel querySupplierLockStock(SupplierLockStockQueryModel model) {
        List<Long> stockIds = new ArrayList<Long>();
        stockIds.add(model.getStockId());
        List<LockRecord> lockRecordList = lockRecordReadMapper.queryLockRecordByUser(stockIds, model.getSupplierId(),
            OperateStockBussinessType.SUPPLIER_MANUAL_LOCK_STOCK.key);

        if (CommonUtils.checkCollectionIsNull(lockRecordList)) {
            return null;
        }

        SupplierLockStockModel supplierLockStock = new SupplierLockStockModel();
        supplierLockStock.setStockId(lockRecordList.get(0).getStockId());
        supplierLockStock.setUserLockNum(lockRecordList.get(0).getLockNum());
        return supplierLockStock;
    }
}
