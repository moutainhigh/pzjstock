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
 * @version $Id: CannotReleaseSeatException.java, v 0.1 2016年8月9日 下午6:44:13 Administrator Exp $
 */
public class CannotReleaseSeatException extends StockException {

	private static final long serialVersionUID = -556960936173333986L;
	private final int errCode = SeatExceptionCode.NOT_RELEASE_SEAT_ERR_CODE;

	public CannotReleaseSeatException(String errMsg) {
		super(SeatExceptionCode.NOT_RELEASE_SEAT_ERR_MSG + errMsg);
	}

	/**
	 * @param error
	 * @param cause
	 */
	public CannotReleaseSeatException(String errMsg, Throwable cause) {
		super(SeatExceptionCode.NOT_RELEASE_SEAT_ERR_MSG + errMsg, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

	//    private void initParam(String errMsg) {
	//        this.setErrCode(SeatExceptionCode.NOT_RELEASE_SEAT_ERR_CODE);
	//        this.setErrMsg(SeatExceptionCode.NOT_RELEASE_SEAT_ERR_MSG + errMsg);
	//    }
}
