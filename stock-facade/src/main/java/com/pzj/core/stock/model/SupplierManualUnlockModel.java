/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

/**
 * 
 * @author Administrator
 * @version $Id: SupplierManualUnlockModel.java, v 0.1 2016年10月31日 下午1:52:44 Administrator Exp $
 */
public class SupplierManualUnlockModel implements java.io.Serializable {

    private static final long serialVersionUID = 655486282083812378L;
    /** 释放库存ID*/
    private Long              stockId;
    /** 操作供应商ID*/
    private Long              supplierId;
    /** 释放库存数量*/
    private Integer           unlockNum;

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
     * Getter method for property <tt>unlockNum</tt>.
     * 
     * @return property value of unlockNum
     */
    public Integer getUnlockNum() {
        return unlockNum;
    }

    /**
     * Setter method for property <tt>unlockNum</tt>.
     * 
     * @param unlockNum value to be assigned to property unlockNum
     */
    public void setUnlockNum(Integer unlockNum) {
        this.unlockNum = unlockNum;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SupplierManualUnlockModel [stockId=" + stockId + ", supplierId=" + supplierId + ", unlockNum=" + unlockNum + "]";
    }

}
