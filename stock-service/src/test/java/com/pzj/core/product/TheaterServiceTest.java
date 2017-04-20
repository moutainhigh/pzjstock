/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

import com.pzj.core.product.model.theater.TheaterCreateReqModel;
import com.pzj.core.product.model.theater.TheaterModifyReqModel;
import com.pzj.core.product.model.theater.TheaterQueryRespModel;
import com.pzj.core.product.service.TheaterQueryService;
import com.pzj.core.product.service.TheaterService;
import com.pzj.framework.armyant.junit.spring.ArmyantSpringRunner;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

/**
 * 
 * @author Administrator
 * @version $Id: TheaterServiceTest.java, v 0.1 2017年3月29日 下午4:44:15 Administrator Exp $
 */
@RunWith(ArmyantSpringRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/applicationContext.xml" })
public class TheaterServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(TheaterServiceTest.class);
	@Resource
	private TheaterService theaterService;
	@Resource
	private TheaterQueryService theaterQueryService;

	ServiceContext context = new ServiceContext();

	//@Test
	public void addTheaterInfo() {
		TheaterCreateReqModel model = new TheaterCreateReqModel();
		model.setTheaterId(99999l);
		model.setColumnNumber(9);
		model.setLineNumber(8);
		model.setSortType(1);
		model.setSeatState(3);
		Result<Boolean> result = theaterService.addTheaterInfo(model, context);
		logger.info("新增剧场信息返回{}", JSONConverter.toJson(result));
	}

	@Test
	public void modifyTheaterInfo() {
		TheaterModifyReqModel model = new TheaterModifyReqModel();
		model.setTheaterId(99999l);
		model.setColumnNumber(12);
		model.setLineNumber(12);
		Result<Boolean> result = theaterService.modifyTheaterInfo(model, context);
		logger.info("修改剧场信息返回{}", JSONConverter.toJson(result));
	}

	//@Test
	public void queryTheaterByTheaterId() {
		Result<TheaterQueryRespModel> result = theaterQueryService.queryTheaterByTheaterId(-1l, context);
		logger.info("查询剧场信息返回{}", JSONConverter.toJson(result));
	}
}
