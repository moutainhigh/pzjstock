/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.acting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.entity.Acting;
import com.pzj.core.product.entity.Area;
import com.pzj.core.product.entity.AreaScreeingsRel;
import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.enums.ActingStateEnum;
import com.pzj.core.product.model.AddActingModel;
import com.pzj.core.product.model.AreaModel;
import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.core.product.read.ActingReadMapper;
import com.pzj.core.product.read.AreaReadMapper;
import com.pzj.core.product.read.ScreeingsReadMapper;
import com.pzj.core.product.write.ActingWriteMapper;
import com.pzj.core.product.write.AreaScreeingsRelWriteMapper;
import com.pzj.core.product.write.AreaWriteMapper;
import com.pzj.core.product.write.ScreeingsWriteMapper;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author Administrator
 * @version $Id: AddActingEngine.java, v 0.1 2016年9月1日 下午5:36:51 Administrator Exp $
 */
@Component("addActingEngine")
public class AddActingEngine {
    @Autowired
    private ActingReadMapper            actingReadMapper;
    @Autowired
    private AreaReadMapper              areaReadMapper;
    @Autowired
    private ScreeingsReadMapper         screeingsReadMapper;
    @Autowired
    private ActingWriteMapper           actingWriteMapper;
    @Autowired
    private AreaWriteMapper             areaWriteMapper;
    @Autowired
    private ScreeingsWriteMapper        screeingsWriteMapper;
    @Autowired
    private AreaScreeingsRelWriteMapper areaScreeingsRelWriteMapper;

    @Transactional(value = "stock.transactionManager", timeout = 2)
    public boolean addActing(AddActingModel model) {
        Long scenicId = model.getScenicId();
        if (checkExistActing(model, scenicId)) {
            return Boolean.FALSE;
        }

        //检查场次是否存在
        List<ScreeingsModel> screeingsModelList = model.getScreeingsModelList();
        if (checkExistScreenings(screeingsModelList, scenicId)) {
            return Boolean.FALSE;
        }

        //检查区域释放存在
        List<AreaModel> areaModelList = model.getAreaModelList();
        if (checkExistArea(areaModelList, scenicId)) {
            return Boolean.FALSE;
        }

        //初始化演艺
        Acting acting = new Acting();
        acting.setName(model.getActingName());
        acting.setScenicId(scenicId);
        acting.setSupplierId(model.getSupplierId());
        acting.setWhetherNeedSeat(model.getWhetherSeat());
        acting.setState(ActingStateEnum.AVAILABLE.getState());

        actingWriteMapper.insertActing(acting); //添加演艺
        Long actingId = acting.getId();
        List<Screeings> screeingsList = addScreeningsList(screeingsModelList); //添加场次
        List<Area> areaList = addAreaList(areaModelList); //添加区域
        this.addScreeningsAreaList(actingId, screeingsList, areaList); //添加场次区域关联

        return Boolean.TRUE;
    }

    /**
     * 添加区域场次关联信息
     * @param actingId
     * @param screeingsList
     * @param areaList
     */
    private void addScreeningsAreaList(Long actingId, List<Screeings> screeingsList, List<Area> areaList) {
        if (!CommonUtils.checkLongIsNull(actingId, true) && !screeingsList.isEmpty() && !areaList.isEmpty()) {
            List<AreaScreeingsRel> list = new ArrayList<AreaScreeingsRel>();
            for (Screeings screenings : screeingsList) {
                for (Area area : areaList) {
                    AreaScreeingsRel areaScreeings = new AreaScreeingsRel();
                    areaScreeings.setActingId(actingId);
                    areaScreeings.setAreaId(area.getId());
                    areaScreeings.setAreaName(area.getName());
                    areaScreeings.setScreeingsId(screenings.getId());
                    areaScreeings.setScreeingsName(screenings.getName());
                    areaScreeings.setState(ActingStateEnum.AVAILABLE.getState());
                    list.add(areaScreeings);
                }
            }
            areaScreeingsRelWriteMapper.insertRelBatch(list);
        }
    }

    /**
     * 添加区域集合
     * @param areaModelList
     * @return
     */
    private List<Area> addAreaList(List<AreaModel> areaModelList) {
        List<Area> areaList = new ArrayList<Area>();
        Area area = null;
        for (AreaModel areaModel : areaModelList) {
            area = new Area();
            area.setName(areaModel.getName());
            area.setScenicId(areaModel.getScenicId());
            area.setCode(areaModel.getCode());
            areaWriteMapper.insertArea(area);
            areaList.add(area);
        }
        return areaList;
    }

    /**
     * 添加场次集合
     * 
     * @param screeingsModelList
     * @return
     */
    private List<Screeings> addScreeningsList(List<ScreeingsModel> screeingsModelList) {
        List<Screeings> screeingsList = new ArrayList<Screeings>();
        Screeings screeings = null;
        for (ScreeingsModel screeingsModel : screeingsModelList) {
            screeings = new Screeings();
            screeings.setName(screeingsModel.getName());
            screeings.setScenicId(screeingsModel.getScenicId());
            screeings.setCode(screeingsModel.getCode());
            screeings.setStartTime(CommonUtils.convertStringToInteger(screeingsModel.getStartTime()));
            screeings.setEndTime(CommonUtils.convertStringToInteger(screeingsModel.getEndTime()));
            screeings.setEndSaleTime(CommonUtils.convertStringToInteger(screeingsModel.getEndSaleTime()));
            screeingsWriteMapper.insertScreeings(screeings);
            screeingsList.add(screeings);
        }
        return screeingsList;
    }

    /**
     * 检查是否存在演艺
     * @param model
     * @param scenicId
     * @return
     */
    private boolean checkExistActing(AddActingModel model, Long scenicId) {
        //检查演艺是否已经存在
        Acting acting = new Acting();
        acting.setScenicId(scenicId);
        acting.setSupplierId(model.getSupplierId());
        acting.setState(ActingStateEnum.AVAILABLE.getState());
        ArrayList<Acting> actingList = actingReadMapper.selectActingsByParam(acting);
        if (!Check.NuNCollections(actingList)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 检查是否存在区域
     * @param areaModelList
     * @param scenicId
     * @return
     */
    private boolean checkExistArea(List<AreaModel> areaModelList, Long scenicId) {

        List<String> areaNameList = new ArrayList<String>();
        for (AreaModel areaModel : areaModelList) {
            areaNameList.add(areaModel.getName());
        }
        Area area = new Area();
        area.setAreaNameList(areaNameList);
        area.setScenicId(scenicId);
        ArrayList<Area> areaList = areaReadMapper.selectAreasByParam(area);
        if (!Check.NuNCollections(areaList)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 检查是否存在场次
     * @param screeingsModelList
     * @param scenicId
     * @return
     */
    private boolean checkExistScreenings(List<ScreeingsModel> screeingsModelList, Long scenicId) {
        List<String> screeingsNameList = new ArrayList<String>();
        for (ScreeingsModel screeingsModel : screeingsModelList) {
            screeingsNameList.add(screeingsModel.getName());
        }
        Screeings screeings = new Screeings();
        screeings.setScenicId(scenicId);
        screeings.setScreeingsNameList(screeingsNameList);
        ArrayList<Screeings> screeningsList = screeingsReadMapper.selectScreeingsesByParam(screeings);
        if (!Check.NuNCollections(screeningsList)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
