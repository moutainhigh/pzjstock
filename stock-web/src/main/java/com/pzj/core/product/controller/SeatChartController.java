/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.product.service.SeatChartServiceImpl;
import com.pzj.core.product.vo.AreaVo;
import com.pzj.core.product.vo.SeatChartVo;
import com.pzj.core.stock.model.SeatChartModel;
import com.pzj.core.stock.service.SeatChartService;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author dongchunfu
 * @version $Id: ActingController.java, v 0.1 2016年8月7日 下午7:25:03 dongchunfu Exp $
 */
@Controller
@RequestMapping(value = "/seatChartContro")
public class SeatChartController {
    Logger                       logger = LoggerFactory.getLogger(SeatChartController.class);
    @Autowired
    private SeatChartService     seatChartService;
    @Autowired
    private SeatChartServiceImpl seatChartServiceImpl;

    @RequestMapping(value = "/createSeatChart")
    public ModelAndView createSeatChart(SeatChartModel model, ModelAndView view) {
        try {
            ServiceContext context = ServiceContext.getServiceContext();
            seatChartService.addSeatChart(model, context);
            view.setViewName("result");
            return view.addObject("result", "success");
        } catch (Exception e) {
            view.setViewName("result");
            return view.addObject("result", e.getMessage());
        }
    }

    @RequestMapping(value = "/getAreaList", produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<AreaVo> getAreaList(@RequestParam("scenicName") String scenicName) {
        logger.info("-----景区名称 获取区域列表----------------" + scenicName);
        List<AreaVo> areaVoList = seatChartServiceImpl.queryAreaVoByScenic(scenicName);
        if (null != areaVoList && !areaVoList.isEmpty()) {
            return areaVoList;
        }
        return null;
    }

    @RequestMapping(value = "/addSeatChart")
    public ModelAndView addSeatChart(SeatChartVo seatChartVo, ModelAndView view) {
        boolean flag = true;
        if (null != seatChartVo) {
            flag: {
                if (CommonUtils.checkStringIsNull(seatChartVo.getScenicName())) {
                    flag = false;
                    break flag;
                }
                if (CommonUtils.checkStringIsNull(seatChartVo.getSeatChart())) {
                    flag = false;
                    break flag;
                }
                if (CommonUtils.checkLongIsNull(seatChartVo.getAreaId(), true)) {
                    flag = false;
                    break flag;
                }
                if (CommonUtils.checkStringIsNullStrict(seatChartVo.getSort())) {
                    seatChartVo.setSort("1");
                } else {
                    if (!CommonUtils.judgeStringIsNum(seatChartVo.getSort())) {
                        flag = false;
                        break flag;
                    }
                }
                if (!CommonUtils.checkStringLen(16, seatChartVo.getCode())) {
                    flag = false;
                    break flag;
                }
            }
            flag = seatChartServiceImpl.addSeatChart(seatChartVo);
        }
        String viewName = flag ? "success" : "error";
        view.setViewName(viewName);
        return view;
    }
}
