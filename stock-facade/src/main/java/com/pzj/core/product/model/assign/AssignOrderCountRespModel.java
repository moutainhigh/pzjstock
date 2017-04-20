package com.pzj.core.product.model.assign;

import java.util.Date;

public class AssignOrderCountRespModel implements java.io.Serializable {

	private static final long serialVersionUID = -3223300067092277178L;
	/**
	 * 待分配订单数量
	 */
	private Integer orderNum;
	/**
	 * 待分配座位数量
	 */
	private Integer seatNum;
	/**
	 * 演出时间
	 */
	private Date showTime;

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}

	public Date getShowTime() {
		return showTime;
	}

	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}

}
