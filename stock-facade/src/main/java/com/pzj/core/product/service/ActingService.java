/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service;

import com.pzj.core.product.model.ActingModel;
import com.pzj.core.product.model.AddActingModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 演艺写操作
 * 
 * @author dongchunfu
 * @version $Id: ActingService.java, v 0.1 2016年8月1日 下午2:57:46 dongchunfu Exp $
 */
public interface ActingService {
	/**
	 * 创建演艺产品
	 * @author dongchunfu
	 * @param ActingModel 演艺实体
	 * @return Result<Integer> 新增实体数量
	 */

	/**
	* @api {dubbo} com.pzj.core.product.service.ActingService.createActing 创建演艺
	* @apiName 创建演艺
	* @apiGroup 演艺接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 创建演艺
	*
	* @apiParam (请求参数) {ActingModel} model 演艺VO
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (ActingModel) {Long} scenicId 景区ID
	* @apiParam (ActingModel) {Long} supplierId 供应商id
	* @apiParam (ActingModel) {Integer} state 状态（1 正常 2 停用，默认为1）
	* @apiParam (ActingModel) {String} name 演艺名称
	* @apiParam (ActingModel) {Integer} whetherNeedSeat 是否需要选座（1 需要选座 2 不需要选座，默认2）
	* @apiParam (ActingModel) {Long} [id] 演艺主键id
	* @apiParam (ActingModel) {Date} [createTime] 创建时间
	* @apiParam (ActingModel) {Date} [updateTime] 修改时间
	* @apiParam (ActingModel) {String} [remarks] 演艺描述
	* @apiParam (ActingMode) {Long} [areaIds] 关联区域ID集合 List<Long>
	* @apiParam (ActingModel) {Long} [screeinsIds] 关联场次ID集合 List<Long>
	*
	* 
	* @apiSuccess {object} object 影响数据库:stock 表:prod_acting 字段:所有字段
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"scenicId":2216619741563797,
	*			"supplierId":2216619741564350,
	*			"state":1,
	*			"name":"陕西演艺",
	*			"whetherNeedSeat":2
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Long} data 返回创建演艺数量
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
	public Result<Long> createActing(ActingModel model, ServiceContext context);

	/**
	 * 更新演艺产品
	 * @author dongchunfu
	 * @param ActingModel 演艺实体
	 * @return Result<Integer> 更新数量
	 */

	/**
	* @api {dubbo} com.pzj.core.product.service.ActingService.updateActing 演艺信息更新
	* @apiName 演艺信息更新
	* @apiGroup 演艺接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 演艺信息更新
	*
	* @apiParam (请求参数) {ActingModel} model 演艺VO
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (ActingModel) {Long} [id] 演艺主键id
	* @apiParam (ActingModel) {Long} [scenicId] 景区ID
	* @apiParam (ActingModel) {Long} [supplierId] 供应商id
	* @apiParam (ActingModel) {Integer} [state] 状态（1 正常 2 停用，默认为1）
	* @apiParam (ActingModel) {String} [name] 演艺名称
	* @apiParam (ActingModel) {Integer} [whetherNeedSeat] 是否需要选座（1 需要选座 2 不需要选座，默认2）
	* @apiParam (ActingModel) {Date} [createTime] 创建时间
	* @apiParam (ActingModel) {Date} [updateTime] 修改时间
	* @apiParam (ActingModel) {String} [remarks] 演艺描述
	* @apiParam (ActingModel) {Long} [areaIds] 关联区域ID集合 List<Long>
	* @apiParam (ActingModel) {Long} [screeinsIds] 关联场次ID集合 List<Long>
	*
	* 
	* @apiSuccess {object} object 影响数据库:stock 表:prod_acting 字段:所有字段
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"id":1,
	*			"name":"a"
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Integer} data 返回修改演艺数量
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

	public Result<Integer> updateActing(ActingModel model, ServiceContext context);

	/**
	 * web端添加演艺数据
	 * @param model
	 * @param context
	 * @return Result<Boolean> 返回操作结果 true 成功 false 失败
	 */
	public Result<Boolean> addActing(AddActingModel model, ServiceContext context);
}
