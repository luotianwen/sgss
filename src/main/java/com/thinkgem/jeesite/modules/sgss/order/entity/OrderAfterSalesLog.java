/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.order.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 售后退款日志Entity
 * @author martins
 * @version 2019-02-28
 */
public class OrderAfterSalesLog extends DataEntity<OrderAfterSalesLog> {
	
	private static final long serialVersionUID = 1L;
	private String num;		// 序号
	private String ordernumber;		// 订单编号
	private String returnAmount;		// 退款金额
	private String refundId;		// 微信退款单号
	private String outRefundNo;		// 商户系统内部的退款单号
	
	public OrderAfterSalesLog() {
		super();
	}

	public OrderAfterSalesLog(String id){
		super(id);
	}

	@Length(min=1, max=11, message="序号长度必须介于 1 和 11 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Length(min=0, max=20, message="订单编号长度必须介于 0 和 20 之间")
	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	
	public String getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(String returnAmount) {
		this.returnAmount = returnAmount;
	}
	
	@Length(min=0, max=32, message="微信退款单号长度必须介于 0 和 32 之间")
	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	
	@Length(min=0, max=64, message="商户系统内部的退款单号长度必须介于 0 和 64 之间")
	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}
	
}