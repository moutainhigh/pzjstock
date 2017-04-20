package com.pzj.core.product.enums;

import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.exception.stock.StockStateException;

/**
 * 库存状态.
 * @author YRJ
 *
 */
public enum ActingStateEnum {

    /**
     * 可用的.
     */
    AVAILABLE(1),
    /**
     * 停用的.
     */
    DISABLED(2);

    /** 状态值. */
    private int state;

    public int getState() {
        return state;
    }

    private ActingStateEnum(int state) {
        this.state = state;
    }

    /**
     * 根据库存状态值获取对应的库存状态对象.
     * @param state
     * @return
     */
    public static ActingStateEnum getStockStateEnum(int state) {
        for (ActingStateEnum stockState : ActingStateEnum.values()) {
            if (stockState.getState() == state) {
                return stockState;
            }
        }
        throw new StockStateException(StockExceptionCode.STOCK_STATE_ERR_MSG);
    }
}
