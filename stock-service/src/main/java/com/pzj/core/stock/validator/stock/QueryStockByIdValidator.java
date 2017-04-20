package com.pzj.core.stock.validator.stock;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.StockQueryRequestModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 主键查库存参数验证器
 * @author dongchunfu
 *
 */
@Component(value = "queryStockByIdValidator")
public class QueryStockByIdValidator implements ObjectConverter<StockQueryRequestModel, ServiceContext, Boolean> {

	@Override
	public Boolean convert(StockQueryRequestModel model, ServiceContext context) {
		if (CommonUtils.checkObjectIsNull(model)) {
			return Boolean.FALSE;
		}

		Long stockId = model.getStockId();
		if (CommonUtils.checkLongIsNull(stockId, true)) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}
}
