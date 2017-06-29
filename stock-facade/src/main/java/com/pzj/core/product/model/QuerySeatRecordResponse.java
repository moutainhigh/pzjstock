package com.pzj.core.product.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017-3-29.
 */
public class QuerySeatRecordResponse implements Serializable {
    /**
     * 主键id
     */
    private Long recordId;
    /**
     * 场次id
     */
    private Long screeningId;
    /**
     * 场次名称
     */
    private String screeningName;
    /**
     * 区域id
     */
    private Long areaId;
    /**
     * 区域名称
     */
    private String areaName;
    /**
     * 交易id
     */
    private String transactionId;
    /**
     * 座位id
     */
    private Long seatId;
    /**
     * 座位名称
     */
    private String seatName;
    /**
     * 占座用户id
     */
    private Long operatorId;
    /**
     * 类别 10.待选 20.已占 30.锁定40.预选
     */
    private Integer category;
    /**
     * 游玩日期
     */
    private Date travelDate;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Long screeningId) {
        this.screeningId = screeningId;
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

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public String getScreeningName() {
        return screeningName;
    }

    public void setScreeningName(String screeningName) {
        this.screeningName = screeningName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
