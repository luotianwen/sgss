/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.category.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 分类管理Entity
 * @author martins
 * @version 2018-11-19
 */
public class Scategory extends TreeEntity<Scategory> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String logo;		// 主图
	private Integer sort;		// 序号
	private Scategory parent;		// 父类
	private String parentIds;		// 所有父级编号
	private Long num;		// 序号

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
	public Scategory() {
		super();
	}

	public Scategory(String id){
		super(id);
	}

	@Length(min=0, max=32, message="名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	@JsonBackReference
	public Scategory getParent() {
		return parent;
	}

	public void setParent(Scategory parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=2000, message="所有父级编号长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}