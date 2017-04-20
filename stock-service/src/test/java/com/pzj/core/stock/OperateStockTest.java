/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.enums.OperateStockBussinessType;
import com.pzj.core.stock.model.CreateStockModel;
import com.pzj.core.stock.model.OccupyStockRequestModel;
import com.pzj.core.stock.model.StockBatchLockModel;
import com.pzj.core.stock.model.SupplierManualLockModel;
import com.pzj.core.stock.model.SupplierManualUnlockModel;
import com.pzj.core.stock.service.StockService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.exception.ServiceException;

/**
 * 
 * @author Administrator
 * @version $Id: OperateStockTest.java, v 0.1 2016年8月10日 下午1:48:46 Administrator Exp $
 */
public class OperateStockTest {
    Logger                    logger  = LoggerFactory.getLogger(OperateStockTest.class);
    static ApplicationContext context = null;

    @BeforeClass
    public static void setUpClass() {
        context = new ClassPathXmlApplicationContext("applicationContext-test.xml");
        System.out.println(context);
    }

    private StockService stockService;

    @Before
    public void setUp() {
        stockService = context.getBean(StockService.class);
    }

    //    @Test
    public void createStock() {
        ServiceContext context = new ServiceContext();

        CreateStockModel model = new CreateStockModel();
        List<Long> ruleIds = new ArrayList<Long>();
        ruleIds.add(2216619741565012L);
        //        ruleIds.add(283L);
        model.setRuleIds(ruleIds);
        Result<Integer> result = stockService.createStock(model, context);
        logger.info("---------createStock result data:-------" + JSONConverter.toJson(result));
    }

