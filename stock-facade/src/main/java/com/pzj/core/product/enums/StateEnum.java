/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.enums;

import com.pzj.core.product.exception.StateException;
import com.pzj.core.product.exception.errorcode.ActingExceptionCode;

/**
 * 
 * @author Administrator
 * @version $Id: AreaScreeingsRelStateEnum.java, v 0.1 2016年8月9日 上午11:48:27 Administrator Exp $
 */
public enum StateEnum {
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

    private StateEnum(int state) {
        this.state = state;
    }

    /**
    * 根据库存状态值获取对应的库存状态对象.
    * @param state
    * @return
    */
    public static StateEnum getStateEnum(int state) {
        for (StateEnum areaScreeingsRelState : StateEnum.values()) {
            if (areaScreeingsRelState.getState() == state) {
                return areaScreeingsRelState;
            }
        }
        throw new StateException(ActingExceptionCode.ACTING_STATE_ERR_MSG);
    }
}
