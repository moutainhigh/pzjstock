/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.service;

import java.util.ArrayList;

import com.pzj.core.stock.model.CreateStockModel;
import com.pzj.core.stock.model.OccupyStockRequestModel;
import com.pzj.core.stock.model.StockBatchLockModel;
import com.pzj.core.stock.model.SupplierManualLockModel;
import com.pzj.core.stock.model.SupplierManualUnlockModel;
import com.pzj.core.stock.model.UpdateAreaStockReqModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 操作库存服务.
 * <ul>
 * <li>占用库存</li>
 * <li>释放库存</li>
 * </ul>
 * @author yhy
 * @version $Id: StockServiceImpl.java, v 0.1 2016年7月26日 上午10:05:12 Administrator Exp $
 */
public interface StockService {

	/**
	* @api {dubbo} com.pzj.core.stock.service.StockService.occupyStock 占库存接口
	* @apiName 占库存
	* @apiGroup 库存接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 交易下单占用库存接口
	*
	* @apiParam (请求参数) {List} orderStockModelList 交易占库存操作OccupyStockRequestModel对象集合
	* @apiParam (请求参数) {ServiceContext} [serviceContext] 上下文
	*
	* @apiParam (OccupyStockRequestModel) {String} transactionId 交易唯一id
	* @apiParam (OccupyStockRequestModel) {Long} productId 产品id
	* @apiParam (OccupyStockRequestModel) {Long} stockId 库存id
	* @apiParam (OccupyStockRequestModel) {Integer} stockNum 占库存数量
	* @apiParam (OccupyStockRequestModel) {Long} [userId] 操作用户id
	* @apiParam (OccupyStockRequestModel) {Integer} [bussinessType] 操作业务类型
	* @apiParam (OccupyStockRequestModel) {String} [invokeOnlyId] 调用唯一id
	* 
	* @apiParamExample {json} 请求示例
	*   {
	*       "orderStockModelList":
	*       [
	*          {
	*            "transactionId": "ab",
	*            "productId": 1234,
	*            "stockId": 234,
	*            "stockNum": 1
	*          },
	*          {
	*            "transactionId": "ab",
	*            "productId": 123,
	*            "stockId": 235,
	*            "stockNum": 1
	*          }
	*       ],
	*       serviceContext:{
	*           .........
	*       }
	*   }
	*   
	* 
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Boolean} data 返回结果对象;true:操作成功，false:操作失败
	*
	* @apiSuccessExample {json} 成功响应数据
	*	{
	*      "errorCode" : 10000,
	*      "errorMsg" : "ok",
	*	   "data" : true
	*	}
	*
	* @apiParam (错误码) {int} 15001 参数错误
	* @apiParam (错误码) {int} 15002 库存服务异常
	* @apiParam (错误码) {int} 15055 找不到相应库存数据
	* @apiParam (错误码) {int} 15050 库存状态不可用
	* @apiParam (错误码) {int} 15052 锁定库存数不可大于现有库存(锁的数量大于剩余的数量)
	* @apiParam (错误码) {int} 15058 超过库存最大数量(使用的数量加锁的数量超出了总数量)
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
	*  stock表的更新字段：used_num,remain_num,update_time
	*  stock_lock_record表的插入字段：id, stock_id, transaction_id, product_id, biz_type, lock_num, operator_id, create_time
	*  
	*/
	public Result<Boolean> occupyStock(ArrayList<OccupyStockRequestModel> orderStockModelList,
			ServiceContext serviceContext);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockService.releaseStock 释放库存
	 * @apiName 释放库存
	 * @apiGroup 库存接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 释放库存
	 * 
	 * @apiParam (请求参数) {List} requestModelList 交易释放库存OccupyStockRequestModel对象集合
	 * @apiParam (请求参数) {ServiceContext} [serviceContext] 上下文
	 * 
	 * @apiParam (OccupyStockRequestModel) {String} transactionId 交易唯一id
	 * @apiParam (OccupyStockRequestModel) {Long} productId 产品id
	 * @apiParam (OccupyStockRequestModel) {Long} stockId 库存id
	 * @apiParam (OccupyStockRequestModel) {Integer} stockNum 释放库存数量
	 * @apiParam (OccupyStockRequestModel) {Long} [userId] 操作用户id
	 * @apiParam (OccupyStockRequestModel) {Integer} [bussinessType] 操作业务类型
	 * @apiParam (OccupyStockRequestModel) {String} [invokeOnlyId] 调用唯一id 
	 * 
	 * 
	 * @apiParamExample {json} 请求示例
	 *   {
	 *       "requestModelList":
	 *       [
	 *          {
	 *            "transactionId": "ab",
	 *            "productId": 1234,
	 *            "stockId": 234,
	 *            "stockNum": 1
	 *          },
	 *          {
	 *            "transactionId": "ab",
	 *            "productId": 123,
	 *            "stockId": 235,
	 *            "stockNum": 1
	 *          }
	 *       ],
	 *       serviceContext:{
	 *           .........
	 *       }
	 *   }
	 *   
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {Boolean} data 返回结果对象;true:操作成功，false:操作失败
	 * 
	 * @apiSuccessExample {json} 成功响应数据
	 *  {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : true
	 *  }
	 * 
	 * @apiParam (错误码) {int} 15001 参数错误
	 * @apiParam (错误码) {int} 15002 库存服务异常
	 * @apiParam (错误码) {int} 15060 解锁库存数不可大于已锁库存
	 * @apiParam (错误码) {int} 15055 找不到相应库存数据
	 * @apiParam (错误码) {int} 15050 库存状态不可用
	 * @apiParam (错误码) {int} 15103 找不到相应库存规则数据
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
	 *  stock表的更新字段：used_num,remain_num,update_time
	 *  stock_lock_record表更新字段：lock_num, update_time
	 *  
	 */
	public Result<Boolean> releaseStock(ArrayList<OccupyStockRequestModel> requestModelList,
			ServiceContext serviceContext);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockService.stockBatchLock 供应商批量占用或释放库存
	 * @apiName 供应端批量占库存或释放库存接口
	 * @apiGroup 库存接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 供应端批量占库存或释放库存接口
	 * 
	 * @apiParam (请求参数) {StockBatchLockModel} stockBatchLockModel 批量操作库存请求model
	 * @apiParam (请求参数) {ServiceContext} [serviceContext] 上下文 
	 * 
	 * @apiParam (StockBatchLockModel) {Map} operateStockMap 操作库存map<Long, Integer>，库存对应锁定或释放数量
	 * @apiParam (StockBatchLockModel) {Integer} operateType 操作库存类型，对应枚举OperateStockBussinessType
	 * @apiParam (StockBatchLockModel) {Long} userId 操作库存用户id
	 * 
	 * @apiParam (OperateStockBussinessType) {int} key 操作库存业务枚举标识值
	 * @apiParam (OperateStockBussinessType) {String} value 操作库存对应描述; 4:"批量占用库存",5:"批量释放库存"
	 * 
	 * 
	 * @apiParamExample {json} 请求示例
	 *   {
	 *       "stockBatchLockModel":
	 *       {
	 *          "operateStockMap": {
	 *              "1019": 1,
	 *              "1020": 1
	 *          }
	 *          "operateType": 5,
	 *          "userId": 2216619746563732
	 *       },
	 *       serviceContext:{
	 *           .........
	 *       }
	 *   }
	 *   
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {Boolean} data 返回结果对象;true:操作成功，false:操作失败
	 * 
	 * @apiSuccessExample {json} 成功响应数据
	 *  {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : true
	 *  }
	 *  
	 * @apiParam (错误码) {int} 15001 参数错误
	 * @apiParam (错误码) {int} 15002 库存服务异常
	 * @apiParam (错误码) {int} 15056 不存在批量锁库存记录
	 * @apiParam (错误码) {int} 15055 找不到相应库存数据
	 * @apiParam (错误码) {int} 15050 库存状态不可用
	 * @apiParam (错误码) {int} 15052 锁定库存数不可大于现有库存(锁的数量大于剩余的数量)
	 * @apiParam (错误码) {int} 15058 超过库存最大数量(使用的数量加锁的数量超出了总数量)
	 * @apiParam (错误码) {int} 15060 解锁库存数不可大于已锁库存
	 * @apiParam (错误码) {int} 15065 不可操作历史库存数据
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
	 *  stock表的更新字段：used_num,remain_num,update_time
	 *  批量锁定场景：stock_lock_record 表更新字段：lock_num, update_time 或 
	 *  stock_lock_record 插入字段：id, stock_id, transaction_id, product_id, biz_type, lock_num, operator_id, create_time
	 *  
	 *  批量解锁场景：stock_lock_record 表删除数据 或 更新字段：lock_num, update_time  
	 *    
	 */
	public Result<Boolean> stockBatchLock(StockBatchLockModel stockBatchLockModel, ServiceContext serviceContext);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockService.rollbackOccupyStock 回滚占库存数据
	 * @apiName 回滚交易占库存数据
	 * @apiGroup 库存接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 交易失败后回滚占用库存数据
	 * 
	 * @apiParam (请求参数) {OccupyStockRequestModel} orderStockModel 回滚库存请求model
	 * @apiParam (请求参数) {ServiceContext} [serviceContext] 上下文 
	 * 
	 * @apiParam (OccupyStockRequestModel) {String} transactionId 交易ID
	 * @apiParam (OccupyStockRequestModel) {Long} [productId] 产品id
	 * @apiParam (OccupyStockRequestModel) {Long} [stockId] 库存id
	 * @apiParam (OccupyStockRequestModel) {Integer} [stockNum] 占库存数量
	 * @apiParam (OccupyStockRequestModel) {Long} [userId] 操作用户id
	 * @apiParam (OccupyStockRequestModel) {Integer} [bussinessType] 操作业务类型
	 * @apiParam (OccupyStockRequestModel) {String} [invokeOnlyId] 调用唯一id
	 * 
	 * @apiParamExample {json} 请求示例
	 *   {
	 *       "orderStockModel":
	 *       {
	 *          "transactionId": "abab"
	 *       },
	 *       serviceContext:{
	 *           .........
	 *       }
	 *   } 
	 * 
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {Boolean} data 返回结果对象;true:操作成功，false:操作失败
	 * 
	 * @apiSuccessExample {json} 成功响应数据
	 *  {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : true
	 *  }
	 * 
	 * @apiParam (错误码) {int} 15001 参数错误
	 * @apiParam (错误码) {int} 15002 库存服务异常
	 * @apiParam (错误码) {int} 15055 找不到相应库存数据
	 * @apiParam (错误码) {int} 15050 库存状态不可用
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
	 *  stock表的更新字段：used_num,remain_num,update_time
	 *  stock_lock_record表删除相关记录
	 * 
	 */
	public Result<Boolean> rollbackOccupyStock(OccupyStockRequestModel orderStockModel, ServiceContext serviceContext);

