package com.pzj.core.product.seatRecord;

import com.pzj.core.common.utils.CRCUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017-5-3.
 */
public class SeatRecordUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 生成座位记录的唯一值
     * @param screeningsId
     * @param seatId
     * @param travelDate
     * @param random
     * @return
     */
    public static Long recordUnique(Long screeningsId, Long seatId, Date travelDate, String random) {
        StringBuilder sb = new StringBuilder();
        sb.append(screeningsId).append(":");
        sb.append(seatId).append(":");
        sb.append(sdf.format(travelDate)).append(":");
        if (random != null) {
            sb.append(":");
            sb.append(random);
        }
        Long recordUnique = CRCUtils.convertUniqueLong(sb.toString());
        return recordUnique;
    }

    public static  Date formatTravelDate(Date travelDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(travelDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
		/*DateTime dateTime = new DateTime(travelDate).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
		travelDate = new Date(dateTime.getMillis());*/
        return calendar.getTime();
    }
}
