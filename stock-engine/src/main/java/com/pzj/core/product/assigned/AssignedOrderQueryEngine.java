/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.assigned;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.commons.utils.DateUtils;
import com.pzj.core.product.entity.AssignedOrder;
import com.pzj.core.product.entity.AssignedOrderQuery;
import com.pzj.core.product.model.assign.AssignedOrderQueryReqModel;
import com.pzj.core.product.model.assign.AssignedOrderQueryRespModel;
import com.pzj.core.product.read.AssignedOrderReadMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 待分配列表核心层处理
 * @author Administrator
 * @version $Id: AssignedOrderQueryEngine.java, v 0.1 2017年3月23日 下午2:48:41 Administrator Exp $
 */
@Component("assignedOrderQueryEngine")
@Transactional(value = "stock.transactionManager")
public class AssignedOrderQueryEngine {
	private static final Logger logger = LoggerFactory.getLogger(AssignedOrderQueryEngine.class);
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	@Resource
	private AssignedOrderReadMapper assignedOrderReadMapper;

	public ArrayList<AssignedOrderQueryRespModel> queryAssignedOrders(AssignedOrderQueryReqModel model,
			ServiceContext serviceContext) {
		AssignedOrderQuery assignedOrderQuery = convertAssignedOrderQuery(model);
		List<AssignedOrder> assignedOrders = assignedOrderReadMapper.queryAssignedOrdersByModel(assignedOrderQuery);
		ArrayList<AssignedOrderQueryRespModel> respModels = convertRespModel(assignedOrders);
		return respModels;

	}

	private AssignedOrderQuery convertAssignedOrderQuery(AssignedOrderQueryReqModel model) {
		AssignedOrderQuery assignedOrderQuery = new AssignedOrderQuery(model.getScreeningId(), DateUtils.getDateByType(
				model.getTheaterDate(), DATE_PATTERN));
		return assignedOrderQuery;
	}

	private ArrayList<AssignedOrderQueryRespModel> convertRespModel(List<AssignedOrder> assignedOrders) {
		ArrayList<AssignedOrderQueryRespModel> respModels = new ArrayList<>();
		for (AssignedOrder ass : assignedOrders) {
			AssignedOrderQueryRespModel model = new AssignedOrderQueryRespModel(ass.getAssignedId(), ass.getState(),
					ass.getTransactionId(), ass.getAreaId(), ass.getAreaName(), ass.getOccupiedNum(),
					ass.getUnOccupiedNum());
			respModels.add(model);
		}
		return respModels;
	}
}
