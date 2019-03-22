/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.jd.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.sgss.brand.entity.Brand;
import com.thinkgem.jeesite.modules.sgss.brand.service.BrandService;
import com.thinkgem.jeesite.modules.sgss.goods.entity.Goods;
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
import com.thinkgem.jeesite.modules.sgss.jd.entity.Jd;
import com.thinkgem.jeesite.modules.sgss.jd.service.JdService;

import java.util.List;

/**
 * 京东抓取Controller
 * @author martins
 * @version 2019-03-22
 */
@Controller
@RequestMapping(value = "${adminPath}/jd/jd")
public class JdController extends BaseController {

	@Autowired
	private JdService jdService;
	
	@ModelAttribute
	public Jd get(@RequestParam(required=false) String id) {
		Jd entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = jdService.get(id);
		}
		if (entity == null){
			entity = new Jd();
		}
		return entity;
	}
	
	@RequiresPermissions("jd:jd:view")
	@RequestMapping(value = {"list", ""})
	public String list(Jd jd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Jd> page = jdService.findPage(new Page<Jd>(request, response), jd); 
		model.addAttribute("page", page);
		return "sgss/jd/jdList";
	}

	@RequiresPermissions("jd:jd:view")
	@RequestMapping(value = "form")
	public String form(Jd jd, Model model) {
		model.addAttribute("jd", jd);
		return "sgss/jd/jdForm";
	}

	@RequiresPermissions("jd:jd:edit")
	@RequestMapping(value = "save")
	public String save(Jd jd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, jd)){
			return form(jd, model);
		}
		jd.setState("1");
		jdService.save(jd);
		Goods goods=jdService.pacth(jd);
		if(StringUtils.isBlank(goods.getSpec1())){
			goods.setSpec1("颜色");
		}
		if(StringUtils.isBlank(goods.getSpec2())){
			goods.setSpec2("尺码");
		}
		List<Brand> brands= brandService.findList(new Brand());
		model.addAttribute("brands", brands);
		model.addAttribute("goods", goods);
		addMessage(redirectAttributes, "抓取成功京东抓取成功");
		return "sgss/goods/goodsForm";
	}
	
	@RequiresPermissions("jd:jd:edit")
	@RequestMapping(value = "delete")
	public String delete(Jd jd, RedirectAttributes redirectAttributes) {
		jdService.delete(jd);
		addMessage(redirectAttributes, "删除京东抓取成功");
		return "redirect:"+Global.getAdminPath()+"/jd/jd/?repage";
	}
	@RequiresPermissions("jd:jd:edit")
	@RequestMapping(value = "pacth")
	public String pacth(Jd jd,   Model model, RedirectAttributes redirectAttributes) {
		Goods goods=jdService.pacth(jd);
		addMessage(redirectAttributes, "抓取成功京东抓取成功");
		if(StringUtils.isBlank(goods.getSpec1())){
			goods.setSpec1("颜色");
		}
		if(StringUtils.isBlank(goods.getSpec2())){
			goods.setSpec2("尺码");
		}
		List<Brand> brands= brandService.findList(new Brand());
		model.addAttribute("brands", brands);
		model.addAttribute("goods", goods);
		return "sgss/goods/goodsForm";
	}
	@Autowired
	private BrandService brandService;
}