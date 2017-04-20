/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.area;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.commons.utils.CheckUtils;
import com.pzj.core.common.exception.StockException;
import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.entity.Area;
import com.pzj.core.product.entity.AreaQuery;
import com.pzj.core.product.entity.PageEntity;
import com.pzj.core.product.model.area.AreaModel;
import com.pzj.core.product.model.area.AreaQueryReqModel;
import com.pzj.core.product.model.area.AreaQueryRequestModel;
import com.pzj.core.product.model.area.TheaterAreaDetailRespModel;
import com.pzj.core.product.model.area.TheaterAreaReqModel;
import com.pzj.core.product.model.area.TheaterAreaRespModel;
import com.pzj.core.product.read.AreaReadMapper;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.entity.QueryResult;

/**
 * 根据参数查询区域信息
 * 
 * @author dongchunfu
 * @version $Id: AreaQueryByParamEngine.java, v 0.1 2016年8月1日 上午10:33:15 dongchunfu Exp $
 */
@Component("areaQueryByParamEngine")
public class AreaQueryByParamEngine {

	private static final Logger logger = LoggerFactory.getLogger(AreaQueryByParamEngine.class);

	@Autowired
	private AreaReadMapper areaReadMapper;

	public ArrayList<Area> queryAreaByParam(AreaQueryRequestModel model, ServiceContext context) {
		AreaQuery areaQuery = new AreaQuery();
		try {
			PropertyUtils.copyProperties(areaQuery, model);
			Area area = new Area();
			PropertyUtils.copyProperties(area, model);
		} catch (Exception e) {
			logger.error(" copy area model properties to area error ", e);
			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}
		return areaReadMapper.selectAreasByParam(areaQuery);
	}

	public List<Area> queryScenicBySupplierId(Long supplierId, PageEntity page) {
		try {
			List<Area> scenics = areaReadMapper.queryScenicBySupplierId(supplierId, page);
			return scenics;
		} catch (Exception e) {
			logger.error("query area error", e);
			throw new StockException(StockExceptionCode.QUERY_AREA_ERR_MSG, e);
		}

	}

	public QueryResult<TheaterAreaRespModel> queryScenicAreas(AreaQueryReqModel areaQueryReqModel,
			ServiceContext serviceContext) {
		Long supplierId = areaQueryReqModel.getSupplierId();
		PageEntity page = new PageEntity(areaQueryReqModel.getCurrentPage(), areaQueryReqModel.getPageSize());
		try {
			Integer count = areaReadMapper.queryCountScenicBySupplierId(supplierId);
			if (count == null || count <= 0) {
				return null;
			}
			List<Area> scenics = queryScenicBySupplierId(supplierId, page);
			List<Long> scenicIds = new ArrayList<Long>();
			for (Area area : scenics) {
				scenicIds.add(area.getScenicId());
			}
			AreaQuery areaQuery = new AreaQuery();
			areaQuery.setSupplierId(supplierId);
			areaQuery.setScenicIds(scenicIds);
			ArrayList<Area> areas = areaReadMapper.selectAreasByParam(areaQuery);

			List<AreaModel> areaModels = convcerAreaModels(areas);
			List<TheaterAreaRespModel> areaRespModels = convertTheaterArea(areaModels, supplierId);
			QueryResult<TheaterAreaRespModel> queryResult = new QueryResult<>(areaQueryReqModel.getCurrentPage(),
					areaQueryReqModel.getPageSize());
			queryResult.setRecords(areaRespModels);
			queryResult.setTotal(count);
			return queryResult;
		} catch (StockException e) {
			throw new StockException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("query scenic areas error", e);
			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}
	}

	private List<AreaModel> convcerAreaModels(ArrayList<Area> areas) {
		List<AreaModel> areaModels = new ArrayList<AreaModel>();
		for (Area area : areas) {
			AreaModel model = new AreaModel();
			model.setId(area.getId());
			model.setName(area.getName());
			model.setCode(area.getCode());
			model.setCreateTime(area.getCreateTime());
			model.setScenicId(area.getScenicId());
			areaModels.add(model);
		}
		return areaModels;
	}

	private List<TheaterAreaRespModel> convertTheaterArea(List<AreaModel> areaModels, Long supplierId) {

		Map<Long, List<AreaModel>> theaterMap = new HashMap<Long, List<AreaModel>>();
		for (AreaModel area : areaModels) {
			List<AreaModel> mapArea = new ArrayList<>();
			if (CheckUtils.isNull(theaterMap.get(area.getScenicId()))) {
				mapArea.add(area);
				theaterMap.put(area.getScenicId(), mapArea);
			} else {
				mapArea = theaterMap.get(area.getScenicId());
				mapArea.add(area);
				theaterMap.put(area.getScenicId(), mapArea);
			}
		}
		List<TheaterAreaRespModel> areaRespModels = new ArrayList<>();
		Iterator<Long> areaIt = theaterMap.keySet().iterator();
		while (areaIt.hasNext()) {
			Long scenicId = areaIt.next();
			TheaterAreaRespModel theaterAreaRespModel = new TheaterAreaRespModel();
			theaterAreaRespModel.setTheaterId(scenicId);
			theaterAreaRespModel.setSupplierId(supplierId);
			theaterAreaRespModel.setAreas(theaterMap.get(scenicId));
			areaRespModels.add(theaterAreaRespModel);
		}
		return areaRespModels;
	}

	public TheaterAreaDetailRespModel queryAreaDetail(TheaterAreaReqModel reqModel) {
		List<Area> areas = areaReadMapper.queryAreaDetail(reqModel.getSupplierId(), reqModel.getTheaterId());
		if (CommonUtils.checkCollectionIsNull(areas)) {
			return null;
		}

		List<AreaModel> areaModels = new ArrayList<AreaModel>();
		AreaModel areaModel = null;
		for (Area area : areas) {
			areaModel = new AreaModel();
			areaModel.setId(area.getId());
			areaModel.setScenicId(area.getScenicId());
			areaModel.setName(area.getName());
			areaModel.setCode(area.getCode());
			areaModels.add(areaModel);
		}
		Area area = areas.get(0);
		TheaterAreaDetailRespModel theaterAreaDetail = new TheaterAreaDetailRespModel();
		theaterAreaDetail.setSupplierId(area.getSupplierId());
		theaterAreaDetail.setTheaterId(area.getScenicId());
		theaterAreaDetail.setSeatChartType(area.getSeatType());
		theaterAreaDetail.setSeatChartIcon(area.getThumb());
		theaterAreaDetail.setFetchSeatModel(area.getSeatMode());
		theaterAreaDetail.setAreas(areaModels);
		return theaterAreaDetail;
	}
}