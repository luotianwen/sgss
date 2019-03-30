/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.supplier.service;

import java.util.List;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sgss.supplier.entity.Supplier;
import com.thinkgem.jeesite.modules.sgss.supplier.dao.SupplierDao;

/**
 * 供应商管理Service
 * @author martins
 * @version 2019-03-26
 */
@Service
@Transactional(readOnly = true)
public class SupplierService extends CrudService<SupplierDao, Supplier> {
	@Autowired
	private SystemService systemService;
	public Supplier get(String id) {
		return super.get(id);
	}
	
	public List<Supplier> findList(Supplier supplier) {
		return super.findList(supplier);
	}
	
	public Page<Supplier> findPage(Page<Supplier> page, Supplier supplier) {
		return super.findPage(page, supplier);
	}
	
	@Transactional(readOnly = false)
	public void save(Supplier supplier) {
		if(supplier.getIsNewRecord()){
			this.saveUser(supplier);
		}
		super.save(supplier);
	}
	private void saveUser(Supplier agent){
		String phone=agent.getPhone();
		User user=new User();
		user.setPassword(SystemService.entryptPassword(phone.substring(phone.length()-6,phone.length())));
		user.setLoginName(agent.getLoginName());
		user.setCompany(new Office("1"));
		user.setOffice(new Office("3"));
		user.setName(agent.getName());
		List<Role> roleList = Lists.newArrayList();
		Role r=new Role();
		r.setId("8");
		roleList.add(r);
		user.setRoleList(roleList);
		// 保存用户信息
		systemService.saveUser(user);
		agent.setUser(user);

	}
	@Transactional(readOnly = false)
	public void delete(Supplier supplier) {
		super.delete(supplier);
	}

	public Supplier getUserId(String id) {
		return  dao.getUserId(id);
	}
}