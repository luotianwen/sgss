/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.weixin.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 公众号配置Entity
 * @author martins
 * @version 2019-03-08
 */
public class Weixin extends DataEntity<Weixin> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String url;		// 路径
	
	public Weixin() {
		super();
	}

	public Weixin(String id){
		super(id);
	}

	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=500, message="路径长度必须介于 0 和 500 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}