package com.pzj.core.product.common.exception;

/**
 * Created by Administrator on 2017-3-20.
 */
public enum TheaterExceptionCode {
    ERROR(15999, "失败"), //
    PARAMETER_EMPTY(15998, "参数为空"), //
    DATASOURCE_EMPTY(15997, "数据源dataSource为空"), //
    MODIFY_DATA_EMPTY(15996, "没有可修改的数据"), //
    OPERATING_E_CREATE_C_MOIDIFY(15995, "创建操作错误，应执行修改操作"), //
    OPERATING_E_MOIDIFY_C_CREATE(15994, "修改操作错误，应执行创建操作"), //

    // 场次
    SCREENINGS_NOT_EXIST(15260, "场次不存在"), //
    SCREENINGS_DELETED(15261, "场次已经被删除"), //
    SCREENINGS_DISABLE(15262, "场次不可用"), //
    SCREENINGS_NULL(15263, "场次为空"), //
    SCREENINGS_NULL_ID(15264, "场次的id为空"), //
    SCREENINGS_NULL_CODE(15265, "场次的标识为空"), //
    SCREENINGS_NULL_NAME(15266, "场次的名称为空"), //
    SCREENINGS_NULL_NAMEABBR(15267, "场次的简称为空"), //
    SCREENINGS_NULL_STATE(15268, "场次的状态为空"), //
    SCREENINGS_NULL_THEATERID(15269, "场次所属剧场Id为空"), //
    SCREENINGS_NULL_SUPPLIERID(15270, "场次所属供应商id为空"), //
    SCREENINGS_NULL_STARTTIME(15271, "场次的开始时间为空"), //
    SCREENINGS_NULL_ENDTIME(15272, "场次的结束时间为空"), //

    // 区域
    AREA_NOT_EXIST(15290, "区域不存在", "区域不存在，区域id为 %1$s。"), //
    AREA_DELETED(15291, "区域已经被删除"), //
    AREA_DISABLE(15292, "区域不可用"), //
    AREA_NULL(15293, "区域为空"), //
    AREA_NULL_ID(15294, "区域的id为空"), //
    AREA_NULL_NAME(15295, "区域的名称为空"), //
    AREA_NULL_NAMEABBR(15296, "区域的简称为空"), //
    AREA_NULL_STATE(15297, "区域的状态为空"), //
    AREA_NULL_SEATMODE(15298, "区域的状态为空"), //
    AREA_NULL_SEATTYPE(15299, "区域的状态为空"), //
    AREA_NULL_THEATERID(15300, "区域所属剧场Id为空"), //
    AREA_NULL_SUPPLIERID(15301, "区域所属供应商id为空"), //
    AREA_ILLEGAL_ID(15302, "区域的ID无效", "区域的ID：%1$s无效"), //
    AREA_ILLEGAL_SEATMODE(15303, "区域的座位模式无效", "区域的座位模式 %1$s 无效，请使用正确的值： %2$s"), //
    AREA_ILLEGAL_SEATTYPE(15304, "区域的座位类型无效", "区域的座位类型 %1$s 无效，请使用正确的值： %2$s"), //
    AREA_RULE_CREATE_MAX(15305, "请求创建的区域总数量超过了最大限制", "请求创建的区域总数量 %1$s 超过了最大限制 %2$s"), //
    AREA_RULE_CREATE_LEAST_ONE(15306, "创建区域时至少要有一个有效的区域详情"), //

