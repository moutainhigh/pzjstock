package com.pzj.core.stock.exception.stock;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;

/**
 * 库存上限异常.
 * @author YRJ
 *
 */
public class LimitedException extends StockException {

	private static final long serialVersionUID = 5037812024120051144L;
	private final int errCode = StockExceptionCode.MAX_STOCK_NUM_ERR_CODE;

	public LimitedException(String message) {
		super(message);
	}

	public LimitedException(String message, Throwable cause) {
		super(message, cause);
	}

	public LimitedException() {
		super(StockExceptionCode.MAX_STOCK_NUM_ERR_MSG);
	}

	public LimitedException(Throwable cause) {
		super(StockExceptionCode.MAX_STOCK_NUM_ERR_MSG, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
