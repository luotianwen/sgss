/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dl.tuan.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dl.tuan.entity.DlTuanOrder;

/**
 * 团购订单DAO接口
 * @author martins
 * @version 2019-07-11
 */
@MyBatisDao
public interface DlTuanOrderDao extends CrudDao<DlTuanOrder> {
	
}