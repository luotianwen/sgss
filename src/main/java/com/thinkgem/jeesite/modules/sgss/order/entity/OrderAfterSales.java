/**
 * Copyright &copy; 01-016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.order.entity;

import com.thinkgem.jeesite.modules.sgss.user.entity.Suser;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单售后Entity
 * @author martins
 * @version 018-11-
 */
public class OrderAfterSales extends DataEntity<OrderAfterSales> {
	
	private static final long serialVersionUID = 1L;
	private String num;		// 序号
	private String ordernumber;		// 订单编号
	private Suser user;		// 用户
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
	private String returnInvoiceNo;		// 退货单号
	private String returnExpressName;		// 退货快递公司
	private String returnPhone;		// 退货收货人电话
	private String returnConsignee;		// 退货收货人
	private String returnAddress;		// 退货地址
	private Date returnTime;		// 退货时间
	private String state;		// 售后状态
	private String refuseContent;		// 拒绝内容
	private int returnAmount;

	private String refundId;		// 退货时间
	private String outRefundNo;		// 售后状态
	private Date refundTime;		// 退货时间
	private String refundState;		// 售后状态

	public String getRefundId() {
		return refundId;
	}

	public int getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(int returnAmount) {
		this.returnAmount = returnAmount;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public String getRefundState() {
		return refundState;
	}

	public void setRefundState(String refundState) {
		this.refundState = refundState;
	}

	public OrderAfterSales() {
		super();
	}

	public OrderAfterSales(String id){
		super(id);
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	
	public Suser getUser() {
		return user;
	}

	public void setUser(Suser user) {
		this.user = user;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getExchangePhone() {
		return exchangePhone;
	}

	public void setExchangePhone(String exchangePhone) {
		this.exchangePhone = exchangePhone;
	}
	
	public String getExchangeConsignee() {
		return exchangeConsignee;
	}

	public void setExchangeConsignee(String exchangeConsignee) {
		this.exchangeConsignee = exchangeConsignee;
	}
	
	public String getExchangeAddress() {
		return exchangeAddress;
	}

	public void setExchangeAddress(String exchangeAddress) {
		this.exchangeAddress = exchangeAddress;
	}
	
	public String getExchangeInvoiceNo() {
		return exchangeInvoiceNo;
	}

	public void setExchangeInvoiceNo(String exchangeInvoiceNo) {
		this.exchangeInvoiceNo = exchangeInvoiceNo;
	}
	
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
	
	public String getReturnInvoiceNo() {
		return returnInvoiceNo;
	}

	public void setReturnInvoiceNo(String returnInvoiceNo) {
		this.returnInvoiceNo = returnInvoiceNo;
	}
	
	public String getReturnExpressName() {
		return returnExpressName;
	}

	public void setReturnExpressName(String returnExpressName) {
		this.returnExpressName = returnExpressName;
	}
	
	public String getReturnPhone() {
		return returnPhone;
	}

	public void setReturnPhone(String returnPhone) {
		this.returnPhone = returnPhone;
	}
	
	public String getReturnConsignee() {
		return returnConsignee;
	}

	public void setReturnConsignee(String returnConsignee) {
		this.returnConsignee = returnConsignee;
	}
	
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
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getRefuseContent() {
		return refuseContent;
	}

	public void setRefuseContent(String refuseContent) {
		this.refuseContent = refuseContent;
	}
	
}