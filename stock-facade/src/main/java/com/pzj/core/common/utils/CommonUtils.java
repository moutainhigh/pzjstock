/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.exception.stock.QueryStockDateIllegalException;
import com.pzj.framework.context.Result;

/**
 * 
 * @author Administrator
 * @version $Id: CommonUtils.java, v 0.1 2016年7月22日 上午11:28:48 Administrator Exp $
 */
public class CommonUtils {

	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	/**
	 * 检查对象是否为空
	 * @param objs（支持字符串，集合，map，普通对象）多对象传参
	 * @return 空：true ；非空：false
	 */
	public static boolean checkObjectIsNull(Object... objs) {
		boolean flag = Boolean.TRUE;
		if (CommonUtils.checkArrIsNull(objs)) {
			return flag;
		}

		for (Object obj : objs) {
			if (obj instanceof String) {
				flag = checkStringIsNull((String) obj);
			} else if (obj instanceof Collection) {
				flag = checkCollectionIsNull((Collection<?>) obj);
			} else if (obj instanceof Map) {
				flag = checkMapIsNull((Map<?, ?>) obj);
			} else {
				flag = obj == null;
			}
			if (flag) {
				break;
			}
		}
		return flag;
	}

	/**
	 * 检查集合是否是空
	 * @param collection
	 * @return 空：true；非空：false
	 */
	public static boolean checkCollectionIsNull(Collection<?> collection) {
		return null == collection || collection.isEmpty();
	}

	/**
	 * 检查map是否是空
	 * @param map
	 * @return 空：true；非空：false
	 */
	public static boolean checkMapIsNull(Map<?, ?> map) {
		return null == map || map.isEmpty();
	}

	/**
	 * 检查字符串是否为空
	 * 空：true ；非空：false
	 * @param str
	 * @return
	 */
	public static boolean checkStringIsNull(String str) {
		return checkStringIsNotNull(str) ? false : true;
	}

	/**
	 * 严格检查字符串是否为空
	 * 空：true ；非空：false
	 * @param str
	 * @return
	 */
	public static boolean checkStringIsNullStrict(String str) {
		return !checkStringIsNotNull(str) || "null".equals(str) ? true : false;
	}

	/**
	 * 检查字符串是否非空
	 * 非空：true ；空：false
	 * @param str
	 * @return
	 */
	public static boolean checkStringIsNotNull(String... str) {
		boolean flag = Boolean.FALSE;
		if (null == str || str.length == 0) {
			return flag;
		}
		for (String s : str) {
			if (null == s || "".equals(s.trim())) {
				flag = Boolean.FALSE;
			} else {
				flag = Boolean.TRUE;
			}

			if (!flag) {
				break;
			}
		}
		return flag;
	}

	/**
	 * 检查long类型参数是否合法
	 * 空：true ；非空：false
	 * @param id
	 * @param checkHigherZero
	 * @return
	 */
	public static boolean checkLongIsNull(Long id, boolean checkHigherZero) {
		boolean flag = Boolean.FALSE;
		if (null == id) {
			flag = Boolean.TRUE;
		}

		if (!flag && checkHigherZero && id <= 0) {
			flag = Boolean.TRUE;
		}
		return flag;
	}

	/**
	 * 检查int类型参数是否合法
	 * 空：true ；非空：false
	 * @param id
	 * @param checkHigherZero
	 * @return
	 */
	public static boolean checkIntegerIsNull(Integer id, boolean checkHigherZero) {
		boolean flag = Boolean.FALSE;
		if (null == id) {
			flag = Boolean.TRUE;
		}

		if (!flag && checkHigherZero && id <= 0) {
			flag = Boolean.TRUE;
		}
		return flag;
	}

