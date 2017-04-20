package com.pzj.core.product.model.assign;

import java.util.List;

public class TheaterScreeingAssignModel implements java.io.Serializable {

	private static final long serialVersionUID = 8235085634053928544L;
	/**
	 * 供应商id
	 */
	private Long supplierId;
	/**
	 * 演艺id
	 */
	private Long spuId;
	/**
	 * 演艺待分配座位数
	 */
	private Integer spuAssignNum;
	/**
	 * 待分配的区域场次信息
	 */
	private List<ScreeingAreaAssignRespModel> assignAreaScreeings;

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

	public List<ScreeingAreaAssignRespModel> getAssignAreaScreeings() {
		return assignAreaScreeings;
	}

	public void setAssignAreaScreeings(List<ScreeingAreaAssignRespModel> assignAreaScreeings) {
		this.assignAreaScreeings = assignAreaScreeings;
	}

	public Integer getSpuAssignNum() {
		return spuAssignNum;
	}

	public void setSpuAssignNum(Integer spuAssignNum) {
		this.spuAssignNum = spuAssignNum;
	}

}
