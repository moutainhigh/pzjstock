/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pzj.commons.utils.CheckUtils;
import com.pzj.core.common.exception.StockException;
import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.model.theater.TheaterCreateReqModel;
import com.pzj.core.product.model.theater.TheaterModifyReqModel;
import com.pzj.core.product.service.TheaterService;
import com.pzj.core.product.theater.TheaterCreateEngine;
import com.pzj.core.product.theater.TheaterUpdateEngine;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author Administrator
 * @version $Id: TheaterServiceImpl.java, v 0.1 2017年3月29日 下午1:49:11 Administrator Exp $
 */
@Service(value = "theaterService")
public class TheaterServiceImpl implements TheaterService {
	@Resource
	private TheaterCreateEngine theaterCreateEngine;
	@Resource
	private TheaterUpdateEngine theaterUpdateEngine;

	/** 
	 * @see com.pzj.core.product.service.TheaterService#addTheaterInfo(com.pzj.core.product.model.theater.TheaterCreateReqModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Boolean> addTheaterInfo(TheaterCreateReqModel model, ServiceContext context) {
		Result<Boolean> checkreResult = checkCreateParams(model);
		if (!checkreResult.isOk()) {
			checkreResult.setData(false);
			return checkreResult;
		}
		try {
			Result<Boolean> result = theaterCreateEngine.createTheaterInfo(model, context);
			return result;
		} catch (StockException e) {
			return new Result<>(e.getErrCode(), e.getMessage());
		}

	}

	private Result<Boolean> checkCreateParams(TheaterCreateReqModel model) {
		Result<Boolean> result = new Result<>();
		if (CheckUtils.isNull(model)) {
			CommonUtils.setParamErr(result);
		}
		if (CheckUtils.isNull(model.getTheaterId())) {
			CommonUtils.setParamErr(result, "剧场id为空");
		}
		if (model.getTheaterId() < 1) {
			CommonUtils.setParamErr(result, "剧场id不合法");
		}
		if (CheckUtils.isNull(model.getColumnNumber())) {
			CommonUtils.setParamErr(result, "列数量为空");
		}
		if (CheckUtils.isNull(model.getLineNumber())) {
			CommonUtils.setParamErr(result, "行数量为空");
		}
		if (CheckUtils.isNull(model.getSortType())) {
			CommonUtils.setParamErr(result, "座位排序类型为空");
		}
		if (CheckUtils.isNull(model.getSeatState())) {
			CommonUtils.setParamErr(result, "座位状态为空");
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.product.service.TheaterService#modifyTheaterInfo(com.pzj.core.product.model.theater.TheaterModifyReqModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Boolean> modifyTheaterInfo(TheaterModifyReqModel model, ServiceContext context) {
		Result<Boolean> checkreResult = checkModifyParams(model);
		if (!checkreResult.isOk()) {
			checkreResult.setData(false);
			return checkreResult;
		}
		try {
			Result<Boolean> result = theaterUpdateEngine.updateTheaterInfo(model, context);
			return result;
		} catch (StockException e) {
			return new Result<>(e.getErrCode(), e.getMessage());
		}
	}

	private Result<Boolean> checkModifyParams(TheaterModifyReqModel model) {
		Result<Boolean> result = new Result<>();
		if (CheckUtils.isNull(model)) {
			CommonUtils.setParamErr(result);
		}
		if (CheckUtils.isNull(model.getTheaterId())) {
			CommonUtils.setParamErr(result, "剧场id为空");
		}
		if (model.getTheaterId() < 1) {
			CommonUtils.setParamErr(result, "剧场id不合法");
		}
		if (CheckUtils.isNull(model.getColumnNumber())) {
			CommonUtils.setParamErr(result, "列数量为空");
		}
		if (CheckUtils.isNull(model.getLineNumber())) {
			CommonUtils.setParamErr(result, "行数量为空");
		}
		return result;
	}
}
