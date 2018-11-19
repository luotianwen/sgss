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
public class GoodsPic extends DataEntity<GoodsPic> {
	
	private static final long serialVersionUID = 1L;
	private Goods goods;		// 商品 父类
	private String logo;		// 主图
	private Integer sort;		// 序号
	
	public GoodsPic() {
		super();
	}

	public GoodsPic(String id){
		super(id);
	}

	public GoodsPic(Goods goods){
		this.goods = goods;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Length(min=0, max=200, message="主图长度必须介于 0 和 200 之间")
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
	
}