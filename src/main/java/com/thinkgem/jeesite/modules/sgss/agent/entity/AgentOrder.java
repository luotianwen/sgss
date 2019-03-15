/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.agent.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 提现申请Entity
 * @author martins
 * @version 2019-03-15
 */
public class AgentOrder extends DataEntity<AgentOrder> {
	
	private static final long serialVersionUID = 1L;
	private String num;		// 序号
	private String state;		// 提现状态
	private String money;		// 提现金额
	private Agent agent;		// 代理
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private Date beginUpdateDate;		// 开始 付款时间
	private Date endUpdateDate;		// 结束 付款时间
	private List<AgentOrderDetail> agentOrderDetailList = Lists.newArrayList();		// 子表列表
	
	public AgentOrder() {
		super();
	}

	public AgentOrder(String id){
		super(id);
	}

	@Length(min=1, max=11, message="序号长度必须介于 1 和 11 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Length(min=0, max=1, message="提现状态长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
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
		
	public Date getBeginUpdateDate() {
		return beginUpdateDate;
	}

	public void setBeginUpdateDate(Date beginUpdateDate) {
		this.beginUpdateDate = beginUpdateDate;
	}
	
	public Date getEndUpdateDate() {
		return endUpdateDate;
	}

	public void setEndUpdateDate(Date endUpdateDate) {
		this.endUpdateDate = endUpdateDate;
	}
		
	public List<AgentOrderDetail> getAgentOrderDetailList() {
		return agentOrderDetailList;
	}

	public void setAgentOrderDetailList(List<AgentOrderDetail> agentOrderDetailList) {
		this.agentOrderDetailList = agentOrderDetailList;
	}
}