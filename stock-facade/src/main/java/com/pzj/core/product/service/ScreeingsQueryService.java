/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service;

import java.util.ArrayList;

import com.pzj.core.product.model.screeings.ArtSpuScreeingOrderModel;
import com.pzj.core.product.model.screeings.ScreeingAreaReqModel;
import com.pzj.core.product.model.screeings.ScreeingAreaRespModel;
import com.pzj.core.product.model.screeings.ScreeingsModel;
import com.pzj.core.product.model.screeings.ScreeingsQueryRequestModel;
import com.pzj.core.product.model.screeings.TheaterScreeingOrderReqModel;
import com.pzj.core.product.model.screeings.TheaterScreeingReqModel;
import com.pzj.core.product.model.screeings.TheaterScreeingRespModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.entity.QueryResult;

/**
 * 演艺场次读操作
 * @author dongchunfu
 * @version $Id: ActingAreaService.java, v 0.1 2016年8月1日 上午10:37:43 dongchunfu Exp $
 */
public interface ScreeingsQueryService {

	/**
	 * @api {dubbo} com.pzj.core.product.service.ScreeingsQueryService.queryScreeingsById 通过场次ID查询场次
	 * @apiName 通过场次ID查询场次
	 * @apiGroup 演艺接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 通过场次ID查询场次
	 *
	 * @apiParam (请求参数) {ScreeingsQueryRequestModel} model 查询场次请求model
	 * @apiParam (请求参数) {ServiceContext} [serviceContext] 请求上下文
	 * 
	 * @apiParam (ScreeingsQueryRequestModel) {Long} screeingsId 演绎场次ID
	 * @apiParam (ScreeingsQueryRequestModel) {String} scenicId 景区ID
	 * @apiParam (ScreeingsQueryRequestModel) {String} [code] 场次标识
	 * @apiParam (ScreeingsQueryRequestModel) {Integer} [name] 场次名称
	 * @apiParam (ScreeingsQueryRequestModel) {Integer} [startTime] 演出开始时间，例如：1000
	 * 
	 * @apiParamExample {json} 请求示例
	 *   {
	 *       "model" : {
	 *          "scenicId": 2216619741564659,
	 *          "screeingsId": 2216619741564659
	 *       },
	 *       serviceContext:{
	 *           .........
	 *       }
	 *   }
	 *   
	 * 
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {ScreeingsModel} data 返回场次结果对象;
	 * 
	 * @apiSuccess (ScreeingsModel) {Long} scenicId 景区ID
	 * @apiSuccess (ScreeingsModel) {String} code 场次标识
	 * @apiSuccess (ScreeingsModel) {String} name 场次名称
	 * @apiSuccess (ScreeingsModel) {Date} startTime 演出开始时间
	 * @apiSuccess (ScreeingsModel) {Date} endTime 演出结束时间
	 * @apiSuccess (ScreeingsModel) {Long} id 场次主键id
	 * @apiSuccess (ScreeingsModel) {Date} [createTime] 创建时间
	 * @apiSuccess (ScreeingsModel) {Date} [updateTime] 修改时间
	 *
	 * @apiSuccessExample {json} 成功响应数据
	 *   {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : {
	 *			"id":1,
	 *			"scenicId":2216619741563801,
	 *			"code":"1",
	 *			"name":"早场",
	 *			"startTime":"830",
	 *			"endTime":"1200",
	 *			"endSaleTime":"1130",
	 *			"createTime":"2016-09-01 16:27:21"
	 *      }
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
	Result<ScreeingsModel> queryScreeingsById(ScreeingsQueryRequestModel model, ServiceContext context);

	/**
	 * 获取该供应商持有的所有场次
	 * 供应商ID为必传参数
	 * @author dongchunfu
	 */

