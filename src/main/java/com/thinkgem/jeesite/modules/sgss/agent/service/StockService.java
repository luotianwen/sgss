/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.agent.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sgss.agent.entity.Stock;
import com.thinkgem.jeesite.modules.sgss.agent.dao.StockDao;

/**
 * 库存Service
 * @author martins
 * @version 2019-03-06
 */
@Service
@Transactional(readOnly = true)
public class StockService extends CrudService<StockDao, Stock> {

	public Stock get(String id) {
		return super.get(id);
	}
	
	public List<Stock> findList(Stock stock) {
		return super.findList(stock);
	}
	
	public Page<Stock> findPage(Page<Stock> page, Stock stock) {
		return super.findPage(page, stock);
	}
	
	@Transactional(readOnly = false)
	public void save(Stock stock) {
		super.save(stock);
	}
	
	@Transactional(readOnly = false)
	public void delete(Stock stock) {
		super.delete(stock);
	}
	
}