package com.pzj.core.product.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 演绎VO
 * 
 * @author dongchunfu
 * @version $Id: ActingModel.java, v 0.1 2016年8月2日 上午11:14:55 dongchunfu Exp $
 */
public class ActingProductModel implements Serializable {

    private static final long         serialVersionUID = 8662503979831938671L;

    /** 景区ID */
    private Long                      scenicId;

    /** 供应商ID */
    private Long                      supplierId;

    /** 演绎ID */
    private Long                      actingId;

    /** 演绎集合 */
    private ArrayList<ActingModel>    actings;

    /** 区域结果集 */
    private ArrayList<AreaModel>      areas;

    /** 场次结果集 */
    private ArrayList<ScreeingsModel> screeingses;

    /** 演艺产品绑定演艺信息 */
    private ActingModel               actingModel;

    /** 演绎产品绑定区域信息  */
    private AreaModel                 areaModel;

    /** 演绎产品绑定场次信息 */
    private ScreeingsModel            screeingsModel;

    public ActingProductModel() {
        super();
    }

    public Long getActingId() {
        return actingId;
    }

    public void setActingId(Long actingId) {
        this.actingId = actingId;
    }

    public Long getScenicId() {
        return scenicId;
    }

    public void setScenicId(Long scenicId) {
        this.scenicId = scenicId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public ArrayList<ActingModel> getActings() {
        return actings;
    }

    public void setActings(ArrayList<ActingModel> actings) {
        this.actings = actings;
    }

    public ArrayList<AreaModel> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<AreaModel> areas) {
        this.areas = areas;
    }

    public ArrayList<ScreeingsModel> getScreeingses() {
        return screeingses;
    }

    public void setScreeingses(ArrayList<ScreeingsModel> screeingses) {
        this.screeingses = screeingses;
    }

    public ActingModel getActingModel() {
        return actingModel;
    }

    public void setActingModel(ActingModel actingModel) {
        this.actingModel = actingModel;
    }

    public AreaModel getAreaModel() {
        return areaModel;
    }

    public void setAreaModel(AreaModel areaModel) {
        this.areaModel = areaModel;
    }

    public ScreeingsModel getScreeingsModel() {
        return screeingsModel;
    }

    public void setScreeingsModel(ScreeingsModel screeingsModel) {
        this.screeingsModel = screeingsModel;
    }

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(ActingProductModel.class.getSimpleName());
        tostr.append("[");
        tostr.append("scenicId: ").append(scenicId).append(",");
        tostr.append("supplierId: ").append(supplierId).append(",");
        tostr.append("actings: ").append(actings).append(",");
        tostr.append("areas: ").append(areas).append(",");
        tostr.append("screeingses: ").append(screeingses).append(",");
        tostr.append("]");
        return tostr.toString().intern();
    }

}