	/**
	* @api {dubbo} com.pzj.core.stock.service.StockService.createStock 创建库存
	* @apiName 生成库存
	* @apiGroup 库存接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 创建库存(支持批量创建)
	*
	* @apiParam (请求参数) {CreateStockModel} model 创建库存请求model
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (CreateStockModel) {List} ruleIds 库存规则主键id集合
	* 
	* @apiParamExample {json} 请求示例 
	*   {
	*       "model":
	*       {
	*          "ruleIds": [
	*               1
	*          ]
	*       },
	*       serviceContext:{
	*           .........
	*       }
	*   } 
	*   
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Integer} data 返回插入库存数量
	* 
	* @apiSuccessExample {json} 成功响应数据
	*  {
	*      "errorCode" : 10000,
	*      "errorMsg" : "ok",
	*      "data" : 27
	*  }
	* 
	* @apiParam (错误码) {int} 15001 参数错误
	* @apiParam (错误码) {int} 15002 库存服务异常
	* @apiParam (错误码) {int} 15103 找不到相应库存规则数据
	* @apiParam (错误码) {int} 15050 库存状态不可用
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
	*  stock表的插入字段：id, rule_id, state, stock_time, total_num, remain_num, create_time
	*  
	*/
	Result<Integer> createStock(CreateStockModel model, ServiceContext context);

