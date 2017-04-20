/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pzj.core.common.utils.StockRuleConstant;
import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.core.stock.model.StockRuleQueryRequestModel;
import com.pzj.core.stock.service.StockRuleQueryService;
import com.pzj.core.stock.service.StockRuleService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

/**
 * 
 * @author dongchunfu
 * @version $Id: StockRuleTest.java, v 0.1 2016年7月25日 下午5:22:45 dongchunfu Exp $
 */
public class StockRuleTest extends BaseTest {
	private final Logger logger = LoggerFactory.getLogger(StockRuleTest.class);
	@Autowired
	private StockRuleService stockRuleService;
	@Autowired
	private StockRuleQueryService stockRuleQueryService;
	ServiceContext context = new ServiceContext();

	//    @Test
	public void testInsert() {
		StockRuleModel StockRuleModel = getStockRuleModel();
		Result<Long> result = stockRuleService.createStockRule(StockRuleModel, context);
		System.out.println(result.getData());
	}

	@SuppressWarnings("deprecation")
	//    @Test
	public void testUpState() {
		StockRuleModel vo = getStockRuleModel();
		vo.setId(47L);
		vo.setState(1);
		Result<Integer> result = stockRuleService.updateStockRuleState(vo, context);
		System.out.println(result.getData());
	}

	//    @Test
	public void testqueryStockRuleById() {
		StockRuleQueryRequestModel request = new StockRuleQueryRequestModel();
		request.setRuleId(2L);
		Result<StockRuleModel> result = stockRuleQueryService.queryStockRuleById(request, context);
		System.out.println(result.getData());
	}

	//  @Test
	private StockRuleModel getStockRuleModel() {
		StockRuleModel vo = new StockRuleModel();
		vo.setName("测试库存规则1");
		vo.setCategory(1);
		vo.setType(StockRuleConstant.StockRuleType.DAILY);
		vo.setUpperLimit(10000);
		vo.setState(StockRuleConstant.StockRuleState.FORBIDDEN);
		vo.setSupplierId(1234567L);
		vo.setWhetherReturn(StockRuleConstant.WhetherReturn.NORETURN);
		return vo;
	}

	@Test
	public void testupdateStockRule() {
		StockRuleModel model = new StockRuleModel();
		model.setId(281L);
		model.setName("0902旅拍旅拍婚纱1");
		//        model.setState(StockStateEnum.AVAILABLE.getState());
		//        model.setSupplierId(2216619741563734L);
		Result<Integer> result = stockRuleService.updateStockRule(model, context);
		if (result.isOk())
			logger.info("updateStockRule result ====" + JSONConverter.toJson(result));
	}

	//    @Test
	public void testqueryStockRulesByParam() {
		StockRuleQueryRequestModel model = new StockRuleQueryRequestModel();
		//        model.setName("one");
		//        List<Long> ruleIds = new ArrayList<>();
		//        ruleIds.add(1L);
		//        ruleIds.add(2L);
		//        model.setRuleIds(ruleIds);
		model.setRuleId(198L);
		Result<ArrayList<StockRuleModel>> result = stockRuleQueryService.queryStockRulesByParam(model, context);
		if (result.isOk()) {
			if (result.getData() != null) {
				System.out.println(result.getData());
			}
		} else {
			System.out.println(result.getErrorMsg());
		}
	}

	//    @Test
	public void testqueryStockRule4Category() {
		StockRuleQueryRequestModel model = new StockRuleQueryRequestModel();
		//model.setName("one");
		List<Long> ruleIds = new ArrayList<>();
		ruleIds.add(1L);
		ruleIds.add(2L);
		model.setRuleIds(ruleIds);

		Result<StockRuleModel> result = stockRuleQueryService.queryStockRule4Category(model, context);
		if (result.isOk()) {
			if (result.getData() != null) {
				System.out.println(result.getData());
			}
		} else {
			System.out.println(result.getErrorMsg());
		}
	}

	//    @Test
	public void testdeleteStockRule() {

		StockRuleModel model = new StockRuleModel();
		model.setId(3L);
		Result<Integer> result = stockRuleService.fakeDeleteStockRule(model, context);
		if (result.isOk()) {
			if (result.getData() != null) {
				System.out.println(result.getData());
			}
		} else {
			System.out.println(result.getErrorMsg());
		}
	}

	//    @Test
	public void updateStockRuleName() {
		StockRuleModel model = new StockRuleModel();
		model.setId(90l);
		model.setName("峨眉山-123456789");
		stockRuleService.updateStockRuleName(model, context);
	}
}
