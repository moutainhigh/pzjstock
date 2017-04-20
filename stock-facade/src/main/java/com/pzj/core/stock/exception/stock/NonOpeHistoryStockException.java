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
 * @version $Id: NonOpeHistoryStockException.java, v 0.1 2016年11月18日 上午11:46:37 Administrator Exp $
 */
public class NonOpeHistoryStockException extends StockException {
	private static final long serialVersionUID = -8471879183649333846L;
	private final int errCode = StockExceptionCode.NON_OPERATE_HISTORY_STOCK_ERR_CODE;

	/**
	 * @param message
	 */
	public NonOpeHistoryStockException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NonOpeHistoryStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public NonOpeHistoryStockException() {
		super(StockExceptionCode.NON_OPERATE_HISTORY_STOCK_ERR_MSG);
	}

	/**
	 * @param cause
	 */
	public NonOpeHistoryStockException(Throwable cause) {
		super(StockExceptionCode.NON_OPERATE_HISTORY_STOCK_ERR_MSG, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
