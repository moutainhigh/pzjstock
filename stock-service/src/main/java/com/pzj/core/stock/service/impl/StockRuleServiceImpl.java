/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.common.exception.ParameterErrorException;
import com.pzj.core.common.exception.StockException;
import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.core.stock.model.query.StockParam;
import com.pzj.core.stock.model.result.JudgeRelationResult;
import com.pzj.core.stock.rulequery.StockRuleNameQueryByParamEngine;
import com.pzj.core.stock.ruleupdate.CreateStockRuleEngine;
import com.pzj.core.stock.ruleupdate.DeleteStockRuleEngine;
import com.pzj.core.stock.ruleupdate.StockRuleNameUpdateEngine;
import com.pzj.core.stock.ruleupdate.StockRuleUpdateEngine;
import com.pzj.core.stock.ruleupdate.StockRuleUpdateStateEngine;
import com.pzj.core.stock.ruleupdate.StockRuleUpdateYetBindEngine;
import com.pzj.core.stock.service.ISkuStockService;
import com.pzj.core.stock.service.StockRuleService;
import com.pzj.core.stock.validator.rule.CreateStockRuleValidator;
import com.pzj.core.stock.validator.rule.DeleteStockRuleValidator;
import com.pzj.core.stock.validator.rule.UpdateStockRuleNameValidator;
import com.pzj.core.stock.validator.rule.UpdateStockRuleNotBindValidator;
import com.pzj.core.stock.validator.rule.UpdateStockRuleStateValidator;
import com.pzj.core.stock.validator.rule.UpdateStockRuleValidator;
import com.pzj.core.stock.validator.rule.UpdateStockRuleYetBindValidator;
import com.pzj.core.stock.validator.stock.DelFakeStockRuleValidator;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.exception.ServiceException;

/**
 * 
 * @author Administrator
 * @version $Id: StockRuleServiceImpl.java, v 0.1 2016年10月24日 上午10:01:25 Administrator Exp $
 */
@Service(value = "stockRuleService")
public class StockRuleServiceImpl implements StockRuleService {

	private static final Logger logger = LoggerFactory.getLogger(StockRuleServiceImpl.class);

	@Resource(name = "skuStockService")
	private ISkuStockService skuStockService;

	@Resource(name = "createStockRuleEngine")
	private CreateStockRuleEngine createStockRuleEngine;
	@Resource(name = "stockRuleUpdateStateEngine")
	private StockRuleUpdateStateEngine stockRuleUpdateStateEngine;
	@Resource(name = "stockRuleUpdateYetBindEngine")
	private StockRuleUpdateYetBindEngine stockRuleUpdateYetBindEngine;
	@Resource(name = "stockRuleUpdateEngine")
	private StockRuleUpdateEngine stockRuleUpdateEngine;
	@Resource(name = "deleteStockRuleEngine")
	private DeleteStockRuleEngine deleteStockRuleEngine;

	@Resource(name = "createStockRuleValidator")
	private CreateStockRuleValidator createStockRuleValidator;
	@Resource(name = "delFakeStockRuleValidator")
	private DelFakeStockRuleValidator delFakeStockRuleValidator;
	@Resource(name = "updateStockRuleStateValidator")
	private UpdateStockRuleStateValidator updateStockRuleStateValidator;
	@Resource(name = "updateStockRuleValidator")
	private UpdateStockRuleValidator updateStockRuleValidator;
	@Resource(name = "updateStockRuleYetBindValidator")
	private UpdateStockRuleYetBindValidator updateStockRuleYetBindValidator;
	@Resource(name = "updateStockRuleNotBindValidator")
	private UpdateStockRuleNotBindValidator updateStockRuleNotBindValidator;
	@Resource(name = "deleteStockRuleValidator")
	private DeleteStockRuleValidator deleteStockRuleValidator;
	@Resource(name = "stockRuleNameQueryByParamEngine")
	private StockRuleNameQueryByParamEngine stockRuleNameQueryByParamEngine;
	@Resource(name = "updateStockRuleNameValidator")
	private UpdateStockRuleNameValidator updateStockRuleNameValidator;
	@Resource(name = "stockRuleNameUpdateEngine")
	private StockRuleNameUpdateEngine stockRuleNameUpdateEngine;

