package com.pzj.core.product.model.area;

import java.util.List;

public class DelAreasReqModel implements java.io.Serializable {

	private static final long serialVersionUID = 6904315067069242735L;
	/**
	 * 区域id集合
	 */
	private List<Long> areaIds;

	public List<Long> getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(List<Long> areaIds) {
		this.areaIds = areaIds;
	}

}
