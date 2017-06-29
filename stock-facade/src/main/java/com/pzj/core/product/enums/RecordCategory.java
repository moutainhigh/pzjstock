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
 * @version $Id: RecordCategory.java, v 0.1 2017年3月16日 下午2:08:15 Administrator Exp $
 */
public enum RecordCategory {
	/**
	 * 座位类型  10、可选 20、已售 30、锁定 40、预选
	 */
	OPTIONAL(10), SOLD(20), LOCKING(30), RESERVED(40);
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

	private RecordCategory(int state) {
		this.state = state;
	}

	/**
	* 根据状态获取不同对象
	* @param state
	* @return
	*/
	public static RecordCategory getRecordCategory(int state) {
		for (RecordCategory recordCategory : RecordCategory.values()) {
			if (recordCategory.getState() == state) {
				return recordCategory;
			}
		}
		throw new StateException(ActingExceptionCode.NOT_FOUND_STOCK_TYPE_ERR_MSG);
	}
}
