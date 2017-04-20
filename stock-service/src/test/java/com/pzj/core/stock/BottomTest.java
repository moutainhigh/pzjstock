/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.stock;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pzj.core.stock.service.StockBottomService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author dongchunfu
 * @version $Id: BotomTest.java, v 0.1 2016年8月16日 下午12:41:05 dongchunfu Exp $
 */
public class BottomTest extends BaseTest {
    @Autowired
    private StockBottomService stockBottomService;
    ServiceContext             context = ServiceContext.getServiceContext();

    @Test
    public void testautoCreateDailyStock() {
        Result<Integer> result = stockBottomService.autoCreateDailyStock(context);

        if (result.isOk()) {
            if (result.getData() != null) {
                System.out.println(result.getData());
            }
        } else {
            System.out.println(result.getErrorMsg());
        }
    }

    @Test
    public void testsyncSkuAndRuleRel() {
        Result<Integer> result = stockBottomService.syncSkuAndRuleRel(context);
        if (result.isOk()) {
            if (result.getData() != null) {
                System.out.println(result.getData());
            }
        } else {
            System.out.println(result.getErrorMsg());
        }
    }

}
