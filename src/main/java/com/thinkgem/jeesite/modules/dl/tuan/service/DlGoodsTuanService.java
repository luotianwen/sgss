/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dl.tuan.service;

import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dl.tuan.entity.DlGoodsTuan;
import com.thinkgem.jeesite.modules.dl.tuan.dao.DlGoodsTuanDao;
import com.thinkgem.jeesite.modules.dl.tuan.entity.DlTuanGoodsSku;
import com.thinkgem.jeesite.modules.dl.tuan.dao.DlTuanGoodsSkuDao;

/**
 * 团购商品Service
 * @author martins
 * @version 2019-07-11
 */
@Service
@Transactional(readOnly = true)
public class DlGoodsTuanService extends CrudService<DlGoodsTuanDao, DlGoodsTuan> {

	@Autowired
	private DlTuanGoodsSkuDao dlTuanGoodsSkuDao;
	
	public DlGoodsTuan get(String id) {
		DlGoodsTuan dlGoodsTuan = super.get(id);
		dlGoodsTuan.setDlTuanGoodsSkuList(dlTuanGoodsSkuDao.findList(new DlTuanGoodsSku(dlGoodsTuan)));
		return dlGoodsTuan;
	}
	
	public List<DlGoodsTuan> findList(DlGoodsTuan dlGoodsTuan) {
		return super.findList(dlGoodsTuan);
	}
	
	public Page<DlGoodsTuan> findPage(Page<DlGoodsTuan> page, DlGoodsTuan dlGoodsTuan) {
		return super.findPage(page, dlGoodsTuan);
	}
	
	@Transactional(readOnly = false)
	public void save(DlGoodsTuan dlGoodsTuan) {
		if (dlGoodsTuan.getDetails()!=null){
			dlGoodsTuan.setDetails(StringEscapeUtils.unescapeHtml4(dlGoodsTuan.getDetails()));
		}

		super.save(dlGoodsTuan);
		for (DlTuanGoodsSku dlTuanGoodsSku : dlGoodsTuan.getDlTuanGoodsSkuList()){
			if (dlTuanGoodsSku.getId() == null){
				continue;
			}
			if (DlTuanGoodsSku.DEL_FLAG_NORMAL.equals(dlTuanGoodsSku.getDelFlag())){
				if (StringUtils.isBlank(dlTuanGoodsSku.getId())){
					dlTuanGoodsSku.setGoods(dlGoodsTuan);
					dlTuanGoodsSku.preInsert();
					dlTuanGoodsSkuDao.insert(dlTuanGoodsSku);
				}else{
					dlTuanGoodsSku.preUpdate();
					dlTuanGoodsSkuDao.update(dlTuanGoodsSku);
				}
			}else{
				dlTuanGoodsSkuDao.delete(dlTuanGoodsSku);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(DlGoodsTuan dlGoodsTuan) {
		super.delete(dlGoodsTuan);
		dlTuanGoodsSkuDao.delete(new DlTuanGoodsSku(dlGoodsTuan));
	}
	
}