    // 座位记录
    SEAT_RECORD_NOT_EXIST(15320, "座位记录不存在"), //
    SEAT_RECORD_DELETED(15321, "座位记录已经被删除"), //
    SEAT_RECORD_DISABLE(15322, "座位记录不可用"), //
    SEAT_RECORD_NULL(15323, "座位记录为空"), //
    SEAT_RECORD_NULL_ID(15324, "座位记录的ID为空"), //
    SEAT_RECORD_NULL_TRANSACTION(15325, "座位记录的交易ID为空"), //
    SEAT_RECORD_NULL_SCREENING(15326, "座位记录的剧场ID为空"), //
    SEAT_RECORD_NULL_AREA(15327, "座位记录的区域ID为空"), //
    SEAT_RECORD_NULL_SEAT(15328, "座位记录的座位ID为空"), //
    SEAT_RECORD_NULL_OPERATOR(15329, "座位记录的操作人ID为空"), //
    SEAT_RECORD_NULL_CATEGORY(15330, "座位记录的类型为空"), //
    SEAT_RECORD_NULL_STATE(15331, "座位记录的状态为空"), //
    SEAT_RECORD_NULL_TRAVEL_DATE(15332, "座位记录的游玩时间为空"), //
    SEAT_RECORD_ILLEGAL_ID(15332, "座位记录的ID无效", "座位记录的ID：%1$s无效"), //
    SEAT_RECORD_ILLEGAL_CATEGORY(15333, "座位记录的类型无效", "座位记录的座位模式 %1$s 无效，请使用正确的值 %2$s"), //
    SEAT_RECORD_ILLEGAL_STATE(15334, "座位记录的状态无效", "座位记录的座位类型 %1$s 无效，请使用正确的值 %2$s"), //
    SEAT_RECORD_RULE_HAS_BEEN_OCCUPIED(15335, "座位已经被占", "座位已经被占，记录ID为 %1$s。"), //
    SEAT_RECORD_RULE_NOT_THE_OWNER(15336, "座位属于其他用户", "座位属于其他用户，记录ID为 %1$s ，拥有都ID为 %2$s，试图操作记录的用户ID为 %3$s。"), //
    SEAT_RECORD_RULE_NOT_THE_TRANSACTION(15337, "座位属于其他交易", "座位属于其他交易，记录ID为 %1$s ，所属交易ID为 %2$s，试图操作记录的交易ID为 %3$s。"), //
    SEAT_RECORD_RULE_SEATS_GREATER_STOCK(15338, "分配的座位数量超过了库存", "分配的座位数量超过了库存，记录ID为 %1$s ，剩余可分配座位数量为 %2$s，尝试新分配的座位数量为 %3$s。"), //
    SEAT_RECORD_MODIFY_ERROR(15339, "修改座位记录异常"), //
    SEAT_RECORD_OCCUPYING_FILURE(15340, "占座时失败，因为有其他人已经抢先占了座位。"), //
    SEAT_RECORD_ILLEGAL_SEATMODE(15341, "申请座位记录类型无效", "申请座位记录类型 %1$s 无效，请使用正确的值： %2$s"), //
    SEAT_RECORD_RULE_TRAVEL_DATE_EXPIRED(15343, "游玩时间不能晚于今天", "游玩时间不能晚于今天，尝试的游玩时间为 %3$s。"), //

    // 待分配列表
    ASSIGNED_NOT_EXIST(15350, "待分配记录不存在"), //
    ASSIGNED_DELETED(15351, "待分配记录已经被删除"), //
    ASSIGNED_DISABLE(15352, "待分配记录不可用"), //
    ASSIGNED_NULL(15353, "待分配记录为空"), //
    ASSIGNED_NULL_ID(15354, "待分配记录的ID为空"), //
    ASSIGNED_NULL_TRANSACTION(15355, "待分配记录的交易ID为空"), //
    ASSIGNED_NULL_SCREENING(15356, "待分配记录的剧场ID为空"), //
    ASSIGNED_NULL_AREA(15357, "待分配记录的区域ID为空"), //
    ASSIGNED_NULL_OPERATOR(15358, "待分配记录的操作人ID为空"), //
    ASSIGNED_NULL_STATE(15359, "待分配记录的状态为空"), //
    ASSIGNED_NULL_UN_OCCUPIED_NUM(15360, "待分配记录的待占数量为空"), //
    ASSIGNED_NULL_OCCUPIED_NUM(15361, "待分配记录的状态为空"), //
    ASSIGNED_NULL_TRAVEL_DATE(15362, "待分配记录的游玩时间为空"), //
    ASSIGNED_ILLEGAL_ID(15363, "待分配记录的ID无效", "待分配记录的ID：%1$s无效"), //
    ASSIGNED_ILLEGAL_CATEGORY(15364, "待分配记录的类型无效", "待分配记录的待分配模式 %1$s 无效，请使用正确的值 %2$s"), //
    ASSIGNED_ILLEGAL_STATE(15365, "待分配记录的状态无效", "待分配记录的待分配类型 %1$s 无效，请使用正确的值 %2$s"), //
    ASSIGNED_RULE_NO_REDISTRIBUTION(15366, "待分配记录不可再分配座位", "待分配记录不可再分配座位，待分配ID为 %1$s ，目前状态为 %2$s。"), //

