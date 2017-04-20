/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pzj.core.stock.model.CheckStockModel;
import com.pzj.core.stock.model.StockDateListQueryModel;
import com.pzj.core.stock.model.StockModel;
import com.pzj.core.stock.model.StockQueryRequestModel;
import com.pzj.core.stock.model.StockRealTimeModel;
import com.pzj.core.stock.model.StockRealTimeQueryModel;
import com.pzj.core.stock.model.SupplierLockStockModel;
import com.pzj.core.stock.model.SupplierLockStockQueryModel;
import com.pzj.core.stock.model.UserOccupyStockModel;
import com.pzj.core.stock.service.StockQueryService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

/**
 * 
 * @author Administrator
 * @version $Id: StockQueryServiceTest.java, v 0.1 2016年7月22日 下午6:09:31 Administrator Exp $
 */
public class StockQueryServiceTest {
	private final Logger logger = LoggerFactory.getLogger(StockQueryServiceTest.class);

	static ApplicationContext context = null;

	@BeforeClass
	public static void setUpClass() {
		context = new ClassPathXmlApplicationContext("applicationContext-test.xml");
		System.out.println(context);
	}

	private StockQueryService stockQueryService;

	@Before
	public void setUp() {
		stockQueryService = context.getBean(StockQueryService.class);
	}

	@Test
	public void queryStockByIdTest() {
		StockQueryRequestModel model = new StockQueryRequestModel();
		//		model.setStockId(233L);
		Result<StockModel> result = stockQueryService.queryStockById(model, new ServiceContext());
		System.out.println("queryStockByIdTest result===" + JSONConverter.toJson(result));
	}

	//    @Test
	public void testQueryStock() {
		StockQueryRequestModel request = new StockQueryRequestModel();
		Long stockId = -374L;
		request.setStockId(stockId);
		ServiceContext serviceContext = ServiceContext.getServiceContext();
		Result<StockModel> result = stockQueryService.queryStockById(request, serviceContext);
		if (!result.isOk())
			System.out.println("getErrorMsg=" + result.getErrorMsg());
		if (null != result && null != result.getData())
			System.out.println("=======stock==========" + result.getData().getUsedNum());
	}

	//	@Test
	public void queryStockByRule() {
		StockQueryRequestModel model = new StockQueryRequestModel();
		model.setRuleId(154L);
		model.setStockTime("20160902");
		Result<StockModel> result = stockQueryService.queryStockByRule(model, new ServiceContext());
		if (!result.isOk()) {
			logger.debug("======queryStockByRule getErrorMsg===" + result.getErrorMsg());
		}
		logger.debug("queryStockByRule result ======" + JSONConverter.toJson(result));
	}

	//	@Test
	public void queryStockBySupplierOpeStock() {
		//{"stockRuleId": 15400,"stockTimeList":["20170210","20161130"]},{}
		StockDateListQueryModel model = new StockDateListQueryModel();
		model.setStockRuleId(154L);
		ArrayList<String> stockTimeList = new ArrayList<String>();
		stockTimeList.add("20160830");
		stockTimeList.add("20161231");
		model.setStockTimeList(stockTimeList);
		Result<ArrayList<StockModel>> result = stockQueryService.querySupplierBatchOperateStockList(model,
				new ServiceContext());
		if (!result.isOk())
			logger.info("======querySupplierBatchOperateStockList getErrorMsg===" + result.getErrorMsg());

		logger.info("queryStockBySupplierOpeStock result===" + JSONConverter.toJson(result));
	}

	//    @Test
	public void queryStockByDateList() {
		StockDateListQueryModel model = new StockDateListQueryModel();
		model.setStockRuleId(154L);
		ArrayList<String> stockTimeList = new ArrayList<String>();
		stockTimeList.add("2016-08-31");
		stockTimeList.add("2016-09-01");
		stockTimeList.add("2016-09-02");
		model.setStockTimeList(stockTimeList);
		Result<ArrayList<StockModel>> result = stockQueryService.queryStockByDateList(model, null);
		if (!result.isOk()) {
			logger.info("======queryStockByDateList getErrorMsg===" + result.getErrorMsg());
		}
		logger.info("queryStockByDateList result" + JSONConverter.toJson(result));
	}

