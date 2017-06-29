/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import com.pzj.framework.armyant.junit.spring.ArmyantSpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pzj.core.product.model.screeings.ArtSpuScreeingOrderModel;
import com.pzj.core.product.model.screeings.CreateScreeningsReqModel;
import com.pzj.core.product.model.screeings.ModifyScreeningReqModel;
import com.pzj.core.product.model.screeings.ScreeingsModel;
import com.pzj.core.product.model.screeings.ScreeingsQueryRequestModel;
import com.pzj.core.product.model.screeings.TheaterScreeingOrderReqModel;
import com.pzj.core.product.model.screeings.TheaterScreeingReqModel;
import com.pzj.core.product.model.screeings.TheaterScreeingRespModel;
import com.pzj.core.product.service.ScreeingsQueryService;
import com.pzj.core.product.service.ScreeingsService;
import com.pzj.framework.armyant.anno.OneCase;
import com.pzj.framework.armyant.anno.TestData;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.entity.QueryResult;

/**
 * 演绎场次测试
 * 
 * @author dongchunfu
 * @version $Id: ScreeingsServiceTest.java, v 0.1 2016年8月1日 下午2:48:55 dongchunfu Exp $
 */
@RunWith(ArmyantSpringRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring/applicationContext.xml" })
public class ScreeingsServiceTest {

	private final Logger logger = LoggerFactory.getLogger(ScreeingsServiceTest.class);

	@Resource
	private ScreeingsService screeingsService;
	@Resource
	private ScreeingsQueryService screeingsQueryService;

	ScreeingsModel screeingsModel = new ScreeingsModel();
	ServiceContext serviceContext = new ServiceContext();

	//
	//    @Test
	public void testcreateScreeings() {
		screeingsModel.setScenicId(1234567L);
		screeingsModel.setName("演绎场次区域二");
		screeingsModel.setCode("YYSY");
		screeingsModel.setStartTime("2030");
		screeingsModel.setEndTime("2230");
		Result<Long> result = screeingsService.createScreeings(screeingsModel, null);
		if (result.getData() == null) {
			System.out.println(result.getErrorMsg());
		} else {
			System.out.println(result.getData());
		}
	}

	//	@Test
	public void testupdateScreeings() {

		screeingsModel.setId(1123L);
		screeingsModel.setName("修改测试");
		Result<Integer> result = screeingsService.updateScreeings(screeingsModel, null);
		if (result.getData() == null) {
			System.out.println(result.getErrorMsg());
		} else {
			System.out.println(result.getData());
		}
	}

	//	@Test
	public void testqueryScreeingsesByParam() {
		ScreeingsQueryRequestModel request = new ScreeingsQueryRequestModel();
		//request.setScreeingsId(1123L);
		request.setScenicId(1234567L);
		Result<ArrayList<ScreeingsModel>> result = screeingsQueryService.queryScreeingsesByParam(request, null);
		if (result.getData() == null) {
			System.out.println(result.getErrorMsg());
		} else {
			System.out.println(result.getData());
		}
	}

	//	@Test
	public void testqueryAreaById() {
		ScreeingsQueryRequestModel request = new ScreeingsQueryRequestModel();
		request.setScreeingsId(1123L);
		Result<ScreeingsModel> result = screeingsQueryService.queryScreeingsById(request, null);
		if (result.getData() == null) {
			System.out.println(result.getErrorMsg());
		} else {
			System.out.println(result.getData());
		}
	}

	@Test
	public void testQueryTheaterScreeing() {
		TheaterScreeingReqModel reqModel = new TheaterScreeingReqModel();
		reqModel.setSupplierId(2216619741563734L);
		Result<QueryResult<TheaterScreeingRespModel>> result = screeingsQueryService.queryTheaterScreeings(reqModel,
				null);
		logger.info("testQueryTheaterScreeing result : {}", JSONConverter.toJson(result));
	}

	@Test
	@OneCase("/com/pzj/core/product/ScreeningService/createScreenings.json")
	public void createScreenings(@TestData CreateScreeningsReqModel createScreeningsReqModel) {
		Result<Long> result = screeingsService.createScreenings(createScreeningsReqModel);

		//		assertNotNull(result);
		//		assertTrue(result.isOk());
		//		assertNotNull(result.getData());

		System.out.println(JSONConverter.toJson(result));
	}

	//	@Test
	@OneCase("/com/pzj/core/product/ScreeningService/modifyScreenings.json")
	public void modifyScreenings(@TestData ModifyScreeningReqModel modifyScreeningReqModel) {
		Result<Boolean> result = screeingsService.modifyScreenings(modifyScreeningReqModel);

		//		assertNotNull(result);
		//		assertTrue(result.isOk());
		//		assertNotNull(result.getData());

		System.out.println(JSONConverter.toJson(result));
	}

	@Test
	public void testQueryScreeingTheaterOrder() {
		TheaterScreeingOrderReqModel reqModel = new TheaterScreeingOrderReqModel();
		reqModel.setSupplierId(2216619741563734L);
		reqModel.setShowTime(new Date());
		Result<QueryResult<ArtSpuScreeingOrderModel>> result = screeingsQueryService.queryArtSpuScreeingsOrderByPage(
				reqModel, null);

		System.out.println("=========testQueryScreeingTheaterOrder" + JSONConverter.toJson(result));
	}

	@Test
	public void testTheaterScreeingsDetail() {
		Long screeingId = 1130L;
		Result<ScreeingsModel> screeing = screeingsQueryService.queryScreeingDetail(screeingId, null);
		System.out.println("=========testTheaterScreeingsDetail:" + JSONConverter.toJson(screeing));
	}
}
