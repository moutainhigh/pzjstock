package com.pzj.core.stock.enums;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.SeatExceptionCode;
import com.pzj.core.stock.exception.seat.OccupySeatBussinessTypeException;

public enum OperateSeatBussinessType {
    /** 下单占座 */
    ORDER_OCCUPY_SEAT(1, "下单占座"),
    /** 预约占座 */
    APPOINMENT_OCCUPY_SEAT(2, "预约占座"),
    /** 取消下单释放座位 */
    CANCLE_ORDER_RELEASE_SEAT(3, "取消下单释放座位"),
    /** 退票释放座位 */
    REFUND_TICKET_RELEASE_SEAT(4, "退票释放座位"),
    /** 预约释放座位 */
    APPOINMENT_RELEASE_SEAT(5, "预约释放座位");

    public int    key;
    public String value;

    private OperateSeatBussinessType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(int key) {
        OperateSeatBussinessType sbt = getSeatBussinessType(key);
        return sbt.value;
    }

    public static OperateSeatBussinessType getSeatBussinessType(int key) throws StockException {
        OperateSeatBussinessType[] operateSeatBussType = OperateSeatBussinessType.values();
        for (OperateSeatBussinessType osbt : operateSeatBussType) {
            if (osbt.key == key) {
                return osbt;
            }
        }
        throw new OccupySeatBussinessTypeException(SeatExceptionCode.OCCUPY_SEAT_BUSSINESS_TYPE_ERR_MSG);
    }

}
