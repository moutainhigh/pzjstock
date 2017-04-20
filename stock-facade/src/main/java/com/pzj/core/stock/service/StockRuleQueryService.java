/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.service;

import java.util.ArrayList;

import com.pzj.core.stock.model.QueryStockRuleByIdsModel;
import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.core.stock.model.StockRuleQueryRequestModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.entity.QueryResult;

/**
 * 库存规则查询服务
 * @author Administrator
 * @version $Id: StockQueryService.java, v 0.1 2016年7月22日 下午6:01:38 Administrator Exp $
 */
public interface StockRuleQueryService {
	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockRuleQueryService.queryStockRuleById 通过库存规则ID获取库存规则
	 * @apiName 通过库存规则ID获取库存规则
	 * @apiGroup 库存接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 通过库存规则ID获取库存规则
	 *
	 * @apiParam (请求参数) {StockRuleQueryRequestModel} model 查询库存规则请求model
	 * @apiParam (请求参数) {ServiceContext} [serviceContext] 上下文
	 * 
	 * @apiParam (StockRuleQueryRequestModel) {Long} ruleId 库存规则ID
	 * @apiParam (StockRuleQueryRequestModel) {String} [name] 库存规则名称 
	 * @apiParam (StockRuleQueryRequestModel) {String} [vagueName] 库存规则模糊查询名称
	 * @apiParam (StockRuleQueryRequestModel) {Integer} [category] 库存规则类别
	 * @apiParam (StockRuleQueryRequestModel) {Integer} [type] 库存规则类型（  1、单一库存 2、每日库存）
	 * @apiParam (StockRuleQueryRequestModel) {Integer} [state] 库存规则状态（  1 正常 2 停用）
	 * @apiParam (StockRuleQueryRequestModel) {Long} [supplierId] 供应商ID
	 * @apiParam (StockRuleQueryRequestModel) {Integer} [whetherReturn] 是否归还库存（ 1、是 2、否）
	 * @apiParam (StockRuleQueryRequestModel) {List} [ruleIds] 库存规则ID集合
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
	public Result<StockRuleModel> queryStockRuleById(StockRuleQueryRequestModel model, ServiceContext context);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockRuleQueryService.queryStockRulesByParam 根据参数集合查询库存规则
	 * @apiName 根据参数集合查询库存规则
	 * @apiGroup 库存接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 根据参数集合查询库存规则
	 *
	 * @apiParam (请求参数) {StockRuleQueryRequestModel} model 查询库存规则请求model
	 * @apiParam (请求参数) {ServiceContext} [serviceContext] 上下文
	 * 
	 * @apiParam (StockRuleQueryRequestModel) {Long} [ruleId] 库存规则ID
	 * @apiParam (StockRuleQueryRequestModel) {String} [name] 库存规则名称 
	 * @apiParam (StockRuleQueryRequestModel) {String} [vagueName] 库存规则模糊查询名称
	 * @apiParam (StockRuleQueryRequestModel) {Integer} [category] 库存规则类别
	 * @apiParam (StockRuleQueryRequestModel) {Integer} [type] 库存规则类型（  1、单一库存 2、每日库存）
	 * @apiParam (StockRuleQueryRequestModel) {Integer} [state] 库存规则状态（  1 正常 2 停用）
	 * @apiParam (StockRuleQueryRequestModel) {Long} [supplierId] 供应商ID
	 * @apiParam (StockRuleQueryRequestModel) {Integer} [whetherReturn] 是否归还库存（ 1、是 2、否）
	 * @apiParam (StockRuleQueryRequestModel) {List} [ruleIds] 库存规则ID集合
	 * 
	 * @apiParamExample {json} 请求示例
	 *   {
	 *       "model" : {
	 *          "name": "0826班车开发测试班车1测试"
	 *       },
	 *       serviceContext:{
	 *           .........
	 *       }
	 *   }
	 *   
	 * 
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {ArrayList} data 返回结果StockRuleModel对象集合;
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
	 *      "data" : [
	 *          {
	 *          "id": 98,
	 *          "name": "0826班车开发测试班车1测试",
	 *          "category": 1,
	 *          "type": 2,
	 *          "upperLimit": 100,
	 *          "state": 1,
	 *          "supplierId": 2216619736763722,
	 *          "whetherReturn": 2
	 *          }
	 *       ]
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
	public Result<ArrayList<StockRuleModel>> queryStockRulesByParam(StockRuleQueryRequestModel model,
			ServiceContext context);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockRuleQueryService.queryStockRulePage 分页查询库存规则
	 * @apiName 分页查询库存规则
	 * @apiGroup 库存接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 分页查询库存规则
	 *
	 * @apiParam (请求参数) {StockRuleQueryRequestModel} model 查询库存规则请求model，继承分页PageableRequestBean
	 * @apiParam (请求参数) {ServiceContext} [serviceContext] 上下文
	 * 
	 * @apiParam (StockRuleQueryRequestModel) {Long} supplierId 供应商ID
	 * @apiParam (StockRuleQueryRequestModel) {Long} [ruleId] 库存规则ID
	 * @apiParam (StockRuleQueryRequestModel) {String} [name] 库存规则名称 
	 * @apiParam (StockRuleQueryRequestModel) {String} [vagueName] 库存规则模糊查询名称
	 * @apiParam (StockRuleQueryRequestModel) {Integer} [category] 库存规则类别
	 * @apiParam (StockRuleQueryRequestModel) {Integer} [type] 库存规则类型（  1、单一库存 2、每日库存）
	 * @apiParam (StockRuleQueryRequestModel) {Integer} [state] 库存规则状态（  1 正常 2 停用）
	 * @apiParam (StockRuleQueryRequestModel) {Integer} [whetherReturn] 是否归还库存（ 1、是 2、否）
	 * @apiParam (StockRuleQueryRequestModel) {List} [ruleIds] 库存规则ID集合
	 * 
	 * @apiParam (PageableRequestBean) {int} [current_page] 当前页码, 默认为1
	 * @apiParam (PageableRequestBean) {int} [page_size] 每页显示的最大数量, 默认为10
	 * 
	 * @apiParamExample {json} 请求示例
	 *   {
	 *       "model" : {
	 *          "supplierId": 2216619741563734
	 *       },
	 *       serviceContext:{
	 *           .........
	 *       }
	 *   }
	 *   
	 * 
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {QueryResult} data 返回结果对象;
	 * 
	 * @apiParam (QueryResult) {int} total 数据总数
	 * @apiParam (QueryResult) {int} current_page 当前页
	 * @apiParam (QueryResult) {int} total_page 总页数
	 * @apiParam (QueryResult) {int} page_size 每页最大记录数
	 * @apiParam (QueryResult) {List} records 查询数据StockRuleModel对象列表
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
	 *      "data" : 
	 *          "total": 508,
	 *          "current_page": 1,
	 *          "total_page": 26,
	 *          "page_size": 20,
	 *          "records": [
	 *              {
	 *              "id": 98,
	 *              "name": "0826班车开发测试班车1测试",
	 *              "category": 1,
	 *              "type": 2,
	 *              "upperLimit": 100,
	 *              "state": 1,
	 *              "supplierId": 2216619736763722,
	 *              "whetherReturn": 2
	 *              },
	 *              {
	 *                  ...
	 *              }
	 *              ...
	 *          ]
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
	public Result<QueryResult<StockRuleModel>> queryStockRulePage(StockRuleQueryRequestModel model,
			ServiceContext context);

