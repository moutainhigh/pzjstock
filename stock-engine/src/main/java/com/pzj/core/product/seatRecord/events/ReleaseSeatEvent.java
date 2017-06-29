package com.pzj.core.product.seatRecord.events;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-5-9.
 */
public class ReleaseSeatEvent implements SeatEvent {
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 加库存数量
     */
    private Integer inQuantity;

    /**
     * 出游日期
     */
    private Date travelDate;

    /**
     * 交易id
     */
    private String transactionId;

    /**
     * 场次id
     */
    private Long screeningsId;

    /**
     * 区域id
     */
    private Long areaId;

    /**
     * 占座类型
     * 20：下单占座
     * 40：预约占座
     */
    private Integer occupyType;

    /**
     * 需要释放的座位id
     */
    private List<Long> releaseSeatIds;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getInQuantity() {
        return inQuantity;
    }

    public void setInQuantity(Integer inQuantity) {
        this.inQuantity = inQuantity;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getScreeningsId() {
        return screeningsId;
    }

    public void setScreeningsId(Long screeningsId) {
        this.screeningsId = screeningsId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public List<Long> getReleaseSeatIds() {
        return releaseSeatIds;
    }

    public void setReleaseSeatIds(List<Long> releaseSeatIds) {
        this.releaseSeatIds = releaseSeatIds;
    }

    public Integer getOccupyType() {
        return occupyType;
    }

    public void setOccupyType(Integer occupyType) {
        this.occupyType = occupyType;
    }
}
