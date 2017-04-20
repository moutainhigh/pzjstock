package com.pzj.core.stock.stockquery;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.model.StockDateListQueryModel;
import com.pzj.core.stock.read.StockReadMapper;

/**
 * 
 * @author Administrator
 * @version $Id: QueryStockByDateListEngine.java, v 0.1 2016年8月29日 下午2:43:40 Administrator Exp $
 */
@Component("queryStockByDateListEngine")
public class QueryStockByDateListEngine {

    @Autowired
    private StockReadMapper stockReadMapper;

    public List<Stock> queryStockByDateList(StockDateListQueryModel model) {
        Set<Integer> stockTimeList = new LinkedHashSet<Integer>();
        for (String stockTime : model.getStockTimeList()) {
            stockTimeList.add(Integer.parseInt(stockTime));
        }

        List<Stock> stockList = stockReadMapper.queryStockByDateList(model.getStockRuleId(), stockTimeList);
        return stockList;
    }
}
