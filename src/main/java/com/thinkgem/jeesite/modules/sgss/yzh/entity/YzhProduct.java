/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.yzh.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 云中鹤商品管理Entity
 * @author martins
 * @version 2020-06-02
 */
public class YzhProduct extends DataEntity<YzhProduct> {
	
	private static final long serialVersionUID = 1L;
	private int productid;		// 商品id
	private String name;		// 商品名称
	private String thumbnailimage;		// 商品缩略图
	private String brand;		// 品牌
	private String productcate;		// 分类
	private String productcode;		// 商品型号
	private String status;		// 状态
	private double marketprice;		// 市场价
	private double retailprice;		// 协议价格
	private String productplace;		// 商品产地
	private String features;		// 商品描述信息
	private String hot;		// 热销商品
	private String createtime;		// 商品创建时间
	private boolean is7toreturn;		// 无理由退货
	private String tax;		// 税率
	private String productDescription;		// 商品详情信息
	private String mobileProductDescription;		// 移动端商品详情信息
	private Date beginCreatetime;		// 开始 商品创建时间
	private Date endCreatetime;		// 结束 商品创建时间
	private Date beginUpdateDate;		// 开始 更新时间
	private Date endUpdateDate;		// 结束 更新时间
	private String sync;

	public String getSync() {
		return sync;
	}

	public void setSync(String sync) {
		this.sync = sync;
	}

	public YzhProduct() {
		super();
	}

	public YzhProduct(String id){
		super(id);
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}
	
	@Length(min=0, max=255, message="商品名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="商品缩略图长度必须介于 0 和 255 之间")
	public String getThumbnailimage() {
		return thumbnailimage;
	}

	public void setThumbnailimage(String thumbnailimage) {
		this.thumbnailimage = thumbnailimage;
	}
	
	@Length(min=0, max=255, message="品牌长度必须介于 0 和 255 之间")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Length(min=0, max=255, message="分类长度必须介于 0 和 255 之间")
	public String getProductcate() {
		return productcate;
	}

	public void setProductcate(String productcate) {
		this.productcate = productcate;
	}
	
	@Length(min=0, max=255, message="商品型号长度必须介于 0 和 255 之间")
	public String getProductcode() {
		return productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	
	@Length(min=0, max=255, message="状态长度必须介于 0 和 255 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public double getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(double marketprice) {
		this.marketprice = marketprice;
	}
	
	public double getRetailprice() {
		return retailprice;
	}

	public void setRetailprice(double retailprice) {
		this.retailprice = retailprice;
	}
	
	@Length(min=0, max=255, message="商品产地长度必须介于 0 和 255 之间")
	public String getProductplace() {
		return productplace;
	}

	public void setProductplace(String productplace) {
		this.productplace = productplace;
	}
	
	@Length(min=0, max=255, message="商品描述信息长度必须介于 0 和 255 之间")
	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}
	
	@Length(min=0, max=255, message="热销商品长度必须介于 0 和 255 之间")
	public String getHot() {
		return hot;
	}

	public void setHot(String hot) {
		this.hot = hot;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	@Length(min=0, max=255, message="无理由退货长度必须介于 0 和 255 之间")
	public boolean getIs7toreturn() {
		return is7toreturn;
	}

	public void setIs7toreturn(boolean is7toreturn) {
		this.is7toreturn = is7toreturn;
	}
	
	@Length(min=0, max=255, message="税率长度必须介于 0 和 255 之间")
	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}
	
	@Length(min=0, max=255, message="商品详情信息长度必须介于 0 和 255 之间")
	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	@Length(min=0, max=255, message="移动端商品详情信息长度必须介于 0 和 255 之间")
	public String getMobileProductDescription() {
		return mobileProductDescription;
	}

	public void setMobileProductDescription(String mobileProductDescription) {
		this.mobileProductDescription = mobileProductDescription;
	}
	
	public Date getBeginCreatetime() {
		return beginCreatetime;
	}

	public void setBeginCreatetime(Date beginCreatetime) {
		this.beginCreatetime = beginCreatetime;
	}
	
	public Date getEndCreatetime() {
		return endCreatetime;
	}

	public void setEndCreatetime(Date endCreatetime) {
		this.endCreatetime = endCreatetime;
	}
		
	public Date getBeginUpdateDate() {
		return beginUpdateDate;
	}

	public void setBeginUpdateDate(Date beginUpdateDate) {
		this.beginUpdateDate = beginUpdateDate;
	}
	
	public Date getEndUpdateDate() {
		return endUpdateDate;
	}

	public void setEndUpdateDate(Date endUpdateDate) {
		this.endUpdateDate = endUpdateDate;
	}
		
}