	/**
	 * 对库存规则按类别进行分类查询
	 * 
	 * @param request
	 * @param context
	 * @return
	 */
	public Result<StockRuleModel> queryStockRule4Category(StockRuleQueryRequestModel model, ServiceContext context);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockRuleQueryService.queryStockRulesByIds 根据规则ID集合查询库存规则
	 * @apiName 通过库存规则ID集合查询库存规则信息列表
	 * @apiGroup 库存接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 通过库存规则ID集合查询库存规则信息列表
	 *
	 * @apiParam (请求参数) {QueryStockRuleByIdsModel} model 查询库存规则请求model
	 * @apiParam (请求参数) {ServiceContext} [serviceContext] 请求上下文
	 * 
	 * @apiParam (QueryStockRuleByIdsModel) {List} [stockRuleIds] 库存规则ID集合 
	 * @apiParam (QueryStockRuleByIdsModel) {Integer} [state] 库存规则状态（  1 正常 2 停用）
	 * 
	 * @apiParamExample {json} 请求示例
	 *   {
	 *       "model" : {
	 *          "stockRuleIds": [
	 *              98
	 *          ]
	 *       },
	 *       serviceContext:{
	 *           .........
	 *       }
	 *   }
	 *   
	 * 
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {ArrayList} data 返回结果StockRuleModel对象集合;
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
	 *      "data" : [
	 *          {
	 *          "id": 98,
	 *          "name": "0826班车开发测试班车1测试",
	 *          "category": 1,
	 *          "type": 2,
	 *          "upperLimit": 100,
	 *          "state": 1,
	 *          "supplierId": 2216619736763722,
	 *          "whetherReturn": 2
	 *          }
	 *       ]
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
	public Result<ArrayList<StockRuleModel>> queryStockRulesByIds(QueryStockRuleByIdsModel model, ServiceContext context);

}
