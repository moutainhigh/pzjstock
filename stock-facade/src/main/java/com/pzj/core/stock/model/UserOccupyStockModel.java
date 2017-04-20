/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.util.List;

/**
 * 
 * @author Administrator
 * @version $Id: UserOccupyStockModel.java, v 0.1 2016年8月26日 上午11:22:16 Administrator Exp $
 */
public class UserOccupyStockModel implements java.io.Serializable {

    private static final long serialVersionUID = 5170750501847456820L;

    /** 锁库存用户ID*/
    private Long              userId;
    /** 库存规则ID*/
    private Long              stockRuleId;
    /** 总库存数*/
    private Integer           totalStockNum;
    /** 库存数据集合*/
    private List<StockModel>  stockModelList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStockRuleId() {
        return stockRuleId;
    }

    public void setStockRuleId(Long stockRuleId) {
        this.stockRuleId = stockRuleId;
    }

    public Integer getTotalStockNum() {
        return totalStockNum;
    }

    public void setTotalStockNum(Integer totalStockNum) {
        this.totalStockNum = totalStockNum;
    }

    public List<StockModel> getStockModelList() {
        return stockModelList;
    }

    public void setStockModelList(List<StockModel> stockModelList) {
        this.stockModelList = stockModelList;
    }

}
