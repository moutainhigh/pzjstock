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
 * @version $Id: RecordState.java, v 0.1 2017年3月21日 下午3:24:43 Administrator Exp $
 */
public enum RecordState {
	/**
	 * 占座记录状态  0、无效 1、有效
	 */
	INVALID(0), EFFECTIVER(1);
	/** 状态值. */
	private int state;

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

	private RecordState(int state) {
		this.state = state;
	}

	/**
	* 根据状态获取不同对象
	* @param state
	* @return
	*/
	public static RecordState getRecordState(int state) {
		for (RecordState recordState : RecordState.values()) {
			if (recordState.getState() == state) {
				return recordState;
			}
		}
		throw new StateException(ActingExceptionCode.NOT_FOUND_STOCK_TYPE_ERR_MSG);
	}
}
