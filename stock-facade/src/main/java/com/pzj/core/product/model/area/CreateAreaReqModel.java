package com.pzj.core.product.model.area;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-3-8.
 */
public class CreateAreaReqModel implements Serializable {
    /**
     * 剧场id
     */
    private Long theaterId;

    /**
     * 供应商id
     */
    private Long supplierId;

    /**
     * 座位类型。
     * 1.	无座位
     * 2.	有座位，只支持矩形
     */
    private Integer seatType;

    /**
     * 座位模式
     * 1.	用户自选
     * 2.	手动派座
     * 3.	自动派座
     */
    private Integer seatMode;

    /**
     * 缩略图(路径)
     */
    private String thumb;

    /**
     * 区域信息集合
     */
    private List<CreateAreaInfoReqModel> areaInfos;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Long theaterId) {
        this.theaterId = theaterId;
    }

    public Integer getSeatType() {
        return seatType;
    }

    public void setSeatType(Integer seatType) {
        this.seatType = seatType;
    }

    public Integer getSeatMode() {
        return seatMode;
    }

    public void setSeatMode(Integer seatMode) {
        this.seatMode = seatMode;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public List<CreateAreaInfoReqModel> getAreaInfos() {
        return areaInfos;
    }

    public void setAreaInfos(List<CreateAreaInfoReqModel> areaInfos) {
        this.areaInfos = areaInfos;
    }
}
