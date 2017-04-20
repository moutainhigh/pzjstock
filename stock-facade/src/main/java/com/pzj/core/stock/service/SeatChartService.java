/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.service;

import java.util.ArrayList;

import com.pzj.core.stock.model.SeatChartModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author Administrator
 * @version $Id: SeatChartService.java, v 0.1 2016年8月11日 上午10:50:40 Administrator Exp $
 */
public interface SeatChartService {
	/**
	 * @api {dubbo} com.pzj.core.stock.service.SeatChartService.addSeatChart 添加座位图
	 * @apiName 添加座位图
	 * @apiGroup 演艺座位接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 添加座位图dubbo接口
	 * 
	 * @apiParam (请求参数) {SeatChartModel} seatChartModel 生成座位图model
	 * @apiParam (请求参数) {ServiceContext} [context] 请求上下文
	 * 
	 * @apiParam (SeatChartModel) Long scenicId 景区主键id
	 * @apiParam (SeatChartModel) Long areaId 区域id
	 * @apiParam (SeatChartModel) String seatMaps 座位图
	 * @apiParam (SeatChartModel) Integer sort 排序,默认是1，如果一个区域对应多个图必须设置不同的排序号
	 * @apiParam (SeatChartModel) Long [areaScreeningsId] 区域场次ID
	 * @apiParam (SeatChartModel) Integer [totalSeats] 座位总数
	 * @apiParam (SeatChartModel) String [code] 区域描述
	 * 
	 * @apiParamExample {json} 请求示例
	 * {
	 *      "seatChartModel" : {
	 *          "scenicId" : 2216619741563800,
	 *          "areaId" : 4,
	 *          "seatMaps" : "VIPA6,_,_,_,_,_,_,20,18,16,14,12,10,8,6,4,2,1,3,5,7,9,11,13,15,17,_;",
	 *          "sort" : 1
	 *      },
	 *      "context" : {
	 *          ...
	 *      }
	 * }
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
	 * @apiExample 影响数据库数据
	 *  数据库：stock
	 *  stock_seat_chart表插入的字段：id, scenic_id, prod_area_id, total_seats, seat_maps, sort, code, create_time
	 *  
	 */
	public Result<Boolean> addSeatChart(SeatChartModel seatChartModel, ServiceContext context);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.SeatChartService.querySeatChartByScenicAndAreaRel 根据景区和区域查询座位图
	 * @apiName 根据景区和区域查询座位图
	 * @apiGroup 演艺座位接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 根据景区和区域查询座位图dubbo接口
	 * 
	 * @apiParam (请求参数) {SeatChartModel} seatChartModel 查询座位图请求model
	 * @apiParam (请求参数) {ServiceContext} [context] 请求上下文
	 * 
	 * @apiParam (SeatChartModel) {Long} areaScreeningsId 产品关联的区域场次ID
	 * @apiParam (SeatChartModel) {Long} [scenicId] 景区主键id
	 * @apiParam (SeatChartModel) {Long} [areaId] 区域id
	 * @apiParam (SeatChartModel) {String} [seatMaps] 座位图
	 * @apiParam (SeatChartModel) {Integer} [sort] 排序,默认是1，如果一个区域对应多个图必须设置不同的排序号
	 * @apiParam (SeatChartModel) {Integer} [totalSeats] 座位总数
	 * @apiParam (SeatChartModel) {String} [code] 区域描述
	 * 
	 * @apiParamExample {json} 请求示例
	 * {
	 *      "seatChartModel" : {
	 *          "areaScreeningsId": 1
	 *      },
	 *      "context" : {
	 *          ...
	 *      }
	 * }
	 * 
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {ArrayList} data 返回结果对象SeatChartModel封装的ArrayList
	 * 
	 * 
	 * @apiSuccessExample {Result} 成功响应数据
	 *  {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : {  
	 *          "scenicId": 2216619741563801,
	 *          "areaId": 1,
	 *          "seatMaps": "A2,_,_,_,24,22,20,18,16,_,_,_,_,_,17,19,21,23,25,_;A3,_,_,_,26,24,22,20,18,_,_,_,_,_,17,19,21,23,25,_;
	 *              A4,_,_,_,26,24,22,20,18,_,_,_,_,_,19,21,23,25,27,_;A5,_,_,_,28,26,24,22,20,_,_,_,_,_,19,21,23,25,27,_;
	 *              A6,_,_,32,30,28,26,24,22,_,_,_,_,_,19,21,23,25,27,29,_;A7,_,_,32,30,28,26,24,22,_,_,_,_,_,21,23,25,27,29,31,_;
	 *              A8,_,_,32,30,28,26,24,22,_,_,_,_,_,23,25,27,29,31,33,_;A9,_,_,34,32,30,28,26,24,_,_,_,_,_,23,25,27,29,31,33,_;
	 *              A10,38,36,34,32,30,28,26,_,_,_,_,_,23,25,27,29,31,33,35,_;A11,38,36,34,32,30,28,26,_,_,_,_,_,25,27,29,31,33,35,37,_;
	 *              A12,38,36,34,32,30,28,26,_,_,_,_,_,23,25,27,29,31,33,35,_;_,_,_,_,_,_,_,_,_,_,_,_,_,_;
	 *              A13,_,36,34,32,30,28,26,_,_,_,_,_,25,27,29,31,33,35,_;A14,_,_,34,32,30,28,26,_,_,_,_,_,23,25,27,29,31,_;
	 *              A15,_,_,_,30,28,26,24,_,_,_,_,_,23,25,27,29,_;A16,_,_,_,32,30,28,26,_,_,_,_,_,23,25,27,29,_;
	 *              A17,_,_,_,30,28,26,24,_,_,_,_,_,23,25,27,29,_;A18,_,_,32,30,28,26,24,_,_,_,_,_,21,23,25,27,29,_;
	 *              A19,_,_,30,28,26,24,22,_,_,_,_,_,21,23,25,27,29,_;A20,_,_,_,26,24,22,20,_,_,_,_,_,17,19,21,23,_;
	 *              A21,_,_,_,_,_,30,28,_,_,_,_,_,25,27,_",
	 *          "totalSeats": 208,
	 *          "sort": 1
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
	 */
	public Result<ArrayList<SeatChartModel>> querySeatChartByScenicAndAreaRel(SeatChartModel seatChartModel,
			ServiceContext context);

	/**
	 * @api {dubbo} com.pzj.core.stock.service.SeatChartService.addSeatChartInitSeat 添加座位图并初始化座位数据
	 * @apiName 添加座位图并初始化座位数据
	 * @apiGroup 演艺座位接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 添加座位图并初始化座位数据
	 * 
	 * @apiParam (请求参数) {SeatChartModel} seatChartModel 添加座位图model
	 * @apiParam (请求参数) {ServiceContext} [context] 请求上下文
	 * 
	 * @apiExample {json} 请求示例
	 * {
	 *      "seatChartModel" : {
	 *          "scenicId": 2216619741563800,
	 *          "areaId": 4,
	 *          "seatMaps": "VIPA6,_,_,_,_,_,_,20,18,16,14,12,10,8,6,4,2,1,3,5,7,9,11,13,15,17,_;
	 *              VIPA7,_,_,_,_,_,20,18,16,14,12,10,8,6,4,2,1,3,5,7,9,11,13,15,17,19,_;
	 *              VIPA8,_,_,_,_,20,18,16,14,12,10,8,6,4,2,1,3,5,7,9,11,13,15,17,19,21,_;
	 *              VIPA9,_,_,_,22,20,18,16,14,12,10,8,6,4,2,1,3,5,7,9,11,13,15,17,19,21,_;
	 *              VIPA10,_,_,24,22,20,18,16,14,12,10,8,6,4,2,1,3,5,7,9,11,_;",
	 *          "sort": 1
	 *      },
	 *      "context" : {
	 *          ...
	 *      }
	 * }
	 *  
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {Boolean} data 返回结果对象,true:成功 false:失败
	 * 
	 * @apiSuccessExample {json} 成功响应数据
	 * {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : true
	 * }
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
	 */
	public Result<Boolean> addSeatChartInitSeat(SeatChartModel seatChartModel, ServiceContext context);
}
