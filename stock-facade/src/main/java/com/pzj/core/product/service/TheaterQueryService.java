/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.service;

import com.pzj.core.product.model.theater.TheaterQueryRespModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 剧场基础信息查询接口
 * @author Administrator
 * @version $Id: TheaterQueryService.java, v 0.1 2017年3月29日 上午11:02:14 Administrator Exp $
 */
public interface TheaterQueryService {
	/**
	* @api {dubbo} com.pzj.core.product.service.TheaterQueryService#queryTheaterByTheaterId 根据剧场id查询剧场基础信息
	* @apiName 根据剧场id查询剧场基础信息
	* @apiGroup SAAS&ERP 区域
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 根据剧场id查询剧场基础信息
	*
	* @apiParam (请求参数) {Long} theaterId 剧场id
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"theaterId":12345678
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {TheaterQueryRespModel} data 剧场基础信息;
	*
	*
	* @apiSuccessExample {json} 成功响应数据
	* {
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":{
	*			"theaterId":12345678,
	*			"lineNumber":1,
	*			"columnNumber":3,
	*			"sortType":1,
	*			"seatInfos":1
	*		}
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
	Result<TheaterQueryRespModel> queryTheaterByTheaterId(Long theaterId, ServiceContext context);
}
