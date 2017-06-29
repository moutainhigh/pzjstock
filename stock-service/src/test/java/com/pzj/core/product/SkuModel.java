package com.pzj.core.product;

import com.pzj.core.product.common.global.IsCombinedEnum;
import com.pzj.core.product.common.global.IsDockedEnum;
import com.pzj.core.product.common.global.IsSimpleProductEnum;
import com.pzj.core.sku.common.constants.SkuProductGlobal.InventoryTypeEnum;

public class SkuModel implements java.io.Serializable {

	/**  */
	private static final long serialVersionUID = -4911654595757920272L;

	/***skuId*/
	private Long id;
	/** 供应商 */
	private String supplierId;
	/** 产品组id */
	private Long spuId;
	/** 通用产品供应端规则id */
	private Long commonProductAttrId;
	/** 产品名称 */
	private String productName;
	/** 产品规格 */
	private String skuName;
	/** 剧场区域 */
	private String region;
	/** 剧场场次 */
	private String ronda;
	/** 场次区域关系ID */
	private Long theaterId;
	/** 是否是无限库存（1：是，0：不是，2：部分子票有库存） */
	private InventoryTypeEnum isUnlimitedStock;
	/** 产品库存规则Id（组合产品的库存规则id：0） */
	private Long stockRuleId;
	/** 是否被组合（1：是 0：否） */
	private IsCombinedEnum isCombined;
	/** 是否是简单产品 */
	private IsSimpleProductEnum isSimple;
	/** 门市价格*100 */
	private Integer productPrice;
	/** 排序 */
	private Integer sort;
	/** 产品分类 */
	private Integer productType;
	/**是否是对接产品，1是，0不是*/
	private IsDockedEnum dockType;

	@Override
	public String toString() {
		return "SkuModel [id=" + id + ", supplierId=" + supplierId + ", spuId=" + spuId + ", commonProductAttrId="
				+ commonProductAttrId + ", productName=" + productName + ", skuName=" + skuName + ", region=" + region
				+ ", ronda=" + ronda + ", theaterId=" + theaterId + ", isUnlimitedStock=" + isUnlimitedStock
				+ ", stockRuleId=" + stockRuleId + ", isCombined=" + isCombined + ", isSimple=" + isSimple
				+ ", productPrice=" + productPrice + ", sort=" + sort + ", productType=" + productType + ", dockType="
				+ dockType + "]";
	}

	/**
	 * Getter method for property <tt>supplierId</tt>.
	 * 
	 * @return property value of supplierId
	 */
	public String getSupplierId() {
		return supplierId;
	}

	/**
	 * Setter method for property <tt>supplierId</tt>.
	 * 
	 * @param supplierId value to be assigned to property supplierId
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * Getter method for property <tt>spuId</tt>.
	 * 
	 * @return property value of spuId
	 */
	public Long getSpuId() {
		return spuId;
	}

	/**
	 * Setter method for property <tt>spuId</tt>.
	 * 
	 * @param spuId value to be assigned to property spuId
	 */
	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	/**
	 * Getter method for property <tt>commonProductAttrId</tt>.
	 * 
	 * @return property value of commonProductAttrId
	 */
	public Long getCommonProductAttrId() {
		return commonProductAttrId;
	}

	/**
	 * Setter method for property <tt>commonProductAttrId</tt>.
	 * 
	 * @param commonProductAttrId value to be assigned to property commonProductAttrId
	 */
	public void setCommonProductAttrId(Long commonProductAttrId) {
		this.commonProductAttrId = commonProductAttrId;
	}

	/**
	 * Getter method for property <tt>productName</tt>.
	 * 
	 * @return property value of productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Setter method for property <tt>productName</tt>.
	 * 
	 * @param productName value to be assigned to property productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * Getter method for property <tt>skuName</tt>.
	 * 
	 * @return property value of skuName
	 */
	public String getSkuName() {
		return skuName;
	}

	/**
	 * Setter method for property <tt>skuName</tt>.
	 * 
	 * @param skuName value to be assigned to property skuName
	 */
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	/**
	 * Getter method for property <tt>region</tt>.
	 * 
	 * @return property value of region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Setter method for property <tt>region</tt>.
	 * 
	 * @param region value to be assigned to property region
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * Getter method for property <tt>ronda</tt>.
	 * 
	 * @return property value of ronda
	 */
	public String getRonda() {
		return ronda;
	}

