package com.pzj.core.stock.model;

import java.util.Date;

public class UpdateAreaStockReqModel implements java.io.Serializable {
	private static final long serialVersionUID = -6797785721428915659L;
	/**
	 * 区域id
	 */
	private Long areaId;
	/**
	 * 场次id
	 */
	private Long screeingId;
	/**
	 * 演出时间
	 */
	private Date showTime;
	/**
	 * 最新库存数
	 */
	private Integer newestStockNum;

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Long getScreeingId() {
		return screeingId;
	}

	public void setScreeingId(Long screeingId) {
		this.screeingId = screeingId;
	}

	public Date getShowTime() {
		return showTime;
	}

	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}

	public Integer getNewestStockNum() {
		return newestStockNum;
	}

	public void setNewestStockNum(Integer newestStockNum) {
		this.newestStockNum = newestStockNum;
	}

}
