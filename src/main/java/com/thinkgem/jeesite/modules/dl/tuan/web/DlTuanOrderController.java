/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dl.tuan.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dl.tuan.entity.DlTuanOrder;
import com.thinkgem.jeesite.modules.dl.tuan.service.DlTuanOrderService;

import java.util.List;

/**
 * 团购订单Controller
 * @author martins
 * @version 2019-07-11
 */
@Controller
@RequestMapping(value = "${adminPath}/tuan/dlTuanOrder")
public class DlTuanOrderController extends BaseController {

	@Autowired
	private DlTuanOrderService dlTuanOrderService;
	
	@ModelAttribute
	public DlTuanOrder get(@RequestParam(required=false) String id) {
		DlTuanOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dlTuanOrderService.get(id);
		}
		if (entity == null){
			entity = new DlTuanOrder();
		}
		return entity;
	}

	@RequiresPermissions("tuan:dlTuanOrder:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(DlTuanOrder dlTuanOrder, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "团购订单数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";

			if(null==dlTuanOrder){
				dlTuanOrder=new DlTuanOrder();
			}

			List<DlTuanOrder> list=dlTuanOrderService.findList(dlTuanOrder);
			new ExportExcel(null, DlTuanOrder.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/simpleorder/simpleOrder/list?repage";
	}
	@RequiresPermissions("tuan:dlTuanOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(DlTuanOrder dlTuanOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DlTuanOrder> page = dlTuanOrderService.findPage(new Page<DlTuanOrder>(request, response), dlTuanOrder); 
		model.addAttribute("page", page);
		return "dl/tuan/dlTuanOrderList";
	}

	@RequiresPermissions("tuan:dlTuanOrder:view")
	@RequestMapping(value = "form")
	public String form(DlTuanOrder dlTuanOrder, Model model) {
		model.addAttribute("dlTuanOrder", dlTuanOrder);
		return "dl/tuan/dlTuanOrderForm";
	}

	@RequiresPermissions("tuan:dlTuanOrder:edit")
	@RequestMapping(value = "save")
	public String save(DlTuanOrder dlTuanOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dlTuanOrder)){
			return form(dlTuanOrder, model);
		}
		dlTuanOrderService.save(dlTuanOrder);
		addMessage(redirectAttributes, "保存团购订单成功");
		return "redirect:"+Global.getAdminPath()+"/tuan/dlTuanOrder/?repage";
	}
	
	@RequiresPermissions("tuan:dlTuanOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(DlTuanOrder dlTuanOrder, RedirectAttributes redirectAttributes) {
		dlTuanOrderService.delete(dlTuanOrder);
		addMessage(redirectAttributes, "删除团购订单成功");
		return "redirect:"+Global.getAdminPath()+"/tuan/dlTuanOrder/?repage";
	}

}