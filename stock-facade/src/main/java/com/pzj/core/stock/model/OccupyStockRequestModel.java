/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

/**
 * 
 * @author Administrator
 * @version $Id: StockServiceImpl.java, v 0.1 2016年7月26日 上午10:05:12 Administrator Exp $
 */
public class OccupyStockRequestModel implements java.io.Serializable {

    private static final long serialVersionUID = -6376739863049491795L;

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

    /**调用唯一id*/
    private String            invokeOnlyId;

    public OccupyStockRequestModel() {
    }

    /**
     * 初始化占库存操作对象
     * @param transactionId
     * @param productId
     * @param stockId
     * @param stockNum
     */
    public OccupyStockRequestModel(String transactionId, Long productId, Long stockId, Integer stockNum) {
        this.transactionId = transactionId;
        this.productId = productId;
        this.stockId = stockId;
        this.stockNum = stockNum;
    }

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

    public String getInvokeOnlyId() {
        return invokeOnlyId;
    }

    public void setInvokeOnlyId(String invokeOnlyId) {
        this.invokeOnlyId = invokeOnlyId;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OccupyStockRequestModel [transactionId=" + transactionId + ", productId=" + productId + ", stockId=" + stockId + ", stockNum=" + stockNum
               + ", userId=" + userId + ", bussinessType=" + bussinessType + ", invokeOnlyId=" + invokeOnlyId + "]";
    }

}
