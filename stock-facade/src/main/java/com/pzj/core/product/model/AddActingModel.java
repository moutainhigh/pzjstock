/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.model;

import java.util.List;

/**
 * 
 * @author Administrator
 * @version $Id: CreateActingModel.java, v 0.1 2016年9月1日 下午4:23:53 Administrator Exp $
 */
public class AddActingModel implements java.io.Serializable {

    private static final long    serialVersionUID = -451959267935761420L;

    private Long                 supplierId;
    private Long                 scenicId;
    private String               actingName;
    private Integer              whetherSeat;
    private List<AreaModel>      areaModelList;
    private List<ScreeingsModel> screeingsModelList;

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

    public String getActingName() {
        return actingName;
    }

    public void setActingName(String actingName) {
        this.actingName = actingName;
    }

    public Integer getWhetherSeat() {
        return whetherSeat;
    }

    public void setWhetherSeat(Integer whetherSeat) {
        this.whetherSeat = whetherSeat;
    }

    public List<AreaModel> getAreaModelList() {
        return areaModelList;
    }

    public void setAreaModelList(List<AreaModel> areaModelList) {
        this.areaModelList = areaModelList;
    }

    public List<ScreeingsModel> getScreeingsModelList() {
        return screeingsModelList;
    }

    public void setScreeingsModelList(List<ScreeingsModel> screeingsModelList) {
        this.screeingsModelList = screeingsModelList;
    }

}
