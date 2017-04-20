/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service;

import com.pzj.core.product.model.area.AreaModel;
import com.pzj.core.product.model.area.CreateAreaReqModel;
import com.pzj.core.product.model.area.DelAreasReqModel;
import com.pzj.core.product.model.area.ModifyAreaReqModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 演艺区域写操作
 * @author dongchunfu
 * @version $Id: ActingAreaService.java, v 0.1 2016年8月1日 上午10:37:43 dongchunfu Exp $
 */
public interface AreaService {
	/**
	 * 创建演艺区域
	 * @author dongchunfu
	 * @param area 区域实体
	 * @return Result<Long> 新增实体ID
	 */

	/**
	 * @api {dubbo} com.pzj.core.product.service.AreaService.createArea 创建演艺区域
	 * @apiName 创建演艺区域
	 * @apiGroup 演艺接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 创建演艺区域
	 *
	 * @apiParam (请求参数) {AreaModel} model 演艺区域VO
	 * @apiParam (请求参数) {ServiceContext} context 上下文
	 * 
	 * @apiParam (AreaModel) {Long} scenicId 景区ID
	 * @apiParam (AreaModel) {String} code 区域标识
	 * @apiParam (AreaModel) {String} name 区域名称
	 * @apiParam (AreaModel) {Long} [id] 区域主键id
	 * @apiParam (AreaModel) {Date} [createTime] 创建时间
	 * @apiParam (AreaModel) {Date} [updateTime] 修改时间
	 *
	 * 
	 * @apiSuccess {object} object 影响数据库:stock 表:prod_area 字段:所有字段
	 *
	 * @apiParamExample {json} 请求示例
	 *	{
	 *		"model":{
	 *			"scenicId":2216619741563804,
	 *			"code":"A区",
	 *			"name":"A区"
	 *		},
	 *		context:{
	 *			.........
	 *		}
	 *	}
	 *
	 * 
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {Long} data 返回创建区域数量
	 *
	 * @apiSuccessExample {json} 成功响应数据
	 *   {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : 1
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
	public Result<Long> createArea(AreaModel model, ServiceContext context);

	/**
	 * 更新演艺区域
	 * @author dongchunfu
	 * @param area 区域实体
	 * @return Result<Integer> 更新数量
	 */

	/**
	* @api {dubbo} com.pzj.core.product.service.AreaService.updateArea 更新演艺区域信息
	* @apiName 演艺区域信息更新
	* @apiGroup 演艺接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 演艺区域信息更新
	*
	* @apiParam (请求参数) {AreaModel} model 演艺区域VO
	* @apiParam (请求参数) {ServiceContext} context 上下文
	*
	* @apiParam (AreaModel) {Long} id 区域主键id
	* @apiParam (AreaModel) {Long} [scenicId] 景区ID
	* @apiParam (AreaModel) {String} [code] 区域标识
	* @apiParam (AreaModel) {String} [name] 区域名称
	* @apiParam (AreaModel) {Date} [createTime] 创建时间
	* @apiParam (AreaModel) {Date} [updateTime] 修改时间
	*
	* 
	* @apiSuccess {object} object 影响数据库:stock 表:prod_area 字段:所有字段
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"model":{
	*			"id":128
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Integer} data 修改成功数量;
	* 
	* @apiSuccessExample {json} 成功响应数据
	* {
	*   "errorCode":10000,
	*   "errorMsg":"ok",
	*	"data":1
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
	*
	*/
	public Result<Integer> updateArea(AreaModel model, ServiceContext context);

