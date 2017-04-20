package com.pzj.core.common.exception;

import com.pzj.core.stock.exception.errcode.StockExceptionCode;

public class InvokeOtherServiceException extends StockException {

	private static final long serialVersionUID = -207193086367343031L;
	private final int errCode = StockExceptionCode.INVOKE_OTHER_SERVICE_ERR_CODE;

	public InvokeOtherServiceException() {
		super(StockExceptionCode.INVOKE_OTHER_SERVICE_ERR_MSG);
	}

	public InvokeOtherServiceException(Throwable cause) {
		super(StockExceptionCode.INVOKE_OTHER_SERVICE_ERR_MSG, cause);
	}

	/**
	 * @param error
	 */
	public InvokeOtherServiceException(String message) {
		super(message);
	}

	public InvokeOtherServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}
}
