/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.goods.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sgss.goods.entity.Goods;

import java.util.List;

/**
 * 商品管理DAO接口
 * @author martins
 * @version 2018-11-19
 */
@MyBatisDao
public interface GoodsDao extends CrudDao<Goods> {

    List<Goods> findByArtno(Goods g);
   void upordown(Goods g);
    void  passornot(Goods g);

    void saveSync(Goods goods);

    void saveSyncby(Goods goods);
}