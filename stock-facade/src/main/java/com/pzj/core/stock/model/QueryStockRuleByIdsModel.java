/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.util.List;

/**
 * 
 * @author Administrator
 * @version $Id: QueryStockRuleByIdsModel.java, v 0.1 2016年10月21日 上午10:12:59 Administrator Exp $
 */
public class QueryStockRuleByIdsModel implements java.io.Serializable {

    private static final long serialVersionUID = 7999717480928510757L;
    /** 库存规则ID集合*/
    List<Long>                stockRuleIds;
    /** 库存规则状态 1 正常 2 停用*/
    private Integer           state;

    /**
     * Getter method for property <tt>stockRuleIds</tt>.
     * 
     * @return property value of stockRuleIds
     */
    public List<Long> getStockRuleIds() {
        return stockRuleIds;
    }

    /**
     * Setter method for property <tt>stockRuleIds</tt>.
     * 
     * @param stockRuleIds value to be assigned to property stockRuleIds
     */
    public void setStockRuleIds(List<Long> stockRuleIds) {
        this.stockRuleIds = stockRuleIds;
    }

    /**
     * Getter method for property <tt>state</tt>.
     * 
     * @return property value of state
     */
    public Integer getState() {
        return state;
    }

    /**
     * Setter method for property <tt>state</tt>.
     * 
     * @param state value to be assigned to property state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "QueryStockRuleByIdsModel [stockRuleIds=" + stockRuleIds + ", state=" + state + "]";
    }

}
