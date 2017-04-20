package com.pzj.core.product;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.pzj.core.product.model.seat.CreateSeatCharReqModel;
import com.pzj.core.product.model.seat.ModifySeatCharReqModel;
import com.pzj.core.product.model.seat.SeatReqModel;
import com.pzj.core.product.model.seat.SeatRespModel;
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
		seatReq.setScenicId(1L);
		seatReq.setScreeingId(1L);
		seatReq.setShowTime(new Date());
		Result<ArrayList<SeatRespModel>> result = seatCharService.queryNewestTheaterSeatchart(seatReq, null);
		System.out.println("queryNewestTheaterSeatchart:" + JSONConverter.toJson(result));
	}

	@Test
	public void queryNewestAreaSeatchart() {
		SeatReqModel seatReq = new SeatReqModel();
		seatReq.setScenicId(1L);
		seatReq.setScreeingId(1L);
		seatReq.setAreaId(1L);
		seatReq.setShowTime(new Date());
		Result<ArrayList<SeatRespModel>> result = seatCharService.queryNewestAreaSeatchart(seatReq, null);
		System.out.println("queryNewestAreaSeatchart:" + JSONConverter.toJson(result));
	}

}
