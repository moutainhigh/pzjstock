package com.pzj.core.product.write;

import com.pzj.core.product.entity.SeatChar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017-3-28.
 */
public interface SeatCharWriteMapper {
    List<SeatChar> selectSeatCharByIds(List<Long> ids);

    int insertBatch(List<SeatChar> seatChars);

    int updateBatch(List<SeatChar> seatChars);

    List<SeatChar> selectRepeatSeat(List<SeatChar> seatChars);
}
