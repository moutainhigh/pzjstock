package com.pzj.core.product.entity;

import java.io.Serializable;
import java.util.Date;

public class AreaScreeingsRel implements Cloneable, Serializable {

    private static final long serialVersionUID = -7394845975640796461L;

    /** 区域场次关联主键id */
    private Long              id;
    /** 演艺主键id */
    private Long              actingId;
    /** 区域主键id */
    private Long              areaId;
    /** 场次主键id */
    private Long              screeingsId;
    /** 状态（1 正常 2 停用，默认为1） */
    private Integer           state;

    /** 创建时间 */
    private Date              createTime;
    /** 修改时间 */
    private Date              updateTime;
    /**
     * 区域名称
     */
    private String            areaName;
    /**
     * 场次名称
     */
    private String            screeingsName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActingId() {
        return actingId;
    }

    public void setActingId(Long actingId) {
        this.actingId = actingId;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    @Override
    public AreaScreeingsRel clone() throws CloneNotSupportedException {
        return (AreaScreeingsRel) super.clone();
    }

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(AreaScreeingsRel.class.getSimpleName());
        tostr.append("[");
        tostr.append("id: ").append(id).append(",");
        tostr.append("actingId: ").append(actingId).append(",");
        tostr.append("areaId: ").append(areaId).append(",");
        tostr.append("areaName: ").append(areaName).append(",");
        tostr.append("screeingsId: ").append(screeingsId).append(",");
        tostr.append("screeingsName: ").append(screeingsName).append(",");
        tostr.append("state: ").append(state).append(",");
        tostr.append("createTime: ").append(createTime).append(",");
        tostr.append("updateTime: ").append(updateTime).append(",");
        tostr.append("]");
        return tostr.toString().intern();
    }

}