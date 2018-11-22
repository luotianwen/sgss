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
import com.thinkgem.jeesite.modules.sgss.order.entity.OrderAfterSales;
import com.thinkgem.jeesite.modules.sgss.order.service.OrderAfterSalesService;

/**
 * 订单售后Controller
 * @author martins
 * @version 2018-11-22
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orderAfterSales")
public class OrderAfterSalesController extends BaseController {

	@Autowired
	private OrderAfterSalesService orderAfterSalesService;
	
	@ModelAttribute
	public OrderAfterSales get(@RequestParam(required=false) String id) {
		OrderAfterSales entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderAfterSalesService.get(id);
		}
		if (entity == null){
			entity = new OrderAfterSales();
		}
		return entity;
	}
	
	@RequiresPermissions("order:orderAfterSales:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrderAfterSales orderAfterSales, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrderAfterSales> page = orderAfterSalesService.findPage(new Page<OrderAfterSales>(request, response), orderAfterSales); 
		model.addAttribute("page", page);
		return "sgss/order/orderAfterSalesList";
	}

	@RequiresPermissions("order:orderAfterSales:view")
	@RequestMapping(value = "form")
	public String form(OrderAfterSales orderAfterSales, Model model) {
		model.addAttribute("orderAfterSales", orderAfterSales);
		return "sgss/order/orderAfterSalesForm";
	}

	@RequiresPermissions("order:orderAfterSales:edit")
	@RequestMapping(value = "save")
	public String save(OrderAfterSales orderAfterSales, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orderAfterSales)){
			return form(orderAfterSales, model);
		}
		orderAfterSalesService.save(orderAfterSales);
		addMessage(redirectAttributes, "保存订单售后成功");
		return "redirect:"+Global.getAdminPath()+"/order/orderAfterSales/?repage";
	}
	
	@RequiresPermissions("order:orderAfterSales:edit")
	@RequestMapping(value = "delete")
	public String delete(OrderAfterSales orderAfterSales, RedirectAttributes redirectAttributes) {
		orderAfterSalesService.delete(orderAfterSales);
		addMessage(redirectAttributes, "删除订单售后成功");
		return "redirect:"+Global.getAdminPath()+"/order/orderAfterSales/?repage";
	}

}