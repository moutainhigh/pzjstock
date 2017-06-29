package com.pzj.core.util;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.common.exception.TheaterExceptionCode;
import com.pzj.core.product.seatRecord.exception.SeatRecordOccupyStockException;
import com.pzj.framework.context.Result;
import com.pzj.framework.converter.JSONConverter;

/**
 * Created by Administrator on 2017-1-12.
 */
public abstract class RpcCaller<T extends Serializable> {
	private static Logger logger = LoggerFactory.getLogger(RpcCaller.class);

	public abstract T call();

	private Object[] args = null;

	public final RpcCaller<T> args(Object... args) {
		this.args = args;
		return this;
	}

	public final Result<T> run() {
		return call(this);
	}

	public final Result<T> runWithTransaction(PlatformTransactionManager platformTransactionManager) {
		return runWithTransaction(platformTransactionManager, TransactionDefinition.PROPAGATION_REQUIRED);
	}

	public final Result<T> runWithTransaction(PlatformTransactionManager platformTransactionManager,
			int transactionDefinition) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(transactionDefinition);

		TransactionStatus transaction = platformTransactionManager.getTransaction(def);

		Result<T> result = call(this);

		if (result.isOk()) {
			platformTransactionManager.commit(transaction);
		} else {
			platformTransactionManager.rollback(transaction);
		}

		return result;
	}

	private static <T extends Serializable> Result<T> call(RpcCaller<T> rpcCaller) {
		Result<T> result = new Result<>();
		try {
			result.setData(rpcCaller.call());
		} catch (TheaterException e) {
			catchCustomerException(result, e);
			if (rpcCaller.args != null) {
				logger.error(e.getMessage() + " 入参：{}", JSONConverter.toJson(rpcCaller.args));
			}
			if (e instanceof SeatRecordOccupyStockException) {
				SeatRecordOccupyStockException seatRecordOccupyStockException = (SeatRecordOccupyStockException) e;
				result.setData((T) seatRecordOccupyStockException.getOccupyStockResponse());
			}
			logger.error(e.getMessage(), e);

		} catch (StockException e) {
			catchStockException(result, e);
			if (rpcCaller.args != null) {
				logger.error(e.getMessage() + " 入参：{}", JSONConverter.toJson(rpcCaller.args));
			}
			logger.error(e.getMessage(), e);
		} catch (Throwable throwable) {
			catchThrowable(result);
			if (rpcCaller.args != null) {
				logger.error(throwable.getMessage() + " 入参：{}", JSONConverter.toJson(rpcCaller.args));
			}
			logger.error(throwable.getMessage(), throwable);
		}
		return result;
	}

	private static <T extends Serializable> void catchCustomerException(Result<T> result, TheaterException e) {
		result.setErrorCode(e.getCode());
		result.setErrorMsg(e.getMessage());
	}

	private static <T extends Serializable> void catchStockException(Result<T> result, StockException e) {
		result.setErrorCode(e.getErrCode());
		result.setErrorMsg(e.getMessage());
	}

	private static <T extends Serializable> void catchThrowable(Result<T> result) {
		result.setErrorCode(TheaterExceptionCode.ERROR.getCode());
		result.setErrorMsg(TheaterExceptionCode.ERROR.getMsg());
	}

	private static <T extends Serializable> void catchThrowableAndServiceException(Result<T> result, Throwable throwable) {
		if (throwable instanceof TheaterException) {
			catchCustomerException(result, (TheaterException) throwable);
		} else {
			catchThrowable(result);
		}
	}
}
