/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.enums;

import com.pzj.core.product.exception.StateException;
import com.pzj.core.product.exception.errorcode.ActingExceptionCode;

/**
 * 
 * @author Administrator
 * @version $Id: SeatType.java, v 0.1 2017年3月28日 下午2:27:14 Administrator Exp $
 */
public enum SeatType {
	IS_SEAT(1), NOT_SEAT(2), REMOVE_SEAT(3);
	/** 状态值. */
	private int state;

	private SeatType(int state) {
		this.state = state;
	}

	/**
	 * Getter method for property <tt>state</tt>.
	 * 
	 * @return property value of state
	 */
	public int getState() {
		return state;
	}

	/**
	 * Setter method for property <tt>state</tt>.
	 * 
	 * @param state value to be assigned to property state
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	* 根据状态获取不同对象
	* @param state
	* @return
	*/
	public static SeatType getSeatType(int state) {
		for (SeatType seatType : SeatType.values()) {
			if (seatType.getState() == state) {
				return seatType;
			}
		}
		throw new StateException(ActingExceptionCode.NOT_FOUND_STOCK_TYPE_ERR_MSG);
	}

}
