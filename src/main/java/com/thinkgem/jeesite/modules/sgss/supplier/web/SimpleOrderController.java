/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.supplier.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.utils.MyBeanUtils;
import com.thinkgem.jeesite.modules.sgss.goods.entity.Goods;
import com.thinkgem.jeesite.modules.sgss.goods.service.GoodsService;
import com.thinkgem.jeesite.modules.sgss.supplier.entity.SimpleOrderAfter;
import com.thinkgem.jeesite.modules.sgss.supplier.entity.Supplier;
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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sgss.supplier.entity.SimpleOrder;
import com.thinkgem.jeesite.modules.sgss.supplier.service.SimpleOrderService;

/**
 * 总订单管理Controller
 * @author martins
 * @version 2019-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/supplier/simpleOrder")
public class SimpleOrderController extends BaseController {
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private SimpleOrderService simpleOrderService;
	@Autowired
	private GoodsService goodsService;
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
	
	@RequiresPermissions("supplier:simpleOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(SimpleOrder simpleOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SimpleOrder> page = simpleOrderService.findPage(new Page<SimpleOrder>(request, response), simpleOrder); 
		model.addAttribute("page", page);
		model.addAttribute("suppliers", supplierService.findList(new Supplier()));
		return "sgss/supplier/simpleOrderList";
	}

	@RequiresPermissions("supplier:simpleOrder:view")
	@RequestMapping(value = "form")
	public String form(SimpleOrder simpleOrder, Model model) {
		model.addAttribute("simpleOrder", simpleOrder);
		model.addAttribute("suppliers", supplierService.findList(new Supplier()));
		return "sgss/supplier/simpleOrderForm";
	}
	@RequiresPermissions("supplier:simpleOrder:dispatch")
	@RequestMapping(value = "dispatch")
	public String dispatch(SimpleOrder simpleOrder, Model model) {
		model.addAttribute("simpleOrder", simpleOrder);
		Goods g=new Goods();
		g.setArtno(simpleOrder.getArticleno());
		g.setSpec1(simpleOrder.getSpec());
		model.addAttribute("goods", goodsService.findByArtno(g));
		/*model.addAttribute("suppliers", supplierService.findList(new Supplier()));*/
		return "sgss/supplier/simpleOrderDispatch";
	}
	@RequiresPermissions("supplier:simpleOrder:dispatch")
	@RequestMapping(value = "dispatchSave")
	public String dispatchSave(SimpleOrder simpleOrder, Model model, RedirectAttributes redirectAttributes) {
		simpleOrderService.dispatchSave(simpleOrder);
		addMessage(redirectAttributes, "总订单派单成功");
		return "redirect:"+Global.getAdminPath()+"/supplier/simpleOrder/?repage";
	}

	@RequiresPermissions("supplier:simpleOrder:edit")
	@RequestMapping(value = "save")
	public String save(SimpleOrder simpleOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, simpleOrder)){
			return form(simpleOrder, model);
		}
		simpleOrderService.save(simpleOrder);
		addMessage(redirectAttributes, "保存总订单管理成功");
		return "redirect:"+Global.getAdminPath()+"/supplier/simpleOrder/?repage";
	}
	
	@RequiresPermissions("supplier:simpleOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(SimpleOrder simpleOrder, RedirectAttributes redirectAttributes) {
		simpleOrderService.delete(simpleOrder);
		addMessage(redirectAttributes, "删除总订单管理成功");
		return "redirect:"+Global.getAdminPath()+"/supplier/simpleOrder/?repage";
	}
	@RequiresPermissions("supplier:simpleOrder:edit")
	@RequestMapping(value = "after")
	public String after(SimpleOrder simpleOrder, Model model) throws Exception {
		simpleOrder=simpleOrderService.get(simpleOrder.getId());
		SimpleOrderAfter aSimpleOrderAfter=new SimpleOrderAfter();
		MyBeanUtils.copyBean2Bean(aSimpleOrderAfter, simpleOrder);
		aSimpleOrderAfter.setId(null);
		aSimpleOrderAfter.setRemarks(null);
		model.addAttribute("simpleOrderAfter", aSimpleOrderAfter);
		return "sgss/supplier/simpleOrderAfterForm";
	}
	@RequiresPermissions("supplier:simpleOrder:edit")
	@RequestMapping(value = "fast")
	@ResponseBody
	public String fast(SimpleOrder order, Model model, RedirectAttributes redirectAttributes) throws Exception {

		simpleOrderService.fast(order);

		return "ok";

	}
}