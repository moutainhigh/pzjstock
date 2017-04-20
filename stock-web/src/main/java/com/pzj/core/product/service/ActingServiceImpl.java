/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.base.entity.SysUser;
import com.pzj.base.service.sys.IUserService;
import com.pzj.core.product.common.model.response.ProductScenicOutModel;
import com.pzj.core.product.common.service.IProductScenicService;
import com.pzj.core.product.model.AddActingModel;
import com.pzj.core.product.model.AreaModel;
import com.pzj.core.product.model.ScreeingsModel;
import com.pzj.core.product.vo.ActingVo;
import com.pzj.core.product.vo.AreaVo;
import com.pzj.core.product.vo.ScreeningsVO;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author Administrator
 * @version $Id: ActingService.java, v 0.1 2016年8月31日 下午3:17:29 Administrator Exp $
 */
@Component("actingServiceImpl")
public class ActingServiceImpl {
	private final Logger logger = LoggerFactory.getLogger(ActingServiceImpl.class);
	@Autowired
	private IProductScenicService productScenicService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ActingService actingService;

	public boolean addActing(ActingVo actingVo) throws Exception {
		//获取供应商ID
		Long supplierId = checkSupplier(actingVo.getSupplierName());
		logger.info("addActing query supplierName:{},supplierId:{}", actingVo.getSupplierName(), supplierId);
		if (null == supplierId) {
			return Boolean.FALSE;
		}

		//获取景区ID
		Long scenicId = checkScenic(actingVo.getScenicName());
		logger.info("addActing query scenicName:{},scenicId:{}", actingVo.getScenicName(), scenicId);
		if (null == scenicId) {
			return Boolean.FALSE;
		}
		List<AreaModel> areaModelList = new ArrayList<AreaModel>();
		List<ScreeingsModel> screeingsModelList = new ArrayList<ScreeingsModel>();

		List<AreaVo> areaVoList = actingVo.getAreaVoList();
		for (AreaVo areavo : areaVoList) {
			AreaModel areaModel = new AreaModel();
			areaModel.setScenicId(scenicId);
			areaModel.setName(areavo.getAreaName());
			areaModel.setCode(areavo.getAreaSign());
			areaModelList.add(areaModel);
		}
		List<ScreeningsVO> screeningsVOList = actingVo.getScreeningsVOList();
		for (ScreeningsVO screeningsVO : screeningsVOList) {
			ScreeingsModel screeingsModel = new ScreeingsModel();
			screeingsModel.setScenicId(scenicId);
			screeingsModel.setName(screeningsVO.getScreeningsName());
			screeingsModel.setCode(screeningsVO.getScreeningsSign());
			screeingsModel.setStartTime(screeningsVO.getScreeningsStart());
			screeingsModel.setEndTime(screeningsVO.getScreeningsEnd());
			screeingsModel.setEndSaleTime(screeningsVO.getScreeningsSaleEnd());
			screeingsModelList.add(screeingsModel);
		}

		AddActingModel model = new AddActingModel();
		model.setScenicId(scenicId);
		model.setSupplierId(supplierId);
		model.setWhetherSeat(actingVo.getWhetherSeat());
		model.setActingName(actingVo.getScenicName());
		model.setScreeingsModelList(screeingsModelList);
		model.setAreaModelList(areaModelList);
		Result<Boolean> result = actingService.addActing(model, ServiceContext.getServiceContext());
		if (result.isOk()) {
			return result.getData();
		}
		return Boolean.FALSE;
	}

	/**
	 * 检查供应商名称并获取供应商ID
	 * @param supplierName
	 * @return
	 */
	private Long checkSupplier(String supplierName) {
		//1.根据供应商名称查询用户服务 获取对应的供应商信息
		SysUser sysUser = new SysUser();
		sysUser.setName(supplierName);
		List<SysUser> users = userService.findListByParams(sysUser);
		if (users.isEmpty() || users.size() > 1) {
			return null;
		}
		return users.get(0).getId();
	}

	/**
	 * 检查景区名称并获取景区ID
	 * @param scenicName
	 * @return
	 */
	private Long checkScenic(String scenicName) {
		Result<ArrayList<ProductScenicOutModel>> productScenicResult = productScenicService
				.findProductScenicsByName(scenicName);
		if (null == productScenicResult || null == productScenicResult.getData()
				|| productScenicResult.getData().size() != 1) {
			return null;
		}
		ProductScenicOutModel productScenic = productScenicResult.getData().get(0);
		return productScenic.getId();
		//        SkuScenicQuery query = new SkuScenicQuery();
		//        query.setName(scenicName);
		//        Result<ArrayList<SkuScenic>> skuScenicResult = skuScenicService.findSkuScenicByName(query);
		//        if (null == skuScenicResult || null == skuScenicResult.getData() || skuScenicResult.getData().size() != 1) {
		//            return null;
		//        }
		//        SkuScenic skuScenic = skuScenicResult.getData().get(0);
		//        return skuScenic.getId();
	}
}
