/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dl.tuan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dl.tuan.entity.DlTuanOrder;
import com.thinkgem.jeesite.modules.dl.tuan.dao.DlTuanOrderDao;

/**
 * 团购订单Service
 * @author martins
 * @version 2019-07-11
 */
@Service
@Transactional(readOnly = true)
public class DlTuanOrderService extends CrudService<DlTuanOrderDao, DlTuanOrder> {

	public DlTuanOrder get(String id) {
		return super.get(id);
	}
	
	public List<DlTuanOrder> findList(DlTuanOrder dlTuanOrder) {
		return super.findList(dlTuanOrder);
	}
	
	public Page<DlTuanOrder> findPage(Page<DlTuanOrder> page, DlTuanOrder dlTuanOrder) {
		return super.findPage(page, dlTuanOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(DlTuanOrder dlTuanOrder) {
		super.save(dlTuanOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(DlTuanOrder dlTuanOrder) {
		super.delete(dlTuanOrder);
	}
	
}