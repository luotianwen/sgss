/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.supplier.web;

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
import com.thinkgem.jeesite.modules.sgss.supplier.entity.SimpleOrderAfter;
import com.thinkgem.jeesite.modules.sgss.supplier.service.SimpleOrderAfterService;

/**
 * 总售后管理Controller
 * @author martins
 * @version 2019-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/supplier/simpleOrderAfter")
public class SimpleOrderAfterController extends BaseController {

	@Autowired
	private SimpleOrderAfterService simpleOrderAfterService;
	
	@ModelAttribute
	public SimpleOrderAfter get(@RequestParam(required=false) String id) {
		SimpleOrderAfter entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = simpleOrderAfterService.get(id);
		}
		if (entity == null){
			entity = new SimpleOrderAfter();
		}
		return entity;
	}
	
	@RequiresPermissions("supplier:simpleOrderAfter:view")
	@RequestMapping(value = {"list", ""})
	public String list(SimpleOrderAfter simpleOrderAfter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SimpleOrderAfter> page = simpleOrderAfterService.findPage(new Page<SimpleOrderAfter>(request, response), simpleOrderAfter); 
		model.addAttribute("page", page);
		return "sgss/supplier/simpleOrderAfterList";
	}

	@RequiresPermissions("supplier:simpleOrderAfter:view")
	@RequestMapping(value = "form")
	public String form(SimpleOrderAfter simpleOrderAfter, Model model) {
		model.addAttribute("simpleOrderAfter", simpleOrderAfter);
		return "sgss/supplier/simpleOrderAfterForm";
	}

	@RequiresPermissions("supplier:simpleOrderAfter:edit")
	@RequestMapping(value = "save")
	public String save(SimpleOrderAfter simpleOrderAfter, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, simpleOrderAfter)){
			return form(simpleOrderAfter, model);
		}
		simpleOrderAfterService.save(simpleOrderAfter);
		addMessage(redirectAttributes, "保存总售后管理成功");
		return "redirect:"+Global.getAdminPath()+"/supplier/simpleOrderAfter/?repage";
	}
	
	@RequiresPermissions("supplier:simpleOrderAfter:edit")
	@RequestMapping(value = "delete")
	public String delete(SimpleOrderAfter simpleOrderAfter, RedirectAttributes redirectAttributes) {
		simpleOrderAfterService.delete(simpleOrderAfter);
		addMessage(redirectAttributes, "删除总售后管理成功");
		return "redirect:"+Global.getAdminPath()+"/supplier/simpleOrderAfter/?repage";
	}
	@RequiresPermissions("supplier:simpleOrderAfter:edit")
	@RequestMapping(value = "backaddress")
	public String backaddress(SimpleOrderAfter simpleOrderAfter, Model model, RedirectAttributes redirectAttributes) {
		simpleOrderAfterService.backaddress(simpleOrderAfter);
		addMessage(redirectAttributes, "保存订单售后成功");
		return "redirect:"+Global.getAdminPath()+"/supplier/simpleOrderAfter/?repage";
	}
	@RequiresPermissions("supplier:simpleOrderAfter:edit")
	@RequestMapping(value = "backcourier")
	public String backcourier(SimpleOrderAfter simpleOrderAfter, Model model, RedirectAttributes redirectAttributes) {

		simpleOrderAfterService.backcourier(simpleOrderAfter);
		addMessage(redirectAttributes, "保存订单售后成功");
		return "redirect:"+Global.getAdminPath()+"/supplier/simpleOrderAfter/?repage";
	}
	@RequiresPermissions("supplier:simpleOrderAfter:edit")
	@RequestMapping(value = "courier")
	public String courier(SimpleOrderAfter simpleOrderAfter, RedirectAttributes redirectAttributes) {
		simpleOrderAfterService.courier(simpleOrderAfter);
		addMessage(redirectAttributes, "订单售后成功");
		return "redirect:"+Global.getAdminPath()+"/supplier/simpleOrderAfter/?repage";
	}
}