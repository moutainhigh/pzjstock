package com.pzj.core.product.model.screeings;

import java.io.Serializable;

/**
 * 修改场次的请求
 * Created by Administrator on 2017-3-8.
 */
public class ModifyScreeningReqModel implements Serializable {
    /**
     * 场次id
     */
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
