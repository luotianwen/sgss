/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.carousel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sgss.carousel.entity.Carousel;
import com.thinkgem.jeesite.modules.sgss.carousel.dao.CarouselDao;

/**
 * 轮播图Service
 * @author martins
 * @version 2018-11-19
 */
@Service
@Transactional(readOnly = true)
public class CarouselService extends CrudService<CarouselDao, Carousel> {

	public Carousel get(String id) {
		return super.get(id);
	}
	
	public List<Carousel> findList(Carousel carousel) {
		return super.findList(carousel);
	}
	
	public Page<Carousel> findPage(Page<Carousel> page, Carousel carousel) {
		return super.findPage(page, carousel);
	}
	
	@Transactional(readOnly = false)
	public void save(Carousel carousel) {
		super.save(carousel);
	}
	
	@Transactional(readOnly = false)
	public void delete(Carousel carousel) {
		super.delete(carousel);
	}
	
}