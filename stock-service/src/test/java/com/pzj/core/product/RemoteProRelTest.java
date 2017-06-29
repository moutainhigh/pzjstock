package com.pzj.core.product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pzj.core.product.model.area.AreaQueryReqModel;
import com.pzj.core.product.model.area.TheaterAreaDetailRespModel;
import com.pzj.core.product.model.area.TheaterAreaReqModel;
import com.pzj.core.product.model.area.TheaterAreaRespModel;
import com.pzj.core.product.model.assign.CalendarAssignReqModel;
import com.pzj.core.product.model.assign.CalendarAssignRespModel;
import com.pzj.core.product.model.assign.TheaterAssignRespModel;
import com.pzj.core.product.model.assign.TheaterScreeingAssignReqModel;
import com.pzj.core.product.model.screeings.ArtSpuScreeingOrderModel;
import com.pzj.core.product.model.screeings.ScreeingAreaReqModel;
import com.pzj.core.product.model.screeings.ScreeingAreaRespModel;
import com.pzj.core.product.model.screeings.ScreeingsModel;
import com.pzj.core.product.model.screeings.ScreeingsQueryRequestModel;
import com.pzj.core.product.model.screeings.TheaterScreeingOrderReqModel;
import com.pzj.core.product.model.screeings.TheaterScreeingReqModel;
import com.pzj.core.product.model.screeings.TheaterScreeingRespModel;
import com.pzj.core.product.model.seat.SeatReqModel;
import com.pzj.core.product.model.seat.SeatRespModel;
import com.pzj.core.product.service.AreaQueryService;
import com.pzj.core.product.service.AssignedOrderQueryService;
import com.pzj.core.product.service.ScreeingsQueryService;
import com.pzj.core.product.service.ScreeingsService;
import com.pzj.core.product.service.SeatCharService;
import com.pzj.core.stock.model.QueryStockByShowReqModel;
import com.pzj.core.stock.model.StockModel;
import com.pzj.core.stock.service.StockQueryService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.entity.QueryResult;

public class RemoteProRelTest {
	private final Logger logger = LoggerFactory.getLogger(ScreeingsServiceTest.class);

	private ScreeingsService screeingsService;
	private ScreeingsQueryService screeingsQueryService;
	private AreaQueryService areaQueryService;
	private AssignedOrderQueryService assignedOrderQueryService;
	private StockQueryService stockQueryService;
	private SeatCharService seatCharService;

	static ApplicationContext context = null;

	@BeforeClass
	public static void setUpClass() {
		context = new ClassPathXmlApplicationContext("applicationContext-test.xml");
		System.out.println(context);
	}

	@Before
	public void setUp() {
		screeingsService = context.getBean(ScreeingsService.class);
		screeingsQueryService = context.getBean(ScreeingsQueryService.class);
		areaQueryService = context.getBean(AreaQueryService.class);
		assignedOrderQueryService = context.getBean(AssignedOrderQueryService.class);
		stockQueryService = context.getBean(StockQueryService.class);

		seatCharService = context.getBean(SeatCharService.class);
	}

	//---------------------远程场次接口测试开始--------------------------------
	//	@Test
	public void testTheaterScreeingsDetail() {
		Long screeingId = 1130L;
		Result<ScreeingsModel> screeing = screeingsQueryService.queryScreeingDetail(screeingId, null);
		logger.info("testTheaterScreeingsDetail result : {}", JSONConverter.toJson(screeing));
	}

	//	@Test
	public void testQueryTheaterScreeing() {
		TheaterScreeingReqModel reqModel = new TheaterScreeingReqModel();
		reqModel.setSupplierId(2216619741563731L);
		reqModel.setCurrentPage(1);
		reqModel.setPageSize(3);
		Result<QueryResult<TheaterScreeingRespModel>> result = screeingsQueryService.queryTheaterScreeings(reqModel,
				null);
		logger.info("testQueryTheaterScreeing result : {}", JSONConverter.toJson(result));
	}

	//	@Test
	public void testQueryScreeingTheaterOrder() {
		TheaterScreeingOrderReqModel reqModel = new TheaterScreeingOrderReqModel();
		reqModel.setSupplierId(2216619741563734L);
		reqModel.setShowTime(new Date());
		Result<QueryResult<ArtSpuScreeingOrderModel>> result = screeingsQueryService.queryArtSpuScreeingsOrderByPage(
				reqModel, null);

		System.out.println("=========testQueryScreeingTheaterOrder" + JSONConverter.toJson(result));
	}

