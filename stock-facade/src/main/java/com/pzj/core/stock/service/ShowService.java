package com.pzj.core.stock.service;

import com.pzj.core.stock.context.ShowResponse;
import com.pzj.core.stock.model.ShowModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

public interface ShowService {
	/**
	 * @api {dubbo} com.pzj.core.stock.service.ShowService.occupySeat 占座接口
	 * @apiName 占座
	 * @apiGroup 演艺座位接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 占座接口
	 *
	 * @apiParam (请求参数) {ShowModel} showModel 占座model
	 * @apiParam (请求参数) {ServiceContext} [serviceContext] 请求上下文
	 *
	 * @apiParam (ShowModel) {List} seats 占用座位字符串集合
	 * @apiParam (ShowModel) {Long} stockId 库存id
	 * @apiParam (ShowModel) {String} transactionId 交易id
	 * @apiParam (ShowModel) {Long} areaScreeingsId 产品上的演艺ID
	 * @apiParam (ShowModel) {Integer} operateBusiness 操作座位枚举，OperateSeatBussinessType
	 * @apiParam (ShowModel) {Long} [productId] 产品ID，操作座位枚举为下单占座时必传产品ID
	 * @apiParam (ShowModel) {Long} [userId] 用户ID，操作座位枚举为预约占座时必传用户ID
	 * @apiParam (ShowModel) {Integer} [randomNum] 动态分配座位数
	 * 
	 * @apiParam (OperateSeatBussinessType) {int} key 操作座位业务枚举标识值
	 * @apiParam (OperateSeatBussinessType) {String} value 操作座位对应描述; 1："下单占座",2："预约占座"
	 * 
	 * @apiParamExample {json} 请求参数示例
	 *   {
	 *       "showModel":
	 *       {
	 *          "operateBusiness": 1,
	 *          "transactionId": "abc",
	 *          "productId": 2216619741564407,
	 *          "seats": [
	 *              "A20_26"
	 *          ],
	 *          "stockId": 257,
	 *          "areaScreeingsId": 16
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
	 *	{
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *	    "data" : true
	 *	}
	 * 
	 * @apiParam (错误码) {int} 15001 参数错误
	 * @apiParam (错误码) {int} 15002 库存服务异常
	 * @apiParam (错误码) {int} 15115 业务重复占座
	 * @apiParam (错误码) {int} 15055  找不到相应库存数据
	 * @apiParam (错误码) {int} 15050 库存状态不可用
	 * @apiParam (错误码) {int} 15059 库存已经过期
	 * 
	 * @apiErrorExample {json} 异常响应数据
	 * {
	 *    "errorCode" : 15001,
	 *    "errorMsg":"参数错误"
	 * }
	 *     
	 * @apiExample 影响数据库数据
	 *  数据库：stock
	 *  stock_seat_rel表新增字段：id, stock_id, state, seat_num, transaction_id, product_id, operate_user_id, create_time  
	 *     
	 */
	public Result<Boolean> occupySeat(ShowModel showModel, ServiceContext serviceContext);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.ShowService.releaseSeat 释放座位
	 * @apiName 释放座
	 * @apiGroup 演艺座位接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 释放座位
	 *
	 * @apiParam (请求参数) {ShowModel} showModel 释放座位请求model
	 * @apiParam (请求参数) {ServiceContext} [serviceContext] 请求上下文
	 *
	 * @apiParam (ShowModel) {List} seats 释放座位字符串集合
	 * @apiParam (ShowModel) {Long} stockId 库存id
	 * @apiParam (ShowModel) {String} transactionId 交易id
	 * @apiParam (ShowModel) {Long} areaScreeingsId 产品上的演艺ID
	 * @apiParam (ShowModel) {Integer} operateBusiness 操作座位枚举，OperateSeatBussinessType
	 * @apiParam (ShowModel) {Long} [productId] 产品ID，操作座位枚举为下单占座时必传产品ID
	 * @apiParam (ShowModel) {Long} [userId] 用户ID，操作座位枚举为预约占座时必传用户ID
	 * @apiParam (ShowModel) {Integer} [randomNum] 动态分配座位数
	 * 
	 * @apiParam (OperateSeatBussinessType) {int} key 操作座位业务枚举标识值
	 * @apiParam (OperateSeatBussinessType) {String} value 操作座位对应描述; 4 : "退票释放座位",5 : "预约释放座位"
	 *  
	 * @apiParamExample {json} 请求参数示例
	 *   {
	 *       "showModel":
	 *       {
	 *          "operateBusiness": 3,
	 *          "transactionId": "abc",
	 *          "productId": 2216619741564407,
	 *          "seats": [
	 *              "A20_26"
	 *          ],
	 *          "stockId": 257,
	 *          "areaScreeingsId": 16
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
	 *	{
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *	    "data" : true
	 *	}
	 * 
	 * @apiParam (错误码) {int} 15001 参数错误
	 * @apiParam (错误码) {int} 15002 库存服务异常
	 * @apiParam (错误码) {int} 15153 没找到释放的座位
	 * @apiParam (错误码) {int} 15157  不能释放座位列表：A1_19
	 * 
	 * @apiErrorExample {json} 异常响应数据
	 * {
	 *    "errorCode" : 15001,
	 *    "errorMsg":"参数错误"
	 * }  
	 * 
	 * @apiExample 影响数据库数据
	 *  数据库：stock
	 *  stock_seat_rel表更新字段：state, update_time 
	 *   
	 */
	public Result<Boolean> releaseSeat(ShowModel showModel, ServiceContext serviceContext);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.ShowService.randomAssignSeat 动态分配座位
	 * @apiName 动态分配座位
	 * @apiGroup 演艺座位接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 动态分配座位
	 *
	 * @apiParam (请求参数) {ShowModel} showModel 随机分配座位请求model
	 * @apiParam (请求参数) {ServiceContext} [serviceContext] 请求上下文
	 *
	 * @apiParam (ShowModel) {Integer} randomNum 动态分配座位数
	 * @apiParam (ShowModel) {Long} stockId 库存id
	 * @apiParam (ShowModel) {Long} areaScreeingsId 产品上的演艺ID
	 * @apiParam (ShowModel) {String} [transactionId] 交易id
	 * @apiParam (ShowModel) {List} [seats] 占用座位字符串集合
	 * @apiParam (ShowModel) {Integer} [operateBusiness] 操作座位枚举，OperateSeatBussinessType
	 * @apiParam (ShowModel) {Long} [productId] 产品ID
	 * @apiParam (ShowModel) {Long} [userId] 用户ID
	 * 
	 * @apiParam (ShowResponse) {String[]} randomAssignSeats 动态分配的座位数组
	 * 
	 * @apiParamExample {json} 请求参数示例
	 *   {
	 *       "showModel":
	 *       {
	 *          "randomNum": 10,
	 *          "stockId": 1393,
	 *          "areaScreeingsId": 1
	 *       },
	 *       serviceContext:{
	 *           .........
	 *       }
	 *   }
	 * 
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {ShowResponse} data 返回结果对象;
	 *
	 * @apiSuccessExample {json} 成功响应数据
	 *   {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : {
	 *          "randomAssignSeats" : [
	 *              "A10_33",
	 *              "A10_32",
	 *              "A10_31",
	 *              "A10_30",
	 *              "A10_29",
	 *              "A10_28",
	 *              "A10_27",
	 *              "A10_26",
	 *              "A10_25",
	 *              "A10_23"
	 *          ]
	 *      }
	 *   }
	 *
	 * @apiParam (错误码) {int} 15001 参数错误
	 * @apiParam (错误码) {int} 15002 库存服务异常
	 * @apiParam (错误码) {int} 15202 找不到对应的区域及场次信息
	 * @apiParam (错误码) {int} 15200 演艺数据状态不可用
	 * @apiParam (错误码) {int} 15155 找不到对应的座位图
	 * @apiParam (错误码) {int} 15154 找不到对应的座位
	 * @apiParam (错误码) {int} 15156 超过可选座位最大数量
	 * 
	 * @apiErrorExample {json} 异常响应数据
	 * {
	 *    "errorCode" : 15001,
	 *    "errorMsg":"参数错误"
	 * }
	 *
	 *     
	 */
	public Result<ShowResponse> randomAssignSeat(ShowModel showModel, ServiceContext serviceContext);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.ShowService.rollbackOccupySeat 回滚占座
	 * @apiName 回滚占座
	 * @apiGroup 演艺座位接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 回滚占座
	 *
	 * @apiParam (请求参数) {ShowModel} showModel 交易回滚占座请求model
	 * @apiParam (请求参数) {ServiceContext} serviceContext 请求上下文
	 *
	 * @apiParam (ShowModel) {String} transactionId 交易id
	 * @apiParam (ShowModel) {List} [seats] 占用座位字符串集合
	 * @apiParam (ShowModel) {Long} [stockId] 库存id
	 * @apiParam (ShowModel) {Long} [areaScreeingsId] 产品上的演艺ID
	 * @apiParam (ShowModel) {Integer} [operateBusiness] 操作座位枚举，OperateSeatBussinessType
	 * @apiParam (ShowModel) {Long} [productId] 产品ID
	 * @apiParam (ShowModel) {Long} [userId] 用户ID
	 * 
	 * @apiParamExample {json} 请求参数示例
	 *   {
	 *       "showModel":
	 *       {
	 *          "transactionId": "ab"
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
	 *	{
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *	    "data" : true
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
	 * 
	 * @apiExample 影响数据库数据
	 *  数据库：stock
	 *  stock_seat_rel表删除占座信息
	 *  
	 */
	public Result<Boolean> rollbackOccupySeat(ShowModel showModel, ServiceContext serviceContext);
}
