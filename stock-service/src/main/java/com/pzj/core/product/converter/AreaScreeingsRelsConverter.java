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
import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.common.utils.MFBeanCopier;
import com.pzj.core.product.entity.AreaScreeingsRel;
import com.pzj.core.product.model.area.AreaScreeingsRelModel;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 演绎区域转换工具
 * 
 * @author dongchunfu
 * @version $Id: AreaConverter.java, v 0.1 2016年8月1日 上午10:57:26 dongchunfu Exp $
 */
@Component(value = "areaScreeingsRelsConverter")
public class AreaScreeingsRelsConverter implements
		ObjectConverter<ArrayList<AreaScreeingsRel>, ServiceContext, ArrayList<AreaScreeingsRelModel>> {

	private static final Logger logger = LoggerFactory.getLogger(AreaScreeingsRelsConverter.class);

	/** 底层实体转换为VO实体 */
	@Override
	public ArrayList<AreaScreeingsRelModel> convert(ArrayList<AreaScreeingsRel> list, ServiceContext context) {
		if (CommonUtils.checkCollectionIsNull(list)) {
			return null;
		}
		ArrayList<AreaScreeingsRelModel> vos = new ArrayList<AreaScreeingsRelModel>(list.size());
		for (AreaScreeingsRel rel : list) {
			AreaScreeingsRelModel model = new AreaScreeingsRelModel();
			try {
				MFBeanCopier.copyProperties(model, rel);
			} catch (Exception e) {
				logger.error(" copy AreaScreeingsRel properties to AreaScreeingsRelModel error , context:{}", context,
						e);
				throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
			}
			vos.add(model);
		}
		return vos;
	}
}
