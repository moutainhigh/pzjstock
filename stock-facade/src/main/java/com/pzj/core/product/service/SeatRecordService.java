package com.pzj.core.product.service;

import com.pzj.core.product.model.OccupyStockReqsModel;
import com.pzj.core.product.model.OccupyStockResponse;
import com.pzj.core.product.model.ReleaseStockReqsModel;
import com.pzj.core.product.model.seatRecord.SeatRecordCreateReqModel;
import com.pzj.core.product.model.seatRecord.SeatRecordUpdateReqModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * Created by Administrator on 2017-3-8.
 */
public interface SeatRecordService {

	/**
	 * 占座
	 *
	 * @api {dubbo} com.pzj.core.product.service.SeatRecordService#occupyStock 占座
	 * @apiGroup SAAS&ERP 座位
	 * @apiVersion 1.2.0-SNAPSHOT
	 * @apiDescription
	 *
	 * <p>
	 *     占座分为下单占座和预约占座两种，主要的表现为下单占座的座位类型是已选状态，而预约占座的座位状态为预选状态。另外业务逻辑上也有些不同。
	 * </p>
	 * <p>
	 *     不同场景的占座时，传参需要一些注意事项：
	 *     <ul>
	 *         <li>
	 *             后台派座时，下单成功后，此时是没有选座的，无法知道需要占的座位id，但是还得要减库存。所以需要传一个库存数量（outQuantity字段），告知接口要减多少库存。
	 *         </li>
	 *         <li>
	 *             用户自选座位时，下单成功后，此时是知道选了哪些座位的，以及座位的id，所以只需要将座位id集合传给占座接口，接口内部会将锁定的座位变更为占座状态。
	 *         </li>
	 *         <li>
	 *             后台派座时，对于同一个订单，需要占多座位，不必一次分配完，可分成多次，每次只分配一部分座位，也可将之前分配的座位释放掉，也可调整库存。
	 *             为了保持多次调占库存接口时，占库存不会错误，outQuantity要传递需要占库存的总量。接口内部会将些值与之前的做对比，如果一致不会调整库存，否则调整库存。
	 *         </li>
	 *     </ul>
	 * </p>
	 *
	 * @apiParam (请求参数) {List} occupyStockReqs 每个产品的占库存参数，每个元素为OccupyStockReqModel类型。
	 *
	 * @apiParam (OccupyStockReqModel) {Long} operator 操作人id
	 * @apiParam (OccupyStockReqModel) {Long} productId 产品id
	 * @apiParam (OccupyStockReqModel) {Long} stockRuleId 库存规则id
	 * @apiParam (OccupyStockReqModel) {Integer} outQuantity 减库存数量
	 * @apiParam (OccupyStockReqModel) {Date} travelDate 出游日期
	 * @apiParam (OccupyStockReqModel) {String} transactionId 交易id
	 * @apiParam (OccupyStockReqModel) {Long} screeningsId 场次id
	 * @apiParam (OccupyStockReqModel) {Long} areaId 区域id
	 * @apiParam (OccupyStockReqModel) {Integer=0：无需占座,30：预约占座,40：下单占座} occupyType 占座类型。请使用常量com.pzj.core.common.utils.StockGlobalDict.ooccupyType。
	 * @apiParam (OccupyStockReqModel) {List} [occupySeatIds] 需要占座的座位id。每个元素为Long类型。
	 * @apiParam (OccupyStockReqModel) {List} [releaseSeatIds] 需要释放的座位id。每个元素为Long类型。
	 *
	 * @apiParamExample {json} 请求参数示例
	 *
	 * {
	 *     "operator": 1747,
	 *     "productId": 1,
	 *     "stockRuleId": 2216619741565822,
	 *     "outQuantity": "5",
	 *     "travelDate": "2017-03-27 00:00:00",
	 *     "transactionId": "WLQ000003",
	 *     "screeningsId": 220001,
	 *     "areaId": 1,
	 *     "occupyType": 40,
	 *     "occupySeatIds": [9, 10, 11],
	 *     "releaseSeatIds": []
	 * }
	 *
	 * @apiSuccess (响应数据) {int} errorCode 错误码
	 * @apiSuccess (响应数据) {String} errorMsg 错误说明
	 * @apiSuccess (响应数据) {OccupyStockResponse} data 占座异常数据
	 *
	 * @apiSuccessExample {json} 响应数据示例
	 *
	 * {
	 *     "errorCode" : 15052,
	 *     "errorMsg" : "对不起，该产品库存不足，当前还剩 1 份，请重新选择购买数量",
	 *     "data" : {
	 *     		"productId":855377373078040576,
	 *     		"stockId":23154,
	 *     		"stockRuleId":2216619741565878,
	 *     		"stockType":1,
	 *     		"remainNum":1,
	 *     		"travelDate":"Jun 22, 2017 12:00:00 AM"
	 *     }
	 * }
	 * @param occupyStockReqsModel
	 */
	Result<OccupyStockResponse> occupyStock(OccupyStockReqsModel occupyStockReqsModel);

	Result<Boolean> occupyStockForDock(OccupyStockReqsModel occupyStockReqsModel);

