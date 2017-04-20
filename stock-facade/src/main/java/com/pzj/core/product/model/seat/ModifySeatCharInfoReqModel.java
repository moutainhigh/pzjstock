package com.pzj.core.product.model.seat;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-3-31.
 */
public class ModifySeatCharInfoReqModel implements Serializable {
    /**
     * 座位id
     */
    private Long seatId;

    /**
     * 区域id
     */
    private Long areaId;

    /**
     * 名称类型
     * 0：默认
     * 1：自定义
     */
    private Integer nameType;

    /**
     * 行号名称
     */
    private String lineName;

    /**
     * 列号名称
     */
    private String columnName;

    /**
     * 座位格子类型
     * 1：是区域的格子（属于区域，必须要有区域id）
     * 2：没有区域的格子（没有归于任何区域）
     * 3：删除的格子
     */
    private Integer seatType;

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getSeatType() {
        return seatType;
    }

    public void setSeatType(Integer seatType) {
        this.seatType = seatType;
    }

    public Integer getNameType() {
        return nameType;
    }

    public void setNameType(Integer nameType) {
        this.nameType = nameType;
    }
}
