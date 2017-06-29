package com.pzj.core.product.model.seat;

public class TheaterSeatChartRespModel implements java.io.Serializable {

	private static final long serialVersionUID = -1395598082664074472L;

	/**
	 * 座位id
	 */
	private Long seatId;
	/**
	 * 演艺id
	 */
	private Long scenicId;
	/**
	 * 区域id
	 */
	private Long areaId;
	/**
	 * 列
	 */
	private Integer column;
	/**
	 * 行
	 */
	private Integer row;
	/**
	 * 名称类型（0：默认名称；1：自定义名称）
	 */
	private Integer nameType;
	/**
	 * 自定义 的行名称
	 */
	private String lineName;
	/**
	 * 自定义的列名称
	 */
	private String columnName;
	/**
	 * 类型（1:是座位、2:不是座位、3:删除）
	 */
	private Integer type;

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
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

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getNameType() {
		return nameType;
	}

	public void setNameType(Integer nameType) {
		this.nameType = nameType;
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

}
