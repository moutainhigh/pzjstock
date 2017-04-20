package com.pzj.core.stock.exception.stock;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;

/**
 * 库存锁定数量异常.
 * @author YRJ
 *
 */
public class LockedNumException extends StockException {

	private static final long serialVersionUID = 6257522763468339983L;
	private final int errCode = StockExceptionCode.LOCKNUM_OUT_OF_STOCK_ERR_CODE;

	public LockedNumException(String message) {
		super(message);
	}

	public LockedNumException(String message, Throwable cause) {
		super(message, cause);
	}

	public LockedNumException() {
		super(StockExceptionCode.LOCKNUM_OUT_OF_STOCK_ERR_MSG);
	}

	public LockedNumException(Throwable cause) {
		super(StockExceptionCode.LOCKNUM_OUT_OF_STOCK_ERR_MSG, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
