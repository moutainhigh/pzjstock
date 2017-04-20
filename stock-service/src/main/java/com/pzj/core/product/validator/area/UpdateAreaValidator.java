package com.pzj.core.product.validator.area;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.model.AreaModel;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.ObjectConverter;
import com.pzj.framework.toolkit.Check;

/**
 * 更新区域验证器
 * 
 * @author dongchunfu
 * @version $Id: UpdateAreaValidator.java, v 0.1 2016年8月5日 下午2:32:51 dongchunfu Exp $
 */
@Component(value = "updateAreaValidator")
public class UpdateAreaValidator implements ObjectConverter<AreaModel, ServiceContext, Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(UpdateAreaValidator.class);

    @Override
    public Boolean convert(AreaModel model, ServiceContext context) {

        if (Check.NuNObject(model, context)) {
            if (logger.isDebugEnabled()) {
                logger.warn("illegal update area param,request:{},context:{}. ", model, context);
            }
            return Boolean.FALSE;
        }
        if (CommonUtils.checkLongIsNull(model.getId(), Boolean.TRUE)) {
            if (logger.isDebugEnabled()) {
                logger.warn("illegal update area param,request:{},context:{}. ", model, context);
            }
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
