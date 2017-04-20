package com.pzj.core.stock.exception.stock;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;

public class StockNumLessUsedException extends StockException {
	private static final long serialVersionUID = 2413227171615602939L;
	private final int errCode = StockExceptionCode.STOCK_NUM_LESS_USED_ERR_CODE;

	public StockNumLessUsedException(String message) {
		super(message);
	}

	public StockNumLessUsedException(String message, Throwable cause) {
		super(message, cause);
	}

	public StockNumLessUsedException() {
		super(StockExceptionCode.STOCK_NUM_LESS_USED_ERR_MSG);
	}

	public StockNumLessUsedException(Throwable cause) {
		super(StockExceptionCode.STOCK_NUM_LESS_USED_ERR_MSG, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}
}
