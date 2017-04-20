package com.pzj.core.product.screeings;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.entity.PageEntity;
import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.model.screeings.ArtSpuScreeingOrderModel;
import com.pzj.core.product.model.screeings.ScreeingOrderRespModel;
import com.pzj.core.product.model.screeings.TheaterScreeingOrderReqModel;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.model.StockRulesDateReqModel;
import com.pzj.core.stock.stockquery.QueryStockByRuleEngine;

@Component("screeingOrderQueryEngine")
public class ScreeingOrderQueryEngine {

	@Resource(name = "queryStockByRuleEngine")
	private QueryStockByRuleEngine queryStockByRuleEngine;
	@Resource(name = "screeingsQueryByParamEngine")
	private ScreeingsQueryByParamEngine screeingsQueryByParamEngine;

	public List<ArtSpuScreeingOrderModel> queryTheaterScreeingsOrder(TheaterScreeingOrderReqModel reqModel,
			Set<Long> theaterIds, List<Long> screeingIds, Map<Long, List<Long>> screeingRules) {
		PageEntity pageEntity = new PageEntity(reqModel.getCurrentPage(), reqModel.getPageSize());
		List<Screeings> screeingPage = screeingsQueryByParamEngine.queryScreeingOrders(pageEntity, theaterIds,
				screeingIds);
		if (CommonUtils.checkCollectionIsNull(screeingPage)) {
			return null;
		}

		Map<Long, List<ScreeingOrderRespModel>> screeingOrderResps = new HashMap<Long, List<ScreeingOrderRespModel>>();
		List<ScreeingOrderRespModel> screeingOrders = null;
		ScreeingOrderRespModel screeingOrderResp = null;
		Long key = null;
		for (Screeings screeing : screeingPage) {
			key = screeing.getScenicId();
			if (screeingOrderResps.containsKey(key)) {
				screeingOrders = screeingOrderResps.get(key);
			} else {
				screeingOrders = new ArrayList<ScreeingOrderRespModel>();
			}
			screeingOrderResp = new ScreeingOrderRespModel();
			screeingOrderResp.setId(screeing.getId());
			screeingOrderResp.setName(screeing.getName());
			screeingOrderResp.setScenicId(key);
			screeingOrderResp.setStartTime(CommonUtils.timeConvert(screeing.getStartTime()));
			screeingOrderResp.setEndTime(CommonUtils.timeConvert(screeing.getEndTime()));
			screeingOrders.add(screeingOrderResp);
			screeingOrderResps.put(key, screeingOrders);

			computeScreeningOrder(reqModel.getShowTime(), screeingRules.get(screeing.getId()), screeingOrderResp);
		}

		List<ArtSpuScreeingOrderModel> theaterScreeingOrders = new ArrayList<ArtSpuScreeingOrderModel>();
		ArtSpuScreeingOrderModel theaterScreeingOrder = null;
		Iterator<Entry<Long, List<ScreeingOrderRespModel>>> theaterScreeings = screeingOrderResps.entrySet().iterator();
		while (theaterScreeings.hasNext()) {
			Map.Entry<Long, List<ScreeingOrderRespModel>> theaterScreeing = theaterScreeings.next();
			theaterScreeingOrder = new ArtSpuScreeingOrderModel();
			//			theaterScreeingOrder.setTheaterId(theaterScreeing.getKey());
			theaterScreeingOrder.setSupplierId(reqModel.getSupplierId());
			theaterScreeingOrder.setScreeingOrders(theaterScreeing.getValue());
			theaterScreeingOrders.add(theaterScreeingOrder);
		}

		return theaterScreeingOrders;
	}

	private void computeScreeningOrder(Date showTime, List<Long> ruleIds, ScreeingOrderRespModel screeingOrderResp) {
		StockRulesDateReqModel stockRulesDate = new StockRulesDateReqModel();
		stockRulesDate.setRuleIds(ruleIds);
		stockRulesDate.setShowTime(showTime);

		List<Stock> stocks = queryStockByRuleEngine.queryStockByRules(stockRulesDate);
		if (CommonUtils.checkCollectionIsNull(stocks)) {
			return;
		}
		Integer saleNum = 0;
		Integer totalNum = 0;

		for (Stock stock : stocks) {
			saleNum += stock.getUsedNum();
			totalNum += stock.getTotalNum();
		}
		Double saleRate = (double) (saleNum / totalNum);
		screeingOrderResp.setSaleNum(saleNum);
		screeingOrderResp.setSaleRate(saleRate);

		return;
	}
}
