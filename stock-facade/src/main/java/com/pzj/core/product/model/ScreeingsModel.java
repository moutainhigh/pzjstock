package com.pzj.core.product.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 演绎场次VO
 * 
 * @author dongchunfu
 * @version $Id: ScreeingsModel.java, v 0.1 2016年8月2日 上午11:15:28 dongchunfu Exp $
 */
public class ScreeingsModel implements Serializable {

    private static final long serialVersionUID = -4131817106268427309L;

    /**
     * 场次主键id
     */
    private Long              id;
    /**
     * 场次标识
     */
    private String            code;
    /**
     * 场次名称
     */
    private String            name;
    /**
     * 景区id
     */
    private Long              scenicId;
    /**
     * 演出开始时间
     */
    private String            startTime;
    /**
     * 演出结束时间
     */
    private String            endTime;
    /**
     * 演出停售时间
     */
    private String            endSaleTime;
    /**
     * 创建时间
     */
    private Date              createTime;
    /**
     * 修改时间
     */
    private Date              updateTime;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndSaleTime() {
        return endSaleTime;
    }

    public void setEndSaleTime(String endSaleTime) {
        this.endSaleTime = endSaleTime;
    }

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(ScreeingsModel.class.getSimpleName());
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