package com.pzj.core.product.model.area;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-3-8.
 */
public class ModifyAreaReqModel implements Serializable {
    /**
     * 剧场id
     */
    private Long theaterId;

    /**
     * 缩略图(路径)
     */
    private String thumb;

    /**
     * 区域信息集合
     */
    private List<ModifyAreaInfoReqModel> areaInfos;


    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public List<ModifyAreaInfoReqModel> getAreaInfos() {
        return areaInfos;
    }

    public void setAreaInfos(List<ModifyAreaInfoReqModel> areaInfos) {
        this.areaInfos = areaInfos;
    }

    public Long getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Long theaterId) {
        this.theaterId = theaterId;
    }
}
