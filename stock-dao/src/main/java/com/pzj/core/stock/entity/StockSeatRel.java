package com.pzj.core.stock.entity;

import java.io.Serializable;
import java.util.Date;

public class StockSeatRel implements Serializable {

    private static final long serialVersionUID = 4099363529682231385L;

    /** 库存座位主键id */
    private Long              id;
    /** 库存主键id */
    private Long              stockId;
    /** 状态（1 正常 2 取消默认1） */
    private Integer           state;
    /** 座位号 */
    private String            seatNum;
    /** 交易id */
    private String            transactionId;
    /** 产品id */
    private Long              productId;
    /** 占座用户id */
    private Long              operateUserId;
    /** 创建时间 */
    private Date              createTime;
    /** 修改时间 */
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
        return "StockSeatRel [id=" + id + ", stockId=" + stockId + ", state=" + state + ", seatNum=" + seatNum + ", transactionId=" + transactionId
               + ", productId=" + productId + ", operateUserId=" + operateUserId + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
    }

}