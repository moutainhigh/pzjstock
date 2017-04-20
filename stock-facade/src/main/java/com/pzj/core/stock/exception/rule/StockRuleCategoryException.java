/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.exception.rule;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.StockRuleExceptionCode;

/**
 * 
 * 
 * @author dongchunfu
 * @version $Id: StockRuleCategoryException.java, v 0.1 2016年8月17日 下午3:59:47 dongchunfu Exp $
 */
public class StockRuleCategoryException extends StockException {

	private static final long serialVersionUID = 5660693517911893191L;
	private final int errCode = StockRuleExceptionCode.CATEGORY_NOTEXIST_ERR_CODE;

	public StockRuleCategoryException() {
		super(StockRuleExceptionCode.CATEGORY_NOTEXIST_ERR_MSG);
	}

	public StockRuleCategoryException(Throwable e) {
		super(StockRuleExceptionCode.CATEGORY_NOTEXIST_ERR_MSG, e);
	}

	public StockRuleCategoryException(String message) {
		super(message);
	}

	public StockRuleCategoryException(String message, Throwable e) {
		super(message, e);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
