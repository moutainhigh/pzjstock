/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.service;

import java.util.ArrayList;

import com.pzj.core.stock.model.CheckStockModel;
import com.pzj.core.stock.model.QueryStockByShowReqModel;
import com.pzj.core.stock.model.StockDateListQueryModel;
import com.pzj.core.stock.model.StockModel;
import com.pzj.core.stock.model.StockQueryRequestModel;
import com.pzj.core.stock.model.StockRealTimeModel;
import com.pzj.core.stock.model.StockRealTimeQueryModel;
import com.pzj.core.stock.model.StockRulesDateReqModel;
import com.pzj.core.stock.model.SupplierLockStockModel;
import com.pzj.core.stock.model.SupplierLockStockQueryModel;
import com.pzj.core.stock.model.UserOccupyStockModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 库存查询服务
 * @author dongchunfu
 * @version $Id: StockQueryService.java, v 0.1 2016年7月22日 下午6:01:38 dongchunfu Exp $
 */
public interface StockQueryService {

	/**
	* @api {dubbo} com.pzj.core.stock.service.StockQueryService.queryStockById 库存按主键查询
	* @apiName 库存按主键查询
	* @apiGroup 库存接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 库存按主键查询
	*
	* @apiParam (请求参数) {StockQueryRequestModel} model 请求查询库存model
	* @apiParam (请求参数) {ServiceContext} context 请求上下文
	*
	* @apiParam (StockQueryRequestModel) {Long} stockId 库存ID
	* @apiParam (StockQueryRequestModel) {Long} [ruleId] 库存规则ID
	* @apiParam (StockQueryRequestModel) {String} [stockTime] 日库存时间
	* @apiParam (StockQueryRequestModel) {Integer} [state] 库存状态
	* @apiParam (StockQueryRequestModel) {String} [beginStockTime] 范围查询开始时间（含）
	* @apiParam (StockQueryRequestModel) {String} [endStockTime] 范围查询结束时间（含）
	* @apiParam (StockQueryRequestModel) {String} [queryMonth] 查询某月（当前日期（含）之后）范围内的所有库存信息
	* @apiParam (StockQueryRequestModel) {List} [stockIds] 库存id集合
	* 
	* @apiParam (StockModel) {Long} id 库存主键id
	* @apiParam (StockModel) {Long} ruleId 库存规则主键id 
	* @apiParam (StockModel) {Integer} state 库存状态（1 正常 2 停用，默认为1） 
	* @apiParam (StockModel) {Integer} stockTime 库存时间 
	* @apiParam (StockModel) {Date} stockDate 库存时间（日期格式）
	* @apiParam (StockModel) {Integer} totalNum 库存总数 
	* @apiParam (StockModel) {Integer} usedNum 已用库存数量
	* @apiParam (StockModel) {Integer} remainNum 剩余库存数 
	* @apiParam (StockModel) {Date} createTime 创建时间
	* @apiParam (StockModel) {Date} updateTime 更新时间 
	* @apiParam (StockModel) {Integer} beginTime 开始时间（含）
	* @apiParam (StockModel) {Integer} endTime 结束时间（含） 
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model" : {
	*			"stockId" : 233
	*		}
	*		context : {
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {StockModel} data 返回结果对象
	* 
	* @apiSuccessExample {json} 成功响应数据
	*  {
	*      "errorCode" : 10000,
	*      "errorMsg" : "ok",
	*      "data" : {
	*         "id": 233,
	*         "ruleId": 154,
	*         "state": 1,
	*         "stockTime": 20160830,
	*         "stockDate": "Aug 30, 2016 12:00:00 AM",
	*         "totalNum": 66,
	*         "usedNum": 3,
	*         "remainNum": 63
	*       }
	*  }
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
	Result<StockModel> queryStockById(StockQueryRequestModel model, ServiceContext context);

	/**
	 * 查询指定日期的库存信息
	 * 
	 * @param stockRuleId 库存规则id
	 * @param request 请求参数
	 * @param serviceContext 服务上下文
	 * @return Result<StockModel> 统一返回结果
	 */
	@Deprecated
	Result<StockModel> queryStockByDate(StockQueryRequestModel model, ServiceContext context);

