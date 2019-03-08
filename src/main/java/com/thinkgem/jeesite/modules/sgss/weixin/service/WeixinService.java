/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.weixin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sgss.weixin.entity.Weixin;
import com.thinkgem.jeesite.modules.sgss.weixin.dao.WeixinDao;

/**
 * 公众号配置Service
 * @author martins
 * @version 2019-03-08
 */
@Service
@Transactional(readOnly = true)
public class WeixinService extends CrudService<WeixinDao, Weixin> {

	public Weixin get(String id) {
		return super.get(id);
	}
	
	public List<Weixin> findList(Weixin weixin) {
		return super.findList(weixin);
	}
	
	public Page<Weixin> findPage(Page<Weixin> page, Weixin weixin) {
		return super.findPage(page, weixin);
	}
	
	@Transactional(readOnly = false)
	public void save(Weixin weixin) {
		super.save(weixin);
	}
	
	@Transactional(readOnly = false)
	public void delete(Weixin weixin) {
		super.delete(weixin);
	}
	
}