	//    @Test
	public void queryUserBatchOccupyStock() {
		UserOccupyStockModel model = new UserOccupyStockModel();
		model.setStockRuleId(156L);
		//        model.setUserId(2216619741564208L);
		Result<UserOccupyStockModel> result = stockQueryService.queryUserBatchOccupyStock(model, null);
		//        UserOccupyStockModel userstock = result.getData();
		//        List<StockModel> stockModelList = userstock.getStockModelList();
		if (!result.isOk())
			logger.info("======queryStockByDateList getErrorMsg===" + JSONConverter.toJson(result));

		logger.info("queryUserBatchOccupyStock result======" + JSONConverter.toJson(result));
	}

	//    @Test
	//    public void queryStockByDate() {
	//        StockQueryRequestModel model = new StockQueryRequestModel();
	//        model.setRuleId(113L);
	//        model.setStockTime("2016-08-26");
	//        Result<StockModel> result = stockQueryService.queryStockByDate(model, ServiceContext.getServiceContext());
	//        StockModel stockModel = result.getData();
	//        if (!result.isOk())
	//            System.out.println("======queryStockByDate getErrorMsg===" + result.getErrorMsg());
	//        if (null != stockModel) {
	//            System.out.println("=======stock id==========" + stockModel.getId());
	//            System.out.println("=======stock userNum==========" + stockModel.getUsedNum());
	//        }
	//        System.out.println(stockModel);
	//    }

	//    @Test
	public void judgeStockIsEnough() {
		CheckStockModel checkStockModel = new CheckStockModel();
		checkStockModel.setStockRuleId(1234L);
		//        checkStockModel.setStockTime("20160906");
		checkStockModel.setStockId(234L);
		checkStockModel.setOccupyNum(2);
		checkStockModel.setQueryType(1);
		Result<Boolean> result = stockQueryService.judgeStockIsEnough(checkStockModel, null);
		if (result.isOk()) {
			logger.info("judgeStockIsEnough result===" + JSONConverter.toJson(result));
		}
	}

	//    @Test
	public void queryallStocks() {
		StockQueryRequestModel model = new StockQueryRequestModel();
		model.setRuleId(2L);
		model.setStockTime("2016-08-26");

		List<Long> stockIds = new ArrayList<Long>();
		//stockIds.add(26L);

		model.setStockIds(stockIds);
		ServiceContext context2 = new ServiceContext();
		Result<ArrayList<StockModel>> result = stockQueryService.queryAllStocks(model, context2);
		ArrayList<StockModel> data = result.getData();
		if (!result.isOk())
			System.out.println("======queryStockByDate getErrorMsg===" + result.getErrorMsg());
		if (null != data)
			System.out.println("=======stock id==========" + data);
		System.out.println("=======stock id==========" + data.size());
	}

	//    @Test
	public void querySupplierRealTimeStock() throws Exception {
		String timeStr = "20160930";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date stockDate = sdf.parse(timeStr);
		List<Long> stockRuleIds = new ArrayList<Long>();
		stockRuleIds.add(302L);
		StockRealTimeQueryModel model = new StockRealTimeQueryModel();
		model.setSupplierId(2216619741563734L);
		model.setStockDate(stockDate);
		model.setStockRuleIdList(stockRuleIds);
		Result<ArrayList<StockRealTimeModel>> result = stockQueryService.querySupplierRealTimeStockList(model, null);
		if (result.isOk()) {
			logger.info("querySupplierRealTimeStockList result====" + JSONConverter.toJson(result));
		}
	}

	//    @Test
	public void querySupplierLockStockTest() {
		SupplierLockStockQueryModel model = new SupplierLockStockQueryModel(233L, 3L);

		Result<SupplierLockStockModel> result = stockQueryService.querySupplierLockStock(model, null);
		if (result.isOk()) {
			logger.info("querySupplierLockStockTest result=======" + JSONConverter.toJson(result));
		}
	}

	//    @Test
	public void rangeDateQueryStockTest() {
		StockQueryRequestModel model = new StockQueryRequestModel();
		model.setRuleId(154L);
		model.setBeginStockTime("20160901");
		model.setEndStockTime("20160902");
		Result<ArrayList<StockModel>> result = stockQueryService.queryRangeStocks(model, new ServiceContext());
		if (result.isOk()) {
			logger.info("rangeDateQueryStockTest result===" + JSONConverter.toJson(result));
		}
	}

	//    @Test
	public void queryMonthStocksTest() {
		StockQueryRequestModel model = new StockQueryRequestModel();
		model.setRuleId(154L);
		model.setQueryMonth("20161115");
		Result<ArrayList<StockModel>> result = stockQueryService.queryMonthStocks(model, new ServiceContext());
		if (result.isOk()) {
			logger.info("queryMonthStocksTest result=" + JSONConverter.toJson(result));
		}
	}
}
