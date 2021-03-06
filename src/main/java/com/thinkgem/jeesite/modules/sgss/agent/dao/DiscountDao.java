/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.agent.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sgss.agent.entity.Discount;

/**
 * 折扣DAO接口
 * @author martins
 * @version 2019-03-06
 */
@MyBatisDao
public interface DiscountDao extends CrudDao<Discount> {
	
}