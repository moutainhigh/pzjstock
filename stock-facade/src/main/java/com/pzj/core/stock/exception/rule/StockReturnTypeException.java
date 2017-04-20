/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.exception.rule;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.StockRuleExceptionCode;

/**
 * 
 * @author Administrator
 * @version $Id: StockReturnTypeException.java, v 0.1 2016年8月3日 下午6:53:34 Administrator Exp $
 */
public class StockReturnTypeException extends StockException {

	private static final long serialVersionUID = -7958104188015299937L;
	private final int errCode = StockRuleExceptionCode.RETURN_TYPE_ERR_CODE;

	public StockReturnTypeException() {
		super(StockRuleExceptionCode.RETURN_TYPE_ERR_MSG);
	}

	public StockReturnTypeException(Throwable e) {
		super(StockRuleExceptionCode.RETURN_TYPE_ERR_MSG, e);
	}

	public StockReturnTypeException(String message) {
		super(message);
	}

	public StockReturnTypeException(String message, Throwable e) {
		super(message, e);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
