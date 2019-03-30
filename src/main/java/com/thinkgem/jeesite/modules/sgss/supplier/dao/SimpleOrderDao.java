/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.supplier.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sgss.supplier.entity.SimpleOrder;

/**
 * 总订单管理DAO接口
 * @author martins
 * @version 2019-03-27
 */
@MyBatisDao
public interface SimpleOrderDao extends CrudDao<SimpleOrder> {

    void dispatchSave(SimpleOrder simpleOrder);

    void noSku(SimpleOrder simpleOrder);

    void fast(SimpleOrder simpleOrder);

    void aftersave(SimpleOrder simpleOrder);

    void aftersaveok(SimpleOrder simpleOrder);

    void aftersavepass(SimpleOrder simpleOrder);
}