package com.pzj.core.stock.exception.stock;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;

/**
 * 库存状态异常.
 * @author YRJ
 *
 */
public class StockStateException extends StockException {

	private static final long serialVersionUID = -854553329462532437L;
	private final int errCode = StockExceptionCode.STOCK_STATE_ERR_CODE;

	public StockStateException(String message) {
		super(message);
	}

	public StockStateException(String message, Throwable cause) {
		super(message, cause);
	}

	public StockStateException() {
		super(StockExceptionCode.STOCK_STATE_ERR_MSG);
	}

	public StockStateException(Throwable cause) {
		super(StockExceptionCode.STOCK_STATE_ERR_MSG, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