	/**
	 * 检查数组是否是空（支持字符串，Collection，Map，基本对象等）
	 * @param objarr
	 * @return 空：true ；非空：false
	 */
	public static boolean checkArrIsNull(Object[] objarr) {
		boolean flag = Boolean.TRUE;
		if (null == objarr || objarr.length == 0) {
			return flag;
		}
		for (Object obj : objarr) {
			if (obj instanceof String) {
				flag = checkStringIsNull((String) obj);
			} else if (obj instanceof Collection) {
				flag = checkCollectionIsNull((Collection<?>) obj);
			} else if (obj instanceof Map) {
				flag = checkMapIsNull((Map<?, ?>) obj);
			} else {
				flag = obj == null;
			}
			if (flag) {
				break;
			}
		}
		return flag;
	}

	/**
	 * 过滤字符串前后多余分隔符
	 * @param str
	 * @param separator
	 * @return 过滤后的字符串对象
	 */
	public static String filterHeadAndTailSeparator(String str, String separator) {
		if (checkStringIsNotNull(str)) {
			while (str.startsWith(separator)) {
				str = str.substring(1);
			}
			while (str.endsWith(separator)) {
				str = str.substring(0, str.length() - 1);
			}
		}
		return str;
	}

	/**
	 * 过滤重复分隔符
	 * @param str
	 * @param separator
	 * @return 过滤后的字符串
	 */
	public static String filterRepeatSeparator(String str, String separator) {
		StringBuffer strBuffer = new StringBuffer("");
		if (checkStringIsNotNull(str)) {
			String[] arr = str.split(separator);
			for (String s : arr) {
				if (checkStringIsNotNull(s)) {
					strBuffer.append(separator).append(s);
				}
			}
			if (strBuffer.length() > 0) {
				strBuffer.deleteCharAt(0);
			}
		}
		return strBuffer.toString();
	}

	/**
	 * 过滤数组重复字符串
	 * @param arr
	 * @return 过滤后的数组对象
	 */
	public static String[] filterRepeatString(String[] arr) {
		String[] filterStrArr = null;
		if (!CommonUtils.checkArrIsNull(arr)) {
			List<String> list = new ArrayList<String>();
			for (String str : arr) {
				if (!list.contains(str)) {
					list.add(str);
				}
			}
			filterStrArr = list.toArray(new String[list.size()]);
		}
		return filterStrArr;
	}

	/**
	 * 过滤集合重复元素
	 * @param list
	 * @return 过滤后的list
	 */
	public static List<String> filterListRepeat(List<String> list) {
		if (!CommonUtils.checkObjectIsNull(list)) {
			Iterator<String> itera = list.iterator();
			List<String> filterList = new ArrayList<String>();
			String val = "";
			while (itera.hasNext()) {
				val = itera.next();
				if (checkStringIsNotNull(val) && !filterList.contains(val)) {
					filterList.add(val);
				}
			}
			return filterList;
		}
		return null;
	}

