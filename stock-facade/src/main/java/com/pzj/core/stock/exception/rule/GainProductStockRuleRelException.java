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
 * @version $Id: GainProductStockRuleRelException.java, v 0.1 2016年8月23日 下午3:33:41 Administrator Exp $
 */
public class GainProductStockRuleRelException extends StockException {

	private static final long serialVersionUID = 8726698509947893887L;
	private final int errCode = StockRuleExceptionCode.GET_STOCK_RULE_REL_PRODUCT_ERR_CODE;

	public GainProductStockRuleRelException() {
		super(StockRuleExceptionCode.GET_STOCK_RULE_REL_PRODUCT_ERR_MSG);
	}

	public GainProductStockRuleRelException(Throwable cause) {
		super(StockRuleExceptionCode.GET_STOCK_RULE_REL_PRODUCT_ERR_MSG, cause);
	}

	public GainProductStockRuleRelException(String message) {
		super(message);
	}

	public GainProductStockRuleRelException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
