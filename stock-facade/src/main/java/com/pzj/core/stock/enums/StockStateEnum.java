package com.pzj.core.stock.enums;

import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.exception.stock.StockStateException;

/**
 * 库存状态.
 * @author YRJ
 *
 */
public enum StockStateEnum {

                            /**
                             * 启用.
                             */
                            AVAILABLE(1),
                            /**
                             * 禁用.
                             */
                            DISABLED(2);

    /** 状态值. */
    private int state;

    public int getState() {
        return state;
    }

    private StockStateEnum(int state) {
        this.state = state;
    }

    /**
     * 根据库存状态值获取对应的库存状态对象.
     * @param state
     * @return
     */
    public static StockStateEnum getStockStateEnum(int state) {
        for (StockStateEnum stockState : StockStateEnum.values()) {
            if (stockState.getState() == state) {
                return stockState;
            }
        }
        throw new StockStateException(StockExceptionCode.STOCK_STATE_ERR_MSG);
    }
}
