/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.order.web;

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
import com.thinkgem.jeesite.modules.sgss.order.entity.OrderAfterSalesLog;
import com.thinkgem.jeesite.modules.sgss.order.service.OrderAfterSalesLogService;

/**
 * 售后退款日志Controller
 * @author martins
 * @version 2019-02-28
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orderAfterSalesLog")
public class OrderAfterSalesLogController extends BaseController {

	@Autowired
	private OrderAfterSalesLogService orderAfterSalesLogService;
	
	@ModelAttribute
	public OrderAfterSalesLog get(@RequestParam(required=false) String id) {
		OrderAfterSalesLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderAfterSalesLogService.get(id);
		}
		if (entity == null){
			entity = new OrderAfterSalesLog();
		}
		return entity;
	}
	
	@RequiresPermissions("order:orderAfterSalesLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrderAfterSalesLog orderAfterSalesLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrderAfterSalesLog> page = orderAfterSalesLogService.findPage(new Page<OrderAfterSalesLog>(request, response), orderAfterSalesLog); 
		model.addAttribute("page", page);
		return "sgss/order/orderAfterSalesLogList";
	}

	@RequiresPermissions("order:orderAfterSalesLog:view")
	@RequestMapping(value = "form")
	public String form(OrderAfterSalesLog orderAfterSalesLog, Model model) {
		model.addAttribute("orderAfterSalesLog", orderAfterSalesLog);
		return "sgss/order/orderAfterSalesLogForm";
	}

	@RequiresPermissions("order:orderAfterSalesLog:edit")
	@RequestMapping(value = "save")
	public String save(OrderAfterSalesLog orderAfterSalesLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orderAfterSalesLog)){
			return form(orderAfterSalesLog, model);
		}
		orderAfterSalesLogService.save(orderAfterSalesLog);
		addMessage(redirectAttributes, "保存售后退款日志成功");
		return "redirect:"+Global.getAdminPath()+"/order/orderAfterSalesLog/?repage";
	}
	
	@RequiresPermissions("order:orderAfterSalesLog:edit")
	@RequestMapping(value = "delete")
	public String delete(OrderAfterSalesLog orderAfterSalesLog, RedirectAttributes redirectAttributes) {
		orderAfterSalesLogService.delete(orderAfterSalesLog);
		addMessage(redirectAttributes, "删除售后退款日志成功");
		return "redirect:"+Global.getAdminPath()+"/order/orderAfterSalesLog/?repage";
	}

}