package com.pzj.core.stock.exception.rule;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.StockRuleExceptionCode;

/**
 * 库存规则不存在异常.
 * @author YRJ
 *
 */
public class NotFoundStockRuleException extends StockException {

	private static final long serialVersionUID = -5519133143607923777L;
	private final int errCode = StockRuleExceptionCode.NOT_FOUND_STOCK_RULE_ERR_CODE;

	public NotFoundStockRuleException() {
		super(StockRuleExceptionCode.NOT_FOUND_STOCK_RULE_ERR_MSG);
	}

	public NotFoundStockRuleException(Throwable cause) {
		super(StockRuleExceptionCode.NOT_FOUND_STOCK_RULE_ERR_MSG, cause);
	}

	public NotFoundStockRuleException(String message) {
		super(message);
	}

	public NotFoundStockRuleException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
