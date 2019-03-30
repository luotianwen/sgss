/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.supplier.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sgss.order.entity.Order;
import com.thinkgem.jeesite.modules.sgss.order.service.OrderService;
import com.thinkgem.jeesite.modules.sgss.supplier.entity.SimpleOrder;
import com.thinkgem.jeesite.modules.sgss.supplier.entity.SimpleOrderAfter;
import com.thinkgem.jeesite.modules.sgss.supplier.entity.Supplier;
import com.thinkgem.jeesite.modules.sgss.supplier.service.SimpleOrderAfterService;
import com.thinkgem.jeesite.modules.sgss.supplier.service.SimpleOrderService;
import com.thinkgem.jeesite.modules.sgss.supplier.service.SupplierService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 订单管理Controller
 * @author martins
 * @version 2018-11-22
 */
@Controller
@RequestMapping(value = "${adminPath}/order/supplierorder")
public class SupplierOrderController extends BaseController {

	@Autowired
	private SupplierService supplierService;
	@Autowired
	private SimpleOrderService simpleOrderService;
	@ModelAttribute
	public SimpleOrder get(@RequestParam(required=false) String id) {
		SimpleOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = simpleOrderService.get(id);
		}
		if (entity == null){
			entity = new SimpleOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("order:supplierorder:view")
	@RequestMapping(value = {"list", ""})
	public String list(SimpleOrder order, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		User user = UserUtils.getUser();
		 Supplier supplier = supplierService.getUserId(user.getId());
		if(null==supplier){
			throw new Exception("不是供应商");
		}
		order.setSupplier(supplier);
		Page<SimpleOrder> page = simpleOrderService.findPage(new Page<SimpleOrder>(request, response), order);
		model.addAttribute("page", page);
		return "sgss/supplier/supplierorderList";
	}


	@RequiresPermissions("order:supplierorder:view")
	@RequestMapping(value = "form")
	public String form(SimpleOrder order, Model model) {

		/*User user = UserUtils.getUser();
		Supplier supplier = supplierService.getUserId(user.getId());
		order.setSupplier(supplier);*/

		model.addAttribute("order", order);
		return "sgss/supplier/supplierorderForm";
	}

	@RequiresPermissions("order:supplierorder:edit")
	@RequestMapping(value = "noSku")
	public String noSku(SimpleOrder simpleOrder, RedirectAttributes redirectAttributes) {
		simpleOrderService.noSku(simpleOrder);
		addMessage(redirectAttributes, "删除总订单管理成功");
		return "redirect:"+Global.getAdminPath()+"/order/supplierorder/?repage";
	}
	@RequiresPermissions("order:supplierorder:edit")
	@RequestMapping(value = "fast")
	@ResponseBody
	public String fast(SimpleOrder order, Model model, RedirectAttributes redirectAttributes) throws Exception {
		User user = UserUtils.getUser();
		Supplier supplier = supplierService.getUserId(user.getId());
		if(null==supplier){
			throw new Exception("不是供应商");
		}

		 simpleOrderService.fast(order);

		return "ok";
		//addMessage(redirectAttributes, "保存下单管理成功");
		//return "redirect:"+Global.getAdminPath()+"/simpleorder/simpleOrder/?repage";
	}
	@RequiresPermissions("order:supplierorder:edit")
	@RequestMapping(value = "save")
	public String save(SimpleOrder order, Model model, RedirectAttributes redirectAttributes) throws Exception {
		if (!beanValidator(model, order)){
			return form(order, model);
		}
		User user = UserUtils.getUser();
		 Supplier supplier = supplierService.getUserId(user.getId());
		if(null==supplier){
			throw new Exception("不是供应商");
		}
		simpleOrderService.save(order);
		addMessage(redirectAttributes, "保存订单管理成功");
		return "redirect:"+Global.getAdminPath()+"/order/supplierorder/?repage";
	}
	@Autowired
	private SimpleOrderAfterService simpleOrderAfterService;
	@RequiresPermissions("order:supplierorder:view")
	@RequestMapping(value ="afterlist")
	public String afterlist(SimpleOrderAfter simpleOrderAfter, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		User user = UserUtils.getUser();
		Supplier supplier = supplierService.getUserId(user.getId());
		if(null==supplier){
			throw new Exception("不是供应商");
		}
		simpleOrderAfter.setSupplier(supplier);
		Page<SimpleOrderAfter> page = simpleOrderAfterService.findPage(new Page<SimpleOrderAfter>(request, response), simpleOrderAfter);
		model.addAttribute("page", page);
		return "sgss/supplier/supplierorderAfterList";
	}


	@RequiresPermissions("order:supplierorder:edit")
	@RequestMapping(value = "backaddress")
	public String backaddress(SimpleOrderAfter simpleOrderAfter, Model model, RedirectAttributes redirectAttributes) throws Exception {
		User user = UserUtils.getUser();
		Supplier supplier = supplierService.getUserId(user.getId());
		if(null==supplier){
			throw new Exception("不是供应商");
		}
		simpleOrderAfter.setSupplier(supplier);
		simpleOrderAfterService.backaddress(simpleOrderAfter);
		addMessage(redirectAttributes, "保存订单售后成功");
		return "redirect:"+Global.getAdminPath()+"/order/supplierorder/afterlist?repage";
	}
	@RequiresPermissions("order:supplierorder:edit")
	@RequestMapping(value = "backcourier")
	public String backcourier(SimpleOrderAfter simpleOrderAfter, Model model, RedirectAttributes redirectAttributes) throws Exception {
		User user = UserUtils.getUser();
		Supplier supplier = supplierService.getUserId(user.getId());
		if(null==supplier){
			throw new Exception("不是供应商");
		}
		simpleOrderAfter.setSupplier(supplier);
		simpleOrderAfterService.backcourier(simpleOrderAfter);
		addMessage(redirectAttributes, "保存订单售后成功");
		return "redirect:"+Global.getAdminPath()+"/order/supplierorder/afterlist?repage";
	}
/*
	@RequiresPermissions("order:supplierorder:edit")
	@RequestMapping(value = "courier")
	public String courier(SimpleOrderAfter simpleOrderAfter, RedirectAttributes redirectAttributes) {
		simpleOrderAfterService.courier(simpleOrderAfter);
		addMessage(redirectAttributes, "订单售后成功");
		return "redirect:"+Global.getAdminPath()+"/order/supplierorder/afterlist?repage";
	}*/
}