	/**
	* @api {dubbo} com.pzj.core.stock.service.StockQueryService.queryAllStocks 库存按条件查询
	* @apiName 库存按条件查询
	* @apiGroup 库存接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 查询符合条件(除ID)的所有库存信息
	*
	* @apiParam (请求参数) {StockQueryRequestModel} model 库存查询参数对象
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (StockQueryRequestModel) {Long} ruleId 库存规则ID
	* @apiParam (StockQueryRequestModel) {String} stockTime 日库存时间
	* @apiParam (StockQueryRequestModel) {Long} [stockId] 库存ID
	* @apiParam (StockQueryRequestModel) {Integer} [state] 库存状态
	* @apiParam (StockQueryRequestModel) {String} [beginStockTime] 范围查询开始时间（含）
	* @apiParam (StockQueryRequestModel) {String} [endStockTime] 范围查询结束时间（含）
	* @apiParam (StockQueryRequestModel) {String} [queryMonth] 查询某月（当前日期（含）之后）范围内的所有库存信息
	* @apiParam (StockQueryRequestModel) {List} [stockIds] 库存集合List<Long>
	* 
	*
	* @apiParam (StockModel) {Long} id 库存主键id
	* @apiParam (StockModel) {Long} ruleId 库存规则主键id 
	* @apiParam (StockModel) {Integer} state 库存状态（1 正常 2 停用，默认为1） 
	* @apiParam (StockModel) {Integer} stockTime 库存时间 
	* @apiParam (StockModel) {Date} stockDate 库存时间（日期格式）
	* @apiParam (StockModel) {Integer} totalNum 库存总数 
	* @apiParam (StockModel) {Integer} usedNum 已用库存数量
	* @apiParam (StockModel) {Integer} remainNum 剩余库存数 
	* @apiParam (StockModel) {Date} createTime 创建时间
	* @apiParam (StockModel) {Date} updateTime 更新时间 
	* @apiParam (StockModel) {Integer} beginTime 开始时间（含）
	* @apiParam (StockModel) {Integer} endTime 结束时间（含） 
	*
	* @apiParamExample 请求参数示例
	*	{
	*		"model":{
	*			"id":"233"
	*		}
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {ArrayList} data 返回结果StockModel对象集合
	*  
	* @apiSuccessExample 成功响应结果:
	*	"Result":{
	*			"errorCode" : 10000,
	*			"errorMsg" : "ok",
	*           "data":[
	*               {
	*                   "id": 233,
	*                   "ruleId": 154,
	*                   "state": 1,
	*                   "stockTime": 20160830,
	*                   "stockDate": "Aug 30, 2016 12:00:00 AM",
	*                   "totalNum": 66,
	*                   "usedNum": 3,
	*                   "remainNum": 63
	*               }
	*           ]
	*		}
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
	Result<ArrayList<StockModel>> queryAllStocks(StockQueryRequestModel model, ServiceContext context);

	/**
	* @api {dubbo} com.pzj.core.stock.service.StockQueryService.queryRangeStocks 库存按时间段查询
	* @apiName 库存按时间段查询
	* @apiGroup 库存接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 查询当前日期（含）及之后 时间范围内 的所有库存信息
	*
	* @apiParam (请求参数) {StockQueryRequestModel} model 查询库存请求model
	* @apiParam (请求参数) {ServiceContext} context 请求上下文
	*
	* @apiParam (StockQueryRequestModel) {Long} ruleId 库存规则ID
	* @apiParam (StockQueryRequestModel) {String} beginStockTime 范围查询开始时间（含）
	* @apiParam (StockQueryRequestModel) {String} endStockTime 范围查询结束时间（含）
	* @apiParam (StockQueryRequestModel) {Long} [stockId] 库存ID
	* @apiParam (StockQueryRequestModel) {String} [stockTime] 日库存时间
	* @apiParam (StockQueryRequestModel) {Integer} [state] 库存状态
	* @apiParam (StockQueryRequestModel) {String} [queryMonth] 查询某月（当前日期（含）之后）范围内的所有库存信息
	* @apiParam (StockQueryRequestModel) {List} [stockIds] 库存id集合
	* 
	* 
	* @apiParam (StockModel) {Long} id 库存主键id
	* @apiParam (StockModel) {Long} ruleId 库存规则主键id 
	* @apiParam (StockModel) {Integer} state 库存状态（1 正常 2 停用，默认为1） 
	* @apiParam (StockModel) {Integer} stockTime 库存时间 
	* @apiParam (StockModel) {Date} stockDate 库存时间（日期格式）
	* @apiParam (StockModel) {Integer} totalNum 库存总数 
	* @apiParam (StockModel) {Integer} usedNum 已用库存数量
	* @apiParam (StockModel) {Integer} remainNum 剩余库存数 
	* @apiParam (StockModel) {Date} createTime 创建时间
	* @apiParam (StockModel) {Date} updateTime 更新时间 
	* @apiParam (StockModel) {Integer} beginTime 开始时间（含）
	* @apiParam (StockModel) {Integer} endTime 结束时间（含） 
	* 
	* @apiParamExample {json} 请求示例
	* {
	*   "model":{
	*	  "ruleId": 154,
	*     "beginStockTime": "20160901",
	*     "endStockTime": "20160902"
	*   },
	*	context:{
	*	  ...
	*	}
	* }
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {ArrayList} data 返回结果StockModel对象集合
	*  
	* @apiSuccessExample 成功响应结果:
	*	{
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data": [
	*                {
	*                  "id": 235,
	*                  "ruleId": 154,
	*                  "state": 1,
	*                  "stockTime": 20160901,
	*                  "stockDate": "Sep 1, 2016 12:00:00 AM",
	*                  "totalNum": 66,
	*                  "usedNum": 0,
	*                  "remainNum": 66
	*                },
	*                {
	*                  "id": 236,
	*                  "ruleId": 154,
	*                  "state": 1,
	*                  "stockTime": 20160902,
	*                  "stockDate": "Sep 2, 2016 12:00:00 AM",
	*                  "totalNum": 66,
	*                  "usedNum": 0,
	*                  "remainNum": 66
	*                }
	*           ]
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
	Result<ArrayList<StockModel>> queryRangeStocks(StockQueryRequestModel model, ServiceContext context);

	/**
	* @api {dubbo} com.pzj.core.stock.service.StockQueryService.queryMonthStocks 库存按月查询
	* @apiName 库存按月查询
	* @apiGroup 库存接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 查询某月（当前日期（含）之后）范围内的所有库存信息
	*
	* @apiParam (请求参数) {StockQueryRequestModel} model 按月查询库存请求model
	* @apiParam (请求参数) {ServiceContext} context 请求上下文
	*
	* @apiParam (StockQueryRequestModel) {String} queryMonth 查询某月（当前日期（含）之后）范围内的所有库存信息
	* @apiParam (StockQueryRequestModel) {Long} [stockId] 库存ID
	* @apiParam (StockQueryRequestModel) {Long} [ruleId] 库存规则ID
	* @apiParam (StockQueryRequestModel) {String} [stockTime] 日库存时间
	* @apiParam (StockQueryRequestModel) {Integer} [state] 库存状态
	* @apiParam (StockQueryRequestModel) {String} [beginStockTim] 范围查询开始时间（含）
	* @apiParam (StockQueryRequestModel) {String} [endStockTime] 范围查询结束时间（含）
	* @apiParam (StockQueryRequestModel) {List} [stockIds] 库存集合List<Long>
	* 
	* @apiParam (StockModel) {Long} id 库存主键id
	* @apiParam (StockModel) {Long} ruleId 库存规则主键id 
	* @apiParam (StockModel) {Integer} state 库存状态（1 正常 2 停用，默认为1） 
	* @apiParam (StockModel) {Integer} stockTime 库存时间 
	* @apiParam (StockModel) {Date} stockDate 库存时间（日期格式）
	* @apiParam (StockModel) {Integer} totalNum 库存总数 
	* @apiParam (StockModel) {Integer} usedNum 已用库存数量
	* @apiParam (StockModel) {Integer} remainNum 剩余库存数 
	* @apiParam (StockModel) {Date} createTime 创建时间
	* @apiParam (StockModel) {Date} updateTime 更新时间 
	* @apiParam (StockModel) {Integer} beginTime 开始时间（含）
	* @apiParam (StockModel) {Integer} endTime 结束时间（含） 
	* 
	* @apiParamExample 请求参数示例
	*	{
	*		"model":{
	*			"ruleId": 154,
	*			"queryMonth": "20161115"
	*		}
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {ArrayList} data 返回StockModel结果对象集合
	* 
	* @apiSuccessExample {json} 成功响应数据
	*  {
	*      "errorCode" : 10000,
	*      "errorMsg" : "ok",
	*      "data" : [
	*           {
	*               "id": 306
	*               "ruleId": 154,
	*               "state": 1,
	*               "stockTime": 20161111,
	*               "stockDate": "Nov 11, 2016 12:00:00 AM",
	*               "totalNum":66,
	*               "usedNum":0,
	*               "remainNum":66
	*           }
	*           ...
	*      ]
	*  } 
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
	Result<ArrayList<StockModel>> queryMonthStocks(StockQueryRequestModel model, ServiceContext context);

	/**
	* @api {dubbo} com.pzj.core.stock.service.StockQueryService.judgeStockIsEnough 判断库存是否足够
	* @apiName 判断库存是否足够
	* @apiGroup 库存接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 判断是否可以占用指定数量库存
	*
	* @apiParam (请求参数) {CheckStockModel} checkStockModel 判断库存是否足够请求model
	* @apiParam (请求参数) {ServiceContext} [context] 请求上下文
	*
	* @apiParam (CheckStockModel) {Long} stockRuleId 库存规则ID(库存规则ID和库存ID两个参数必须传入一个)
	* @apiParam (CheckStockModel) {String} stockTime 库存时间 例：20160820
	* @apiParam (CheckStockModel) {Long} stockId 库存ID(库存规则ID和库存ID两个参数必须传入一个)
	* @apiParam (CheckStockModel) {Integer} occupyNum 占用库存数量
	* @apiParam (CheckStockModel) {Integer} [queryType] 查询库存类型 1：通过库存ID 2：通过库存规则ID
	* 
	* @apiParamExample 请求参数示例
	*   {
	*       "model":{
	*           "occupyNum" : 5,
	*           "stockRuleId" : 261,
	*           "stockTime" : "20160906"
	*       }
	*       context:{
	*           .........
	*       }
	*   }
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Boolean} data 返回结果对象；true:可以占用,false:不可占用
	* 
	* @apiSuccessExample {json} 成功响应数据
	*  {
	*      "errorCode" : 10000,
	*      "errorMsg" : "ok",
	*      "data" : true
	*  } 
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
	Result<Boolean> judgeStockIsEnough(CheckStockModel checkStockModel, ServiceContext context);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockQueryService.queryUserBatchOccupyStock 获取用户针对一个库存规则批量占用的库存信息
	 * @apiName 获取用户针对一个库存规则批量占用的库存信息
	 * @apiGroup 库存接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 获取用户针对一个库存规则批量占用的库存信息
	 * 
	 * @apiParam (请求参数) {UserOccupyStockModel} userOccupyStockModel 查询用户批量占用库存信息model
	 * @apiParam (请求参数) {ServiceContext} [context] 请求上下文
	 * 
	 * @apiParam (UserOccupyStockModel) {Long} userId 锁库存用户ID
	 * @apiParam (UserOccupyStockModel) {Long} stockRuleId 库存规则ID
	 * @apiParam (UserOccupyStockModel) {Integer} [totalStockNum] 总库存数
	 * @apiParam (UserOccupyStockModel) {List} [stockModelList] 库存数据StockModel集合
	 * 
	 * @apiExample {json} 请求示例
	 *  {
	 *      "userOccupyStockModel" : {
	 *          "userId": 2216619741564208,
	 *          "stockRuleId": 156
	 *      },
	 *      "context" : {
	 *          ...
	 *      }
	 *  }
	 * 
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {UserOccupyStockModel} data 返回结果对象
	 * 
	 * @apiSuccessExample {json} 成功响应数据
	 *  {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : {
	 *          "userId" : 2216619741564208,
	 *          "stockRuleId" : 156,
	 *          "totalStockNum" : 1220,
	 *          "stockModelList" : [
	 *              {
	 *                  "id": 604,
	 *                   "ruleId": 156,
	 *                   "stockTime": 20160831,
	 *                   "stockDate": "Aug 31, 2016 12:00:00 AM",
	 *                   "totalNum": 1220,
	 *                   "usedNum": 1120,
	 *                   "remainNum": 100
	 *              },
	 *              {
	 *                  "id": 605,
	 *                   "ruleId": 156,
	 *                   "stockTime": 20160901,
	 *                   "stockDate": "Sep 1, 2016 12:00:00 AM",
	 *                   "totalNum": 1220,
	 *                   "usedNum": 1120,
	 *                   "remainNum": 100
	 *              },
	 *              {
	 *                  "id": 606,
	 *                   "ruleId": 156,
	 *                   "stockTime": 20160902,
	 *                   "stockDate": "Sep 2, 2016 12:00:00 AM",
	 *                   "totalNum": 1220,
	 *                   "usedNum": 1120,
	 *                   "remainNum": 100
	 *              }                           
	 *          ]
	 *      }
	 *  }
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
	Result<UserOccupyStockModel> queryUserBatchOccupyStock(UserOccupyStockModel userOccupyStockModel,
			ServiceContext context);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockQueryService.queryStockByDateList 通过多个日期查找库存集合
	 * @apiName 通过多个日期查找库存集合
	 * @apiGroup 库存接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 通过多个日期查找库存集合
	 * 
	 * @apiParam (请求参数) {StockDateListQueryModel} model 根据日期查询库存集合请求model
	 * @apiParam (请求参数) {ServiceContext} [context] 请求上下文
	 * 
	 * @apiParam (StockDateListQueryModel) {Long} stockRuleId 库存规则ID
	 * @apiParam (StockDateListQueryModel) {ArrayList} stockTimeList 库存时间集合
	 * 
	 * @apiExample {json} 请求示例
	 *  {
	 *      "stockRuleId": 154,
	 *      "stockTimeList": [
	 *          "20160831",
	 *          "20160901",
	 *          "20160902"
	 *      ]
	 *  }
	 * 
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {ArrayList} data 返回结果StockModel对象
	 * 
	 * @apiSuccessExample {json} 成功响应数据
	 *  {
	 *      "errorCode": 10000,
	 *      "errorMsg": "ok",
	 *      "data": [
	 *          {
	 *              "id": 234,
	 *              "ruleId": 154,
	 *              "stockTime": 20160831,
	 *              "stockDate": "Aug 31, 2016 12:00:00 AM",
	 *              "totalNum": 66,
	 *              "usedNum": 0,
	 *              "remainNum": 66
	 *          },
	 *          {
	 *              "id": 235,
	 *              "ruleId": 154,
	 *              "stockTime": 20160901,
	 *              "stockDate": "Sep 1, 2016 12:00:00 AM",
	 *              "totalNum": 66,
	 *              "usedNum": 0,
	 *              "remainNum": 66          
	 *          },
	 *          {
	 *              "id": 236,
	 *              "ruleId": 154,
	 *              "stockTime": 20160902,
	 *              "stockDate": "Sep 2, 2016 12:00:00 AM",
	 *              "totalNum": 66,
	 *              "usedNum": 0,
	 *              "remainNum": 66          
	 *          }
	 *      ]
	 *  }
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
	Result<ArrayList<StockModel>> queryStockByDateList(StockDateListQueryModel model, ServiceContext context);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockQueryService.queryStockByRule 根据库存规则查库存
	 * @apiName 根据库存规则查库存
	 * @apiGroup 库存接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 根据库存规则查库存(如果是每日库存则需要传时间)
	 * 
	 * @apiParam (请求参数) {StockQueryRequestModel} model 根据库存规则查询 库存model
	 * @apiParam (请求参数) {ServiceContext} [context] 请求上下文
	 * 
	 * @apiParam (StockQueryRequestModel) {Long} ruleId 库存规则ID
	 * @apiParam (StockQueryRequestModel) {String} [stockTime] 日库存必须传时间,总量库存可不传
	 * @apiParam (StockQueryRequestModel) {Long} [stockId] 库存ID
	 * @apiParam (StockQueryRequestModel) {String} [beginStockTime] 范围查询开始时间（含）
	 * @apiParam (StockQueryRequestModel) {String} [endStockTime] 范围查询结束时间（含）
	 * @apiParam (StockQueryRequestModel) {String} [queryMonth] 查询某月（当前日期（含）之后）范围内的所有库存信息
	 * @apiParam (StockQueryRequestModel) {Integer} [state] 库存状态
	 * @apiParam (StockQueryRequestModel) {List} [stockIds] 库存规则ID集合
	 * 
	 * @apiExample {json} 请求示例
	 *  {
	 *      "model" : {
	 *          "ruleId": 233,
	 *          "stockTime": "20160903"
	 *      },
	 *      "context" : {
	 *          ...
	 *      }
	 *  }
	 * 
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {StockModel} data 返回结果对象
	 * 
	 * @apiSuccessExample {json} 成功响应数据
	 *  {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : {
	 *          "id": 1211,
	 *          "ruleId": 233,
	 *          "stockTime": 20160903,
	 *          "stockDate": "Sep 3, 2016 12:00:00 AM",
	 *          "totalNum": 50,
	 *          "usedNum": 0,
	 *          "remainNum": 50
	 *      }
	 *  }
	 * 
	 * @apiParam (错误码) {int} 15001 参数错误
	 * @apiParam (错误码) {int} 15002 库存服务异常
	 * @apiParam (错误码) {int} 15062 库存查询日期格式不合法
	 * @apiParam (错误码) {int} 15103 找不到相应库存规则数据
	 * 
	 * @apiErrorExample {json} 异常响应数据
	 * {
	 *    "errorCode" : 15001,
	 *    "errorMsg":"参数错误"
	 * } 
	 * 
	 * 
	 */
	Result<StockModel> queryStockByRule(StockQueryRequestModel model, ServiceContext context);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockQueryService.querySupplierBatchOperateStockList 供应端根据日历查询操作锁库存集合
	 * @apiName 供应端根据日历查询操作锁库存集合
	 * @apiGroup 库存接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 供应端根据日历查询操作锁库存集合
	 * 
	 * @apiParam (请求参数) {StockDateListQueryModel} model 请求查询model
	 * @apiParam (请求参数) {ServiceContext} [context] 请求上下文
	 * 
	 * @apiParam (StockDateListQueryModel) {Long} stockRuleId 库存规则ID
	 * @apiParam (StockDateListQueryModel) {ArrayList} stockTimeList 库存时间集合
	 * 
	 * @apiExample {json} 请求示例
	 *  {
	 *      "model" : {
	 *          "stockRuleId": 433,
	 *          "stockTimeList": [
	 *              "20170210",
	 *              "20161130"
	 *          ]
	 *      },
	 *      "context" : {
	 *          ...
	 *      }  
	 *  }
	 *  
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {StockModel} data 返回结果对象
	 * 
	 * @apiSuccessExample {json} 成功响应数据
	 *  {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : {
	 *      }
	 *  }
	 * 
	 * @apiParam (错误码) {int} 15001 参数错误
	 * @apiParam (错误码) {int} 15002 库存服务异常
	 * @apiParam (错误码) {int} 15103 找不到相应库存规则数据
	 * 
	 * @apiErrorExample {json} 异常响应数据
	 * {
	 *    "errorCode" : 15001,
	 *    "errorMsg":"参数错误"
	 * }  
	 * 
	 *            
	 * @apiExample 调用外部接口
	 *  系统：产品服务
	 *  接口：com.pzj.core.stock.service.ISkuStockService.findStockSkuByStockRuleIds
	 */
	Result<ArrayList<StockModel>> querySupplierBatchOperateStockList(StockDateListQueryModel model,
			ServiceContext context);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockQueryService.querySupplierRealTimeStockList 供应商查询产品实时库存列表
	 * @apiName 查询产品可用日期对应的实时库存列表
	 * @apiGroup 库存接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 查询产品可用日期对应的实时库存列表
	 * 
	 * @apiParam (请求参数) {StockRealTimeQueryModel} model 查询实时库存请求model
	 * @apiParam (请求参数) {ServiceContext} [context] 请求上下文
	 * 
	 * @apiParam (StockRealTimeQueryModel) {Long} supplierId 供应商ID
	 * @apiParam (StockRealTimeQueryModel) {Date} stockDate 库存时间
	 * @apiParam (StockRealTimeQueryModel) {List} stockRuleIdList 库存规则ID集合
	 * 
	 * @apiExample {json} 请求示例
	 *  {
	 *      "model" : {
	 *          "supplierId": 2216619741563734,
	 *          "stockDate": "Sep 30, 2016 12:00:00 AM",
	 *          "stockRuleIdList": [
	 *              302
	 *          ]
	 *      },
	 *      "context" : {
	 *          ...
	 *      }  
	 *  }
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {StockModel} data 返回结果对象
	 * 
	 * @apiSuccessExample {json} 成功响应数据
	 *  {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : [
	 *          {
	 *             "stockId": 2671,
	 *             "stockRuleId": 302,
	 *             "stockRuleName": "923dxTest库-1436",
	 *             "stockRuleType": 2,
	 *             "stockDate": "Sep 30, 2016 12:00:00 AM",
	 *             "totalStockNum": 6,
	 *             "remainStockNum": 3,
	 *             "userLockStockNum": 0
	 *           }
	 *      ]
	 *  }
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
	public Result<ArrayList<StockRealTimeModel>> querySupplierRealTimeStockList(StockRealTimeQueryModel model,
			ServiceContext context);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockQueryService.querySupplierLockStock 查询供应商锁定的库存
	 * @apiName 查询供应商锁定的库存
	 * @apiGroup 库存接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 查询供应商实时锁定库存数量
	 * 
	 * @apiParam (请求参数) {SupplierLockStockQueryModel} model 查询供应商实时锁定库存数量model
	 * @apiParam (请求参数) {ServiceContext} [context] 请求上下文
	 * 
	 * @apiParam (SupplierLockStockQueryModel) {Long} stockId 库存ID
	 * @apiParam (SupplierLockStockQueryModel) {Long} supplierId 供应商ID
	 * 
	 * @apiExample {json} 请求示例
	 *  {
	 *      "model" : {
	 *          "stockId": 233,
	 *          "supplierId": 3
	 *      },
	 *      "context" : {
	 *          ...
	 *      }   
	 *  }
	 *  
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {SupplierLockStockModel} data 返回结果对象
	 * 
	 * @apiSuccessExample {json} 成功响应数据
	 *  {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : {
	 *          "stockId": 233,
	 *          "userLockNum": 1
	 *      }
	 *  }
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
	Result<SupplierLockStockModel> querySupplierLockStock(SupplierLockStockQueryModel model, ServiceContext context);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockQueryService.queryStockByShow 根据演艺演出信息查找库存对象
	 * @apiName 根据演艺演出信息查找库存对象
	 * @apiGroup 库存接口
	 * @apiVersion 1.2.0-SNAPSHOT
	 * @apiDescription 根据演艺演出信息查找库存对象
	 * 
	 * @apiParam (请求参数) {QueryStockByShowReqModel} model 查询演艺库存信息
	 * @apiParam (请求参数) {ServiceContext} [context] 请求上下文
	 * 
	 * @apiParam (QueryStockByShowReqModel) {Long} screeingId 场次ID
	 * @apiParam (QueryStockByShowReqModel) {Long} areaId 区域ID
	 * @apiParam (QueryStockByShowReqModel) {Date} showTime 演出时间
	 * 
	 * @apiExample {json} 请求示例
	 *  {
	 *      "model" : {
	 *          "screeingId": 233,
	 *          "areaId": 3,
	 *          "showTime":"2017-03-17"
	 *      },
	 *      "context" : {
	 *          ...
	 *      }   
	 *  }
	 *  
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {StockModel} data 返回库存结果对象
	 * 
	 * @apiParam (StockModel) {Long} id 库存主键id
	 * @apiParam (StockModel) {Long} ruleId 库存规则主键id 
	 * @apiParam (StockModel) {Integer} state 库存状态（1 正常 2 停用，默认为1） 
	 * @apiParam (StockModel) {Integer} stockTime 库存时间 
	 * @apiParam (StockModel) {Date} stockDate 库存时间（日期格式）
	 * @apiParam (StockModel) {Integer} totalNum 库存总数 
	 * @apiParam (StockModel) {Integer} usedNum 已用库存数量
	 * @apiParam (StockModel) {Integer} remainNum 剩余库存数 
	 * @apiParam (StockModel) {Date} [createTime] 创建时间
	 * @apiParam (StockModel) {Date} [updateTime] 更新时间 
	 * @apiParam (StockModel) {Integer} [beginTime] 开始时间（含）
	 * @apiParam (StockModel) {Integer} [endTime] 结束时间（含）  
	 * 
	 * @apiSuccessExample {json} 成功响应数据
	 *  {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : {
	 *             "stockId": 2671,
	 *             "stockRuleId": 302,
	 *             "stockRuleName": "923dxTest库-1436",
	 *             "stockRuleType": 2,
	 *             "stockDate": "Sep 30, 2016 12:00:00 AM",
	 *             "totalStockNum": 6,
	 *             "remainStockNum": 3,
	 *             "userLockStockNum": 0
	 *      }
	 *  }
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
	Result<StockModel> queryStockByShow(QueryStockByShowReqModel reqModel, ServiceContext context);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockQueryService.queryStockByScreeing 根据场次查找库存对象集合
	 * @apiName 根据场次查找库存对象集合
	 * @apiGroup 库存接口
	 * @apiVersion 1.2.0-SNAPSHOT
	 * @apiDescription 根据场次查找库存对象集合
	 * 
	 * @apiParam (请求参数) {QueryStockByScreeingReqModel} model 查询演艺库存信息
	 * @apiParam (请求参数) {ServiceContext} [context] 请求上下文
	 * 
	 * @apiParam (QueryStockByScreeingReqModel) {List} screeings 场次ID集合
	 * 
	 * @apiExample {json} 请求示例
	 *  {
	 *      "model" : [
	 *      	12,
	 *      	23
	 *      ],
	 *      "context" : {
	 *          ...
	 *      }   
	 *  }
	 *  
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {ArrayList} data 返回库存结果集合对象
	 * 
	 * @apiParam (StockModel) {Long} id 库存主键id
	 * @apiParam (StockModel) {Long} ruleId 库存规则主键id 
	 * @apiParam (StockModel) {Integer} state 库存状态（1 正常 2 停用，默认为1） 
	 * @apiParam (StockModel) {Integer} stockTime 库存时间 
	 * @apiParam (StockModel) {Date} stockDate 库存时间（日期格式）
	 * @apiParam (StockModel) {Integer} totalNum 库存总数 
	 * @apiParam (StockModel) {Integer} usedNum 已用库存数量
	 * @apiParam (StockModel) {Integer} remainNum 剩余库存数 
	 * @apiParam (StockModel) {Date} [createTime] 创建时间
	 * @apiParam (StockModel) {Date} [updateTime] 更新时间 
	 * @apiParam (StockModel) {Integer} [beginTime] 开始时间（含）
	 * @apiParam (StockModel) {Integer} [endTime] 结束时间（含）  
	 * 
	 * @apiSuccessExample {json} 成功响应数据
	 *  {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : [{
	 *             "stockId": 2671,
	 *             "stockRuleId": 302,
	 *             "stockRuleName": "923dxTest库-1436",
	 *             "stockRuleType": 2,
	 *             "stockDate": "Sep 30, 2016 12:00:00 AM",
	 *             "totalStockNum": 6,
	 *             "remainStockNum": 3,
	 *             "userLockStockNum": 0
	 *      }]
	 *  }
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
	//	Result<ArrayList<StockModel>> queryStockByScreeing(QueryStockByScreeingReqModel reqModel, ServiceContext context);

	Result<ArrayList<StockModel>> queryStockByRulesDate(StockRulesDateReqModel reqModel, ServiceContext context);
}
