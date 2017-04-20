/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service;

import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 演艺场次写操作
 * @author dongchunfu
 * @version $Id: ActingAreaService.java, v 0.1 2016年8月1日 上午10:37:43 dongchunfu Exp $
 */
public interface ScreeingsService {
	/**
	 * 创建演艺场次
	 * @author dongchunfu
	 * @param screeings 场次实体
	 * @return Result<Long> 新增实体ID
	 */

	/**
	* @api {dubbo} com.pzj.core.product.service.ScreeingsService.createScreeings 创建（添加）演艺场次
	* @apiName  创建（添加）演艺场次
	* @apiGroup 演艺接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription  创建（添加）演艺场次
	*
	* @apiParam (请求参数) {ScreeingsModel} model 演艺场次VO
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (ScreeingsModel) {Long} scenicId 景区ID
	* @apiParam (ScreeingsModel) {String} code 场次标识
	* @apiParam (ScreeingsModel) {String} name 场次名称
	* @apiParam (ScreeingsModel) {Date} startTime 演出开始时间
	* @apiParam (ScreeingsModel) {Date} endTime 演出结束时间
	* @apiParam (ScreeingsModel) {Long} [id] 场次主键id
	* @apiParam (ScreeingsModel) {Date} [endSaleTime] 演出停售时间
	* @apiParam (ScreeingsModel) {Date} [createTime] 创建时间
	* @apiParam (ScreeingsModel) {Date} [updateTime] 修改时间
	*
	* 
	* @apiSuccess {object} object 影响数据库:stock 表:prod_screeings 字段:所有字段
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"scenicId":2216619741563801,
	*			"code":"1",
	*			"name":"早场",
	*			"startTime":"830",
	*			"endTime":"1200"
	*		}
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Long} data 创建场次数量
	* 
	* @apiSuccessExample {json} 成功响应数据
	* {
	*   "errorCode":10000,
	*   "errorMsg":"ok",
	*   "data":1
	* }
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
	public Result<Long> createScreeings(ScreeingsModel model, ServiceContext context);

	/**
	 * 更新演艺场次
	 * @author dongchunfu
	 * @param screeings 场次实体
	 * @return Result<Integer> 更新数量
	 */

	/**
	* @api {dubbo} com.pzj.core.product.service.ScreeingsService.updateScreeings 更新演艺场次
	* @apiName 更新演艺场次
	* @apiGroup 演艺接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 更新演艺场次
	*
	* @apiParam (请求参数) {ScreeingsModel} model 演艺场次VO
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (ScreeingsModel) {Long} id 场次主键id
	* @apiParam (ScreeingsModel) {Long} [scenicId] 景区ID
	* @apiParam (ScreeingsModel) {String} [code] 场次标识
	* @apiParam (ScreeingsModel) {String} [name] 场次名称
	* @apiParam (ScreeingsModel) {Date} [startTime] 演出开始时间
	* @apiParam (ScreeingsModel) {Date} [endTime] 演出结束时间
	* @apiParam (ScreeingsModel) {Date} [endSaleTime] 演出停售时间
	* @apiParam (ScreeingsModel) {Date} [createTime] 创建时间
	* @apiParam (ScreeingsModel) {Date} [updateTime] 修改时间
	*
	* 
	* @apiSuccess {object} object 影响数据库:stock 表:prod_screeings 字段:所有字段
	*
	* @apiParamExample 请求参数示例
	*	{
	*		model:{
	*			id:1125
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Integer} data 修改场次成功数量
	* 
	* @apiSuccessExample {json} 成功响应数据
	* {
	*   "errorCode":10000,
	*   "errorMsg":"ok",
	*   "data":1
	* }
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
	public Result<Integer> updateScreeings(ScreeingsModel model, ServiceContext context);
}
