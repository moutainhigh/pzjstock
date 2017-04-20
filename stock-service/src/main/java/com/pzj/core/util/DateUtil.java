/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;

/**
 * 
 * @author dongchunfu
 * @version $Id: DateUtil.java, v 0.1 2016年8月10日 下午7:51:22 dongchunfu Exp $
 */
@Component(value = "dateUtil")
public class DateUtil {

    //在指定时间上加 N 个月
    public Date monthLater(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        Date result = calendar.getTime();
        return result;

    }

    //在当天时间上加 N 天
    @SuppressWarnings("static-access")
    public Date dayLater(Date date, int add) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, add);
        return calendar.getTime();
    }

    //获取时间的整形值
    public Integer getIntegerTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(date);
        return Integer.valueOf(format);
    }

    //计算日期间相差天数
    public int daysDif(Date begin, Date end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        begin = sdf.parse(sdf.format(begin));
        end = sdf.parse(sdf.format(end));

        Calendar cal = Calendar.getInstance();
        cal.setTime(begin);
        long beginTime = cal.getTimeInMillis();
        cal.setTime(end);
        long endTime = cal.getTimeInMillis();

        return (int) Math.abs((endTime - beginTime) / (1000 * 3600 * 24));
    }

    //获取月份的最后一天
    public int getLastDay(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    //Integer 数字转换为时间格式
    public Date getDate(Integer time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.parse(String.valueOf(time));
    }
}
