package com.pzj.core.product.model.assign;

public class ScreeingAreaAssignRespModel implements java.io.Serializable {

	private static final long serialVersionUID = 4129914624653431538L;
	/**
	 * 场次名称
	 */
	private String screeingName;
	/**
	 * 区域名称
	 */
	private String areaName;
	/**
	 * 场次id
	 */
	private Long screeingId;
	/**
	 * 区域id
	 */
	private Long areaId;
	/**
	 * 待分配座位数量
	 */
	private Integer assignNum;

	public String getScreeingName() {
		return screeingName;
	}

	public void setScreeingName(String screeingName) {
		this.screeingName = screeingName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public Integer getAssignNum() {
		return assignNum;
	}

	public void setAssignNum(Integer assignNum) {
		this.assignNum = assignNum;
	}

}
