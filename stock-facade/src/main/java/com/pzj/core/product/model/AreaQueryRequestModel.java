/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.model;

import java.io.Serializable;

/**
 * 演绎区域查询参数对象.
 * 
 * @author dongchunfu
 * @version $Id: AreaQueryRequestBean.java, v 0.1 2016年8月1日 上午10:45:15 dongchunfu Exp $
 */
public class AreaQueryRequestModel implements Serializable {

    private static final long serialVersionUID = 159366981940257726L;

    /**
     * 演绎区域ID
     */
    private Long              areaId;
    /**
     * 景区ID
     */
    private Long              scenicId;
    /**
     * 区域标识
     */
    private String            code;
    /**
     *  区域名称 
     */
    private String            name;

    public AreaQueryRequestModel() {
        super();
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
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

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(AreaQueryRequestModel.class.getSimpleName());
        tostr.append("[");
        tostr.append("areaId: ").append(areaId).append(",");
        tostr.append("scenicId: ").append(scenicId).append(",");
        tostr.append("code: ").append(code).append(",");
        tostr.append("name: ").append(name).append(",");
        tostr.append("]");
        return tostr.toString().intern();
    }
}
