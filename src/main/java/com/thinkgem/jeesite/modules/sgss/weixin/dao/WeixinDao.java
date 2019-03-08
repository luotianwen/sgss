/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.weixin.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sgss.weixin.entity.Weixin;

/**
 * 公众号配置DAO接口
 * @author martins
 * @version 2019-03-08
 */
@MyBatisDao
public interface WeixinDao extends CrudDao<Weixin> {
	
}