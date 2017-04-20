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
 * @version $Id: UserSeatSceneTypeEnum.java, v 0.1 2016年8月22日 下午5:08:11 Administrator Exp $
 */
public enum UserSeatSceneTypeEnum {
    /**
     * 下单占座.
     */
    ORDER(1),
    /**
     * 预约占座.
     */
    APPOINTMENT(2);

    /** 状态值. */
    private int state;

    public int getState() {
        return state;
    }

    private UserSeatSceneTypeEnum(int state) {
        this.state = state;
    }

    /**
    * 根据库存状态值获取对应的库存状态对象.
    * @param state
    * @return
    */
    public static UserSeatSceneTypeEnum getUserSeatSceneTypeEnum(int state) {
        for (UserSeatSceneTypeEnum userSeatSceneType : UserSeatSceneTypeEnum.values()) {
            if (userSeatSceneType.getState() == state) {
                return userSeatSceneType;
            }
        }
        throw new StockStateException(StockExceptionCode.STOCK_STATE_ERR_MSG);
    }
}
