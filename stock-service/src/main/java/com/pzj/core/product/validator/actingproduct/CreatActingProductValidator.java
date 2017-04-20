package com.pzj.core.product.validator.actingproduct;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.model.ActingProductModel;
import com.pzj.core.product.model.AreaModel;
import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 创建演绎产品时收集产品的相关信息验证器
 * @author dongchunfu
 *
 */
@Component(value = "creatActingProductValidator")
public class CreatActingProductValidator implements ObjectConverter<ActingProductModel, ServiceContext, Boolean> {

    @Override
    public Boolean convert(ActingProductModel model, ServiceContext context) {

        if (Check.NuNObject(model)) {
            return Boolean.FALSE;
        }

        Long actingId = model.getActingId();
        if (CommonUtils.checkLongIsNull(actingId, Boolean.TRUE)) {
            return Boolean.FALSE;
        }

        ArrayList<AreaModel> areas = model.getAreas();
        ArrayList<ScreeingsModel> screeingses = model.getScreeingses();
        if (CommonUtils.checkObjectIsNull(areas, screeingses)) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
