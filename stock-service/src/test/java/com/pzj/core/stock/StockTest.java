/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pzj.core.stock.model.CreateStockModel;
import com.pzj.core.stock.model.OccupyStockRequestModel;
import com.pzj.core.stock.model.StockQueryRequestModel;
import com.pzj.core.stock.service.StockService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;

/**
 * 
 * @author dongchunfu
 * @version $Id: StockRuleTest.java, v 0.1 2016年7月25日 下午5:22:45 dongchunfu Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-test.xml" })
public class StockTest {
    private static Logger  logger  = LoggerFactory.getLogger(StockTest.class);
    @Autowired
    private StockService   stockService;

    ServiceContext         context = ServiceContext.getServiceContext();
    StockQueryRequestModel request = new StockQueryRequestModel();

    //    @Test
    public void testcreatStock() {
        CreateStockModel model = new CreateStockModel();
        List<Long> ruleIds = new ArrayList<Long>();
        ruleIds.add(2L);
        model.setRuleIds(ruleIds);
        Result<Integer> result = stockService.createStock(model, context);
        if (result.getData() != null) {
            System.out.println(result.getData());
        } else {
            System.out.println(result.getErrorMsg());
        }
    }

    public static void main(String[] args) {
        ArrayList<OccupyStockRequestModel> modelList = new ArrayList<OccupyStockRequestModel>();
        OccupyStockRequestModel requestModel = new OccupyStockRequestModel();
        requestModel.setTransactionId("ab");
        requestModel.setProductId(1234L);
        requestModel.setStockId(234L);
        requestModel.setStockNum(1);
        modelList.add(requestModel);

        Object obj = modelList.get(0);
        if (obj instanceof Map) {
            logger.info("----obj instanceof Map----");
        } else if (obj instanceof OccupyStockRequestModel) {
            logger.info("----obj instanceof OccupyStockRequestModel----");
        }

        logger.info("modelList:{} requestModel:{}", JSONConverter.toJson(modelList), JSONConverter.toJson(requestModel));
        //        try {
        //            Object obj = JSON.parse("[{\"transactionId\":\"ab\",\"productId\":1234,\"stockId\":234,\"stockNum\":1}]");
        //            System.out.println(obj.getClass().getName());
        //        } catch (ParseException e) {
        //            e.printStackTrace();
        //        }
    }
}
