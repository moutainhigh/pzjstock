/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service;

import java.util.ArrayList;

import com.pzj.core.product.model.area.AreaModel;
import com.pzj.core.product.model.area.AreaQueryReqModel;
import com.pzj.core.product.model.area.AreaQueryRequestModel;
import com.pzj.core.product.model.area.TheaterAreaDetailRespModel;
import com.pzj.core.product.model.area.TheaterAreaReqModel;
import com.pzj.core.product.model.area.TheaterAreaRespModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.entity.QueryResult;

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

	/**
	 * 查找剧场区域详情
	 * @param reqModel
	 * @param context
	 * @return
	 */
	/**
	* @api {dubbo} com.pzj.core.product.service.AreaQueryService#queryAreaDetail 查找剧场区域详情
	* @apiName 查找剧场区域详情
	* @apiGroup SAAS&ERP 区域
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 查找剧场区域详情
	*
	* @apiParam (请求参数) {TheaterAreaReqModel} reqModel 区域详情查询参数
	* @apiParam (请求参数) {ServiceContext} [context] 上下文
	*
	* @apiParam (TheaterAreaReqModel) {Long} supplierId 供应商id
	* @apiParam (TheaterAreaReqModel) {Long} theaterId 剧场id
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"reqModel":{
	*			"supplierId":12345678,
	*			"theaterId":1
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {TheaterAreaDetailRespModel} data 返回区域详情信息对象
	* 
	* 
	* @apiParam (TheaterAreaDetailRespModel) {Long} supplierId 供应商id
	* @apiParam (TheaterAreaDetailRespModel) {Long} theaterId 剧场id
	* @apiParam (TheaterAreaDetailRespModel) {String} theaterName 剧场名称
	* @apiParam (TheaterAreaDetailRespModel) {Integer} seatChartType 座位图类型;1:无座位图，2:矩形座位图
	* @apiParam (TheaterAreaDetailRespModel) {String} seatChartIcon 座位图缩影
	* @apiParam (TheaterAreaDetailRespModel) {Integer} fetchSeatModel 选座模式;1:用户自选，2:后台分配
	* @apiParam (TheaterAreaDetailRespModel) {List} areas 剧场下的区域集合AreaModel
	* 
	* 
	* @apiParam (AreaModel) {Long} id 区域主键id
	* @apiParam (AreaModel) {Long} scenicId 剧场id
	* @apiParam (AreaModel) {String} code 区域标识 
	* @apiParam (AreaModel) {String} name 区域名称
	* @apiParam (AreaModel) {Date} [createTime] 创建时间
	* @apiParam (AreaModel) {Date} [updateTime] 修改时间 
	*  
	* @apiSuccessExample {json} 成功响应数据
	* {
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":
	*               {
	*					"supplierId":12345678,
	*					"theaterId":2216619741563804,
	*					"theaterName":"演艺名称",
	*					"seatChartType":2,
	*					"seatChartIcon":"",
	*					"fetchSeatModel":1,
	*					"areas":[
	*						{
	*							"id":11122,
	*							"scenicId":2216619741563804,
	*							"code":"区域标识",
	*							"name":"区域名称",
	*							"createTime":"2016-09-01 16:27:21",
	*							"updateTime":"2016-09-02 16:27:21"
	*						}
	*					]
	*			    }
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
	Result<TheaterAreaDetailRespModel> queryAreaDetail(TheaterAreaReqModel reqModel, ServiceContext context);

	/**
	* @api {dubbo} com.pzj.core.product.service.AreaQueryService#queryAreas 查询供应商下的所有剧场及剧场下的区域
	* @apiName 查询供应商下的所有剧场及剧场下的区域
	* @apiGroup SAAS&ERP 区域
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 查询供应商下的所有剧场及剧场下的区域，并且按照剧场分页
	*
	* @apiParam (请求参数) {AreaQueryReqModel} areaQueryReqModel 区域查询参数
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (AreaQueryReqModel) {Long} supplierId 供应商id
	* @apiParam (AreaQueryReqModel) {Integer} [pageSize] 分页数量
	* @apiParam (AreaQueryReqModel) {Integer} [currentPage] 当前页数
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"areaQueryReqModel":{
	*			"supplierId":12345678,
	*			"pageSize":1,
	*			"currentPage":20
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {TheaterAreaRespModel} data 结果集为集合;
	* 
	* 
	* @apiParam (TheaterAreaRespModel) {Long} supplierId 供应商id
	* @apiParam (TheaterAreaRespModel) {Long} theaterId 剧场id
	* @apiParam (TheaterAreaRespModel) {String} theaterName 剧场名称
	* @apiParam (TheaterAreaRespModel) {AreaModel} areas 剧场下的区域集合
	* 
	* 
	* @apiParam (AreaModel) {Long} id 区域主键id
	* @apiParam (AreaModel) {Long} scenicId 景区id
	* @apiParam (AreaModel) {String} code 区域标识 
	* @apiParam (AreaModel) {String} name 区域名称
	* @apiParam (AreaModel) {Date} createTime 创建时间
	* @apiParam (AreaModel) {Date} updateTime 修改时间 
	*  
	* @apiSuccessExample {json} 成功响应数据
	* {
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":[
	*               {
	*					"supplierId":12345678,
	*					"theaterId":2216619741563804,
	*					"theaterName":"演艺名称",
	*					"areas":{
	*							"id":11122,
	*							"scenicId":2216619741563804,
	*							"code":"区域标识",
	*							"name":"区域名称",
	*							...
	*						}
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
	Result<QueryResult<TheaterAreaRespModel>> queryAreas(AreaQueryReqModel areaQueryReqModel,
			ServiceContext serviceContext);

}
