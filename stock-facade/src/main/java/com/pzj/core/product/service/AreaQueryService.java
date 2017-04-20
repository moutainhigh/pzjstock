/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service;

import java.util.ArrayList;

import com.pzj.core.product.model.AreaModel;
import com.pzj.core.product.model.AreaQueryRequestModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 演绎区域读操作
 * @author dongchunfu
 * @version $Id: ActingAreaService.java, v 0.1 2016年8月1日 上午10:37:43 dongchunfu Exp $
 */
public interface AreaQueryService {
	/**
	 * 根据ID查询演绎区域
	 * @author dongchunfu
	 */

	/**
	* @api {dubbo} com.pzj.core.product.service.AreaQueryService.queryAreaById 根据主键ID查询演绎区域
	* @apiName 根据主键ID查询演绎区域
	* @apiGroup 演艺接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 根据ID查询演绎区域
	*
	* @apiParam (请求参数) {AreaQueryRequestModel} model 演绎区域查询参数对象
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (AreaQueryRequestModel) {Long} areaId 演绎区域ID
	* @apiParam (AreaQueryRequestModel) {Long} [scenicId] 景区ID
	* @apiParam (AreaQueryRequestModel) {String} [code] 区域标识
	* @apiParam (AreaQueryRequestModel) {String} [name] 区域名称
	*
	* 
	* @apiParam (AreaModel) {Long} scenicId 景区ID
	* @apiParam (AreaModel) {String} code 区域标识
	* @apiParam (AreaModel) {String} name 区域名称 
	* @apiParam (AreaModel) {Long} [id] 区域主键id
	* @apiParam (AreaModel) {Date} [createTime] 创建时间
	* @apiParam (AreaModel) {Date} [updateTime] 修改时间
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"areaId":1,
	*			"scenicId":2216619741563797,
	*			"code":"vip",
	*			"name":"VIP区"
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {AreaModel} data 返回结果对象;
	*
	* @apiSuccessExample {json} 成功响应数据
	* {
	*   "errorCode":10000,
	*   "errorMsg":"ok",
	*   "data":{
	*       "id":1,
	*       "scenicId":2216619741563804,
	*       "code":"A区",
	*       "name":"A区",
	*       "createTime":"2016-09-01 16:27:21"
	*   }
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
	Result<AreaModel> queryAreaById(AreaQueryRequestModel model, ServiceContext context);

	/**
	 * 获取该供应商持有的所有区域
	 * 供应商ID为必传参数
	 * @author dongchunfu
	 */

	/**
	* @api {dubbo} com.pzj.core.product.service.AreaQueryService.queryAreasByParam 区域参数查
	* @apiName 区域参数查
	* @apiGroup 演艺接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 获取该供应商持有的所有区域
	*
	* @apiParam (请求参数) {AreaQueryRequestModel} model 演绎区域查询参数对象
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (AreaQueryRequestModel) {Long} scenicId 景区ID
	* @apiParam (AreaQueryRequestModel) {Long} [areaId] 区域ID
	* @apiParam (AreaQueryRequestModel) {String} [code] 区域标识
	* @apiParam (AreaQueryRequestModel) {String} [name] 区域名称
	*
	* 
	* @apiSuccess (AreaModel) {Long} scenicId 景区ID
	* @apiSuccess (AreaModel) {String} code 区域标识
	* @apiSuccess (AreaModel) {String} name 区域名称
	* @apiSuccess (AreaModel) {Long} [id] 区域主键id
	* @apiSuccess (AreaModel) {Date} [createTime] 创建时间
	* @apiSuccess (AreaModel) {Date} [updateTime] 修改时间
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"areaId":1,
	*			"scenicId":2216619741563797,
	*			"code":"vip",
	*			"name":"VIP区"
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {ArrayList} data 返回查询AreaModel结果对象集合;
	*  
	* @apiSuccessExample {json} 成功响应数据
	* {
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":[
	*               {
	*					"id":1,
	*					"scenicId":2216619741563804,
	*					"code":"A区",
	*					"name":"A区",
	*					"createTime":"2016-09-01 16:27:21"
	*			    }
	*           ]
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
	Result<ArrayList<AreaModel>> queryAreasByParam(AreaQueryRequestModel model, ServiceContext context);

	/**
	 * 座位图界面加载获取区域列表
	 * @param model
	 * @param context
	 * @return
	 */
	Result<ArrayList<AreaModel>> queryAreasByWebSeatChartParam(AreaQueryRequestModel model, ServiceContext context);

}
