package com.pzj.core.product.enums;

public enum ShowSeatStateEnum {
	SELECTABLE(1, "可选"), SELECTED(2, "已选"), LOCKING(3, "锁定"), RESERVE(4, "预选");

	private Integer showSeatState;
	private String desc;

	private ShowSeatStateEnum(Integer showSeatState, String desc) {
		this.showSeatState = showSeatState;
		this.desc = desc;
	}

	public Integer getShowSeatState() {
		return showSeatState;
	}

	public String getDesc() {
		return desc;
	}

}
