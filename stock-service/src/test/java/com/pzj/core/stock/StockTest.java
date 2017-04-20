/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pzj.core.stock.model.CreateStockModel;
import com.pzj.core.stock.model.StockQueryRequestModel;
import com.pzj.core.stock.model.UpdateAreaStockReqModel;
import com.pzj.core.stock.service.StockService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

/**
 * 
 * @author dongchunfu
 * @version $Id: StockRuleTest.java, v 0.1 2016年7月25日 下午5:22:45 dongchunfu Exp $
 */
public class StockTest {
	private static Logger logger = LoggerFactory.getLogger(StockTest.class);
	private StockService stockService;
	static ApplicationContext context = null;

	@BeforeClass
	public static void setUpClass() {
		context = new ClassPathXmlApplicationContext("applicationContext-test.xml");
		System.out.println(context);
	}

	@Before
	public void setUp() {
		stockService = context.getBean(StockService.class);
	}

	ServiceContext serviceContext = null;
	StockQueryRequestModel request = new StockQueryRequestModel();

	//    @Test
	public void testcreatStock() {
		CreateStockModel model = new CreateStockModel();
		List<Long> ruleIds = new ArrayList<Long>();
		ruleIds.add(2L);
		model.setRuleIds(ruleIds);
		Result<Integer> result = stockService.createStock(model, serviceContext);
		if (result.getData() != null) {
			System.out.println(result.getData());
		} else {
			System.out.println(result.getErrorMsg());
		}
	}

	//	@Test
	public void testUpdAreaStock() {
		UpdateAreaStockReqModel reqModel = new UpdateAreaStockReqModel();
		reqModel.setScreeingId(1L);
		reqModel.setAreaId(1L);
		reqModel.setNewestStockNum(1);
		reqModel.setShowTime(new Date());
		Result<Boolean> result = stockService.updateAreaStock(reqModel, null);
		System.out.println("=======testUpdAreaStock:" + JSONConverter.toJson(result));
	}

	public static void main(String[] args) {
		//		ArrayList<OccupyStockRequestModel> modelList = new ArrayList<OccupyStockRequestModel>();
		//		OccupyStockRequestModel requestModel = new OccupyStockRequestModel();
		//		requestModel.setTransactionId("ab");
		//		requestModel.setProductId(1234L);
		//		requestModel.setStockId(234L);
		//		requestModel.setStockNum(1);
		//		modelList.add(requestModel);
		//
		//		Object obj = modelList.get(0);
		//		if (obj instanceof Map) {
		//			logger.info("----obj instanceof Map----");
		//		} else if (obj instanceof OccupyStockRequestModel) {
		//			logger.info("----obj instanceof OccupyStockRequestModel----");
		//		}
		//
		//		logger.info("modelList:{} requestModel:{}", JSONConverter.toJson(modelList), JSONConverter.toJson(requestModel));
		//        try {
		//            Object obj = JSON.parse("[{\"transactionId\":\"ab\",\"productId\":1234,\"stockId\":234,\"stockNum\":1}]");
		//            System.out.println(obj.getClass().getName());
		//        } catch (ParseException e) {
		//            e.printStackTrace();
		//        }
	}
}
