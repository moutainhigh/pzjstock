/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.converter;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.common.utils.MFBeanCopier;
import com.pzj.core.product.entity.Acting;
import com.pzj.core.product.model.ActingModel;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * @author dongchunfu
 * @version $Id: ActingConverter.java, v 0.1 2016年8月1日 下午3:14:54 dongchunfu Exp $
 */
@Component(value = "actingsConverter")
public class ActingsConverter implements ObjectConverter<ArrayList<Acting>, ServiceContext, ArrayList<ActingModel>> {

	private static final Logger logger = LoggerFactory.getLogger(ActingsConverter.class);

	/** 
	 * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
	 */
	@Override
	public ArrayList<ActingModel> convert(ArrayList<Acting> list, ServiceContext context) {
		if (null == list || list.size() == 0)
			return null;
		ArrayList<ActingModel> vos = new ArrayList<ActingModel>(list.size());
		for (Acting acting : list) {
			ActingModel model = new ActingModel();
			try {
				MFBeanCopier.copyProperties(model, acting);

			} catch (Exception e) {
				logger.error("copy Acting properties to ActingModel error,acting:{}.", acting, e);
				throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
			}
			vos.add(model);
		}
		return vos;
	}

}
