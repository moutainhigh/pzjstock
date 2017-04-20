/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.service;

import com.pzj.core.product.model.theater.TheaterCreateReqModel;
import com.pzj.core.product.model.theater.TheaterModifyReqModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 剧场基础信息
 * @author Administrator
 * @version $Id: TheaterService.java, v 0.1 2017年3月29日 上午10:30:16 Administrator Exp $
 */
public interface TheaterService {
	/**
	* @api {dubbo} com.pzj.core.product.service.TheaterService#addTheaterInfo 新增剧场基础信息
	* @apiName 新增剧场基础信息
	* @apiGroup SAAS&ERP 区域
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 剧场首次添加座位新增基础信息
	*
	* @apiParam (请求参数) {TheaterCreateReqModel} model 剧场基础信息实体
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (TheaterCreateReqModel) {Long} theaterId 剧场id
	* @apiParam (TheaterCreateReqModel) {Integer} lineNumber 行数量
	* @apiParam (TheaterCreateReqModel) {Integer} columnNumber 列数量
	* @apiParam (TheaterCreateReqModel) {Integer} sortType  座位排序规则  StockGlobalDict.theaterSort
	* @apiParam (TheaterCreateReqModel) {Integer} seatInfos 座位状态  StockGlobalDict.seatType
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"theaterId":12345678,
	*			"lineNumber":1,
	*			"columnNumber":3,
	*			"sortType":1,
	*			"seatInfos":1
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Boolean} data 成功或失败，主键id为新增的剧场id;
	*
	*
	* @apiSuccessExample {json} 成功响应数据
	* {
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":true
	* }
	*
	*
	* @apiParam (错误码) {int} 15001 参数错误
	* @apiParam (错误码) {int} 15002 库存服务异常
	*
	* @apiErrorExample {json} 异常响应数据
	* {
	*    "errorCode" : 15001,
	*    "errorMsg":"参数错误"
	* }
	*
	*/
	Result<Boolean> addTheaterInfo(TheaterCreateReqModel model, ServiceContext context);

	/**
	* @api {dubbo} com.pzj.core.product.service.TheaterService#modifyTheaterInfo 修改剧场基础信息
	* @apiName 修改剧场基础信息
	* @apiGroup SAAS&ERP 区域
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 修改剧场基础信息
	*
	* @apiParam (请求参数) {TheaterModifyReqModel} model 剧场基础信息实体
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (TheaterModifyReqModel) {Long} theaterId 剧场id
	* @apiParam (TheaterModifyReqModel) {Integer} lineNumber 行数量
	* @apiParam (TheaterModifyReqModel) {Integer} columnNumber 列数量
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"theaterId":12345678,
	*			"lineNumber":1,
	*			"columnNumber":3
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Boolean} data 成功或失败，主键id为新增的剧场id;
	*
	*
	* @apiSuccessExample {json} 成功响应数据
	* {
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":true
	* }
	*
	*
	* @apiParam (错误码) {int} 15001 参数错误
	* @apiParam (错误码) {int} 15002 库存服务异常
	*
	* @apiErrorExample {json} 异常响应数据
	* {
	*    "errorCode" : 15001,
	*    "errorMsg":"参数错误"
	* }
	*
	*/
	Result<Boolean> modifyTheaterInfo(TheaterModifyReqModel model, ServiceContext context);
}