    //    @Test
    public void testOccupyStock() {
        ArrayList<OccupyStockRequestModel> modelList = new ArrayList<OccupyStockRequestModel>();
        OccupyStockRequestModel requestModel = new OccupyStockRequestModel();
        requestModel.setTransactionId("ab");
        requestModel.setProductId(1234L);
        requestModel.setStockId(234L);
        requestModel.setStockNum(1);
        modelList.add(requestModel);
        requestModel = new OccupyStockRequestModel();
        requestModel.setTransactionId("ab");
        requestModel.setProductId(123L);
        requestModel.setStockId(235L);
        requestModel.setStockNum(1);
        modelList.add(requestModel);
        //        requestModel = new OccupyStockRequestModel();
        //        requestModel.setTransactionId("ab");
        //        requestModel.setProductId(1234L);
        //        requestModel.setStockId(234L);
        //        requestModel.setStockNum(1);
        //        modelList.add(requestModel);

        Result<Boolean> result = null;
        try {
            result = stockService.occupyStock(modelList, null);
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                logger.error("异常：" + e.getMessage());
            } else {
                logger.error("未处理异常：", e);
            }
        }
        if (CommonUtils.checkObjectIsNull(result)) {
            return;
        }
        if (result.isOk()) {
            System.out.println("操作返回结果：" + result.getData());
        } else {
            System.out.println("错误信息：" + result.getErrorMsg());
        }
    }

    //    @Test
    public void testRollbackStock() {
        OccupyStockRequestModel requestModel = new OccupyStockRequestModel();
        requestModel.setTransactionId("ab");
        Result<Boolean> result = stockService.rollbackOccupyStock(requestModel, null);
        if (CommonUtils.checkObjectIsNull(result)) {
            return;
        }
        if (result.isOk()) {
            System.out.println("回滚库存 操作返回结果：" + result.getData());
        } else {
            System.out.println("回滚库存 错误信息：" + result.getErrorMsg());
        }
    }

    //    @Test
    public void testReleaseStock() {
        ArrayList<OccupyStockRequestModel> modelList = new ArrayList<OccupyStockRequestModel>();
        OccupyStockRequestModel requestModel = new OccupyStockRequestModel();
        requestModel.setTransactionId("ab");
        requestModel.setProductId(123L);
        requestModel.setStockId(234L);
        requestModel.setStockNum(1);
        modelList.add(requestModel);
        requestModel = new OccupyStockRequestModel();
        requestModel.setTransactionId("ab");
        requestModel.setProductId(1234L);
        requestModel.setStockId(234L);
        requestModel.setStockNum(1);
        modelList.add(requestModel);

        Result<Boolean> result = null;
        try {
            result = stockService.releaseStock(modelList, null);
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                logger.error("异常：" + e.getMessage());
            } else {
                logger.error("未处理异常：", e);
            }
        }

        if (!result.isOk()) {
            System.out.println("错误信息：" + result.getErrorMsg());
        }
    }

    @Test
    public void batchOccupyStock() {
        //        int count = 0;
        //        while (count < 100) {
        //            count++;

        // {"operateStockMap":{"12121023": 2,"12121024": 2},"operateType": 4,"userId": 2216619746563732},{}
        // {"operateStockMap":{"21023": 2,"242": 2},"operateType": 5,"userId": 2216619746563732},{}
        StockBatchLockModel stockBatchLockModel = new StockBatchLockModel();
        Map<Long, Integer> operateStockMap = new HashMap<Long, Integer>();
        operateStockMap.put(21023L, 2);
        operateStockMap.put(242L, 2);
        stockBatchLockModel.setOperateStockMap(operateStockMap);
        stockBatchLockModel.setOperateType(OperateStockBussinessType.BATCH_RELEASE_STOCK.key);
        stockBatchLockModel.setUserId(2216619746563732L);

        Result<Boolean> result = null;
        try {
            result = stockService.stockBatchLock(stockBatchLockModel, null);
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                logger.error("异常：" + e.getMessage());
            } else {
                logger.error("未处理异常：", e);
            }
        }
        if (!result.isOk()) {
            System.out.println("错误信息：" + result.getErrorMsg());
        } else {
            System.out.println("操作返回结果：" + result.getData());
        }
        //        }

    }

    //    @Test
    public void testManualLockStock() {
        int i = 1;
        //        for (int i = 1; i < 10; i++) {
        SupplierManualLockModel manualLockModel = new SupplierManualLockModel();
        manualLockModel.setLockNum(i);
        manualLockModel.setStockId(233L);
        manualLockModel.setSupplierId(3L);

        Result<Boolean> result = null;
        try {
            result = stockService.supplierManualLockStock(manualLockModel, null);
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                logger.error("异常：" + e.getMessage());
            } else {
                logger.error("未处理异常：", e);
            }
        }

        if (!result.isOk()) {
            System.out.println(System.currentTimeMillis() + "锁库存数：" + i + "；错误信息：" + result.getErrorMsg());
        } else {
            System.out.println(System.currentTimeMillis() + "锁库存数：" + i + "；操作返回结果：" + result.getData());
        }
        //        }

    }

    //    @Test
    public void testManualUnlockStock() {
        for (int i = 1; i < 10; i++) {
            SupplierManualUnlockModel manualUnlockModel = new SupplierManualUnlockModel();
            manualUnlockModel.setUnlockNum(i);
            manualUnlockModel.setStockId(233L);
            manualUnlockModel.setSupplierId(3L);

            Result<Boolean> result = null;
            try {
                result = stockService.supplierManualUnlockStock(manualUnlockModel, null);
            } catch (Exception e) {
                if (e instanceof ServiceException) {
                    logger.error("异常：" + e.getMessage());
                } else {
                    logger.error("未处理异常：", e);
                }
            }
            if (!result.isOk()) {
                System.out.println(System.currentTimeMillis() + "释放库存数：" + i + "；错误信息：" + result.getErrorMsg());
            } else {
                System.out.println(System.currentTimeMillis() + "释放库存数：" + i + "；操作返回结果：" + result.getData());
            }
        }

    }
}
