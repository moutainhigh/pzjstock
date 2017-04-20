package com.pzj.core.stock.entity;

import java.io.Serializable;
import java.util.Date;

public class Seat implements Serializable {

    private static final long serialVersionUID = -5772024363103040026L;

    /** 座位表主键id */
    private Long              id;
    /** 座位图主键id */
    private Long              stockSeatChartId;
    /** 座位号 */
    private String            seatNum;
    /** 创建时间 */
    private Date              createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStockSeatChartId() {
        return stockSeatChartId;
    }

    public void setStockSeatChartId(Long stockSeatChartId) {
        this.stockSeatChartId = stockSeatChartId;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum == null ? null : seatNum.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(Seat.class.getSimpleName());
        tostr.append("[");
        tostr.append("id: ").append(id).append(",");
        tostr.append("stockSeatChartId: ").append(stockSeatChartId).append(",");
        tostr.append("seatNum: ").append(seatNum).append(",");
        tostr.append("createTime: ").append(createTime).append(",");
        tostr.append("]");
        return tostr.toString().intern();
    }
}