    // 座位
    SEAT_NOT_EXIST(15350, "座位不存在"), //
    SEAT_DELETED(15351, "座位已经被删除"), //
    SEAT_DISABLE(15352, "座位不可用"), //
    SEAT_NULL(15353, "座位为空"), //
    SEAT_NULL_ID(15354, "座位的ID为空"), //
    SEAT_NULL_TRANSACTION(15355, "座位的交易ID为空"), //
    SEAT_NULL_SCREENING(15356, "座位的剧场ID为空"), //
    SEAT_NULL_AREA(15357, "座位的区域ID为空"), //
    SEAT_NULL_OPERATOR(15358, "座位的操作人ID为空"), //
    SEAT_NULL_STATE(15359, "座位的状态为空"), //
    SEAT_NULL_UN_OCCUPIED_NUM(15360, "座位的待占数量为空"), //
    SEAT_NULL_OCCUPIED_NUM(15361, "座位的状态为空"), //
    SEAT_NULL_TRAVEL_DATE(15362, "座位的游玩时间为空"), //
    SEAT_ILLEGAL_ID(15363, "座位的ID无效", "座位的ID：%1$s无效"), //
    SEAT_ILLEGAL_CATEGORY(15364, "座位的类型无效", "座位的待分配模式 %1$s 无效，请使用正确的值 %2$s"), //
    SEAT_ILLEGAL_STATE(15365, "座位的状态无效", "座位的待分配类型 %1$s 无效，请使用正确的值 %2$s"), //
    SEAT_RULE_NO_REDISTRIBUTION(15366, "座位不可再分配座位", "座位不可再分配座位，待分配ID为 %1$s ，目前状态为 %2$s。"), //
    SEAT_RULE_REPEAT(15367, "座位不能重复", "座位不能重复，重复的座位唯一值为 %1$s。"), //

    OCCUPYED_STOCK_NULL_OUT_QUANTITY(15367, "占库存数量不能为空"),
    OCCUPYED_STOCK_NULL_RULE_ID(15367, "库存规则ID不能为空"),
    OCCUPYED_STOCK_NULL_SCREENING(15367, "场次ID不能为空"),
    OCCUPYED_STOCK_NULL_AREA(15367, "区域ID不能为空"),
    OCCUPYED_STOCK_NOT_EXIST(15367, "无法找到库存", "无法找到库存，规则ID为 %1$s，游玩时间为 %2$s。"),

    // 库存
    STOCK_NULL_PRODUCT(15380, "产品id为空"), //
    /*
    STOCK_DELETED(15381, "库存已经被删除"), //
    STOCK_DISABLE(15382, "库存不可用"), //
    STOCK_NULL(15383, "库存为空"), //
    STOCK_NULL_ID(15384, "库存的ID为空"), //
    STOCK_NULL_TRANSACTION(15385, "库存的交易ID为空"), //
    STOCK_NULL_SCREENING(15386, "库存的剧场ID为空"), //
    STOCK_NULL_AREA(15387, "库存的区域ID为空"), //
    STOCK_NULL_OPERATOR(15388, "库存的操作人ID为空"), //
    STOCK_NULL_STATE(15359, "库存的状态为空"), //
    STOCK_NULL_UN_OCCUPIED_NUM(15360, "库存的待占数量为空"), //
    STOCK_NULL_OCCUPIED_NUM(15361, "库存的状态为空"), //
    STOCK_NULL_TRAVEL_DATE(15362, "库存的游玩时间为空"), //
    STOCK_ILLEGAL_ID(15363, "库存的ID无效", "库存的ID：%1$s无效"), //
    STOCK_ILLEGAL_CATEGORY(15364, "库存的类型无效", "库存的待分配模式 %1$s 无效，请使用正确的值 %2$s"), //
    STOCK_ILLEGAL_STATE(15365, "库存的状态无效", "库存的待分配类型 %1$s 无效，请使用正确的值 %2$s"), //
    STOCK_RULE_NO_REDISTRIBUTION(15366, "库存不可再分配库存", "库存不可再分配库存，待分配ID为 %1$s ，目前状态为 %2$s。"), //*/


    ;

    private int code;
    private String msg;
    private String template;

    TheaterExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    TheaterExceptionCode(int code, String msg, String template) {
        this(code, msg);
        this.template = template;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getTemplate() {
        return template;
    }

    public String getTemplateMessage(Object... args) {
        if (args == null || args.length == 0)
            return getMsg();

        return String.format(getTemplate(), args);
    }
}
