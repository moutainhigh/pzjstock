package com.pzj.core.stock.entity;

import java.io.Serializable;
import java.util.Date;

public class SeatChart implements Serializable {

    private static final long serialVersionUID = 7922565292978513303L;

    /** 座位图主键id */
    private Long              id;
    /** 景区主键id */
    private Long              scenicId;
    /** 区域id */
    private Long              areaId;
    /** 总座位数 */
    private Integer           totalSeats;
    /** 座位图 */
    private String            seatMaps;
    /** 排序 */
    private Integer           sort;
    /** 座位图标识 */
    private String            code;
    /** 创建时间 */
    private Date              createTime;
    /** 修改时间 */
    private Date              updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScenicId() {
        return scenicId;
    }

    public void setScenicId(Long scenicId) {
        this.scenicId = scenicId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getSeatMaps() {
        return seatMaps;
    }

    public void setSeatMaps(String seatMaps) {
        this.seatMaps = seatMaps;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SeatChart [id=" + id + ", scenicId=" + scenicId + ", areaId=" + areaId + ", totalSeats=" + totalSeats + ", seatMaps=" + seatMaps + ", sort="
               + sort + ", code=" + code + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
    }

}