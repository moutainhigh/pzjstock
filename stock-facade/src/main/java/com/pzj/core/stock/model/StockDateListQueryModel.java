/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.util.ArrayList;
import java.util.Set;

/**
 * 
 * @author Administrator
 * @version $Id: StockDateListQueryModel.java, v 0.1 2016年8月29日 下午1:44:58 Administrator Exp $
 */
public class StockDateListQueryModel implements java.io.Serializable {

    private static final long serialVersionUID = 2796297496011689636L;
    /** 库存规则ID*/
    private Long              stockRuleId;
    /** 库存时间集合*/
    private ArrayList<String> stockTimeList;
    /** 可用库存时间集合*/
    private Set<Integer>      avaiStockTimeSet;

    public Long getStockRuleId() {
        return stockRuleId;
    }

    public void setStockRuleId(Long stockRuleId) {
        this.stockRuleId = stockRuleId;
    }

    public ArrayList<String> getStockTimeList() {
        return stockTimeList;
    }

    public void setStockTimeList(ArrayList<String> stockTimeList) {
        this.stockTimeList = stockTimeList;
    }

    /**
     * Getter method for property <tt>avaiStockTimeSet</tt>.
     * 
     * @return property value of avaiStockTimeSet
     */
    public Set<Integer> getAvaiStockTimeSet() {
        return avaiStockTimeSet;
    }

    /**
     * Setter method for property <tt>avaiStockTimeSet</tt>.
     * 
     * @param avaiStockTimeSet value to be assigned to property avaiStockTimeSet
     */
    public void setAvaiStockTimeSet(Set<Integer> avaiStockTimeSet) {
        this.avaiStockTimeSet = avaiStockTimeSet;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StockDateListQueryModel [stockRuleId=" + stockRuleId + ", stockTimeList=" + stockTimeList + ", avaiStockTimeSet=" + avaiStockTimeSet + "]";
    }

}
