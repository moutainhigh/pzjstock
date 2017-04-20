package com.pzj.core.product.assigned;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.entity.AssignedOrder;
import com.pzj.core.product.model.assign.CalendarAssignReqModel;
import com.pzj.core.product.model.assign.CalendarAssignRespModel;
import com.pzj.core.product.read.AssignedOrderReadMapper;

@Component("queryCountAssignEngine")
public class QueryCountAssignEngine {

	@Resource(name = "assignedOrderReadMapper")
	private AssignedOrderReadMapper assignedOrderReadMapper;

	public Integer countTotalAssignSeats() {
		return assignedOrderReadMapper.countTotalAssignNum();
	}

	public CalendarAssignRespModel queryCalendarAssign(CalendarAssignReqModel reqModel) {
		String calendarDate = reqModel.getCalendarDate().toString();
		Calendar calendar = getMinDay(calendarDate);
		Date showStartDate = calendar.getTime();
		getMaxDay(calendar);
		Date showEndDate = calendar.getTime();
		List<AssignedOrder> assignedOrders = assignedOrderReadMapper.queryCalendarAssignCount(showStartDate,
				showEndDate);
		if (!CommonUtils.checkCollectionIsNull(assignedOrders)) {
			CalendarAssignRespModel calendarAssignResp = new CalendarAssignRespModel();
			List<Integer> days = new ArrayList<Integer>();
			Date travelDate = null;
			Calendar travelCalendar = Calendar.getInstance();
			for (AssignedOrder assignedOrder : assignedOrders) {
				travelDate = assignedOrder.getTravelDate();
				travelCalendar.setTime(travelDate);
				int day = travelCalendar.get(Calendar.DAY_OF_MONTH);
				if (!days.contains(day)) {
					days.add(day);
				}
			}
			calendarAssignResp.setDays(days);
			return calendarAssignResp;
		}

		return null;
	}

	private Calendar getMinDay(String calendarDate) {
		int year = Integer.parseInt(calendarDate.substring(0, 4));
		int month = Integer.parseInt(calendarDate.substring(4)) - 1;
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	private void getMaxDay(Calendar calendar) {
		int maxday = calendar.getActualMaximum(Calendar.DATE);
		calendar.set(Calendar.DAY_OF_MONTH, maxday);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
	}
}
