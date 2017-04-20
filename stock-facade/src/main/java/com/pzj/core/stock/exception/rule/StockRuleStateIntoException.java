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
 * @version $Id: StockRuleStateIntoException.java, v 0.1 2016年10月21日 上午10:30:56 Administrator Exp $
 */
public class StockRuleStateIntoException extends StockException {
	private static final long serialVersionUID = -8312241687072809977L;
	private final int errCode = StockRuleExceptionCode.STOCK_RULE_STATE_INTO_ERR_CODE;

	public StockRuleStateIntoException() {
		super(StockRuleExceptionCode.STOCK_RULE_STATE_INTO_ERR_MSG);
	}

	/**
	 * @param cause
	 */
	public StockRuleStateIntoException(Throwable cause) {
		super(StockRuleExceptionCode.STOCK_RULE_STATE_INTO_ERR_MSG, cause);
	}

	/**
	 * @param message
	 */
	public StockRuleStateIntoException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public StockRuleStateIntoException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
