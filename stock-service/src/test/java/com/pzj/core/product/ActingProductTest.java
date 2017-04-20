/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pzj.core.product.model.ActingProductModel;
import com.pzj.core.product.model.ActingProductQueryRequstModel;
import com.pzj.core.product.model.AreaModel;
import com.pzj.core.product.model.AreaScreeingsRelModel;
import com.pzj.core.product.model.ProScreeningsQueryModel;
import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.core.product.service.ActingProductService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

/**
 * 
 * @author dongchunfu
 * @version $Id: ActingProductTest.java, v 0.1 2016年8月8日 下午7:31:19 dongchunfu Exp $
 */
public class ActingProductTest {
	//    @Resource
	//    private ActingProductService actingProductService;

	static ApplicationContext context = null;

	@BeforeClass
	public static void setUpClass() {
		context = new ClassPathXmlApplicationContext("applicationContext-test.xml");
		System.out.println(context);
	}

	private ActingProductService actingProductService;

	@Before
	public void setUp() {
		actingProductService = context.getBean(ActingProductService.class);
	}

	//    @Test
	public void createActingProductTest() {
		// { "actingId":12214, "areas":[125,126], "screeingses":[1125,1126] },{}
		ActingProductModel model = new ActingProductModel();
		model.setActingId(12214L);

		ArrayList<AreaModel> areaList = new ArrayList<AreaModel>();
		AreaModel areaModel = new AreaModel();
		areaModel.setId(125L);
		areaList.add(areaModel);
		areaModel = new AreaModel();
		areaModel.setId(126L);
		areaList.add(areaModel);
		model.setAreas(areaList);

		ArrayList<ScreeingsModel> screeingses = new ArrayList<ScreeingsModel>();
		ScreeingsModel screeingsModel = new ScreeingsModel();
		screeingsModel.setId(1125L);
		screeingses.add(screeingsModel);
		screeingsModel = new ScreeingsModel();
		screeingsModel.setId(1126L);
		screeingses.add(screeingsModel);
		model.setScreeingses(screeingses);

		actingProductService.createActingProduct(model, new ServiceContext());
	}

	//	@Test
	public void testqueryInfo4CreateActingProduct() {
		//{ "scenicId":2216619741563804, "supplierId":2216619741564350, "actProId":5 },{}
		ActingProductQueryRequstModel model = new ActingProductQueryRequstModel();
		model.setScenicId(2216619741563745L);
		model.setSupplierId(2216619741563734L);
		Result<ArrayList<AreaScreeingsRelModel>> result = actingProductService.queryInfoForSkuProduct(model, null);

		if (result.isOk()) {
			System.out.println("testqueryInfo4CreateActingProduct result==" + JSONConverter.toJson(result));
		}
	}

	//    @Test
	public void testqueryInfoForCreateActingProduct() {
		ActingProductQueryRequstModel model = new ActingProductQueryRequstModel();
		model.setScenicId(2216619741563787L);
		model.setSupplierId(2216619741564303L);
		Result<ActingProductModel> result = actingProductService.queryInfoForCreateActingProduct(model,
				ServiceContext.getServiceContext());
		if (result.isOk()) {
			ActingProductModel data = result.getData();
			System.out.println(data);
		} else {
			System.out.println(result.getErrorMsg());
		}
	}

	//	@Test
	public void testqueryActProInfo() {
		ActingProductQueryRequstModel model = new ActingProductQueryRequstModel();
		model.setActProId(25L);
		Result<ActingProductModel> result = actingProductService.queryActProInfo(model,
				ServiceContext.getServiceContext());
		if (result.isOk()) {
			ActingProductModel data = result.getData();
			System.out.println(data);
		} else {
			System.out.println(result.getErrorMsg());
		}
	}

	//    @Test
	public void testQueryScreeningsByActPro() {
		ProScreeningsQueryModel model = new ProScreeningsQueryModel();
		model.setActProId(151L);
		Result<ScreeingsModel> result = actingProductService.queryScreeningsByActProductId(model, null);
		if (null != result && null != result.getData()) {
			ScreeingsModel screenings = result.getData();
			System.out.println("----场次名称------" + screenings.getName());
			System.out.println("----场次开始------" + screenings.getStartTime());
			System.out.println("----场次结束------" + screenings.getEndTime());
			System.out.println("----场次停售------" + screenings.getEndSaleTime());
		}
	}
}
