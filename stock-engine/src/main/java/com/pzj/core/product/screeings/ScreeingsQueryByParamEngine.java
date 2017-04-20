/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.screeings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.ParameterErrorException;
import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.entity.PageEntity;
import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.model.screeings.ScreeingsModel;
import com.pzj.core.product.model.screeings.ScreeingsQueryRequestModel;
import com.pzj.core.product.model.screeings.TheaterScreeingReqModel;
import com.pzj.core.product.model.screeings.TheaterScreeingRespModel;
import com.pzj.core.product.read.ScreeingsReadMapper;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.entity.QueryResult;

/**
 * 
 * 
 * @author dongchunfu
 * @version $Id: ScreeingsQueryByParamEngine.java, v 0.1 2016年8月8日 下午4:42:43 dongchunfu Exp $
 */
@Component("screeingsQueryByParamEngine")
public class ScreeingsQueryByParamEngine {

	private static final Logger logger = LoggerFactory.getLogger(ScreeingsQueryByParamEngine.class);

	@Autowired
	private ScreeingsReadMapper screeingsReadMapper;

	/**
	 * 查询剧场场次分页对象
	 * @param reqModel
	 * @return
	 */
	public QueryResult<TheaterScreeingRespModel> queryTheaterScreeings(TheaterScreeingReqModel reqModel) {
		Long supplierId = reqModel.getSupplierId();
		PageEntity pageEntity = new PageEntity(reqModel.getCurrentPage(), reqModel.getPageSize());

		List<Screeings> screeings = screeingsReadMapper.queryTheaterScreeingByPage(pageEntity, supplierId);
		if (screeings == null || screeings.isEmpty()) {
			return null;
		}
		int total = screeingsReadMapper.countTheaterScreeing(supplierId);
		List<TheaterScreeingRespModel> theaterScreeings = convertTheaterScreeings(screeings);

		QueryResult<TheaterScreeingRespModel> queryResult = new QueryResult<TheaterScreeingRespModel>(
				pageEntity.getCurrentPage(), pageEntity.getPageSize());
		queryResult.setRecords(theaterScreeings);
		queryResult.setTotal(total);
		return queryResult;

	}

	/**
	 * 查询场次信息通过场次参数
	 * @param model
	 * @param context
	 * @return
	 */
	public ArrayList<Screeings> queryScreeingsByParam(ScreeingsQueryRequestModel model, ServiceContext context) {

		Screeings screeings = null;
		try {
			screeings = convertRequest(model);
		} catch (Throwable t) {
			logger.error(" set Screeings startTime error,context:{}. ", context, t);
			throw new ParameterErrorException(StockExceptionCode.PARAM_ERR_MSG, t);
		}

		return screeingsReadMapper.selectScreeingsesByParam(screeings);
	}

	/**
	 * 查询供应商下所有场次集合
	 * @param supplierId
	 * @return List<Screeings>
	 */
	public List<Screeings> queryUserScreeings(Long supplierId) {
		return screeingsReadMapper.queryUserOfScreeing(supplierId);
	}

	/**
	 * 查询场次预订率分页列表
	 * @param pageEntity
	 * @param scenicIds
	 * @param screeingIds
	 * @return
	 */
	public List<Screeings> queryScreeingOrders(PageEntity pageEntity, Set<Long> scenicIds, List<Long> screeingIds) {
		return screeingsReadMapper.queryScreeingOrders(pageEntity, scenicIds, screeingIds);
	}

	private Screeings convertRequest(ScreeingsQueryRequestModel model) {
		Screeings screeings = new Screeings();
		screeings.setCode(model.getCode());
		screeings.setName(model.getName());
		screeings.setScenicId(model.getScenicId());
		String startTime = model.getStartTime();
		if (null != startTime && !"".equals(startTime)) {
			screeings.setStartTime(Integer.valueOf(startTime));
		}
		return screeings;

	}

	private List<TheaterScreeingRespModel> convertTheaterScreeings(List<Screeings> screeings) {
		Map<Long, List<ScreeingsModel>> screeingEntry = new HashMap<Long, List<ScreeingsModel>>();
		List<ScreeingsModel> screeingModels = null;
		ScreeingsModel screeingModel = null;
		Long key = null, supplierId = screeings.get(0).getSupplierId();
		String sTime = null, eTime = null;
		for (Screeings screeing : screeings) {
			key = screeing.getScenicId();
			sTime = CommonUtils.timeConvert(screeing.getStartTime());
			eTime = CommonUtils.timeConvert(screeing.getEndTime());
			screeingModel = new ScreeingsModel();
			screeingModel.setSupplierId(supplierId);
			screeingModel.setId(screeing.getId());
			screeingModel.setScenicId(key);
			screeingModel.setName(screeing.getName());
			screeingModel.setState(screeing.getState());
			screeingModel.setCode(screeing.getCode());
			screeingModel.setStartTime(sTime);
			screeingModel.setEndTime(eTime);
			if (screeingEntry.containsKey(key)) {
				screeingEntry.get(key).add(screeingModel);
			} else {
				screeingModels = new ArrayList<ScreeingsModel>();
				screeingModels.add(screeingModel);
				screeingEntry.put(key, screeingModels);
			}
		}

		List<TheaterScreeingRespModel> theaterScreeings = new ArrayList<TheaterScreeingRespModel>();
		TheaterScreeingRespModel theaterScreeing = null;
		Iterator<Long> itera = screeingEntry.keySet().iterator();
		while (itera.hasNext()) {
			key = itera.next();
			screeingModels = screeingEntry.get(key);
			theaterScreeing = new TheaterScreeingRespModel();
			theaterScreeing.setSupplierId(supplierId);
			theaterScreeing.setTheaterId(key);
			theaterScreeing.setScreeings(screeingModels);
			theaterScreeings.add(theaterScreeing);
		}
		return theaterScreeings;
	}

}