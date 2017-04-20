/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

/**
 * 预占库存VO
 * 
 * @author dongchunfu
 * @version $Id: StockServiceImpl.java, v 0.1 2016年7月26日 上午10:05:12 dongchunfu Exp $
 */
public class OrderStockModel implements java.io.Serializable {

    private static final long serialVersionUID = -6584533228789514798L;

    /**
     * 交易唯一id
     */
    private String            transactionId;

    /**
     * 产品id
     */
    private Long              productId;

    /**
     * 库存id
     */
    private Long              stockId;

    /**
     * 使用库存数量
     */
    private Integer           stockNum;

    /**
     * 操作用户id
     */
    private Long              userId;

    /**
     * 操作业务类型
     */
    private Integer           bussinessType;

    /**
     * 调用唯一id
     */
    private String            invokeOnlyId;

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

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(OrderStockModel.class.getSimpleName());
        tostr.append("[");
        tostr.append("transactionId: ").append(transactionId).append(",");
        tostr.append("productId: ").append(productId).append(",");
        tostr.append("stockId: ").append(stockId).append(",");
        tostr.append("stockNum: ").append(stockNum).append(",");
        tostr.append("userId: ").append(userId).append(",");
        tostr.append("bussinessType: ").append(bussinessType).append(",");
        tostr.append("invokeOnlyId: ").append(invokeOnlyId).append(",");
        tostr.append("]");
        return tostr.toString().intern();
    }

}
