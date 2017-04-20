package com.pzj.core.common.exception;

import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.exception.ServiceException;

/**
 * 库存异常基类.
 * @author YRJ
 *
 */
public class StockException extends ServiceException {

	private static final long serialVersionUID = 7282302020938367713L;

	private final int errCode = StockExceptionCode.STOCK_ERR_CODE;

	public StockException() {
		super(StockExceptionCode.STOCK_ERR_MSG);
	}

	public StockException(Throwable cause) {
		super(StockExceptionCode.STOCK_ERR_MSG, cause);
	}

	public StockException(String message) {
		super(message);
	}

	public StockException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getErrCode() {
		return errCode;
	}

}
