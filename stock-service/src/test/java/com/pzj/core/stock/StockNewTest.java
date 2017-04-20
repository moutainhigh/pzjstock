package com.pzj.core.stock;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

import com.pzj.core.stock.model.OccupyStockRequestModel;
import com.pzj.core.stock.service.StockService;
import com.pzj.framework.armyant.anno.OneCase;
import com.pzj.framework.armyant.junit.spring.ArmyantSpringRunner;

@RunWith(ArmyantSpringRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/applicationContext.xml" })
public class StockNewTest {
	private static final Logger logger = LoggerFactory.getLogger(StockNewTest.class);
	@Resource
	private StockService stockService;

	@Test
	@OneCase("/com/pzj/core/stock/testStockNewOccupy.json")
	public void testOccupy(List<OccupyStockRequestModel> orderStockModelList) {
		//		stockService.testOperateStock(orderStockModelList);
	}
}
