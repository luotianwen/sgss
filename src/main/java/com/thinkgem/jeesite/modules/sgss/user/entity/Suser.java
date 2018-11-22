/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.user.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户管理Entity
 * @author martins
 * @version 2018-11-22
 */
public class Suser extends DataEntity<Suser> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 用户名称
	private String headImg;		// 头像
	private String integral;		// 积分
	private String phone;		// 手机号
	private String nickname;		// 昵称
	private String state;		// 状态
	private String openid;		// 微信openid
	
	public Suser() {
		super();
	}

	public Suser(String id){
		super(id);
	}

	@Length(min=0, max=200, message="用户名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=200, message="头像长度必须介于 0 和 200 之间")
	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
	@Length(min=0, max=11, message="积分长度必须介于 0 和 11 之间")
	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}
	
	@Length(min=0, max=11, message="手机号长度必须介于 0 和 11 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=200, message="昵称长度必须介于 0 和 200 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Length(min=0, max=11, message="状态长度必须介于 0 和 11 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=100, message="微信openid长度必须介于 0 和 100 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}