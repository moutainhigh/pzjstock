/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service;

import java.util.ArrayList;

import com.pzj.core.product.model.ActingProductModel;
import com.pzj.core.product.model.ActingProductQueryRequstModel;
import com.pzj.core.product.model.AreaScreeingsRelModel;
import com.pzj.core.product.model.ProScreeningsQueryModel;
import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author dongchunfu
 * @version $Id: CreatActingProductService.java, v 0.1 2016年8月8日 上午10:05:20 dongchunfu Exp $
 */
public interface ActingProductService {

	/**
	 * @api {dubbo} com.pzj.core.product.service.ActingProductService.createActingProduct 新增区域场次关联
	 * @apiName 新增区域场次关联
	 * @apiGroup 演艺接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 新增区域场次关联
	 *
	 * @apiParam (请求参数) {ActingProductModel} model 新增区域场次关联请求model
	 * @apiParam (请求参数) {ServiceContext} [context] 请求上下文
	 *
	 * @apiParam (ActingProductModel) {Long} actingId 演绎ID
	 * @apiParam (ActingProductModel) {ArrayList} areas 区域AreaModel对象集合
	 * @apiParam (ActingProductModel) {ArrayList} screeingses 场次ScreeingsModel对象集合 
	 * @apiParam (ActingProductModel) {Long} [scenicId] 景区ID
	 * @apiParam (ActingProductModel) {Long} [supplierId] 供应商ID
	 * @apiParam (ActingProductModel) {ArrayList} [actings] 演艺ActingModel对象集合
	 * @apiParam (ActingProductModel) {ActingModel} [actingModel] 演艺产品绑定演艺信息对象
	 * @apiParam (ActingProductModel) {AreaModel} [areaModel] 演绎产品绑定区域信息
	 * @apiParam (ActingProductModel) {ScreeingsModel} [screeingsModel] 演绎产品绑定场次信息
	 * 
	 * @apiParam (AreaModel) {Long} id 区域主键id
	 * @apiParam (AreaModel) {String} name 区域名称
	 * @apiParam (AreaModel) {Long} [scenicId] 景区ID
	 * @apiParam (AreaModel) {String} [code] 区域标识
	 * @apiParam (AreaModel) {Date} [createTime] 创建时间
	 * @apiParam (AreaModel) {Date} [updateTime] 修改时间 
	 * 
	 * @apiParam (ScreeingsModel) {Long} id 场次主键id
	 * @apiParam (ScreeingsModel) {String} name 场次名称
	 * @apiParam (ScreeingsModel) {Long} [scenicId] 景区ID
	 * @apiParam (ScreeingsModel) {String} [code] 场次标识
	 * @apiParam (ScreeingsModel) {Date} [startTime] 演出开始时间
	 * @apiParam (ScreeingsModel) {Date} [endTime] 演出结束时间
	 * @apiParam (ScreeingsModel) {Date} [endSaleTime] 演出停售时间
	 * @apiParam (ScreeingsModel) {Date} [createTime] 创建时间
	 * @apiParam (ScreeingsModel) {Date} [updateTime] 修改时间 
	 *
	 * @apiParamExample 请求参数示例
	 *   {
	 *       "model":{
	 *          "actingId": 12214,
	 *          "areas": [
	 *              {
	 *              "id": 125,
	 *              "name": "A区"
	 *              },
	 *              {
	 *              "id": 126,
	 *              "name": "B区"
	 *              }
	 *          ],
	 *          "screeingses": [
	 *              {
	 *              "id": 1125,
	 *              "name": "第一场"
	 *              },
	 *              {
	 *              "id": 1126,
	 *              "name": "第二场"
	 *              }
	 *          ]
	 *       }
	 *       context:{
	 *           .........
	 *       }
	 *   }
	 *
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {Integer} data 返回新增区域场次关联数量
	 *
	 * @apiSuccessExample {json} 成功响应数据
	 *   {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : 4
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
	 * @apiExample 影响数据库数据
	 *  数据库：stock
	 *  prod_area_screeings表新增字段：acting_id, area_id, area_name, screeings_id, screeings_name, state, create_time
	 *
	 */
	Result<Integer> createActingProduct(ActingProductModel model, ServiceContext context);

