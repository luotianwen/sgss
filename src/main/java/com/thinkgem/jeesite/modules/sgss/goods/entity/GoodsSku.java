/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.goods.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商品管理Entity
 * @author martins
 * @version 2018-11-19
 */
public class GoodsSku extends DataEntity<GoodsSku> {
	
	private static final long serialVersionUID = 1L;
	private Goods goods;		// 商品 父类
	private String spec1;		// 规格1
	private String spec2;		// 规格2
	private String price;		// 本店售价
	private Integer sort;		// 序号
	private String stock;		// 库存
	
	public GoodsSku() {
		super();
	}

	public GoodsSku(String id){
		super(id);
	}

	public GoodsSku(Goods goods){
		this.goods = goods;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Length(min=0, max=20, message="规格1长度必须介于 0 和 20 之间")
	public String getSpec1() {
		return spec1;
	}

	public void setSpec1(String spec1) {
		this.spec1 = spec1;
	}
	
	@Length(min=0, max=20, message="规格2长度必须介于 0 和 20 之间")
	public String getSpec2() {
		return spec2;
	}

	public void setSpec2(String spec2) {
		this.spec2 = spec2;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=11, message="库存长度必须介于 0 和 11 之间")
	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}
	
}