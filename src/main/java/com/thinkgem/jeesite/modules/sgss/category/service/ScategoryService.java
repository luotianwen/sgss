/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.category.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sgss.category.entity.Scategory;
import com.thinkgem.jeesite.modules.sgss.category.dao.ScategoryDao;

/**
 * 分类管理Service
 * @author martins
 * @version 2018-11-19
 */
@Service
@Transactional(readOnly = true)
public class ScategoryService extends TreeService<ScategoryDao, Scategory> {

	public Scategory get(String id) {
		return super.get(id);
	}
	
	public List<Scategory> findList(Scategory scategory) {
		if (StringUtils.isNotBlank(scategory.getParentIds())){
			scategory.setParentIds(","+scategory.getParentIds()+",");
		}
		return super.findList(scategory);
	}
	
	@Transactional(readOnly = false)
	public void save(Scategory scategory) {
		super.save(scategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(Scategory scategory) {
		super.delete(scategory);
	}
	
}