package com.pzj.core.stock.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.stock.entity.StockSeatRel;

public interface StockSeatRelReadMapper {

    /**
     * 根据业务id查询下单座位数
     * @param serviceId
     * @return int
     */
    public int countSeatNumByServiceId(String serviceId);

    /**
     * 根据业务唯一id查询占库存信息
     * @param serviceId
     * @return
     */
    public List<StockSeatRel> querySeatByBussId(String serviceId);

    /**
     * 根据库存和当前传入座位查库存座位集合
     * @param stockId
     * @param seats
     * @return List<StockSeatRel>
     */
    public List<StockSeatRel> queryByStockAndSeats(@Param(value = "stockId") Long stockId, @Param(value = "seats") List<String> seats);

    /**
     * 根据库存查询座位集合
     * @param stockId
     * @return
     */
    public List<String> querySeatNumByStockId(Long stockId);
}