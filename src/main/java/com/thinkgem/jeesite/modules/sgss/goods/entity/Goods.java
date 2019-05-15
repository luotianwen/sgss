/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.goods.entity;

import com.thinkgem.jeesite.modules.sgss.brand.entity.Brand;
import com.thinkgem.jeesite.modules.sgss.supplier.entity.Supplier;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.springframework.beans.factory.annotation.Required;

/**
 * 商品管理Entity
 * @author martins
 * @version 2018-11-19
 */
public class Goods extends DataEntity<Goods> {
	
	private static final long serialVersionUID = 1L;
	private String num;		// 名称
	private String name;		// 名称
	private String categoryName;		// 分类名称
	private String categoryId;		// 分类id
	private String artno;		// 货号
	private String logo;		// 主图
	private Integer sort;		// 序号
	private double marketPrice;		// 市场售价
	private double price;		// 本店售价
	private double settlementPrice;//供货价
	private Brand brand;		// 品牌
	private String state;		// 上架状态
	private String spec1;		// 规格1
	private String spec2;		// 规格2
	private List<GoodsCategory> goodsCategoryList = Lists.newArrayList();		// 子表列表
	private List<GoodsPic> goodsPicList = Lists.newArrayList();		// 子表列表
	private List<GoodsSku> goodsSkuList = Lists.newArrayList();		// 子表列表
	private GoodsDetail detail;		// 商品详情
    private String imgs;
    private int sales;
	private Supplier supplier;
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private String pass;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public double getSettlementPrice() {
		return settlementPrice;
	}

	public void setSettlementPrice(double settlementPrice) {
		this.settlementPrice = settlementPrice;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Length(min=1, max=1000, message="分类必须选择")
	public String getCategoryId() {
		return categoryId;
	}

	public GoodsDetail getDetail() {
		return detail;
	}
	public void setDetail(GoodsDetail detail) {
		this.detail = detail;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Goods() {
		super();
	}

	public Goods(String id){
		super(id);
	}

	@Length(min=1, max=100, message="名称必须填")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=20, message="货号长度必须填")
	public String getArtno() {
		return artno;
	}

	public void setArtno(String artno) {
		this.artno = artno;
	}
	
	@Length(min=1, max=2000, message="主图上传")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@Length(min=0, max=2, message="上架状态长度必须介于 0 和 2 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=1, max=20, message="规格1必须填")
	public String getSpec1() {
		return spec1;
	}

	public void setSpec1(String spec1) {
		this.spec1 = spec1;
	}
	

	public String getSpec2() {
		return spec2;
	}

	public void setSpec2(String spec2) {
		this.spec2 = spec2;
	}
	@Length(min=1, max=2000, message="多图必须填")
	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public List<GoodsCategory> getGoodsCategoryList() {
		return goodsCategoryList;
	}

	public void setGoodsCategoryList(List<GoodsCategory> goodsCategoryList) {
		this.goodsCategoryList = goodsCategoryList;
	}
	public List<GoodsPic> getGoodsPicList() {
		return goodsPicList;
	}

	public void setGoodsPicList(List<GoodsPic> goodsPicList) {
		this.goodsPicList = goodsPicList;
	}
	public List<GoodsSku> getGoodsSkuList() {
		return goodsSkuList;
	}

	public void setGoodsSkuList(List<GoodsSku> goodsSkuList) {
		this.goodsSkuList = goodsSkuList;
	}
}