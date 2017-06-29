/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.event;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.InvokeOtherServiceException;
import com.pzj.core.common.exception.StockException;
import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.common.model.response.SkuProductOutModel;
import com.pzj.core.product.common.service.ISpuProductService;
import com.pzj.core.product.model.ActingProductQueryRequstModel;
import com.pzj.core.product.model.area.AreaScreeingsRelModel;
import com.pzj.core.product.service.ActingProductService;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.entity.StockSeatRel;
import com.pzj.core.stock.enums.OperateSeatBussinessType;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.model.ShowModel;
import com.pzj.core.stock.model.StockModel;
import com.pzj.core.stock.model.StockQueryRequestModel;
import com.pzj.core.stock.seat.UserSeatQueryEngine;
import com.pzj.core.stock.service.StockQueryService;
import com.pzj.core.stock.stockquery.StockQueryByIdEngine;
import com.pzj.dock.supplier.vo.DockSeatVO;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author Administrator
 * @version $Id: InitDockSeatData.java, v 0.1 2016年9月13日 下午5:55:09 Administrator Exp $
 */
@Component("initDockSeatData")
public class InitDockSeatData {
    private static final Logger  logger = LoggerFactory.getLogger(InitDockSeatData.class);
    @Autowired
    private ActingProductService actingProductService;
    @Autowired
    private StockQueryService    stockQueryService;

    /**
     * 占座/释放 座位 初始化对接系统数据
     * @param showModel
     * @return
     */
    public DockSeatVO initDockSeat(ShowModel showModel) {
        ServiceContext context = new ServiceContext();
        ActingProductQueryRequstModel model = new ActingProductQueryRequstModel();
        model.setActProId(showModel.getAreaScreeingsId());
        Result<AreaScreeingsRelModel> actingResult = actingProductService.queryAreaScreeRelByProAct(model, context);
        if (!actingResult.isOk() || CommonUtils.checkObjectIsNull(actingResult.getData())) {
            logger.error("occupy/release seat invoke query acting all info fail. request: " + model + ", result: " + actingResult);
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG + ",查询演艺详细信息有误！");
        }

        StockQueryRequestModel stockQueryModel = new StockQueryRequestModel();
        stockQueryModel.setStockId(showModel.getStockId());
        Result<StockModel> stockModelResult = stockQueryService.queryStockById(stockQueryModel, context);
        if (!stockModelResult.isOk() || CommonUtils.checkObjectIsNull(stockModelResult.getData())) {
            logger.error("occupy/release seat invoke query stock by id fail. request: " + stockQueryModel + ", result: " + stockModelResult);
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG + ",查询库存信息有误！");
        }
        StockModel stockModel = stockModelResult.getData();

        StringBuffer seats = new StringBuffer("");
        for (String seat : showModel.getSeats()) {
            seats.append(seat).append(",");
        }
        AreaScreeingsRelModel areaScreeRel = actingResult.getData();
        DockSeatVO dockSeatVO = new DockSeatVO();
//        dockSeatVO.setSupplierId(areaScreeRel.getSupplierId());
//        dockSeatVO.setScenicId(areaScreeRel.getScenicId());
//        dockSeatVO.setTransactionId(showModel.getTransactionId());
//        dockSeatVO.setScreenings(areaScreeRel.getScreeingsId());
//        dockSeatVO.setArea(areaScreeRel.getAreaId().toString());
//        dockSeatVO.setSeats(seats.toString());
//        dockSeatVO.setShowTime(stockModel.getStockDate());
//        dockSeatVO.setOperateBusiness(showModel.getOperateBusiness());
        return dockSeatVO;
    }
}
