package com.pzj.core.product.model.seat;

import java.util.List;

/**
 * Created by Administrator on 2017-3-8.
 */
public class CreateManySeatCharReqModel {
    /**
     * 创建多个座位的集合
     */
    private List<CreateSeatCharReqModel> seatChars;

    public List<CreateSeatCharReqModel> getSeatChars() {
        return seatChars;
    }

    public void setSeatChars(List<CreateSeatCharReqModel> seatChars) {
        this.seatChars = seatChars;
    }
}
