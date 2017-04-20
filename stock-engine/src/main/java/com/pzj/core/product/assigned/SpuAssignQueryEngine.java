package com.pzj.core.product.assigned;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.entity.AssignedOrder;
import com.pzj.core.product.entity.PageEntity;
import com.pzj.core.product.model.assign.AssignOrderCountRespModel;
import com.pzj.core.product.model.assign.ScreeingAreaAssignRespModel;
import com.pzj.core.product.model.assign.TheaterAssignRespModel;
import com.pzj.core.product.model.assign.TheaterScreeingAssignModel;
import com.pzj.core.product.model.assign.TheaterScreeingAssignReqModel;
import com.pzj.core.product.read.AssignedOrderReadMapper;
import com.pzj.framework.entity.QueryResult;

@Component("spuAssignQueryEngine")
public class SpuAssignQueryEngine {

	@Resource(name = "assignedOrderReadMapper")
	private AssignedOrderReadMapper assignedOrderReadMapper;

	public TheaterAssignRespModel queryArtSpuAssignCount(TheaterScreeingAssignReqModel reqModel) {
		TheaterAssignRespModel theaterAssignResp = new TheaterAssignRespModel();
		QueryResult<TheaterScreeingAssignModel> queryResult = queryArtSpuAssignPage(reqModel);
		AssignOrderCountRespModel assignOrderCount = countOrderSeat(reqModel);
		if (queryResult == null || assignOrderCount == null) {
			return null;
		}
		theaterAssignResp.setTheaterScreeingAssigns(queryResult);
		theaterAssignResp.setAssignOrderCount(assignOrderCount);
		return theaterAssignResp;
	}

	private QueryResult<TheaterScreeingAssignModel> queryArtSpuAssignPage(TheaterScreeingAssignReqModel reqModel) {
		int curPage = reqModel.getCurrentPage(), pageSize = reqModel.getPageSize();
		QueryResult<TheaterScreeingAssignModel> queryResult = new QueryResult<TheaterScreeingAssignModel>(curPage,
				pageSize);
		PageEntity pageEntity = new PageEntity(curPage, pageSize);
		List<AssignedOrder> assignedOrders = assignedOrderReadMapper.querySpuAssignByPage(pageEntity,
				reqModel.getShowTime());
		List<TheaterScreeingAssignModel> theaterScreeingAssignModels = initArtSpuAssign(assignedOrders,
				reqModel.getSupplierId());
		if (CommonUtils.checkCollectionIsNull(theaterScreeingAssignModels)) {
			return null;
		}
		Integer count = assignedOrderReadMapper.countSpuAssign(reqModel.getShowTime());
		queryResult.setRecords(theaterScreeingAssignModels);
		queryResult.setTotal(count);
		return queryResult;
	}

	private AssignOrderCountRespModel countOrderSeat(TheaterScreeingAssignReqModel reqModel) {

		AssignOrderCountRespModel assiginOrder = new AssignOrderCountRespModel();
		assiginOrder.setOrderNum(assignedOrderReadMapper.countShowOrder(reqModel.getShowTime()));
		assiginOrder.setSeatNum(assignedOrderReadMapper.countShowSeat(reqModel.getShowTime()));
		assiginOrder.setShowTime(reqModel.getShowTime());
		return assiginOrder;
	}

	private List<TheaterScreeingAssignModel> initArtSpuAssign(List<AssignedOrder> assignedOrders, Long supplierId) {
		if (CommonUtils.checkCollectionIsNull(assignedOrders)) {
			return null;
		}
		List<TheaterScreeingAssignModel> artSpuAssigns = new ArrayList<TheaterScreeingAssignModel>();
		TheaterScreeingAssignModel theaterScreeingAssignModel = null;
		Long spuId = null;
		Integer spuAssignNum = 0;
		List<ScreeingAreaAssignRespModel> assignAreaScreeings = null;
		ScreeingAreaAssignRespModel screeingAreaAssignRespModel = null;
		for (AssignedOrder assignedOrder : assignedOrders) {
			if (spuId == null || spuId.longValue() != assignedOrder.getSpuId().longValue()) {
				spuId = assignedOrder.getSpuId();
				spuAssignNum = assignedOrder.getAssignNum();
				theaterScreeingAssignModel = new TheaterScreeingAssignModel();
				theaterScreeingAssignModel.setSpuId(spuId);
				theaterScreeingAssignModel.setSpuAssignNum(spuAssignNum);
				theaterScreeingAssignModel.setSupplierId(supplierId);
				assignAreaScreeings = new ArrayList<ScreeingAreaAssignRespModel>();
				theaterScreeingAssignModel.setAssignAreaScreeings(assignAreaScreeings);
				artSpuAssigns.add(theaterScreeingAssignModel);
			}
			spuAssignNum += assignedOrder.getAssignNum();
			screeingAreaAssignRespModel = new ScreeingAreaAssignRespModel();
			screeingAreaAssignRespModel.setScreeingId(assignedOrder.getScreeningsId());
			screeingAreaAssignRespModel.setAreaId(assignedOrder.getAreaId());
			screeingAreaAssignRespModel.setScreeingName(assignedOrder.getScreName());
			screeingAreaAssignRespModel.setAreaName(assignedOrder.getAreaName());
			screeingAreaAssignRespModel.setAssignNum(assignedOrder.getAssignNum());
			assignAreaScreeings.add(screeingAreaAssignRespModel);
		}

		return artSpuAssigns;
	}
}
