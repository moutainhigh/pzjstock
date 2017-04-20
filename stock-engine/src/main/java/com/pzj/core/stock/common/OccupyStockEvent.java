package com.pzj.core.stock.common;

import org.springframework.stereotype.Component;

import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.enums.StockStateEnum;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.exception.stock.LimitedException;
import com.pzj.core.stock.exception.stock.NotFoundStockException;
import com.pzj.core.stock.exception.stock.ShortageStockException;
import com.pzj.core.stock.exception.stock.StockStateException;

/**
 * 判断库存是否可被占.
 * <p>也就是判断库存数量是否充足.</p>
 * @author YRJ
 *
 */
@Component(value = "occupyStockEvent")
public class OccupyStockEvent {

    public void occupyable(Stock stock, int occupyNum) {
        if (stock == null) {
            throw new NotFoundStockException(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG);
        }

        int state = stock.getState();
        if (state != StockStateEnum.AVAILABLE.getState()) {
            throw new StockStateException(StockExceptionCode.STOCK_STATE_ERR_MSG);
        }

        int remain = stock.getRemainNum();
        if (remain < occupyNum) {
            throw new ShortageStockException(StockExceptionCode.STOCK_SHORTAGE_ERR_MSG);
        }

        int total = stock.getTotalNum();
        if ((stock.getUsedNum() + occupyNum) > total) {
            throw new LimitedException(StockExceptionCode.MAX_STOCK_NUM_ERR_MSG);
        }
    }
}
