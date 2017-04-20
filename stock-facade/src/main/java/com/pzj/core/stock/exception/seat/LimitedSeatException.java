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
 * @version $Id: LimitedSeatException.java, v 0.1 2016年8月9日 下午5:26:05 Administrator Exp $
 */
public class LimitedSeatException extends StockException {

	private static final long serialVersionUID = 2383642491081796137L;
	private final int errCode = SeatExceptionCode.MAX_SEAT_NUM_ERR_CODE;

	public LimitedSeatException() {
		super(SeatExceptionCode.MAX_SEAT_NUM_ERR_MSG);
	}

	/**
	 * @param cause
	 */
	public LimitedSeatException(Throwable cause) {
		super(SeatExceptionCode.MAX_SEAT_NUM_ERR_MSG, cause);
	}

	public LimitedSeatException(String message) {
		super(message);
	}

	/**
	 * @param error
	 * @param cause
	 */
	public LimitedSeatException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
