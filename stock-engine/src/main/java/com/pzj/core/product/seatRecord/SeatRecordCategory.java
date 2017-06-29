package com.pzj.core.product.seatRecord;

import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.common.exception.TheaterExceptionCode;

/**
 * Created by Administrator on 2017-3-27.
 */
public enum SeatRecordCategory {
    // 待选
    Candidate(10, "待选"),
    // 锁座
    LockSeat(30, "锁座"),
    // 预选
    PreemptionSeat(40,"预选"),
    // 占座
    OccupyingSeat(20, "占座");

    private int value;
    private String desc;
    
    SeatRecordCategory(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public static boolean containsValue(int value){
        switch (value){
            case 10:
            case 20:
            case 30:
            case 40:
                return true;
            default:
                return false;
        }
    }

    public boolean equals(Integer value){
        return this.getValue() == value;
    }

    private static String SEAT_RECORD_CATEGORY_STRING = seatRecordCategoryValuesString();

    private static String seatRecordCategoryValuesString() {
        SeatRecordCategory[] categorys = values();
        if (categorys != null && categorys.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < categorys.length; i++) {
                append(sb, categorys[i]);
            }
            return sb.toString();
        }
        return null;
    }

    private static void append(StringBuilder sb, SeatRecordCategory category){
        sb.append(category.getValue());
        sb.append("：");
        sb.append(category.getDesc());
        sb.append("；");
    }

    public static void checkSeatRecordCategoryValue(int value){
        boolean check = containsValue(value);
        if (!check){
            TheaterExceptionCode exceptionCode = TheaterExceptionCode.SEAT_RECORD_ILLEGAL_SEATMODE;
            String templateMessage = exceptionCode.getTemplateMessage(value, SEAT_RECORD_CATEGORY_STRING);
            throw new TheaterException(exceptionCode.getCode(), templateMessage);
        }
    }

    public int getValue() {
        return value;
    }

    public String getDesc(){
        return desc;
    }
}
