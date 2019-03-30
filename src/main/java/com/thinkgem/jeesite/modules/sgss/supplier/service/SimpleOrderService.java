/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.supplier.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.modules.sgss.goods.dao.GoodsDetailDao;
import com.thinkgem.jeesite.modules.sgss.goods.dao.GoodsSkuDao;
import com.thinkgem.jeesite.modules.sgss.goods.entity.GoodsDetail;
import com.thinkgem.jeesite.modules.sgss.goods.entity.GoodsSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sgss.supplier.entity.SimpleOrder;
import com.thinkgem.jeesite.modules.sgss.supplier.dao.SimpleOrderDao;

/**
 * 总订单管理Service
 * @author martins
 * @version 2019-03-27
 */
@Service
@Transactional(readOnly = true)
public class SimpleOrderService extends CrudService<SimpleOrderDao, SimpleOrder> {
	@Autowired
	private GoodsSkuDao goodsSkuDao;
	public SimpleOrder get(String id) {
		return super.get(id);
	}
	
	public List<SimpleOrder> findList(SimpleOrder simpleOrder) {
		return super.findList(simpleOrder);
	}
	
	public Page<SimpleOrder> findPage(Page<SimpleOrder> page, SimpleOrder simpleOrder) {
		return super.findPage(page, simpleOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(SimpleOrder simpleOrder) {
		if(simpleOrder.getIsNewRecord()){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
			String onumber = sdf.format(new Date());
			simpleOrder.setOrderid(onumber);
		}
		super.save(simpleOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(SimpleOrder simpleOrder) {
		super.delete(simpleOrder);
	}
	@Transactional(readOnly = false)
	public void dispatchSave(SimpleOrder simpleOrder) {
		String[] id=simpleOrder.getSupplier().getId().split("-");
		simpleOrder.getSupplier().setId(id[0]);
		GoodsSku goodsSku=goodsSkuDao.get(id[1]);
		simpleOrder.setSettlementPrice(goodsSku.getSettlementPrice());
		simpleOrder.setProfit(goodsSku.getProfit());
		dao.dispatchSave(simpleOrder);
	}
	@Transactional(readOnly = false)
	public void noSku(SimpleOrder simpleOrder) {
		dao.noSku(simpleOrder);
	}

	@Transactional(readOnly = false)
	public void fast(SimpleOrder simpleOrder) throws Exception {
		double totlamoney=simpleOrder.getNum()*simpleOrder.getMoney();
		totlamoney=totlamoney+simpleOrder.getDelivermoney();
		simpleOrder.setTotalmoney(totlamoney);
		simpleOrder.preUpdate();
		dao.fast(simpleOrder);
	}
	@Transactional(readOnly = false)
	public void aftersave(SimpleOrder simpleOrder) {
		dao.aftersave(simpleOrder);
	}

	@Transactional(readOnly = false)
	public void aftersaveok(SimpleOrder simpleOrder) {
		dao.aftersaveok(simpleOrder);
	}

	@Transactional(readOnly = false)
	public void aftersavepass(SimpleOrder simpleOrder) {
		dao.aftersavepass(simpleOrder);
	}
}