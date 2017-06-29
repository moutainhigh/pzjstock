package com.pzj.core.product;

import java.io.Serializable;

import com.pzj.core.product.common.model.response.SpuProductExtendOutModel;

public class SpuModel implements Serializable {

	/**  */
	private static final long serialVersionUID = -258872543211793856L;
	/**spuId*/
	private Long id;
	/** 供应商id */
	private Long supplierId;
	/** 通用供应端产品id */
	private String commonProductId;
	/** 备注名称 */
	private String name;
	/** 内部序号 */
	private String productNo;
	/** 产品类别 */
	private Integer productType;
	/** 产品封面图片地址 */
	private String coverImage;
	/** 产品图片地址列表 */
	private String imageList;
	/** 产品可用开始日期(到秒) */
	private java.util.Date useStartDate;
	/** 产品可用开始日期(到秒) */
	private java.util.Date useEndDate;
	/** 产品销售开始日期 */
	private java.util.Date saleStartDate;
	/** 产品销售结束日期 */
	private java.util.Date saleEndDate;
	/** 入园开始时间（时分） */
	private Integer checkinStartTime;
	/** 入园结束时间（时分） */
	private Integer checkinEndTime;
	/** 入园方式(1:凭码入园，2:换票入园) */
	private Integer checkinType;
	/** 入园地址 */
	private String checkinAddress;
	/** 省 */
	private String province;
	/** 市 */
	private String city;
	/** 县 */
	private String country;
	/** 是否需要选择场次区域（1：是，0：否） */
	private Integer isNeedRonda;
	/** 是否绑定景区（1：是，0：否） */
	private Integer isNeedScenic;
	/** 1: 简单产品 0：组合产品 */
	private Integer isSimple;
	/** 排序 */
	private Integer sort;
	/** 状态：（0：已删除  1：已上架  2：待提交  3：待审核  4：已拒绝  5已下架） */
	private Integer flag;
	/** 供应端产品code */
	private Integer hashCode;
	/** 团散（1：团 0：散） */
	private Integer ticketVarie;
	/**  */
	private java.util.Date createDate;
	/**  */
	private java.util.Date updateDate;
	/** 创建人 */
	private String createBy;
	/** 最后一次更新人 */
	private String updateBy;

	/** 产品组大字段实体*/
	private SpuProductExtendOutModel spuProductExtendOutModel;

	//	@Override
	//	public String toString() {
	//		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	//	}

	@Override
	public String toString() {
		return "SpuModel [id=" + id + ", supplierId=" + supplierId + ", commonProductId=" + commonProductId + ", name="
				+ name + ", productNo=" + productNo + ", productType=" + productType + ", coverImage=" + coverImage
				+ ", imageList=" + imageList + ", useStartDate=" + useStartDate + ", useEndDate=" + useEndDate
				+ ", saleStartDate=" + saleStartDate + ", saleEndDate=" + saleEndDate + ", checkinStartTime="
				+ checkinStartTime + ", checkinEndTime=" + checkinEndTime + ", checkinType=" + checkinType
				+ ", checkinAddress=" + checkinAddress + ", province=" + province + ", city=" + city + ", country="
				+ country + ", isNeedRonda=" + isNeedRonda + ", isNeedScenic=" + isNeedScenic + ", isSimple="
				+ isSimple + ", sort=" + sort + ", flag=" + flag + ", hashCode=" + hashCode + ", ticketVarie="
				+ ticketVarie + ", createDate=" + createDate + ", updateDate=" + updateDate + ", createBy=" + createBy
				+ ", updateBy=" + updateBy + ", spuProductExtendOutModel=" + spuProductExtendOutModel + "]";
	}

	/**
	 * Getter method for property <tt>supplierId</tt>.
	 * 
	 * @return property value of supplierId
	 */
	public Long getSupplierId() {
		return supplierId;
	}

	/**
	 * Setter method for property <tt>supplierId</tt>.
	 * 
	 * @param supplierId value to be assigned to property supplierId
	 */
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * Getter method for property <tt>commonProductId</tt>.
	 * 
	 * @return property value of commonProductId
	 */
	public String getCommonProductId() {
		return commonProductId;
	}

	/**
	 * Setter method for property <tt>commonProductId</tt>.
	 * 
	 * @param commonProductId value to be assigned to property commonProductId
	 */
	public void setCommonProductId(String commonProductId) {
		this.commonProductId = commonProductId;
	}

	/**
	 * Getter method for property <tt>name</tt>.
	 * 
	 * @return property value of name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter method for property <tt>name</tt>.
	 * 
	 * @param name value to be assigned to property name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter method for property <tt>productNo</tt>.
	 * 
	 * @return property value of productNo
	 */
	public String getProductNo() {
		return productNo;
	}

