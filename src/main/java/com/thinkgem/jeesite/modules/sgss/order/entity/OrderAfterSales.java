/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.order.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单售后Entity
 * @author martins
 * @version 2018-11-22
 */
public class OrderAfterSales extends DataEntity<OrderAfterSales> {
	
	private static final long serialVersionUID = 1L;
	private String num;		// 序号
	private String ordernumber;		// 订单编号
	private User user;		// 用户
	private Date applyTime;		// 申请时间
	private String type;		// 售后类型
	private String content;		// 售后内容
	private String exchangePhone;		// 换货收货人电话
	private String exchangeConsignee;		// 换货收货人
	private String exchangeAddress;		// 换货收货地址
	private String exchangeInvoiceNo;		// 换货单号
	private String exchangeExpressName;		// 换货快递公司
	private String exchangeFreight;		// 换货运费
	private Date exchangeTime;		// 换货时间
	private String returnInvoiceNo2;		// 退货单号
	private String returnExpressName2;		// 退货快递公司
	private String returnPhone;		// 退货收货人电话
	private String returnConsignee2;		// 退货收货人
	private String returnAddress;		// 退货地址
	private Date returnTime;		// 退货时间
	private String state;		// 售后状态
	private String refuseContent;		// 拒绝内容
	
	public OrderAfterSales() {
		super();
	}

	public OrderAfterSales(String id){
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	@Length(min=0, max=11, message="售后类型长度必须介于 0 和 11 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=200, message="售后内容长度必须介于 0 和 200 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=11, message="换货收货人电话长度必须介于 0 和 11 之间")
	public String getExchangePhone() {
		return exchangePhone;
	}

	public void setExchangePhone(String exchangePhone) {
		this.exchangePhone = exchangePhone;
	}
	
	@Length(min=0, max=100, message="换货收货人长度必须介于 0 和 100 之间")
	public String getExchangeConsignee() {
		return exchangeConsignee;
	}

	public void setExchangeConsignee(String exchangeConsignee) {
		this.exchangeConsignee = exchangeConsignee;
	}
	
	@Length(min=0, max=200, message="换货收货地址长度必须介于 0 和 200 之间")
	public String getExchangeAddress() {
		return exchangeAddress;
	}

	public void setExchangeAddress(String exchangeAddress) {
		this.exchangeAddress = exchangeAddress;
	}
	
	@Length(min=0, max=50, message="换货单号长度必须介于 0 和 50 之间")
	public String getExchangeInvoiceNo() {
		return exchangeInvoiceNo;
	}

	public void setExchangeInvoiceNo(String exchangeInvoiceNo) {
		this.exchangeInvoiceNo = exchangeInvoiceNo;
	}
	
	@Length(min=0, max=20, message="换货快递公司长度必须介于 0 和 20 之间")
	public String getExchangeExpressName() {
		return exchangeExpressName;
	}

	public void setExchangeExpressName(String exchangeExpressName) {
		this.exchangeExpressName = exchangeExpressName;
	}
	
	public String getExchangeFreight() {
		return exchangeFreight;
	}

	public void setExchangeFreight(String exchangeFreight) {
		this.exchangeFreight = exchangeFreight;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExchangeTime() {
		return exchangeTime;
	}

	public void setExchangeTime(Date exchangeTime) {
		this.exchangeTime = exchangeTime;
	}
	
	@Length(min=0, max=50, message="退货单号长度必须介于 0 和 50 之间")
	public String getReturnInvoiceNo2() {
		return returnInvoiceNo2;
	}

	public void setReturnInvoiceNo2(String returnInvoiceNo2) {
		this.returnInvoiceNo2 = returnInvoiceNo2;
	}
	
	@Length(min=0, max=20, message="退货快递公司长度必须介于 0 和 20 之间")
	public String getReturnExpressName2() {
		return returnExpressName2;
	}

	public void setReturnExpressName2(String returnExpressName2) {
		this.returnExpressName2 = returnExpressName2;
	}
	
	@Length(min=0, max=11, message="退货收货人电话长度必须介于 0 和 11 之间")
	public String getReturnPhone() {
		return returnPhone;
	}

	public void setReturnPhone(String returnPhone) {
		this.returnPhone = returnPhone;
	}
	
	@Length(min=0, max=100, message="退货收货人长度必须介于 0 和 100 之间")
	public String getReturnConsignee2() {
		return returnConsignee2;
	}

	public void setReturnConsignee2(String returnConsignee2) {
		this.returnConsignee2 = returnConsignee2;
	}
	
	@Length(min=0, max=200, message="退货地址长度必须介于 0 和 200 之间")
	public String getReturnAddress() {
		return returnAddress;
	}

	public void setReturnAddress(String returnAddress) {
		this.returnAddress = returnAddress;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	
	@Length(min=0, max=11, message="售后状态长度必须介于 0 和 11 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=200, message="拒绝内容长度必须介于 0 和 200 之间")
	public String getRefuseContent() {
		return refuseContent;
	}

	public void setRefuseContent(String refuseContent) {
		this.refuseContent = refuseContent;
	}
	
}