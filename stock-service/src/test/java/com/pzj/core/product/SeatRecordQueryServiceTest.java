package com.pzj.core.product;

import com.pzj.core.product.model.QueryValidSeatRecordResponse;
import com.pzj.core.product.service.SeatRecordQueryService;
import com.pzj.framework.armyant.junit.spring.ArmyantSpringRunner;
import com.pzj.framework.context.Result;
import com.pzj.framework.converter.JSONConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 2017-4-5.
 */
@RunWith(ArmyantSpringRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/applicationContext.xml" })
public class SeatRecordQueryServiceTest {
    @Resource
    SeatRecordQueryService seatRecordQueryService;

    @Test
    public void test(){
        String transactionId = "202002017040510002";
        Result<QueryValidSeatRecordResponse> result = seatRecordQueryService.queryValidSeatRecordByTransactionId(transactionId);

        assertNotNull(result);
        assertTrue(result.isOk());
        assertNotNull(result.getData());

        System.out.println(JSONConverter.toJson(result));
    }
}
