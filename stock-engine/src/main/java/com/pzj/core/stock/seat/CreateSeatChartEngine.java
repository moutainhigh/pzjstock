/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.seat;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.stock.entity.SeatChart;
import com.pzj.core.stock.model.SeatChartModel;
import com.pzj.core.stock.write.SeatChartWriteMapper;

/**
 * 
 * @author Administrator
 * @version $Id: CreateSeatChartEngine.java, v 0.1 2016年8月11日 上午10:55:53 Administrator Exp $
 */
@Component("createSeatChartEngine")
public class CreateSeatChartEngine {
    @Resource(name = "seatChartWriteMapper")
    private SeatChartWriteMapper seatChartWriteMapper;

    @Transactional(value = "stock.transactionManager", timeout = 1)
    public void createSeatChart(SeatChartModel seatChartModel) {
        //初始化座位图信息
        SeatChart seatChart = initSeatChart(seatChartModel);

        //座位图入库
        seatChartWriteMapper.insert(seatChart);
    }

    /**
     * 初始化座位图信息
     * @param seatChartModel
     * @return
     */
    private SeatChart initSeatChart(SeatChartModel seatChartModel) {
        SeatChart seatChart = new SeatChart();
        seatChart.setScenicId(seatChartModel.getScenicId());
        seatChart.setAreaId(seatChartModel.getAreaId());
        seatChart.setSeatMaps(seatChartModel.getSeatMaps());
        seatChart.setTotalSeats(countSeatNum(seatChartModel.getSeatMaps()));
        seatChart.setSort(seatChartModel.getSort());
        return seatChart;
    }

    /**
     * 统计座位图中座位数量
     * @param seatMap
     * @return
     */
    private int countSeatNum(String seatMap) {
        int totalCount = 0;
        String[] rowArr = seatMap.split(";");
        for (String row : rowArr) {
            String[] lineArr = row.split(",");
            for (int i = 0; i < lineArr.length; i++) {
                if (i != 0) {
                    if (lineArr[i].matches("^[0-9]+$")) {
                        totalCount++;
                    }
                }
            }
        }
        return totalCount;
    }
}
