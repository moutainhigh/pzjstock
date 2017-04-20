package com.pzj.core.stock.exception.stock;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;

/**
 * 库存查询异常.
 * @author YRJ
 *
 */
public class QueryStockException extends StockException {

	private static final long serialVersionUID = 4866241544384054781L;
	private final int errCode = StockExceptionCode.STOCK_QUERY_ERR_CODE;

	public QueryStockException(String message) {
		super(message);
	}

	public QueryStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public QueryStockException() {
		super(StockExceptionCode.STOCK_QUERY_ERR_MSG);
	}

	public QueryStockException(Throwable cause) {
		super(StockExceptionCode.STOCK_QUERY_ERR_MSG, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
