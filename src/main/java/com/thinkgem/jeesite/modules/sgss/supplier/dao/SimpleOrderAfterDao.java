/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.supplier.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sgss.supplier.entity.SimpleOrderAfter;

/**
 * 总售后管理DAO接口
 * @author martins
 * @version 2019-03-27
 */
@MyBatisDao
public interface SimpleOrderAfterDao extends CrudDao<SimpleOrderAfter> {

    void backaddress(SimpleOrderAfter simpleOrderAfter);

    void backcourier(SimpleOrderAfter simpleOrderAfter);

    void courier(SimpleOrderAfter simpleOrderAfter);

    void updateCourierOk(SimpleOrderAfter simpleOrderAfter);
}