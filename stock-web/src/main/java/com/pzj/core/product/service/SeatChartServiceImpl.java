/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.product.common.model.response.ProductScenicOutModel;
import com.pzj.core.product.common.service.IProductScenicService;
import com.pzj.core.product.model.area.AreaModel;
import com.pzj.core.product.model.area.AreaQueryRequestModel;
import com.pzj.core.product.vo.AreaVo;
import com.pzj.core.product.vo.SeatChartVo;
import com.pzj.core.stock.model.SeatChartModel;
import com.pzj.core.stock.service.SeatChartService;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

/**
 * 
 * @author Administrator
 * @version $Id: SeatChartServiceImpl.java, v 0.1 2016年9月2日 下午4:49:28 Administrator Exp $
 */
@Component("seatChartServiceImpl")
public class SeatChartServiceImpl {
	@Autowired
	private IProductScenicService productScenicService;
	@Autowired
	private AreaQueryService areaQueryService;
	@Autowired
	private SeatChartService seatChartService;

	public Boolean addSeatChart(SeatChartVo seatChartVo) {
		Boolean flag = true;
		//获取景区ID
		Long scenicId = checkScenic(seatChartVo.getScenicName());
		if (null == scenicId) {
			return Boolean.FALSE;
		}
		SeatChartModel seatChartModel = new SeatChartModel();
		seatChartModel.setAreaId(seatChartVo.getAreaId());
		seatChartModel.setScenicId(scenicId);
		seatChartModel.setSeatMaps(seatChartVo.getSeatChart());
		seatChartModel.setSort(Integer.parseInt(seatChartVo.getSort()));
		seatChartModel.setCode(seatChartVo.getCode());
		Result<Boolean> result = seatChartService.addSeatChartInitSeat(seatChartModel,
				ServiceContext.getServiceContext());
		if (result.isOk()) {
			flag = result.getData();
		}
		return flag;
	}

	/**
	 * 通过景区查询区域列表
	 * @param scenicName
	 * @return
	 */
	public List<AreaVo> queryAreaVoByScenic(String scenicName) {
		List<AreaVo> areaVoList = new ArrayList<AreaVo>();
		//获取景区ID
		Long scenicId = checkScenic(scenicName);
		if (null == scenicId) {
			return areaVoList;
		}
		AreaQueryRequestModel model = new AreaQueryRequestModel();
		model.setScenicId(scenicId);
		Result<ArrayList<AreaModel>> result = areaQueryService.queryAreasByWebSeatChartParam(model,
				ServiceContext.getServiceContext());
		if (result.isOk()) {
			ArrayList<AreaModel> areaModelList = result.getData();
			if (null != areaModelList && !areaModelList.isEmpty()) {
				AreaVo areaVo = null;
				for (AreaModel areaModel : areaModelList) {
					areaVo = new AreaVo();
					areaVo.setAreaId(areaModel.getId());
					areaVo.setAreaName(areaModel.getName());
					areaVoList.add(areaVo);
				}
			}
		}
		return areaVoList;
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
