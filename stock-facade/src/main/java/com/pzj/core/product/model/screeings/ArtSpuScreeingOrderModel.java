package com.pzj.core.product.model.screeings;

import java.util.List;

public class ArtSpuScreeingOrderModel implements java.io.Serializable {

	private static final long serialVersionUID = 9049082201015049485L;
	/**
	 * 供应商id
	 */
	private Long supplierId;
	/**
	 * 演艺SPUid
	 */
	private Long spuId;
	/**
	 * 演艺SPU名称
	 */
	private String spuName;
	/**
	 * 场次预定信息
	 */
	List<ScreeingOrderRespModel> screeingOrders;

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public String getSpuName() {
		return spuName;
	}

	public void setSpuName(String spuName) {
		this.spuName = spuName;
	}

	public List<ScreeingOrderRespModel> getScreeingOrders() {
		return screeingOrders;
	}

	public void setScreeingOrders(List<ScreeingOrderRespModel> screeingOrders) {
		this.screeingOrders = screeingOrders;
	}

}