	//	@Test
	public void areaScreeingQueryTest() {
		Long screeingId = 1755L;
		Long areaId = 3863390235656673L;
		List<Long> screeingIds = new ArrayList<Long>();
		List<Long> areaIds = new ArrayList<Long>();
		screeingIds.add(screeingId);
		areaIds.add(areaId);
		ScreeingAreaReqModel reqModel = new ScreeingAreaReqModel();
		reqModel.setAreaIds(areaIds);
		reqModel.setScreeingIds(screeingIds);
		Result<ScreeingAreaRespModel> result = screeingsQueryService.queryScreeingAreaBaseInfo(reqModel, null);
		logger.info("queryScreeingAreaBaseInfo result : {}", JSONConverter.toJson(result));
	}

	//	@Test
	public void queryScreeingByIdTest() {
		Long screeingId = 1755L;
		ScreeingsQueryRequestModel model = new ScreeingsQueryRequestModel();
		model.setScreeingsId(screeingId);
		Result<ScreeingsModel> result = screeingsQueryService.queryScreeingsById(model, new ServiceContext());
		logger.info("queryScreeingsById result : {}", JSONConverter.toJson(result));
	}

	//---------------------远程区域接口测试开始--------------------------------
	//	@Test
	public void testQueryAreaDetail() {
		Long supplierId = 123456789L, theaterId = 22345678L;
		TheaterAreaReqModel reqModel = new TheaterAreaReqModel();
		reqModel.setSupplierId(supplierId);
		reqModel.setTheaterId(theaterId);
		Result<TheaterAreaDetailRespModel> result = areaQueryService.queryAreaDetail(reqModel, null);

		System.out.println("=====testQueryAreaDetail" + JSONConverter.toJson(result));

	}

	//---------------------远程待分配接口测试开始--------------------------------
	//	@Test
	public void queryCalendarAssignIden() {
		CalendarAssignReqModel reqModel = new CalendarAssignReqModel();
		reqModel.setSupplierId(111222L);
		reqModel.setCalendarDate(201703);
		Result<CalendarAssignRespModel> result = assignedOrderQueryService.queryCalendarAssignIden(reqModel, null);
		logger.info("queryCalendarAssignIden result:{}", JSONConverter.toJson(result));
	}

	//	@Test
	public void countAssignSeatTotal() {
		Long supplierId = 111222L;
		Result<Integer> result = assignedOrderQueryService.countAssignSeatTotal(supplierId, null);
		logger.info("countAssignSeatTotal result:{}", JSONConverter.toJson(result));
	}

	//	@Test
	public void queryArtSpuAssignCountOrders() {
		TheaterScreeingAssignReqModel reqModel = new TheaterScreeingAssignReqModel();
		reqModel.setSupplierId(3121242274529281L);
		String time = "2017-03-01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		reqModel.setShowTime(date);
		Result<TheaterAssignRespModel> result = assignedOrderQueryService.queryArtSpuAssignCountOrders(reqModel, null);
		System.out.println("queryArtSpuAssignCountOrders:" + JSONConverter.toJson(result));
	}

	//	@Test
	public void queryArea() {
		AreaQueryReqModel areaQueryReqModel = new AreaQueryReqModel();
		areaQueryReqModel.setSupplierId(2216619741563911l);
		areaQueryReqModel.setCurrentPage(1);
		areaQueryReqModel.setPageSize(1);
		Result<QueryResult<TheaterAreaRespModel>> result = areaQueryService.queryAreas(areaQueryReqModel,
				new ServiceContext());
		System.out.println(JSONConverter.toJson(result));
	}

	//	@Test
	public void testQueryShowStock() {
		QueryStockByShowReqModel reqModel = new QueryStockByShowReqModel();
		reqModel.setAreaId(220L);
		reqModel.setScreeingId(1221L);
		Long time = 1501344000000L;
		Date date = new Date(time);
		reqModel.setShowTime(date);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(1501344000000L);
		//		calendar
		Result<StockModel> result = stockQueryService.queryStockByShow(reqModel, null);
		System.out.println("==========testQueryShowStock==========" + JSONConverter.toJson(result));
	}

	//查询座位图入参：{"areaId":83,"operateUserId":4146470091948033,"scenicId":869740991778754560,"screeingId":63,"showTime":1496246400000}
	@Test
	public void queryAreaSeatchartTest() {
		SeatReqModel seatReq = new SeatReqModel();
		seatReq.setScenicId(869740991778754560L);
		seatReq.setScreeingId(63L);
		seatReq.setAreaId(83L);
		seatReq.setShowTime(new Date());
		seatReq.setOperateUserId(4146470091948033L);
		Result<ArrayList<SeatRespModel>> result = seatCharService.queryNewestAreaSeatchart(seatReq, null);
		System.out.println("queryNewestAreaSeatchart:" + JSONConverter.toJson(result));
	}
}
