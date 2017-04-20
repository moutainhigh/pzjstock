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
 * @version $Id: ReleaseSeatNotFoundException.java, v 0.1 2016年8月8日 下午5:16:57 Administrator Exp $
 */
public class ReleaseSeatNotFoundException extends StockException {

	private static final long serialVersionUID = 3432504240924193212L;
	private final int errCode = SeatExceptionCode.NOT_FOUND_RELEASE_SEAT_ERR_CODE;

	public ReleaseSeatNotFoundException() {
		super(SeatExceptionCode.NOT_FOUND_RELEASE_SEAT_ERR_MSG);
	}

	/**
	 * @param cause
	 */
	public ReleaseSeatNotFoundException(Throwable cause) {
		super(SeatExceptionCode.NOT_FOUND_RELEASE_SEAT_ERR_MSG, cause);
	}

	public ReleaseSeatNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param error
	 * @param cause
	 */
	public ReleaseSeatNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
