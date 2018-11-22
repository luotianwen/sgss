/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.order.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sgss.order.entity.OrderAfterSales;

/**
 * 订单售后DAO接口
 * @author martins
 * @version 2018-11-22
 */
@MyBatisDao
public interface OrderAfterSalesDao extends CrudDao<OrderAfterSales> {
	
}