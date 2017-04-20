/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.service;

import java.util.ArrayList;

import com.pzj.core.product.model.assign.AssignedOrderQueryReqModel;
import com.pzj.core.product.model.assign.AssignedOrderQueryRespModel;
import com.pzj.core.product.model.assign.CalendarAssignReqModel;
import com.pzj.core.product.model.assign.CalendarAssignRespModel;
import com.pzj.core.product.model.assign.TheaterAssignRespModel;
import com.pzj.core.product.model.assign.TheaterScreeingAssignReqModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 分配订单模块查询类
 * @author Administrator
 * @version $Id: AssignedOrderQueryService.java, v 0.1 2017年3月8日 下午2:45:18 Administrator Exp $
 */
public interface AssignedOrderQueryService {

	/**
	* @api {dubbo} com.pzj.core.product.service.AssignedOrderQueryService#queryAssignedOrders 查询某场次下的待分配订单列表
	* @apiName 查询某场次下的待分配订单列表
	* @apiGroup SAAS&ERP 座位
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 根据剧场、场次、时间查询出待分配订单列表
	*
	* @apiParam (请求参数) {AssignedOrderQueryReqModel} model 区域查询参数
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (AssignedOrderQueryReqModel) {Long} screeningId 场次id
	* @apiParam (AssignedOrderQueryReqModel) {Date} theaterDate 演出时间 年月日
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"screeningId":987654,
	*			"theaterDate":"2017-03-08"
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {AssignedOrderQueryRespModel} data 结果集为集合;
	* 
	* 
	* @apiParam (AssignedOrderQueryRespModel) {Long} assignedId 主键id
	* @apiParam (AssignedOrderQueryRespModel) {Long} state 分配状态 1.	待分配     2.	分配完成

	* @apiParam (AssignedOrderQueryRespModel) {String} transactionId 交易id
	* @apiParam (AssignedOrderQueryRespModel) {Long} areaId 区域id
	* @apiParam (AssignedOrderQueryRespModel) {String} areaName 区域名称
	* @apiParam (AssignedOrderQueryRespModel) {Integer} occupiedNum 已分配
	* @apiParam (AssignedOrderQueryRespModel) {Integer} unOccupiedNum 待分配(总需分配数量)
	* 
	*  
	* @apiSuccessExample {json} 成功响应数据
	* {
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":[
	*               {
	*					"assignedId":12345678,
	*					"state":1,
	*					"transactionId":"MF1587451",
	*					"areaId":"11",
	*					"areaName":"VIP",
	*					"occupiedNum":"10",
	*					"unOccupiedNum":"20"
	*			    },
	*  				 {
	*					"assignedId":23456789,
	*					"state":2,
	*					"transactionId":"MF1587452",
	*					"areaId":22,
	*					"areaName":"B",
	*					"occupiedNum":8,
	*					"unOccupiedNum":10
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
	Result<ArrayList<AssignedOrderQueryRespModel>> queryAssignedOrders(AssignedOrderQueryReqModel model,
			ServiceContext serviceContext);

	/**
	 * 查询剧场场次待分配座位场次列表,以及统计信息
	 * @param reqModel
	 * @param serviceContext
	 * @return
	 */
	/**
	* @api {dubbo} com.pzj.core.product.service.AssignedOrderQueryService.queryArtSpuAssignCountOrders 查询演艺产品待分配座位场次区域列表,以及统计信息
	* @apiName 查询演艺产品待分配座位场次区域列表,以及统计信息
	* @apiGroup SAAS&ERP 统计
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 查询演艺产品待分配座位场次区域列表,以及统计信息
	*
	* @apiParam (请求参数) {TheaterScreeingAssignReqModel} reqModel 查询待分配座位统计查询参数
	* @apiParam (请求参数) {ServiceContext} [context] 上下文
	*
	* @apiSuccess (TheaterScreeingAssignReqModel) {Long} supplierId 供应商id
	* @apiSuccess (TheaterScreeingAssignReqModel) {Date} showTime 演艺时间
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"supplierId":1,
	*			"showTime":"1017-03-08"
	*		}
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {TheaterAssignRespModel} data 返回剧场待分配座位以及统计数据
	* 
	* @apiSuccess (TheaterAssignRespModel) {AssignOrderCountRespModel} assignOrderCount 统计订单、座位数据
	* @apiSuccess (TheaterAssignRespModel) {QueryResult} theaterScreeingAssigns 剧场场次待分配分页对象
	* 
	* @apiSuccess (AssignOrderCountRespModel) {Long} orderNum 订单数量
	* @apiSuccess (AssignOrderCountRespModel) {Integer} seatNum 座位数
	* @apiSuccess (AssignOrderCountRespModel) {Date} showTime 演艺时间
	* 
	* @apiParam (QueryResult) {int} total 数据总数
	* @apiParam (QueryResult) {int} current_page 当前页
	* @apiParam (QueryResult) {int} total_page 总页数
	* @apiParam (QueryResult) {int} page_size 每页最大记录数
	* @apiParam (QueryResult) {List} records 查询数据TheaterScreeingAssignModel对象列表  
	* 
	* @apiSuccess (TheaterScreeingAssignModel) {Long} supplierId 供应商id
	* @apiSuccess (TheaterScreeingAssignModel) {String} theaterId 演艺剧场id
	* @apiSuccess (TheaterScreeingAssignModel) {Long} theaterName 演艺剧场名称
	* @apiSuccess (TheaterScreeingAssignModel) {List} assignAreaScreeings 待分配座位场次集合
	* 
	* 
	* @apiSuccess (ScreeingAreaAssignRespModel) {String} screeingName 场次名称
	* @apiSuccess (ScreeingAreaAssignRespModel) {String} areaName 区域名称
	* @apiSuccess (ScreeingAreaAssignRespModel) {Long} screeingId 场次ID
	* @apiSuccess (ScreeingAreaAssignRespModel) {Long} areaId 区域id
	* @apiSuccess (ScreeingAreaAssignRespModel) {Integer} assignNum 待分配数量
	* 
	* @apiSuccessExample {json} 成功响应数据
	*	{
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":{
	*				"assignOrderCount":{
	*					"orderNum":1,
	*					"seatNum":3,
	*					"showTime":"2017-03-08",
	*				},
	*				"theaterScreeingAssigns":{
	*			 	"total": 508,
	*          		"current_page": 1,
	*          		"total_page": 26,
	*          		"page_size": 20,
	*          		"records": [
	*          			{
	* 					"supplierId" : 1,
	*					"theaterId" : 1,
	*					"theaterName" : "边城演艺",
	*					"assignAreaScreeings":[
	*						{
	*				   		"screeingName":"场次名称",
	*						"areaName":"区域名称",
	*						"screeingId":2216619741563801,
	*						"areaId":2216619741563801,
	*						"assignNum":8
	*			    		}
	*					]
	*					}
	*          		]
	*				}
	*			}
	*	}
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
	Result<TheaterAssignRespModel> queryArtSpuAssignCountOrders(TheaterScreeingAssignReqModel reqModel,
			ServiceContext serviceContext);

	/**
	 * 统计当前供应商一个有多少个座位待分配
	 * @param supplierId
	 * @param serviceContext
	 * @return
	 */
	/**
	* @api {dubbo} com.pzj.core.product.service.AssignedOrderQueryService.countAssignSeatTotal 统计当前供应商共有多少个座位待分配
	* @apiName 统计当前供应商共有多少个座位待分配
	* @apiGroup SAAS&ERP 统计
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 统计当前供应商共有多少个座位待分配
	*
	* @apiParam (请求参数) {Long} supplierId 供应商id
	* @apiParam (请求参数) {ServiceContext} [context] 上下文
	*
	*
	* @apiParamExample 请求参数示例
	*	{
	*		model:{
	*			supplierId : 1125
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Integer} data 返回统计数量
	*
	* @apiSuccessExample {json} 成功响应数据
	* {
	*   "errorCode":10000,
	*   "errorMsg":"ok",
	*   "data":12
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
	Result<Integer> countAssignSeatTotal(Long supplierId, ServiceContext serviceContext);

	/**
	 * 按日历查询返回有待分配座位天
	 * @param reqModel
	 * @param serviceContext
	 * @return
	 */
	/**
	* @api {dubbo} com.pzj.core.product.service.AssignedOrderQueryService.countAssignSeatTotal 按日历查询返回有待分配座位天
	* @apiName 按日历查询返回有待分配座位天
	* @apiGroup SAAS&ERP 统计
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 按日历查询返回有待分配座位天
	*
	* @apiParam (请求参数) {CalendarAssignReqModel} reqModel 按日历查询参数
	* @apiParam (请求参数) {ServiceContext} [context] 上下文
	*
	* @apiParamExample 请求参数示例
	*	{
	*		model:{
	*			"supplierId" : 1125,
	*			"calendarDate" : 20170312
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {CalendarAssignRespModel} data 返回日历有待分配座位的天
	* 
	* @apiParam (响CalendarAssignRespModel参数) {List} [days] 返回待分配的天集合
	*
	* @apiSuccessExample {json} 成功响应数据
	* {
	*   "errorCode":10000,
	*   "errorMsg":"ok",
	*   "data":[12,13]
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
	Result<CalendarAssignRespModel> queryCalendarAssignIden(CalendarAssignReqModel reqModel,
			ServiceContext serviceContext);
}
