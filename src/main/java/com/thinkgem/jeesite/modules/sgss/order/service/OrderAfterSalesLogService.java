/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sgss.order.entity.OrderAfterSalesLog;
import com.thinkgem.jeesite.modules.sgss.order.dao.OrderAfterSalesLogDao;

/**
 * 售后退款日志Service
 * @author martins
 * @version 2019-02-28
 */
@Service
@Transactional(readOnly = true)
public class OrderAfterSalesLogService extends CrudService<OrderAfterSalesLogDao, OrderAfterSalesLog> {

	public OrderAfterSalesLog get(String id) {
		return super.get(id);
	}
	
	public List<OrderAfterSalesLog> findList(OrderAfterSalesLog orderAfterSalesLog) {
		return super.findList(orderAfterSalesLog);
	}
	
	public Page<OrderAfterSalesLog> findPage(Page<OrderAfterSalesLog> page, OrderAfterSalesLog orderAfterSalesLog) {
		return super.findPage(page, orderAfterSalesLog);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderAfterSalesLog orderAfterSalesLog) {
		super.save(orderAfterSalesLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderAfterSalesLog orderAfterSalesLog) {
		super.delete(orderAfterSalesLog);
	}
	
}