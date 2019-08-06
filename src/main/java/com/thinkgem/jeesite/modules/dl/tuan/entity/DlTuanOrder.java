/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dl.tuan.entity;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * 团购订单Entity
 * @author martins
 * @version 2019-07-11
 */
public class DlTuanOrder extends DataEntity<DlTuanOrder> {
	
	private static final long serialVersionUID = 1L;
	private String username;		// 会员姓名
	private String mobile;		// 会员电话
	private String name;		// 商品名称
	private String artno;		// 货号
	private String spec1;		// 规格1
	private String spec2;		// 规格2
	private String number;		// 数量
	private String ordernumber;		// 订单编号
	private String phone;		// 收货人电话
	private String consignee;		// 收货人
	private String address;		// 收货地址
	private String goodsId;		// 商品id
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间

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

	public DlTuanOrder() {
		super();
	}

	public DlTuanOrder(String id){
		super(id);
	}
	@ExcelField(title="会员姓名", align=1, sort=1)
	@Length(min=0, max=255, message="会员姓名长度必须介于 0 和 255 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@ExcelField(title="会员电话", align=1, sort=2)
	@Length(min=0, max=255, message="会员电话长度必须介于 0 和 255 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@ExcelField(title="商品名称", align=1, sort=7)
	@Length(min=0, max=200, message="商品名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ExcelField(title="货号", align=1, sort=8)
	@Length(min=0, max=20, message="货号长度必须介于 0 和 20 之间")
	public String getArtno() {
		return artno;
	}

	public void setArtno(String artno) {
		this.artno = artno;
	}
	@ExcelField(title="规格1", align=1, sort=9)
	@Length(min=0, max=20, message="规格1长度必须介于 0 和 20 之间")
	public String getSpec1() {
		return spec1;
	}

	public void setSpec1(String spec1) {
		this.spec1 = spec1;
	}
	@ExcelField(title="规格2", align=1, sort=10)
	@Length(min=0, max=20, message="规格2长度必须介于 0 和 20 之间")
	public String getSpec2() {
		return spec2;
	}

	public void setSpec2(String spec2) {
		this.spec2 = spec2;
	}
	@ExcelField(title="数量", align=1, sort=11)
	@Length(min=0, max=11, message="数量长度必须介于 0 和 11 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	@ExcelField(title="订单编号", align=1, sort=0)
	@Length(min=1, max=10, message="订单编号长度必须介于 1 和 10 之间")
	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	@ExcelField(title="收货人电话", align=1, sort=5)
	@Length(min=0, max=11, message="收货人电话长度必须介于 0 和 11 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@ExcelField(title="收货人", align=1, sort=3)
	@Length(min=0, max=100, message="收货人长度必须介于 0 和 100 之间")
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	@ExcelField(title="收货地址", align=1, sort=4)
	@Length(min=0, max=200, message="收货地址长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=32, message="商品id长度必须介于 0 和 32 之间")
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
}