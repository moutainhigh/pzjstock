package com.pzj.core.product.area;

import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.common.exception.TheaterExceptionCode;
import com.pzj.core.product.entity.Area;
import com.pzj.core.product.model.area.CreateAreaReqModel;
import com.pzj.core.product.model.area.ModifyAreaReqModel;
import com.pzj.core.product.write.AreaWriteMapper;
import com.pzj.framework.idgen.IDGenerater;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2017-3-17.
 */
@Component("areaWriteEngine")
@Transactional(value = "stock.transactionManager")
public class AreaWriteEngine {
    @Resource
    private IDGenerater idGenerater;

    @Resource
    private AreaWriteMapper areaWriteMapper;

    /*-- start create area --*/

    public Boolean createArea(CreateAreaReqModel createAreaReqModel){
        List<Area> areas = AreaModelConvert.convertToAreas(createAreaReqModel);
        AreaCommonInfo areaCommonInfo = AreaModelConvert.convertToAreaCommonInfo(createAreaReqModel);

        return createArea(areaCommonInfo, areas);
    }

    public Boolean createArea(AreaCommonInfo areaCommonInfo, List<Area> requestAreas) {
        // 预校验
        createAreaCheck(areaCommonInfo, requestAreas);

        // 获取当前已创建的区域信息
        List<Area> existAreas = areaWriteMapper.selectAreaByTheater(areaCommonInfo.getTheaterId());

        if (existAreas != null && existAreas.size() > 0){
            throw new TheaterException(TheaterExceptionCode.OPERATING_E_CREATE_C_MOIDIFY);
        }

        Date currentDate = new Date();
        for (Area area : requestAreas){
            createAreaInit(area, currentDate);
            copyAreaCommonInfoToArea(areaCommonInfo, area);
        }

        int insert = areaWriteMapper.addBatchArea(requestAreas);

        return insert == 1;
    }

    private void createAreaCheck(AreaCommonInfo areaCommonInfo, List<Area> requestAreas){
        // 检查区域公共信息
        if (areaCommonInfo == null){
            throw new TheaterException(TheaterExceptionCode.PARAMETER_EMPTY);
        }
        if (areaCommonInfo.getTheaterId() == null){
            throw new TheaterException(TheaterExceptionCode.AREA_NULL_THEATERID);
        }
        if (areaCommonInfo.getSupplierId() == null){
            throw new TheaterException(TheaterExceptionCode.AREA_NULL_SUPPLIERID);
        }
        if (areaCommonInfo.getSeatMode() == null){
            throw new TheaterException(TheaterExceptionCode.AREA_NULL_SEATMODE);
        }
        AreaSeatMode.checkAreaTypeValue(areaCommonInfo.getSeatMode());
        if (areaCommonInfo.getSeatType() == null){
            throw new TheaterException(TheaterExceptionCode.AREA_NULL_SEATTYPE);
        }
        AreaSeatType.checkAreaTypeValue(areaCommonInfo.getSeatType());
        // 检查区域详细信息
        if (requestAreas == null || requestAreas.isEmpty()){
            throw new TheaterException(TheaterExceptionCode.AREA_RULE_CREATE_LEAST_ONE);
        }
        maximumNumberOfAreasCheck(requestAreas.size());
        for (Area area : requestAreas){
            areaNullCheck(area);
            createAreaCheck(area);
        }
    }

    private void maximumNumberOfAreasCheck(int currentNum){
        // 检验区域的总数量是否超过限制
        if (currentNum > 10){
            throw new TheaterException(TheaterExceptionCode.AREA_RULE_CREATE_MAX);
        }
    }

    private void areaNullCheck(Area area){
        if (area == null){
            throw new TheaterException(TheaterExceptionCode.AREA_NULL);
        }
    }

    private void createAreaCheck(Area area){
        if (area.getName() == null){
            throw new TheaterException(TheaterExceptionCode.AREA_NULL_NAME);
        }
    }

    private void createAreaInit(Area area, Date currentDate){
        area.setId(idGenerater.nextId());
        area.setCreateTime(currentDate);
        area.setState(AreaState.ENABLE.getValue());
    }

    private void copyAreaCommonInfoToArea(AreaCommonInfo areaCommonInfo, Area area){
        area.setSeatType(areaCommonInfo.getSeatType());
        area.setSeatMode(areaCommonInfo.getSeatMode());
        area.setScenicId(areaCommonInfo.getTheaterId());
        area.setSupplierId(areaCommonInfo.getSupplierId());
        area.setThumb(areaCommonInfo.getThumb());
    }

    /*-- start modify area --*/
    public Boolean modifyArea(ModifyAreaReqModel modifyAreaReqModel){
        List<Area> areas = AreaModelConvert.convertToAreas(modifyAreaReqModel);
        AreaCommonInfo areaCommonInfo = AreaModelConvert.convertToAreaCommonInfo(modifyAreaReqModel);

        return modifyArea(areaCommonInfo, areas);
    }

    public Boolean modifyArea(AreaCommonInfo areaCommonInfo, List<Area> requestAreas) {
        // 预校验
        modifyAreaCheck(areaCommonInfo, requestAreas);

        // 获取当前已创建的区域信息
        List<Area> existAreas = areaWriteMapper.selectAreaByTheater(areaCommonInfo.getTheaterId());

        if (existAreas == null || existAreas.isEmpty()) {
            throw new TheaterException(TheaterExceptionCode.OPERATING_E_MOIDIFY_C_CREATE);
        }

        // 获取可用于保存到所有区域上的共同的信息
        AreaCommonInfo insertAreaCommonInfo = commonInfoOnModifyArea(areaCommonInfo, existAreas);

        // 构建可用于插入到数据库的区域信息
        List<Area> insertAreas = buildInsertAreas(insertAreaCommonInfo, existAreas, requestAreas);

        // 检验区域的总数量是否超过限制
        maximumNumberOfAreasCheck(insertAreas.size());

        areaWriteMapper.deleteAreaByTheater(areaCommonInfo.getTheaterId());
        areaWriteMapper.addBatchArea(insertAreas);

        return true;
    }

