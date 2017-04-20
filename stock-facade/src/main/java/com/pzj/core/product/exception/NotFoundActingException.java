/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.exception;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.exception.errorcode.ActingExceptionCode;

/**
 * 
 * 
 * @author dongchunfu
 * @version $Id: NotFoundActingException.java, v 0.1 2016年8月8日 下午7:07:56 dongchunfu Exp $
 */
public class NotFoundActingException extends StockException {

	private static final long serialVersionUID = -145572626995565658L;
	private final int errCode = ActingExceptionCode.NOT_FOUND_ACTING_ERR_CODE;

	public NotFoundActingException(String message) {
		super(message);
	}

	public NotFoundActingException(String message, Throwable e) {
		super(message, e);
	}

	public NotFoundActingException() {
		super(ActingExceptionCode.NOT_FOUND_ACTING_ERR_MSG);
	}

	public NotFoundActingException(Throwable e) {
		super(ActingExceptionCode.NOT_FOUND_ACTING_ERR_MSG, e);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
