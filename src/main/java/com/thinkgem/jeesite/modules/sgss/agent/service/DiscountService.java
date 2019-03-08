/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.agent.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sgss.agent.entity.Discount;
import com.thinkgem.jeesite.modules.sgss.agent.dao.DiscountDao;

/**
 * 折扣Service
 * @author martins
 * @version 2019-03-06
 */
@Service
@Transactional(readOnly = true)
public class DiscountService extends CrudService<DiscountDao, Discount> {

	public Discount get(String id) {
		return super.get(id);
	}
	
	public List<Discount> findList(Discount discount) {
		return super.findList(discount);
	}
	
	public Page<Discount> findPage(Page<Discount> page, Discount discount) {
		return super.findPage(page, discount);
	}
	
	@Transactional(readOnly = false)
	public void save(Discount discount) {
		super.save(discount);
	}
	
	@Transactional(readOnly = false)
	public void delete(Discount discount) {
		super.delete(discount);
	}
	
}