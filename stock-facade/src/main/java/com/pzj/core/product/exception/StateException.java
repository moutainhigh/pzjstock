/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.exception;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.exception.errorcode.ActingExceptionCode;

/**
 * 
 * @author Administrator
 * @version $Id: AreaScreeingsRelStateException.java, v 0.1 2016年8月9日 上午11:44:57 Administrator Exp $
 */
public class StateException extends StockException {
	private static final long serialVersionUID = 2514946885675571304L;
	private final int errCode = ActingExceptionCode.ACTING_STATE_ERR_CODE;

	public StateException(String message) {
		super(message);
	}

	public StateException(String message, Throwable e) {
		super(message, e);
	}

	public StateException() {
		super(ActingExceptionCode.ACTING_STATE_ERR_MSG);
	}

	public StateException(Throwable e) {
		super(ActingExceptionCode.ACTING_STATE_ERR_MSG, e);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
