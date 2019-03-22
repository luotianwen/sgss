/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.jd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sgss.jd.entity.Jd;

/**
 * 京东抓取DAO接口
 * @author martins
 * @version 2019-03-22
 */
@MyBatisDao
public interface JdDao extends CrudDao<Jd> {
	
}