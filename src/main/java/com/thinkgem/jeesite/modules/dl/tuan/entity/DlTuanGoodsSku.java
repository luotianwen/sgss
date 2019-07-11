/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dl.tuan.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 团购商品Entity
 * @author martins
 * @version 2019-07-11
 */
public class DlTuanGoodsSku extends DataEntity<DlTuanGoodsSku> {
	
	private static final long serialVersionUID = 1L;
	private DlGoodsTuan goods;		// 商品 父类
	private String spec1;		// 规格1
	private String spec2;		// 规格2
	private String sort;		// 序号
	private int stock;		// 库存
	
	public DlTuanGoodsSku() {
		super();
	}

	public DlTuanGoodsSku(String id){
		super(id);
	}

	public DlTuanGoodsSku(DlGoodsTuan goods){
		this.goods = goods;
	}

	public DlGoodsTuan getGoods() {
		return goods;
	}

	public void setGoods(DlGoodsTuan goods) {
		this.goods = goods;
	}
	
	@Length(min=0, max=2000, message="规格1长度必须介于 0 和 2000 之间")
	public String getSpec1() {
		return spec1;
	}

	public void setSpec1(String spec1) {
		this.spec1 = spec1;
	}
	
	@Length(min=0, max=2000, message="规格2长度必须介于 0 和 2000 之间")
	public String getSpec2() {
		return spec2;
	}

	public void setSpec2(String spec2) {
		this.spec2 = spec2;
	}
	
	@Length(min=0, max=11, message="序号长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=11, message="库存长度必须介于 0 和 11 之间")
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}