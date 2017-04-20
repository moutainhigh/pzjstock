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
 * @version $Id: LockSeatType.java, v 0.1 2017年3月10日 上午10:27:34 Administrator Exp $
 */
public enum LockSeatType {
	/**
	 * 锁座类型 1、供应商锁定，长期锁定 2、分销商锁定，临时锁定
	 */
	LONG_VALID(1), TMP_VALID(2);

	/** 状态值. */
	private int state;

	public int getState() {
		return state;
	}

	private LockSeatType(int state) {
		this.state = state;
	}

	/**
	* 根据状态获取不同对象
	* @param state
	* @return
	*/
	public static LockSeatType getLockSeatType(int state) {
		for (LockSeatType lockType : LockSeatType.values()) {
			if (lockType.getState() == state) {
				return lockType;
			}
		}
		throw new StateException(ActingExceptionCode.NOT_FOUND_STOCK_TYPE_ERR_MSG);
	}
}
