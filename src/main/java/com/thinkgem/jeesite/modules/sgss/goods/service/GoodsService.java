/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.goods.service;

import java.util.List;

import com.thinkgem.jeesite.modules.sgss.category.entity.Scategory;
import com.thinkgem.jeesite.modules.sgss.goods.dao.*;
import com.thinkgem.jeesite.modules.sgss.goods.entity.*;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 商品管理Service
 * @author martins
 * @version 2018-11-19
 */
@Service
@Transactional(readOnly = true)
public class GoodsService extends CrudService<GoodsDao, Goods> {

	@Autowired
	private GoodsCategoryDao goodsCategoryDao;
	@Autowired
	private GoodsPicDao goodsPicDao;
	@Autowired
	private GoodsSkuDao goodsSkuDao;
	@Autowired
	private GoodsDetailDao goodsDetailDao;

	public Goods get(String id) {
		Goods goods = super.get(id);
		List<GoodsCategory> goodsCategoryList=goodsCategoryDao.findList(new GoodsCategory(goods));
		goods.setGoodsCategoryList(goodsCategoryList);
		StringBuffer categoryIds=new StringBuffer();
		StringBuffer categoryName=new StringBuffer();
		for (GoodsCategory c:goodsCategoryList
			 ) {
			if(null!=c.getCategory()) {
				categoryIds.append(c.getCategory().getId()).append(",");
				categoryName.append(c.getCategory().getName()).append(",");
			}
		}
		goods.setCategoryId(categoryIds.toString());
		goods.setCategoryName(categoryName.toString());
		goods.setGoodsPicList(goodsPicDao.findList(new GoodsPic(goods)));
		goods.setGoodsSkuList(goodsSkuDao.findList(new GoodsSku(goods)));
		goods.setDetail(goodsDetailDao.getDetailByGoods(new GoodsDetail(goods)));
		return goods;
	}
	
	public List<Goods> findList(Goods goods) {
		return super.findList(goods);
	}
	
	public Page<Goods> findPage(Page<Goods> page, Goods goods) {
		return super.findPage(page, goods);
	}
	
	@Transactional(readOnly = false)
	public void save(Goods goods) {
		super.save(goods);
		GoodsCategory goodsCategory=new GoodsCategory(goods);
		goodsCategoryDao.delete(goodsCategory);
		if(StringUtils.isNotBlank(goods.getCategoryId())){
			String cids[]=goods.getCategoryId().split(",");
			for (String c:cids
				 ) {
				GoodsCategory gCategory=new GoodsCategory(goods);
				gCategory.setCategory(new Scategory(c));
				goodsCategory.preInsert();
				goodsCategoryDao.insert(gCategory);
			}
		}

		/*for (GoodsCategory goodsCategory : goods.getGoodsCategoryList()){
			if (goodsCategory.getId() == null){
				continue;
			}
			if (GoodsCategory.DEL_FLAG_NORMAL.equals(goodsCategory.getDelFlag())){
				if (StringUtils.isBlank(goodsCategory.getId())){
					goodsCategory.setGoods(goods);
					goodsCategory.preInsert();
					goodsCategoryDao.insert(goodsCategory);
				}else{
					goodsCategory.preUpdate();
					goodsCategoryDao.update(goodsCategory);
				}
			}else{
				goodsCategoryDao.delete(goodsCategory);
			}
		}*/
		for (GoodsPic goodsPic : goods.getGoodsPicList()){
			if (goodsPic.getId() == null){
				continue;
			}
			if (GoodsPic.DEL_FLAG_NORMAL.equals(goodsPic.getDelFlag())){
				if (StringUtils.isBlank(goodsPic.getId())){
					goodsPic.setGoods(goods);
					goodsPic.preInsert();
					goodsPicDao.insert(goodsPic);
				}else{
					goodsPic.preUpdate();
					goodsPicDao.update(goodsPic);
				}
			}else{
				goodsPicDao.delete(goodsPic);
			}
		}
		for (GoodsSku goodsSku : goods.getGoodsSkuList()){
			if (goodsSku.getId() == null){
				continue;
			}
			if (GoodsSku.DEL_FLAG_NORMAL.equals(goodsSku.getDelFlag())){
				if (StringUtils.isBlank(goodsSku.getId())){
					goodsSku.setGoods(goods);
					goodsSku.preInsert();
					goodsSkuDao.insert(goodsSku);
				}else{
					goodsSku.preUpdate();
					goodsSkuDao.update(goodsSku);
				}
			}else{
				goodsSkuDao.delete(goodsSku);
			}
		}

		if (goods.getDetail()!=null){
			GoodsDetail g=goods.getDetail();
			g.setGoods(goods);
			goods.getDetail().setDetails(StringEscapeUtils.unescapeHtml4(g.getDetails()));
			goodsDetailDao.delete(g);
			goodsDetailDao.insert(g);
		}

	}
	
