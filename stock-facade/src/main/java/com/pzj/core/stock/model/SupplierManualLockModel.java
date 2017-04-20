/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

/**
 * 
 * @author Administrator
 * @version $Id: SupplierManualLockModel.java, v 0.1 2016年10月31日 下午1:51:48 Administrator Exp $
 */
public class SupplierManualLockModel implements java.io.Serializable {

    private static final long serialVersionUID = -129943368938134434L;
    /** 锁定库存ID*/
    private Long              stockId;
    /** 操作供应商ID*/
    private Long              supplierId;
    /** 锁定库存数量*/
    private Integer           lockNum;

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
     * Getter method for property <tt>lockNum</tt>.
     * 
     * @return property value of lockNum
     */
    public Integer getLockNum() {
        return lockNum;
    }

    /**
     * Setter method for property <tt>lockNum</tt>.
     * 
     * @param lockNum value to be assigned to property lockNum
     */
    public void setLockNum(Integer lockNum) {
        this.lockNum = lockNum;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SupplierManualLockModel [stockId=" + stockId + ", supplierId=" + supplierId + ", lockNum=" + lockNum + "]";
    }
}
