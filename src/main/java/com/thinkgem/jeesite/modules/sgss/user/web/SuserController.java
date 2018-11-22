/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.user.web;

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
import com.thinkgem.jeesite.modules.sgss.user.entity.Suser;
import com.thinkgem.jeesite.modules.sgss.user.service.SuserService;

/**
 * 用户管理Controller
 * @author martins
 * @version 2018-11-22
 */
@Controller
@RequestMapping(value = "${adminPath}/user/suser")
public class SuserController extends BaseController {

	@Autowired
	private SuserService suserService;
	
	@ModelAttribute
	public Suser get(@RequestParam(required=false) String id) {
		Suser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = suserService.get(id);
		}
		if (entity == null){
			entity = new Suser();
		}
		return entity;
	}
	
	@RequiresPermissions("user:suser:view")
	@RequestMapping(value = {"list", ""})
	public String list(Suser suser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Suser> page = suserService.findPage(new Page<Suser>(request, response), suser); 
		model.addAttribute("page", page);
		return "sgss/user/suserList";
	}

	@RequiresPermissions("user:suser:view")
	@RequestMapping(value = "form")
	public String form(Suser suser, Model model) {
		model.addAttribute("suser", suser);
		return "sgss/user/suserForm";
	}

	@RequiresPermissions("user:suser:edit")
	@RequestMapping(value = "save")
	public String save(Suser suser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, suser)){
			return form(suser, model);
		}
		suserService.save(suser);
		addMessage(redirectAttributes, "保存用户管理成功");
		return "redirect:"+Global.getAdminPath()+"/user/suser/?repage";
	}
	
	@RequiresPermissions("user:suser:edit")
	@RequestMapping(value = "delete")
	public String delete(Suser suser, RedirectAttributes redirectAttributes) {
		suserService.delete(suser);
		addMessage(redirectAttributes, "删除用户管理成功");
		return "redirect:"+Global.getAdminPath()+"/user/suser/?repage";
	}

}