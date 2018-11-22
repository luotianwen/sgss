/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sgss.user.entity.Suser;
import com.thinkgem.jeesite.modules.sgss.user.dao.SuserDao;

/**
 * 用户管理Service
 * @author martins
 * @version 2018-11-22
 */
@Service
@Transactional(readOnly = true)
public class SuserService extends CrudService<SuserDao, Suser> {

	public Suser get(String id) {
		return super.get(id);
	}
	
	public List<Suser> findList(Suser suser) {
		return super.findList(suser);
	}
	
	public Page<Suser> findPage(Page<Suser> page, Suser suser) {
		return super.findPage(page, suser);
	}
	
	@Transactional(readOnly = false)
	public void save(Suser suser) {
		super.save(suser);
	}
	
	@Transactional(readOnly = false)
	public void delete(Suser suser) {
		super.delete(suser);
	}
	
}