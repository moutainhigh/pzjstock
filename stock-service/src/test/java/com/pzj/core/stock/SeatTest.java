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

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.context.UserSeatResponse;
import com.pzj.core.stock.model.CheckSeatsOptionalModel;
import com.pzj.core.stock.model.SeatModel;
import com.pzj.core.stock.model.SeatsOptionalResponse;
import com.pzj.core.stock.model.UserSeatModel;
import com.pzj.core.stock.service.SeatService;
import com.pzj.framework.context.Result;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.exception.ServiceException;

/**
 * 
 * @author Administrator
 * @version $Id: SeatTest.java, v 0.1 2016年8月11日 下午2:06:41 Administrator Exp $
 */
public class SeatTest {
    Logger                    logger  = LoggerFactory.getLogger(SeatTest.class);
    static ApplicationContext context = null;

    @BeforeClass
    public static void setUpClass() {
        context = new ClassPathXmlApplicationContext("applicationContext-test.xml");
        System.out.println(context);
    }

    private SeatService seatService;

    @Before
    public void setUp() {
        seatService = context.getBean(SeatService.class);
    }

    @Test
    public void testAddSeat() {
        SeatModel seatModel = new SeatModel();
        seatModel.setSeatChartId(4L);
        try {
            seatService.createSeat(seatModel, null);
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                logger.error("异常：" + e.getMessage());
            } else {
                logger.error("未处理异常：", e);
            }
        }
    }

    //    @Test
    public void judgeSeatsIsChoose() {
        CheckSeatsOptionalModel model = new CheckSeatsOptionalModel();
        model.setStockId(1686L);
        List<String> seats = new ArrayList<String>();
        seats.add("VIPA8_2");
        seats.add("VIPA9_3");
        model.setSeats(seats);
        model.setOperateUserId(2216619741563800L);
        logger.info("judgeSeatWheatherOptional request:{}", JSONConverter.toJson(model));
        Result<SeatsOptionalResponse> result = seatService.judgeSeatWheatherOptional(model, null);
        if (result.isOk()) {
            logger.info("judgeSeatWheatherOptional result:{}", JSONConverter.toJson(result));
        }
    }

    //    @Test
    public void queryAlreadyOccupySeat() {
        try {
            UserSeatModel userSeatModel = new UserSeatModel();
            //        userSeatModel.setStockId(1393L);
            Result<UserSeatResponse> result = seatService.queryAlreadyOccupySeat(userSeatModel, null);
            if (result.isOk()) {
                System.out.println("queryAlreadyOccupySeat result======" + JSONConverter.toJson(result));
            }
        } catch (StockException e) {
            //            logger.error("", e);
            System.out.println("exception-----" + JSONConverter.toJson(e));
        }
    }
}
