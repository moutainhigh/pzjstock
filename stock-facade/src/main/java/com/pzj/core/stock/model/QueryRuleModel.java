/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author dongchunfu
 * @version $Id: QueryRuleModel.java, v 0.1 2016年8月15日 下午1:40:18 dongchunfu Exp $
 */
public class QueryRuleModel implements Serializable {

    private static final long serialVersionUID = -3997023268088604912L;

    private List<Long>        ruleIds;
    private Integer           stockTime;

    /**
     * 
     */
    public QueryRuleModel() {
        super();
    }

    public List<Long> getRuleIds() {
        return null == ruleIds || ruleIds.size() == 0 ? null : ruleIds;
    }

    public void setRuleIds(List<Long> ruleIds) {
        this.ruleIds = ruleIds;
    }

    public Integer getStockTime() {
        return stockTime;
    }

    public void setStockTime(Integer stockTime) {
        this.stockTime = stockTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(QueryRuleModel.class.getSimpleName());
        sb.append("[");
        sb.append("ruleIds: ").append(ruleIds).append(",");
        sb.append("stockTime: ").append(stockTime).append(".");
        sb.append("]");
        return sb.toString().intern();
    }

}
