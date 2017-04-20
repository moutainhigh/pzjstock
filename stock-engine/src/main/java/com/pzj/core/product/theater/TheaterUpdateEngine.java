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
import com.pzj.core.product.model.theater.TheaterModifyReqModel;
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
@Component("theaterUpdateEngine")
@Transactional(value = "stock.transactionManager")
public class TheaterUpdateEngine {
	private static final Logger logger = LoggerFactory.getLogger(TheaterUpdateEngine.class);
	@Resource
	private TheaterWriteMapper theaterWriteMapper;
	@Resource
	private TheaterQueryEngine theaterQueryEngine;

	public Result<Boolean> updateTheaterInfo(TheaterModifyReqModel model, ServiceContext context) {
		if (logger.isDebugEnabled()) {
			logger.debug("The parameter to modify the theater info is {} ", JSONConverter.toJson(model));
		}
		try {
			TheaterQueryRespModel queryResult = theaterQueryEngine
					.queryTheaterBeforeOper(model.getTheaterId(), context);
			if (CheckUtils.isNull(queryResult)) {
				throw new StockException(StockExceptionCode.THEATER_NOT_EXIST_ERR_MSG);
			}
			TheaterInfo theaterInfo = convertInfo(model);
			Integer updateResult = theaterWriteMapper.updateTheaterInfo(theaterInfo);
			if (logger.isDebugEnabled()) {
				logger.debug("Modified results is {}", updateResult);
			}
			if (updateResult < 1) {
				logger.warn("modify theater info fail. result:{}", updateResult);
				throw new StockException(StockExceptionCode.MODIFY_THEATER_ERR_MSG);
			}
		} catch (Exception e) {
			logger.error("modify theater info error", e);
			throw new StockException(StockExceptionCode.MODIFY_THEATER_ERR_MSG);
		}

		return new Result<>();
	}

	private TheaterInfo convertInfo(TheaterModifyReqModel model) {
		TheaterInfo theaterInfo = new TheaterInfo();
		theaterInfo.setTheaterId(model.getTheaterId());
		theaterInfo.setColumnNum(model.getColumnNumber());
		theaterInfo.setLineNum(model.getLineNumber());
		return theaterInfo;

	}
}
