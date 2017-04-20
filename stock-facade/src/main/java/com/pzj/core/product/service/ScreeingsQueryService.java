/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service;

import java.util.ArrayList;

import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.core.product.model.ScreeingsQueryRequestModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

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
	 *          "id": 2216619741564659
	 *       },
	 *       serviceContext:{
	 *           .........
	 *       }
	 *   }
	 *   
	 * 
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {StockRuleModel} data 返回结果对象;
	 * 
	 * @apiParam (StockRuleModel) {Long} id 库存规则ID
	 * @apiParam (StockRuleModel) {Integer} state 库存规则状态（  1 正常 2 停用）
	 * @apiParam (StockRuleModel) {String} name 库存规则名称 
	 * @apiParam (StockRuleModel) {Integer} category 库存规则类别
	 * @apiParam (StockRuleModel) {Integer} type 库存规则类型（  1、单一库存 2、每日库存）
	 * @apiParam (StockRuleModel) {Integer} upperLimit 库存上限
	 * @apiParam (StockRuleModel) {Long} supplierId 供应商ID 
	 * @apiParam (StockRuleModel) {Integer} whetherReturn 是否归还库存（ 1、是 2、否）
	 * @apiParam (StockRuleModel) {Date} [createTime] 创建时间
	 * @apiParam (StockRuleModel) {Date} [updateTime] 修改时间
	 *
	 * @apiSuccessExample {json} 成功响应数据
	 *   {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : {
	 *          "id": 89,
	 *          "name": "峨眉山两日游nbin测试01峨眉成人",
	 *          "category": 1,
	 *          "type": 1,
	 *          "upperLimit": 1000,
	 *          "state": 2,
	 *          "supplierId": 2216619736763722,
	 *          "whetherReturn": 2
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
}
