/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.yzh.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sgss.yzh.entity.YzhProduct;

/**
 * 云中鹤商品管理DAO接口
 * @author martins
 * @version 2020-06-02
 */
@MyBatisDao
public interface YzhProductDao extends CrudDao<YzhProduct> {

    void saveSync(YzhProduct goods);
}