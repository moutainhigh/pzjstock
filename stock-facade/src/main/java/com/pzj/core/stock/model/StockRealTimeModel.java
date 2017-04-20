/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.util.Date;

/**
 * 
 * @author Administrator
 * @version $Id: StockRealTimeModel.java, v 0.1 2016年10月14日 下午2:05:39 Administrator Exp $
 */
public class StockRealTimeModel implements java.io.Serializable {

    private static final long serialVersionUID = -6703308300589065465L;
    /** 库存ID*/
    private Long              stockId;
    /** 库存规则ID*/
    private Long              stockRuleId;
    /** 库存规则名称*/
    private String            stockRuleName;
    /** 库存规则类型 1、单一库存 2、每日库存*/
    private Integer           stockRuleType;
    /** 库存时间*/
    private Date              stockDate;
    /** 总库存数*/
    private Integer           totalStockNum;
    /** 剩余库存数*/
    private Integer           remainStockNum;
    /** 当前用户所锁定库存数*/
    private Integer           userLockStockNum;

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
     * Getter method for property <tt>stockRuleName</tt>.
     * 
     * @return property value of stockRuleName
     */
    public String getStockRuleName() {
        return stockRuleName;
    }

    /**
     * Setter method for property <tt>stockRuleName</tt>.
     * 
     * @param stockRuleName value to be assigned to property stockRuleName
     */
    public void setStockRuleName(String stockRuleName) {
        this.stockRuleName = stockRuleName;
    }

    /**
     * Getter method for property <tt>stockRuleType</tt>.
     * 
     * @return property value of stockRuleType
     */
    public Integer getStockRuleType() {
        return stockRuleType;
    }

    /**
     * Setter method for property <tt>stockRuleType</tt>.
     * 
     * @param stockRuleType value to be assigned to property stockRuleType
     */
    public void setStockRuleType(Integer stockRuleType) {
        this.stockRuleType = stockRuleType;
    }

    /**
     * Getter method for property <tt>stockDate</tt>.
     * 
     * @return property value of stockDate
     */
    public Date getStockDate() {
        return stockDate;
    }

    /**
     * Setter method for property <tt>stockDate</tt>.
     * 
     * @param stockDate value to be assigned to property stockDate
     */
    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }

    /**
     * Getter method for property <tt>totalStockNum</tt>.
     * 
     * @return property value of totalStockNum
     */
    public Integer getTotalStockNum() {
        return totalStockNum;
    }

    /**
     * Setter method for property <tt>totalStockNum</tt>.
     * 
     * @param totalStockNum value to be assigned to property totalStockNum
     */
    public void setTotalStockNum(Integer totalStockNum) {
        this.totalStockNum = totalStockNum;
    }

    /**
     * Getter method for property <tt>remainStockNum</tt>.
     * 
     * @return property value of remainStockNum
     */
    public Integer getRemainStockNum() {
        return remainStockNum;
    }

    /**
     * Setter method for property <tt>remainStockNum</tt>.
     * 
     * @param remainStockNum value to be assigned to property remainStockNum
     */
    public void setRemainStockNum(Integer remainStockNum) {
        this.remainStockNum = remainStockNum;
    }

    /**
     * Getter method for property <tt>userLockStockNum</tt>.
     * 
     * @return property value of userLockStockNum
     */
    public Integer getUserLockStockNum() {
        return userLockStockNum;
    }

    /**
     * Setter method for property <tt>userLockStockNum</tt>.
     * 
     * @param userLockStockNum value to be assigned to property userLockStockNum
     */
    public void setUserLockStockNum(Integer userLockStockNum) {
        this.userLockStockNum = userLockStockNum;
    }

    /**
     * Getter method for property <tt>stockRuleId</tt>.
     * 
     * @return property value of stockRuleId
     */
    public Long getStockRuleId() {
        return stockRuleId;
    }

    /**
     * Setter method for property <tt>stockRuleId</tt>.
     * 
     * @param stockRuleId value to be assigned to property stockRuleId
     */
    public void setStockRuleId(Long stockRuleId) {
        this.stockRuleId = stockRuleId;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StockRealTimeModel [stockId=" + stockId + ", stockRuleId=" + stockRuleId + ", stockRuleName=" + stockRuleName + ", stockRuleType="
               + stockRuleType + ", stockDate=" + stockDate + ", totalStockNum=" + totalStockNum + ", remainStockNum=" + remainStockNum + ", userLockStockNum="
               + userLockStockNum + "]";
    }

}
