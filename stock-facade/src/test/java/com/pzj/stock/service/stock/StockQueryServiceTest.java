package com.pzj.stock.service.stock;

public class StockQueryServiceTest {

    //    public static void main(String[] args) {
    //        StockQueryServiceTest t = new StockQueryServiceTest();
    //        t.testQueryStockRuleById();
    //    }
    //
    //    public void testQueryStockRuleById() {
    //        ServiceContext serviceContext = ServiceContext.getServiceContext();
    //
    //        StockQueryService stockQueryService = new StockQueryService() {
    //
    //            @Override
    //            public Result<StockModel> queryStockRuleById(Long stockRuleId, ServiceContext serviceContext) {
    //
    //                if (stockRuleId == null || stockRuleId <= 0) {
    //                    //logger.warn("Ilegal stockRuleId: {}.", stockRuleId);
    //                    return new Result<StockModel>(new ParameterErrorCode());
    //                }
    //
    //                //logger.debug(serviceContext);
    //
    //                //业务逻辑处理.
    //                try {
    //
    //                } catch (Throwable e) {
    //                    //logger.error();
    //                    throw new QueryStockException(new ErrorCode(50001, "查询库存错误.") {
    //                    }, e);
    //                }
    //
    //                return new Result<StockModel>(ErrorCode.OK);
    //            }
    //
    //            @Override
    //            public Result<HashMap<String, StockModel>> queryStockRule(ServiceContext serviceContext) {
    //                HashMap<String, StockModel> map = new HashMap<String, StockModel>();
    //
    //                Result<HashMap<String, StockModel>> result = new Result<HashMap<String, StockModel>>(ErrorCode.OK);
    //                result.setData(map);
    //
    //                return result;
    //            }
    //
    //            @Override
    //            public Result<StockModel> queryStockById(Long stockId, ServiceContext serviceContext) {
    //                return null;
    //            }
    //        };
    //
    //        stockQueryService.queryStockRuleById(123L, serviceContext);
    //    }

}
