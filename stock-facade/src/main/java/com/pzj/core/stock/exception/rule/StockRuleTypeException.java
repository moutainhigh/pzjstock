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
 * @version $Id: StockRuleTypeException.java, v 0.1 2016年8月3日 下午6:05:02 Administrator Exp $
 */
public class StockRuleTypeException extends StockException {

	private static final long serialVersionUID = 5660693517911893191L;
	private final int errCode = StockRuleExceptionCode.RETURN_TYPE_ERR_CODE;

	public StockRuleTypeException() {
		super(StockRuleExceptionCode.RETURN_TYPE_ERR_MSG);
	}

	public StockRuleTypeException(Throwable e) {
		super(StockRuleExceptionCode.RETURN_TYPE_ERR_MSG, e);
	}

	public StockRuleTypeException(String message) {
		super(message);
	}

	public StockRuleTypeException(String message, Throwable e) {
		super(message, e);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
