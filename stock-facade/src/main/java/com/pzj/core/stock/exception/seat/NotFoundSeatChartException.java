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
 * @version $Id: NotFoundSeatChartException.java, v 0.1 2016年8月5日 上午10:50:58 Administrator Exp $
 */
public class NotFoundSeatChartException extends StockException {

	private static final long serialVersionUID = -5445624647767179797L;
	private final int errCode = SeatExceptionCode.NOT_FOUND_SEAT_CHART_ERR_CODE;

	public NotFoundSeatChartException() {
		super(SeatExceptionCode.NOT_FOUND_SEAT_CHART_ERR_MSG);
	}

	public NotFoundSeatChartException(Throwable e) {
		super(SeatExceptionCode.NOT_FOUND_SEAT_CHART_ERR_MSG, e);
	}

	public NotFoundSeatChartException(String message) {
		super(message);
	}

	public NotFoundSeatChartException(String message, Throwable e) {
		super(message, e);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
