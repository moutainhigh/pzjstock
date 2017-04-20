/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.util.Date;

/**
 * 锁库记录VO
 * 
 * @author dongchunfu
 * @version $Id: LockRecordVO.java, v 0.1 2016年8月2日 上午11:15:52 dongchunfu Exp $
 */
public class LockRecordModel implements java.io.Serializable {

    private static final long serialVersionUID = 5181388261073608635L;

    /**
     * 锁库存记录主键id
     */
    private Long              id;
    /**
     * 库存主键id
     */
    private Long              stockId;
    /**
     * 交易id
     */
    private String            transactionId;
    /**
     * 产品id
     */
    private Long              productId;
    /**
     * 操作库存业务场景
     */
    private Integer           bussinessType;
    /**
     * 锁定库存数
     */
    private Integer           lockNum;
    /**
     * 操作库存用户id
     */
    private Long              operateUserId;
    /**
     * 创建时间
     */
    private Date              createTime;
    /**
     * 更新时间
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

    public Integer getBussinessType() {
        return bussinessType;
    }

    public void setBussinessType(Integer bussinessType) {
        this.bussinessType = bussinessType;
    }

    public Integer getLockNum() {
        return lockNum;
    }

    public void setLockNum(Integer lockNum) {
        this.lockNum = lockNum;
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
        StringBuilder tostr = new StringBuilder(LockRecordModel.class.getSimpleName());
        tostr.append("[");
        tostr.append("id: ").append(id).append(",");
        tostr.append("stockId: ").append(stockId).append(",");
        tostr.append("transactionId: ").append(transactionId).append(",");
        tostr.append("productId: ").append(productId).append(",");
        tostr.append("bussinessType: ").append(bussinessType).append(",");
        tostr.append("lockNum: ").append(lockNum).append(",");
        tostr.append("operateUserId: ").append(operateUserId).append(",");
        tostr.append("createTime: ").append(createTime).append(",");
        tostr.append("updateTime: ").append(updateTime).append(",");
        tostr.append("]");
        return tostr.toString().intern();
    }

}