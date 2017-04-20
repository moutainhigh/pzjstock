package com.pzj.core.product.service;

import java.util.ArrayList;

import com.pzj.core.product.model.QuerySeatDescResModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

public interface SeatQueryService {

	/**
	* @api {dubbo} com.pzj.core.product.service.SeatQueryService#querySeatByTransactionId 根据订单id查询座位
	* @apiName 根据订单id查询座位
	* @apiGroup SAAS&ERP 座位
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 根据订单id查询座位
	*
	* @apiParam (请求参数) {String} transacTionId 订单id
	* @apiParam (请求参数) {ServiceContext} context 上下文
	* 
	* @apiParamExample {json} 请求示例
	*	{
	*		"transacTionId":"MF123456",
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
	* @apiSuccessExample {json} 成功响应数据
	* {
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":{
	*				"seatId":1,
	*				"seatName":"A1_27"
	*			}
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
	Result<ArrayList<QuerySeatDescResModel>> querySeatByTransactionId(String transacTionId,
			ServiceContext serviceContext);
}
