package com.pzj.core.product.seatRecord.events;

import com.pzj.core.work.Event;

/**
 * Created by Administrator on 2017-5-9.
 */
public interface SeatEvent extends Event {
    String getTransactionId();
}