	/**
	 * 创建区域
	 *
	 * @api {dubbo} com.pzj.core.product.service.AreaService#createArea 创建区域
	 * @apiGroup SAAS&ERP 区域
	 * @apiVersion 1.2.0-SNAPSHOT
	 * @apiDescription
	 *
	 * <p>
	 *     创建区域信息
	 * </p>
	 *
	 * @apiParam (请求参数) {Long} theaterId 剧场id
	 * @apiParam (请求参数) {Long} supplierId 供应商id
	 * @apiParam (请求参数) {Integer=1：无座位,2：有座位，只支持矩形} seatType 座位类型
	 * @apiParam (请求参数) {Integer=1：用户自选,2：手动派座,3：自动派座} seatMode 座位模式
	 * @apiParam (请求参数) {String} thumb 缩略图(路径)
	 * @apiParam (请求参数) {List} areaInfos 区域信息集合，每个元素的类型为CreateAreaInfoReqModel
	 *
	 * @apiParam (CreateAreaInfoReqModel) {String} name 区域名称
	 * @apiParam (CreateAreaInfoReqModel) {String} [code] 区域标识
	 *
	 * @apiParam (AreaModel) {List} areaIds 区域主键id集合
	 *
	 * @apiParamExample {json} 请求参数示例
	 *
	 * {
	 *     "theaterId" : 546982334534256236,
	 *     "supplierId" : 123456789,
	 *     "seatType" : "1",
	 *     "seatMode" : "1",
	 *     "thumb" : "地址",
	 *	   "areaInfos" : [{
	 *         "name" : "VIP1区",
	 *         "code" : "VIP1"
	 *	    }]
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
	 *
	 * @param createAreaReqModel
	 * @return
     */
	Result<Boolean> createArea(CreateAreaReqModel createAreaReqModel);

	/**
	 * 修改区域
	 *
	 * @api {dubbo} com.pzj.core.product.service.AreaService#modifyArea 修改区域
	 * @apiGroup SAAS&ERP 区域
	 * @apiVersion 1.2.0-SNAPSHOT
	 * @apiDescription
	 * <p>
	 *     根据id修改区域信息
	 * </p>
	 *
	 * @apiParam (请求参数) {ModifyAreaReqModel} [thumb] 缩略图(路径)
	 * @apiParam (请求参数) {List} [areaInfos] 区域信息集合，List的元素类型为ModifyAreaInfoReqModel
	 *
	 * @apiParam (ModifyAreaInfoReqModel) {Long} id 区域id
	 * @apiParam (ModifyAreaInfoReqModel) {String} [name] 区域名称
	 * @apiParam (ModifyAreaInfoReqModel) {String} [code] 区域标识
	 *
	 * @apiParam (AreaModel) {List} areaIds 区域主键id集合
	 *
	 * @apiParamExample {json} 请求参数示例
	 *
	 * {
	 *     "thumb" : "地址",
	 *	   "areaInfos" : [{
	 *         "id" : 538342443t64567,
	 *         "name" : "VIP1区",
	 *         "code" : "VIP1"
	 *	    }]
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
	 *
	 * @param modifyAreaReqModel
	 * @return
     */
	Result<Boolean> modifyArea(ModifyAreaReqModel modifyAreaReqModel);

	/**
	* @api {dubbo} com.pzj.core.product.service.AreaService.deleteAreas 删除区域
	* @apiName 删除区域
	* @apiGroup SAAS&ERP 区域
	* @apiVersion 1.2.0-SNAPSHOT
	* @apiDescription 删除区域
	*
	* @apiParam (请求参数) {DelAreasReqModel} reqModel 删除区域对象
	* @apiParam (请求参数) {ServiceContext} [context] 上下文
	*
	* @apiParam (AreaModel) {List} areaIds 区域主键id集合
	*
	* @apiSuccess {object} object 影响数据库:stock 表:prod_area 字段:state字段
	*
	* @apiParamExample {json} 请求示例
	*	{
	*		"reqModel":{
	*			"areaIds":[128]
	*		},
	*		context:{
	*			.........
	*		}
	*	}
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Boolean} data 修改结果返回，TRUE：成功，FALSE：失败
	*
	* @apiSuccessExample {json} 成功响应数据
	* {
	*   "errorCode":10000,
	*   "errorMsg":"ok",
	*	"data":true
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
	*/
	public Result<Boolean> deleteAreas(DelAreasReqModel reqModel, ServiceContext context);
}
