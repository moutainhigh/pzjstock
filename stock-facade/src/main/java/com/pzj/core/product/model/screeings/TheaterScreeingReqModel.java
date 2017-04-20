package com.pzj.core.product.model.screeings;

import com.pzj.framework.entity.PageableRequestBean;

public class TheaterScreeingReqModel extends PageableRequestBean implements java.io.Serializable {

	private static final long serialVersionUID = -8670762215994592786L;

	private Long supplierId;

	public TheaterScreeingReqModel() {

	}

	public TheaterScreeingReqModel(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

}
