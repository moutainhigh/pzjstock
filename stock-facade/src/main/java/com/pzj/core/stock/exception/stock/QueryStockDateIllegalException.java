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
 * @version $Id: QueryStockDateIllegalException.java, v 0.1 2016年8月29日 下午2:37:56 Administrator Exp $
 */
public class QueryStockDateIllegalException extends StockException {
	private static final long serialVersionUID = -4372224406749131923L;
	private final int errCode = StockExceptionCode.STOCK_QUERY_DATE_ILLEGAL_ERR_CODE;

	public QueryStockDateIllegalException(String message) {
		super(message);
	}

	public QueryStockDateIllegalException(String message, Throwable cause) {
		super(message, cause);
	}

	public QueryStockDateIllegalException() {
		super(StockExceptionCode.STOCK_QUERY_DATE_ILLEGAL_ERR_MSG);
	}

	public QueryStockDateIllegalException(Throwable cause) {
		super(StockExceptionCode.STOCK_QUERY_DATE_ILLEGAL_ERR_MSG, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
