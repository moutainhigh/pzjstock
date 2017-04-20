/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.util.ArrayList;

/**
 * 
 * @author Administrator
 * @version $Id: AddStockModel.java, v 0.1 2016年9月8日 下午4:58:10 Administrator Exp $
 */
public class AddStockModel implements java.io.Serializable {

    private static final long  serialVersionUID = 8601495397433625770L;
    /** 库存规则ID*/
    private Long               stockRuleId;
    /** 生成库存的时间集合(整形)*/
    private ArrayList<Integer> stockTimeIntList;
    /** 生成库存的时间集合(字符串)*/
    private ArrayList<String>  stockTimeStrList;

    public Long getStockRuleId() {
        return stockRuleId;
    }

    public void setStockRuleId(Long stockRuleId) {
        this.stockRuleId = stockRuleId;
    }

    public ArrayList<Integer> getStockTimeIntList() {
        return stockTimeIntList;
    }

    public void setStockTimeIntList(ArrayList<Integer> stockTimeIntList) {
        this.stockTimeIntList = stockTimeIntList;
    }

    public ArrayList<String> getStockTimeStrList() {
        return stockTimeStrList;
    }

    public void setStockTimeStrList(ArrayList<String> stockTimeStrList) {
        this.stockTimeStrList = stockTimeStrList;
    }

}
