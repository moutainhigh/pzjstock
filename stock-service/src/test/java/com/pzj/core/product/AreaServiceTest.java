/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.pzj.core.product.model.AreaModel;
import com.pzj.core.product.model.AreaQueryRequestModel;
import com.pzj.core.product.service.AreaQueryService;
import com.pzj.core.product.service.AreaService;
import com.pzj.core.stock.BaseTest;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author dongchunfu
 * @version $Id: AreaServiceTest.java, v 0.1 2016年8月1日 上午11:34:07 dongchunfu Exp $
 */
public class AreaServiceTest extends BaseTest {
	@Autowired
	private AreaService areaService;
	@Autowired
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
}
