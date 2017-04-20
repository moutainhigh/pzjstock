/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

/**
 * 
 * @author Administrator
 * @version $Id: CheckStockModel.java, v 0.1 2016年8月26日 上午9:37:08 Administrator Exp $
 */
public class CheckStockModel implements java.io.Serializable {

    private static final long serialVersionUID = 912479125921402852L;
    /** 库存规则ID*/
    private Long              stockRuleId;
    /** 库存时间 例：20160820*/
    private String            stockTime;
    /** 库存ID*/
    private Long              stockId;
    /** 占用库存数量*/
    private Integer           occupyNum;
    /** 查询库存类型 1：通过库存ID 2：通过库存规则ID*/
    private Integer           queryType;

    public enum QueryStockType {
        STOCK_ID(1), STOCK_RULE_ID(2);

        QueryStockType(int key) {
            this.key = key;
        }

        public int key;

    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Integer getOccupyNum() {
        return occupyNum;
    }

    public void setOccupyNum(Integer occupyNum) {
        this.occupyNum = occupyNum;
    }

    public Long getStockRuleId() {
        return stockRuleId;
    }

    public void setStockRuleId(Long stockRuleId) {
        this.stockRuleId = stockRuleId;
    }

    public String getStockTime() {
        return stockTime;
    }

    public void setStockTime(String stockTime) {
        this.stockTime = stockTime;
    }

    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    @Override
    public String toString() {
        return "CheckStockModel [stockRuleId=" + stockRuleId + ", stockTime=" + stockTime + ", stockId=" + stockId + ", occupyNum=" + occupyNum
               + ", queryType=" + queryType + "]";
    }

}
