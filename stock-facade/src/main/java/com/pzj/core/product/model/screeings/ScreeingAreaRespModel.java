package com.pzj.core.product.model.screeings;

import java.util.List;

import com.pzj.core.product.model.area.AreaModel;

public class ScreeingAreaRespModel implements java.io.Serializable {

	private static final long serialVersionUID = -1439615613159788855L;

	private List<ScreeingsModel> screeings;

	private List<AreaModel> areas;

	public List<ScreeingsModel> getScreeings() {
		return screeings;
	}

	public void setScreeings(List<ScreeingsModel> screeings) {
		this.screeings = screeings;
	}

	public List<AreaModel> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaModel> areas) {
		this.areas = areas;
	}

}
