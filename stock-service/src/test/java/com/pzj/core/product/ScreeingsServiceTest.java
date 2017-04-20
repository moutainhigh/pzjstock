/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.core.product.model.ScreeingsQueryRequestModel;
import com.pzj.core.product.service.ScreeingsQueryService;
import com.pzj.core.product.service.ScreeingsService;
import com.pzj.core.stock.BaseTest;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 演绎场次测试
 * 
 * @author dongchunfu
 * @version $Id: ScreeingsServiceTest.java, v 0.1 2016年8月1日 下午2:48:55 dongchunfu Exp $
 */
public class ScreeingsServiceTest extends BaseTest {
	@Autowired
	private ScreeingsService screeingsService;
	@Autowired
	private ScreeingsQueryService screeingsQueryService;

	ScreeingsModel screeingsModel = new ScreeingsModel();
	ServiceContext serviceContext = new ServiceContext();

	//    @Test
	public void testcreateScreeings() {
		screeingsModel.setScenicId(1234567L);
		screeingsModel.setName("演绎场次区域二");
		screeingsModel.setCode("YYSY");
		screeingsModel.setStartTime("2030");
		screeingsModel.setEndTime("2230");
		Result<Long> result = screeingsService.createScreeings(screeingsModel, serviceContext);
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
		Result<Integer> result = screeingsService.updateScreeings(screeingsModel, serviceContext);
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
		Result<ArrayList<ScreeingsModel>> result = screeingsQueryService.queryScreeingsesByParam(request,
				serviceContext);
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
		Result<ScreeingsModel> result = screeingsQueryService.queryScreeingsById(request, serviceContext);
		if (result.getData() == null) {
			System.out.println(result.getErrorMsg());
		} else {
			System.out.println(result.getData());
		}
	}

}
