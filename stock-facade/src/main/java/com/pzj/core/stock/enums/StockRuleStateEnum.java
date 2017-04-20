/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.enums;

import com.pzj.core.stock.exception.errcode.StockRuleExceptionCode;
import com.pzj.core.stock.exception.rule.StockRuleStateException;

/**
 * 
 * @author Administrator
 * @version $Id: StockRuleStateEnum.java, v 0.1 2016年8月29日 下午3:46:53 Administrator Exp $
 */
public enum StockRuleStateEnum {
    /**
     * 启用.
     */
    AVAILABLE(1),
    /**
     * 禁用.
     */
    DISABLED(2);

    /** 状态值. */
    private final int state;

    public int getState() {
        return state;
    }

    private StockRuleStateEnum(int state) {
        this.state = state;
    }

    /**
    * 根据库存状态值获取对应的库存规则状态对象.
    * @param state
    * @return
    */
    public static StockRuleStateEnum getStockRuleStateEnum(int state) {
        for (StockRuleStateEnum stockRuleState : StockRuleStateEnum.values()) {
            if (stockRuleState.getState() == state) {
                return stockRuleState;
            }
        }
        throw new StockRuleStateException(StockRuleExceptionCode.STOCK_RULE_STATE_ERR_MSG);
    }
}
