/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.pzj.core.product;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

import com.pzj.commons.utils.DateUtils;
import com.pzj.core.product.enums.LockSeatType;
import com.pzj.core.product.enums.RecordCategory;
import com.pzj.core.product.model.OccupyStockReqModel;
import com.pzj.core.product.model.OccupyStockReqsModel;
import com.pzj.core.product.model.OccupyStockResponse;
import com.pzj.core.product.model.ReleaseStockReqModel;
import com.pzj.core.product.model.ReleaseStockReqsModel;
import com.pzj.core.product.model.seat.SeatInfoModel;
import com.pzj.core.product.model.seat.SeatRespModel;
import com.pzj.core.product.model.seatRecord.SeatRecordCreateReqModel;
import com.pzj.core.product.model.seatRecord.SeatRecordReqModel;
import com.pzj.core.product.model.seatRecord.SeatRecordUpdateReqModel;
import com.pzj.core.product.model.statistics.AreaCollectRespModel;
import com.pzj.core.product.service.SeatRecordQueryService;
import com.pzj.core.product.service.SeatRecordService;
import com.pzj.framework.armyant.anno.OneCase;
import com.pzj.framework.armyant.anno.TestData;
import com.pzj.framework.armyant.junit.spring.ArmyantSpringRunner;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.idgen.IDGenerater;

/**
 * 
 * @author Administrator
 * @version $Id: SeatRecordServiceTest.java, v 0.1 2017年3月16日 下午3:24:56 Administrator Exp $
 */
@RunWith(ArmyantSpringRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/applicationContext.xml" })
public class SeatRecordServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(SeatRecordServiceTest.class);
	@Resource
	private SeatRecordService seatRecordService;
	@Resource
	private SeatRecordQueryService seatRecordQueryService;

	//@Test
	public void createSeatRecords() {
		SeatRecordCreateReqModel createReqModel = new SeatRecordCreateReqModel();
		createReqModel.setScreeingId(10l);
		createReqModel.setTheaterDate(DateUtils.parseDate("2017-04-26", "yyyy-MM-dd"));
		createReqModel.setOperateUserId(2216619736763787l);
		createReqModel.setLockSeatType(LockSeatType.TMP_VALID);

		List<SeatInfoModel> seatInfoModels = new ArrayList<>();
		SeatInfoModel seatInfoModel1 = new SeatInfoModel();
		seatInfoModel1.setSeatId(3932147874274651l);
		seatInfoModel1.setSeatName("座位963");
		seatInfoModel1.setAreaId(9l);
		SeatInfoModel seatInfoModel2 = new SeatInfoModel();
		seatInfoModel2.setSeatId(3932147874274674l);
		seatInfoModel2.setSeatName("座位964");
		seatInfoModel2.setAreaId(9l);
		seatInfoModels.add(seatInfoModel1);
		seatInfoModels.add(seatInfoModel2);
		createReqModel.setSeatInfos(seatInfoModels);
		Result<Boolean> result = seatRecordService.lockingSeat(createReqModel, new ServiceContext());
		logger.info("锁座返回结果{}", JSONConverter.toJson(result));

	}

	//@Test
	public void releaseSeat() {
		SeatRecordUpdateReqModel updateReqModel = new SeatRecordUpdateReqModel();
		updateReqModel.setScreeingId(10l);
		updateReqModel.setTheaterDate(DateUtils.parseDate("2017-05-04", "yyyy-MM-dd"));
		updateReqModel.setOperateUserId(2216619736763787l);
		updateReqModel.setAreaId(9l);
		Result<Boolean> result = seatRecordService.releaseSeat(updateReqModel, new ServiceContext());
		logger.info("释放座位返回结果{}", JSONConverter.toJson(result));

	}

	//	@Test
	public void checkavailableSeatRecord() {
		Result<Boolean> result = seatRecordService.checkavailableSeatRecord(new ServiceContext());
		logger.info("自动释放座位返回结果{}", JSONConverter.toJson(result));

	}

	//@Test
	public void querySeatStateByRecord() {
		SeatRecordReqModel recordReqModel = new SeatRecordReqModel();
		recordReqModel.setScreeingId(321l);
		recordReqModel.setTheaterDate(DateUtils.parseDate("2017-03-16", "yyyy-MM-dd"));
		recordReqModel.setRecordCategory(RecordCategory.LOCKING);
		Result<ArrayList<SeatRespModel>> result = seatRecordQueryService.querySeatStateByRecord(recordReqModel,
				new ServiceContext());
		logger.info("查询占座记录返回结果{}", JSONConverter.toJson(result));

	}

	//@Test
	public void queryAreaCollects() {
		SeatRecordReqModel recordReqModel = new SeatRecordReqModel();
		recordReqModel.setScreeingId(321l);
		recordReqModel.setTheaterDate(DateUtils.parseDate("2017-03-16", "yyyy-MM-dd"));
		Result<ArrayList<AreaCollectRespModel>> result = seatRecordQueryService.queryAreaCollects(recordReqModel,
				new ServiceContext());
		logger.info("座位统计返回结果{}", JSONConverter.toJson(result));
	}

	@Test
	@OneCase("/com/pzj/core/product/SeatRecordService/occupyStock.json")
	public void occupyStock(@TestData OccupyStockReqsModel occupyStockReqModel) {
		//setTranscationId(occupyStockReqModel);
		System.out.println("占库存数量：" + occupyStockReqModel.getOccupyStockReqs().get(0).getOutQuantity());

		occupyStockReqModel.getOccupyStockReqs().get(0).setOutQuantity(1);

		Result<OccupyStockResponse> result = seatRecordService.occupyStock(occupyStockReqModel);

		assertTrue(result.isOk());
		assertNotNull(result.getData());

		System.out.println(JSONConverter.toJson(result));
	}

	IDGenerater idGenerater = new IDGenerater();

	private void setTranscationId(OccupyStockReqsModel occupyStockReqModel) {
		List<OccupyStockReqModel> occupyStockReqs = occupyStockReqModel.getOccupyStockReqs();
		for (OccupyStockReqModel occupy : occupyStockReqs) {
			occupy.setTransactionId(String.valueOf(idGenerater.nextId()));
		}
	}

	//	@Test
	@OneCase("/com/pzj/core/product/SeatRecordService/occupyStock.json")
	public void occupyStockForDock(@TestData OccupyStockReqsModel occupyStockReqModel) {

		Result<Boolean> result = seatRecordService.occupyStockForDock(occupyStockReqModel);

		assertNotNull(result);
		assertTrue(result.isOk());
		assertNotNull(result.getData());

		System.out.println(JSONConverter.toJson(result));
	}

	//	@Test
	public void releaseStock() {

		List<ReleaseStockReqModel> lists = new ArrayList<ReleaseStockReqModel>();

		{
			ReleaseStockReqModel model = new ReleaseStockReqModel();
			model.setProductId(855377373078040576L);
			model.setStockNum(1);
			lists.add(model);
		}
		{
			ReleaseStockReqModel model2 = new ReleaseStockReqModel();
			model2.setProductId(855377373078040576L);
			model2.setStockNum(1);
			//lists.add(model2);
		}

		ReleaseStockReqsModel reqsModel = new ReleaseStockReqsModel();
		reqsModel.setTransactionId("1173417043600035");
		reqsModel.setReleaseStockReqs(lists);

		Result<Boolean> result = seatRecordService.releaaseStock(reqsModel, new ServiceContext());
		logger.info("释放正常占座:{}", JSONConverter.toJson(result));

	}

}
