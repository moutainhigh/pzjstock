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
 * @version $Id: GainProductDateErrorException.java, v 0.1 2016年8月23日 下午3:43:24 Administrator Exp $
 */
public class GainProductDateDataException extends StockException {
	private static final long serialVersionUID = -8284126189746585719L;

	private final int errCode = StockRuleExceptionCode.GET_PRODUCT_DATE_ERR_CODE;

	public GainProductDateDataException() {
		super(StockRuleExceptionCode.GET_PRODUCT_DATE_ERR_MSG);
	}

	public GainProductDateDataException(Throwable cause) {
		super(StockRuleExceptionCode.GET_PRODUCT_DATE_ERR_MSG, cause);
	}

	public GainProductDateDataException(String message) {
		super(message);
	}

	public GainProductDateDataException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
