package com.pzj.core.product.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017-3-30.
 */
public class QuerySeatDescResModel implements Serializable{
    /**
     * 座位id
     */
    private Long seatId;

    /**
     * 座位名称
     */
    private String seatName;

    /***
     * 场次id
     */
    private Long screeningsId;

    /**
     * 场次名称
     */
    private String screeningsName;

    /**
     * 区域id
     */
    private Long areaId;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 游玩时间
     */
    private Date travelDate;

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

    public Long getScreeningsId() {
        return screeningsId;
    }

    public void setScreeningsId(Long screeningsId) {
        this.screeningsId = screeningsId;
    }

    public String getScreeningsName() {
        return screeningsName;
    }

    public void setScreeningsName(String screeningsName) {
        this.screeningsName = screeningsName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }
}
