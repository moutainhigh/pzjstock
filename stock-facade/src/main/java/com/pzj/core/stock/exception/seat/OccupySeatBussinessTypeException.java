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
 * @version $Id: OccupySeatBussinessTypeException.java, v 0.1 2016年9月9日 下午2:18:17 Administrator Exp $
 */
public class OccupySeatBussinessTypeException extends StockException {

	private static final long serialVersionUID = 881176385756884585L;
	private final int errCode = SeatExceptionCode.OCCUPY_SEAT_BUSSINESS_TYPE_ERR_CODE;

	public OccupySeatBussinessTypeException() {
		super(SeatExceptionCode.OCCUPY_SEAT_BUSSINESS_TYPE_ERR_MSG);
	}

	/**
	 * @param cause
	 */
	public OccupySeatBussinessTypeException(Throwable cause) {
		super(SeatExceptionCode.OCCUPY_SEAT_BUSSINESS_TYPE_ERR_MSG, cause);
	}

	public OccupySeatBussinessTypeException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OccupySeatBussinessTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
