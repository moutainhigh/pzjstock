package com.pzj.core.product.model.assign;

public class CalendarAssignReqModel implements java.io.Serializable {

	private static final long serialVersionUID = -5278820015045818094L;
	/**
	 * 供应商id
	 */
	private Long supplierId;

	/**
	 * 查询的日历时间格式如:201703
	 */
	private Integer calendarDate;

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getCalendarDate() {
		return calendarDate;
	}

	public void setCalendarDate(Integer calendarDate) {
		this.calendarDate = calendarDate;
	}

}
