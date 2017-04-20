/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.util.Date;
import java.util.List;

public class ShowModel implements java.io.Serializable {

    private static final long   serialVersionUID = 2222307442023431415L;

    /**
     * 操作的业务 1、下单占座 2、预约占座 3、取消下单释放座位 4、退票释放座位 5、预约释放座位
     */
    private Integer             operateBusiness;

    /**
     * 交易唯一id或预约座位主键id
     */
    private String              transactionId;

    /**
     * 产品id
     */
    private Long                productId;

    /**
     * 座位号用逗号分隔
     */
    private List<String>        seats;

    /**
     * 随机分配座位数量
     */
    private Integer             randomNum;

    /**
     * 操作用户id
     */
    private Long                userId;

    /**
     * 库存id
     */
    private Long                stockId;

    /**
     * 场次区域中间表id
     */
    private Long                areaScreeingsId;

    /**
     * 演出时间
     */
    private Date                showTime;

    /**
     * 供应商id
     */
    private Long                supplierId;

    /**
     * 景区id
     */
    private Long                scenicId;

    /**
     * 场次id
     */
    private Long                screeningsId;

    /**
     * 区域id
     */
    private Long                areaId;
    /**
     * 是否调用线下占座释放座位接口 1、调用 2、不调用 
     */
    private Integer             isCallOffline;
    /** 1、调用 */
    public static final Integer CALL_OFFLINE_YES = 1;
    /** 2、不调用 */
    public static final Integer CALL_OFFLINE_NO  = 2;

    public ShowModel() {
    }

    /**
     * 初始化ShowModel操作座位模型
     * @param seats
     * @param stockId
     * @param transactionId
     * @param operateBusiness
     * @param productId
     * @param userId
     */
    public ShowModel(List<String> seats, Long stockId, String transactionId, Integer operateBusiness, Long productId, Long userId) {
        this.seats = seats;
        this.stockId = stockId;
        this.transactionId = transactionId;
        this.operateBusiness = operateBusiness;
        this.productId = productId;
        this.userId = userId;
    }

    public Integer getOperateBusiness() {
        return operateBusiness;
    }

    public void setOperateBusiness(Integer operateBusiness) {
        this.operateBusiness = operateBusiness;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getScenicId() {
        return scenicId;
    }

    public void setScenicId(Long scenicId) {
        this.scenicId = scenicId;
    }

    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
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

    public Long getScreeningsId() {
        return screeningsId;
    }

    public void setScreeningsId(Long screeningsId) {
        this.screeningsId = screeningsId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getAreaScreeingsId() {
        return areaScreeingsId;
    }

    public void setAreaScreeingsId(Long areaScreeingsId) {
        this.areaScreeingsId = areaScreeingsId;
    }

    public Integer getIsCallOffline() {
        return isCallOffline;
    }

    public void setIsCallOffline(Integer isCallOffline) {
        this.isCallOffline = isCallOffline;
    }

    @Override
    public String toString() {
        return "ShowModel [operateBusiness=" + operateBusiness + ", transactionId=" + transactionId + ", productId=" + productId + ", seats=" + seats
               + ", randomNum=" + randomNum + ", userId=" + userId + ", stockId=" + stockId + ", areaScreeingsId=" + areaScreeingsId + ", showTime=" + showTime
               + ", supplierId=" + supplierId + ", scenicId=" + scenicId + ", screeningsId=" + screeningsId + ", areaId=" + areaId + ", isCallOffline="
               + isCallOffline + "]";
    }

}