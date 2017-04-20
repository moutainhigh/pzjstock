/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.core.product.service.ScreeingsService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author dongchunfu
 * @version $Id: AreaController.java, v 0.1 2016年8月7日 上午10:24:44 dongchunfu Exp $
 */
@Controller
@RequestMapping(value = "/screeinsContro")
public class ScreeingsController {
    @Autowired
    private ScreeingsService screeingsService;

    @RequestMapping(value = "/createScreeings")
    public ModelAndView createScreeings(ScreeingsModel model, ModelAndView view) {
        ServiceContext context = ServiceContext.getServiceContext();
        Result<Long> result = screeingsService.createScreeings(model, context);
        view.setViewName("result");
        return view.addObject("result", result.getData());
    }
}
