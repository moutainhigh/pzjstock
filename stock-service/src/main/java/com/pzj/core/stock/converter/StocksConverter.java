package com.pzj.core.stock.converter;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.MFBeanCopier;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.model.StockModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 库存转换器.
 * @author YRJ
 *
 */
@Component(value = "stocksConverter")
public class StocksConverter implements ObjectConverter<ArrayList<Stock>, ServiceContext, ArrayList<StockModel>> {

    /** 底层实体 转换为  VO*/
    @Override
    public ArrayList<StockModel> convert(ArrayList<Stock> list, ServiceContext context) {
        if (null == list || list.size() == 0)
            return null;

        ArrayList<StockModel> vos = new ArrayList<StockModel>(list.size());

        for (Stock stock : list) {
            StockModel model = new StockModel();
            MFBeanCopier.copyProperties(model, stock);
            vos.add(model);
        }
        return vos;
    }
}
