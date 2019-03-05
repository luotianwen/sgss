/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.express.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sgss.express.entity.Express;

/**
 * 快递配置DAO接口
 * @author martins
 * @version 2019-03-01
 */
@MyBatisDao
public interface ExpressDao extends CrudDao<Express> {
	
}