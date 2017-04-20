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
import com.pzj.core.product.model.theater.TheaterQueryRespModel;
import com.pzj.core.product.read.TheaterReadMapper;
import com.pzj.core.product.write.TheaterWriteMapper;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

/**
 * 
 * @author Administrator
 * @version $Id: TheaterCreateEngine.java, v 0.1 2017年3月29日 下午1:54:32 Administrator Exp $
 */
@Component("theaterQueryEngine")
@Transactional(value = "stock.transactionManager")
public class TheaterQueryEngine {
	private static final Logger logger = LoggerFactory.getLogger(TheaterQueryEngine.class);
	@Resource
	private TheaterReadMapper theaterReadMapper;
	@Resource
	private TheaterWriteMapper theaterWriteMapper;

	public TheaterQueryRespModel queryTheaterByTheaterId(Long theaterId, ServiceContext context) {
		if (logger.isDebugEnabled()) {
			logger.debug("The parameter of the query theater info is {} ", theaterId);
		}
		try {
			TheaterInfo theaterInfo = theaterReadMapper.queryTheaterByTheaterId(theaterId);
			if (logger.isDebugEnabled()) {
				logger.debug("Query results is {}", JSONConverter.toJson(theaterInfo));
			}
			if (CheckUtils.isNull(theaterInfo)) {
				return null;
			}
			return convertRespModel(theaterInfo);
		} catch (Exception e) {
			logger.error("Query theater info error", e);
			throw new StockException(StockExceptionCode.QUERY_THEATER_ERR_MSG);
		}
	}

	private TheaterQueryRespModel convertRespModel(TheaterInfo theaterInfo) {
		TheaterQueryRespModel respModel = new TheaterQueryRespModel(theaterInfo.getTheaterId(),
				theaterInfo.getLineNum(), theaterInfo.getColumnNum(), theaterInfo.getSortType(),
				theaterInfo.getSeatState());
		return respModel;
	}

	public TheaterQueryRespModel queryTheaterBeforeOper(Long theaterId, ServiceContext context) {
		if (logger.isDebugEnabled()) {
			logger.debug("The parameter of the query theater info is {} ", theaterId);
		}
		try {
			TheaterInfo theaterInfo = theaterWriteMapper.queryTheaterByTheaterId(theaterId);
			if (logger.isDebugEnabled()) {
				logger.debug("Query results is {}", JSONConverter.toJson(theaterInfo));
			}
			if (CheckUtils.isNull(theaterInfo)) {
				return null;
			}
			return convertRespModel(theaterInfo);
		} catch (Exception e) {
			logger.error("Query theater info error", e);
			throw new StockException(StockExceptionCode.QUERY_THEATER_ERR_MSG);
		}
	}
}
