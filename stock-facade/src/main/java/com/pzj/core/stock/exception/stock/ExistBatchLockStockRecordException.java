/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.exception.stock;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;

/**
 * 
 * @author Administrator
 * @version $Id: ExistBatchLockStockRecordException.java, v 0.1 2016年8月10日 上午10:11:11 Administrator Exp $
 */
public class ExistBatchLockStockRecordException extends StockException {

	private static final long serialVersionUID = -409094869564003749L;
	private final int errCode = StockExceptionCode.EXIST_BATCH_LOCK_STOCK_ERR_CODE;

	public ExistBatchLockStockRecordException(String msg) {
		super(StockExceptionCode.EXIST_BATCH_LOCK_STOCK_ERR_MSG + msg);
	}

	/**
	 * @param error
	 * @param cause
	 */
	public ExistBatchLockStockRecordException(String msg, Throwable cause) {
		super(StockExceptionCode.EXIST_BATCH_LOCK_STOCK_ERR_MSG + msg, cause);
	}

	@Override
	public int getErrCode() {
		return errCode;
	}

}
