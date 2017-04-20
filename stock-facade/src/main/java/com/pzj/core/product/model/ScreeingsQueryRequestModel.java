/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.model;

import java.io.Serializable;

/**
 * 演绎场次查询参数对象.
 * 
 * @author dongchunfu
 * @version $Id: ScreeingsQueryRequestBean.java, v 0.1 2016年8月1日 下午2:46:33 dongchunfu Exp $
 */
public class ScreeingsQueryRequestModel implements Serializable {
    private static final long serialVersionUID = 6497818068012091624L;
    /**
     * 演绎场次ID
     */
    private Long              screeingsId;
    /**
     * 景区ID
     */
    private Long              scenicId;
    /**
     *  场次标识
     */
    private String            code;
    /**
     * 场次名称
     */
    private String            name;
    /**
     * 演出开始时间
     */
    private String            startTime;

    public ScreeingsQueryRequestModel() {
        super();
    }

    public Long getScreeingsId() {
        return screeingsId;
    }

    public void setScreeingsId(Long screeingsId) {
        this.screeingsId = screeingsId;
    }

    public Long getScenicId() {
        return scenicId;
    }

    public void setScenicId(Long scenicId) {
        this.scenicId = scenicId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(ScreeingsQueryRequestModel.class.getSimpleName());
        tostr.append("[");
        tostr.append("screeingsId: ").append(screeingsId).append(",");
        tostr.append("scenicId: ").append(scenicId).append(",");
        tostr.append("code: ").append(code).append(",");
        tostr.append("name: ").append(name).append(",");
        tostr.append("startTime: ").append(startTime).append(",");
        tostr.append("]");
        return tostr.toString().intern();
    }

}
