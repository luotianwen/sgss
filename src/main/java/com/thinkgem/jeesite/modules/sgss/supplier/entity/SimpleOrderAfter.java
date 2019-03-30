/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.supplier.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 总售后管理Entity
 * @author martins
 * @version 2019-03-27
 */
public class SimpleOrderAfter extends DataEntity<SimpleOrderAfter> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 售后类型
	private Supplier supplier;		// 供应商
	private String no;		// 序号
	private String orderid;		// 订单ID
	private String articleno;		// 货号
	private String address;		// 地址
	private String consignee;		// 收件人
	private String phone;		// 手机
	private String afterstate;		// 售后状态
	private String state;		// 状态
	private String backaddress;		// 退货地址
	private String backcourier;		// 换货快递公司
	private String backnumber;		// 换货单号
	private String courier;		// 退货公司
	private String delivernumber;		// 退货快递单号
	private String backmoney;		// 换货快递费用
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private String delivernumberOk;
	private String backnumberOk;

	public String getDelivernumberOk() {
		return delivernumberOk;
	}

	public void setDelivernumberOk(String delivernumberOk) {
		this.delivernumberOk = delivernumberOk;
	}

	public String getBacknumberOk() {
		return backnumberOk;
	}

	public void setBacknumberOk(String backnumberOk) {
		this.backnumberOk = backnumberOk;
	}

	public SimpleOrderAfter() {
		super();
	}

	public SimpleOrderAfter(String id){
		super(id);
	}

	@Length(min=0, max=1, message="售后类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@Length(min=1, max=11, message="序号长度必须介于 1 和 11 之间")
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	@Length(min=0, max=32, message="订单ID长度必须介于 0 和 32 之间")
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	@Length(min=0, max=200, message="货号长度必须介于 0 和 200 之间")
	public String getArticleno() {
		return articleno;
	}

	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}
	
	@Length(min=0, max=200, message="地址长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=100, message="收件人长度必须介于 0 和 100 之间")
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	@Length(min=0, max=50, message="手机长度必须介于 0 和 50 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=1, message="售后状态长度必须介于 0 和 1 之间")
	public String getAfterstate() {
		return afterstate;
	}

	public void setAfterstate(String afterstate) {
		this.afterstate = afterstate;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=200, message="退货地址长度必须介于 0 和 200 之间")
	public String getBackaddress() {
		return backaddress;
	}

	public void setBackaddress(String backaddress) {
		this.backaddress = backaddress;
	}
	
	@Length(min=0, max=100, message="退货快递公司长度必须介于 0 和 100 之间")
	public String getBackcourier() {
		return backcourier;
	}

	public void setBackcourier(String backcourier) {
		this.backcourier = backcourier;
	}
	
	@Length(min=0, max=32, message="退货单号长度必须介于 0 和 32 之间")
	public String getBacknumber() {
		return backnumber;
	}

	public void setBacknumber(String backnumber) {
		this.backnumber = backnumber;
	}
	
	@Length(min=0, max=100, message="换货快递公司长度必须介于 0 和 100 之间")
	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}
	
	@Length(min=0, max=32, message="换货快递单号长度必须介于 0 和 32 之间")
	public String getDelivernumber() {
		return delivernumber;
	}

	public void setDelivernumber(String delivernumber) {
		this.delivernumber = delivernumber;
	}
	
	@Length(min=0, max=10, message="换货快递费用长度必须介于 0 和 10 之间")
	public String getBackmoney() {
		return backmoney;
	}

	public void setBackmoney(String backmoney) {
		this.backmoney = backmoney;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
}