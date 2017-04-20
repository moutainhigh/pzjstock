/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.commons.utils.CheckUtils;
import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.assigned.AssignedOrderQueryEngine;
import com.pzj.core.product.assigned.QueryCountAssignEngine;
import com.pzj.core.product.assigned.SpuAssignQueryEngine;
import com.pzj.core.product.model.assign.AssignedOrderQueryReqModel;
import com.pzj.core.product.model.assign.AssignedOrderQueryRespModel;
import com.pzj.core.product.model.assign.CalendarAssignReqModel;
import com.pzj.core.product.model.assign.CalendarAssignRespModel;
import com.pzj.core.product.model.assign.TheaterAssignRespModel;
import com.pzj.core.product.model.assign.TheaterScreeingAssignReqModel;
import com.pzj.core.product.service.AssignedOrderQueryService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

/**
 * 待分配列表查询接口
 * @author Administrator
 * @version $Id: AssignedOrderQueryServiceImpl.java, v 0.1 2017年3月23日 下午2:29:32 Administrator Exp $
 */
@Service("assignedOrderQueryService")
public class AssignedOrderQueryServiceImpl implements AssignedOrderQueryService {

	private final Logger logger = LoggerFactory.getLogger(AssignedOrderQueryServiceImpl.class);

	@Resource
	private AssignedOrderQueryEngine assignedOrderQueryEngine;
	@Resource(name = "spuAssignQueryEngine")
	private SpuAssignQueryEngine spuAssignQueryEngine;
	@Resource(name = "queryCountAssignEngine")
	private QueryCountAssignEngine queryCountAssignEngine;

	/** 
	 * @see com.pzj.core.product.service.AssignedOrderQueryService#queryAssignedOrders(com.pzj.core.product.model.assign.AssignedOrderQueryReqModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<ArrayList<AssignedOrderQueryRespModel>> queryAssignedOrders(AssignedOrderQueryReqModel model,
			ServiceContext serviceContext) {
		Result<ArrayList<AssignedOrderQueryRespModel>> checkResult = checkParams(model);
		if (!checkResult.isOk()) {
			return checkResult;
		}
		ArrayList<AssignedOrderQueryRespModel> respModels = assignedOrderQueryEngine.queryAssignedOrders(model,
				serviceContext);
		return new Result<>(respModels);
	}

	private Result<ArrayList<AssignedOrderQueryRespModel>> checkParams(AssignedOrderQueryReqModel model) {
		Result<ArrayList<AssignedOrderQueryRespModel>> result = new Result<>();
		if (CheckUtils.isNull(model)) {
			CommonUtils.setParamErr(result);
			return result;
		}
		if (CheckUtils.isNull(model.getScreeningId())) {
			CommonUtils.setParamErr(result, "场次id为空");
			return result;
		}
		if (CheckUtils.isNull(model.getTheaterDate())) {
			CommonUtils.setParamErr(result, "演出时间为空");
			return result;
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.product.service.AssignedOrderQueryService#queryTheaterAssignCountOrders(com.pzj.core.product.model.assign.TheaterScreeingAssignReqModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<TheaterAssignRespModel> queryArtSpuAssignCountOrders(TheaterScreeingAssignReqModel reqModel,
			ServiceContext serviceContext) {
		Result<TheaterAssignRespModel> result = new Result<TheaterAssignRespModel>();
		if (reqModel == null || CommonUtils.checkLongIsNull(reqModel.getSupplierId(), true)
				|| reqModel.getShowTime() == null) {
			logger.error("query art spu assigin count order. illegal param:{}", JSONConverter.toJson(reqModel));
			CommonUtils.setParamErr(result);
			return result;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query art spu assigin count order .request param:{}", JSONConverter.toJson(reqModel));
		}

		try {
			TheaterAssignRespModel theaterAssignRespModel = spuAssignQueryEngine.queryArtSpuAssignCount(reqModel);
			result.setData(theaterAssignRespModel);
		} catch (Throwable e) {
			logger.error("query art spu assigin count order fail ,request:{}.", JSONConverter.toJson(reqModel), e);
			CommonUtils.convertException(e, result);
			return result;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("query art spu assigin count order .result :{}", JSONConverter.toJson(reqModel));
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.product.service.AssignedOrderQueryService#countAssignSeatTotal(java.lang.Long, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Integer> countAssignSeatTotal(Long supplierId, ServiceContext serviceContext) {
		Result<Integer> result = new Result<Integer>();
		if (logger.isDebugEnabled()) {
			logger.debug("query count assign seat total. reqesut param supplierId:{}", supplierId);
		}
		try {
			Integer count = queryCountAssignEngine.countTotalAssignSeats();
			result.setData(count);
		} catch (Throwable e) {
			logger.error("query count assign seat total fail ,request supplierId:{}.", supplierId, e);
			CommonUtils.convertException(e, result);
			return result;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query count assign seat total .result :{}", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.product.service.AssignedOrderQueryService#queryCalendarAssignIden(com.pzj.core.product.model.assign.CalendarAssignReqModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<CalendarAssignRespModel> queryCalendarAssignIden(CalendarAssignReqModel reqModel,
			ServiceContext serviceContext) {
		Result<CalendarAssignRespModel> result = new Result<CalendarAssignRespModel>();
		if (logger.isDebugEnabled()) {
			logger.debug("query calendar assign iden. reqesut param :{}", JSONConverter.toJson(reqModel));
		}
		try {
			CalendarAssignRespModel calendarAssignResp = queryCountAssignEngine.queryCalendarAssign(reqModel);
			result.setData(calendarAssignResp);
		} catch (Throwable e) {
			logger.error("query calendar assign iden fail ,request param:{}.", JSONConverter.toJson(reqModel), e);
			CommonUtils.convertException(e, result);
			return result;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("query calendar assign iden .result :{}", JSONConverter.toJson(result));
		}
		return result;
	}
}
