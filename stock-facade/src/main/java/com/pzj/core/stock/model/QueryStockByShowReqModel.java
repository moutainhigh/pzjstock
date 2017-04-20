package com.pzj.core.stock.model;

import java.util.Date;

public class QueryStockByShowReqModel implements java.io.Serializable {

	private static final long serialVersionUID = 5404729580023533846L;
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
