/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.goods.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sgss.goods.entity.Sdg;
import com.thinkgem.jeesite.modules.sgss.goods.dao.SdgDao;

/**
 * 动力折扣Service
 * @author 动力折扣
 * @version 2019-12-24
 */
@Service
@Transactional(readOnly = true)
public class SdgService extends CrudService<SdgDao, Sdg> {

	public Sdg get(String id) {
		return super.get(id);
	}
	
	public List<Sdg> findList(Sdg sdg) {
		return super.findList(sdg);
	}
	
	public Page<Sdg> findPage(Page<Sdg> page, Sdg sdg) {
		return super.findPage(page, sdg);
	}
	
	@Transactional(readOnly = false)
	public void save(Sdg sdg) {
		super.save(sdg);
	}
	
	@Transactional(readOnly = false)
	public void delete(Sdg sdg) {
		super.delete(sdg);
	}
	
}