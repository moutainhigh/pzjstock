package com.pzj.core.util;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.common.exception.TheaterExceptionCode;
import com.pzj.framework.context.Result;

/**
 * Created by Administrator on 2017-1-12.
 */
public abstract class RpcCaller<T extends Serializable> {
	private static Logger logger = LoggerFactory.getLogger(RpcCaller.class);

	public abstract T call();

	public final Result<T> run() {
		return call(this);
	}

	public static <T extends Serializable> Result<T> call(RpcCaller<T> rpcCaller) {
		Result<T> result = new Result<>();
		try {
			result.setData(rpcCaller.call());
		} catch (TheaterException e) {
			catchCustomerException(result, e);
		} catch (StockException e) {
			catchStockException(result, e);
		} catch (Throwable throwable) {
			catchThrowable(result, throwable);
		}
		return result;
	}

	public static <T extends Serializable> void catchCustomerException(Result<T> result, TheaterException e) {
		result.setErrorCode(e.getCode());
		result.setErrorMsg(e.getMessage());
		logger.error(e.getMessage(), e);
	}

	public static <T extends Serializable> void catchStockException(Result<T> result, StockException e) {
		result.setErrorCode(e.getErrCode());
		result.setErrorMsg(e.getMessage());
		logger.error(e.getMessage(), e);
	}

	public static <T extends Serializable> void catchThrowable(Result<T> result, Throwable throwable) {
		result.setErrorCode(TheaterExceptionCode.ERROR.getCode());
		result.setErrorMsg(TheaterExceptionCode.ERROR.getMsg());
		logger.error(throwable.getMessage(), throwable);
	}

	public static <T extends Serializable> void catchThrowableAndServiceException(Result<T> result, Throwable throwable) {
		if (throwable instanceof TheaterException) {
			catchCustomerException(result, (TheaterException) throwable);
		} else {
			catchThrowable(result, throwable);
		}
	}
}
