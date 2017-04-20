/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.service;

import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 库存规则写入相关操作
 * @author dongchunfu
 * @version $Id: StockQueryService.java, v 0.1 2016年7月22日 下午6:01:38 dongchunfu Exp $
 */
public interface StockRuleService {

	/**
	* @api {dubbo} com.pzj.core.stock.service.StockRuleService.createStockRule 创建库存规则
	* @apiName 创建库存规则
	* @apiGroup 库存接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 创建库存规则
	*
	* @apiParam (请求参数) {StockRuleModel} model 创建库存规则model
	* @apiParam (请求参数) {ServiceContext} context 请求上下文
	*
	* @apiParam (StockRuleModel) {Long} supplierId 供应商ID 
	* @apiParam (StockRuleModel) {String} name 库存规则名称 
	* @apiParam (StockRuleModel) {Integer} category 库存规则类别
	* @apiParam (StockRuleModel) {Integer} upperLimit 库存上限
	* @apiParam (StockRuleModel) {Long} [id] 库存规则ID 
	* @apiParam (StockRuleModel) {Integer} [type] 库存规则类型（  1、单一库存 2、每日库存）
	* @apiParam (StockRuleModel) {Integer} [state] 库存规则状态（  1 正常 2 停用）
	* @apiParam (StockRuleModel) {Integer} [whetherReturn] 是否归还库存（ 1、是 2、否）
	* @apiParam (StockRuleModel) {Date} [createTime] 创建时间
	* @apiParam (StockRuleModel) {Date} [updateTime] 修改时间
	*
	* @apiParamExample 请求参数示例
	* {	
	*   "model":{
	*       "name": "测试库存规则1",
	*       "category": 1,
	*       "type": 2,
	*       "upperLimit": 10000,
	*       "state": 2,
	*       "supplierId": 1234567,
	*       "whetherReturn": 2
	*	},
	*	context:{
	*		...
	*	}
	* }
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Long} data 返回创建库存规则id主键
	*
	* @apiSuccessExample {json} 成功响应数据
	*   {
	*      "errorCode" : 10000,
	*      "errorMsg" : "ok",
	*      "data" : 438
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
	* @apiExample 影响数据库数据
	*  数据库：stock
	*  stock_rule表新增字段：id, state, name, category, type, upper_limit, supplier_id, whether_return, create_time
	*
	*/
	public Result<Long> createStockRule(StockRuleModel model, ServiceContext context);

	/**
	* @api {dubbo} com.pzj.core.stock.service.StockRuleService.updateStockRule 更新库存规则
	* @apiName 更新库存规则
	* @apiGroup 库存接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 更新库存规则
	*
	* @apiParam (请求参数) {StockRuleModel} model 更新库存规则model
	* @apiParam (请求参数) {ServiceContext} context 请求上下文
	*
	* @apiParam (StockRuleModel) {Long} id 库存规则ID
	* @apiParam (StockRuleModel) {String} [name] 库存规则名称 
	* @apiParam (StockRuleModel) {Integer} [category] 库存规则类别
	* @apiParam (StockRuleModel) {Integer} [type] 库存规则类型（  1、单一库存 2、每日库存）
	* @apiParam (StockRuleModel) {Integer} [upperLimit] 库存上限
	* @apiParam (StockRuleModel) {Integer} [state] 库存规则状态（  1 正常 2 停用）
	* @apiParam (StockRuleModel) {Long} [supplierId 供应商ID
	* @apiParam (StockRuleModel) {Integer} [whetherReturn] 是否归还库存（ 1、是 2、否）
	* @apiParam (StockRuleModel) {Date} [createTime] 创建时间
	* @apiParam (StockRuleModel) {Date} [updateTime] 修改时间
	*
	*
	* @apiParamExample 请求参数示例
	*	{
	*		"model":{
	*			"id": 438,
	*           "name": "成人2-1512"
	*		}
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Integer} data 返回修改库存规则数量
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
	* @apiExample 影响数据库数据
	*  数据库：stock
	*  stock_rule表的更新字段：name等 (此接口修改库存规则设置修改相应字段)
	*  
	*/
	public Result<Integer> updateStockRule(StockRuleModel model, ServiceContext context);

