package com.pzj.core.product;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pzj.core.product.common.model.response.ProductScenicOutModel;
import com.pzj.core.product.common.model.response.SkuProductOutModel;
import com.pzj.core.product.common.model.response.SpuSkuScenicProductOutModel;
import com.pzj.core.product.common.service.ISpuProductService;
import com.pzj.framework.context.Result;

public class InvokeProductServiceTest {
	Logger logger = LoggerFactory.getLogger(InvokeProductServiceTest.class);
	static ApplicationContext context = null;

	@BeforeClass
	public static void setUpClass() {
		context = new ClassPathXmlApplicationContext("applicationContext-test.xml");
		System.out.println(context);
	}

	private ISpuProductService spuProductService;

	@Before
	public void setUp() {
		spuProductService = context.getBean(ISpuProductService.class);
	}

	@Test
	public void testGetProductDetail() {
//		Long spuId = 814370759806521344L;
//		Result<SpuSkuScenicProductOutModel> result = spuProductService.getSpuSkuScenicBySpuId(spuId);
//		if (result.isOk() && null != result.getData()) {
//			SpuSkuScenicProductOutModel spuInfo = result.getData();
//			ProductScenicOutModel proScenic = spuInfo.getProductScenic().get(0);
//			SkuProductOutModel skuPro = spuInfo.getSpuSkuProductOutModel().getSkuProductResuts().get(0);
//			System.out.println("scenicId:" + proScenic.getId() + ";;supplierId:" + skuPro.getSupplierId());
//
//			System.out.println("区域id：" + skuPro.getRegion() + ";;;场次id：" + skuPro.getRonda());
//
//			System.out.println("剧场id：" + skuPro.getTheaterId());
//		}

		//		try {
		//			Long spuId = 814370759806521344L;
		//			Result<SpuProductOutModel> spuResult = spuProductService.getSpuById(spuId);
		//			SpuProductOutModel spu = spuResult.getData();
		//			SpuModel spuModel = new SpuModel();
		//			BeanUtils.copyProperties(spuModel, spu);
		//			logger.debug("spuResult===", spuModel);
		//
		//			Long skuId = 814370759911378944L;
		//			Result<SkuProductOutModel> skuResult = spuProductService.getSkuById(skuId);
		//			SkuProductOutModel sku = skuResult.getData();
		//			SkuModel skuModel = new SkuModel();
		//			BeanUtils.copyProperties(skuModel, sku);
		//			logger.debug("skuResult===", skuModel);
		//		} catch (IllegalAccessException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (InvocationTargetException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
	}
}