	/**
	 * Setter method for property <tt>productNo</tt>.
	 * 
	 * @param productNo value to be assigned to property productNo
	 */
	public void setProductNo(String productNo) {
		this.productNo = productNo;
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
	 * Getter method for property <tt>coverImage</tt>.
	 * 
	 * @return property value of coverImage
	 */
	public String getCoverImage() {
		return coverImage;
	}

	/**
	 * Setter method for property <tt>coverImage</tt>.
	 * 
	 * @param coverImage value to be assigned to property coverImage
	 */
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	/**
	 * Getter method for property <tt>imageList</tt>.
	 * 
	 * @return property value of imageList
	 */
	public String getImageList() {
		return imageList;
	}

	/**
	 * Setter method for property <tt>imageList</tt>.
	 * 
	 * @param imageList value to be assigned to property imageList
	 */
	public void setImageList(String imageList) {
		this.imageList = imageList;
	}

	/**
	 * Getter method for property <tt>useStartDate</tt>.
	 * 
	 * @return property value of useStartDate
	 */
	public java.util.Date getUseStartDate() {
		return useStartDate;
	}

	/**
	 * Setter method for property <tt>useStartDate</tt>.
	 * 
	 * @param useStartDate value to be assigned to property useStartDate
	 */
	public void setUseStartDate(java.util.Date useStartDate) {
		this.useStartDate = useStartDate;
	}

	/**
	 * Getter method for property <tt>useEndDate</tt>.
	 * 
	 * @return property value of useEndDate
	 */
	public java.util.Date getUseEndDate() {
		return useEndDate;
	}

	/**
	 * Setter method for property <tt>useEndDate</tt>.
	 * 
	 * @param useEndDate value to be assigned to property useEndDate
	 */
	public void setUseEndDate(java.util.Date useEndDate) {
		this.useEndDate = useEndDate;
	}

	/**
	 * Getter method for property <tt>saleStartDate</tt>.
	 * 
	 * @return property value of saleStartDate
	 */
	public java.util.Date getSaleStartDate() {
		return saleStartDate;
	}

	/**
	 * Setter method for property <tt>saleStartDate</tt>.
	 * 
	 * @param saleStartDate value to be assigned to property saleStartDate
	 */
	public void setSaleStartDate(java.util.Date saleStartDate) {
		this.saleStartDate = saleStartDate;
	}

	/**
	 * Getter method for property <tt>saleEndDate</tt>.
	 * 
	 * @return property value of saleEndDate
	 */
	public java.util.Date getSaleEndDate() {
		return saleEndDate;
	}

	/**
	 * Setter method for property <tt>saleEndDate</tt>.
	 * 
	 * @param saleEndDate value to be assigned to property saleEndDate
	 */
	public void setSaleEndDate(java.util.Date saleEndDate) {
		this.saleEndDate = saleEndDate;
	}

	/**
	 * Getter method for property <tt>checkinStartTime</tt>.
	 * 
	 * @return property value of checkinStartTime
	 */
	public Integer getCheckinStartTime() {
		return checkinStartTime;
	}

	/**
	 * Setter method for property <tt>checkinStartTime</tt>.
	 * 
	 * @param checkinStartTime value to be assigned to property checkinStartTime
	 */
	public void setCheckinStartTime(Integer checkinStartTime) {
		this.checkinStartTime = checkinStartTime;
	}

	/**
	 * Getter method for property <tt>checkinEndTime</tt>.
	 * 
	 * @return property value of checkinEndTime
	 */
	public Integer getCheckinEndTime() {
		return checkinEndTime;
	}

	/**
	 * Setter method for property <tt>checkinEndTime</tt>.
	 * 
	 * @param checkinEndTime value to be assigned to property checkinEndTime
	 */
	public void setCheckinEndTime(Integer checkinEndTime) {
		this.checkinEndTime = checkinEndTime;
	}

	/**
	 * Getter method for property <tt>checkinType</tt>.
	 * 
	 * @return property value of checkinType
	 */
	public Integer getCheckinType() {
		return checkinType;
	}

	/**
	 * Setter method for property <tt>checkinType</tt>.
	 * 
	 * @param checkinType value to be assigned to property checkinType
	 */
	public void setCheckinType(Integer checkinType) {
		this.checkinType = checkinType;
	}

	/**
	 * Getter method for property <tt>checkinAddress</tt>.
	 * 
	 * @return property value of checkinAddress
	 */
	public String getCheckinAddress() {
		return checkinAddress;
	}

	/**
	 * Setter method for property <tt>checkinAddress</tt>.
	 * 
	 * @param checkinAddress value to be assigned to property checkinAddress
	 */
	public void setCheckinAddress(String checkinAddress) {
		this.checkinAddress = checkinAddress;
	}

	/**
	 * Getter method for property <tt>province</tt>.
	 * 
	 * @return property value of province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Setter method for property <tt>province</tt>.
	 * 
	 * @param province value to be assigned to property province
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * Getter method for property <tt>city</tt>.
	 * 
	 * @return property value of city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Setter method for property <tt>city</tt>.
	 * 
	 * @param city value to be assigned to property city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Getter method for property <tt>country</tt>.
	 * 
	 * @return property value of country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Setter method for property <tt>country</tt>.
	 * 
	 * @param country value to be assigned to property country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Getter method for property <tt>isNeedRonda</tt>.
	 * 
	 * @return property value of isNeedRonda
	 */
	public Integer getIsNeedRonda() {
		return isNeedRonda;
	}

	/**
	 * Setter method for property <tt>isNeedRonda</tt>.
	 * 
	 * @param isNeedRonda value to be assigned to property isNeedRonda
	 */
	public void setIsNeedRonda(Integer isNeedRonda) {
		this.isNeedRonda = isNeedRonda;
	}

	/**
	 * Getter method for property <tt>isNeedScenic</tt>.
	 * 
	 * @return property value of isNeedScenic
	 */
	public Integer getIsNeedScenic() {
		return isNeedScenic;
	}

	/**
	 * Setter method for property <tt>isNeedScenic</tt>.
	 * 
	 * @param isNeedScenic value to be assigned to property isNeedScenic
	 */
	public void setIsNeedScenic(Integer isNeedScenic) {
		this.isNeedScenic = isNeedScenic;
	}

	/**
	 * Getter method for property <tt>isSimple</tt>.
	 * 
	 * @return property value of isSimple
	 */
	public Integer getIsSimple() {
		return isSimple;
	}

	/**
	 * Setter method for property <tt>isSimple</tt>.
	 * 
	 * @param isSimple value to be assigned to property isSimple
	 */
	public void setIsSimple(Integer isSimple) {
		this.isSimple = isSimple;
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
	 * Getter method for property <tt>flag</tt>.
	 * 
	 * @return property value of flag
	 */
	public Integer getFlag() {
		return flag;
	}

	/**
	 * Setter method for property <tt>flag</tt>.
	 * 
	 * @param flag value to be assigned to property flag
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 * Getter method for property <tt>hashCode</tt>.
	 * 
	 * @return property value of hashCode
	 */
	public Integer getHashCode() {
		return hashCode;
	}

	/**
	 * Setter method for property <tt>hashCode</tt>.
	 * 
	 * @param hashCode value to be assigned to property hashCode
	 */
	public void setHashCode(Integer hashCode) {
		this.hashCode = hashCode;
	}

	/**
	 * Getter method for property <tt>ticketVarie</tt>.
	 * 
	 * @return property value of ticketVarie
	 */
	public Integer getTicketVarie() {
		return ticketVarie;
	}

	/**
	 * Setter method for property <tt>ticketVarie</tt>.
	 * 
	 * @param ticketVarie value to be assigned to property ticketVarie
	 */
	public void setTicketVarie(Integer ticketVarie) {
		this.ticketVarie = ticketVarie;
	}

	/**
	 * Getter method for property <tt>createDate</tt>.
	 * 
	 * @return property value of createDate
	 */
	public java.util.Date getCreateDate() {
		return createDate;
	}

	/**
	 * Setter method for property <tt>createDate</tt>.
	 * 
	 * @param createDate value to be assigned to property createDate
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Getter method for property <tt>updateDate</tt>.
	 * 
	 * @return property value of updateDate
	 */
	public java.util.Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * Setter method for property <tt>updateDate</tt>.
	 * 
	 * @param updateDate value to be assigned to property updateDate
	 */
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * Getter method for property <tt>createBy</tt>.
	 * 
	 * @return property value of createBy
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * Setter method for property <tt>createBy</tt>.
	 * 
	 * @param createBy value to be assigned to property createBy
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * Getter method for property <tt>updateBy</tt>.
	 * 
	 * @return property value of updateBy
	 */
	public String getUpdateBy() {
		return updateBy;
	}

	/**
	 * Setter method for property <tt>updateBy</tt>.
	 * 
	 * @param updateBy value to be assigned to property updateBy
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * Getter method for property <tt>spuProductExtendOutModel</tt>.
	 * 
	 * @return property value of spuProductExtendOutModel
	 */
	public SpuProductExtendOutModel getSpuProductExtendOutModel() {
		return spuProductExtendOutModel;
	}

	/**
	 * Setter method for property <tt>spuProductExtendOutModel</tt>.
	 * 
	 * @param spuProductExtendOutModel value to be assigned to property spuProductExtendOutModel
	 */
	public void setSpuProductExtendOutModel(SpuProductExtendOutModel spuProductExtendOutModel) {
		this.spuProductExtendOutModel = spuProductExtendOutModel;
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
