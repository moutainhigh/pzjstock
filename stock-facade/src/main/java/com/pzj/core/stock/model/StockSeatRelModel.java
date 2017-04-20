/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 库存与座位关联VO
 * 
 * @author dongchunfu
 * @version $Id: StockSeatRelVO.java, v 0.1 2016年8月2日 上午11:51:49 dongchunfu Exp $
 */
public class StockSeatRelModel implements Serializable {

    private static final long serialVersionUID = -3736718023895814550L;

    /**
     * 库存座位主键id 
     */
    private Long              id;
    /**
     * 库存主键id 
     */
    private Long              stockId;
    /**
     * 状态（1 正常 2 取消默认1） 
     */
    private Integer           state;
    /**
     * 座位号 
     */
    private String            seatNum;
    /**
     * 交易id 
     */
    private String            transactionId;
    /**
     * 占座用户id 
     */
    private Long              operateUserId;
    /**
     * 创建时间 
     */
    private Date              createTime;
    /**
     * 修改时间 
     */
    private Date              updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum == null ? null : seatNum.trim();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    public Long getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Long operateUserId) {
        this.operateUserId = operateUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(StockSeatRelModel.class.getSimpleName());
        tostr.append("[");
        tostr.append("id: ").append(id).append(",");
        tostr.append("stockId: ").append(stockId).append(",");
        tostr.append("state: ").append(state).append(",");
        tostr.append("seatNum: ").append(seatNum).append(",");
        tostr.append("transactionId: ").append(transactionId).append(",");
        tostr.append("operateUserId: ").append(operateUserId).append(",");
        tostr.append("createTime: ").append(createTime).append(",");
        tostr.append("updateTime").append(updateTime).append(",");
        tostr.append("]");
        return tostr.toString().intern();
    }

}