	/**
	 * 校验座位号是否合法
	 * @param seats
	 * @return 合法：true 不合法：false
	 */
	public static boolean checkSeatIfLegal(List<String> seats) {
		for (String seat : seats) {
			if (CommonUtils.checkStringIsNull(seat)) {
				return Boolean.FALSE;
			}
			if (!seat.matches("\\d{0,10}[a-zA-Z]{1,10}\\d{0,10}_\\d{1,10}$")) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	/**
	 * 检查座位图排号是否合法
	 * @param row
	 * @return 合法：true 不合法：false
	 */
	public static boolean checkSeatRowLegal(String row) {
		if (CommonUtils.checkStringIsNullStrict(row)) {
			return Boolean.FALSE;
		}
		if (!row.matches("\\d{0,10}[a-zA-Z]{1,10}\\d{0,10}$")) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * 检查字符串长度和非空
	 * 空或长度大于指定长度则为false，其余返回true
	 * @param str
	 * @param length
	 * @return Boolean 
	 */
	public static Boolean checkStrLengthAndNull(String str, int length) {
		return (checkStringIsNull(str) || str.length() > length) ? Boolean.FALSE : Boolean.TRUE;
	}

	/**
	 * 字符串日期格式转换为int类型
	 * <p>日期格式：</p>
	 * </ul>
	 * <li>20160727</li>
	 * <li>2016-07-27</li>
	 * <li>2016/07/27</li>
	 * <ul>
	 * 
	 */
	//TODO 建议移至toolkit中，在facade中严格要求不允许定义无关类
	public static int strDate2Int(String dateStr) {
		/**
		 * 日期格式.
		 */
		final Pattern DATA_PATTERN = Pattern
				.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");

		int temp = 0;
		if (dateStr == null) {
			return temp;
		}

		final Matcher m = DATA_PATTERN.matcher(dateStr);
		if (!m.matches()) {
			return temp;
		}

		String tempDate = dateStr.replaceAll("[\\-\\/]", "");
		temp = Integer.parseInt(tempDate);
		return temp;
	}

	/**
	 * 检查库存时间是否可以占座
	 * @param stockTime
	 * @return boolean 合法：true；不合法：false
	 */
	public static boolean checkStockTimeIsAvai(Integer stockTime) {
		Integer nowTime = getStockDateInteger(new Date());
		if (checkObjectIsNull(stockTime, nowTime) || stockTime.intValue() < nowTime.intValue()) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	/**
	 * 将日期格式转换为库存需要处理的日期整形
	 * @param date
	 * @return Integer 20160910
	 */
	public static Integer getStockDateInteger(Date date) {
		if (!checkObjectIsNull(date)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR);
			int bmonth = calendar.get(Calendar.MONTH) + 1;
			int bday = calendar.get(Calendar.DAY_OF_MONTH);
			String monthStr = (bmonth < 10) ? "0" + bmonth : "" + bmonth;
			String dayStr = (bday < 10) ? "0" + bday : "" + bday;
			String timeStr = year + monthStr + dayStr;
			return Integer.parseInt(timeStr);
		}
		return null;
	}

	/**
	 * 检查查询库存时间格式是否合法
	 * @param stockTime
	 * @return string 返回年月日字符串20160812
	 */
	public static String checkStockTime(String stockTime) {
		if (checkStringIsNotNull(stockTime)) {
			if (!stockTime.matches("^\\d{4}[-/_]?\\d{2}[-/_]?\\d{2}$")) {
				logger.error("check stock time error!stockTime:{}", stockTime);
				throw new QueryStockDateIllegalException(StockExceptionCode.STOCK_QUERY_DATE_ILLEGAL_ERR_MSG);
			} else {
				Pattern p = Pattern.compile("[-/_]");
				Matcher m = p.matcher(stockTime);
				stockTime = m.replaceAll("");
			}
		} else {
			logger.error("check stock time error!stockTime:{}", stockTime);
			throw new QueryStockDateIllegalException(StockExceptionCode.STOCK_QUERY_DATE_ILLEGAL_ERR_MSG);
		}
		return stockTime;
	}

	/**
	 * 将库存整形转换成日期类型
	 * @param stockTime
	 * @return Date
	 * @throws ParseException
	 */
	public static Date integerConvertDate(Integer stockTime) {
		if (!checkIntegerIsNull(stockTime, true)) {
			try {
				String datestr = stockTime.toString();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				Date strToDate = sdf.parse(datestr);
				return strToDate;
			} catch (ParseException e) {
				logger.error("---库存整型日期转日期格式异常-------", e);
				throw new StockException(StockExceptionCode.STOCK_ERR_MSG);
			}
		}
		return null;
	}

	/**
	 * 场次时间整形转成四位字符串格式
	 * @param screeningsTime
	 * @return 将场次整形时间转换成4位字符串，格式：0010
	 */
	public static String screeningsTimeConvert(Integer screeningsTime) {
		if (!checkIntegerIsNull(screeningsTime, false)) {
			StringBuffer screenigsTimeBuffer = new StringBuffer(screeningsTime.toString());
			while (screenigsTimeBuffer.length() < 4) {
				screenigsTimeBuffer.insert(0, "0");
			}
			return screenigsTimeBuffer.toString();
		}
		return null;
	}

	/**
	 * 校验场次时间
	 * 格式正确：true；格式错误：false
	 * @param screeningsTime
	 * @return 是否是4位数时间，如：1200
	 */
	public static boolean checkScreeningsTime(String screeningsTime) {
		if (checkStringIsNotNull(screeningsTime) && screeningsTime.matches("^\\d{1,4}$")) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * 将字符串转换成整形
	 * @param str
	 * @return 将字符串转换成整形
	 */
	public static Integer convertStringToInteger(String str) {
		if (checkStringIsNotNull(str) && str.matches("^\\d+$")) {
			return Integer.parseInt(str);
		}
		return null;
	}

	/**
	 * 判断字符串是否是数值类型
	 * @param str
	 * @return 是数值：true；不是数值：false
	 */
	public static Boolean judgeStringIsNum(String... str) {
		boolean flag = Boolean.FALSE;
		if (checkStringIsNotNull(str)) {
			int count = str.length;
			for (String s : str) {
				if (s.matches("^\\d+$")) {
					count--;
				}
			}
			if (count == 0)
				flag = Boolean.TRUE;
		}
		return flag;
	}

	/**
	 * 检查字符串长度是否超出最大长度
	 * @param len
	 * @param strs
	 * @return 未超出最大长度：true；超出最大长度：false
	 */
	public static Boolean checkStringLen(int len, String... strs) {
		boolean flag = Boolean.TRUE;
		if (checkArrIsNull(strs)) {
			return flag;
		}

		for (String str : strs) {
			str = str.trim();
			flag = str.matches("^[\\d\\D]{0," + len + "}$") ? Boolean.TRUE : Boolean.FALSE;
			if (!flag) {
				break;
			}
		}
		return flag;
	}

	/**
	 * 获取最小日期
	 * @param dateList
	 * @return Date
	 */
	public static Date getMinDate(List<Date> dateList) {
		if (!checkObjectIsNull(dateList)) {
			Date minDate = null;
			for (Date date : dateList) {
				if (null == date) {
					continue;
				}
				if (null == minDate) {
					minDate = date;
				}
				long minDateLong = minDate.getTime();
				long curDateLong = date.getTime();
				if (curDateLong < minDateLong) {
					minDate = date;
				}
			}
			return minDate;
		}
		return null;
	}

	/**
	 * 检查生成库存最小时间是否小于当前时间
	 * @param minDate
	 * @return boolean 小于 false；大于 true
	 */
	public static boolean checkStockMinTime(Date minDate) {
		boolean flag = Boolean.TRUE;
		if (!checkObjectIsNull(minDate)) {
			long minDateLong = minDate.getTime();
			long curDateLong = new Date().getTime();
			if (curDateLong > minDateLong) {
				flag = Boolean.FALSE;
			}
		}
		return flag;
	}

	/**
	 * 获取最大日期
	 * @param dateList
	 * @return Date
	 */
	public static Date getMaxDate(List<Date> dateList) {
		if (!checkCollectionIsNull(dateList)) {
			Date maxDate = null;
			for (Date date : dateList) {
				if (null == date) {
					continue;
				}
				if (null == maxDate) {
					maxDate = date;
				}
				long maxDateLong = maxDate.getTime();
				long curDateLong = date.getTime();
				if (curDateLong > maxDateLong) {
					maxDate = date;
				}
			}
			return maxDate;
		}
		return null;
	}

	/**
	 * 获取两个日期相隔天数
	 * @param startDate
	 * @param endDate
	 * @return Long 相隔天数
	 */
	public static Long getDateDiffDay(Date startDate, Date endDate) {
		if (!checkObjectIsNull(startDate, endDate) && startDate.getTime() < endDate.getTime()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			try {
				startDate = sdf.parse(sdf.format(startDate));
				endDate = sdf.parse(sdf.format(endDate));
			} catch (Exception e) {
				logger.error("获取两个日期相隔天数转换日期格式异常；startDate:{},endDate:{}", startDate, endDate, e);
				throw new StockException(StockExceptionCode.STOCK_ERR_MSG);
			}
			Long startDateLong = startDate.getTime();
			Long endDateLong = endDate.getTime();

			long dayLong = 86400000; //(24 * 60 * 60 * 1000)
			long diffDay = (endDateLong - startDateLong) / dayLong;
			if (logger.isDebugEnabled()) {
				logger.debug("计算两个日期相隔天数；startDate:{},endDate:{} 相隔天数:{}", startDate, endDate, diffDay);
			}
			return diffDay;
		}
		return null;
	}

	/**
	 * 处理日期添加指定天后的整形时间
	 * @param date
	 * @param addDay
	 * @return Integer
	 */
	public static Integer dateAddDay(Date date, Integer addDay) {
		if (!checkObjectIsNull(date, addDay)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, addDay);

			Integer addDayInt = getStockDateInteger(calendar.getTime());
			if (logger.isDebugEnabled()) {
				logger.debug("处理日期添加指定天后的整形时间;dateAddDay date:{},addDayInt:{} ", date, addDayInt);
			}
			return addDayInt;
		}
		return null;
	}

	/**
	 * 日期添加指定月份后的最终日期整形格式
	 * @param date
	 * @param addMonth
	 * @return Integer
	 */
	public static Integer dateAddMonthMaxDay(Date date, Integer addMonth) {
		if (!checkObjectIsNull(date, addMonth)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, addMonth);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			Integer addMonthInt = getStockDateInteger(calendar.getTime());
			if (logger.isDebugEnabled()) {
				logger.debug("日期添加指定月份后的最后日期整形格式; date:{},addMonthInt:{} ", date, addMonthInt);
			}
			return addMonthInt;
		}
		return null;
	}

	/**
	 * 获取当前日期三个月后的最后一天
	 * 
	 * @return Date
	 */
	public static Date getThreeMonthDate() {
		Integer dateint = dateAddMonthMaxDay(new Date(), 3);
		return integerConvertDate(dateint);
	}

	/**
	 * 获取日期添加指定月份后的每天日期整形格式
	 * @param date
	 * @param addMonth
	 * @return Integer[] 整形格式：20160101
	 */
	public static Integer[] dateAddMonthDayArr(Date date, Integer addMonth) {
		if (!checkObjectIsNull(date, addMonth)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, addMonth);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			Date endDate = calendar.getTime();

			Long diffDay = getDateDiffDay(date, endDate);
			if (checkLongIsNull(diffDay, true)) {
				return null;
			}

			int iteraNum = Integer.parseInt(diffDay.toString()) + 1;
			calendar.setTime(date);
			int count = 0;
			Integer[] retDayArr = new Integer[iteraNum];
			while (count < iteraNum) {
				if (count > 0) {
					calendar.add(Calendar.DAY_OF_MONTH, 1);
				}
				Integer addDayInt = getStockDateInteger(calendar.getTime());
				retDayArr[count++] = addDayInt;
			}

			if (logger.isDebugEnabled()) {
				logger.debug("获取日期添加指定月份后的每天日期整形格式;dateAddMonthDayArr date:{},returnDay:{} ", date, retDayArr);
			}
			return retDayArr;
		}
		return null;
	}

	/**
	 * 获取产品可以日期范围内库存可操作整形时间
	 * @param sDate
	 * @param eDate
	 * @return Integer[] 产品有效期时间整形格式如：20160919
	 */
	public static Integer[] dateBetweenIntegerTime(Date sDate, Date eDate) {
		if (!checkObjectIsNull(sDate, eDate)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sDate);

			Long diffDay = getDateDiffDay(sDate, eDate);
			if (checkLongIsNull(diffDay, true)) {
				return null;
			}

			int iteraNum = Integer.parseInt(diffDay.toString()) + 1;
			int count = 0;
			Integer[] retDayArr = new Integer[iteraNum];
			while (count < iteraNum) {
				if (count > 0) {
					calendar.add(Calendar.DAY_OF_MONTH, 1);
				}
				Integer addDayInt = getStockDateInteger(calendar.getTime());
				retDayArr[count++] = addDayInt;
			}

			if (logger.isDebugEnabled()) {
				logger.debug("获取两个日期之间的日期整形格式; sDate:{},eDate:{},returnDay:{} ", sDate, eDate, retDayArr);
			}
			return retDayArr;
		}
		return null;
	}

