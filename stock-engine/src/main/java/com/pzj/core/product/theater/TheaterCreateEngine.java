/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.theater;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.commons.utils.CheckUtils;
import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.entity.TheaterInfo;
import com.pzj.core.product.model.theater.TheaterCreateReqModel;
import com.pzj.core.product.model.theater.TheaterQueryRespModel;
import com.pzj.core.product.write.TheaterWriteMapper;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

/**
 * 
 * @author Administrator
 * @version $Id: TheaterCreateEngine.java, v 0.1 2017年3月29日 下午1:54:32 Administrator Exp $
 */
@Component("theaterCreateEngine")
@Transactional(value = "stock.transactionManager")
public class TheaterCreateEngine {
	private static final Logger logger = LoggerFactory.getLogger(TheaterCreateEngine.class);
	@Resource
	private TheaterWriteMapper theaterWriteMapper;
	@Resource
	private TheaterQueryEngine theaterQueryEngine;

	public Result<Boolean> createTheaterInfo(TheaterCreateReqModel model, ServiceContext context) {
		if (logger.isDebugEnabled()) {
			logger.debug("The parameter to create the theater info is {} ", JSONConverter.toJson(model));
		}
		try {
			TheaterQueryRespModel queryResult = theaterQueryEngine
					.queryTheaterBeforeOper(model.getTheaterId(), context);
			if (CheckUtils.isNotNull(queryResult)) {
				throw new StockException(StockExceptionCode.THEATER_EXIST_ERR_MSG);
			}
			TheaterInfo theaterInfo = convertInfo(model);
			Integer createResult = theaterWriteMapper.createTheaterInfo(theaterInfo);
			if (logger.isDebugEnabled()) {
				logger.debug("Create results is {}", createResult);
			}
			if (createResult < 1) {
				logger.warn("add theater info fail. result:{}", createResult);
				throw new StockException(StockExceptionCode.ADD_THEATER_ERR_MSG);
			}
		} catch (Exception e) {
			logger.error("add theater info error", e);
			throw new StockException(StockExceptionCode.ADD_THEATER_ERR_MSG);
		}

		return new Result<>();
	}

	private TheaterInfo convertInfo(TheaterCreateReqModel model) {
		TheaterInfo theaterInfo = new TheaterInfo(model.getTheaterId(), model.getLineNumber(), model.getColumnNumber(),
				model.getSortType(), model.getSeatState());
		return theaterInfo;

	}
}
