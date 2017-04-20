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
 * @version $Id: StockRuleStateException.java, v 0.1 2016年8月29日 下午3:40:31 Administrator Exp $
 */
public class StockRuleNameException extends StockException {
	private static final long serialVersionUID = 5052127223956241546L;
	private final int errCode = StockRuleExceptionCode.STOCK_RULE_ERR_CODE;

	public StockRuleNameException() {
		super(StockRuleExceptionCode.STOCK_RULE_ERR_MSG);
	}

	public StockRuleNameException(Throwable cause) {
		super(StockRuleExceptionCode.STOCK_RULE_ERR_MSG, cause);
	}

	public StockRuleNameException(String message) {
		super(message);
	}

	public StockRuleNameException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
