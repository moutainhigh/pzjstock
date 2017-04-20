package com.pzj.core.product.area;

import java.util.ArrayList;
import java.util.List;

import com.pzj.core.product.model.area.CreateAreaInfoReqModel;
import com.pzj.core.product.model.area.ModifyAreaInfoReqModel;
import com.pzj.core.product.model.area.ModifyAreaReqModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pzj.core.product.entity.Area;
import com.pzj.core.product.model.area.CreateAreaReqModel;

/**
 * Created by Administrator on 2017-3-21.
 */
public class AreaModelConvert {

    private static final Logger logger = LoggerFactory.getLogger(AreaModelConvert.class);

    public static List<Area> convertToAreas(CreateAreaReqModel model) {
        if (model == null){
            return null;
        }

        List<CreateAreaInfoReqModel> areaInfos = model.getAreaInfos();

        if (areaInfos == null || areaInfos.isEmpty()){
            return null;
        }

        List<Area> areas = new ArrayList<>(areaInfos.size());

        for (CreateAreaInfoReqModel reqModel : areaInfos){
            Area area = new Area();
            area.setName(reqModel.getName());
            area.setCode(reqModel.getNameAbbr());
            areas.add(area);
        }

        return areas;
    }

    public static AreaCommonInfo convertToAreaCommonInfo(CreateAreaReqModel model) {
        if (model == null){
            return null;
        }

        AreaCommonInfo areaCommonInfo = new AreaCommonInfo();
        areaCommonInfo.setTheaterId(model.getTheaterId());
        areaCommonInfo.setSupplierId(model.getSupplierId());
        areaCommonInfo.setSeatType(model.getSeatType());
        areaCommonInfo.setSeatMode(model.getSeatMode());
        areaCommonInfo.setThumb(model.getThumb());

        return areaCommonInfo;
    }

    public static List<Area> convertToAreas(ModifyAreaReqModel model) {
        if (model == null){
            return null;
        }

        List<ModifyAreaInfoReqModel> areaInfos = model.getAreaInfos();

        if (areaInfos == null || areaInfos.isEmpty()){
            return null;
        }

        List<Area> areas = new ArrayList<>(areaInfos.size());

        for (ModifyAreaInfoReqModel reqModel : areaInfos){
            Area area = new Area();
            area.setId(reqModel.getId());
            area.setName(reqModel.getName());
            area.setCode(reqModel.getNameAbbr());
            areas.add(area);
        }

        return areas;
    }

    public static AreaCommonInfo convertToAreaCommonInfo(ModifyAreaReqModel model) {
        if (model == null){
            return null;
        }

        AreaCommonInfo areaCommonInfo = new AreaCommonInfo();
        areaCommonInfo.setTheaterId(model.getTheaterId());
        areaCommonInfo.setThumb(model.getThumb());

        return areaCommonInfo;
    }


    public static AreaCommonInfo convertToAreaCommonInfo(Area area) {
        if (area == null){
            return null;
        }

        AreaCommonInfo areaCommonInfo = new AreaCommonInfo();
        areaCommonInfo.setTheaterId(area.getScenicId());
        areaCommonInfo.setSupplierId(area.getSupplierId());
        areaCommonInfo.setSeatType(area.getSeatType());
        areaCommonInfo.setSeatMode(area.getSeatMode());
        areaCommonInfo.setThumb(area.getThumb());

        return areaCommonInfo;
    }
}
