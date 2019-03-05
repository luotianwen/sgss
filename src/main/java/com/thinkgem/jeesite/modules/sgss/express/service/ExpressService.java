/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.express.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sgss.express.entity.Express;
import com.thinkgem.jeesite.modules.sgss.express.dao.ExpressDao;

/**
 * 快递配置Service
 * @author martins
 * @version 2019-03-01
 */
@Service
@Transactional(readOnly = true)
public class ExpressService extends CrudService<ExpressDao, Express> {

	public Express get(String id) {
		return super.get(id);
	}
	
	public List<Express> findList(Express express) {
		return super.findList(express);
	}
	
	public Page<Express> findPage(Page<Express> page, Express express) {
		return super.findPage(page, express);
	}
	
	@Transactional(readOnly = false)
	public void save(Express express) {
		super.save(express);
	}
	
	@Transactional(readOnly = false)
	public void delete(Express express) {
		super.delete(express);
	}
	
}