package com.pzj.core.stock.exception.stock;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;

/**
 * 库存不足异常.
 * @author YRJ
 *
 */
public class ShortageStockException extends StockException {

	private static final long serialVersionUID = -2633835879610174343L;
	private final int errCode = StockExceptionCode.STOCK_SHORTAGE_ERR_CODE;

	public ShortageStockException(String message) {
		super(message);
	}

	public ShortageStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShortageStockException() {
		super(StockExceptionCode.STOCK_SHORTAGE_ERR_MSG);
	}

	public ShortageStockException(Throwable cause) {
		super(StockExceptionCode.STOCK_SHORTAGE_ERR_MSG, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
