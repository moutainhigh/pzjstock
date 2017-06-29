package com.pzj.core.product.seatchar;

import com.pzj.core.product.entity.SeatChar;
import com.pzj.core.product.model.seat.SeatChartRespModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-5-23.
 */
public class SeatCharModelConvert {
    public static List<SeatChartRespModel> convertToSeatChartRespModel(List<SeatChar> seatChars){
        if (seatChars == null || seatChars.isEmpty()){
            return null;
        }

        List<SeatChartRespModel> seatChartRespModels = new ArrayList<>(seatChars.size());
        for (SeatChar seatChar : seatChars){
            seatChartRespModels.add(convertToSeatChartRespModel(seatChar));
        }

        return seatChartRespModels;
    }

    public static SeatChartRespModel convertToSeatChartRespModel(SeatChar seatChar){
        if (seatChar == null){
            return null;
        }

        SeatChartRespModel seatChartRespModel = new SeatChartRespModel();
        seatChartRespModel.setId(seatChar.getId());
        seatChartRespModel.setAreaId(seatChar.getAreaId());
        seatChartRespModel.setAbscissa(seatChar.getAbscissa());
        seatChartRespModel.setOrdinate(seatChar.getOrdinate());
        seatChartRespModel.setNameType(seatChar.getNameType());
        seatChartRespModel.setLineName(seatChar.getLineName());
        seatChartRespModel.setColumnName(seatChar.getColumnName());
        seatChartRespModel.setSeatName(seatChar.getSeatName());
        seatChartRespModel.setSeatType(seatChar.getType());
        return seatChartRespModel;
    }
}
