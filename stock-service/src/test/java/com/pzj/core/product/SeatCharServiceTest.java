package com.pzj.core.product;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.pzj.core.product.model.seat.CreateSeatCharReqModel;
import com.pzj.core.product.model.seat.ModifySeatCharReqModel;
import com.pzj.core.product.model.seat.SeatChartManyRespModel;
import com.pzj.core.product.model.seat.SeatReqModel;
import com.pzj.core.product.model.seat.SeatRespModel;
import com.pzj.core.product.model.seat.TheaterSeatChartRespModel;
import com.pzj.core.product.service.SeatCharService;
import com.pzj.framework.armyant.anno.OneCase;
import com.pzj.framework.armyant.anno.TestData;
import com.pzj.framework.armyant.junit.spring.ArmyantSpringRunner;
import com.pzj.framework.context.Result;
import com.pzj.framework.converter.JSONConverter;

/**
 * Created by Administrator on 2017-3-31.
 */
@RunWith(ArmyantSpringRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/applicationContext.xml" })
public class SeatCharServiceTest {
	@Resource
	private SeatCharService seatCharService;

	//	@Test
	@OneCase("/com/pzj/core/product/SeatCharService/createSeatChar.json")
	public void createSeatChar(@TestData CreateSeatCharReqModel createSeatCharReqModel) {
		Result<Boolean> result = seatCharService.createSeatChar(createSeatCharReqModel);

		assertNotNull(result);
		assertTrue(result.getData());
	}

	//	@Test
	@OneCase("/com/pzj/core/product/SeatCharService/modifySeatChar.json")
	public void modifySeatChar(@TestData ModifySeatCharReqModel modifySeatCharReqModel) {
		Result<Boolean> result = seatCharService.modifySeatChar(modifySeatCharReqModel);

		assertNotNull(result);
		assertTrue(result.getData());
	}

	//	@Test
	public void totalAreaSeat() {
		Long areaId = 999999L;
		Result<Integer> result = seatCharService.queryAreaSeatTotal(areaId, null);

		System.out.println("totalAreaSeat:" + JSONConverter.toJson(result));
	}

	//	@Test
	public void queryNewestTheaterSeatchart() {
		SeatReqModel seatReq = new SeatReqModel();
		seatReq.setScenicId(814306872523067392L);
		seatReq.setScreeingId(10L);
		seatReq.setAreaId(9L);
		seatReq.setOperateUserId(2216619736763787L);
		Long time = 1494259200000L;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		Date date = c.getTime();
		seatReq.setShowTime(date);
		SimpleDateFormat sdf = null;
		Date testDate = null;
		try {
			String testTime = "2017-05-05 00:00:00";
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			testDate = sdf.parse(testTime);
			seatReq.setShowTime(testDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String timeStr = sdf.format(date);
		System.out.println("timeStr----" + timeStr);
		//{"areaId":9,"operateUserId":2216619736763787,"scenicId":814306872523067392,"screeingId":10,"showTime":1483200000000}
		//{"areaId":9,"operateUserId":2216619736763787,"scenicId":814306872523067392,"screeingId":10,"showTime":1494259200000}

		Result<ArrayList<SeatRespModel>> result = seatCharService.queryNewestTheaterSeatchart(seatReq, null);
		System.out.println("queryNewestTheaterSeatchart:" + JSONConverter.toJson(result));
	}

	//	@Test
	public void queryNewestAreaSeatchart() {
		SeatReqModel seatReq = new SeatReqModel();
		seatReq.setScenicId(1L);
		seatReq.setScreeingId(1L);
		seatReq.setAreaId(1L);
		seatReq.setShowTime(new Date());
		Result<ArrayList<SeatRespModel>> result = seatCharService.queryNewestAreaSeatchart(seatReq, null);
		System.out.println("queryNewestAreaSeatchart:" + JSONConverter.toJson(result));
	}

	@Test
	@OneCase("/com/pzj/core/product/SeatCharService/querySeatChartBySeatId.json")
	public void querySeatChartBySeatId(@TestData List<Long> seatIds) {
		Result<SeatChartManyRespModel> result = seatCharService.querySeatChartBySeatId(seatIds);
		System.out.println("querySeatChartBySeatId result:" + JSONConverter.toJson(result));
	}

	//	@Test
	public void theaterSeatchartTest() {
		Long theaterId = 814314939503448064L;
		Result<ArrayList<TheaterSeatChartRespModel>> result = seatCharService.queryTheaterSeatchart(theaterId, null);

		System.out.println("queryTheaterSeatchart result:" + JSONConverter.toJson(result));
	}
}
