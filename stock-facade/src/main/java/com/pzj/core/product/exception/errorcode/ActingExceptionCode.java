/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.exception.errorcode;

/**
 * 演艺库存异常code定义
 * 15开头
 * 200~249 库存演艺异常码
 * 250-259 占座记录异常
 */
public class ActingExceptionCode {

	public static final int ACTING_STATE_ERR_CODE = 15200;
	public static final String ACTING_STATE_ERR_MSG = "演艺数据状态不可用";

	public static final int NOT_FOUND_ACTING_ERR_CODE = 15201;
	public static final String NOT_FOUND_ACTING_ERR_MSG = "未找到演艺区域场次，请前往演艺管理进行相关设置";

	public static final int NOT_FOUND_AREA_SCREEINGS_REL_ERR_CODE = 15202;
	public static final String NOT_FOUND_AREA_SCREEINGS_REL_ERR_MSG = "找不到对应的区域及场次信息";

	public static final int AREA_SCREEINGS_STATE_ERR_CODE = 15203;
	public static final String AREA_SCREEINGS_STATE_ERR_MSG = "演艺区域场次对应数据状态不可用";

	public static final int NOT_FOUND_SCREENINGS_ERR_CODE = 15204;
	public static final String NOT_FOUND_SCREENINGS_ERR_MSG = "找不到场次信息";

	public static final int NOT_FOUND_STOCK_TYPE_ERR_CODE = 15250;
	public static final String NOT_FOUND_STOCK_TYPE_ERR_MSG = "不是有效的锁座操作类型";
}
