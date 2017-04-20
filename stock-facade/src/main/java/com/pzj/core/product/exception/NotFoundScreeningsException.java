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
 * @version $Id: NotFoundScreeningsException.java, v 0.1 2016年8月31日 下午4:30:46 Administrator Exp $
 */
public class NotFoundScreeningsException extends StockException {
	private static final long serialVersionUID = -7859643616509697239L;
	private final int errCode = ActingExceptionCode.NOT_FOUND_SCREENINGS_ERR_CODE;

	public NotFoundScreeningsException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NotFoundScreeningsException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundScreeningsException() {
		super(ActingExceptionCode.NOT_FOUND_SCREENINGS_ERR_MSG);
	}

	/**
	 * @param cause
	 */
	public NotFoundScreeningsException(Throwable cause) {
		super(ActingExceptionCode.NOT_FOUND_SCREENINGS_ERR_MSG, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
