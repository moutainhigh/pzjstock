/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock.event;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.stock.entity.StockRule;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.model.query.StockListParam;
import com.pzj.core.stock.model.result.SkuStockListResult;
import com.pzj.core.stock.service.ISkuStockService;
import com.pzj.core.stock.validator.sku.CallSkuService4CreatStockValidator;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author dongchunfu
 * @version $Id: CallSkuService4CreateStock.java, v 0.1 2016年8月10日 上午10:53:07 dongchunfu Exp $
 */
@Component(value = "callSkuService4CreateStock")
public class CallSkuService4CreateStock {

    private static final Logger                logger = LoggerFactory.getLogger(CallSkuService4CreateStock.class);

    @Resource(name = "skuStockService")
    private ISkuStockService                   skuStockService;

    @Resource(name = "callSkuService4CreatStockValidator")
    private CallSkuService4CreatStockValidator callSkuService4CreatStockValidator;

    public ArrayList<SkuStockListResult> getProductDate(List<StockRule> rules, ServiceContext context) {
        if (null == rules || rules.size() == 0) {
            return null;
        }
        List<Long> ids = getIds(rules);
        //1.调用skuService 获取相关产品的可用日期数据
        StockListParam param = new StockListParam();
        param.setStockIdList(ids);
        try {
            Result<ArrayList<SkuStockListResult>> data = skuStockService.findStockSkuByStockRuleIds(param);
            if (data.isOk()) {
                return data.getData();
            } else {
                throw new StockException("stock rule does not band product.");
            }
        } catch (Throwable t) {
            logger.error("call sku service exception,request:{},context:{}.", ids, context, t);
            throw new StockException(StockExceptionCode.STOCK_ERR_MSG);
        }

    }

    /**
     * 获取所有库存规则ID集合
     * 
     * @param rules     待提取ID库存规则集合
     * @return          库存规则ID集合
     */
    private List<Long> getIds(List<StockRule> rules) {
        if (null == rules || rules.size() == 0) {
            return null;
        }
        List<Long> ids = new ArrayList<Long>(rules.size());

        for (StockRule rule : rules) {
            ids.add(rule.getId());
        }
        return ids;
    }
}