	/**
	 * 设置异常返回结果
	 * @param e
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	public static void convertException(Throwable e, Result result) {
		if (checkObjectIsNull(result) || checkObjectIsNull(e)) {
			return;
		}

		if (e instanceof StockException) {
			result.setErrorCode(((StockException) e).getErrCode());
			result.setErrorMsg(((StockException) e).getMessage());
		} else {
			result.setErrorCode(StockExceptionCode.STOCK_ERR_CODE);
			result.setErrorMsg(StockExceptionCode.STOCK_ERR_MSG);
		}
	}

	/**
	 * 设置错误码
	 * @param code
	 * @param errorMsg
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	public static void setResultErr(Integer code, String errorMsg, Result result) {
		if (checkObjectIsNull(result) || checkIntegerIsNull(code, true) || checkStringIsNullStrict(errorMsg)) {
			return;
		}

		result.setErrorCode(code);
		result.setErrorMsg(errorMsg);
	}

	/**
	 * 设置默认的参数错误错误码
	 * @param result
	 * @param errorMsg
	 */
	@SuppressWarnings("rawtypes")
	public static void setParamErr(Result result, String errorMsg) {
		if (checkObjectIsNull(result)) {
			return;
		}
		if (checkStringIsNullStrict(errorMsg)) {
			errorMsg = StockExceptionCode.PARAM_ERR_MSG;
		}
		result.setErrorCode(StockExceptionCode.PARAM_ERR_CODE);
		result.setErrorMsg(errorMsg);
	}

