/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

import com.pzj.core.product.model.assign.AssignedOrderQueryReqModel;
import com.pzj.core.product.model.assign.AssignedOrderQueryRespModel;
import com.pzj.core.product.model.assign.CalendarAssignReqModel;
import com.pzj.core.product.model.assign.CalendarAssignRespModel;
import com.pzj.core.product.model.assign.TheaterAssignRespModel;
import com.pzj.core.product.model.assign.TheaterScreeingAssignReqModel;
import com.pzj.core.product.service.AssignedOrderQueryService;
import com.pzj.framework.armyant.junit.spring.ArmyantSpringRunner;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

/**
 * 
 * @author Administrator
 * @version $Id: AssignedOrderTest.java, v 0.1 2017年3月23日 下午3:26:33 Administrator Exp $
 */
@RunWith(ArmyantSpringRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/applicationContext.xml" })
public class AssignedOrderTest {
	private static final Logger logger = LoggerFactory.getLogger(AssignedOrderTest.class);
	@Resource
	private AssignedOrderQueryService assignedOrderQueryService;
	ServiceContext serviceContext = new ServiceContext();

	//	@Test
	public void queryAssignedOrders() {
		AssignedOrderQueryReqModel model = new AssignedOrderQueryReqModel();
		model.setScreeningId(111l);
		model.setTheaterDate(new Date());
		Result<ArrayList<AssignedOrderQueryRespModel>> result = assignedOrderQueryService.queryAssignedOrders(model,
				serviceContext);
		logger.info("查询待分配列表返回结果:{}", JSONConverter.toJson(result));
	}

	//	@Test
	public void queryArtSpuAssignCountOrders() {
		TheaterScreeingAssignReqModel reqModel = new TheaterScreeingAssignReqModel();
		reqModel.setSupplierId(3121242274529281L);
		String time = "2017-03-01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		reqModel.setShowTime(date);
		Result<TheaterAssignRespModel> result = assignedOrderQueryService.queryArtSpuAssignCountOrders(reqModel, null);
		System.out.println("queryArtSpuAssignCountOrders:" + JSONConverter.toJson(result));
	}

	//	@Test
	public void countAssignSeatTotal() {
		Long supplierId = 111222L;
		Result<Integer> result = assignedOrderQueryService.countAssignSeatTotal(supplierId, null);
		logger.info("countAssignSeatTotal result:{}", JSONConverter.toJson(result));
	}

	@Test
	public void queryCalendarAssignIden() {
		CalendarAssignReqModel reqModel = new CalendarAssignReqModel();
		reqModel.setSupplierId(111222L);
		reqModel.setCalendarDate(201703);
		Result<CalendarAssignRespModel> result = assignedOrderQueryService.queryCalendarAssignIden(reqModel, null);
		logger.info("queryCalendarAssignIden result:{}", JSONConverter.toJson(result));
	}
}
