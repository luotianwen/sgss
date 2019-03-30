/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.supplier.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.sgss.brand.service.BrandService;
import com.thinkgem.jeesite.modules.sgss.goods.service.GoodsService;
import com.thinkgem.jeesite.modules.sgss.order.service.OrderService;
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
import com.thinkgem.jeesite.modules.sgss.supplier.entity.Supplier;
import com.thinkgem.jeesite.modules.sgss.supplier.service.SupplierService;

/**
 * 供应商管理Controller
 * @author martins
 * @version 2019-03-26
 */
@Controller
@RequestMapping(value = "${adminPath}/supplier/supplier")
public class SupplierController extends BaseController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private SupplierService supplierService;
	
	@ModelAttribute
	public Supplier get(@RequestParam(required=false) String id) {
		Supplier entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = supplierService.get(id);
		}
		if (entity == null){
			entity = new Supplier();
		}
		return entity;
	}
	
	@RequiresPermissions("supplier:supplier:view")
	@RequestMapping(value = {"list", ""})
	public String list(Supplier supplier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Supplier> page = supplierService.findPage(new Page<Supplier>(request, response), supplier); 
		model.addAttribute("page", page);
		return "sgss/supplier/supplierList";
	}

	@RequiresPermissions("supplier:supplier:view")
	@RequestMapping(value = "form")
	public String form(Supplier supplier, Model model) {
		model.addAttribute("supplier", supplier);
		return "sgss/supplier/supplierForm";
	}

	@RequiresPermissions("supplier:supplier:edit")
	@RequestMapping(value = "save")
	public String save(Supplier supplier, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, supplier)){
			return form(supplier, model);
		}
		supplierService.save(supplier);
		addMessage(redirectAttributes, "保存供应商管理成功");
		return "redirect:"+Global.getAdminPath()+"/supplier/supplier/?repage";
	}
	
	@RequiresPermissions("supplier:supplier:edit")
	@RequestMapping(value = "delete")
	public String delete(Supplier supplier, RedirectAttributes redirectAttributes) {
		supplierService.delete(supplier);
		addMessage(redirectAttributes, "删除供应商管理成功");
		return "redirect:"+Global.getAdminPath()+"/supplier/supplier/?repage";
	}

}