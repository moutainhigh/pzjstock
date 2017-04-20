package com.pzj.core.product.model;

import java.io.Serializable;
import java.util.ArrayList;

public class AreaScreeingsRelModel implements Cloneable, Serializable {

    private static final long         serialVersionUID = -7394845975640796461L;
    /**
     * 关系id
     */
    private Long                      id;
    /**
     * 演艺主键id
     */
    private Long                      actingId;
    /**
     * 区域主键id
     */
    private Long                      areaId;
    /**
     * 区域名称
     */
    private String                    areaName;
    /**
     * 场次主键id
     */
    private Long                      screeingsId;
    /**
     * 场次名称
     */
    private String                    screeingsName;
    /**
     * 景区id
     */
    private Long                      scenicId;
    /**
     * 供应商id
     */
    private Long                      supplierId;

    /**
     * 区域主键id集合
     */
    private ArrayList<AreaModel>      areas;
    /**
     * 场次主键id集合
     */
    private ArrayList<ScreeingsModel> screeings;

    public Long getActingId() {
        return actingId;
    }

    public void setActingId(Long actingId) {
        this.actingId = actingId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getScreeingsId() {
        return screeingsId;
    }

    public void setScreeingsId(Long screeingsId) {
        this.screeingsId = screeingsId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getScreeingsName() {
        return screeingsName;
    }

    public void setScreeingsName(String screeingsName) {
        this.screeingsName = screeingsName;
    }

    public ArrayList<AreaModel> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<AreaModel> areas) {
        this.areas = areas;
    }

    public ArrayList<ScreeingsModel> getScreeings() {
        return screeings;
    }

    public void setScreeings(ArrayList<ScreeingsModel> screeings) {
        this.screeings = screeings;
    }

    public Long getScenicId() {
        return scenicId;
    }

    public void setScenicId(Long scenicId) {
        this.scenicId = scenicId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "AreaScreeingsRelModel [id=" + id + ", actingId=" + actingId + ", areaId=" + areaId + ", areaName=" + areaName + ", screeingsId=" + screeingsId
               + ", screeingsName=" + screeingsName + ", scenicId=" + scenicId + ", supplierId=" + supplierId + ", areas=" + areas + ", screeings=" + screeings
               + "]";
    }

}