package com.pzj.core.product.area;

import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.common.exception.TheaterExceptionCode;

/**
 * Created by Administrator on 2017-3-21.
 */
public enum  AreaSeatMode {
    // 用户自选
    CustomerSelected(1, "用户自选"),
    // 后台配座
    BackgroundAllocation(2, "后台配座"),
    // 系统分配
    SystemAllocation(3, "系统分配"),
    ;

    private int value;
    private String desc;

    AreaSeatMode(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public static boolean containsValue(int value){
        switch (value){
            case 1:
            case 2:
            case 3:
                return true;
            default:
                return false;
        }
    }

    private static String AREA_SEAT_MODE_VALUES_STRING = seatModeValuesString();

    private static String seatModeValuesString() {
        AreaSeatMode[] areaSeatModes = values();
        if (areaSeatModes != null && areaSeatModes.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < areaSeatModes.length; i++) {
                append(sb, areaSeatModes[i]);
            }
            return sb.toString();
        }
        return null;
    }

    private static void append(StringBuilder sb, AreaSeatMode areaSeatMode){
        sb.append(areaSeatMode.getValue());
        sb.append("：");
        sb.append(areaSeatMode.getDesc());
        sb.append("；");
    }

    public static void checkAreaTypeValue(int value){
        boolean check = containsValue(value);
        if (!check){
            TheaterExceptionCode exceptionCode = TheaterExceptionCode.AREA_ILLEGAL_SEATMODE;
            String templateMessage = exceptionCode.getTemplateMessage(value, AREA_SEAT_MODE_VALUES_STRING);
            throw new TheaterException(exceptionCode.getCode(), templateMessage);
        }
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
