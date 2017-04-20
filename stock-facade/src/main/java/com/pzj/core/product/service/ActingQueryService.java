/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service;

import java.util.ArrayList;

import com.pzj.core.product.model.ActingModel;
import com.pzj.core.product.model.ActingQueryRequestModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 演艺读操作
 * 
 * @author dongchunfu
 * @version $Id: ActingQueryService.java, v 0.1 2016年8月1日 下午3:00:06 dongchunfu Exp $
 */
public interface ActingQueryService {

	/**
	* @api {dubbo} com.pzj.core.product.service.ActingQueryService.queryActingById 根据主键查演艺
	* @apiName 演艺主键查
	* @apiGroup 演艺接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 演艺主键查
	*
	* @apiParam (请求参数) {ActingQueryRequestModel} model 演艺场次查询参数对象
	* @apiParam (请求参数) {ServiceContext} [context] 上下文
	*
	* @apiParam (ActingQueryRequestModel) {Long} actingId 演艺ID
	* @apiParam (ActingQueryRequestModel) {Long} [supplierId] 供应商ID
	* @apiParam (ActingQueryRequestModel) {Long} [scenicId] 景区id
	* @apiParam (ActingQueryRequestModel) {Integer} [state] 状态(1 正常 2 停用，默认为1)
	* @apiParam (ActingQueryRequestModel) {String} [name] 演艺名称
	* @apiParam (ActingQueryRequestModel) {Integer} [whetherNeedSeat] 是否需要选座(1 需要选座 2 不需要选座，默认2)
	* 
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
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"actingId":1
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {ActingModel} data 返回结果对象;
	*
	* @apiSuccessExample {json} 成功响应数据
	* {
	*   "errorCode":10000,
	*   "errorMsg":"ok",
	*   "data":{
	*       "id":1,
	*       "scenicId":2216619741563797,
	*		"supplierId":2216619741564350,
	*		"state":1,
	*		"name":"陕西演艺",
	*		"whetherNeedSeat":2,
	*		"createTime":"2016-09-01 16:27:21",
	*	}
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
	Result<ActingModel> queryActingById(ActingQueryRequestModel model, ServiceContext context);

	/**
	 * 获取该供应商持有的所有演艺
	 * 供应商ID为必传参数
	 * @author dongchunfu
	 */

	/**
	* @api {dubbo} com.pzj.core.product.service.ActingQueryService.queryActingByParam 演艺参数查
	* @apiName 演艺参数查
	* @apiGroup 演艺接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 123演艺参数查
	*
	* @apiParam (请求参数) {ActingQueryRequestModel} model 演艺场次查询参数对象
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (ActingQueryRequestModel) {Long} actingId 演艺ID
	* @apiParam (ActingQueryRequestModel) {Long} [supplierId] 供应商ID
	* @apiParam (ActingQueryRequestModel) {Long} [scenicId] 景区id
	* @apiParam (ActingQueryRequestModel) {Integer} [state] 状态(1 正常 2 停用，默认为1)
	* @apiParam (ActingQueryRequestModel) {String} [name] 演艺名称
	* @apiParam (ActingQueryRequestModel) {Integer} [whetherNeedSeat] 是否需要选座(1 需要选座 2 不需要选座，默认2)
	*
	*
	* @apiParam (ActingModel) {Long} id 演艺主键id
	* @apiParam (ActingModel) {Long} scenicId 景区ID
	* @apiParam (ActingModel) {Long} supplierId 供应商id
	* @apiParam (ActingModel) {Integer} state 状态（1 正常 2 停用，默认为1）
	* @apiParam (ActingModel) {String} name 演艺名称
	* @apiParam (ActingModel) {Integer} whetherNeedSeat 是否需要选座（1 需要选座 2 不需要选座，默认2）
	* @apiParam (ActingModel) {Date} [createTime] 创建时间
	* @apiParam (ActingModel) {Date} [updateTime] 修改时间
	* @apiParam (ActingModel) {String} [remarks] 演艺描述
	* @apiParam (ActingModel) {Long} [areaIds] 关联区域ID集合 List<Long>
	* @apiParam (ActingModel) {Long} [screeinsIds] 关联场次ID集合 List<Long>
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"actingId":1
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {ArrayList} data 返回ActingModel对象集合;
	*
	* @apiSuccessExample {json} 成功响应数据
	* {
	*   "errorCode":10000,
	*   "errorMsg":"ok",
	*   "data":[
	*	  {
	*	      id:1,
	*	      scenicId:2216619741563797,
	*		  supplierId:2216619741564350,
	*         state:1,
	*         name:"陕西演艺",
	*         whetherNeedSeat:2,
	*         createTime:2016-09-01 16:27:21
	*     }
	*   ]
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
	Result<ArrayList<ActingModel>> queryActingByParam(ActingQueryRequestModel model, ServiceContext context);

}
