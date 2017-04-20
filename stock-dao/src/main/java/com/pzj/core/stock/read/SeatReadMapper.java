package com.pzj.core.stock.read;

import java.util.List;

public interface SeatReadMapper {

    /**
     * 根据座位图查询生成的座位图列表
     * @param seatChartId
     * @return Seat
     */
    public List<String> querySeatBySeatChartId(Long seatChartId);

    /**
     * 统计座位图下生成的座位数
     * @param seatChartId
     * @return Integer
     */
    public Integer countSeat(Long seatChartId);

    /**
     * 根据座位图id集合查找座位
     * @param seatChartIds
     * @return
     */
    public List<String> querySeatBySeatChartIds(List<Long> seatChartIds);

}