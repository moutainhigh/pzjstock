/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.util.List;

/**
 * 
 * @author Administrator
 * @version $Id: OccupyStockAndSeatModel.java, v 0.1 2016年8月18日 下午4:11:48 Administrator Exp $
 */
public class OccupyStockAndSeatModel implements java.io.Serializable {

    /**  */
    private static final long serialVersionUID = 2439643288283202760L;

    /**交易唯一id*/
    private String            transactionId;

    /**产品id*/
    private Long              productId;

    /**库存id*/
    private Long              stockId;

    /**使用库存数量*/
    private Integer           stockNum;

    /**操作用户id*/
    private Long              userId;

    /** 操作业务类型*/
    private Integer           bussinessType;

    /** 座位号用逗号分隔*/
    private List<String>      seats;

    /** 随机分配座位数量*/
    private Integer           randomNum;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getBussinessType() {
        return bussinessType;
    }

    public void setBussinessType(Integer bussinessType) {
        this.bussinessType = bussinessType;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    public Integer getRandomNum() {
        return randomNum;
    }

    public void setRandomNum(Integer randomNum) {
        this.randomNum = randomNum;
    }

}
