package com.pzj.core.product.enums;

import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.exception.stock.StockStateException;

/**
 * 库存状态.
 * @author YRJ
 *
 */
public enum PramLengthEnum {
                            /**
                             * 可用的.
                             * @return 
                             */
                            NAME(20),
                            /**
                             * 停用的.
                             */
                            REMARKS(2);

    /** 状态值. */
    private int length;

    public int getLength() {
        return length;
    }

    private PramLengthEnum(int length) {
        this.length = length;
    }

    /**
    * 根据库存状态值获取对应的库存状态对象.
    * @param state
    * @return
    */
    public static PramLengthEnum getPramLengthEnum(int length) {
        for (PramLengthEnum param : PramLengthEnum.values()) {
            if (param.getLength() == length) {
                return param;
            }
        }
        throw new StockStateException(StockExceptionCode.STOCK_STATE_ERR_MSG);
    }
}
