/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

/**
 * 
 * @author Administrator
 * @version $Id: SupplierLockStockQueryModel.java, v 0.1 2016年11月9日 下午4:13:00 Administrator Exp $
 */
public class SupplierLockStockQueryModel implements java.io.Serializable {

    private static final long serialVersionUID = -2198145269666286080L;
    /** 库存ID*/
    private Long              stockId;
    /** 供应商ID*/
    private Long              supplierId;

    /**
     * @param stockId
     * @param supplierId
     */
    public SupplierLockStockQueryModel(Long stockId, Long supplierId) {
        super();
        this.stockId = stockId;
        this.supplierId = supplierId;
    }

    /**
     * Getter method for property <tt>stockId</tt>.
     * 
     * @return property value of stockId
     */
    public Long getStockId() {
        return stockId;
    }

    /**
     * Setter method for property <tt>stockId</tt>.
     * 
     * @param stockId value to be assigned to property stockId
     */
    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    /**
     * Getter method for property <tt>supplierId</tt>.
     * 
     * @return property value of supplierId
     */
    public Long getSupplierId() {
        return supplierId;
    }

    /**
     * Setter method for property <tt>supplierId</tt>.
     * 
     * @param supplierId value to be assigned to property supplierId
     */
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SupplierLockStockQueryModel [stockId=" + stockId + ", supplierId=" + supplierId + "]";
    }

}
