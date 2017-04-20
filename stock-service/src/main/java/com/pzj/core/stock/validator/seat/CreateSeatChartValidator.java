/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.seat;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.SeatChartModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author Administrator
 * @version $Id: SeatChartValidator.java, v 0.1 2016年8月11日 上午10:54:22 Administrator Exp $
 */
@Component("createSeatChartValidator")
public class CreateSeatChartValidator implements ObjectConverter<SeatChartModel, ServiceContext, Boolean> {

	/** 
	 * @see com.pzj.framework.converter.ObjectConverter#convert(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Boolean convert(SeatChartModel seatChartModel, ServiceContext context) {
		if (Check.NuNObject(seatChartModel)) {
			return Boolean.FALSE;
		}
		String seatMap = seatChartModel.getSeatMaps();
		if (Check.NuNObject(seatChartModel.getScenicId(), seatChartModel.getAreaId(), seatMap)) {
			return Boolean.FALSE;
		}
		if (CommonUtils.checkIntegerIsNull(seatChartModel.getSort(), true)) {
			seatChartModel.setSort(1);
		}

		//校验座位图格式
		String[] rowArr = seatMap.split(";");
		for (String row : rowArr) {
			if (Check.NuNObject(row) || row.indexOf(",") < 0) {
				return Boolean.FALSE;
			}
			String[] lineArr = row.split(",");
			for (int i = 0; i < lineArr.length; i++) {
				if (!"_".equals(lineArr[i])) {
					//                    if (i == 0) {
					//                        if (!CommonUtils.checkSeatRowLegal(lineArr[i])) {
					//                            return Boolean.FALSE;
					//                        }
					//                    } else {
					//                        if (!lineArr[i].matches("^\\d+$")) {
					//                            return Boolean.FALSE;
					//                        }
					//                    }
					if (!CommonUtils.checkSeatRowLegal(lineArr[i]) && !lineArr[i].matches("^\\d+$")) {
						return Boolean.FALSE;
					}
				}
			}
		}
		return Boolean.TRUE;
	}

}
