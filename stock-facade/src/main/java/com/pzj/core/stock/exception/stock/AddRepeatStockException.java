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
 * @version $Id: AddRepeatStockException.java, v 0.1 2016年9月21日 下午2:28:59 Administrator Exp $
 */
public class AddRepeatStockException extends StockException {

	private static final long serialVersionUID = -7933432226425944517L;
	private final int errCode = StockExceptionCode.STOCK_REPEAT_ADD_ERR_CODE;

	public AddRepeatStockException(String message) {
		super(message);
	}

	public AddRepeatStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddRepeatStockException() {
		super(StockExceptionCode.STOCK_REPEAT_ADD_ERR_MSG);
	}

	public AddRepeatStockException(Throwable cause) {
		super(StockExceptionCode.STOCK_REPEAT_ADD_ERR_MSG, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
