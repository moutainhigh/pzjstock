package com.pzj.core.product.model.assign;

import java.util.List;

public class CalendarAssignRespModel implements java.io.Serializable {

	private static final long serialVersionUID = -8468296283422706066L;
	/**
	 * 待分配的天
	 */
	private List<Integer> days;

	public List<Integer> getDays() {
		return days;
	}

	public void setDays(List<Integer> days) {
		this.days = days;
	}

}
