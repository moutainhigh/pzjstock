package com.pzj.core.product.model.area;

public class TheaterAreaReqModel implements java.io.Serializable {

	private static final long serialVersionUID = 3762812833817437727L;
	/**
	 * 供应商id
	 */
	private Long supplierId;
	/**
	 * 剧场id
	 */
	private Long theaterId;

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Long getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(Long theaterId) {
		this.theaterId = theaterId;
	}

}
