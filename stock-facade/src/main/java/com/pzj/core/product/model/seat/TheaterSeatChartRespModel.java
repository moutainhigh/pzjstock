package com.pzj.core.product.model.seat;

public class TheaterSeatChartRespModel implements java.io.Serializable {

	private static final long serialVersionUID = -1395598082664074472L;

	/**
	 * 座位id
	 */
	private Long seatId;
	/**
	 * 区域id
	 */
	private Long areaId;
	/**
	 * 座位号（在同一个区域下唯一）;空区域：1，空被删区域：2；其他默认有组织好的座位号例如：A1_1
	 */
	private String seatNum;
	/**
	 * 座位名称,根据区域对应的行号和列号来组合而成（行列可编辑）
	 */
	private String seatName;
	/**
	 * 列
	 */
	private Integer column;
	/**
	 * 行
	 */
	private Integer row;
	/**
	 * 1：可售；2：锁定（此处锁定是座位图的锁定）
	 */
	private Integer saleState;

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

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
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

	public Integer getSaleState() {
		return saleState;
	}

	public void setSaleState(Integer saleState) {
		this.saleState = saleState;
	}

}
