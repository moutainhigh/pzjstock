/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.stock.converter.StockRulesConverter;
import com.pzj.core.stock.enums.StockRuleCategoryEnum;
import com.pzj.core.stock.model.StockRuleModel;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author dongchunfu
 * @version $Id: GenerateDefaultStockTimes.java, v 0.1 2016年8月10日 下午7:41:50 dongchunfu Exp $
 */
@Component(value = "packagResult4Category")
public class PackagResult4Category {

    @Resource(name = "stockRulesConverter")
    private StockRulesConverter stockRulesConverter;

    public StockRuleModel packagResult(List<StockRuleModel> rules, ServiceContext context) {
        if (null == rules || rules.size() == 0) {
            return null;
        }

        StockRuleModel model = new StockRuleModel();
        model.setCategories(new HashSet<String>());
        model.setMappings(new HashMap<String, ArrayList<StockRuleModel>>());

        HashSet<String> categories = model.getCategories();

        for (StockRuleModel rule : rules) {
            String categoryName = StockRuleCategoryEnum.getCategoryName(rule.getCategory());
            categories.add(categoryName);
        }

        for (String category : categories) {
            ArrayList<StockRuleModel> list = new ArrayList<StockRuleModel>();
            for (StockRuleModel rule : rules) {

                String categoryName = StockRuleCategoryEnum.getCategoryName(rule.getCategory());

                if (categoryName.equals(category)) {
                    list.add(rule);
                }

            }
            model.getMappings().put(category, list);
        }
        return model;
    }
}
