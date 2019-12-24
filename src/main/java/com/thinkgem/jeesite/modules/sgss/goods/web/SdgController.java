/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.goods.web;

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
import com.thinkgem.jeesite.modules.sgss.goods.entity.Sdg;
import com.thinkgem.jeesite.modules.sgss.goods.service.SdgService;

/**
 * 动力折扣Controller
 * @author 动力折扣
 * @version 2019-12-24
 */
@Controller
@RequestMapping(value = "${adminPath}/goods/sdg")
public class SdgController extends BaseController {

	@Autowired
	private SdgService sdgService;
	
	@ModelAttribute
	public Sdg get(@RequestParam(required=false) String id) {
		Sdg entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sdgService.get(id);
		}
		if (entity == null){
			entity = new Sdg();
		}
		return entity;
	}
	
	@RequiresPermissions("goods:sdg:view")
	@RequestMapping(value = {"list", ""})
	public String list(Sdg sdg, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Sdg> page = sdgService.findPage(new Page<Sdg>(request, response), sdg); 
		model.addAttribute("page", page);
		return "sgss/goods/sdgList";
	}

	@RequiresPermissions("goods:sdg:view")
	@RequestMapping(value = "form")
	public String form(Sdg sdg, Model model) {
		model.addAttribute("sdg", sdg);
		return "sgss/goods/sdgForm";
	}

	@RequiresPermissions("goods:sdg:edit")
	@RequestMapping(value = "save")
	public String save(Sdg sdg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sdg)){
			return form(sdg, model);
		}
		sdgService.save(sdg);
		addMessage(redirectAttributes, "保存动力折扣成功");
		return "redirect:"+Global.getAdminPath()+"/goods/sdg/?repage";
	}
	
	@RequiresPermissions("goods:sdg:edit")
	@RequestMapping(value = "delete")
	public String delete(Sdg sdg, RedirectAttributes redirectAttributes) {
		sdgService.delete(sdg);
		addMessage(redirectAttributes, "删除动力折扣成功");
		return "redirect:"+Global.getAdminPath()+"/goods/sdg/?repage";
	}

}