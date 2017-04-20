package com.pzj.core.stock.converter;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.pzj.core.stock.entity.SkuRuleRel;
import com.pzj.core.stock.model.result.SkuStockRelationResult;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 库存转换器.
 * @author YRJ
 *
 */
@Component(value = "skuRuleRelConverter")
public class SkuRuleRelConverter implements ObjectConverter<ArrayList<SkuStockRelationResult>, ServiceContext, ArrayList<SkuRuleRel>> {

    @Override
    public ArrayList<SkuRuleRel> convert(ArrayList<SkuStockRelationResult> list, ServiceContext context) {
        return null == list || list.size() == 0 ? null : skuRels2StockRels(list);
    }

    private ArrayList<SkuRuleRel> skuRels2StockRels(ArrayList<SkuStockRelationResult> skuRels) {

        ArrayList<SkuRuleRel> stockRels = new ArrayList<SkuRuleRel>(skuRels.size());

        for (SkuStockRelationResult skuRel : skuRels) {
            if (skuRel.getStockRuleId() > 0) {
                stockRels.add(skuRel2StockRel(skuRel));
            }
        }
        return stockRels;
    }

    private SkuRuleRel skuRel2StockRel(SkuStockRelationResult skuRel) {
        SkuRuleRel stockRel = new SkuRuleRel();

        stockRel.setProductId(skuRel.getProductId());
        stockRel.setRuleId(skuRel.getStockRuleId());

        return stockRel;
    }

}
