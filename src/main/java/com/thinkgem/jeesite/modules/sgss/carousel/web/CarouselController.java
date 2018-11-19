/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.carousel.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sgss.carousel.entity.Carousel;
import com.thinkgem.jeesite.modules.sgss.carousel.service.CarouselService;

/**
 * 轮播图Controller
 * @author martins
 * @version 2018-11-19
 */
@Controller
@RequestMapping(value = "${adminPath}/carousel/carousel")
public class CarouselController extends BaseController {

	@Autowired
	private CarouselService carouselService;
	
	@ModelAttribute
	public Carousel get(@RequestParam(required=false) String id) {
		Carousel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = carouselService.get(id);
		}
		if (entity == null){
			entity = new Carousel();
		}
		return entity;
	}
	
	@RequiresPermissions("carousel:carousel:view")
	@RequestMapping(value = {"list", ""})
	public String list(Carousel carousel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Carousel> page = carouselService.findPage(new Page<Carousel>(request, response), carousel); 
		model.addAttribute("page", page);
		return "sgss/carousel/carouselList";
	}

	@RequiresPermissions("carousel:carousel:view")
	@RequestMapping(value = "form")
	public String form(Carousel carousel, Model model) {
		model.addAttribute("carousel", carousel);
		return "sgss/carousel/carouselForm";
	}

	@RequiresPermissions("carousel:carousel:edit")
	@RequestMapping(value = "save")
	public String save(Carousel carousel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, carousel)){
			return form(carousel, model);
		}
		carouselService.save(carousel);
		addMessage(redirectAttributes, "保存轮播图成功");
		return "redirect:"+Global.getAdminPath()+"/carousel/carousel/?repage";
	}
	
	@RequiresPermissions("carousel:carousel:edit")
	@RequestMapping(value = "delete")
	public String delete(Carousel carousel, RedirectAttributes redirectAttributes) {
		carouselService.delete(carousel);
		addMessage(redirectAttributes, "删除轮播图成功");
		return "redirect:"+Global.getAdminPath()+"/carousel/carousel/?repage";
	}

}