/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.screeings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.model.screeings.ScreeingsModel;
import com.pzj.core.product.read.ScreeingsReadMapper;

/**
 * 
 * 
 * @author dongchunfu
 * @version $Id: ScreeingsQueryByIdEngine.java, v 0.1 2016年8月8日 下午4:42:38 dongchunfu Exp $
 */
@Component("screeingsQueryByIdEngine")
public class ScreeingsQueryByIdEngine {
	@Autowired
	private ScreeingsReadMapper screeingsReadMapper;

	public Screeings queryScreeingsById(Long screeingsId) {
		return screeingsReadMapper.selectScreeingsById(screeingsId);
	}

	public ScreeingsModel queryScreeingDetail(Long screeingId) {
		Screeings screeing = queryScreeingsById(screeingId);
		if (screeing == null) {
			return null;
		}
		ScreeingsModel screeingsModel = new ScreeingsModel();
		screeingsModel.setId(screeing.getId());
		screeingsModel.setCode(screeing.getCode());
		screeingsModel.setName(screeing.getName());
		screeingsModel.setSupplierId(screeing.getSupplierId());
		screeingsModel.setScenicId(screeing.getScenicId());
		screeingsModel.setStartTime(CommonUtils.timeConvert(screeing.getStartTime()));
		screeingsModel.setEndTime(CommonUtils.timeConvert(screeing.getEndTime()));
		return screeingsModel;
	}

}