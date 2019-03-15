/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.agent.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sgss.agent.entity.AgentOrder;

/**
 * 提现申请DAO接口
 * @author martins
 * @version 2019-03-15
 */
@MyBatisDao
public interface AgentOrderDao extends CrudDao<AgentOrder> {
	
}