package com.pzj.core.stock.entity;

import java.util.Date;

public class LockRecord implements java.io.Serializable {

    private static final long serialVersionUID = -6549459758044236847L;

    /** 锁库存记录主键id */
    private Long              id;
    /** 库存主键id */
    private Long              stockId;
    /** 交易id */
    private String            transactionId;
    /** 产品id */
    private Long              productId;
    /** 操作库存业务场景 */
    private Integer           bizType;
    /** 锁定库存数 */
    private Integer           lockNum;
    /** 操作库存用户id */
    private Long              operatorId;
    /** 创建时间 */
    private Date              createTime;
    /** 更新时间 */
    private Date              updateTime;

    /** 历史锁定库存数 */
    private Integer           historyLockNum;

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

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public Integer getLockNum() {
        return lockNum;
    }

    public void setLockNum(Integer lockNum) {
        this.lockNum = lockNum;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
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

    /**
     * Getter method for property <tt>historyLockNum</tt>.
     * 
     * @return property value of historyLockNum
     */
    public Integer getHistoryLockNum() {
        return historyLockNum;
    }

    /**
     * Setter method for property <tt>historyLockNum</tt>.
     * 
     * @param historyLockNum value to be assigned to property historyLockNum
     */
    public void setHistoryLockNum(Integer historyLockNum) {
        this.historyLockNum = historyLockNum;
    }

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(LockRecord.class.getSimpleName());
        tostr.append("[");
        tostr.append("id: ").append(id).append(",");
        tostr.append("stockId: ").append(stockId).append(",");
        tostr.append("transactionId: ").append(transactionId).append(",");
        tostr.append("productId: ").append(productId).append(",");
        tostr.append("bizType: ").append(bizType).append(",");
        tostr.append("lockNum: ").append(lockNum).append(",");
        tostr.append("operatorId: ").append(operatorId).append(",");
        tostr.append("createTime: ").append(createTime).append(",");
        tostr.append("updateTime: ").append(updateTime).append(",");
        tostr.append("]");
        return tostr.toString().intern();
    }

}