	/**
	 * Setter method for property <tt>ronda</tt>.
	 * 
	 * @param ronda value to be assigned to property ronda
	 */
	public void setRonda(String ronda) {
		this.ronda = ronda;
	}

	/**
	 * Getter method for property <tt>theaterId</tt>.
	 * 
	 * @return property value of theaterId
	 */
	public Long getTheaterId() {
		return theaterId;
	}

	/**
	 * Setter method for property <tt>theaterId</tt>.
	 * 
	 * @param theaterId value to be assigned to property theaterId
	 */
	public void setTheaterId(Long theaterId) {
		this.theaterId = theaterId;
	}

	/**
	 * Getter method for property <tt>isUnlimitedStock</tt>.
	 * 
	 * @return property value of isUnlimitedStock
	 */
	public InventoryTypeEnum getIsUnlimitedStock() {
		return isUnlimitedStock;
	}

	/**
	 * Setter method for property <tt>isUnlimitedStock</tt>.
	 * 
	 * @param isUnlimitedStock value to be assigned to property isUnlimitedStock
	 */
	public void setIsUnlimitedStock(InventoryTypeEnum isUnlimitedStock) {
		this.isUnlimitedStock = isUnlimitedStock;
	}

	/**
	 * Getter method for property <tt>stockRuleId</tt>.
	 * 
	 * @return property value of stockRuleId
	 */
	public Long getStockRuleId() {
		return stockRuleId;
	}

	/**
	 * Setter method for property <tt>stockRuleId</tt>.
	 * 
	 * @param stockRuleId value to be assigned to property stockRuleId
	 */
	public void setStockRuleId(Long stockRuleId) {
		this.stockRuleId = stockRuleId;
	}

	/**
	 * Getter method for property <tt>isCombined</tt>.
	 * 
	 * @return property value of isCombined
	 */
	public IsCombinedEnum getIsCombined() {
		return isCombined;
	}

	/**
	 * Setter method for property <tt>isCombined</tt>.
	 * 
	 * @param isCombined value to be assigned to property isCombined
	 */
	public void setIsCombined(IsCombinedEnum isCombined) {
		this.isCombined = isCombined;
	}

	/**
	 * Getter method for property <tt>isSimple</tt>.
	 * 
	 * @return property value of isSimple
	 */
	public IsSimpleProductEnum getIsSimple() {
		return isSimple;
	}

	/**
	 * Setter method for property <tt>isSimple</tt>.
	 * 
	 * @param isSimple value to be assigned to property isSimple
	 */
	public void setIsSimple(IsSimpleProductEnum isSimple) {
		this.isSimple = isSimple;
	}

	/**
	 * Getter method for property <tt>productPrice</tt>.
	 * 
	 * @return property value of productPrice
	 */
	public Integer getProductPrice() {
		return productPrice;
	}

	/**
	 * Setter method for property <tt>productPrice</tt>.
	 * 
	 * @param productPrice value to be assigned to property productPrice
	 */
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * Getter method for property <tt>sort</tt>.
	 * 
	 * @return property value of sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * Setter method for property <tt>sort</tt>.
	 * 
	 * @param sort value to be assigned to property sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * Getter method for property <tt>productType</tt>.
	 * 
	 * @return property value of productType
	 */
	public Integer getProductType() {
		return productType;
	}

	/**
	 * Setter method for property <tt>productType</tt>.
	 * 
	 * @param productType value to be assigned to property productType
	 */
	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	/**
	 * Getter method for property <tt>dockType</tt>.
	 * 
	 * @return property value of dockType
	 */
	public IsDockedEnum getDockType() {
		return dockType;
	}

	/**
	 * Setter method for property <tt>dockType</tt>.
	 * 
	 * @param dockType value to be assigned to property dockType
	 */
	public void setDockType(IsDockedEnum dockType) {
		this.dockType = dockType;
	}

	/**
	 * Getter method for property <tt>id</tt>.
	 * 
	 * @return property value of id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter method for property <tt>id</tt>.
	 * 
	 * @param id value to be assigned to property id
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
