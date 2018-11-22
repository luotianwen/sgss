/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.order.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单管理Entity
 * @author martins
 * @version 2018-11-22
 */
public class OrderDetail extends DataEntity<OrderDetail> {
	
	private static final long serialVersionUID = 1L;
	private Order order;		// 订单编号 父类
	private String goods;		// 商品id
	private String sku;		// skuid
	private String name;		// 商品名称
	private String artno;		// 货号
	private String logo;		// 商品主图
	private String spec1;		// 规格1
	private String spec2;		// 规格2
	private String price;		// 本店售价
	private String number;		// 数量
	
	public OrderDetail() {
		super();
	}

	public OrderDetail(String id){
		super(id);
	}

	public OrderDetail(Order order){
		this.order = order;
	}

	@Length(min=0, max=20, message="订单编号长度必须介于 0 和 20 之间")
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	@Length(min=0, max=32, message="商品id长度必须介于 0 和 32 之间")
	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}
	
	@Length(min=0, max=32, message="skuid长度必须介于 0 和 32 之间")
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	@Length(min=0, max=200, message="商品名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="货号长度必须介于 0 和 20 之间")
	public String getArtno() {
		return artno;
	}

	public void setArtno(String artno) {
		this.artno = artno;
	}
	
	@Length(min=0, max=200, message="商品主图长度必须介于 0 和 200 之间")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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
	
	@Length(min=0, max=11, message="数量长度必须介于 0 和 11 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
}