package com.pzj.core.product.screeings;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.model.screeings.CreateScreeningsReqModel;
import com.pzj.core.product.model.screeings.ModifyScreeningReqModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017-3-20.
 */
public class ScreeningModelConvert {

    private static final Logger logger = LoggerFactory.getLogger(ScreeningModelConvert.class);

    public static Screeings convert(CreateScreeningsReqModel model) {
        if (model == null) {
            return null;
        }

        Screeings screenings = new Screeings();
        screenings.setName(model.getName());
        screenings.setCode(model.getNameAbbr());
        screenings.setScenicId(model.getTheaterId());
        screenings.setSupplierId(model.getSupplierId());

        setTimeOfScreening(screenings, model.getBeginTime(), model.getEndTime());

        return screenings;
    }

    private static void setTimeOfScreening(Screeings screenings, String beginTime, String endTime) {
        if (beginTime != null || endTime != null) {
            SimpleDateFormat s_sdf = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat t_sdf = new SimpleDateFormat("HHmmss");
            if (beginTime != null) {
                screenings.setStartTime(convertDateToInteger(s_sdf, t_sdf, beginTime));
            }
            if (endTime != null) {
                screenings.setEndTime(convertDateToInteger(s_sdf, t_sdf, endTime));
            }
        }
    }

    private static Integer convertDateToInteger(SimpleDateFormat s_sdf, SimpleDateFormat t_sdf, String dateString){
        try {
            Date date = s_sdf.parse(dateString);
            return convertDateToInteger(t_sdf, date);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            throw new StockException(e);
        }
    }

    private static Integer convertDateToInteger(SimpleDateFormat sdf, Date date){
        String format = sdf.format(date);
        if (format != null){
            return Integer.valueOf(format);
        }
        return null;
    }

    public static Screeings convert(ModifyScreeningReqModel model) {
        if (model == null) {
            return null;
        }

        Screeings screenings = new Screeings();
        screenings.setId(model.getId());
        screenings.setName(model.getName());
        screenings.setCode(model.getNameAbbr());

        setTimeOfScreening(screenings, model.getBeginTime(), model.getEndTime());

        return screenings;
    }
}