	/**
	* @api {dubbo} com.pzj.core.stock.service.StockService.supplierManualLockStock 供应商手动锁定可用库存
	* @apiName 供应商手动锁定可用库存
	* @apiGroup 库存接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 供应商手动锁定可用库存
	*
	* @apiParam (请求参数) {SupplierManualLockModel} manualLockModel 锁定库存model
	* @apiParam (请求参数) {ServiceContext} [context] 上下文
	*
	* @apiParam (SupplierManualLockModel) {Long} stockId 锁定库存ID
	* @apiParam (SupplierManualLockModel) {Long} supplierId 操作供应商ID
	* @apiParam (SupplierManualLockModel) {Integer} lockNum 锁定库存数量
	* 
	*
	* @apiParamExample 请求参数示例
	*	{
	*		"manualLockModel":{
	*			"stockId":233,
	*			"supplierId":3,
	*			"lockNum":1
	*		}
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Boolean} data 返回结果对象;true:操作成功，false:操作失败
	* 
	* @apiSuccessExample {json} 成功响应数据
	*  {
	*      "errorCode" : 10000,
	*      "errorMsg" : "ok",
	*      "data" : true
	*  }
	* 
	* @apiParam (错误码) {int} 15001 参数错误
	* @apiParam (错误码) {int} 15002 库存服务异常
	* @apiParam (错误码) {int} 15055 找不到相应库存数据
	* @apiParam (错误码) {int} 15050 库存状态不可用
	* @apiParam (错误码) {int} 15057 锁库存数量超出库存数量
	* @apiParam (错误码) {int} 15064 锁库存数量必须大于已锁库存数量
	* @apiParam (错误码) {int} 15065 不可操作历史库存数据
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
	*  stock表的更新字段：used_num,remain_num,update_time
	*  stock_lock_record表更新字段：lock_num,update_time 或  
	*  插入字段：id, stock_id, transaction_id, product_id, biz_type, lock_num, operator_id, create_time
	*
	*/
	public Result<Boolean> supplierManualLockStock(SupplierManualLockModel manualLockModel,
			ServiceContext serviceContext);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockService.supplierManualUnlockStock 供应商手动释放锁定库存
	 * @apiName 供应商手动释放锁定库存
	 * @apiGroup 库存接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 供应商手动释放锁定库存
	 *
	 * @apiParam (请求参数) {SupplierManualUnlockModel} manualUnlockModel 释放库存model
	 * @apiParam (请求参数) {ServiceContext} [context] 请求上下文
	 *
	 * @apiParam (SupplierManualLockModel) {Long} stockId 释放库存ID
	 * @apiParam (SupplierManualLockModel) {Long} supplierId 操作供应商ID
	 * @apiParam (SupplierManualLockModel) {Integer} unlockNum 释放库存数量
	 * 
	 *
	 * @apiParamExample 请求参数示例
	 *   {
	 *       "manualUnlockModel":{
	 *           "stockId":233,
	 *           "supplierId":3,
	 *           "unlockNum":1
	 *       }
	 *       context:{
	 *           .........
	 *       }
	 *   }
	 *
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {Boolean} data 返回结果对象;true:操作成功，false:操作失败
	 * 
	 * @apiSuccessExample {json} 成功响应数据
	 *  {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : true
	 *  }
	 *
	 * @apiParam (错误码) {int} 15001 参数错误
	 * @apiParam (错误码) {int} 15002 库存服务异常
	 * @apiParam (错误码) {int} 15055 找不到相应库存数据
	 * @apiParam (错误码) {int} 15050 库存状态不可用
	 * @apiParam (错误码) {int} 15060 解锁库存数不可大于已锁库存
	 * @apiParam (错误码) {int} 15054 找不到相应库存操作记录
	 * @apiParam (错误码) {int} 15065 不可操作历史库存数据
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
	 *  stock表的更新字段：used_num,remain_num,update_time
	 *  stock_lock_record表更新字段：lock_num,update_time  
	 *  
	 */
	public Result<Boolean> supplierManualUnlockStock(SupplierManualUnlockModel manualUnlockModel,
			ServiceContext serviceContext);