	/**
	 * 更新库存规则状态
	 * @author dongchunfu
	 * @param model 库存规则实体
	 * @return 统一响应结果
	 */
	@Deprecated
	public Result<Integer> updateStockRuleState(StockRuleModel model, ServiceContext context);

	/**
	* @api {dubbo} com.pzj.core.stock.service.StockRuleService.fakeDeleteStockRule 删除库存规则
	* @apiName 删除库存规则
	* @apiGroup 库存接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 删除库存规则
	*
	* @apiParam {StockRuleModel} model 删除库存规则请求model
	* @apiParam {ServiceContext} context 请求上下文
	*
	* @apiParam (StockRuleModel) {Long} id 库存规则ID
	* @apiParam (StockRuleModel) {String} [name] 库存规则名称 
	* @apiParam (StockRuleModel) {Integer} [category] 库存规则类别
	* @apiParam (StockRuleModel) {Integer} [type] 库存规则类型（  1、单一库存 2、每日库存）
	* @apiParam (StockRuleModel) {Integer} [upperLimit] 库存上限
	* @apiParam (StockRuleModel) {Integer} [state] 库存规则状态（  1 正常 2 停用）
	* @apiParam (StockRuleModel) {Long} [supplierId] 供应商ID
	* @apiParam (StockRuleModel) {Integer} [whetherReturn] 是否归还库存（ 1、是 2、否）
	* @apiParam (StockRuleModel) {Date} [createTime] 创建时间
	* @apiParam (StockRuleModel) {Date} [updateTime] 修改时间
	*
	* 
	* @apiParamExample 请求参数示例
	*	{
	*		"model" : {
	*			"id" : 3
	*		}
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Integer} data 返回删除库存规则数量
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
	* @apiExample 影响数据库数据
	*  数据库：stock
	*  stock_rule表的更新字段：state
	*/
	public Result<Integer> fakeDeleteStockRule(StockRuleModel model, ServiceContext context);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockRuleService.updateStockRuleName 修改库存规则名称
	 * @apiName 修改库存规则名称
	 * @apiGroup 库存接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 供应端修改库存规则名称
	 *
	 * @apiParam (请求参数) {StockRuleModel} model 修改库存规则请求参数
	 * @apiParam (请求参数) {ServiceContext} [serviceContext] 上下文
	 *
	 * @apiParam (StockRuleModel) {Long} id 库存规则ID
	 * @apiParam (StockRuleModel) {String} name 库存规则名称 
	 * @apiParam (StockRuleModel) {Integer} [category] 库存规则类别
	 * @apiParam (StockRuleModel) {Integer} [type] 库存规则类型（  1、单一库存 2、每日库存）
	 * @apiParam (StockRuleModel) {Integer} [upperLimit] 库存上限
	 * @apiParam (StockRuleModel) {Integer} [state] 库存规则状态（  1 正常 2 停用）
	 * @apiParam (StockRuleModel) {Long} [supplierId] 供应商ID
	 * @apiParam (StockRuleModel) {Integer} [whetherReturn] 是否归还库存（ 1、是 2、否）
	 * @apiParam (StockRuleModel) {Date} [createTime] 创建时间
	 * @apiParam (StockRuleModel) {Date} [updateTime] 修改时间
	 * 
	 * @apiParamExample {json} 请求示例
	 *   {
	 *       "model" : {
	 *          "id": 2216619741564659,
	 *          "name": "浑身-1790"
	 *       },
	 *       serviceContext:{
	 *           .........
	 *       }
	 *   }
	 *   
	 * 
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {Integer} data 返回结果对象;返回修改库存规则数量
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
	 * @apiParam (错误码) {int} 15105 库存规则异常
	 * 
	 * @apiErrorExample {json} 异常响应数据
	 * {
	 *    "errorCode" : 15001,
	 *    "errorMsg":"参数错误"
	 * }
	 *
	 * 
	 * @apiExample 影响数据库数据
	 *  数据库：stock
	 *  stock_rule表的更新字段：name
	 *  
	 */
	public Result<Integer> updateStockRuleName(StockRuleModel model, ServiceContext context);

}
