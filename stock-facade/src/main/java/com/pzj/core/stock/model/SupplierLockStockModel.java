/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

/**
 * 
 * @author Administrator
 * @version $Id: SupplierLockStockModel.java, v 0.1 2016年11月9日 下午4:16:51 Administrator Exp $
 */
public class SupplierLockStockModel implements java.io.Serializable {

    private static final long serialVersionUID = 8410552275738216689L;
    /** 库存ID*/
    private Long              stockId;
    /** 用户锁定库存数量*/
    private Integer           userLockNum;

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
     * Getter method for property <tt>userLockNum</tt>.
     * 
     * @return property value of userLockNum
     */
    public Integer getUserLockNum() {
        return userLockNum;
    }

    /**
     * Setter method for property <tt>userLockNum</tt>.
     * 
     * @param userLockNum value to be assigned to property userLockNum
     */
    public void setUserLockNum(Integer userLockNum) {
        this.userLockNum = userLockNum;
    }

}
