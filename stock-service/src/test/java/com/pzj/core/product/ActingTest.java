/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pzj.core.product.model.ActingModel;
import com.pzj.core.product.model.ActingQueryRequestModel;
import com.pzj.core.product.service.ActingQueryService;
import com.pzj.core.product.service.ActingService;
import com.pzj.core.stock.BaseTest;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author dongchunfu
 * @version $Id: ActingTest.java, v 0.1 2016年8月1日 下午5:10:50 dongchunfu Exp $
 */
public class ActingTest extends BaseTest {
	@Autowired
	private ActingService actingService;
	@Autowired
	private ActingQueryService actingQueryService;

	ActingModel ActingModel = new ActingModel();
	ServiceContext serviceContext = ServiceContext.getServiceContext();

	//    @Test
	//新增
	public void testcreateActing() {
		ActingModel.setName("开发演艺");
		ActingModel.setScenicId(2216619741563787L);
		List<Long> screeinsIds = new ArrayList<Long>();
		screeinsIds.add(1L);
		screeinsIds.add(2L);
		ActingModel.setScreeinsIds(screeinsIds);
		List<Long> areaIds = new ArrayList<Long>();
		areaIds.add(1L);
		areaIds.add(2L);
		ActingModel.setAreaIds(areaIds);
		ActingModel.setSupplierId(1234567L);
		ActingModel.setState(2);
		Result<Long> result = actingService.createActing(ActingModel, serviceContext);
		if (result.getData() == null) {
			System.out.println(result.getErrorMsg());
		} else {
			System.out.println(result.getData());
		}
	}

	//	@Test
	//修改
	public void testupdateActing() {
		ActingModel.setId(12213L);
		ActingModel.setName("修改测试");
		ActingModel.setScenicId(1234567L);

		List<Long> screeinsIds = new ArrayList<Long>();
		screeinsIds.add(3L);
		screeinsIds.add(4L);
		ActingModel.setScreeinsIds(screeinsIds);
		List<Long> areaIds = new ArrayList<Long>();
		areaIds.add(3L);
		areaIds.add(4L);
		ActingModel.setAreaIds(areaIds);

		ActingModel.setSupplierId(1234567L);
		ActingModel.setState(1);

		Result<Integer> result = actingService.updateActing(ActingModel, serviceContext);
		if (result.getData() == null) {
			System.out.println(result.getErrorMsg());
		} else {
			System.out.println(result.getData());
		}
	}

	//	@Test
	public void testqueryActingById() {

		ActingQueryRequestModel model = new ActingQueryRequestModel();
		model.setActingId(12213L);
		ServiceContext context = ServiceContext.getServiceContext();
		Result<ActingModel> result = actingQueryService.queryActingById(model, context);
		if (result.getData() == null) {
			System.out.println(result.getErrorMsg());
		} else {
			System.out.println(result.getData());
		}
	}

	//	@Test
	public void testqueryActingByParam() {

		ActingQueryRequestModel model = new ActingQueryRequestModel();
		model.setActingId(12213L);
		model.setSupplierId(1234567L);
		ServiceContext context = ServiceContext.getServiceContext();
		Result<ArrayList<ActingModel>> result = actingQueryService.queryActingByParam(model, context);
		if (result.getData() == null) {
			System.out.println(result.getErrorMsg());
		} else {
			System.out.println(result.getData());
		}
	}

}
