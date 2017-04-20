package com.pzj.core.product.screeings;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.model.screeings.ArtSpuScreeingOrderModel;
import com.pzj.core.product.model.screeings.ScreeingOrderRespModel;
import com.pzj.core.product.read.ScreeingsReadMapper;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.model.StockRulesDateReqModel;
import com.pzj.core.stock.stockquery.QueryStockByRuleEngine;

@Component("artSpuScreeingOrderQueryEngine")
public class ArtSpuScreeingOrderQueryEngine {
	@Resource(name = "queryStockByRuleEngine")
	private QueryStockByRuleEngine queryStockByRuleEngine;
	@Resource(name = "screeingsReadMapper")
	private ScreeingsReadMapper screeingsReadMapper;

	public void intArtSpuScreeingOrder(List<ArtSpuScreeingOrderModel> artSpuScreeings,
			Map<Long, List<Long>> screeingRules, Date showTime) {

		if (CommonUtils.checkCollectionIsNull(artSpuScreeings)) {
			return;
		}
		List<ScreeingOrderRespModel> screeingOrders;
		for (ArtSpuScreeingOrderModel artSpuScreeing : artSpuScreeings) {
			screeingOrders = artSpuScreeing.getScreeingOrders();
			if (CommonUtils.checkCollectionIsNull(screeingOrders)) {
				continue;
			}
			for (ScreeingOrderRespModel screeingOrder : screeingOrders) {
				//初始化场次信息
				initScreeing(screeingOrder.getId(), screeingOrder);
				//初始化预订率
				computeScreeningOrder(showTime, screeingRules.get(screeingOrder.getId()), screeingOrder);
			}
		}
	}

	private void initScreeing(Long screeingsId, ScreeingOrderRespModel screeingOrder) {
		Screeings screeings = screeingsReadMapper.selectScreeingsById(screeingsId);
		if (screeings == null) {
			return;
		}
		screeingOrder.setName(screeings.getName());
		screeingOrder.setScenicId(screeings.getScenicId());
		screeingOrder.setStartTime(CommonUtils.timeConvert(screeings.getStartTime()));
		screeingOrder.setEndTime(CommonUtils.timeConvert(screeings.getStartTime()));
	}

	private void computeScreeningOrder(Date showTime, List<Long> ruleIds, ScreeingOrderRespModel screeingOrderResp) {
		if (CommonUtils.checkCollectionIsNull(ruleIds)) {
			return;
		}
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
