/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.goods.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sgss.goods.entity.Goods;
import com.thinkgem.jeesite.modules.sgss.goods.entity.GoodsDetail;

/**
 * 商品详情DAO接口
 * @author martins
 * @version 2018-11-19
 */
@MyBatisDao
public interface GoodsDetailDao extends CrudDao<GoodsDetail> {

    GoodsDetail getDetailByGoods(GoodsDetail goodsDetail);
}