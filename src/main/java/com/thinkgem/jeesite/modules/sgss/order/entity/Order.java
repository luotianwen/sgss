/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.order.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单管理Entity
 * @author martins
 * @version 2018-11-22
 */
public class Order extends DataEntity<Order> {
	
	private static final long serialVersionUID = 1L;
	private String num;		// 序号
	private String ordernumber;		// 订单编号
	private String phone;		// 收货人电话
	private String consignee;		// 收货人
	private String address;		// 收货地址
	private User user;		// 用户
	private Date payTime;		// 付款时间
	private Date orderTime;		// 下单时间
	private String state;		// 订单状态
	private Date deliveryTime;		// 发货时间
	private String invoiceNo;		// 发货单号
	private String expressName;		// 快递公司
	private String goodsPrice;		// 商品总价
	private String freight;		// 运费
	private String favorablePrice;		// 优惠价格
	private String totalPrice;		// 总计
	private String payType;		// 支付方式
	private String couponId;		// 优惠券id
	private Date completeTime;		// 完成时间
	private List<OrderDetail> orderDetailList = Lists.newArrayList();		// 子表列表
	
	public Order() {
		super();
	}

	public Order(String id){
		super(id);
	}

	@Length(min=1, max=11, message="序号长度必须介于 1 和 11 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Length(min=1, max=20, message="订单编号长度必须介于 1 和 20 之间")
	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	
	@Length(min=0, max=11, message="收货人电话长度必须介于 0 和 11 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=100, message="收货人长度必须介于 0 和 100 之间")
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	@Length(min=0, max=200, message="收货地址长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	
	@Length(min=0, max=11, message="订单状态长度必须介于 0 和 11 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	
	@Length(min=0, max=50, message="发货单号长度必须介于 0 和 50 之间")
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	@Length(min=0, max=20, message="快递公司长度必须介于 0 和 20 之间")
	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	
	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}
	
	public String getFavorablePrice() {
		return favorablePrice;
	}

	public void setFavorablePrice(String favorablePrice) {
		this.favorablePrice = favorablePrice;
	}
	
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Length(min=0, max=11, message="支付方式长度必须介于 0 和 11 之间")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	@Length(min=0, max=32, message="优惠券id长度必须介于 0 和 32 之间")
	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	
	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
}