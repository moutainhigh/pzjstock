package com.pzj.core.stock.event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.model.QueryStockByShowReqModel;
import com.pzj.core.stock.model.result.StockRuleInfoResult;
import com.pzj.core.stock.service.ISkuStockService;
import com.pzj.framework.context.Result;

@Component("queryRuleByShow")
public class QueryRuleByShow {

	Logger logger = LoggerFactory.getLogger(QueryRuleByShow.class);
	@Resource(name = "skuStockService")
	private ISkuStockService skuStockService;

	public Long queryRuleByScreeingArea(QueryStockByShowReqModel reqModel) {
		Result<ArrayList<StockRuleInfoResult>> skuResult = skuStockService.getStockRuleInfo(reqModel.getAreaId(),
				reqModel.getScreeingId(), Boolean.TRUE);

		if (skuResult.isOk()) {
			ArrayList<StockRuleInfoResult> stockRuleInfos = skuResult.getData();
			if (CommonUtils.checkCollectionIsNull(stockRuleInfos)) {
				return null;
			}
			Long stockRuleId = null;
			for (StockRuleInfoResult stockRuleInfo : stockRuleInfos) {
				if (null != stockRuleInfo.getStockRuleId() && stockRuleInfo.getStockRuleId().longValue() > 0) {
					stockRuleId = stockRuleInfo.getStockRuleId();
					break;
				}
			}
			return stockRuleId;

		} else {
			logger.error("QueryRuleByShow queryRuleByScreeingArea call sku service failed,code:{},message:{}",
					skuResult.getErrorCode(), skuResult.getErrorMsg());
			throw new StockException(StockExceptionCode.INVOKE_OTHER_SERVICE_ERR_MSG);
		}
	}

	public Map<Long, List<Long>> queryRuleByScreeing(List<Long> screeings, Date showDate) {
		Map<Long, List<Long>> screeingRules = new HashMap<Long, List<Long>>();
		Result<ArrayList<StockRuleInfoResult>> skuResult = skuStockService.getStockRuleInfo(screeings);
		if (skuResult.isOk()) {
			Map<String, StockRuleTimeModel> stockRuleTime = getSkuDate(skuResult.getData(), Boolean.FALSE);
			if (!CommonUtils.checkMapIsNull(stockRuleTime)) {
				Integer showTime = CommonUtils.getStockDateInteger(showDate);
				Iterator<Entry<String, StockRuleTimeModel>> itera = stockRuleTime.entrySet().iterator();
				StockRuleTimeModel stockRuleTimeModel = null;
				String key = null;
				Long screeingId = null, ruleId = null;
				List<Long> stockRuleIds = null;
				while (itera.hasNext()) {
					Map.Entry<String, StockRuleTimeModel> entry = itera.next();
					stockRuleTimeModel = entry.getValue();
					key = entry.getKey();
					screeingId = Long.parseLong(key.split("#")[0]);
					ruleId = stockRuleTimeModel.getStockRuleId();
					if (CommonUtils.checkLongIsNull(ruleId, Boolean.TRUE)) {
						continue;
					}
					if (stockRuleTimeModel.getAvaiDates().contains(showTime)) {
						if (screeingRules.containsKey(screeingId)) {
							stockRuleIds = new ArrayList<Long>();
							stockRuleIds.add(ruleId);
							screeingRules.put(screeingId, stockRuleIds);
						} else {
							screeingRules.get(screeingId).add(ruleId);
						}
					}
				}
			}
		} else {
			logger.error("QueryRuleByShow queryRuleByScreeing call sku service failed,code:{},message:{}",
					skuResult.getErrorCode(), skuResult.getErrorMsg());
			throw new StockException(StockExceptionCode.INVOKE_OTHER_SERVICE_ERR_MSG);
		}
		return screeingRules;
	}

