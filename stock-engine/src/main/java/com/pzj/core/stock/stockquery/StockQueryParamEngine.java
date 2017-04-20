/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.stockquery;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.stock.entity.LockRecord;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.enums.StockStateEnum;
import com.pzj.core.stock.model.StockQueryRequestModel;
import com.pzj.core.stock.read.StockReadMapper;
import com.pzj.core.stock.write.LockRecordWriteMapper;
import com.pzj.framework.context.ServiceContext;

/**
 * 根据ID查询库存引擎
 * 
 * @author dongchunfu
 * @version $Id: StockQueryByIdEngine.java, v 0.1 2016年8月10日 下午4:26:27 dongchunfu Exp $
 */
@Component(value = "stockQueryParamEngine")
public class StockQueryParamEngine {
	@Resource(name = "stockReadMapper")
	private StockReadMapper stockReadMapper;
	@Resource(name = "lockRecordWriteMapper")
	private LockRecordWriteMapper lockRecordWriteMapper;

	public ArrayList<Stock> queryStockByParam(StockQueryRequestModel model, ServiceContext context) {
		Stock stock = produceStock(model);
		return stockReadMapper.selectStockListByParam(stock);
	}

	private Stock produceStock(StockQueryRequestModel model) {
		Stock stock = new Stock();

		stock.setRuleId(model.getRuleId());
		Integer state = model.getState();
		if (null != state) {
			stock.setState(state);
		} else {
			stock.setState(StockStateEnum.AVAILABLE.getState());
		}
		Integer stockTime = model.getStockTimeInt();
		if (stockTime == 0) {
			stock.setStockTime(null);
		} else {
			stock.setStockTime(stockTime);
		}
		stock.setStockIds(model.getStockIds());

		return stock;
	}

	public Integer queryTranOccupyStockNum(String tranId) {
		Integer occupyNum = 0;
		List<LockRecord> lockRecords = lockRecordWriteMapper.queryStockRecordByTranId(tranId);
		if (lockRecords != null && !lockRecords.isEmpty()) {
			for (LockRecord lockRecord : lockRecords) {
				occupyNum += lockRecord.getLockNum();
			}
		}
		return occupyNum;
	}
}
