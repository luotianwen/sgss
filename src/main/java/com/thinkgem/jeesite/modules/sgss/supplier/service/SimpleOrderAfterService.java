/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.supplier.service;

import java.util.List;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sgss.supplier.entity.SimpleOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sgss.supplier.entity.SimpleOrderAfter;
import com.thinkgem.jeesite.modules.sgss.supplier.dao.SimpleOrderAfterDao;

/**
 * 总售后管理Service
 * @author martins
 * @version 2019-03-27
 */
@Service
@Transactional(readOnly = true)
public class SimpleOrderAfterService extends CrudService<SimpleOrderAfterDao, SimpleOrderAfter> {

	public SimpleOrderAfter get(String id) {
		return super.get(id);
	}
	
	public List<SimpleOrderAfter> findList(SimpleOrderAfter simpleOrderAfter) {
		return super.findList(simpleOrderAfter);
	}
	
	public Page<SimpleOrderAfter> findPage(Page<SimpleOrderAfter> page, SimpleOrderAfter simpleOrderAfter) {
		return super.findPage(page, simpleOrderAfter);
	}
	
	@Transactional(readOnly = false)
	public void save(SimpleOrderAfter simpleOrderAfter) {
		if(StringUtils.isEmpty(simpleOrderAfter.getId())) {
			SimpleOrder simpleOrder = new SimpleOrder();
			simpleOrder.setOrderid(simpleOrderAfter.getOrderid());
			simpleOrderService.aftersave(simpleOrder);
		}
		super.save(simpleOrderAfter);

	}
	
	@Transactional(readOnly = false)
	public void delete(SimpleOrderAfter simpleOrderAfter) {
		super.delete(simpleOrderAfter);
	}
	@Autowired
	private SimpleOrderService simpleOrderService;
	@Transactional(readOnly = false)
	public void backaddress(SimpleOrderAfter simpleOrderAfter) {
		SimpleOrder simpleOrder=new SimpleOrder();
		simpleOrder.setOrderid(simpleOrderAfter.getOrderid());
		simpleOrderService.aftersaveok(simpleOrder);
		dao.backaddress(simpleOrderAfter);
	}

	//换货
	@Transactional(readOnly = false)
	public void backcourier(SimpleOrderAfter simpleOrderAfter) {
		SimpleOrder simpleOrder=new SimpleOrder();
		simpleOrder.setOrderid(simpleOrderAfter.getOrderid());
		simpleOrderService.aftersavepass(simpleOrder);
		dao.backcourier(simpleOrderAfter);
	}
   //退货
	@Transactional(readOnly = false)
	public void courier(SimpleOrderAfter simpleOrderAfter) {
		if("1".equals(simpleOrderAfter.getType())){
			dao.updateCourierOk(simpleOrderAfter);
			SimpleOrder simpleOrder=new SimpleOrder();
			simpleOrder.setOrderid(simpleOrderAfter.getOrderid());
			simpleOrderService.aftersavepass(simpleOrder);
		}
		dao.courier(simpleOrderAfter);
	}
}