	/**
	* @api {dubbo} com.pzj.core.product.service.ScreeingsQueryService.queryScreeingsesByParam 场次参数查
	* @apiName 获取该供应商持有的所有场次
	* @apiGroup 演艺接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 获取该供应商持有的所有场次
	*
	* @apiParam (请求参数) {ScreeingsQueryRequestModel} model 演艺场次查询参数对象
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (ScreeingsQueryRequestModel) {Long} screeingsId 演艺场次ID
	* @apiParam (ScreeingsQueryRequestModel) {Long} [scenicId] 景区ID
	* @apiParam (ScreeingsQueryRequestModel) {String} [code] 场次标识
	* @apiParam (ScreeingsQueryRequestModel) {String} [name] 场次名称
	* @apiParam (ScreeingsQueryRequestModel) {String} [startTime] 演出开始时间
	*
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"screeingsId":1
	*		}
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {ArrayList} data 返回结果ScreeingsModel对象集合;
	* 
	* @apiSuccess (ScreeingsModel) {Long} scenicId 景区ID
	* @apiSuccess (ScreeingsModel) {String} code 场次标识
	* @apiSuccess (ScreeingsModel) {String} name 场次名称
	* @apiSuccess (ScreeingsModel) {Date} startTime 演出开始时间
	* @apiSuccess (ScreeingsModel) {Date} endTime 演出结束时间
	* @apiSuccess (ScreeingsModel) {Long} [id] 场次主键id
	* @apiSuccess (ScreeingsModel) {Date} [endSaleTime] 演出停售时间
	* @apiSuccess (ScreeingsModel) {Date} [createTime] 创建时间
	* @apiSuccess (ScreeingsModel) {Date} [updateTime] 修改时间 
	* 
	* @apiSuccessExample {json} 成功响应数据
	*	{
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":[
	*				{
	*				   "id":1,
	*					"scenicId":2216619741563801,
	*					"code":"1",
	*					"name":"早场",
	*					"startTime":"830",
	*					"endTime":"1200",
	*					"endSaleTime":"1130",
	*					"createTime":"2016-09-01 16:27:21"
	*			    }
	*			]
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
	Result<ArrayList<ScreeingsModel>> queryScreeingsesByParam(ScreeingsQueryRequestModel model, ServiceContext context);

	/**
	 * 查询供应商对应的剧场场次列表
	 * @param supplierId
	 * @param context
	 * @return
	 */
	/**
	* @api {dubbo} com.pzj.core.product.service.ScreeingsQueryService.queryTheaterScreeings 查询供应商对应的剧场场次列表
	* @apiName 查询供应商对应的剧场场次列表
	* @apiGroup SAAS&ERP 场次
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 查询供应商对应的剧场场次列表
	*
	* @apiParam (请求参数) {TheaterScreeingReqModel} reqModel 剧场场次分页列表查询参数
	* @apiParam (请求参数) {ServiceContext} [context] 上下文
	* 
	* @apiParam (TheaterScreeingReqModel) {Long} supplierId 供应商id
	* @apiParam (TheaterScreeingReqModel) {int} [current_page] 当前页码, 默认为1
	* @apiParam (TheaterScreeingReqModel) {int} [page_size] 每页显示的最大数量, 默认为20
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"supplierId":1
	*		}
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {QueryResult} data 返回分页结果
	* 
	* @apiParam (QueryResult) {int} total 数据总数
	* @apiParam (QueryResult) {int} current_page 当前页
	* @apiParam (QueryResult) {int} total_page 总页数
	* @apiParam (QueryResult) {int} page_size 每页最大记录数
	* @apiParam (QueryResult) {List} records 查询数据TheaterScreeingRespModel对象列表 
	* 
	* @apiSuccess (TheaterScreeingRespModel) {Long} supplierId 供应商id
	* @apiSuccess (TheaterScreeingRespModel) {Long} theaterId 演艺剧场id
	* @apiSuccess (TheaterScreeingRespModel) {List} screeings 场次集合ScreeingsModel
	* 
	* @apiSuccess (ScreeingsModel) {Long} id 场次ID
	* @apiSuccess (ScreeingsModel) {Long} supplierId 供应商ID
	* @apiSuccess (ScreeingsModel) {Long} scenicId 景区ID
	* @apiSuccess (ScreeingsModel) {String} code 场次标识
	* @apiSuccess (ScreeingsModel) {String} name 场次名称
	* @apiSuccess (ScreeingsModel) {String} startTime 演出开始时间
	* @apiSuccess (ScreeingsModel) {String} endTime 演出结束时间
	* @apiSuccess (ScreeingsModel) {Date} [createTime] 创建时间
	* @apiSuccess (ScreeingsModel) {Date} [updateTime] 修改时间 
	* 
	* @apiSuccessExample {json} 成功响应数据
	*	{
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":{
	*			 	"total": 508,
	*          		"current_page": 1,
	*          		"total_page": 26,
	*          		"page_size": 20,
	*          		"records": [
	*          		{
	*					"supplierId":1,
	*					"theaterId":3,
	*					"screeings":[
	*				   		"id":1,
	*						"supplierId":1,
	*						"scenicId":2216619741563801,
	*						"name":"早场",
	*						"startTime":"8:30",
	*						"endTime":"12:00",
	*						"createTime":"2016-09-01 16:27:21"					
	*					]
	*				}]
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
	Result<QueryResult<TheaterScreeingRespModel>> queryTheaterScreeings(TheaterScreeingReqModel reqModel,
			ServiceContext context);

	/**
	* @api {dubbo} com.pzj.core.product.service.ScreeingsQueryService.queryScreeingDetail 查询场次详情
	* @apiName 查询场次详情
	* @apiGroup SAAS&ERP 场次
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 查询场次详情
	*
	* @apiParam (请求参数) {Long} screeingId 场次ID
	* @apiParam (请求参数) {ServiceContext} [context] 上下文
	*
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"screeingId":1
	*		}
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {ScreeingsModel} data 返回结果场次对象;
	* 
	* @apiSuccess (ScreeingsModel) {Long} id 场次ID
	* @apiSuccess (ScreeingsModel) {Long} supplierId 供应商ID
	* @apiSuccess (ScreeingsModel) {Long} scenicId 景区ID
	* @apiSuccess (ScreeingsModel) {Long} state 状态
	* @apiSuccess (ScreeingsModel) {String} code 场次标识
	* @apiSuccess (ScreeingsModel) {String} name 场次名称
	* @apiSuccess (ScreeingsModel) {String} startTime 演出开始时间
	* @apiSuccess (ScreeingsModel) {String} endTime 演出结束时间
	* @apiSuccess (ScreeingsModel) {Date} createTime 创建时间
	* @apiSuccess (ScreeingsModel) {Date} updateTime 修改时间 
	* 
	* @apiSuccessExample {json} 成功响应数据
	*	{
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":{
	*				"id":1,
	*				"scenicId":2216619741563801,
	*				"supplierId":12,
	*				"name":"早场",
	*				"code":"zc",
	*				"state":1,
	*				"startTime":"8:30",
	*				"endTime":"12:00",
	*				"createTime":"2016-09-01 16:27:21",
	*				"updateTime":"2016-09-02 16:27:21"
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
	Result<ScreeingsModel> queryScreeingDetail(Long screeingId, ServiceContext context);

	/**
	 * 场次预订率分页查询
	 * @param reqModel
	 * @param context
	 * @return
	 */
	/**
	* @api {dubbo} com.pzj.core.product.service.ScreeingsQueryService.queryArtSpuScreeingsOrderByPage 场次预订率分页查询
	* @apiName 场次预订率分页查询
	* @apiGroup SAAS&ERP 场次
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 场次预订率分页查询
	*
	* @apiParam (请求参数) {TheaterScreeingOrderReqModel} reqModel 剧场预订率查询model
	* @apiParam (请求参数) {ServiceContext} [context] 上下文
	*
	* @apiSuccess (TheaterScreeingOrderReqModel) {Long} supplierId 供应商id
	* @apiSuccess (TheaterScreeingOrderReqModel) {Date} showTime 演艺时间
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"supplierId":1,
	*			"showTime":"2017-03-08"
	*		}
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {QueryResult} data 返回预订率剧场分页列表
	* 
	* 
	* @apiParam (QueryResult) {int} total 数据总数
	* @apiParam (QueryResult) {int} current_page 当前页
	* @apiParam (QueryResult) {int} total_page 总页数
	* @apiParam (QueryResult) {int} page_size 每页最大记录数
	* @apiParam (QueryResult) {List} records 查询数据ArtSpuScreeingOrderModel对象列表  
	* 
	* @apiSuccess (ArtSpuScreeingOrderModel) {Long} supplierId 供应商id
	* @apiSuccess (ArtSpuScreeingOrderModel) {Long} spuId 演艺产品id
	* @apiSuccess (ArtSpuScreeingOrderModel) {String} spuName 演艺产品名称
	* @apiSuccess (ArtSpuScreeingOrderModel) {List} screeingOrders 场次预定信息ScreeingOrderRespModel
	* 
	* 
	* @apiSuccess (ScreeingOrderRespModel) {Long} id 场次ID
	* @apiSuccess (ScreeingOrderRespModel) {Long} scenicId 景区ID
	* @apiSuccess (ScreeingOrderRespModel) {String} name 场次名称
	* @apiSuccess (ScreeingOrderRespModel) {String} startTime 演出开始时间
	* @apiSuccess (ScreeingOrderRespModel) {String} endTime 演出结束时间
	* @apiSuccess (ScreeingOrderRespModel) {Integer} saleNum 销售数量
	* @apiSuccess (ScreeingOrderRespModel) {Double} saleRate 销售率
	* 
	* @apiSuccessExample {json} 成功响应数据
	*	{
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":{
	*			 	"total": 508,
	*          		"current_page": 1,
	*          		"total_page": 26,
	*          		"page_size": 20,
	*          		"records": [
	*          			{
	* 					"supplierId" : 1,
	*					"spuId" : 1,
	*					"spuName" : "边城演艺产品",
	*					"screeingOrders":[
	*						{
	*				   		"id":1,
	*						"supplierId":2,
	*						"scenicId":2216619741563801,
	*						"name":"早场",
	*						"startTime":"8:30",
	*						"endTime":"12:00",
	*						"saleNum":1,
	*						"saleRate":89
	*			    		}
	*					]
	*					}
	*          		]
	*          }
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
	Result<QueryResult<ArtSpuScreeingOrderModel>> queryArtSpuScreeingsOrderByPage(
			TheaterScreeingOrderReqModel reqModel, ServiceContext context);

	/**
	* @api {dubbo} com.pzj.core.product.service.ScreeingsQueryService.queryScreeingAreaBaseInfo 查询场次区域基本信息
	* @apiName 查询场次区域基本信息
	* @apiGroup SAAS&ERP 场次区域详情
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 查询场次区域基本信息
	*
	* @apiParam (请求参数) (ScreeingAreaReqModel) {List} [screeingIds] 场次ID集合,场次id或区域id必须传一个
	* @apiParam (请求参数) (ScreeingAreaReqModel) {List} [areaIds] 区域id集合,场次id或区域id必须传一个
	* @apiParam (请求参数) {ServiceContext} [context] 上下文
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"screeingIds":[1],
	*			"areaIds":[1]
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {ScreeingAreaRespModel} data 返回结果场次区域基本信息对象;
	* 
	* @apiSuccess (ScreeingAreaRespModel) {List} screeings 场次基本信息集合（ScreeingsModel）
	* @apiSuccess (ScreeingAreaRespModel) {List} areas 区域基本信息集合（AreaModel）
	* 
	* @apiSuccess (ScreeingsModel) {Long} id 场次ID
	* @apiSuccess (ScreeingsModel) {Long} scenicId 景区ID
	* @apiSuccess (ScreeingsModel) {String} name 场次名称
	* 
	* @apiParam (AreaModel) {Long} id 区域主键id
	* @apiParam (AreaModel) {Long} scenicId 景区ID
	* @apiParam (AreaModel) {String} name 区域名称 
	* 
	* 
	* @apiSuccessExample {json} 成功响应数据
	*	{
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":{
	*				"screeings":[
	*					{
	*						"id":1,
	*						"scenicId":2216619741563801,
	*						"name":"早场"
	*					}
	*				],
	*				"areas":[
	*					{
	*						"id":1,
	*						"scenicId":2216619741563801,
	*						"name":"早场"
	*					}
	*				]
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
	Result<ScreeingAreaRespModel> queryScreeingAreaBaseInfo(ScreeingAreaReqModel reqModel, ServiceContext context);
}
