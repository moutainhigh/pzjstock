package com.pzj.core.stock.read;

import java.util.List;

import com.pzj.core.stock.entity.SeatChart;

public interface SeatChartReadMapper {
    /**
     * 根据主键查询座位图
     * @param id
     * @return SeatChart
     */
    public SeatChart querySeatChartById(Long id);

    /**
     * 获取座位图通过id集合
     * @param list
     * @return List<SeatChart>
     */
    public List<SeatChart> querySeatChartByIds(List<Long> list);

    /**
     * 根据区域id获取座位图id集合
     * @param areaId
     * @return List<SeatChart>
     */
    public List<Long> querySeatChartIdsByArea(Long areaId);

    /**
     * 根据景区和区域查询座位图
     * @param scenic
     * @param areaId
     * @return List<SeatChart>
     */
    public List<SeatChart> querySeatChartByArea(Long areaId);
}