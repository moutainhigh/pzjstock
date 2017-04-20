/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.seat;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.CheckSeatsOptionalModel;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 
 * @author Administrator
 * @version $Id: CheckSeatsOptionalValidator.java, v 0.1 2016年10月26日 上午11:53:23 Administrator Exp $
 */
@Component("checkSeatsOptionalValidator")
public class CheckSeatsOptionalValidator implements
		ObjectConverter<CheckSeatsOptionalModel, ServiceContext, ParamModel> {

	/** 
	 * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
	 */
	@Override
	public ParamModel convert(CheckSeatsOptionalModel model, ServiceContext context) {
		ParamModel paramModel = ParamModel.getInstance();
		if (CommonUtils.checkObjectIsNull(model)) {
			paramModel.setErrorModel("检查座位是否可选传入CheckSeatsOptionalModel对象为空!");
			return paramModel;
		}
		if (CommonUtils.checkLongIsNull(model.getStockId(), true)) {
			paramModel.setErrorModel("检查座位是否可选传入stockId库存ID为空!");
			return paramModel;
		}

		//过滤重复座位
		model.setSeats(CommonUtils.filterListRepeat(model.getSeats()));

		List<String> seats = model.getSeats();

		if (CommonUtils.checkCollectionIsNull(seats)) {
			paramModel.setErrorModel("检查座位是否可选传入seats座位集合为空!");
			return paramModel;
		}

		//判断座位格式是否合法
		if (!CommonUtils.checkSeatIfLegal(seats)) {
			paramModel.setErrorModel("检查座位是否可选传入seats座位集合不合法!");
			return paramModel;
		}

		return paramModel;
	}

}
