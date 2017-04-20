package com.pzj.core.stock.exception.stock;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;

public class StockNumLessSeatException extends StockException {

	private static final long serialVersionUID = 283841783545521574L;
	private final int errCode = StockExceptionCode.AREA_STOCK_NUM_LESS_SEAT_ERR_CODE;

	public StockNumLessSeatException(String message) {
		super(message);
	}

	public StockNumLessSeatException(String message, Throwable cause) {
		super(message, cause);
	}

	public StockNumLessSeatException() {
		super(StockExceptionCode.AREA_STOCK_NUM_LESS_SEAT_ERR_MSG);
	}

	public StockNumLessSeatException(Throwable cause) {
		super(StockExceptionCode.AREA_STOCK_NUM_LESS_SEAT_ERR_MSG, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}
}
