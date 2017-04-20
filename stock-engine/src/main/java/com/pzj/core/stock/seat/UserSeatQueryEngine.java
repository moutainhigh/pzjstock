/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.seat;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.stock.model.UserSeatModel;
import com.pzj.core.stock.read.StockSeatRelReadMapper;

/**
 * 
 * @author Administrator
 * @version $Id: UserSeatQueryEngine.java, v 0.1 2016年8月22日 下午5:24:35 Administrator Exp $
 */
@Component("userSeatQueryEngine")
public class UserSeatQueryEngine {

    @Resource(name = "stockSeatRelReadMapper")
    private StockSeatRelReadMapper stockSeatRelReadMapper;

    public List<String> queryOccupySeats(UserSeatModel userSeatModel) {

        List<String> stockSeatRelList = stockSeatRelReadMapper.querySeatNumByStockId(userSeatModel.getStockId());
        return stockSeatRelList;
    }
}
