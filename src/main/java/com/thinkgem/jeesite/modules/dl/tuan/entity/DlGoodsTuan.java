/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dl.tuan.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 团购商品Entity
 * @author martins
 * @version 2019-07-11
 */
public class DlGoodsTuan extends DataEntity<DlGoodsTuan> {
	
	private static final long serialVersionUID = 1L;
	private String num;		// 序号
	private String name;		// 名称
	private String artno;		// 货号
	private String logo;		// 主图
	private Long sort;		// 序号
	private double marketPrice;		// 市场售价
	private double price;		// 团购价
	private String state;		// 状态
	private String spec1;		// 规格1
	private String spec2;		// 规格2
	private String imgs;		// 图片
	private double costPrice;		// 结算价
	private String details;		// 商品详情
	private Date beginDate;		// 团购开始日期
	private Date endDate;		// 团购结束日期
	private Date beginBeginDate;		// 开始 团购开始日期
	private Date endBeginDate;		// 结束 团购开始日期
	private Date beginEndDate;		// 开始 团购结束日期
	private Date endEndDate;		// 结束 团购结束日期
	private List<DlTuanGoodsSku> dlTuanGoodsSkuList = Lists.newArrayList();		// 子表列表
	
	public DlGoodsTuan() {
		super();
	}

	public DlGoodsTuan(String id){
		super(id);
	}

	@Length(min=1, max=11, message="序号长度必须介于 1 和 11 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Length(min=0, max=1000, message="名称长度必须介于 0 和 1000 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=200, message="货号长度必须介于 0 和 200 之间")
	public String getArtno() {
		return artno;
	}

	public void setArtno(String artno) {
		this.artno = artno;
	}
	
	@Length(min=0, max=200, message="主图长度必须介于 0 和 200 之间")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=20, message="规格1长度必须介于 0 和 20 之间")
	public String getSpec1() {
		return spec1;
	}

	public void setSpec1(String spec1) {
		this.spec1 = spec1;
	}
	
	@Length(min=0, max=20, message="规格2长度必须介于 0 和 20 之间")
	public String getSpec2() {
		return spec2;
	}

	public void setSpec2(String spec2) {
		this.spec2 = spec2;
	}
	
	@Length(min=0, max=5000, message="图片长度必须介于 0 和 5000 之间")
	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	
	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
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
		
	public List<DlTuanGoodsSku> getDlTuanGoodsSkuList() {
		return dlTuanGoodsSkuList;
	}

	public void setDlTuanGoodsSkuList(List<DlTuanGoodsSku> dlTuanGoodsSkuList) {
		this.dlTuanGoodsSkuList = dlTuanGoodsSkuList;
	}
}