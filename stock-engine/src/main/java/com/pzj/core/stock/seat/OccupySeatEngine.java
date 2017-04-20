/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.seat;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.entity.StockSeatRel;
import com.pzj.core.stock.enums.StockSeatRelStateEnum;
import com.pzj.core.stock.enums.StockStateEnum;
import com.pzj.core.stock.exception.errcode.SeatExceptionCode;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.exception.seat.CannotOccupySeatException;
import com.pzj.core.stock.exception.seat.RepeatOccupySeatException;
import com.pzj.core.stock.exception.stock.NotFoundStockException;
import com.pzj.core.stock.exception.stock.StockExpiredException;
import com.pzj.core.stock.exception.stock.StockStateException;
import com.pzj.core.stock.model.ShowModel;
import com.pzj.core.stock.read.StockReadMapper;
import com.pzj.core.stock.read.StockSeatRelReadMapper;
import com.pzj.core.stock.write.StockSeatRelWriteMapper;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author Administrator
 * @version $Id: OccupySeatEngine.java, v 0.1 2016年8月6日 上午11:10:49 Administrator Exp $
 */
@Component("occupySeatEngine")
public class OccupySeatEngine {

	@Resource(name = "stockSeatRelReadMapper")
	private StockSeatRelReadMapper stockSeatRelReadMapper;

	@Resource(name = "stockSeatRelWriteMapper")
	private StockSeatRelWriteMapper stockSeatRelWriteMapper;

	@Resource(name = "stockReadMapper")
	private StockReadMapper stockReadMapper;

	@Transactional(value = "stock.transactionManager", timeout = 2)
	public void occupySeat(ShowModel showModel) {
		List<StockSeatRel> stockSeatRelList = checkSeatIsAvai(showModel);

		//初始化库存和座位关系集合
		stockSeatRelList = initStockSeatRelInfo(showModel);

		stockSeatRelWriteMapper.addBatch(stockSeatRelList);
	}

	public List<StockSeatRel> checkSeatIsAvai(ShowModel showModel) {
		//检查该业务唯一id是否已经占座
		int occupySeatNum = stockSeatRelReadMapper.countSeatNumByServiceId(showModel.getTransactionId());
		if (occupySeatNum > 0) {
			throw new RepeatOccupySeatException(SeatExceptionCode.REPEAT_OCCUPY_SEAT_ERR_MSG);
		}

		//检查库存可以占座
		Stock stock = stockReadMapper.selectStockById(showModel.getStockId());
		canUseStock(stock);

		//检查库存对应的座位是否已经被占
		List<StockSeatRel> stockSeatRelList = stockSeatRelReadMapper.queryByStockAndSeats(showModel.getStockId(),
				showModel.getSeats());

		if (!Check.NuNCollections(stockSeatRelList)) {
			StringBuffer errorTip = new StringBuffer("无法占座的座位号：");
			for (StockSeatRel stockSeatRel : stockSeatRelList) {
				errorTip.append(stockSeatRel.getSeatNum()).append(" ");
			}
			throw new CannotOccupySeatException(errorTip.toString());

		}
		return stockSeatRelList;
	}

	/**
	 * 检查是否可用库存
	 * @param stock
	 */
	private void canUseStock(Stock stock) {
		if (stock == null) {
			throw new NotFoundStockException(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG);
		}

		int state = stock.getState();
		if (state != StockStateEnum.AVAILABLE.getState()) {
			throw new StockStateException(StockExceptionCode.STOCK_STATE_ERR_MSG);
		}

		int stockTime = stock.getStockTime();
		if (!CommonUtils.checkStockTimeIsAvai(stockTime)) {
			throw new StockExpiredException(StockExceptionCode.STOCK_EXPIRE_ERR_MSG);
		}
	}

	/**
	 * 初始化库存座位图关联集合
	 * @param showModel
	 * @return List<StockSeatRel>
	 */
	private List<StockSeatRel> initStockSeatRelInfo(ShowModel showModel) {
		List<StockSeatRel> stockSeatRelList = new ArrayList<StockSeatRel>();
		StockSeatRel stockSeatRel = null;
		for (String seatNum : showModel.getSeats()) {
			stockSeatRel = new StockSeatRel();
			stockSeatRel.setSeatNum(seatNum);
			stockSeatRel.setStockId(showModel.getStockId());
			stockSeatRel.setTransactionId(showModel.getTransactionId());
			stockSeatRel.setProductId(showModel.getProductId());
			stockSeatRel.setOperateUserId(showModel.getUserId());
			stockSeatRel.setState(StockSeatRelStateEnum.AVAILABLE.getState());
			stockSeatRelList.add(stockSeatRel);
		}
		return stockSeatRelList;
	}
}
