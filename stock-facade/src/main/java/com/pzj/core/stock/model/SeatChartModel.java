/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.model;

import java.io.Serializable;

/**
 * 座位图VO
 * 
 * @author dongchunfu
 * @version $Id: SeatChartVO.java, v 0.1 2016年8月2日 上午11:14:01 dongchunfu Exp $
 */
public class SeatChartModel implements Serializable {

    private static final long serialVersionUID = -7229036283732732918L;

    /**
     * 景区主键id 
     */
    private Long              scenicId;
    /**
     * 区域id
     */
    private Long              areaId;
    /**
     * 区域场次ID
     */
    private Long              areaScreeningsId;
    /**
     * 座位图 
     */
    private String            seatMaps;
    /**
     * 座位总数
     */
    private Integer           totalSeats;
    /**
     * 排序
     */
    private Integer           sort;
    /** 区域描述*/
    private String            code;

    public String getSeatMaps() {
        return seatMaps;
    }

    public void setSeatMaps(String seatMaps) {
        this.seatMaps = seatMaps;
    }

    public Long getScenicId() {
        return scenicId;
    }

    public void setScenicId(Long scenicId) {
        this.scenicId = scenicId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Long getAreaScreeningsId() {
        return areaScreeningsId;
    }

    public void setAreaScreeningsId(Long areaScreeningsId) {
        this.areaScreeningsId = areaScreeningsId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SeatChartModel [scenicId=" + scenicId + ", areaId=" + areaId + ", areaScreeningsId=" + areaScreeningsId + ", seatMaps=" + seatMaps
               + ", totalSeats=" + totalSeats + ", sort=" + sort + ", code=" + code + "]";
    }

}