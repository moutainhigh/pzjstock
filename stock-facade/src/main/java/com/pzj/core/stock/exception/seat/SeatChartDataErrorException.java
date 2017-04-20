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
 * @version $Id: SeatChartDataErrorException.java, v 0.1 2016年8月10日 上午11:18:58 Administrator Exp $
 */
public class SeatChartDataErrorException extends StockException {

	private static final long serialVersionUID = 3254547280719943339L;
	private final int errCode = SeatExceptionCode.SEAT_CHART_DATA_ERR_CODE;

	public SeatChartDataErrorException(String message) {
		super(message);
	}

	/**
	 * @param error
	 * @param cause
	 */
	public SeatChartDataErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeatChartDataErrorException() {
		super(SeatExceptionCode.SEAT_CHART_DATA_ERR_MSG);
	}

	/**
	 * @param error
	 * @param cause
	 */
	public SeatChartDataErrorException(Throwable cause) {
		super(SeatExceptionCode.SEAT_CHART_DATA_ERR_MSG, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
