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
public class StockRuleExceptionCode {
	public static final int TYPE_NOTEXIST_ERR_CODE = 15100;
	public static final String TYPE_NOTEXIST_ERR_MSG = "库存规则类型不存在";

	public static final int CATEGORY_NOTEXIST_ERR_CODE = 15101;
	public static final String CATEGORY_NOTEXIST_ERR_MSG = "库存规则类别不存在";

	public static final int RETURN_TYPE_ERR_CODE = 15102;
	public static final String RETURN_TYPE_ERR_MSG = "库存归还类型不匹配";

	public static final int NOT_FOUND_STOCK_RULE_ERR_CODE = 15103;
	public static final String NOT_FOUND_STOCK_RULE_ERR_MSG = "找不到相应库存规则数据";

	public static final int NOT_BIND_PRODUCT_ERR_CODE = 15104;
	public static final String NOT_BIND_PRODUCT_ERR_MSG = "库存规则未绑定产品";

	public static final int STOCK_RULE_ERR_CODE = 15105;
	public static final String STOCK_RULE_ERR_MSG = "库存规则异常";

	public static final int GET_STOCK_RULE_REL_PRODUCT_ERR_CODE = 15106;
	public static final String GET_STOCK_RULE_REL_PRODUCT_ERR_MSG = "调用产品服务获取库存规则和产品关系异常";

	public static final int GET_PRODUCT_DATE_ERR_CODE = 15107;
	public static final String GET_PRODUCT_DATE_ERR_MSG = "调用产品服务获取产品日期数据异常";

	public static final int STOCK_RULE_STATE_ERR_CODE = 15108;
	public static final String STOCK_RULE_STATE_ERR_MSG = "库存规则状态不可用";

	public static final int STOCK_RULE_STATE_INTO_ERR_CODE = 15109;
	public static final String STOCK_RULE_STATE_INTO_ERR_MSG = "库存规则状态值传入不合法";

	public static final int STOCK_RULE_REL_AVAI_PRODUCT_ERR_CODE = 15110;
	public static final String STOCK_RULE_REL_AVAI_PRODUCT_ERR_MSG = "库存规则绑定了上架的产品，不允许修改";
}
