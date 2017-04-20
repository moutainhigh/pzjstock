package com.pzj.core.product.model.seat;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-3-8.
 */
public class CreateSeatCharReqModel implements Serializable {
    /**
     * 剧场id
     */
    private Long theaterId;

    private List<CreateSeatCharInfoReqModel> seatCharInfos;

    public Long getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Long theaterId) {
        this.theaterId = theaterId;
    }

    public List<CreateSeatCharInfoReqModel> getSeatCharInfos() {
        return seatCharInfos;
    }

    public void setSeatCharInfos(List<CreateSeatCharInfoReqModel> seatCharInfos) {
        this.seatCharInfos = seatCharInfos;
    }
}
