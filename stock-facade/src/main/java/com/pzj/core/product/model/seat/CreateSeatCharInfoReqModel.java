package com.pzj.core.product.model.seat;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-3-24.
 */
public class CreateSeatCharInfoReqModel implements Serializable {
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
     * 横坐标
     */
    private Integer abscissa;

    /**
     * 纵坐标
     */
    private Integer ordinate;

    /**
     * 座位格子类型
     * 1：是区域的格子（属于区域，必须要有区域id）
     * 2：没有区域的格子（没有归于任何区域）
     * 3：删除的格子
     */
    private Integer seatType;

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

    public Integer getAbscissa() {
        return abscissa;
    }

    public void setAbscissa(Integer abscissa) {
        this.abscissa = abscissa;
    }

    public Integer getOrdinate() {
        return ordinate;
    }

    public void setOrdinate(Integer ordinate) {
        this.ordinate = ordinate;
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
