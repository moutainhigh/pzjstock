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
 * @version $Id: NotFoundStockRecordException.java, v 0.1 2016年8月5日 上午10:05:38 Administrator Exp $
 */
public class NotFoundStockRecordException extends StockException {

	private static final long serialVersionUID = -145572626995565658L;
	private final int errCode = StockExceptionCode.NOT_FOUND_STOCK_RECORD_ERR_CODE;

	public NotFoundStockRecordException(String message) {
		super(message);
	}

	public NotFoundStockRecordException(String message, Throwable e) {
		super(message, e);
	}

	public NotFoundStockRecordException() {
		super(StockExceptionCode.NOT_FOUND_STOCK_RECORD_ERR_MSG);
	}

	public NotFoundStockRecordException(Throwable e) {
		super(StockExceptionCode.NOT_FOUND_STOCK_RECORD_ERR_MSG, e);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
