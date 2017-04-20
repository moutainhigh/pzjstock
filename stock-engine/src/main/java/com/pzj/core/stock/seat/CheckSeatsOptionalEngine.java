/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.seat;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.entity.StockSeatRel;
import com.pzj.core.stock.model.CheckSeatsOptionalModel;
import com.pzj.core.stock.model.SeatsOptionalResponse;
import com.pzj.core.stock.read.StockSeatRelReadMapper;

/**
 * 
 * @author Administrator
 * @version $Id: CheckSeatsOptionalEngine.java, v 0.1 2016年10月26日 下午12:00:29 Administrator Exp $
 */
@Component("checkSeatsOptionalEngine")
public class CheckSeatsOptionalEngine {
    @Resource(name = "stockSeatRelReadMapper")
    private StockSeatRelReadMapper stockSeatRelReadMapper;

    public SeatsOptionalResponse checkSeatsOptional(CheckSeatsOptionalModel model) {
        SeatsOptionalResponse seatsOptionalResponse = new SeatsOptionalResponse();
        List<StockSeatRel> stockSeatList = stockSeatRelReadMapper.queryByStockAndSeats(model.getStockId(), model.getSeats());
        if (!CommonUtils.checkCollectionIsNull(stockSeatList)) {
            seatsOptionalResponse.setFlag(Boolean.FALSE);
            List<String> repeatSeats = new ArrayList<String>();
            for (StockSeatRel stockSeatRel : stockSeatList) {
                repeatSeats.add(stockSeatRel.getSeatNum());
            }
            seatsOptionalResponse.setNotOptionalSeats(repeatSeats);
        } else {
            seatsOptionalResponse.setFlag(Boolean.TRUE);
        }
        return seatsOptionalResponse;
    }
}
