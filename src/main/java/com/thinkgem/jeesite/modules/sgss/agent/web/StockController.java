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
import com.thinkgem.jeesite.modules.sgss.agent.entity.Stock;
import com.thinkgem.jeesite.modules.sgss.agent.service.StockService;

/**
 * 库存Controller
 * @author martins
 * @version 2019-03-06
 */
@Controller
@RequestMapping(value = "${adminPath}/agent/stock")
public class StockController extends BaseController {

	@Autowired
	private StockService stockService;
	
	@ModelAttribute
	public Stock get(@RequestParam(required=false) String id) {
		Stock entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = stockService.get(id);
		}
		if (entity == null){
			entity = new Stock();
		}
		return entity;
	}
	
	@RequiresPermissions("agent:stock:view")
	@RequestMapping(value = {"list", ""})
	public String list(Stock stock, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Stock> page = stockService.findPage(new Page<Stock>(request, response), stock); 
		model.addAttribute("page", page);
		return "sgss/agent/stockList";
	}

	@RequiresPermissions("agent:stock:view")
	@RequestMapping(value = "form")
	public String form(Stock stock, Model model) {
		model.addAttribute("stock", stock);
		return "sgss/agent/stockForm";
	}

	@RequiresPermissions("agent:stock:edit")
	@RequestMapping(value = "save")
	public String save(Stock stock, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, stock)){
			return form(stock, model);
		}
		stockService.save(stock);
		addMessage(redirectAttributes, "保存库存成功");
		return "redirect:"+Global.getAdminPath()+"/agent/stock/?repage";
	}
	
	@RequiresPermissions("agent:stock:edit")
	@RequestMapping(value = "delete")
	public String delete(Stock stock, RedirectAttributes redirectAttributes) {
		stockService.delete(stock);
		addMessage(redirectAttributes, "删除库存成功");
		return "redirect:"+Global.getAdminPath()+"/agent/stock/?repage";
	}

}