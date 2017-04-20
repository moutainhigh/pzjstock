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
 * @version $Id: StockSeatRelStateException.java, v 0.1 2016年8月6日 下午2:46:07 Administrator Exp $
 */
public class StockSeatRelStateException extends StockException {

	private static final long serialVersionUID = -3645400955991020791L;
	private final int errCode = StockExceptionCode.STOCK_SEAT_STATE_ERR_CODE;

	public StockSeatRelStateException(String message) {
		super(message);
	}

	/**
	 * @param error
	 * @param cause
	 */
	public StockSeatRelStateException(String message, Throwable cause) {
		super(message, cause);
	}

	public StockSeatRelStateException() {
		super(StockExceptionCode.STOCK_SEAT_STATE_ERR_MSG);
	}

	/**
	 * @param cause
	 */
	public StockSeatRelStateException(Throwable cause) {
		super(StockExceptionCode.STOCK_SEAT_STATE_ERR_MSG, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
