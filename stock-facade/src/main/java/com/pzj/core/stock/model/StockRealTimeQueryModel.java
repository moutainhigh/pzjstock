/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author Administrator
 * @version $Id: StockRealTimeQueryModel.java, v 0.1 2016年10月14日 下午2:00:39 Administrator Exp $
 */
public class StockRealTimeQueryModel implements java.io.Serializable {

    private static final long serialVersionUID = -79699790939032029L;
    /** 供应商ID*/
    private Long              supplierId;
    /** 库存时间*/
    private Date              stockDate;
    /** 库存规则ID集合*/
    private List<Long>        stockRuleIdList;

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
     * Getter method for property <tt>stockRuleIdList</tt>.
     * 
     * @return property value of stockRuleIdList
     */
    public List<Long> getStockRuleIdList() {
        return stockRuleIdList;
    }

    /**
     * Setter method for property <tt>stockRuleIdList</tt>.
     * 
     * @param stockRuleIdList value to be assigned to property stockRuleIdList
     */
    public void setStockRuleIdList(List<Long> stockRuleIdList) {
        this.stockRuleIdList = stockRuleIdList;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StockRealTimeQueryModel [supplierId=" + supplierId + ", stockDate=" + stockDate + ", stockRuleIdList=" + stockRuleIdList + "]";
    }

}