	/**
	 * 设置默认的参数错误错误码
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	public static void setParamErr(Result result) {
		setParamErr(result, StockExceptionCode.PARAM_ERR_MSG);
	}

	/**
	 * 返回前端展示的时间格式
	 * @param time
	 * @return String
	 */
	public static String timeConvert(int time) {
		if (time < 0 || (time > 2400)) {
			return null;
		}
		String date = String.valueOf(time);
		StringBuffer strBuffer = new StringBuffer();
		if (date.length() <= 2) {
			strBuffer.append("00").append(":").append(date.length() < 2 ? "0" + date : date);
			return strBuffer.toString();
		}
		int midSep = date.length() - 2;
		String startT = date.substring(0, midSep);
		startT = startT.length() == 1 ? "0" + startT : startT;
		String endT = date.substring(midSep);

		strBuffer.append(startT).append(":").append(endT);
		return strBuffer.toString();
	}

	public static String timerConvertor(Integer timer) {
		StringBuilder sb = new StringBuilder();
		String str = String.valueOf(timer);
		int length = str.length();
		sb.append(str);
		for (int i = 0; i < 4 - length; i++) {
			sb.insert(0, "0", 0, 1);
		}
		return sb.toString().intern();
	}

	public static int getLinePos(String prefixSeat) {
		int linePos = 0;
		if (CommonUtils.checkStringIsNullStrict(prefixSeat)) {
			return linePos;
		}
		char[] chars = prefixSeat.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			String cs = chars[i] + "";
			if (cs.matches("^\\d$")) {
				linePos = Integer.parseInt(prefixSeat.substring(i));
				break;
			}
		}
		return linePos;
	}

	public static String getLineName(String prefixSeat) {
		if (CommonUtils.checkStringIsNullStrict(prefixSeat)) {
			return null;
		}
		String lineName = "";
		char[] chars = prefixSeat.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			String cs = chars[i] + "";
			if (cs.matches("^\\d$")) {
				lineName = prefixSeat.substring(0, i);
				break;
			}
		}
		return lineName;
	}

	/**
	 * 
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {

	}
}
