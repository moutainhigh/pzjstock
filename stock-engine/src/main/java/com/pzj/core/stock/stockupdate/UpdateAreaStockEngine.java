package com.pzj.core.stock.stockupdate;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.read.SeatCharReadMapper;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.exception.stock.StockNumLessSeatException;
import com.pzj.core.stock.exception.stock.StockNumLessUsedException;
import com.pzj.core.stock.write.StockWriteMapper;

@Component("updateAreaStockEngine")
public class UpdateAreaStockEngine {
	@Resource(name = "stockWriteMapper")
	private StockWriteMapper stockWriteMapper;
	@Resource(name = "seatCharReadMapper")
	private SeatCharReadMapper seatCharReadMapper;

	@Transactional(value = "stock.transactionManager", timeout = 2)
	public int updateAreaStock(Long stockId, Integer newestStockNum, Long areaId) {
		int updnum = 0;
		if (CommonUtils.checkLongIsNull(stockId, true)) {
			return updnum;
		}

		Stock stock = stockWriteMapper.queryStockByIdForUpdate(stockId);
		if (!checkStock(stock, newestStockNum, areaId)) {
			return updnum;
		}

		updnum = stockWriteMapper.updateStockLimitById(stockId, newestStockNum);
		return updnum;
	}

	private Boolean checkStock(Stock stock, Integer newestStockNum, Long areaId) {
		if (stock == null) {
			return Boolean.FALSE;
		}
		Integer seatNum = seatCharReadMapper.countAreaSeat(areaId);
		seatNum = seatNum == null ? 0 : seatNum;
		int usedNum = stock.getUsedNum() == null ? 0 : stock.getUsedNum();
		if (usedNum < newestStockNum.intValue()) {
			throw new StockNumLessUsedException();
		}
		if (seatNum.intValue() > newestStockNum.intValue()) {
			throw new StockNumLessSeatException();
		}
		return Boolean.TRUE;
	}
}
