/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.goods.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 动力折扣Entity
 * @author 动力折扣
 * @version 2019-12-24
 */
public class Sdg extends DataEntity<Sdg> {
	
	private static final long serialVersionUID = 1L;
	private String artno;		// 货号
	private String price;		// 本店售价
	private Double discount;		// 折扣
	private Double beginDiscount;		// 开始 折扣
	private Double endDiscount;		// 结束 折扣
	
	public Sdg() {
		super();
	}

	public Sdg(String id){
		super(id);
	}

	@Length(min=1, max=200, message="货号长度必须介于 1 和 200 之间")
	public String getArtno() {
		return artno;
	}

	public void setArtno(String artno) {
		this.artno = artno;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@NotNull(message="折扣不能为空")
	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	public Double getBeginDiscount() {
		return beginDiscount;
	}

	public void setBeginDiscount(Double beginDiscount) {
		this.beginDiscount = beginDiscount;
	}
	
	public Double getEndDiscount() {
		return endDiscount;
	}

	public void setEndDiscount(Double endDiscount) {
		this.endDiscount = endDiscount;
	}
		
}