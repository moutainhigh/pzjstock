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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pzj.core.stock.context.ShowResponse;
import com.pzj.core.stock.enums.OperateSeatBussinessType;
import com.pzj.core.stock.model.ShowModel;
import com.pzj.core.stock.service.ShowService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.exception.ServiceException;

/**
 * 
 * @author Administrator
 * @version $Id: ShowTest.java, v 0.1 2016年8月10日 下午6:44:40 Administrator Exp $
 */
public class ShowTest {

    Logger                    logger  = LoggerFactory.getLogger(ShowTest.class);
    static ApplicationContext context = null;

    @BeforeClass
    public static void setUpClass() {
        context = new ClassPathXmlApplicationContext("applicationContext-test.xml");
        System.out.println(context);
    }

    private ShowService showService;

    @Before
    public void setUp() {
        showService = context.getBean(ShowService.class);
    }

    //    @Test
    public void testOccupySeat() {
        //        {"operateBusiness":1,"transactionId":"abc","productId":2216619741564407,"seats":["A20_26"],"stockId":12826,"areaScreeingsId":5}
        ShowModel showModel = new ShowModel();
        List<String> seats = new ArrayList<String>();
        seats.add("A20_26");
        showModel.setSeats(seats);
        showModel.setTransactionId("abc");
        showModel.setProductId(2216619741564407L);
        showModel.setAreaScreeingsId(5L);
        showModel.setStockId(156L);
        //        showModel.setOperateBusiness(OperateSeatBussinessType.ORDER_OCCUPY_SEAT.key);
        try {
            showService.occupySeat(showModel, new ServiceContext());
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                logger.error("异常：" + e.getMessage());
            } else {
                logger.error("未处理异常：", e);
            }
        }
    }

    @Test
    public void testReleaseSeat() {
        //{"operateBusiness": 4,"transactionId": "abc","productId": 2216619741564407,"seats": ["A20_26","A20_120"],"stockId":12826,"areaScreeingsId":5},{}
        ShowModel showModel = new ShowModel();
        List<String> seats = new ArrayList<String>();
        seats.add("A20_26");
        seats.add("A20_120");
        showModel.setSeats(seats);
        showModel.setTransactionId("abc");
        showModel.setProductId(2216619741564407L);
        showModel.setAreaScreeingsId(5L);
        showModel.setStockId(12826L);
        showModel.setOperateBusiness(OperateSeatBussinessType.REFUND_TICKET_RELEASE_SEAT.key);
        try {
            showService.releaseSeat(showModel, null);
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                logger.error("异常：" + e.getMessage());
            } else {
                logger.error("未处理异常：", e);
            }
        }
    }

    //    @Test
    public void randomSeats() {
        ShowModel showModel = new ShowModel();
        showModel.setRandomNum(10);
        showModel.setAreaScreeingsId(1L);
        showModel.setStockId(1393L);
        Result<ShowResponse> result = showService.randomAssignSeat(showModel, null);
        logger.info("randomSeats result =====" + JSONConverter.toJson(result));
    }
}
