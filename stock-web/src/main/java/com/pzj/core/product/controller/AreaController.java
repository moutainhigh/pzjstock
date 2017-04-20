/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pzj.core.product.model.AreaModel;
import com.pzj.core.product.service.AreaService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author dongchunfu
 * @version $Id: ActingController.java, v 0.1 2016年8月7日 下午7:25:03 dongchunfu Exp $
 */
@Controller
@RequestMapping(value = "/areaContro")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/createArea")
    public ModelAndView createArea(AreaModel model, ModelAndView view) {
        ServiceContext context = ServiceContext.getServiceContext();
        Result<Long> result = areaService.createArea(model, context);
        view.setViewName("result");
        return view.addObject("result", result.getData());
    }
}
