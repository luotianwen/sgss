/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.weixin.web;

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
import com.thinkgem.jeesite.modules.sgss.weixin.entity.Weixin;
import com.thinkgem.jeesite.modules.sgss.weixin.service.WeixinService;

/**
 * 公众号配置Controller
 * @author martins
 * @version 2019-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/weixin")
public class WeixinController extends BaseController {

	@Autowired
	private WeixinService weixinService;
	
	@ModelAttribute
	public Weixin get(@RequestParam(required=false) String id) {
		Weixin entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinService.get(id);
		}
		if (entity == null){
			entity = new Weixin();
		}
		return entity;
	}
	
	@RequiresPermissions("weixin:weixin:view")
	@RequestMapping(value = {"list", ""})
	public String list(Weixin weixin, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Weixin> page = weixinService.findPage(new Page<Weixin>(request, response), weixin); 
		model.addAttribute("page", page);
		return "sgss/weixin/weixinList";
	}

	@RequiresPermissions("weixin:weixin:view")
	@RequestMapping(value = "form")
	public String form(Weixin weixin, Model model) {
		model.addAttribute("weixin", weixin);
		return "sgss/weixin/weixinForm";
	}

	@RequiresPermissions("weixin:weixin:edit")
	@RequestMapping(value = "save")
	public String save(Weixin weixin, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixin)){
			return form(weixin, model);
		}
		weixinService.save(weixin);
		addMessage(redirectAttributes, "保存公众号配置成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixin/?repage";
	}
	
	@RequiresPermissions("weixin:weixin:edit")
	@RequestMapping(value = "delete")
	public String delete(Weixin weixin, RedirectAttributes redirectAttributes) {
		weixinService.delete(weixin);
		addMessage(redirectAttributes, "删除公众号配置成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixin/?repage";
	}

}