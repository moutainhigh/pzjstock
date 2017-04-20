package com.pzj.core.product.model.seat;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-3-8.
 */
public class ModifySeatCharReqModel implements Serializable {

    private List<ModifySeatCharInfoReqModel> seatCharInfos;

    public List<ModifySeatCharInfoReqModel> getSeatCharInfos() {
        return seatCharInfos;
    }

    public void setSeatCharInfos(List<ModifySeatCharInfoReqModel> seatCharInfos) {
        this.seatCharInfos = seatCharInfos;
    }
}
