package com.pzj.core.stock.read;

import com.pzj.core.stock.entity.OrderedSeat;

public interface OrderedSeatReadMapper {
    /**
     * 根据主键查询
     * 
     * @param orderedSeatid 预定座位ID
     * @return OrderedSeat
     */
    public OrderedSeat selectOrderedSeatById(Long orderedSeatId);
}