package com.pzj.core.stock.model;

import java.util.List;

public class QueryStockByScreeingReqModel implements java.io.Serializable {

	private static final long serialVersionUID = -5904502614923982558L;
	/**
	 * 场次id集合
	 */
	private List<Long> screeings;

	public List<Long> getScreeings() {
		return screeings;
	}

	public void setScreeings(List<Long> screeings) {
		this.screeings = screeings;
	}

}
