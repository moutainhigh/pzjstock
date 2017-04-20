package com.pzj.core.product.model.screeings;

import java.util.List;

public class ScreeingAreaReqModel implements java.io.Serializable {

	private static final long serialVersionUID = -443666266327409354L;
	/**
	 * 场次id集合
	 */
	private List<Long> screeingIds;
	/**
	 * 区域id集合
	 */
	private List<Long> areaIds;

	public List<Long> getScreeingIds() {
		return screeingIds;
	}

	public void setScreeingIds(List<Long> screeingIds) {
		this.screeingIds = screeingIds;
	}

	public List<Long> getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(List<Long> areaIds) {
		this.areaIds = areaIds;
	}

}
