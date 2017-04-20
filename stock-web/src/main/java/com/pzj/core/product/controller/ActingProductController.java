/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pzj.base.entity.SysUser;
import com.pzj.base.service.sys.IUserService;
import com.pzj.core.product.common.model.response.ProductScenicOutModel;
import com.pzj.core.product.common.service.IProductScenicService;
import com.pzj.core.product.model.ActingProductModel;
import com.pzj.core.product.model.ActingProductQueryRequstModel;
import com.pzj.core.product.service.ActingProductService;
import com.pzj.core.product.vo.ActingProductVO;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.exception.ServiceException;

/**
 * 
 * @author dongchunfu
 * @version $Id: ActingProductController.java, v 0.1 2016年8月8日 下午3:45:32 dongchunfu Exp $
 */
@Controller
@RequestMapping(value = "/apContro")
public class ActingProductController {

	@Resource(name = "actingProductService")
	private ActingProductService actingProductService;
	@Resource(name = "userService")
	private IUserService userService;
	//    @Resource(name = "productScenicService")
	//    IProductScenicService        productScenicService;
	@Autowired
	private IProductScenicService productScenicService;

	@RequestMapping(value = "/createActProduct")
	public ModelAndView createActProduct(ActingProductModel model, ModelAndView view) {

		ServiceContext context = ServiceContext.getServiceContext();
		try {
			Result<Integer> result = actingProductService.createActingProduct(model, context);
			view.setViewName("acting/addActingProduct");
			view.addObject("acting", result.getData());
			return view;

		} catch (Throwable t) {
			if (t instanceof ServiceException) {
				view.setViewName("error");
				view.addObject("error", t.getMessage());
				return view;
			}
			view.setViewName("error");
			view.addObject("error", "服务器忙！");
			return view;
		}

	}

	@RequestMapping(value = "/queryUserInfo")
	public ModelAndView queryUserInfo(ActingProductVO vo, ModelAndView view) {

		//1.根据供应商名称查询用户服务 获取对应的供应商信息
		SysUser sysUser = new SysUser();
		sysUser.setName(vo.getSupplierName());
		List<SysUser> users = userService.findListByParams(sysUser);
		if (users.isEmpty() || users.size() > 1) {
			view.setViewName("result");
			view.addObject("result", "供应商不存在或不唯一.");
			return view;
		}

		//2.根据景区名称 查询产品服务 获取对应的景区信息

		//        ProductScenic productScenic = new ProductScenic();
		//        productScenic.setName(vo.getScienName());
		//        List<ProductScenic> productScenics = productScenicService.findListByParams(productScenic);
		//        if (productScenics.isEmpty() || productScenics.size() > 1) {
		//            view.setViewName("result");
		//            view.addObject("result", "景区不存在或不唯一.");
		//            return view;
		//        }

		Result<ArrayList<ProductScenicOutModel>> productScenicResult = productScenicService.findProductScenicsByName(vo
				.getScienName());
		if (null == productScenicResult || null == productScenicResult.getData()
				|| productScenicResult.getData().size() != 1) {
			view.setViewName("result");
			view.addObject("result", "景区不存在或不唯一.");
			return view;
		}
		ProductScenicOutModel productScenic = productScenicResult.getData().get(0);

		//		SkuScenicQuery query = new SkuScenicQuery();
		//		query.setName(vo.getScienName());
		//		Result<ArrayList<SkuScenic>> skuScenicResult = skuScenicService.findSkuScenicByName(query);
		//		SkuScenic skuScenic = null;
		//		if (null == skuScenicResult || null == skuScenicResult.getData() || skuScenicResult.getData().size() != 1) {
		//			view.setViewName("result");
		//			view.addObject("result", "景区不存在或不唯一.");
		//			return view;
		//		}
		//		skuScenic = skuScenicResult.getData().get(0);

		ActingProductQueryRequstModel model = new ActingProductQueryRequstModel();
		model.setScenicId(productScenic.getId());
		Long userId = users.get(0).getId();
		model.setSupplierId(userId);
		ServiceContext context = ServiceContext.getServiceContext();
		Result<ActingProductModel> result = actingProductService.queryInfoForCreateActingProduct(model, context);
		if (result.isOk()) {
			ActingProductModel data = result.getData();
			if (data == null) {
				view.addObject("result", result.getErrorMsg());
				view.setViewName("result");
				return view;
			}
			data.setScenicId(12345L);
			data.setSupplierId(userId);
			view.addObject("actingProduct", data);
			view.setViewName("acting/addActingProduct");
			return view;
		}

		view.addObject("result", result.getErrorMsg());
		view.setViewName("result");
		return view;
	}
}
