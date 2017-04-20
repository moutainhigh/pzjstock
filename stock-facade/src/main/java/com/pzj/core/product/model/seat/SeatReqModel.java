package com.pzj.core.product.model.seat;

import java.util.Date;

public class SeatReqModel implements java.io.Serializable {

	private static final long serialVersionUID = 84322958941428759L;
	/**
	 * 景区id
	 */
	private Long scenicId;
	/**
	 * 场次id
	 */
	private Long screeingId;
	/**
	 * 区域id
	 */
	private Long areaId;
	/**
	 * 演出时间
	 */
	private Date showTime;

	public Long getScenicId() {
		return scenicId;
	}

	public void setScenicId(Long scenicId) {
		this.scenicId = scenicId;
	}

	public Long getScreeingId() {
		return screeingId;
	}

	public void setScreeingId(Long screeingId) {
		this.screeingId = screeingId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Date getShowTime() {
		return showTime;
	}

	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}

}
