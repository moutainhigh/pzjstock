package com.pzj.core.product.service;

import java.util.ArrayList;
import java.util.List;

import com.pzj.core.product.model.seat.*;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * Created by Administrator on 2017-3-8.
 */
public interface SeatCharService {

	/**
	 * 创建座位
	 *
	 * @api {dubbo} com.pzj.core.product.service.SeatCharService#createSeatCahar 创建座位
	 * @apiGroup SAAS&ERP 座位
	 * @apiVersion 1.2.0-SNAPSHOT
	 * @apiDescription
	 *
	 * <p>
	 *     创建座位
	 * </p>
	 *
	 * @apiParam (请求参数) {Long} theaterId 剧场id
	 * @apiParam (请求参数) {Long} areaId 区域id
	 * @apiParam (请求参数) {String} [lineName] 自定义的行的名称
	 * @apiParam (请求参数) {String} [columnName] 自定义的列的名称
	 * @apiParam (请求参数) {Integer} abscissa 横坐标
	 * @apiParam (请求参数) {Integer} ordinate 纵坐标
	 * @apiParam (请求参数) {Boolean=true：是座位,false：不是座位} isSeat 是否座位
	 *
	 * @apiParamExample {json} 请求参数示例
	 *
	 * {
	 *     "theaterId" : 6478924528342394564,
	 *     "areaId" : 6478924528342394564,
	 *     "lineName" : "3排",
	 *     "columnName" : "5列",
	 *     "abscissa" : 3,
	 *     "ordinate" : 5,
	 *     "isSeat" : true
	 * }
	 *
	 * @apiSuccess (响应数据) {int} errorCode 错误码
	 * @apiSuccess (响应数据) {String} errorMsg 错误说明
	 * @apiSuccess (响应数据) {Boolean=true：成功,false：失败} data 是否创建成功
	 *
	 * @apiSuccessExample {json} 响应数据示例
	 *
	 * {
	 *     "errorCode" : 10000,
	 *     "errorMsg" : null,
	 *     "data" : true
	 * }
	 * @param createSeatCharReqModel
	 */
	Result<Boolean> createSeatChar(CreateSeatCharReqModel createSeatCharReqModel);

	/**
	 * 修改座位
	 *
	 * @api {dubbo} com.pzj.core.product.service.SeatCharService#modifySeatChar 修改座位
	 * @apiGroup SAAS&ERP 座位
	 * @apiVersion 1.2.0-SNAPSHOT
	 * @apiDescription
	 *
	 * <p>
	 *     修改座位
	 * </p>
	 *
	 * @apiParam (请求参数) {Long} seatId 座位id
	 * @apiParam (请求参数) {Long} [areaId] 区域id
	 * @apiParam (请求参数) {String} [lineName] 自定义的行的名称
	 * @apiParam (请求参数) {String} [columnName] 自定义的列的名称
	 * @apiParam (请求参数) {Boolean=true：是座位,false：不是座位} [isSeat] 是否座位
	 *
	 * @apiParamExample {json} 请求参数示例
	 *
	 * {
	 *     "seatId" : 6478924528342394564,
	 *     "areaId" : 6478924528342394564,
	 *     "lineName" : "3排",
	 *     "columnName" : "5列",
	 *     "isSeat" : true
	 * }
	 *
	 * @apiSuccess (响应数据) {int} errorCode 错误码
	 * @apiSuccess (响应数据) {String} errorMsg 错误说明
	 * @apiSuccess (响应数据) {Boolean=true：成功,false：失败} data 是否修改成功
	 *
	 * @apiSuccessExample {json} 响应数据示例
	 *
	 * {
	 *     "errorCode" : 10000,
	 *     "errorMsg" : null,
	 *     "data" : true
	 * }
	 * @param modifySeatCharReqModel
	 * @return
	 */
	Result<Boolean> modifySeatChar(ModifySeatCharReqModel modifySeatCharReqModel);

