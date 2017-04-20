/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.enums;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;

/**
 * 
 * @author Administrator
 * @version $Id: OperateStockBussinessType.java, v 0.1 2016年7月22日 上午11:40:36 Administrator Exp $
 */
public enum OperateStockBussinessType {
    /** 下单占库存*/
    ORDER_OCCUPY_STOCK(1, "下单占库存"),
    /** 退票释放库存*/
    REFUND_RELEASE_STOCK(2, "退票释放库存"),
    /** 订单过期释放库存*/
    EXPIRE_RELEASE_STOCK(3, "订单过期释放库存"),
    /** 批量占用库存*/
    BATCH_OCCUPY_STOCK(4, "批量占用库存"),
    /** 批量释放库存*/
    BATCH_RELEASE_STOCK(5, "批量释放库存"),
    /** 供应商手动锁定库存*/
    SUPPLIER_MANUAL_LOCK_STOCK(6, "供应商手动锁定库存");

    public int    key;
    public String value;

    private OperateStockBussinessType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(int key) {
        OperateStockBussinessType sbt = getOperateShowBussinessType(key);
        return sbt.value;
    }

    public static OperateStockBussinessType getOperateShowBussinessType(int key) throws StockException {
        OperateStockBussinessType[] operateStockBussType = OperateStockBussinessType.values();
        for (OperateStockBussinessType osbt : operateStockBussType) {
            if (osbt.key == key) {
                return osbt;
            }
        }
        throw new StockException(StockExceptionCode.STOCK_ERR_MSG);
    }
}
