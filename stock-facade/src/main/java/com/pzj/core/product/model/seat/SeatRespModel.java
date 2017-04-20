package com.pzj.core.product.model.seat;

public class SeatRespModel implements java.io.Serializable {

	private static final long serialVersionUID = 457157332033394223L;
	/**
	 * 座位id
	 */
	private Long seatId;
	/**
	 * 区域id
	 */
	private Long areaId;
	/**
	 * 座位号;空区域：1；空被删区域：2；其他默认有组织好的座位号例如：A3_1_1
	 */
	private String seatName;
	/**
	 * 横坐标(初始化后不允许更改)
	 */
	private Integer xPos;
	/**
	 * 纵坐标(初始化后不允许更改)
	 */
	private Integer yPos;
	/**
	 * 1：可售；2：锁定（此处锁定是座位图的锁定）
	 */
	private Integer saleState;
	/**
	 * 1:可选；2：已选；3：锁定；4：预选
	 */
	private Integer showState;
	/**
	 * 交易id
	 */
	private String transactionId;

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	/**
	 * Getter method for property <tt>seatName</tt>.
	 * 
	 * @return property value of seatName
	 */
	public String getSeatName() {
		return seatName;
	}

	/**
	 * Setter method for property <tt>seatName</tt>.
	 * 
	 * @param seatName value to be assigned to property seatName
	 */
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

	public Integer getSaleState() {
		return saleState;
	}

	public void setSaleState(Integer saleState) {
		this.saleState = saleState;
	}

	public Integer getShowState() {
		return showState;
	}

	public void setShowState(Integer showState) {
		this.showState = showState;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	public Integer getxPos() {
		return xPos;
	}

	public void setxPos(Integer xPos) {
		this.xPos = xPos;
	}

	public Integer getyPos() {
		return yPos;
	}

	public void setyPos(Integer yPos) {
		this.yPos = yPos;
	}

	public Long getSeatId() {
		return seatId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
