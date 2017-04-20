package com.pzj.core.product.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Area implements Serializable {

    private static final long serialVersionUID = -5581928289613845035L;

    /**
     * 区域主键id
     */
    private Long              id;
    /**
     * 景区id
     */
    private Long              scenicId;
    /**
     * 区域标识 
     */
    private String            code;
    /**
     * 区域名称 
     */
    private String            name;
    /**
     * 创建时间 
     */
    private Date              createTime;
    /**
     * 修改时间 
     */
    private Date              updateTime;

    private List<String>      areaNameList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getScenicId() {
        return scenicId;
    }

    public void setScenicId(Long scenicId) {
        this.scenicId = scenicId;
    }

    public List<String> getAreaNameList() {
        return areaNameList;
    }

    public void setAreaNameList(List<String> areaNameList) {
        this.areaNameList = areaNameList;
    }

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(Area.class.getSimpleName());
        tostr.append("[");
        tostr.append("id: ").append(id).append(",");
        tostr.append("scenicId: ").append(scenicId).append(",");
        tostr.append("code: ").append(code).append(",");
        tostr.append("name: ").append(name).append(",");
        tostr.append("createTime: ").append(createTime).append(",");
        tostr.append("updateTime: ").append(updateTime).append(",");
        tostr.append("]");
        return tostr.toString().intern();
    }
}