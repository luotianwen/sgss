/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.coupon.web;

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
import com.thinkgem.jeesite.modules.sgss.coupon.entity.Coupon;
import com.thinkgem.jeesite.modules.sgss.coupon.service.CouponService;

/**
 * 优惠券Controller
 * @author martins
 * @version 2018-11-22
 */
@Controller
@RequestMapping(value = "${adminPath}/coupon/coupon")
public class CouponController extends BaseController {

	@Autowired
	private CouponService couponService;
	
	@ModelAttribute
	public Coupon get(@RequestParam(required=false) String id) {
		Coupon entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = couponService.get(id);
		}
		if (entity == null){
			entity = new Coupon();
		}
		return entity;
	}
	
	@RequiresPermissions("coupon:coupon:view")
	@RequestMapping(value = {"list", ""})
	public String list(Coupon coupon, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Coupon> page = couponService.findPage(new Page<Coupon>(request, response), coupon); 
		model.addAttribute("page", page);
		return "sgss/coupon/couponList";
	}

	@RequiresPermissions("coupon:coupon:view")
	@RequestMapping(value = "form")
	public String form(Coupon coupon, Model model) {
		model.addAttribute("coupon", coupon);
		return "sgss/coupon/couponForm";
	}

	@RequiresPermissions("coupon:coupon:edit")
	@RequestMapping(value = "save")
	public String save(Coupon coupon, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, coupon)){
			return form(coupon, model);
		}
		couponService.save(coupon);
		addMessage(redirectAttributes, "保存优惠券成功");
		return "redirect:"+Global.getAdminPath()+"/coupon/coupon/?repage";
	}
	
	@RequiresPermissions("coupon:coupon:edit")
	@RequestMapping(value = "delete")
	public String delete(Coupon coupon, RedirectAttributes redirectAttributes) {
		couponService.delete(coupon);
		addMessage(redirectAttributes, "删除优惠券成功");
		return "redirect:"+Global.getAdminPath()+"/coupon/coupon/?repage";
	}

}