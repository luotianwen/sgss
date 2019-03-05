/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.express.web;

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
import com.thinkgem.jeesite.modules.sgss.express.entity.Express;
import com.thinkgem.jeesite.modules.sgss.express.service.ExpressService;

/**
 * 快递配置Controller
 * @author martins
 * @version 2019-03-01
 */
@Controller
@RequestMapping(value = "${adminPath}/express/express")
public class ExpressController extends BaseController {

	@Autowired
	private ExpressService expressService;
	
	@ModelAttribute
	public Express get(@RequestParam(required=false) String id) {
		Express entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = expressService.get(id);
		}
		if (entity == null){
			entity = new Express();
		}
		return entity;
	}
	
	@RequiresPermissions("express:express:view")
	@RequestMapping(value = {"list", ""})
	public String list(Express express, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Express> page = expressService.findPage(new Page<Express>(request, response), express); 
		model.addAttribute("page", page);
		return "sgss/express/expressList";
	}

	@RequiresPermissions("express:express:view")
	@RequestMapping(value = "form")
	public String form(Express express, Model model) {
		model.addAttribute("express", express);
		return "sgss/express/expressForm";
	}

	@RequiresPermissions("express:express:edit")
	@RequestMapping(value = "save")
	public String save(Express express, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, express)){
			return form(express, model);
		}
		expressService.save(express);
		addMessage(redirectAttributes, "保存快递配置成功");
		return "redirect:"+Global.getAdminPath()+"/express/express/?repage";
	}
	
	@RequiresPermissions("express:express:edit")
	@RequestMapping(value = "delete")
	public String delete(Express express, RedirectAttributes redirectAttributes) {
		expressService.delete(express);
		addMessage(redirectAttributes, "删除快递配置成功");
		return "redirect:"+Global.getAdminPath()+"/express/express/?repage";
	}

}