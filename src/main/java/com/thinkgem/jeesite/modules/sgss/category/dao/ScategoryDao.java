/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.category.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sgss.category.entity.Scategory;

/**
 * 分类管理DAO接口
 * @author martins
 * @version 2018-11-19
 */
@MyBatisDao
public interface ScategoryDao extends TreeDao<Scategory> {
	
}