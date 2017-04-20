/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.util.List;

/**
 * 
 * @author Administrator
 * @version $Id: CheckSeatsOptionalModel.java, v 0.1 2016年10月26日 上午11:43:54 Administrator Exp $
 */
public class CheckSeatsOptionalModel implements java.io.Serializable {

    private static final long serialVersionUID = 8024985133118221402L;
    /** 库存ID*/
    private Long              stockId;
    /** 座位号集合*/
    private List<String>      seats;

    /**  占座用户ID*/
    private Long              operateUserId;

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
     * Getter method for property <tt>seats</tt>.
     * 
     * @return property value of seats
     */
    public List<String> getSeats() {
        return seats;
    }

    /**
     * Setter method for property <tt>seats</tt>.
     * 
     * @param seats value to be assigned to property seats
     */
    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    /**
     * Getter method for property <tt>operateUserId</tt>.
     * 
     * @return property value of operateUserId
     */
    public Long getOperateUserId() {
        return operateUserId;
    }

    /**
     * Setter method for property <tt>operateUserId</tt>.
     * 
     * @param operateUserId value to be assigned to property operateUserId
     */
    public void setOperateUserId(Long operateUserId) {
        this.operateUserId = operateUserId;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CheckSeatsOptionalModel [stockId=" + stockId + ", seats=" + seats + ", operateUserId=" + operateUserId + "]";
    }

}
