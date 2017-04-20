package com.pzj.core.product.screeings;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.entity.Area;
import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.model.area.AreaModel;
import com.pzj.core.product.model.screeings.ScreeingAreaReqModel;
import com.pzj.core.product.model.screeings.ScreeingAreaRespModel;
import com.pzj.core.product.model.screeings.ScreeingsModel;
import com.pzj.core.product.read.AreaReadMapper;
import com.pzj.core.product.read.ScreeingsReadMapper;

@Component("screeingAreaQueryEngine")
public class ScreeingAreaQueryEngine {

	@Resource(name = "screeingsReadMapper")
	private ScreeingsReadMapper screeingsReadMapper;
	@Resource(name = "areaReadMapper")
	private AreaReadMapper areaReadMapper;

	public ScreeingAreaRespModel queryScreeingAreaBaseInfo(ScreeingAreaReqModel reqModel) {
		ScreeingAreaRespModel screeingAreaResp = new ScreeingAreaRespModel();
		if (!CommonUtils.checkCollectionIsNull(reqModel.getScreeingIds())) {
			List<Screeings> screeings = screeingsReadMapper.queryScreeingByIds(reqModel.getScreeingIds());
			screeingAreaResp.setScreeings(initScreeings(screeings));
		}
		if (!CommonUtils.checkCollectionIsNull(reqModel.getAreaIds())) {
			List<Area> areas = areaReadMapper.queryAreaByIds(reqModel.getAreaIds());
			screeingAreaResp.setAreas(initAreas(areas));
		}
		return screeingAreaResp;
	}

	private List<ScreeingsModel> initScreeings(List<Screeings> screeings) {
		if (CommonUtils.checkCollectionIsNull(screeings)) {
			return null;
		}
		List<ScreeingsModel> screeingModels = new ArrayList<ScreeingsModel>();
		ScreeingsModel screeingsModel = null;
		for (Screeings screeing : screeings) {
			screeingsModel = new ScreeingsModel();
			screeingsModel.setId(screeing.getId());
			screeingsModel.setName(screeing.getName());
			screeingsModel.setScenicId(screeing.getScenicId());
			screeingModels.add(screeingsModel);
		}
		return screeingModels;
	}

	private List<AreaModel> initAreas(List<Area> areas) {
		if (CommonUtils.checkCollectionIsNull(areas)) {
			return null;
		}
		List<AreaModel> areaModels = new ArrayList<AreaModel>();
		AreaModel areaModel = null;
		for (Area area : areas) {
			areaModel = new AreaModel();
			areaModel.setId(area.getId());
			areaModel.setName(area.getName());
			areaModel.setScenicId(area.getScenicId());
			areaModels.add(areaModel);
		}
		return areaModels;
	}
}
