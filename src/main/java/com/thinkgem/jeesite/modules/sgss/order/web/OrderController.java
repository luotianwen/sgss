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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sgss.order.entity.Order;
import com.thinkgem.jeesite.modules.sgss.order.service.OrderService;

/**
 * 订单管理Controller
 * @author martins
 * @version 2018-11-22
 */
@Controller
@RequestMapping(value = "${adminPath}/order/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderService orderService;
	
	@ModelAttribute
	public Order get(@RequestParam(required=false) String id) {
		Order entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderService.get(id);
		}
		if (entity == null){
			entity = new Order();
		}
		return entity;
	}
	
	@RequiresPermissions("order:order:view")
	@RequestMapping(value = {"list", ""})
	public String list(Order order, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Order> page = orderService.findPage(new Page<Order>(request, response), order); 
		model.addAttribute("page", page);
		return "sgss/order/orderList";
	}

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "form")
	public String form(Order order, Model model) {
		model.addAttribute("order", order);
		return "sgss/order/orderForm";
	}
	@RequiresPermissions("order:order:edit")
	@RequestMapping(value = "fast")
	@ResponseBody
	public String fast(Order order, Model model, RedirectAttributes redirectAttributes) throws Exception {
		if (!beanValidator(model, order)){
			return "error";
		}
		orderService.fast(order);

		return "ok";
		//addMessage(redirectAttributes, "保存下单管理成功");
		//return "redirect:"+Global.getAdminPath()+"/simpleorder/simpleOrder/?repage";
	}
	@RequiresPermissions("order:order:edit")
	@RequestMapping(value = "save")
	public String save(Order order, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, order)){
			return form(order, model);
		}
		orderService.save(order);
		addMessage(redirectAttributes, "保存订单管理成功");
		return "redirect:"+Global.getAdminPath()+"/order/order/?repage";
	}
    @RequiresPermissions("order:order:edit")
    @RequestMapping(value = "after")
    public String after(Order order, Model model, RedirectAttributes redirectAttributes) {
        orderService.after(order);
        addMessage(redirectAttributes, "申请订单售后成功");
        return "redirect:"+Global.getAdminPath()+"/order/order/?repage";
    }

	@RequiresPermissions("order:order:edit")
	@RequestMapping(value = "delete")
	public String delete(Order order, RedirectAttributes redirectAttributes) {
		orderService.delete(order);
		addMessage(redirectAttributes, "删除订单管理成功");
		return "redirect:"+Global.getAdminPath()+"/order/order/?repage";
	}

}