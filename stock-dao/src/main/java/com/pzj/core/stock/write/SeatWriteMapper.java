package com.pzj.core.stock.write;

import java.util.List;

import com.pzj.core.stock.entity.Seat;

public interface SeatWriteMapper {
    /**
     * 批量添加座位
     * @param list
     * @return
     */
    public Integer addBatchSeat(List<Seat> seatList);
}