	private Map<String, StockRuleTimeModel> getSkuDate(ArrayList<StockRuleInfoResult> stockRuleInfos, Boolean isClose) {
		if (!CommonUtils.checkCollectionIsNull(stockRuleInfos)) {
			return null;
		}
		Map<String, StockRuleTimeModel> screeingRuleAvaiTimes = new HashMap<String, StockRuleTimeModel>();
		List<Date> closeDates = null;
		Set<String> delWeeks = new HashSet<String>();
		Date sDate = null, eDate = null, tempSDate, tempEDate, now = new Date(), bjsDate, bjeDate;
		String key;
		StockRuleTimeModel stockRuleTime = null;
		String[] weeks = null;
		Long areaId, screeingId;
		for (StockRuleInfoResult stockRuleInfo : stockRuleInfos) {
			if (CommonUtils.checkObjectIsNull(stockRuleInfo)) {
				continue;
			}
			tempSDate = stockRuleInfo.getStartDate();
			tempEDate = stockRuleInfo.getEndDate();

			key = stockRuleInfo.getRondaId() + "#" + stockRuleInfo.getRegionId();
			if (screeingRuleAvaiTimes.containsKey(key)) {
				stockRuleTime = screeingRuleAvaiTimes.get(key);
				sDate = stockRuleTime.getsDate();
				eDate = stockRuleTime.geteDate();
				if (isClose) {
					closeDates = stockRuleTime.getCloseDates();
					delWeeks = stockRuleTime.getDelWeeks();
				}
			} else {
				stockRuleTime = new StockRuleTimeModel();
				areaId = null; /*CommonUtils.checkStringIsNullStrict(stockRuleInfo.getRegionId()) ? 0 : Long
								.parseLong(stockRuleInfo.getRegionId());*/
				screeingId = null; /*CommonUtils.checkStringIsNullStrict(stockRuleInfo.getRondaId()) ? 0 : Long
									.parseLong(stockRuleInfo.getRondaId());*/
				stockRuleTime.setScreeingId(screeingId);
				stockRuleTime.setAreaId(areaId);
				stockRuleTime.setStockRuleId(stockRuleInfo.getStockRuleId());
				if (isClose) {
					closeDates = new ArrayList<Date>();
					stockRuleTime.setCloseDates(closeDates);
					delWeeks = new HashSet<String>();
					stockRuleTime.setDelWeeks(delWeeks);
				}
			}

			bjsDate = sDate == null ? now : sDate;
			bjeDate = eDate == null ? bjsDate : eDate;
			sDate = tempSDate.getTime() < bjsDate.getTime() ? tempSDate : sDate;
			eDate = tempEDate.getTime() < bjeDate.getTime() ? bjeDate : tempEDate;
			screeingRuleAvaiTimes.put(key, stockRuleTime);

			if (isClose) {
				closeDates.addAll(stockRuleInfo.getCloseDates());
				if (CommonUtils.checkStringIsNullStrict(stockRuleInfo.getCloseWeek())) {
					continue;
				}
				weeks = stockRuleInfo.getCloseWeek().split(",");
				delWeeks.addAll(Arrays.asList(weeks));
			}
		}

		Iterator<Entry<String, StockRuleTimeModel>> itera = screeingRuleAvaiTimes.entrySet().iterator();
		List<Integer> delWeekDays, avaiDates;
		while (itera.hasNext()) {
			Map.Entry<String, StockRuleTimeModel> entry = itera.next();
			key = entry.getKey();
			stockRuleTime = entry.getValue();
			delWeekDays = convertWeek(delWeeks);
			avaiDates = filterDateBetween(stockRuleTime.getsDate(), stockRuleTime.geteDate(),
					stockRuleTime.getCloseDates(), delWeekDays);
			stockRuleTime.setAvaiDates(avaiDates);
		}

		return screeingRuleAvaiTimes;
	}

	private List<Integer> filterDateBetween(Date sDate, Date eDate, List<Date> closeDates, List<Integer> delWeekDays) {
		List<Integer> avaiDays = new ArrayList<Integer>();
		if (!CommonUtils.checkObjectIsNull(sDate, eDate)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sDate);

			Long diffDay = CommonUtils.getDateDiffDay(sDate, eDate);
			if (CommonUtils.checkLongIsNull(diffDay, true)) {
				return null;
			}
			int count = 0, iteraNum = Integer.parseInt(diffDay.toString()) + 1;
			Boolean isClose = Boolean.FALSE;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			if (!CommonUtils.checkCollectionIsNull(closeDates)) {
				isClose = Boolean.TRUE;
			}
			String closeDay = null, calendarDay = null;
			while (count < iteraNum) {
				//过滤周
				int curWeek = calendar.get(Calendar.DAY_OF_WEEK);
				if (!CommonUtils.checkCollectionIsNull(delWeekDays) && delWeekDays.contains(curWeek)) {
					continue;
				}
				//过滤天
				if (isClose) {
					calendarDay = sdf.format(calendar.getTime());
					for (Date cDate : closeDates) {
						closeDay = sdf.format(cDate);
						if (calendarDay.equals(closeDay)) {
							continue;
						}
					}
				}
				avaiDays.add(CommonUtils.getStockDateInteger(calendar.getTime()));
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				count++;
			}
		}

		return avaiDays;
	}

	private List<Integer> convertWeek(Set<String> delWeeks) {
		if (CommonUtils.checkCollectionIsNull(delWeeks)) {
			return null;
		}
		List<Integer> weekDays = new ArrayList<Integer>();
		Iterator<String> itera = delWeeks.iterator();
		String weekDay = null;
		while (itera.hasNext()) {
			weekDay = itera.next();
			if (weekDay.equals("7")) {
				weekDays.add(1);
			} else {
				weekDays.add(Integer.parseInt(weekDay) + 1);
			}
		}
		return weekDays;
	}
}
