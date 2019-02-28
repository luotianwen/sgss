/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.order.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sgss.order.entity.OrderAfterSalesLog;

/**
 * 售后退款日志DAO接口
 * @author martins
 * @version 2019-02-28
 */
@MyBatisDao
public interface OrderAfterSalesLogDao extends CrudDao<OrderAfterSalesLog> {
	
}