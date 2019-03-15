/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sgss.agent.entity.AgentOrder;
import com.thinkgem.jeesite.modules.sgss.agent.dao.AgentOrderDao;
import com.thinkgem.jeesite.modules.sgss.agent.entity.AgentOrderDetail;
import com.thinkgem.jeesite.modules.sgss.agent.dao.AgentOrderDetailDao;

/**
 * 提现申请Service
 * @author martins
 * @version 2019-03-15
 */
@Service
@Transactional(readOnly = true)
public class AgentOrderService extends CrudService<AgentOrderDao, AgentOrder> {

	@Autowired
	private AgentOrderDetailDao agentOrderDetailDao;
	
	public AgentOrder get(String id) {
		AgentOrder agentOrder = super.get(id);
		agentOrder.setAgentOrderDetailList(agentOrderDetailDao.findList(new AgentOrderDetail(agentOrder)));
		return agentOrder;
	}
	
	public List<AgentOrder> findList(AgentOrder agentOrder) {
		return super.findList(agentOrder);
	}
	
	public Page<AgentOrder> findPage(Page<AgentOrder> page, AgentOrder agentOrder) {
		return super.findPage(page, agentOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(AgentOrder agentOrder) {
		super.save(agentOrder);
		for (AgentOrderDetail agentOrderDetail : agentOrder.getAgentOrderDetailList()){
			if (agentOrderDetail.getId() == null){
				continue;
			}
			if (AgentOrderDetail.DEL_FLAG_NORMAL.equals(agentOrderDetail.getDelFlag())){
				if (StringUtils.isBlank(agentOrderDetail.getId())){
					agentOrderDetail.setAgentorder(agentOrder);
					agentOrderDetail.preInsert();
					agentOrderDetailDao.insert(agentOrderDetail);
				}else{
					agentOrderDetail.preUpdate();
					agentOrderDetailDao.update(agentOrderDetail);
				}
			}else{
				agentOrderDetailDao.delete(agentOrderDetail);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(AgentOrder agentOrder) {
		super.delete(agentOrder);
		agentOrderDetailDao.delete(new AgentOrderDetail(agentOrder));
	}
	
}