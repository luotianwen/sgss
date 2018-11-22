/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.coupon.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 优惠券Entity
 * @author martins
 * @version 2018-11-22
 */
public class Coupon extends DataEntity<Coupon> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private Date beginDate;		// 开始时间
	private Date endDate;		// 结束时间
	private String state;		// 是否开放
	private String num;		// 数量
	private String full;		// 满
	private String reduction;		// 减
	private Date beginBeginDate;		// 开始 开始时间
	private Date endBeginDate;		// 结束 开始时间
	private Date beginEndDate;		// 开始 结束时间
	private Date endEndDate;		// 结束 结束时间
	
	public Coupon() {
		super();
	}

	public Coupon(String id){
		super(id);
	}

	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Length(min=0, max=11, message="是否开放长度必须介于 0 和 11 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=11, message="数量长度必须介于 0 和 11 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	public String getFull() {
		return full;
	}

	public void setFull(String full) {
		this.full = full;
	}
	
	public String getReduction() {
		return reduction;
	}

	public void setReduction(String reduction) {
		this.reduction = reduction;
	}
	
	public Date getBeginBeginDate() {
		return beginBeginDate;
	}

	public void setBeginBeginDate(Date beginBeginDate) {
		this.beginBeginDate = beginBeginDate;
	}
	
	public Date getEndBeginDate() {
		return endBeginDate;
	}

	public void setEndBeginDate(Date endBeginDate) {
		this.endBeginDate = endBeginDate;
	}
		
	public Date getBeginEndDate() {
		return beginEndDate;
	}

	public void setBeginEndDate(Date beginEndDate) {
		this.beginEndDate = beginEndDate;
	}
	
	public Date getEndEndDate() {
		return endEndDate;
	}

	public void setEndEndDate(Date endEndDate) {
		this.endEndDate = endEndDate;
	}
		
}