	/**
	 * 查询区域实时座位图
	 * @param areaId
	 * @param serviceContext
	 * @return
	 */
	/**
	* @api {dubbo} com.pzj.core.product.service.SeatCharService.queryAreaSeatchart.queryNewestSeatchart 查询实时座位图
	* @apiName 查询实时座位图
	* @apiGroup SAAS&ERP 座位
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 查询实时座位图
	*
	* @apiParam (请求参数) {SeatReqModel} reqModel 实时座位图查询参数
	* @apiParam (请求参数) {ServiceContext} [context] 上下文
	*
	* @apiSuccess (SeatReqModel) {Long} scenicId 景区ID
	* @apiSuccess (SeatReqModel) {Long} screeingId 场次ID
	* @apiSuccess (SeatReqModel) {Date} showTime 演出时间
	* @apiSuccess (SeatReqModel) {Long} [operateUserId] 锁座时获取操作的用户id
	* 
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"scenicId":1,
	*			"screeingId":1,
	*			"showTime":"2017-03-08"
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {ArrayList} data 返回结果座位图对象SeatRespModel;
	* 
	* @apiSuccess (SeatRespModel) {Long} seatId 座位id
	* @apiSuccess (SeatRespModel) {Long} areaId 区域id
	* @apiSuccess (SeatRespModel) {String} seatName 座位名称
	* @apiSuccess (SeatRespModel) {String} yPos 列
	* @apiSuccess (SeatRespModel) {String} xPos 行
	* @apiSuccess (SeatRespModel) {Integer} saleState 1：可售；2：锁定
	* @apiSuccess (SeatRespModel) {String} showState 1:可选；2：已选；3：锁定；4：预选
	* @apiSuccess (SeatRespModel) {Long} operateUserId 操作的用户id
	* @apiSuccess (SeatRespModel) {Long} lockSeatCurUserIsOpe 锁定的座位当前用户是否可以操作;TRUE:可以操作，FALSE:不可以操作
	* @apiSuccess (SeatRespModel) {String} [transactionId] 交易id
	* 
	* @apiSuccessExample {json} 成功响应数据
	*	{
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":[
	*			{
	*				"seatId":1,
	*				"areaId":1,
	*				"seatNum":"A1_1_27",
	*				"xPos":"1",
	*				"yPos":"27",
	*				"saleState":1,
	*				"showState":1,
	*				"lockSeatCurUserIsOpe":false,
	*				"transactionId":"MF98764523"
	*			}
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
	Result<ArrayList<SeatRespModel>> queryNewestTheaterSeatchart(SeatReqModel reqModel, ServiceContext serviceContext);

	/**
	* @api {dubbo} com.pzj.core.product.service.SeatCharService.queryAreaSeatchart.queryNewestAreaSeatchart 查询实时区域座位图
	* @apiName 查询实时区域座位图
	* @apiGroup SAAS&ERP 座位
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 查询实时区域座位图
	*
	* @apiParam (请求参数) {SeatReqModel} reqModel 实时座位图查询参数
	* @apiParam (请求参数) {ServiceContext} [context] 上下文
	*
	* @apiSuccess (SeatReqModel) {Long} scenicId 景区ID
	* @apiSuccess (SeatReqModel) {Long} screeingId 场次ID
	* @apiSuccess (SeatReqModel) {Long} areaId 区域ID
	* @apiSuccess (SeatReqModel) {Date} showTime 演出时间
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"screeingId":1,
	*			"areaId":1,
	*			"showTime":"2017-03-08"
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {ArrayList} data 返回结果座位图对象SeatRespModel;
	* 
	* @apiSuccess (SeatRespModel) {Long} seatId 座位id
	* @apiSuccess (SeatRespModel) {Long} areaId 区域id
	* @apiSuccess (SeatRespModel) {String} seatName 座位名称
	* @apiSuccess (SeatRespModel) {String} yPos 列
	* @apiSuccess (SeatRespModel) {String} xPos 行
	* @apiSuccess (SeatRespModel) {Integer} saleState 1：可售；2：锁定
	* @apiSuccess (SeatRespModel) {String} showState 1:可选；2：已选；3：锁定；4：预选
	* @apiSuccess (SeatRespModel) {String} [transactionId] 交易id
	* 
	* @apiSuccessExample {json} 成功响应数据
	*	{
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":[
	*			{
	*				"seatId":1,
	*				"areaId":1,
	*				"seatNum":"A1_1_27",
	*				"xPos":"1",
	*				"yPos":"27",
	*				"saleState":1,
	*				"showState":1
	*			}
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
	Result<ArrayList<SeatRespModel>> queryNewestAreaSeatchart(SeatReqModel reqModel, ServiceContext serviceContext);

	/**
	 * 根据剧场查询区域实时座位图
	 * @param areaId
	 * @param serviceContext
	 * @return
	 */
	/**
	* @api {dubbo} com.pzj.core.product.service.SeatCharService.queryAreaSeatchart.queryTheaterSeatchart 根据剧场查询区域座位图
	* @apiName 查询区域座位图
	* @apiGroup SAAS&ERP 座位
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 根据剧场id查询区域座位图
	*
	* @apiParam (请求参数) {Long} theaterId 剧场ID
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"theaterId" : 1
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {ArrayList} data 返回结果座位图对象AreaSeatChartRespModel;
	* 
	* @apiSuccess (TheaterSeatChartRespModel) {Long} seatId 座位id
	* @apiSuccess (TheaterSeatChartRespModel) {Long} areaId 区域id
	* @apiSuccess (TheaterSeatChartRespModel) {String} seatNum 座位号
	* @apiSuccess (TheaterSeatChartRespModel) {String} seatName 座位名称
	* @apiSuccess (TheaterSeatChartRespModel) {String} column  列
	* @apiSuccess (TheaterSeatChartRespModel) {String} row 行
	* @apiSuccess (TheaterSeatChartRespModel) {Integer} saleState 1：可售；2：锁定
	* 
	* @apiSuccessExample {json} 成功响应数据
	*	{
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":[
	*			{
	*				"areaId":1,
	*				"seatNum":"A1_27",
	*				"seatName":"12",
	*				"name":"早场",
	*				"column":"zc",
	*				"row":"1",
	*				"saleState":1,
	*				"showState":1
	*			}
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
	Result<ArrayList<TheaterSeatChartRespModel>> queryTheaterSeatchart(Long theaterId, ServiceContext serviceContext);

	/**
	* @api {dubbo} com.pzj.core.product.service.SeatCharService.queryAreaSeatchart.queryAreaSeatTotal 统计区域座位总数
	* @apiName 统计区域座位总数
	* @apiGroup SAAS&ERP 座位
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 统计区域座位总数
	*
	* @apiParam (请求参数) {Long} areaId 区域ID
	* @apiParam (请求参数) {ServiceContext} [serviceContext] 上下文参数
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"areaId" : 1
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Integer} data 返回结果座位总数
	* 
	* 
	* @apiSuccessExample {json} 成功响应数据
	*	{
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data": 100
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
	Result<Integer> queryAreaSeatTotal(Long areaId, ServiceContext serviceContext);

	/**
	 * 根据id查询座位
	 * @api {dubbo} com.pzj.core.product.service.SeatCharService.queryTheaterSeatchartBySeatId 根据id查询座位
	 *apiName 统计区域座位总数
	 * @apiGroup SAAS&ERP 座位
	 * @apiVersion 1.2.0-SNAPSHOT
	 * @apiDescription 根据id查询座位
	 *
	 * @apiParam (请求参数) {List} seatIds 座位ID集合，每个元素为Long类型
	 *
	 * @apiParamExample {json} 请求示例
	 *	{
	 *		"seatIds" : [
	 *			1,
	 *			2
	 *		]
	 *	}
	 *
	 * @apiSuccess (响应数据) {int} errorCode 返回结果码
	 * @apiSuccess (响应数据) {String} errorMsg 返回结果提示
	 * @apiSuccess (响应数据) {SeatChartManyRespModel} data 查询座位结果
	 *
	 * @apiSuccess (SeatChartManyRespModel) {List} seats 座位集合，每个元素类型为TheaterSeatChartRespModel。
	 *
	 * @apiSuccessExample {json} 成功响应数据
	 *	{
	 *			"errorCode":10000,
	 *			"errorMsg":"ok",
	 *			"data": { "seats" : [
	 *				{
	 *					"areaId":1,
	 *					"seatNum":"A1_27",
	 *					"seatName":"12",
	 *					"name":"早场",
	 *					"column":"zc",
	 *					"row":"1",
	 *					"saleState":1,
	 *					"showState":1
	 *				}
	 *			]}
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
	 * @param seatIds
	 * @return
	 */
	Result<SeatChartManyRespModel> querySeatChartBySeatId(List<Long> seatIds);
}
