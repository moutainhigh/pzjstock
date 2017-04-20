/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.controller;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.model.ActingModel;
import com.pzj.core.product.service.ActingService;
import com.pzj.core.product.service.ActingServiceImpl;
import com.pzj.core.product.vo.ActingVo;
import com.pzj.core.product.vo.AreaVo;
import com.pzj.core.product.vo.ScreeningsVO;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author dongchunfu
 * @version $Id: ActingController.java, v 0.1 2016年8月7日 下午7:25:03 dongchunfu Exp $
 */
@Controller
@RequestMapping(value = "/actingContro")
public class ActingController {
    private final Logger      logger = LoggerFactory.getLogger(ActingController.class);
    @Autowired
    private ActingService     actingService;
    @Autowired
    private ActingServiceImpl actingServiceImpl;

    @RequestMapping(value = "/createActing")
    public ModelAndView createActing(ActingModel model, ModelAndView view) {
        ServiceContext context = ServiceContext.getServiceContext();
        Result<Long> result = actingService.createActing(model, context);
        view.setViewName("result");
        return view.addObject("result", result.getData());
    }

    @RequestMapping(value = "/createActingInfo")
    public ModelAndView createActingInfo(ActingVo actingVo, ModelAndView view) {
        boolean flag = true;
        if (null != actingVo) {
            String supplierName = actingVo.getSupplierName();
            String scenicName = actingVo.getScenicName();
            logger.info("-----演艺供应商名称--------" + supplierName);
            logger.info("-----演艺景区名称--------" + scenicName);
        }
        List<AreaVo> areaVoList = actingVo.getAreaVoList();
        if (null != areaVoList && !areaVoList.isEmpty()) {
            String areaName = "";
            Iterator<AreaVo> itera = areaVoList.iterator();
            while (itera.hasNext()) {
                areaName = itera.next().getAreaName();
                if (CommonUtils.checkStringIsNotNull(areaName)) {
                    logger.info("区域名称------------" + areaName);
                } else {
                    itera.remove();
                    continue;
                }
            }
            if (areaVoList.isEmpty()) {
                flag = false;
            }
        } else {
            flag = false;
            logger.info("-----------区域信息是空------------");
        }
        List<ScreeningsVO> screeningsVOList = actingVo.getScreeningsVOList();
        if (null != screeningsVOList && !screeningsVOList.isEmpty()) {
            String screeningsName = "", screeningsStart = "", screeningsEnd = "", screeningsEndSale = "";
            Iterator<ScreeningsVO> itera = screeningsVOList.iterator();
            ScreeningsVO screeningsVO = null;
            while (itera.hasNext()) {
                screeningsVO = itera.next();
                screeningsName = screeningsVO.getScreeningsName();
                screeningsStart = screeningsVO.getScreeningsStart();
                screeningsEnd = screeningsVO.getScreeningsEnd();
                screeningsEndSale = screeningsVO.getScreeningsSaleEnd();
                if (CommonUtils.checkStringIsNotNull(screeningsName)) {
                    logger.info("场次名称------------" + screeningsName);
                } else {
                    itera.remove();
                    continue;
                }
                if (!CommonUtils.checkScreeningsTime(screeningsStart) || !CommonUtils.checkScreeningsTime(screeningsEnd)) {
                    itera.remove();
                    continue;
                }
                if (CommonUtils.checkStringIsNotNull(screeningsEndSale) && !CommonUtils.checkScreeningsTime(screeningsStart)) {
                    itera.remove();
                    continue;
                }
            }
            if (screeningsVOList.isEmpty()) {
                flag = false;
            }
        } else {
            flag = false;
            logger.info("----------场次信息是空------------");
        }
        if (flag) {
            try {
                flag = actingServiceImpl.addActing(actingVo);
            } catch (Exception e) {
                logger.error("----------添加演艺异常------------", e);
            }
        }
        String viewName = flag ? "success" : "error";
        view.setViewName(viewName);
        return view;
    }
}
