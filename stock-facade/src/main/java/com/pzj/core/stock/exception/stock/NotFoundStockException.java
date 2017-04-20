package com.pzj.core.stock.exception.stock;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;

/**
 * 库存规则未绑定产品异常
 * @author dongchunfu
 *
 */
public class NotFoundStockException extends StockException {

	private static final long serialVersionUID = -5519133143607923777L;
	private final int errCode = StockExceptionCode.NOT_FOUND_STOCK_ERR_CODE;

	public NotFoundStockException(String message) {
		super(message);
	}

	public NotFoundStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundStockException() {
		super(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG);
	}

	public NotFoundStockException(Throwable cause) {
		super(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
