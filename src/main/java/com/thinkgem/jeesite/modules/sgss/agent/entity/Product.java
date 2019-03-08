/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.agent.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商品Entity
 * @author martins
 * @version 2019-03-06
 */
public class Product extends DataEntity<Product> {
	
	private static final long serialVersionUID = 1L;
	private String articleno;		// 商品货号
	private String descr;		// 商品描述信息
	private String division;		// 商品类别(鞋,服,配)
	private String quarter;		// 商品上市季节
	private String brandname;		// 品牌
	private String sex;		// 性别
	private String colour;		// 商品颜色
	private String marketprice;		// 市场价
	private Date listingdate;		// 上市时间
	private String total;		// 现有商品信息总数
	private String pid;		// 商品id
	private String price;		// 销售价
	
	public Product() {
		super();
	}

	public Product(String id){
		super(id);
	}

	@Length(min=0, max=100, message="商品货号长度必须介于 0 和 100 之间")
	public String getArticleno() {
		return articleno;
	}

	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}
	
	@Length(min=0, max=2000, message="商品描述信息长度必须介于 0 和 2000 之间")
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	@Length(min=0, max=10, message="商品类别(鞋,服,配)长度必须介于 0 和 10 之间")
	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}
	
	@Length(min=0, max=50, message="商品上市季节长度必须介于 0 和 50 之间")
	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	
	@Length(min=0, max=100, message="品牌长度必须介于 0 和 100 之间")
	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	
	@Length(min=0, max=4, message="性别长度必须介于 0 和 4 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=20, message="商品颜色长度必须介于 0 和 20 之间")
	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public String getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(String marketprice) {
		this.marketprice = marketprice;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getListingdate() {
		return listingdate;
	}

	public void setListingdate(Date listingdate) {
		this.listingdate = listingdate;
	}
	
	@Length(min=0, max=11, message="现有商品信息总数长度必须介于 0 和 11 之间")
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	@Length(min=0, max=32, message="商品id长度必须介于 0 和 32 之间")
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
}