	@Transactional(readOnly = false)
	public void delete(Goods goods) {
		super.delete(goods);
		goodsCategoryDao.delete(new GoodsCategory(goods));
		goodsPicDao.delete(new GoodsPic(goods));
		goodsSkuDao.delete(new GoodsSku(goods));
	}

	public List<GoodsCategory> findGoodsCategoryList(GoodsCategory goodsCategory) {
		return goodsCategoryDao.findList(goodsCategory);
	}

	@Transactional(readOnly = false)
	public void savePass(Goods goods) {
		for (GoodsSku goodsSku : goods.getGoodsSkuList()) {
			goods.setMarketPrice(goodsSku.getMarketPrice());
			goods.setPrice(goodsSku.getPrice());
		}
		super.save(goods);
		GoodsCategory goodsCategory=new GoodsCategory(goods);
		goodsCategoryDao.delete(goodsCategory);
		if(StringUtils.isNotBlank(goods.getCategoryId())){
			String cids[]=goods.getCategoryId().split(",");
			for (String c:cids
			) {
				GoodsCategory gCategory=new GoodsCategory(goods);
				gCategory.setCategory(new Scategory(c));
				goodsCategory.preInsert();
				goodsCategoryDao.insert(gCategory);
			}
		}

		for (GoodsPic goodsPic : goods.getGoodsPicList()){
			if (goodsPic.getId() == null){
				continue;
			}
			if (GoodsPic.DEL_FLAG_NORMAL.equals(goodsPic.getDelFlag())){
				if (StringUtils.isBlank(goodsPic.getId())){
					goodsPic.setGoods(goods);
					goodsPic.preInsert();
					goodsPicDao.insert(goodsPic);
				}else{
					goodsPic.preUpdate();
					goodsPicDao.update(goodsPic);
				}
			}else{
				goodsPicDao.delete(goodsPic);
			}
		}
		for (GoodsSku goodsSku : goods.getGoodsSkuList()){
			goods.setMarketPrice(goodsSku.getMarketPrice());
			goods.setPrice(goodsSku.getPrice());
			goodsSku.setProfit(goodsSku.getPrice()-goodsSku.getSettlementPrice());
			goodsSku.setDiscount((goodsSku.getPrice()/goodsSku.getMarketPrice()));
			goodsSku.setSettlementDiscount((goodsSku.getSettlementPrice()/goodsSku.getPrice()));
			goodsSku.setProfitDiscount((goodsSku.getProfit()/goodsSku.getPrice()));
			if (GoodsSku.DEL_FLAG_NORMAL.equals(goodsSku.getDelFlag())){
				if (StringUtils.isBlank(goodsSku.getId())){
					goodsSku.setGoods(goods);
					goodsSku.preInsert();
					goodsSkuDao.insert(goodsSku);
				}else{
					goodsSku.preUpdate();
					goodsSkuDao.update(goodsSku);
				}
			}else{
				goodsSkuDao.delete(goodsSku);
			}
		}

		if (goods.getDetail()!=null){
			GoodsDetail g=goods.getDetail();
			g.setGoods(goods);
			goods.getDetail().setDetails(StringEscapeUtils.unescapeHtml4(g.getDetails()));
			goodsDetailDao.delete(g);
			goodsDetailDao.insert(g);
		}

	}

	public List<Goods> findByArtno(Goods g) {

		return dao.findByArtno(g);
	}
    @Transactional(readOnly = false)
    public void upordown(Goods goods) {
		dao.upordown(goods);
    }
	@Transactional(readOnly = false)
	public void passornot(Goods goods) {
		dao.passornot(goods);
	}
}