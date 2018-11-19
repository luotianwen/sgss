/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.goods.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sgss.goods.entity.GoodsDetail;
import com.thinkgem.jeesite.modules.sgss.goods.dao.GoodsDetailDao;

/**
 * 商品详情Service
 * @author martins
 * @version 2018-11-19
 */
@Service
@Transactional(readOnly = true)
public class GoodsDetailService extends CrudService<GoodsDetailDao, GoodsDetail> {

	public GoodsDetail get(String id) {
		return super.get(id);
	}
	
	public List<GoodsDetail> findList(GoodsDetail goodsDetail) {
		return super.findList(goodsDetail);
	}
	
	public Page<GoodsDetail> findPage(Page<GoodsDetail> page, GoodsDetail goodsDetail) {
		return super.findPage(page, goodsDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(GoodsDetail goodsDetail) {
		super.save(goodsDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(GoodsDetail goodsDetail) {
		super.delete(goodsDetail);
	}
	
}