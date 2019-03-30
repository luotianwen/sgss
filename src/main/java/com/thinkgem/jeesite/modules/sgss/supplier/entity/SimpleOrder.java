/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.supplier.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 总订单管理Entity
 * @author martins
 * @version 2019-03-27
 */
public class SimpleOrder extends DataEntity<SimpleOrder> {
	
	private static final long serialVersionUID = 1L;
	private String sourcetype;		// 订单来源
	private Supplier supplier;		// 供应商
	private String no;		// 序号
	private String orderid;		// 订单id
	private String tradeid;		// 三方订单编号
	private String articleno;		// 货号
	private int num;		// 数量
	private Double money;		// 金额
	private String afterstate;		// 售后状态
	private String type;		// 售后类型
	private Double price;		// 单价
	private String state;		// 状态
	private String courier;		// 快递公司
	private String delivernumber;		// 快递单号
	private Double delivermoney;		// 快递费用
	private String consignee;		// 收件人
	private String phone;		// 手机
	private String address;		// 地址
	private String colour;		// 颜色
	private String spec;		// 规格尺码
	private Double totalmoney;		// 总价
	private String isaccount;		// 是否对账
	private Double settlementPrice;		// 供货价
	private Double profit;		// 利润
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private String isdelivernumber;

	public int getNum() {
		return num;
	}

	public Double getMoney() {
		return money;
	}

	public Double getPrice() {
		return price;
	}

	public Double getDelivermoney() {
		return delivermoney;
	}

	public Double getTotalmoney() {
		return totalmoney;
	}

	public Double getSettlementPrice() {
		return settlementPrice;
	}

	public Double getProfit() {
		return profit;
	}

	public String getIsdelivernumber() {
		return isdelivernumber;
	}

	public void setIsdelivernumber(String isdelivernumber) {
		this.isdelivernumber = isdelivernumber;
	}

	public SimpleOrder() {
		super();
	}

	public SimpleOrder(String id){
		super(id);
	}

	@Length(min=0, max=2, message="订单来源长度必须介于 0 和 2 之间")
	public String getSourcetype() {
		return sourcetype;
	}

	public void setSourcetype(String sourcetype) {
		this.sourcetype = sourcetype;
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
	
	@Length(min=0, max=32, message="订单id长度必须介于 0 和 32 之间")
	public String getOrderid() {
		return orderid;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setDelivermoney(Double delivermoney) {
		this.delivermoney = delivermoney;
	}

	public void setTotalmoney(Double totalmoney) {
		this.totalmoney = totalmoney;
	}

	public void setSettlementPrice(Double settlementPrice) {
		this.settlementPrice = settlementPrice;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	@Length(min=0, max=32, message="三方订单编号长度必须介于 0 和 32 之间")
	public String getTradeid() {
		return tradeid;
	}

	public void setTradeid(String tradeid) {
		this.tradeid = tradeid;
	}
	
	@Length(min=0, max=200, message="货号长度必须介于 0 和 200 之间")
	public String getArticleno() {
		return articleno;
	}

	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}
	

	@Length(min=0, max=2, message="售后状态长度必须介于 0 和 2 之间")
	public String getAfterstate() {
		return afterstate;
	}

	public void setAfterstate(String afterstate) {
		this.afterstate = afterstate;
	}
	
	@Length(min=0, max=2, message="售后类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min=1, max=4, message="状态长度必须介于 1 和 4 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=100, message="快递公司长度必须介于 0 和 100 之间")
	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}
	
	@Length(min=0, max=32, message="快递单号长度必须介于 0 和 32 之间")
	public String getDelivernumber() {
		return delivernumber;
	}

	public void setDelivernumber(String delivernumber) {
		this.delivernumber = delivernumber;
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
	
	@Length(min=0, max=200, message="地址长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=200, message="颜色长度必须介于 0 和 200 之间")
	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
	
	@Length(min=0, max=255, message="规格尺码长度必须介于 0 和 255 之间")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	
	@Length(min=0, max=1, message="是否对账长度必须介于 0 和 1 之间")
	public String getIsaccount() {
		return isaccount;
	}

	public void setIsaccount(String isaccount) {
		this.isaccount = isaccount;
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