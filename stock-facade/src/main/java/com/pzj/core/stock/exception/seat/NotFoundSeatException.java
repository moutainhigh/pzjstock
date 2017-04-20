/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.exception.seat;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.SeatExceptionCode;

/**
 * 
 * @author Administrator
 * @version $Id: NotFoundSeatException.java, v 0.1 2016年8月9日 下午4:45:25 Administrator Exp $
 */
public class NotFoundSeatException extends StockException {

	private static final long serialVersionUID = 8796871636675914665L;
	private final int errCode = SeatExceptionCode.NOT_FOUND_SEAT_ERR_CODE;

	public NotFoundSeatException() {
		super(SeatExceptionCode.NOT_FOUND_SEAT_ERR_MSG);
	}

	/**
	 * @param error
	 * @param cause
	 */
	public NotFoundSeatException(Throwable cause) {
		super(SeatExceptionCode.NOT_FOUND_SEAT_ERR_MSG, cause);
	}

	public NotFoundSeatException(String message) {
		super(message);
	}

	/**
	 * @param error
	 * @param cause
	 */
	public NotFoundSeatException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
