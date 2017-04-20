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
 * @version $Id: RepeatOccupySeatException.java, v 0.1 2016年8月6日 下午2:14:00 Administrator Exp $
 */
public class RepeatOccupySeatException extends StockException {

	private static final long serialVersionUID = -2532601876163106360L;
	private final int errCode = SeatExceptionCode.REPEAT_OCCUPY_SEAT_ERR_CODE;

	public RepeatOccupySeatException(String message) {
		super(message);
	}

	/**
	 * @param error
	 * @param cause
	 */
	public RepeatOccupySeatException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatOccupySeatException() {
		super(SeatExceptionCode.REPEAT_OCCUPY_SEAT_ERR_MSG);
	}

	/**
	 * @param error
	 * @param cause
	 */
	public RepeatOccupySeatException(Throwable cause) {
		super(SeatExceptionCode.REPEAT_OCCUPY_SEAT_ERR_MSG, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
