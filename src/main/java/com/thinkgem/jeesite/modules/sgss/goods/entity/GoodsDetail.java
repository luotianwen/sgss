/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.goods.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商品详情Entity
 * @author martins
 * @version 2018-11-19
 */
public class GoodsDetail extends DataEntity<GoodsDetail> {
	
	private static final long serialVersionUID = 1L;
	private Goods goods;		// 商品
	private String details;		// 商品详情
	
	public GoodsDetail() {
		super();
	}
	public GoodsDetail(Goods goods) {
		this.goods = goods;
	}
	public GoodsDetail(String id){
		super(id);
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
}