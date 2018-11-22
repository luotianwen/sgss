/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.coupon.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sgss.coupon.entity.Coupon;

/**
 * 优惠券DAO接口
 * @author martins
 * @version 2018-11-22
 */
@MyBatisDao
public interface CouponDao extends CrudDao<Coupon> {
	
}