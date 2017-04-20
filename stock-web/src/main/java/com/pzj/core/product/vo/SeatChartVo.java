/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.vo;

/**
 * 
 * @author Administrator
 * @version $Id: SeatChartVo.java, v 0.1 2016年9月2日 下午4:45:45 Administrator Exp $
 */
public class SeatChartVo implements java.io.Serializable {

    private static final long serialVersionUID = 5070138239353689015L;

    private String            scenicName;

    private Long              areaId;

    private String            seatChart;

    private String            sort;

    private String            code;

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getSeatChart() {
        return seatChart;
    }

    public void setSeatChart(String seatChart) {
        this.seatChart = seatChart;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
