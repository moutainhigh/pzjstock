/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.service;

import com.pzj.core.stock.context.UserSeatResponse;
import com.pzj.core.stock.model.CheckSeatsOptionalModel;
import com.pzj.core.stock.model.SeatModel;
import com.pzj.core.stock.model.SeatsOptionalResponse;
import com.pzj.core.stock.model.UserSeatModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author Administrator
 * @version $Id: SeatService.java, v 0.1 2016年8月4日 下午5:11:18 Administrator Exp $
 */
public interface SeatService {
	/**
	 * @api {dubbo} com.pzj.core.stock.service.SeatService.createSeat 生成座位
	 * @apiName 生成座位
	 * @apiGroup 演艺座位接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 生成座位
	 * 
	 * @apiParam (请求参数) {SeatModel} seatModel 生成座位model
	 * @apiParam (请求参数) {ServiceContext} [context] 上下文
	 * 
	 * @apiParam (SeatModel) {Long} seatChartId 座位图主键id
	 * 
	 * @apiParamExample {json} 请求示例：
	 * {
	 *      "seatModel" : {
	 *          "seatChartId" : 1
	 *      },
	 *      "context" : {
	 *          ....
	 *      }
	 * }
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
	 * @apiParam (错误码) {int} 15151 录入座位图数据有误
	 * 
	 * @apiErrorExample {json} 异常响应数据
	 * {
	 *    "errorCode" : 15001,
	 *    "errorMsg":"参数错误"
	 * } 
	 * 
	 * @apiExample 影响数据库数据
	 *  数据库：stock
	 *  stock_seat表添加的字段：id, seat_chart_id, seat_num, create_time
	 *  
	 */
	public Result<Boolean> createSeat(SeatModel seatModel, ServiceContext context);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.SeatService.queryAlreadyOccupySeat 查找已被占用的座位
	 * @apiName 查找已被占用的座位
	 * @apiGroup 演艺座位接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 查找已被占用的座位
	 * 
	 * @apiParam (请求参数) {UserSeatModel} userSeatModel 获取已占用座位请求model
	 * @apiParam (请求参数) {ServiceContext} [context] 上下文
	 * 
	 * @apiParam (UserSeatModel) {Long} stockId 库存ID
	 * @apiParam (UserSeatModel) {Long} operateUserId 占座用户ID
	 * @apiParam (UserSeatModel) {Long} screeingId 场次ID
	 * @apiParam (UserSeatModel) {Long} areaId 区域ID
	 * 
	 * @apiParamExample {json} 请求示例
	 *  {
	 *       "userSeatModel" : {
	 *           "stockId" : 1
	 *       },
	 *       "context" : {
	 *           ...
	 *       }
	 *  } 
	 * 
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {UserSeatResponse} data 返回结果对象
	 * 
	 * @apiParam (UserSeatResponse) {List} occupySeat 已经被占用的座位集合
	 * 
	 * @apiSuccessExample {json} 成功响应数据
	 * {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : {
	 *          "occupySeat" : [
	 *              "VIPA6_20",
	 *              "VIPA7_20"
	 *          ]
	 *      }
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
	public Result<UserSeatResponse> queryAlreadyOccupySeat(UserSeatModel userSeatModel, ServiceContext context);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.SeatService.judgeSeatWheatherOptional 判断座位是否可以下单
	 * @apiName 判断座位是否可以下单
	 * @apiGroup 演艺座位接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * 
	 * @apiParam (请求参数) {CheckSeatsOptionalModel} model 查询请求model
	 * @apiParam (请求参数) {ServiceContext} [context] 请求上下文
	 * 
	 * @apiParam (CheckSeatsOptionalModel) {Long} stockId 库存ID
	 * @apiParam (CheckSeatsOptionalModel) {List} seats 座位号集合
	 * @apiParam (CheckSeatsOptionalModel) {Long} [operateUserId] 占座用户ID
	 * 
	 * @apiParamExample {json} 请求示例
	 *  {
	 *      "model" : {
	 *          "stockId" : 1393,
	 *          "seats" : [
	 *              "VIPA7_201",
	 *              "VIPA7_189"
	 *          ]
	 *      },
	 *      "context" : {
	 *          ...
	 *      }
	 *  }
	 * 
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {SeatsOptionalResponse} data 返回结果对象
	 * 
	 * @apiParam (SeatsOptionalResponse) {Boolean} flag 座位是否可选返回标识,可选：true；不可选：false
	 * @apiParam (SeatsOptionalResponse) {List} notOptionalSeats 不可选座位集合
	 * 
	 * 
	 * @apiSuccessExample {Result} 成功响应结果
	 * {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : {
	 *          "flag" : "false",
	 *          "notOptionalSeats" : [
	 *              "A1_2"
	 *          ]
	 *      }
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
	public Result<SeatsOptionalResponse> judgeSeatWheatherOptional(CheckSeatsOptionalModel model, ServiceContext context);
}
