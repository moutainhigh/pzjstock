package com.pzj.core.product.seatRecord.events;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-5-9.
 */
public class OccupySeatEvent implements SeatEvent {
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 减库存数量
     */
    private Integer outQuantity;

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
     * 需要占座的座位id
     */
    private List<Long> occupySeatIds;

    public List<Long> getOccupySeatIds() {
        return occupySeatIds;
    }

    public void setOccupySeatIds(List<Long> occupySeatIds) {
        this.occupySeatIds = occupySeatIds;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getOutQuantity() {
        return outQuantity;
    }

    public void setOutQuantity(Integer outQuantity) {
        this.outQuantity = outQuantity;
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

    public Integer getOccupyType() {
        return occupyType;
    }

    public void setOccupyType(Integer occupyType) {
        this.occupyType = occupyType;
    }
}
