package com.pzj.core.stock.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.stock.service.StockBottomService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 库存定时任务
 * @author dongchunfu
 */
@Component(value = "dailyStockJob")
public class DailyStockJob {

    private final static Logger logger = LoggerFactory.getLogger(DailyStockJob.class);

    @Resource(name = "stockBottomService")
    private StockBottomService  stockBottomService;

    public void autoCreateStockJob() {
        ServiceContext context = ServiceContext.getServiceContext();
        try {
            long startTime = System.currentTimeMillis();

            //1.同步产品与库存规则的关联关系只库存服务的临时表中：tmp_sku_rule_rel.
            if (logger.isDebugEnabled()) {
                logger.debug(" syncing sku and stock rule relation,context:{}.", context);
            }

            Result<Integer> count = stockBottomService.syncSkuAndRuleRel(context);

            if (logger.isDebugEnabled()) {
                logger.debug(" sync sku and stock rule relation success ,response:{},context:{}.", count.getData(), context);
            }

            //2.启动创建库存记录
            if (logger.isDebugEnabled()) {
                logger.debug(" auto creating stock record ,context:{}.", context);
            }

            Result<Integer> result = stockBottomService.autoCreateDailyStock(context);

            long endTime = System.currentTimeMillis();
            if (result.isOk()) {
                if (logger.isDebugEnabled()) {
                    logger.debug(" auto create stock record success,elapsedTime:{},context:{}.", endTime - startTime, context);
                }
            } else {
                logger.error("auto create stock failed ,message:{}.", result.getErrorMsg());
            }

        } catch (Throwable t) {
            logger.error("auto create stock fail.", t);
        }
    }

}
