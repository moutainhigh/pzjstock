/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.exception.errcode;

/**
 * 库存异常code定义
 * 15开头
 * 001~049 全局异常码
 * 050~099 库存异常码
 * 100~149 库存规则异常码
 * 150~199 演艺座位异常码
 */
public class SeatExceptionCode {

	public static final int UNABLE_RELEASE_SEAT_ERR_CODE = 15150;
	public static final String UNABLE_RELEASE_SEAT_ERR_MSG = "不能释放座位";

	public static final int SEAT_CHART_DATA_ERR_CODE = 15151;
	public static final String SEAT_CHART_DATA_ERR_MSG = "录入座位图数据有误";

	public static final int REPEAT_OCCUPY_SEAT_ERR_CODE = 15115;
	public static final String REPEAT_OCCUPY_SEAT_ERR_MSG = "业务重复占座";

	public static final int NOT_FOUND_RELEASE_SEAT_ERR_CODE = 15153;
	public static final String NOT_FOUND_RELEASE_SEAT_ERR_MSG = "没找到释放的座位";

	public static final int NOT_FOUND_SEAT_ERR_CODE = 15154;
	public static final String NOT_FOUND_SEAT_ERR_MSG = "找不到对应的座位";

	public static final int NOT_FOUND_SEAT_CHART_ERR_CODE = 15155;
	public static final String NOT_FOUND_SEAT_CHART_ERR_MSG = "找不到对应的座位图";

	public static final int MAX_SEAT_NUM_ERR_CODE = 15156;
	public static final String MAX_SEAT_NUM_ERR_MSG = "超过可选座位最大数量";

	public static final int NOT_RELEASE_SEAT_ERR_CODE = 15157;
	public static final String NOT_RELEASE_SEAT_ERR_MSG = "不能释放座位列表：";

	public static final int NOT_OCCUPY_SEAT_ERR_CODE = 15158;
	public static final String NOT_OCCUPY_SEAT_ERR_MSG = "不能占用座位列表：";

	public static final int NOT_OPERATE_RELEASE_SEAT_ERR_CODE = 15159;
	public static final String NOT_OPERATE_RELEASE_SEAT_ERR_MSG = "不能操作释放座位";

	public static final int OCCUPY_SEAT_BUSSINESS_TYPE_ERR_CODE = 15160;
	public static final String OCCUPY_SEAT_BUSSINESS_TYPE_ERR_MSG = "不能操作释放座位";

}
