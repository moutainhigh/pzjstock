/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product.service;

import java.util.ArrayList;

import com.pzj.core.product.model.QueryValidSeatRecordResponse;
import com.pzj.core.product.model.seat.SeatRespModel;
import com.pzj.core.product.model.seatRecord.SeatRecordReqModel;
import com.pzj.core.product.model.statistics.AreaCollectRespModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 占座记录查询相关
 * @author Administrator
 * @version $Id: SeatRecordQueryService.java, v 0.1 2017年3月8日 下午3:14:08 Administrator Exp $
 */
public interface SeatRecordQueryService {

	/**
	* @api {dubbo} com.pzj.core.product.service.SeatRecordQueryService#queryAreaCollects 查询某场次下各个区域的各个座位图状态数量
	* @apiName 查询某场次下各个区域的各个座位图状态数量
	* @apiGroup SAAS&ERP 统计
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 根据剧场、场次、时间查询各个区域的各个座位图状态数量
	*
	* @apiParam (请求参数) {SeatRecordReqModel} model 占座记录查询参数
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (SeatRecordReqModel) {Long} screeingId 场次id
	* @apiParam (SeatRecordReqModel) {Date} theaterDate 演出时间 年月日
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"screeingId":987654,
	*			"theaterDate":"2017-03-08"
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {AreaCollectRespModel} data 结果集为集合;
	* 
	* 
	* @apiParam (AreaCollectRespModel) {Long} areaId 区域id
	* @apiParam (AreaCollectRespModel) {Long} areaName 区域名称
	* @apiParam (AreaCollectRespModel) {Integer} occupiedNum 已占数量
	* @apiParam (AreaCollectRespModel) {Integer} unOccupiedNum 可选数量
	* @apiParam (AreaCollectRespModel) {Integer} lockedNum 已锁
	* @apiParam (AreaCollectRespModel) {Integer} preOccupiedNum 预占
	* @apiParam (AreaCollectRespModel) {Integer} totalNum 总座位数
	* 
	*  
	* @apiSuccessExample {json} 成功响应数据
	* {
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":[
	*               {
	*					"assignedId":12345678,
	*					"assignedState":1,
	*					"transactionId":"MF1587451",
	*					"areaId":"11",
	*					"areaName":"VIP",
	*					"occupiedNum":"10",
	*					"unOccupiedNum":"20"
	*			    },
	*  				 {
	*					"assignedId":23456789,
	*					"assignedState":2,
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
	Result<ArrayList<AreaCollectRespModel>> queryAreaCollects(SeatRecordReqModel model, ServiceContext serviceContext);

	/**
	* @api {dubbo} com.pzj.core.product.service.SeatRecordQueryService#querySeatState 查询某场次下的占座情况
	* @apiName 查询某场次下的占座情况
	* @apiGroup SAAS&ERP 统计
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 根据剧场、场次、占座状态查询占座记录 已占，锁定，预占3个状态
	*
	* @apiParam (请求参数) {SeatRecordReqModel} model 占座记录查询参数
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (SeatRecordReqModel) {Date} theaterDate 游玩时间
	* @apiParam (SeatRecordReqModel) {Long} screeingId 场次id
	* @apiParam (SeatRecordReqModel) {RecordCategory} [recordCategory] 占座类型
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"travelDate":"2017-03-20",
	*			"screeingId":987654,
	*			"recordCategory":RecordCategory
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {SeatRespModel} data 结果集为集合;
	* 
	* 
	* @apiParam (SeatRespModel) {Long} areaId 区域id
	* @apiParam (SeatRespModel) {Long} seatId 座位id
	* @apiParam (SeatRespModel) {String} seatName 座位名称
	* @apiParam (SeatRespModel) {Integer} showState 座位状态 2：已选；3：锁定；4：预选
	* 
	*  
	* @apiSuccessExample {json} 成功响应数据
	* {
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":[
	*               {
	*					"areaId":12345678,
	*					"seatId":1,
	*					"seatName":"MF1587451",
	*					"showState":3
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
	Result<ArrayList<SeatRespModel>> querySeatStateByRecord(SeatRecordReqModel model, ServiceContext context);

	/**
	 * 通过交易ID，查询有效的占座记录
	 * @param transactionId
	 * @return
	 */
	Result<QueryValidSeatRecordResponse> queryValidSeatRecordByTransactionId(String transactionId);
}
