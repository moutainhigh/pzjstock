/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.seat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.stock.entity.Seat;
import com.pzj.core.stock.entity.SeatChart;
import com.pzj.core.stock.exception.errcode.SeatExceptionCode;
import com.pzj.core.stock.exception.seat.NotFoundSeatChartException;
import com.pzj.core.stock.exception.seat.SeatChartDataErrorException;
import com.pzj.core.stock.model.SeatModel;
import com.pzj.core.stock.read.SeatChartReadMapper;
import com.pzj.core.stock.read.SeatReadMapper;
import com.pzj.core.stock.write.SeatWriteMapper;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author Administrator
 * @version $Id: CreateSeatEngine.java, v 0.1 2016年8月4日 下午5:27:48 Administrator Exp $
 */
@Component("createSeatEngine")
public class CreateSeatEngine {
    @Autowired
    private SeatReadMapper      seatReadMapper;
    @Autowired
    private SeatChartReadMapper seatChartReadMapper;
    @Autowired
    private SeatWriteMapper     seatWriteMapper;

    @Transactional(value = "stock.transactionManager", timeout = 2)
    public void createSeat(SeatModel seatModel) {
        //获取座位图集合
        Long seatChartId = seatModel.getSeatChartId();
        SeatChart seatChart = seatChartReadMapper.querySeatChartById(seatChartId);
        if (Check.NuNObject(seatChart)) {
            throw new NotFoundSeatChartException(SeatExceptionCode.NOT_FOUND_SEAT_CHART_ERR_MSG);
        }

        //验证座位图是否合规
        Set<String> seatSet = checkSeatChart(seatChart);

        //初始化座位图数据
        List<Seat> seatList = initSeatList(seatSet, seatChartId);

        //批量添加座位
        seatWriteMapper.addBatchSeat(seatList);
    }

    /**
     * 初始化座位图数据
     * @param seatSet
     * @param seatChartId
     * @return
     */
    private List<Seat> initSeatList(Set<String> seatSet, Long seatChartId) {
        List<Seat> seatList = new ArrayList<Seat>();
        Iterator<String> itera = seatSet.iterator();
        while (itera.hasNext()) {
            String seatNum = itera.next();
            Seat seat = new Seat();
            seat.setStockSeatChartId(seatChartId);
            seat.setSeatNum(seatNum);
            seatList.add(seat);
        }
        return seatList;
    }

    /**
     * 检查解析座位图
     * @param seatChart
     * @return
     */
    private Set<String> checkSeatChart(SeatChart seatChart) {
        Set<String> seatSet = new HashSet<String>();
        String seatMap = seatChart.getSeatMaps();
        if (seatMap.indexOf(";") < 0 || seatMap.indexOf(",") < 0) {
            throw new SeatChartDataErrorException(SeatExceptionCode.SEAT_CHART_DATA_ERR_MSG);
        }
        String[] rowData = seatMap.split(";");
        for (String row : rowData) {
            String[] seatInfo = row.split(",");
            String prefixSeat = seatInfo[0];
            for (String seat : seatInfo) {
                if (seat.matches("^[0-9]+$")) {
                    String seatNum = prefixSeat + "_" + seat;
                    seatSet.add(seatNum);
                }
            }
        }
        if (seatSet.size() == 0) {
            throw new SeatChartDataErrorException(SeatExceptionCode.SEAT_CHART_DATA_ERR_MSG);
        }
        return seatSet;
    }
}
