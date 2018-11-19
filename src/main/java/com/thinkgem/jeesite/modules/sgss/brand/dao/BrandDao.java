/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.brand.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sgss.brand.entity.Brand;

/**
 * 品牌表DAO接口
 * @author martins
 * @version 2018-11-19
 */
@MyBatisDao
public interface BrandDao extends CrudDao<Brand> {
	
}