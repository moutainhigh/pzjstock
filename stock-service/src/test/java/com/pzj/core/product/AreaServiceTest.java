/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.pzj.core.product.model.area.AreaModel;
import com.pzj.core.product.model.area.AreaQueryReqModel;
import com.pzj.core.product.model.area.AreaQueryRequestModel;
import com.pzj.core.product.model.area.CreateAreaReqModel;
import com.pzj.core.product.model.area.ModifyAreaReqModel;
import com.pzj.core.product.model.area.TheaterAreaDetailRespModel;
import com.pzj.core.product.model.area.TheaterAreaReqModel;
import com.pzj.core.product.model.area.TheaterAreaRespModel;
import com.pzj.core.product.service.AreaQueryService;
import com.pzj.core.product.service.AreaService;
import com.pzj.framework.armyant.anno.OneCase;
import com.pzj.framework.armyant.anno.TestData;
import com.pzj.framework.armyant.junit.spring.ArmyantSpringRunner;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.entity.QueryResult;

/**
 * 
 * @author dongchunfu
 * @version $Id: AreaServiceTest.java, v 0.1 2016年8月1日 上午11:34:07 dongchunfu Exp $
 */
@RunWith(ArmyantSpringRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/applicationContext.xml" })
public class AreaServiceTest {
	@Resource
	private AreaService areaService;
	@Resource
	private AreaQueryService areaQueryService;

	AreaModel area = new AreaModel();
	ServiceContext context = new ServiceContext();

	//    @Test //创建测试
	public void testcreateArea() {

		area.setScenicId(1234567L);
		area.setName("演绎测试区域一");
		area.setCode("YYAY");
		Result<Long> result = areaService.createArea(area, context);
		if (result.getData() == null) {
			System.out.println(result.getErrorMsg());
		} else {
			System.out.println(result.getData());
		}
	}

	//    @Test
	//修改测试
	public void testUpdate() {
		area.setId(112L);
		area.setName("修改测试");
		Result<Integer> result = areaService.updateArea(area, context);
		if (result.getData() == null) {
			System.out.println(result.getErrorMsg());
		} else {
			System.out.println(result.getData());
		}

	}

	//    @Test
	//主键查测试
	public void testqueryAreaById() {
		AreaQueryRequestModel request = new AreaQueryRequestModel();
		request.setAreaId(112L);
		Result<AreaModel> result = areaQueryService.queryAreaById(request, context);
		if (result.getData() == null) {
			System.out.println(result.getErrorMsg());
		} else {
			System.out.println(result.getData());
		}
	}

	//    @Test
	//参数查测试
	public void testqueryAreasByParam() {

		AreaQueryRequestModel model = new AreaQueryRequestModel();
		model.setScenicId(2216619736563715L);
		Result<ArrayList<AreaModel>> result = areaQueryService.queryAreasByWebSeatChartParam(model, context);
		if (result.getData() == null) {
			System.out.println(result.getErrorMsg());
		} else {
			System.out.println(result.getData());
		}
	}

	//@Test
	@OneCase("/com/pzj/core/product/AreaService/createArea.json")
	public void createArea(@TestData CreateAreaReqModel createAreaReqModel) {
		Result<Boolean> result = areaService.createArea(createAreaReqModel);

		assertNotNull(result);
		assertTrue(result.isOk());
		assertNotNull(result.getData());

		System.out.println(JSONConverter.toJson(result));
	}

	//@Test
	@OneCase("/com/pzj/core/product/AreaService/modifyArea.json")
	public void modifyArea(@TestData ModifyAreaReqModel modifyAreaReqModel) {
		Result<Boolean> result = areaService.modifyArea(modifyAreaReqModel);

		assertNotNull(result);
		assertTrue(result.isOk());
		assertNotNull(result.getData());
	}

	@Test
	public void queryArea() {
		AreaQueryReqModel areaQueryReqModel = new AreaQueryReqModel();
		areaQueryReqModel.setSupplierId(2216619741563911l);
		areaQueryReqModel.setCurrentPage(2);
		areaQueryReqModel.setPageSize(1);
		Result<QueryResult<TheaterAreaRespModel>> result = areaQueryService.queryAreas(areaQueryReqModel,
				new ServiceContext());
		System.out.println(JSONConverter.toJson(result));
	}

	//	@Test
	public void testQueryAreaDetail() {
		Long supplierId = 123456789L, theaterId = 22345678L;
		TheaterAreaReqModel reqModel = new TheaterAreaReqModel();
		reqModel.setSupplierId(supplierId);
		reqModel.setTheaterId(theaterId);
		Result<TheaterAreaDetailRespModel> result = areaQueryService.queryAreaDetail(reqModel, context);

		System.out.println("=====testQueryAreaDetail" + JSONConverter.toJson(result));

	}
}
