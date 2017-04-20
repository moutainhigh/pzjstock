/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.seat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.stock.entity.StockSeatRel;
import com.pzj.core.stock.enums.OperateSeatBussinessType;
import com.pzj.core.stock.exception.errcode.SeatExceptionCode;
import com.pzj.core.stock.exception.seat.CannotReleaseSeatException;
import com.pzj.core.stock.exception.seat.ReleaseSeatNotFoundException;
import com.pzj.core.stock.exception.seat.UnableReleaseSeatException;
import com.pzj.core.stock.model.ShowModel;
import com.pzj.core.stock.read.StockSeatRelReadMapper;
import com.pzj.core.stock.write.StockSeatRelWriteMapper;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author Administrator
 * @version $Id: ReleaseSeatEngine.java, v 0.1 2016年8月6日 下午6:43:36 Administrator Exp $
 */
@Component("releaseSeatEngine")
public class ReleaseSeatEngine {
    @Resource(name = "stockSeatRelReadMapper")
    private StockSeatRelReadMapper  stockSeatRelReadMapper;
    @Resource(name = "stockSeatRelWriteMapper")
    private StockSeatRelWriteMapper stockSeatRelWriteMapper;

    @Transactional(value = "stock.transactionManager", timeout = 2)
    public void releaseSeat(ShowModel showModel) {
        //库存和座位集合获取可以释放的对象信息
        List<StockSeatRel> stockSeatList = stockSeatRelReadMapper.queryByStockAndSeats(showModel.getStockId(), showModel.getSeats());

        //检查是否可以释放座位
        checkCanReleaseSeat(stockSeatList, showModel);

        stockSeatRelWriteMapper.batchUpdateStockSeatState(stockSeatList);
    }

    /**
     * 检查是否可以释放座位
     * @param stockSeatList
     * @param showModel
     */
    private void checkCanReleaseSeat(List<StockSeatRel> stockSeatList, ShowModel showModel) {
        if (Check.NuNCollections(stockSeatList)) {
            throw new ReleaseSeatNotFoundException(SeatExceptionCode.NOT_FOUND_RELEASE_SEAT_ERR_MSG);
        }

        List<String> seatList = showModel.getSeats();

        if (stockSeatList.size() < seatList.size()) {
            StringBuffer occupySeatInfos = new StringBuffer("");
            for (StockSeatRel stockSeatRel : stockSeatList) {
                occupySeatInfos.append("," + stockSeatRel.getSeatNum());
            }
            boolean flag = false;
            StringBuffer errorTip = new StringBuffer("无法释放的座位号：");
            //部分座位无法释放
            for (String seat : seatList) {
                if (occupySeatInfos.indexOf(seat) < 0) {
                    flag = true;
                    errorTip.append(seat).append(" ");
                }
            }
            if (flag) {
                throw new CannotReleaseSeatException(errorTip.toString());
            }
        }

        Set<String> tranSet = new HashSet<String>();
        Set<Long> userSet = new HashSet<Long>();
        for (StockSeatRel stockSeatRel : stockSeatList) {
            tranSet.add(stockSeatRel.getTransactionId());
            userSet.add(stockSeatRel.getOperateUserId());
        }

        String alreadyTran = tranSet.iterator().next();
        if (tranSet.size() > 1 || !alreadyTran.equals(showModel.getTransactionId())) {
            throw new UnableReleaseSeatException(SeatExceptionCode.NOT_RELEASE_SEAT_ERR_MSG);
        }

        if (showModel.getOperateBusiness() == OperateSeatBussinessType.APPOINMENT_RELEASE_SEAT.key) {
            Long userId = userSet.iterator().next();
            if (null == userId || userSet.size() > 1 || showModel.getUserId().longValue() != userId.longValue()) {
                throw new UnableReleaseSeatException(SeatExceptionCode.NOT_RELEASE_SEAT_ERR_MSG);
            }
        }

    }
}
