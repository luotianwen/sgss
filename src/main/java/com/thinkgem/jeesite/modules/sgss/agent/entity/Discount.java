/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.agent.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 折扣Entity
 * @author martins
 * @version 2019-03-06
 */
public class Discount extends DataEntity<Discount> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String discount;		// 折扣
	
	public Discount() {
		super();
	}

	public Discount(String id){
		super(id);
	}

	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
}