    /**
     * 修改操作参数检查
     * @param areaCommonInfo
     */
    private void modifyAreaCheck(AreaCommonInfo areaCommonInfo, List<Area> requestAreas) {
        if (areaCommonInfo == null){
            throw new TheaterException(TheaterExceptionCode.PARAMETER_EMPTY);
        }
        if (areaCommonInfo.getTheaterId() == null){
            throw new TheaterException(TheaterExceptionCode.AREA_NULL_THEATERID);
        }
        // 检查是否有可修改的数据
        canModifyTheDataCheck(areaCommonInfo, requestAreas);
    }

    private void canModifyTheDataCheck(AreaCommonInfo areaCommonInfo, List<Area> areas){
        boolean needModifyCount = false;
        if (areaCommonInfo.getThumb() != null){
            needModifyCount = true;
        }

        if (areas != null && !areas.isEmpty()){
            for (Area area : areas){
                areaNullCheck(area);
                if (area.getId() == null){
                    // 对需要新建的区域的详细信息检查
                    createAreaCheck(area);
                } else {
                    // 对需要修改的区域的详细信息检查
                    modifyAreaCheck(area);
                }
            }
            needModifyCount = true;
        }

        if (!needModifyCount){
            throw new TheaterException(TheaterExceptionCode.MODIFY_DATA_EMPTY);
        }
    }

    private void modifyAreaCheck(Area area){
        if (area.getName() == null && area.getCode() == null){
            throw new TheaterException(TheaterExceptionCode.MODIFY_DATA_EMPTY);
        }
    }

    private AreaCommonInfo commonInfoOnModifyArea(AreaCommonInfo areaCommonInfo, List<Area> existAreas) {
        Area area = existAreas.get(0);

        AreaCommonInfo info = new AreaCommonInfo();
        info.setTheaterId(area.getScenicId());
        info.setSupplierId(area.getSupplierId());
        info.setSeatMode(area.getSeatMode());
        info.setSeatType(area.getSeatType());
        info.setThumb(area.getThumb());

        if (areaCommonInfo.getThumb() != null){
            info.setThumb(areaCommonInfo.getThumb());
        }

        return info;
    }

    /**
     * 更新时，为每个区域设置保存的数据
     * @param saveAreaCommonInfo 可以用于保存区域的公共信息
     * @param requestAreas 调用方传递需要更新或创建的区域信息
     */
    private List<Area> buildInsertAreas(AreaCommonInfo saveAreaCommonInfo, List<Area> existAreas, List<Area> requestAreas) {
        // 将区域集合复制到Map中
        HashMap<Long, Area> saveAreas = copyAreasToMap(existAreas);

        List<Area> insertAreas = new ArrayList<>(existAreas);

        Date currentDate = new Date();

        if (requestAreas != null && !requestAreas.isEmpty()) {
            for (Area requestArea : requestAreas) {
                if (requestArea.getId() == null) {
                    createAreaInit(requestArea, currentDate);
                    insertAreas.add(requestArea);
                } else if (saveAreas.containsKey(requestArea.getId())) {
                    Area existArea = saveAreas.get(requestArea.getId());
                    modifyAreaInit(existArea, requestArea, currentDate);
                } else {
                    throw new TheaterException(TheaterExceptionCode.AREA_ILLEGAL_ID);
                }
            }
        }

        for (Area area : insertAreas){
            copyAreaCommonInfoToArea(saveAreaCommonInfo, area);
        }

        return insertAreas;
    }

    private HashMap<Long, Area> copyAreasToMap(List<Area> existAreas) {
        HashMap<Long, Area> saveAreas = new HashMap<>();
        if (!existAreas.isEmpty()) {
            for (Area area : existAreas) {
                saveAreas.put(area.getId(), area);
            }
        }
        return saveAreas;
    }

    private void modifyAreaInit(Area area, Area requestArea, Date currentDate){
        area.setUpdateTime(currentDate);
        if (area.getName() != null){
            area.setName(requestArea.getName());
        }
        if (area.getCode() != null){
            area.setCode(requestArea.getCode());
        }
    }

    /**
     * 根据区域id查询所有区域的公共信息
     * @param areaId
     * @return
     */
    public AreaCommonInfo queryAreaCommonInfoByAreaId(Long areaId) {
        Area area = areaWriteMapper.selectAreaById(areaId);
        if (area != null){
            AreaCommonInfo areaCommonInfo = AreaModelConvert.convertToAreaCommonInfo(area);
            return areaCommonInfo;
        }
        return null;
    }

    /**
     * 检查指定区域是否需求占库存
     * @param areaId 被检查区域的id
     * @return
     */
    public boolean needOccupySeat(Long areaId){
        if (areaId == null || areaId == 0) {
            return false;
        }
        AreaCommonInfo areaCommonInfo = queryAreaCommonInfoByAreaId(areaId);
        if (areaCommonInfo == null) {
            TheaterExceptionCode code = TheaterExceptionCode.AREA_NOT_EXIST;
            String msg = code.getTemplateMessage(areaId);
            throw new TheaterException(code.getCode(), msg);
        }
        return AreaSeatType.RectangularSeat.equals(areaCommonInfo.getSeatType());
    }
}
