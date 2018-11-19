/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.carousel.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sgss.carousel.entity.Carousel;

/**
 * 轮播图DAO接口
 * @author martins
 * @version 2018-11-19
 */
@MyBatisDao
public interface CarouselDao extends CrudDao<Carousel> {
	
}