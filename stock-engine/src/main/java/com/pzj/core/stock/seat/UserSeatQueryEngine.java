/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.seat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.enums.RecordCategory;
import com.pzj.core.product.model.seat.SeatRespModel;
import com.pzj.core.product.model.seatRecord.SeatRecordReqModel;
import com.pzj.core.product.seatRecord.SeatRecordQueryEngine;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.enums.StockStateEnum;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.exception.stock.NotFoundStockException;
import com.pzj.core.stock.exception.stock.StockStateException;
import com.pzj.core.stock.entity.StockSeatRel;
import com.pzj.core.stock.model.UserSeatModel;
import com.pzj.core.stock.read.StockReadMapper;
import com.pzj.core.stock.read.StockSeatRelReadMapper;

/**
 * 
 * @author Administrator
 * @version $Id: UserSeatQueryEngine.java, v 0.1 2016年8月22日 下午5:24:35 Administrator Exp $
 */
@Component("userSeatQueryEngine")
public class UserSeatQueryEngine {

	@Resource(name = "seatRecordQueryEngine")
	private SeatRecordQueryEngine seatRecordQueryEngine;
	@Resource(name = "stockReadMapper")
	private StockReadMapper stockReadMapper;
	@Resource(name = "stockSeatRelReadMapper")
	private StockSeatRelReadMapper stockSeatRelReadMapper;

	public List<String> queryOccupySeats(UserSeatModel userSeatModel) {
		List<String> initOccupySeats = lockSeats(userSeatModel);
		List<String> occupySeats = stockSeatRelReadMapper.querySeatNumByStockId(userSeatModel.getStockId());
		if (occupySeats != null && !occupySeats.isEmpty()) {
			initOccupySeats.addAll(occupySeats);
		}
		return initOccupySeats;
	}

	private List<String> lockSeats(UserSeatModel userSeatModel) {
		Stock stock = stockReadMapper.selectStockById(userSeatModel.getStockId());
		if (stock == null) {
			throw new NotFoundStockException(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG);
		}

		int state = stock.getState();
		if (state != StockStateEnum.AVAILABLE.getState()) {
			throw new StockStateException(StockExceptionCode.STOCK_STATE_ERR_MSG);
		}
		List<String> stockSeatRelList = new ArrayList<String>();
		Integer stockTime = stock.getStockTime();
		Date showDate = CommonUtils.integerConvertDate(stockTime);
		SeatRecordReqModel seatRecordModel = new SeatRecordReqModel();
		seatRecordModel.setTheaterDate(showDate);
		seatRecordModel.setAreaId(userSeatModel.getAreaId());
		seatRecordModel.setScreeingId(userSeatModel.getScreeingId());
		seatRecordModel.setRecordCategory(RecordCategory.LOCKING);
		ArrayList<SeatRespModel> lockSeats = seatRecordQueryEngine.querySeatStateByRecord(seatRecordModel, null);
		if (lockSeats != null && !lockSeats.isEmpty()) {
			for (SeatRespModel lockSeat : lockSeats) {
				stockSeatRelList.add(lockSeat.getSeatName());
			}
		}
		return stockSeatRelList;
	}


	public List<StockSeatRel> queryStockSeatByTransaction(String transactionId) {
		List<StockSeatRel> stockSeatRels = stockSeatRelReadMapper.querySeatByBussId(transactionId);
		return stockSeatRels;
	}
}
