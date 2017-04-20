package com.pzj.core.stock.write;

import com.pzj.core.stock.entity.SeatChart;

public interface SeatChartWriteMapper {
    /**
     * 添加座位图
     * @param seatChart
     * @return
     */
    public Long insert(SeatChart seatChart);
}