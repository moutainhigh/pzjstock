package com.pzj.core.product.model.screeings;

import java.util.Date;

import com.pzj.framework.entity.PageableRequestBean;

public class TheaterScreeingOrderReqModel extends PageableRequestBean implements java.io.Serializable {

	private static final long serialVersionUID = -1120546960730836736L;

	/**
	 * 供应商id
	 */
	private Long supplierId;

	/**
	 * 演艺时间
	 */
	private Date showTime;

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Date getShowTime() {
		return showTime;
	}

	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}

}
