package com.pzj.core.product.model.seat;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-5-23.
 */
public class SeatChartManyRespModel implements Serializable {
    private List<SeatChartRespModel> seats;

    public List<SeatChartRespModel> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatChartRespModel> seats) {
        this.seats = seats;
    }
}
