package com.pzj.core.product.model.screeings;

import java.io.Serializable;

/**
 * 创建场次的请求
 * Created by Administrator on 2017-3-8.
 */
public class CreateScreeningsReqModel implements Serializable {
    /**
     * 剧场id
     */
    private Long theaterId;

    /**
     * 供应商id
     */
    private Long supplierId;

    /**
     * 名称
     */
    private String name;

    /**
     * 简称
     */
    private String nameAbbr;

    /**
     * 开始时间，只有小时分钞，格式为HH:mm:ss
     */
    private String beginTime;

    /**
     * 结束时间，只有小时分钞，格式为HH:mm:ss
     */
    private String endTime;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAbbr() {
        return nameAbbr;
    }

    public void setNameAbbr(String nameAbbr) {
        this.nameAbbr = nameAbbr;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Long theaterId) {
        this.theaterId = theaterId;
    }
}
