package com.pzj.core.product.area;

import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.common.exception.TheaterExceptionCode;

/**
 * Created by Administrator on 2017-3-21.
 */
public enum AreaSeatType {
    // 用户自选
    NoSeat(1, "用户自选"),
    // 后台配座
    RectangularSeat(2, "后台配座"),
    ;

    private int value;
    private String desc;

    AreaSeatType(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public static boolean containsValue(int value){
        switch (value){
            case 1:
            case 2:
                return true;
            default:
                return false;
        }
    }

    private static String AREA_SEAT_TYPE_VALUES_STRING = araTypeaValuesString();

    private static String araTypeaValuesString() {
        AreaSeatType[] categorys = values();
        if (categorys != null && categorys.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < categorys.length; i++) {
                append(sb, categorys[i]);
            }
            return sb.toString();
        }
        return null;
    }

    private static void append(StringBuilder sb, AreaSeatType areaSeatType){
        sb.append(areaSeatType.getValue());
        sb.append("：");
        sb.append(areaSeatType.getDesc());
        sb.append("；");
    }

    public static void checkAreaTypeValue(int value){
        boolean check = containsValue(value);
        if (!check){
            TheaterExceptionCode exceptionCode = TheaterExceptionCode.AREA_ILLEGAL_SEATTYPE;
            String templateMessage = exceptionCode.getTemplateMessage(value, AREA_SEAT_TYPE_VALUES_STRING);
            throw new TheaterException(exceptionCode.getCode(), templateMessage);
        }
    }

    public int getValue() {
        return value;
    }

    public String getDesc(){
        return desc;
    }

    public boolean equals(Integer value){
        return value != null && this.value == value;
    }

    public boolean equals(int value){
        return this.value == value;
    }
}
