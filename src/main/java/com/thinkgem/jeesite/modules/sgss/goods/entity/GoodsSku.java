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
	private double price;		// 本店售价
	private Integer sort;		// 序号
	private int stock;		// 库存
	private double marketPrice; //市场价
	private double settlementPrice; //供货价
	private double profit; //市场价
	private double discount; //折扣
	private double settlementDiscount; //结算折扣
	private double profitDiscount;
	public GoodsSku() {
		super();
	}

	public double getProfitDiscount() {
		return profitDiscount;
	}

	public void setProfitDiscount(double profitDiscount) {
		this.profitDiscount = profitDiscount;
	}

	public double getSettlementDiscount() {
		return settlementDiscount;
	}

	public void setSettlementDiscount(double settlementDiscount) {
		this.settlementDiscount = settlementDiscount;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public double getSettlementPrice() {
		return settlementPrice;
	}

	public void setSettlementPrice(double settlementPrice) {
		this.settlementPrice = settlementPrice;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}