/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.enums;

import com.pzj.core.stock.exception.errcode.StockRuleExceptionCode;
import com.pzj.core.stock.exception.rule.StockReturnTypeException;

/**
 * 
 * @author Administrator
 * @version $Id: StockReturnTypeEnum.java, v 0.1 2016年8月3日 下午6:46:55 Administrator Exp $
 */
public enum StockReturnTypeEnum {
    /** 可以归还库存(只针对于单一库存)*/
    NEED_RETURN(1),
    /** 不可以归还库存(只针对于单一库存)*/
    NOT_NEED_RETURN(2);

    private int returnType;

    private StockReturnTypeEnum(int returnType) {
        this.returnType = returnType;
    }

    public int getReturnType() {
        return returnType;
    }

    public static StockReturnTypeEnum getStockReturnTypeEnum(int returnType) {
        for (StockReturnTypeEnum srte : StockReturnTypeEnum.values()) {
            if (returnType == srte.getReturnType()) {
                return srte;
            }
        }
        throw new StockReturnTypeException(StockRuleExceptionCode.RETURN_TYPE_ERR_MSG);
    }
}
