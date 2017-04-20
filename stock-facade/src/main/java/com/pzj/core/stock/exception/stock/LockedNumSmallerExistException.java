/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.exception.stock;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;

/**
 * 
 * @author Administrator
 * @version $Id: LockedNumSmallerExistException.java, v 0.1 2016年10月20日 上午9:51:27 Administrator Exp $
 */
public class LockedNumSmallerExistException extends StockException {
	private static final long serialVersionUID = 8998911411004917643L;
	private final int errCode = StockExceptionCode.LOCKNUM_SMALLER_EXISTLOCK_STOCK_ERR_CODE;

	/**
	 * @param message
	 */
	public LockedNumSmallerExistException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LockedNumSmallerExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public LockedNumSmallerExistException() {
		super(StockExceptionCode.LOCKNUM_SMALLER_EXISTLOCK_STOCK_ERR_MSG);
	}

	/**
	 * @param cause
	 */
	public LockedNumSmallerExistException(Throwable cause) {
		super(StockExceptionCode.LOCKNUM_SMALLER_EXISTLOCK_STOCK_ERR_MSG, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