	/** 
	 * @see com.pzj.stock.service.stock.StockRuleService#createStockRule(com.pzj.core.stock.model.StockRuleModel)
	 */
	@Override
	public Result<Long> createStockRule(StockRuleModel model, ServiceContext context) {
		Result<Long> result = new Result<Long>();
		Boolean success = createStockRuleValidator.convert(model, context);
		if (!success) {
			logger.error("create stock rule Illegal params ,request:{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("creating stock rule ,request:{},context:{}.", model, context);
		}

		try {
			Long id = createStockRuleEngine.createStockRule(model, context);
			result.setData(id);
		} catch (Throwable e) {
			logger.error("create stock rule fail,request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			throw t instanceof ServiceException ? (ServiceException) t : new StockException(
			//					StockExceptionCode.STOCK_ERR_MSG, t);

		}
		if (logger.isDebugEnabled()) {
			logger.debug("create stock rule success ,request:{},context:{},result:{}.", model, context,
					JSONConverter.toJson(result));
		}

		return result;
	}

	/** 
	 * @see com.pzj.stock.service.stock.StockRuleService#updateStockRule(com.pzj.core.stock.model.StockRuleModel, java.lang.Boolean)
	 */
	@Override
	public Result<Integer> updateStockRule(StockRuleModel model, ServiceContext context) {
		Result<Integer> result = new Result<Integer>();
		//1.参数验证，规则ID不允许为空
		Boolean success = updateStockRuleValidator.convert(model, context);
		if (!success) {
			logger.error("update stock rule error ,request:{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("update stock rule request:{},context:{}" + model, context);
		}

		//1.调用产品接口，获取当前库存规则是否与产品或产品集具有绑定关系
		Result<JudgeRelationResult> relation;
		try {
			StockParam stockParam = new StockParam();
			stockParam.setStockRuleId(model.getId());
			relation = skuStockService.judgeValidSkuByStockRuleId(stockParam);
		} catch (Throwable e) {
			logger.error("call sku stock service error ,request:{},context:{}.", model, context, e);
			CommonUtils.setResultErr(StockExceptionCode.INVOKE_OTHER_SERVICE_ERR_CODE,
					StockExceptionCode.INVOKE_OTHER_SERVICE_ERR_MSG, result);
			return result;

			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, t);
		}

		try {
			Integer count = 0;
			JudgeRelationResult judgeRelation = relation.getData();
			if (!CommonUtils.checkObjectIsNull(judgeRelation)) {
				Boolean have = judgeRelation.getIsHaveRelation();
				//2.根据关系选择调用更新方法
				if (have) {
					//已绑定产品的修改规则
					count = updateStockRuleYetBind(model, context);

				} else {
					//未绑定产品的修改规则 
					count = updateStockRuleNoBind(model, context);
				}
			}
			result.setData(count);
		} catch (Throwable e) {
			logger.error("invoke update stock rule error ,request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("update stock rule success result:{},context:{}", result, context);
		}
		return result;
	}

	//已绑定产品的修改规则
	private Integer updateStockRuleYetBind(StockRuleModel model, ServiceContext context) {

		//验证参数是否符合要求
		if (!updateStockRuleYetBindValidator.convert(model, context)) {
			logger.error("illegal param ,request:{},context:{}.", model, context);
			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" updating yet bind stock rule , request:{},context:{}.", model, context);
		}

		try {
			Integer count = stockRuleUpdateYetBindEngine.updateStockRuleYetBind(model, context);

			if (logger.isDebugEnabled()) {
				logger.debug(" update yet bind stock rule success, result:{}.", count);
			}
			return count;
		} catch (Throwable t) {

			logger.error("update yet bing stock rule fail :", t);
			throw t instanceof ServiceException ? (ServiceException) t : new StockException(
					StockExceptionCode.STOCK_ERR_MSG, t);

		}
	}

	/** 库存规则未绑定产品的修改规则  */
	private Integer updateStockRuleNoBind(StockRuleModel model, ServiceContext context) {
		//验证参数是否符合要求
		if (!updateStockRuleNotBindValidator.convert(model, context)) {
			logger.error("illegal param ,request:{},context:{}.", model, context);
			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("update not bind stock rule ,request：{},context:{}.", model, context);
		}
		try {
			Integer amount = stockRuleUpdateEngine.updateStockRule(model, context);

			if (logger.isDebugEnabled()) {
				logger.debug("update not bind stock rule success ,result:{}.", amount);
			}

			return amount;

		} catch (Throwable t) {
			logger.error("update not bing stock rule fail :", t);

			throw t instanceof ServiceException ? (ServiceException) t : new StockException(
					StockExceptionCode.STOCK_ERR_MSG, t);

		}
	}

	/** 
	 * @see com.pzj.stock.service.stock.StockRuleService#updateStockRuleState(com.pzj.core.stock.model.StockRuleModel)
	 */
	@Override
	@Deprecated
	public Result<Integer> updateStockRuleState(StockRuleModel model, ServiceContext context) {

		//验证参数是否符合要求
		Boolean success = updateStockRuleStateValidator.convert(model, context);
		if (!success) {
			logger.error("update stock rule state illegal param , request:{},context:{}.", model, context);
			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("updating stock rule state , request：{},context:{}", model, context);
		}

		try {

			int amount = stockRuleUpdateStateEngine.updateStockRuleState(model, context);

			if (logger.isDebugEnabled()) {
				logger.debug("update stock rule state success, request：{},context:{},response:{}.", model, context,
						amount);
			}

			return new Result<Integer>(amount);
		} catch (Throwable t) {

			logger.error("update stock rule state fail ,request:{},context:{}.", model, context, t);

			throw t instanceof ServiceException ? (ServiceException) t : new StockException(
					StockExceptionCode.STOCK_ERR_MSG, t);
		}

	}

	/** 
	 * @see com.pzj.core.stock.service.StockRuleService#fakeDeleteStockRule(com.pzj.core.stock.model.StockRuleModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Integer> fakeDeleteStockRule(StockRuleModel model, ServiceContext context) {
		Result<Integer> result = new Result<Integer>();
		//验证参数是否符合要求
		Boolean success = deleteStockRuleValidator.convert(model, context);
		if (!success) {
			logger.error("update stock rule state illegal param , request:{},context:{}.", model, context);
			CommonUtils.setParamErr(result);
			return result;
			//			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("deleting stock rule , request：{},context:{}", model, context);
		}

		try {
			Integer amount = deleteStockRuleEngine.fakeDeletStockRule(model, context);
			result.setData(amount);
		} catch (Throwable e) {
			logger.error("delete stock rule fail ,request:{},context:{}.", model, context, e);
			CommonUtils.convertException(e, result);

			//			throw t instanceof ServiceException ? (ServiceException) t : new StockException(
			//					StockExceptionCode.STOCK_ERR_MSG, t);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("delete stock rule success, result:{}.", result);
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.StockRuleService#updateStockRuleName(java.lang.String, java.lang.String)
	 */
	@Override
	public Result<Integer> updateStockRuleName(StockRuleModel model, ServiceContext context) {
		Result<Integer> result = new Result<Integer>();
		try {
			// 验证参数是否符合要求
			ParamModel paramModel = updateStockRuleNameValidator.convert(model, context);
			if (!paramModel.paramIsOk()) {
				logger.error("update StockRuleName fail, Illegal params. request: {}, context: {}.", model, context);
				CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
				return result;
				//				throw new ParameterErrorException(paramModel.getParamErrorMsg());
			}
			if (logger.isDebugEnabled()) {// 方法入口打印日志
				logger.debug("update stock rule name success, request: {}, context: {}.", model, context);
			}
			// 查看是否改动了name中的skuid部分
			stockRuleNameQueryByParamEngine.queryStockRuleNameById(model.getId(), model.getName());
			// 查看表中是否已经存在值为name的记录
			stockRuleNameQueryByParamEngine.queryStockRuleNameRepeat(model.getId(), model.getName());
			// 根据id更新name
			int amount = stockRuleNameUpdateEngine.updateStockRuleName(model.getId(), model.getName());
			result.setData(amount);

		} catch (Throwable e) {
			logger.error("update StockRuleName fail ,request:{},context:{}.", model.getId(), model.getName(), context,
					e);
			CommonUtils.convertException(e, result);
			//			throw t;
		}
		if (logger.isDebugEnabled()) {
			// 方法出口打印日志
			logger.debug("update stock rule name success, result：{}.", JSONConverter.toJson(result));
		}

		return result;
	}

}
