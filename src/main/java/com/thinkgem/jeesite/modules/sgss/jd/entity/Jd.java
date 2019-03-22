/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.jd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 京东抓取Entity
 * @author martins
 * @version 2019-03-22
 */
public class Jd extends DataEntity<Jd> {
	
	private static final long serialVersionUID = 1L;
	private String url;		// 京东编号
	private String state;		// 京东编号

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Jd() {
		super();
	}

	public Jd(String id){
		super(id);
	}

	@Length(min=0, max=500, message="京东编号长度必须介于 0 和 500 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}