	/**
	 * 释放下单及预占的座位
	 * 请使用releaseStock接口。
	 *
	* @api {dubbo} com.pzj.core.product.service.SeatRecordService#releaaseStock 释放下单及预占的座位
	* @apiName 释放下单及预占的座位
	* @apiGroup SAAS&ERP 座位
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 根据交易id释放座位及库存
	*
	* @apiParam (请求参数) {ReleaseStockReqsModel} model 释放库存
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (ReleaseStockReqsModel) {Long} transactionId 交易id
	* @apiParam (ReleaseStockReqsModel) {ReleaseStockReqModel} releaseStockReqs 库存信息
	* 
	* @apiParam (ReleaseStockReqModel) {Long} productId 操作人id
	* @apiParam (ReleaseStockReqModel) {Integer} [seatNum] 释放的座位数量
	* @apiParam (ReleaseStockReqModel) {Integer} [stockNum] 释放的库存数量,默认为座位数量
	*
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"transactionId":987654,
	*			"releaseStockReqs":{
	*				"productId":12345678,
	*			},{
	*			"productId":12345677,
	*			"seatNum":11
	*			}
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Boolean} data 结果集为集合;
	*
	*
	* @apiSuccessExample {json} 成功响应数据
	* {
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":true
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
	@Deprecated
	Result<Boolean> releaaseStock(ReleaseStockReqsModel releaseStockReqsModel, ServiceContext context);

	Result<Boolean> releaseStock(ReleaseStockReqsModel releaseStockReqsModel);

	Result<Boolean> releaaseStockForDock(ReleaseStockReqsModel releaseStockReqsModel);

	Result<Boolean> releaseStockForDock(ReleaseStockReqsModel releaseStockReqsModel);

	/**
	* @api {dubbo} com.pzj.core.product.service.SeatRecordService#releaseSeat 释放锁定的座位及库存
	* @apiName 释放座位及库存
	* @apiGroup SAAS&ERP 座位
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 根据场次区域下的部分座位及库存
	*
	* @apiParam (请求参数) {SeatRecordUpdateReqModel} model 占座记录查询参数
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (SeatRecordUpdateReqModel) {Long} screeingId 场次id 交易id为空，则不可为空
	* @apiParam (SeatRecordUpdateReqModel) {Date} theaterDate 演出时间 年月日 交易id为空，则不可为空
	* @apiParam (SeatRecordUpdateReqModel) {String} transactionId 交易id 场次id和演出时间为空，则不可为空
	* @apiParam (SeatRecordUpdateReqModel) {Long} operateUserId 操作人id
	* @apiParam (SeatRecordUpdateReqModel) {SeatInfoModel} seatInfos 座位信息集合
	*
	* @apiParam (SeatInfoModel) {Long} seatId 座位id
	* @apiParam (SeatInfoModel) {Long} [areaId] 区域id
	*
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"screeingId":987654,
	*			"theaterDate":"2017-03-08",
	*			"seatInfos":{
	*				"areaId":12345678,
	*				"seatId":123
	*			}
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Boolean} data 结果集为集合;
	*
	*
	* @apiSuccessExample {json} 成功响应数据
	* {
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":true
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
	Result<Boolean> releaseSeat(SeatRecordUpdateReqModel model, ServiceContext serviceContext);

	/**
	* @api {dubbo} com.pzj.core.product.service.SeatRecordService#lockingSeat 锁定座位及库存
	* @apiName 锁定座位及库存
	* @apiGroup SAAS&ERP 座位
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 锁定场次区域下的部分座位及库存
	*
	* @apiParam (请求参数) {SeatRecordCreateReqModel} model 占座记录查询参数
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (SeatRecordCreateReqModel) {Long} screeingId 场次id
	* @apiParam (SeatRecordCreateReqModel) {String} operateUserId 操作人id
	* @apiParam (SeatRecordCreateReqModel) {Date} theaterDate 演出时间
	* @apiParam (SeatRecordCreateReqModel) {LockSeatType} lockSeatType 锁座类型 参考LockSeatType枚举
	* @apiParam (SeatRecordCreateReqModel) {SeatInfoModel} seatInfos 占座的座位集合
	*
	*
	* @apiParam (SeatInfoModel) {String} seatName 座位号
	* @apiParam (SeatInfoModel) {Long} seatId 座位id
	* @apiParam (SeatInfoModel) {Long} areaId 区域id
	*
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"screeingId":987654,
	*			"operateUserId":123456,
	*			"theaterDate":"2017-03-08",
	*			"lockSeatType":LockSeatType.LONG_VALID
	*			"seatInfos":{
	*				"areaId":12345678,
	*				"seatName":"A1_22",
	*				"seatId":111
	*				
	*			}
	*
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Boolean} data 结果集为集合;
	*
	*
	* @apiSuccessExample {json} 成功响应数据
	* {
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":true
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
	Result<Boolean> lockingSeat(SeatRecordCreateReqModel model, ServiceContext serviceContext);

	/**
	* @api {dubbo} com.pzj.core.product.service.SeatRecordService#checkavailableSeatRecord 自动释放座位及库存
	* @apiName 自动释放座位及库存
	* @apiGroup SAAS&ERP 座位
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 扫描所有过期的锁定座位进行释放
	*
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Boolean} data 结果集为集合;
	* 
	*  
	* @apiSuccessExample {json} 成功响应数据
	* {
	*			"errorCode":10000,
	*			"errorMsg":"ok",
	*			"data":true
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
	Result<Boolean> checkavailableSeatRecord(ServiceContext serviceContext);

}
