/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.seat;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.product.entity.AreaScreeingsRel;
import com.pzj.core.product.enums.ActingStateEnum;
import com.pzj.core.product.exception.AreaScreeningsRelStateException;
import com.pzj.core.product.exception.NotFoundAreaScreeingsRelException;
import com.pzj.core.product.exception.errorcode.ActingExceptionCode;
import com.pzj.core.product.read.AreaScreeingsRelReadMapper;
import com.pzj.core.stock.exception.errcode.SeatExceptionCode;
import com.pzj.core.stock.exception.seat.LimitedSeatException;
import com.pzj.core.stock.exception.seat.NotFoundSeatChartException;
import com.pzj.core.stock.exception.seat.NotFoundSeatException;
import com.pzj.core.stock.model.ShowModel;
import com.pzj.core.stock.read.SeatChartReadMapper;
import com.pzj.core.stock.read.SeatReadMapper;
import com.pzj.core.stock.read.StockSeatRelReadMapper;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author Administrator
 * @version $Id: RandomAssignSeatEngine.java, v 0.1 2016年8月9日 上午11:27:44 Administrator Exp $
 */
@Component("randomAssignSeatEngine")
public class RandomAssignSeatEngine {
    private static Logger              logger = LoggerFactory.getLogger(RandomAssignSeatEngine.class);
    @Resource(name = "areaScreeingsRelReadMapper")
    private AreaScreeingsRelReadMapper areaScreeingsRelReadMapper;

    @Resource(name = "seatChartReadMapper")
    private SeatChartReadMapper        seatChartReadMapper;

    @Resource(name = "seatReadMapper")
    private SeatReadMapper             seatReadMapper;

    @Resource(name = "stockSeatRelReadMapper")
    private StockSeatRelReadMapper     stockSeatRelReadMapper;

    public String[] randomAssignSeat(ShowModel showModel) {

        AreaScreeingsRel areaScreeingsRel = areaScreeingsRelReadMapper.selectAreaScreeingsRelById(showModel.getAreaScreeingsId());
        checkAreaScreeingInfo(areaScreeingsRel);

        List<Long> seatChartIds = seatChartReadMapper.querySeatChartIdsByArea(areaScreeingsRel.getAreaId());

        return randomSeats(seatChartIds, showModel);
    }

    /**
     * 检查区域场次数据
     * @param areaScreeingsRel
     */
    private void checkAreaScreeingInfo(AreaScreeingsRel areaScreeingsRel) {
        if (Check.NuNObject(areaScreeingsRel)) {
            throw new NotFoundAreaScreeingsRelException(ActingExceptionCode.NOT_FOUND_AREA_SCREEINGS_REL_ERR_MSG);
        }

        if (areaScreeingsRel.getState() != ActingStateEnum.AVAILABLE.getState()) {
            throw new AreaScreeningsRelStateException(ActingExceptionCode.ACTING_STATE_ERR_MSG);
        }
    }

    /**
     * 获取动态分配的座位集合
     * @param seatChartIds
     * @param showModel
     * @return
     */
    private String[] randomSeats(List<Long> seatChartIds, ShowModel showModel) {

        if (Check.NuNCollections(seatChartIds)) {
            throw new NotFoundSeatChartException(SeatExceptionCode.NOT_FOUND_SEAT_CHART_ERR_MSG);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("-----------randomSeats--seatChartIds---" + seatChartIds);
        }

        //获取所有区域对应的座位集合
        List<String> seatList = seatReadMapper.querySeatBySeatChartIds(seatChartIds);
        if (Check.NuNCollections(seatList)) {
            throw new NotFoundSeatException(SeatExceptionCode.NOT_FOUND_SEAT_ERR_MSG);
        }

        //获取库存已经占用的座位集合
        List<String> usedSeatNumList = stockSeatRelReadMapper.querySeatNumByStockId(showModel.getStockId());
        if (logger.isDebugEnabled()) {
            logger.debug("-----randomSeats .usedSeatNumList:{}-----", usedSeatNumList);
        }

        //过滤已选座位列表
        if (!Check.NuNCollections(usedSeatNumList)) {
            Set<String> usedSet = new HashSet<String>();
            usedSet.addAll(usedSeatNumList);

            Iterator<String> itera = seatList.iterator();
            while (itera.hasNext()) {
                if (usedSet.contains(itera.next())) {
                    itera.remove();
                }
            }
        }

        int randomNum = showModel.getRandomNum();
        if (Check.NuNCollections(seatList) || randomNum > seatList.size()) {
            throw new LimitedSeatException(SeatExceptionCode.MAX_SEAT_NUM_ERR_MSG);
        }

        //随机生成座位号
        String[] randomSeatArr = new String[randomNum];
        Iterator<String> itera = seatList.iterator();
        while (itera.hasNext() && randomNum > 0) {
            randomSeatArr[--randomNum] = itera.next();
        }

        return randomSeatArr;
    }
}
