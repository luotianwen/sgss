/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.user.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sgss.user.entity.Suser;

/**
 * 用户管理DAO接口
 * @author martins
 * @version 2018-11-22
 */
@MyBatisDao
public interface SuserDao extends CrudDao<Suser> {
	
}