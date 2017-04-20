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
 * @version $Id: CannotOccupySeatException.java, v 0.1 2016年8月9日 下午6:43:58 Administrator Exp $
 */
public class CannotOccupySeatException extends StockException {

	private static final long serialVersionUID = -3434239962882880725L;
	private final int errCode = SeatExceptionCode.NOT_OCCUPY_SEAT_ERR_CODE;

	public CannotOccupySeatException(String errMsg) {
		super(SeatExceptionCode.NOT_OCCUPY_SEAT_ERR_MSG + errMsg);
	}

	/**
	 * @param error
	 * @param cause
	 */
	public CannotOccupySeatException(String errMsg, Throwable cause) {
		super(SeatExceptionCode.NOT_OCCUPY_SEAT_ERR_MSG + errMsg, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
