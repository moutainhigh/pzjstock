/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.enums;

import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.exception.stock.StockStateException;

/**
 * 
 * @author Administrator
 * @version $Id: StockSeatRelStateEnum.java, v 0.1 2016年8月6日 下午2:37:42 Administrator Exp $
 */
public enum StockSeatRelStateEnum {
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

    private StockSeatRelStateEnum(int state) {
        this.state = state;
    }

    /**
     * 根据库存状态值获取对应的库存状态对象.
     * @param state
     * @return
     */
    public static StockSeatRelStateEnum getStockSeatRelStateEnum(int state) {
        for (StockSeatRelStateEnum stockSeatRelState : StockSeatRelStateEnum.values()) {
            if (stockSeatRelState.getState() == state) {
                return stockSeatRelState;
            }
        }
        throw new StockStateException(StockExceptionCode.STOCK_STATE_ERR_MSG);
    }
}
