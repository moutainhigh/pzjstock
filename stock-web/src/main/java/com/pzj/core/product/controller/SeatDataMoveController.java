package com.pzj.core.product.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pzj.core.stock.service.SeatChartService;
import com.pzj.framework.context.Result;
import com.pzj.framework.converter.JSONConverter;

@Controller("seatDataMoveController")
@RequestMapping(value = "/seatDataMoveContro")
public class SeatDataMoveController {
    private final Logger     logger = LoggerFactory.getLogger(SeatDataMoveController.class);
    @Resource(name = "seatChartService")
    private SeatChartService seatChartService;

    @RequestMapping(value = "/transferSeatData")
    public ModelAndView transferSeatData(ModelAndView view) {
        logger.info("transferSeatData start ...");
        Result<Boolean> result = seatChartService.transferSeatChartData();
        logger.info("transferSeatData result:{}", JSONConverter.toJson(result));
        String viewName = result.getData() ? "success" : "error";
        view.setViewName(viewName);
        return view;
    }
}
