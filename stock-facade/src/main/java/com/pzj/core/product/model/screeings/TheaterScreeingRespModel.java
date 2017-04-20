package com.pzj.core.product.model.screeings;

import java.util.List;

public class TheaterScreeingRespModel implements java.io.Serializable {

	private static final long serialVersionUID = -105639680263850606L;
	/**
	 * 供应商id
	 */
	private Long supplierId;
	/**
	 * 演艺剧场id
	 */
	private Long theaterId;
	/**
	 * 演艺剧场名称
	 */
	private String theaterName;
	/**
	 * 场次集合
	 */
	private List<ScreeingsModel> screeings;

	public Long getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(Long theaterId) {
		this.theaterId = theaterId;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public List<ScreeingsModel> getScreeings() {
		return screeings;
	}

	public void setScreeings(List<ScreeingsModel> screeings) {
		this.screeings = screeings;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

}
