package com.pzj.core.product.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Screeings implements Serializable {

    private static final long serialVersionUID = 6126137696744312135L;

    /** 场次主键id */
    private Long              id;
    /** 场次标识 */
    private String            code;
    /** 场次名称 */
    private String            name;
    /** 景区id */
    private Long              scenicId;
    /** 演出开始时间 */
    private Integer           startTime;
    /** 演出结束时间 */
    private Integer           endTime;
    /** 演出停售时间 */
    private Integer           endSaleTime;
    /** 创建时间 */
    private Date              createTime;
    /** 修改时间 */
    private Date              updateTime;

    private List<String>      screeingsNameList;

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

    public Long getScenicId() {
        return scenicId;
    }

    public void setScenicId(Long scenicId) {
        this.scenicId = scenicId;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getEndSaleTime() {
        return endSaleTime;
    }

    public void setEndSaleTime(Integer endSaleTime) {
        this.endSaleTime = endSaleTime;
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

    public List<String> getScreeingsNameList() {
        return screeingsNameList;
    }

    public void setScreeingsNameList(List<String> screeingsNameList) {
        this.screeingsNameList = screeingsNameList;
    }

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(Screeings.class.getSimpleName());
        tostr.append("[");
        tostr.append("id: ").append(id).append(",");
        tostr.append("code: ").append(code).append(",");
        tostr.append("name: ").append(name).append(",");
        tostr.append("scenicId: ").append(scenicId).append(",");
        tostr.append("startTime: ").append(startTime).append(",");
        tostr.append("endTime: ").append(endTime).append(",");
        tostr.append("endSaleTime: ").append(endSaleTime).append(",");
        tostr.append("createTime: ").append(createTime).append(",");
        tostr.append("updateTime: ").append(updateTime).append(",");
        tostr.append("]");
        return tostr.toString().intern();
    }
}