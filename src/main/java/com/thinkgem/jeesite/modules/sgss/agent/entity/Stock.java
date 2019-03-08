/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.agent.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 库存Entity
 * @author martins
 * @version 2019-03-06
 */
public class Stock extends DataEntity<Stock> {
	
	private static final long serialVersionUID = 1L;
	private String articleno;		// 商品货号
	private String division;		// 商品类别(鞋,服,配)
	private String brandname;		// 品牌
	private String uksize;		// 尺码2
	private String size;		// 尺码1
	private String innernum;		// 库存数量
	private String sex;		// 性别
	private String marketprice;		// 市场价
	private String price;		// 销售价
	private String quarter;		// 商品上市季节
	private String discount;		// 折扣信息
	private String warehousename;		// 货源名
	
	public Stock() {
		super();
	}

	public Stock(String id){
		super(id);
	}

	@Length(min=0, max=100, message="商品货号长度必须介于 0 和 100 之间")
	public String getArticleno() {
		return articleno;
	}

	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}
	
	@Length(min=0, max=10, message="商品类别(鞋,服,配)长度必须介于 0 和 10 之间")
	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}
	
	@Length(min=0, max=100, message="品牌长度必须介于 0 和 100 之间")
	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	
	@Length(min=0, max=255, message="尺码2长度必须介于 0 和 255 之间")
	public String getUksize() {
		return uksize;
	}

	public void setUksize(String uksize) {
		this.uksize = uksize;
	}
	
	@Length(min=0, max=30, message="尺码1长度必须介于 0 和 30 之间")
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	@Length(min=0, max=11, message="库存数量长度必须介于 0 和 11 之间")
	public String getInnernum() {
		return innernum;
	}

	public void setInnernum(String innernum) {
		this.innernum = innernum;
	}
	
	@Length(min=0, max=4, message="性别长度必须介于 0 和 4 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(String marketprice) {
		this.marketprice = marketprice;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=0, max=50, message="商品上市季节长度必须介于 0 和 50 之间")
	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	
	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	@Length(min=0, max=255, message="货源名长度必须介于 0 和 255 之间")
	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	
}