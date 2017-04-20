/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pzj.commons.utils.CheckUtils;
import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.model.theater.TheaterQueryRespModel;
import com.pzj.core.product.service.TheaterQueryService;
import com.pzj.core.product.theater.TheaterQueryEngine;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 剧场基础信息查询类
 * @author Administrator
 * @version $Id: TheaterQueryServiceImpl.java, v 0.1 2017年3月29日 下午4:16:17 Administrator Exp $
 */
@Service("theaterQueryService")
public class TheaterQueryServiceImpl implements TheaterQueryService {
	@Resource
	private TheaterQueryEngine theaterQueryEngine;

	/** 
	 * @see com.pzj.core.product.service.TheaterQueryService#queryTheaterByTheaterId(java.lang.Long, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<TheaterQueryRespModel> queryTheaterByTheaterId(Long theaterId, ServiceContext context) {
		Result<TheaterQueryRespModel> result = new Result<TheaterQueryRespModel>();
		if (CheckUtils.isNull(theaterId)) {
			CommonUtils.setParamErr(result, "剧场id为空");
			return result;
		}
		if (theaterId < 1) {
			CommonUtils.setParamErr(result, "剧场id不合法");
			return result;
		}
		TheaterQueryRespModel model = theaterQueryEngine.queryTheaterByTheaterId(theaterId, context);
		return new Result<TheaterQueryRespModel>(model);
	}

}
