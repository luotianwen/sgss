/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.agent.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 提现申请Entity
 * @author martins
 * @version 2019-03-15
 */
public class AgentOrderDetail extends DataEntity<AgentOrderDetail> {
	
	private static final long serialVersionUID = 1L;
	private String ordernumber;		// 订单号
	private String money;		// 金额
	private AgentOrder agentorder;		// agentorderid 父类
	
	public AgentOrderDetail() {
		super();
	}

	public AgentOrderDetail(String id){
		super(id);
	}

	public AgentOrderDetail(AgentOrder agentorder){
		this.agentorder = agentorder;
	}

	@Length(min=0, max=32, message="订单号长度必须介于 0 和 32 之间")
	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	@Length(min=0, max=32, message="agentorderid长度必须介于 0 和 32 之间")
	public AgentOrder getAgentorder() {
		return agentorder;
	}

	public void setAgentorder(AgentOrder agentorder) {
		this.agentorder = agentorder;
	}
	
}