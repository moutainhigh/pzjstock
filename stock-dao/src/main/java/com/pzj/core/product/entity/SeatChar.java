package com.pzj.core.product.entity;

import java.util.Date;

public class SeatChar implements java.io.Serializable {

	private static final long serialVersionUID = 1068385380543045025L;
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 景区id
	 */
	private Long scenicId;
	/**
	 * 剧场id+横坐标+纵坐标的唯一值
	 */
	private Long seatUnique;
	/**
	 * 区域id
	 */
	private Long areaId;
	/**
	 * 横坐标
	 */
	private Integer abscissa;
	/**
	 * ordinate
	 */
	private Integer ordinate;
	/**
	 * 名称类型
	 */
	private Integer nameType;
	/**
	 * 座位名词
	 */
	private String seatName;
	/**
	 * 行名称
	 */
	private String lineName;
	/**
	 * 列名称
	 */
	private String columnName;
	/**
	 * 类型（1:是座位、2:不是座位、3:删除）
	 */
	private Integer type;

	private Date createTime;

	private Date updateTime;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSeatName() {
		if (seatName == null) {
			if (lineName != null && columnName != null) {
				seatName = lineName + "_" + columnName;
			}
		}
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
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

	public Integer getNameType() {
		return nameType;
	}

	public void setNameType(Integer nameType) {
		this.nameType = nameType;
	}

	public Long getSeatUnique() {
		return seatUnique;
	}

	public void setSeatUnique(Long seatUnique) {
		this.seatUnique = seatUnique;
	}
}
