package com.pzj.core.stock.validator.stock;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.model.CreateStockModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;

/**
 * 创建库存参数验证器
 * 
 * @version 1.0.0
 * @author dongchunfu
 */
@Component(value = "createStockValidator")
public class CreateStockValidator implements ObjectConverter<CreateStockModel, ServiceContext, Boolean> {

    /**
     * @param model 创建库存参数，规则ID不得为空
     * @return <code>true</code> validate success,<code>false</code> validate false.
     */
    @Override
    public Boolean convert(CreateStockModel model, ServiceContext context) {
        if (CommonUtils.checkObjectIsNull(model)) {
            return Boolean.FALSE;
        }
        if (CommonUtils.checkCollectionIsNull(model.getRuleIds())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
