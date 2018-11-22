/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.coupon.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sgss.coupon.entity.Coupon;
import com.thinkgem.jeesite.modules.sgss.coupon.dao.CouponDao;

/**
 * 优惠券Service
 * @author martins
 * @version 2018-11-22
 */
@Service
@Transactional(readOnly = true)
public class CouponService extends CrudService<CouponDao, Coupon> {

	public Coupon get(String id) {
		return super.get(id);
	}
	
	public List<Coupon> findList(Coupon coupon) {
		return super.findList(coupon);
	}
	
	public Page<Coupon> findPage(Page<Coupon> page, Coupon coupon) {
		return super.findPage(page, coupon);
	}
	
	@Transactional(readOnly = false)
	public void save(Coupon coupon) {
		super.save(coupon);
	}
	
	@Transactional(readOnly = false)
	public void delete(Coupon coupon) {
		super.delete(coupon);
	}
	
}