	/**
	 * @api {dubbo} com.pzj.core.product.service.ActingProductService.queryInfoForSkuProduct 查询区域场次相关信息
	 * @apiName 查询区域场次相关信息
	 * @apiGroup 演艺接口
	 * @apiVersion 1.1.0-SNAPSHOT
	 * @apiDescription 查询区域场次相关信息
	 *
	 * @apiParam (请求参数) {ActingProductQueryRequstModel} model 新增区域场次关联请求model
	 * @apiParam (请求参数) {ServiceContext} [context] 请求上下文
	 *
	 * @apiParam (ActingProductQueryRequstModel) {Long} scenicId 景区id
	 * @apiParam (ActingProductQueryRequstModel) {Long} supplierId 供应商id
	 * @apiParam (ActingProductQueryRequstModel) {Long} [actProId] 演艺区域场次关联ID
	 * 
	 *
	 * @apiParamExample 请求参数示例
	 *   {
	 *       "model":{
	 *          "scenicId": 12214,
	 *          "supplierId": 12214
	 *       }
	 *       context:{
	 *           .........
	 *       }
	 *   }
	 *
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {ArrayList} data 返回区域场次关联AreaScreeingsRelModel对象集合
	 *
	 * @apiSuccessExample {json} 成功响应数据
	 *   {
	 *      "errorCode" : 10000,
	 *      "errorMsg" : "ok",
	 *      "data" : []
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
	 *
	 */
	Result<ArrayList<AreaScreeingsRelModel>> queryInfoForSkuProduct(ActingProductQueryRequstModel model,
			ServiceContext context);

	/**
	 * 根据演艺产品查询场次信息
	 * @param model
	 * @param context
	 * @return
	 */

	Result<ScreeingsModel> queryScreeningsByActProductId(ProScreeningsQueryModel model, ServiceContext context);

	/**
	 * 根据供应商ID 及景区ID 获取演艺相关的数据 区域/场次/演艺（供web使用）
	 * 
	 * @param model 演艺产品请求参数
	 * @param context 服务上下文
	 * @return 返回符合要求的 演艺/区域/场次集合信息
	 */
	Result<ActingProductModel> queryInfoForCreateActingProduct(ActingProductQueryRequstModel model,
			ServiceContext context);

	/**
	* @api {dubbo} com.pzj.core.product.service.ActingProductService.queryActProInfo 查询景区场次区域关联信息
	* @apiName 查询景区场次区域关联信息
	* @apiGroup 演艺接口
	* @apiVersion 1.1.0-SNAPSHOT
	* @apiDescription 查询景区场次区域关联信息
	*
	* @apiParam (请求参数) {ActingProductQueryRequstModel} model 演艺产品查询参数
	* @apiParam (请求参数) {ServiceContext} [context] 上下文
	*
	* @apiParam (ActingProductQueryRequstModel) {Long} scenicId 景区ID
	* @apiParam (ActingProductQueryRequstModel) {Long} supplierId 供应商id
	* @apiParam (ActingProductQueryRequstModel) {Long} [actProId] 演艺产品ID (暂为中间表主键)
	*
	*
	* @apiParamExample 请求参数示例
	*   {
	*       "model":{
	*          "scenicId": 12214,
	*          "supplierId": 12214
	*       }
	*       context:{
	*           .........
	*       }
	*   }
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {ArrayList} data 返回区域场次关联AreaScreeingsRelModel对象集合
	*
	* @apiParam (AreaScreeingsRelModel) {Long} id 区域场次关联ID
	* @apiParam (AreaScreeingsRelModel) {Long} actingId 演艺主键id
	* @apiParam (AreaScreeingsRelModel) {Long} areaId 区域主键id
	* @apiParam (AreaScreeingsRelModel) {Long} areaName 区域名称
	* @apiParam (AreaScreeingsRelModel) {Long} screeingsId 场次主键id
	* @apiParam (AreaScreeingsRelModel) {Long} screeingsName 场次名称
	* @apiParam (AreaScreeingsRelModel) {Long} scenicId 景区ID
	* @apiParam (AreaScreeingsRelModel) {Long} supplierId 供应商id
	* 
	* @apiSuccessExample {json} 成功响应数据
	*   {
	*      "errorCode" : 10000,
	*      "errorMsg" : "ok",
	*      "data" : []
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

	Result<ActingProductModel> queryActProInfo(ActingProductQueryRequstModel model, ServiceContext context);

	/**
	 * 根据产品关联演艺ID查询演艺信息详情
	 * @param model
	 * @param context
	 * @return Result<AreaScreeingsRelModel>
	 */
	Result<AreaScreeingsRelModel> queryAreaScreeRelByProAct(ActingProductQueryRequstModel model, ServiceContext context);
}
