package com.pzj.core.stock.validator.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.OccupyStockRequestModel;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 占库存接口参数验证器.
 * @author YRJ
 *
 */
@Component(value = "occupyStockValidator")
public class OccupyStockValidator implements ObjectConverter<ArrayList<OccupyStockRequestModel>, ServiceContext, ParamModel> {

    @Override
    public ParamModel convert(ArrayList<OccupyStockRequestModel> stockModelList, ServiceContext context) {
        ParamModel paramModel = ParamModel.getInstance();
        if (CommonUtils.checkObjectIsNull(stockModelList)) {
            paramModel.setErrorModel("占/释放 库存传入stockModelList对象集合为空!");
            return paramModel;
        }

        Map<String, String> filterRepeatMap = new HashMap<String, String>();
        Iterator<OccupyStockRequestModel> itera = stockModelList.iterator();
        OccupyStockRequestModel stockModel = null;
        String repeatKey = "";
        while (itera.hasNext()) {
            stockModel = itera.next();
            if (CommonUtils.checkObjectIsNull(stockModel)) {
                itera.remove();
                continue;
            }
            if (CommonUtils.checkStringIsNullStrict(stockModel.getTransactionId())) {
                paramModel.setErrorModel("占/释放 库存传入transactionId交易ID为空!");
                return paramModel;
            }
            if (CommonUtils.checkLongIsNull(stockModel.getProductId(), true)) {
                paramModel.setErrorModel("占/释放 库存传入productId产品ID为空!");
                return paramModel;
            }
            if (CommonUtils.checkLongIsNull(stockModel.getStockId(), true)) {
                paramModel.setErrorModel("占/释放 库存传入stockId库存ID为空!");
                return paramModel;
            }
            if (CommonUtils.checkIntegerIsNull(stockModel.getStockNum(), true)) {
                paramModel.setErrorModel("占/释放 库存传入stockNum占库存数为空!");
                return paramModel;
            }
            repeatKey = stockModel.getTransactionId() + stockModel.getProductId();
            if (filterRepeatMap.containsKey(repeatKey)) {
                itera.remove();
                continue;
            } else {
                filterRepeatMap.put(repeatKey, repeatKey);
            }
        }
        if (CommonUtils.checkCollectionIsNull(stockModelList)) {
            paramModel.setErrorModel("占/释放 库存传入OccupyStockRequestModel对象为空!");
            return paramModel;
        }
        return paramModel;
    }
}