	/**
	 * 更新区域库存数量
	 * @param reqModel
	 * @param serviceContext
	 * @return
	 */
	/**
	 * @api {dubbo} com.pzj.core.stock.service.StockService.updateAreaStock 更新区域库存数量
	 * @apiName 更新区域库存数量
	 * @apiGroup SAAS&ERP 库存
	 * @apiVersion 1.2.0-SNAPSHOT
	 * @apiDescription 更新区域库存数量
	 *
	 * @apiParam (请求参数) {UpdateAreaStockReqModel} reqModel 更新库存请求model
	 * @apiParam (请求参数) {ServiceContext} [context] 请求上下文
	 *
	 * @apiParam (UpdateAreaStockReqModel) {Long} areaId 区域id
	 * @apiParam (UpdateAreaStockReqModel) {Long} screeingId 场次id
	 * @apiParam (UpdateAreaStockReqModel) {Date} showTime 演出时间
	 * @apiParam (UpdateAreaStockReqModel) {Integer} newestStockNum 最新库存数
	 *
	 * @apiParamExample 请求参数示例
	 *   {
	 *       "manualUnlockModel":{
	 *           "areaId":233,
	 *           "screeingId":3,
	 *           "showTime":"2017-03-14",
	 *           "newestStockNum":23
	 *       }
	 *       context:{
	 *           .........
	 *       }
	 *   }
	 *
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {Boolean} data 返回结果对象;true:操作成功，false:操作失败
	 * 
	 * @apiSuccessExample {json} 成功响应数据
	 *  {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : true
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
	 * @apiExample 影响数据库数据
	 *  数据库：stock
	 *  stock表的更新字段：used_num,remain_num,update_time
	 *  stock_lock_record表更新字段：lock_num,update_time  
	 *  
	 */
	public Result<Boolean> updateAreaStock(UpdateAreaStockReqModel reqModel, ServiceContext serviceContext);

}
