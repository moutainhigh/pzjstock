package com.pzj.core.stock.exception.rule;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.StockRuleExceptionCode;

/**
 * 库存不存在异常.
 * @author YRJ
 *
 */
public class NotBindStockRuleException extends StockException {

	private static final long serialVersionUID = -5519133143607923777L;
	private final int errCode = StockRuleExceptionCode.NOT_BIND_PRODUCT_ERR_CODE;

	public NotBindStockRuleException() {
		super(StockRuleExceptionCode.NOT_BIND_PRODUCT_ERR_MSG);
	}

	public NotBindStockRuleException(Throwable cause) {
		super(StockRuleExceptionCode.NOT_BIND_PRODUCT_ERR_MSG, cause);
	}

	public NotBindStockRuleException(String message) {
		super(message);
	}

	public NotBindStockRuleException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}
}
