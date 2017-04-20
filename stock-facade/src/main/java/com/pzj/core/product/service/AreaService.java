/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service;

import com.pzj.core.product.model.AreaModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 演艺区域写操作
 * @author dongchunfu
 * @version $Id: ActingAreaService.java, v 0.1 2016年8月1日 上午10:37:43 dongchunfu Exp $
 */
public interface AreaService {
	/**
	 * 创建演艺区域
	 * @author dongchunfu
	 * @param area 区域实体
	 * @return Result<Long> 新增实体ID
	 */

	/**
	 * @api {dubbo} com.pzj.core.product.service.AreaService.createArea 创建演艺区域
	 * @apiName 创建演艺区域
	 * @apiGroup 演艺接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 创建演艺区域
	 *
	 * @apiParam (请求参数) {AreaModel} model 演艺区域VO
	 * @apiParam (请求参数) {ServiceContext} context 上下文
	 * 
	 * @apiParam (AreaModel) {Long} scenicId 景区ID
	 * @apiParam (AreaModel) {String} code 区域标识
	 * @apiParam (AreaModel) {String} name 区域名称
	 * @apiParam (AreaModel) {Long} [id] 区域主键id
	 * @apiParam (AreaModel) {Date} [createTime] 创建时间
	 * @apiParam (AreaModel) {Date} [updateTime] 修改时间
	 *
	 * 
	 * @apiSuccess {object} object 影响数据库:stock 表:prod_area 字段:所有字段
	 *
	 * @apiParamExample {json} 请求示例
	 *	{
	 *		"model":{
	 *			"scenicId":2216619741563804,
	 *			"code":"A区",
	 *			"name":"A区"
	 *		},
	 *		context:{
	 *			.........
	 *		}
	 *	}
	 *
	 * 
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {Long} data 返回创建区域数量
	 *
	 * @apiSuccessExample {json} 成功响应数据
	 *   {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : 1
	 *   }
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
	public Result<Long> createArea(AreaModel model, ServiceContext context);

	/**
	 * 更新演艺区域
	 * @author dongchunfu
	 * @param area 区域实体
	 * @return Result<Integer> 更新数量
	 */

	/**
	* @api {dubbo} com.pzj.core.product.service.AreaService.updateArea 更新演艺区域信息
	* @apiName 演艺区域信息更新
	* @apiGroup 演艺接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 演艺区域信息更新
	*
	* @apiParam (请求参数) {AreaModel} model 演艺区域VO
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (AreaModel) {Long} id 区域主键id
	* @apiParam (AreaModel) {Long} [scenicId] 景区ID
	* @apiParam (AreaModel) {String} [code] 区域标识
	* @apiParam (AreaModel) {String} [name] 区域名称
	* @apiParam (AreaModel) {Date} [createTime] 创建时间
	* @apiParam (AreaModel) {Date} [updateTime] 修改时间
	*
	* 
	* @apiSuccess {object} object 影响数据库:stock 表:prod_area 字段:所有字段
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"id":128
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Integer} data 修改成功数量;
	* 
	* @apiSuccessExample {json} 成功响应数据
	* {
	*   "errorCode":10000,
	*   "errorMsg":"ok",
	*	"data":1
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
	*
	*/
	public Result<Integer> updateArea(AreaModel model, ServiceContext context);
}
