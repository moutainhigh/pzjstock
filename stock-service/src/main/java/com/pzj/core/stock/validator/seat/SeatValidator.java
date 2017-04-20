/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.validator.seat;

import org.springframework.stereotype.Component;

import com.pzj.core.stock.model.SeatModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author Administrator
 * @version $Id: SeatValidator.java, v 0.1 2016年8月4日 下午5:15:47 Administrator Exp $
 */
@Component("seatValidator")
public class SeatValidator implements ObjectConverter<SeatModel, ServiceContext, Boolean> {

    @Override
    public Boolean convert(SeatModel seatModel, ServiceContext context) {
        if (Check.NuNObject(seatModel) || Check.NuNObject(seatModel.getSeatChartId())) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
