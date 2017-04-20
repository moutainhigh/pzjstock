/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.seat;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.entity.AreaScreeingsRel;
import com.pzj.core.product.enums.ActingStateEnum;
import com.pzj.core.product.exception.AreaScreeningsRelStateException;
import com.pzj.core.product.exception.NotFoundAreaScreeingsRelException;
import com.pzj.core.product.exception.errorcode.ActingExceptionCode;
import com.pzj.core.product.read.AreaScreeingsRelReadMapper;
import com.pzj.core.stock.entity.SeatChart;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.model.SeatChartModel;
import com.pzj.core.stock.read.SeatChartReadMapper;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author Administrator
 * @version $Id: SeatChartQueryEngine.java, v 0.1 2016年8月22日 下午2:19:00 Administrator Exp $
 */
@Component("seatChartQueryEngine")
public class SeatChartQueryEngine {
    Logger                             logger = LoggerFactory.getLogger(SeatChartQueryEngine.class);

    @Resource(name = "areaScreeingsRelReadMapper")
    private AreaScreeingsRelReadMapper areaScreeingsRelReadMapper;
    @Resource(name = "seatChartReadMapper")
    private SeatChartReadMapper        seatChartReadMapper;

    /** 根据景区和区域查询座位图列表*/
    public ArrayList<SeatChartModel> querySeatChartByScenicAndArea(SeatChartModel seatChartModel) {
        //获取演艺区域场次信息
        AreaScreeingsRel areaScreeingsRel = areaScreeingsRelReadMapper.selectAreaScreeingsRelById(seatChartModel.getAreaScreeningsId());
        checkAreaScreeingInfo(areaScreeingsRel);

        List<SeatChart> seatChartList = seatChartReadMapper.querySeatChartByArea(areaScreeingsRel.getAreaId());
        if (!Check.NuNCollections(seatChartList)) {
            ArrayList<SeatChartModel> list = new ArrayList<SeatChartModel>();
            for (SeatChart seatChart : seatChartList) {
                SeatChartModel returnSeatChartModel = new SeatChartModel();
                try {
                    PropertyUtils.copyProperties(returnSeatChartModel, seatChart);
                } catch (Throwable e) {
                    logger.error("查询座位图，参数转换错误！", e);
                    throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
                }
                list.add(returnSeatChartModel);
            }
            return list;
        }

        return null;
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
            throw new AreaScreeningsRelStateException(ActingExceptionCode.AREA_SCREEINGS_STATE_ERR_MSG);
        }
    }
}
