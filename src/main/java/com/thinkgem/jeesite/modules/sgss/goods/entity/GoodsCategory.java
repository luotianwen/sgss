/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.goods.entity;

import com.thinkgem.jeesite.modules.sgss.category.entity.Scategory;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商品管理Entity
 * @author martins
 * @version 2018-11-19
 */
public class GoodsCategory extends DataEntity<GoodsCategory> {
	
	private static final long serialVersionUID = 1L;
	private Goods goods;		// 商品 父类
	private Scategory category;		// 分类id
	public GoodsCategory( ) {

	}
	public GoodsCategory(Goods goods) {
		this.goods = goods;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Scategory getCategory() {
		return category;
	}

	public void setCategory(Scategory category) {
		this.category = category;
	}
}