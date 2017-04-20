/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pzj.core.stock.model.QueryStockRuleByIdsModel;
import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.core.stock.model.StockRuleQueryRequestModel;
import com.pzj.core.stock.service.StockRuleQueryService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

/**
 * 
 * @author Administrator
 * @version $Id: StockRuleQueryTest.java, v 0.1 2016年8月30日 下午3:04:28 Administrator Exp $
 */
public class StockRuleQueryTest {
	static ApplicationContext context = null;

	@BeforeClass
	public static void setUpClass() {
		context = new ClassPathXmlApplicationContext("applicationContext-test.xml");
		System.out.println(context);
	}

	private StockRuleQueryService stockRuleQueryService;

	@Before
	public void setUp() {
		stockRuleQueryService = context.getBean(StockRuleQueryService.class);
	}

	//    @Test
	public void queryStockRuleById() {
		StockRuleQueryRequestModel model = new StockRuleQueryRequestModel();
		model.setRuleId(89L);
		stockRuleQueryService.queryStockRuleById(model, null);
	}

	//    @Test
	public void queryStockRuleByParamTest() {
		StockRuleQueryRequestModel model = new StockRuleQueryRequestModel();
		model.setName("0826班车开发测试班车1测试");
		stockRuleQueryService.queryStockRulesByParam(model, null);
	}

	//    @Test
	public void testqueryStockRulePage() {
		StockRuleQueryRequestModel request = new StockRuleQueryRequestModel();
		request.setSupplierId(2216619746563732L);
		//        request.setCurrentPage(1);
		//        request.setPageSize(20);
		stockRuleQueryService.queryStockRulePage(request, new ServiceContext());
	}

	@Test
	public void queryStockRuleByIds() {
		QueryStockRuleByIdsModel model = new QueryStockRuleByIdsModel();
		List<Long> stockRuleIds = new ArrayList<Long>();
		//        stockRuleIds.add(154L);
		//2216619741565282, 2216619741565283, 2216619741565284
		stockRuleIds.add(2216619741565282L);
		stockRuleIds.add(2216619741565283L);
		stockRuleIds.add(2216619741565284L);
		model.setStockRuleIds(stockRuleIds);
		//        model.setState(3);
		Result<ArrayList<StockRuleModel>> result = stockRuleQueryService.queryStockRulesByIds(model, null);

		System.out.println("queryStockRuleByIds:" + JSONConverter.toJson(result));
	}
}
