/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sgss.order.entity.OrderAfterSales;
import com.thinkgem.jeesite.modules.sgss.order.dao.OrderAfterSalesDao;

/**
 * 订单售后Service
 * @author martins
 * @version 2018-11-22
 */
@Service
@Transactional(readOnly = true)
public class OrderAfterSalesService extends CrudService<OrderAfterSalesDao, OrderAfterSales> {

	public OrderAfterSales get(String id) {
		return super.get(id);
	}
	
	public List<OrderAfterSales> findList(OrderAfterSales orderAfterSales) {
		return super.findList(orderAfterSales);
	}
	
	public Page<OrderAfterSales> findPage(Page<OrderAfterSales> page, OrderAfterSales orderAfterSales) {
		return super.findPage(page, orderAfterSales);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderAfterSales orderAfterSales) {
		super.save(orderAfterSales);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderAfterSales orderAfterSales) {
		super.delete(orderAfterSales);
	}
	
}