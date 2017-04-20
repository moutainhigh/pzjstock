package com.pzj.core.stock.write;

import com.pzj.core.product.entity.SeatChar;
import com.pzj.core.stock.entity.SeatChart;

import java.util.List;

public interface SeatChartWriteMapper {
    /**
     * 添加座位图
     * @param seatChart
     * @return
     */
    Long insert(SeatChart seatChart);

    List<SeatChar> selectSeatCharByIds(List<Long> ids);
}