/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.carousel.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 轮播图Entity
 * @author martins
 * @version 2018-11-19
 */
public class Carousel extends DataEntity<Carousel> {
	
	private static final long serialVersionUID = 1L;
	private String goodsId;		// 商品
	private String logo;		// 主图
	private Long sort;		// 序号
	
	public Carousel() {
		super();
	}

	public Carousel(String id){
		super(id);
	}

	@Length(min=0, max=32, message="商品长度必须介于 0 和 32 之间")
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	@Length(min=0, max=200, message="主图长度必须介于 0 和 200 之间")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
}