/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.agent.web;

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
import com.thinkgem.jeesite.modules.sgss.agent.entity.Discount;
import com.thinkgem.jeesite.modules.sgss.agent.service.DiscountService;

/**
 * 折扣Controller
 * @author martins
 * @version 2019-03-06
 */
@Controller
@RequestMapping(value = "${adminPath}/agent/discount")
public class DiscountController extends BaseController {

	@Autowired
	private DiscountService discountService;
	
	@ModelAttribute
	public Discount get(@RequestParam(required=false) String id) {
		Discount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = discountService.get(id);
		}
		if (entity == null){
			entity = new Discount();
		}
		return entity;
	}
	
	@RequiresPermissions("agent:discount:view")
	@RequestMapping(value = {"list", ""})
	public String list(Discount discount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Discount> page = discountService.findPage(new Page<Discount>(request, response), discount); 
		model.addAttribute("page", page);
		return "sgss/agent/discountList";
	}

	@RequiresPermissions("agent:discount:view")
	@RequestMapping(value = "form")
	public String form(Discount discount, Model model) {
		model.addAttribute("discount", discount);
		return "sgss/agent/discountForm";
	}

	@RequiresPermissions("agent:discount:edit")
	@RequestMapping(value = "save")
	public String save(Discount discount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, discount)){
			return form(discount, model);
		}
		discountService.save(discount);
		addMessage(redirectAttributes, "保存折扣成功");
		return "redirect:"+Global.getAdminPath()+"/agent/discount/?repage";
	}
	
	@RequiresPermissions("agent:discount:edit")
	@RequestMapping(value = "delete")
	public String delete(Discount discount, RedirectAttributes redirectAttributes) {
		discountService.delete(discount);
		addMessage(redirectAttributes, "删除折扣成功");
		return "redirect:"+Global.getAdminPath()+"/agent/discount/?repage";
	}

}