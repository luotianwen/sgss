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
import com.thinkgem.jeesite.modules.sgss.goods.entity.GoodsDetail;
import com.thinkgem.jeesite.modules.sgss.goods.service.GoodsDetailService;

/**
 * 商品详情Controller
 * @author martins
 * @version 2018-11-19
 */
@Controller
@RequestMapping(value = "${adminPath}/goods/goodsDetail")
public class GoodsDetailController extends BaseController {

	@Autowired
	private GoodsDetailService goodsDetailService;
	
	@ModelAttribute
	public GoodsDetail get(@RequestParam(required=false) String id) {
		GoodsDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = goodsDetailService.get(id);
		}
		if (entity == null){
			entity = new GoodsDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("goods:goodsDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(GoodsDetail goodsDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GoodsDetail> page = goodsDetailService.findPage(new Page<GoodsDetail>(request, response), goodsDetail); 
		model.addAttribute("page", page);
		return "sgss/goods/goodsDetailList";
	}

	@RequiresPermissions("goods:goodsDetail:view")
	@RequestMapping(value = "form")
	public String form(GoodsDetail goodsDetail, Model model) {
		model.addAttribute("goodsDetail", goodsDetail);
		return "sgss/goods/goodsDetailForm";
	}

	@RequiresPermissions("goods:goodsDetail:edit")
	@RequestMapping(value = "save")
	public String save(GoodsDetail goodsDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, goodsDetail)){
			return form(goodsDetail, model);
		}
		goodsDetailService.save(goodsDetail);
		addMessage(redirectAttributes, "保存商品详情成功");
		return "redirect:"+Global.getAdminPath()+"/goods/goodsDetail/?repage";
	}
	
	@RequiresPermissions("goods:goodsDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(GoodsDetail goodsDetail, RedirectAttributes redirectAttributes) {
		goodsDetailService.delete(goodsDetail);
		addMessage(redirectAttributes, "删除商品详情成功");
		return "redirect:"+Global.getAdminPath()+"/goods/goodsDetail/?repage";
	}

}