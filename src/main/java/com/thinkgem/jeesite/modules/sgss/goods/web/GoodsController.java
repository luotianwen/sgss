/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.goods.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.sgss.brand.entity.Brand;
import com.thinkgem.jeesite.modules.sgss.brand.service.BrandService;
import com.thinkgem.jeesite.modules.sgss.goods.entity.GoodsCategory;
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
import com.thinkgem.jeesite.modules.sgss.goods.entity.Goods;
import com.thinkgem.jeesite.modules.sgss.goods.service.GoodsService;

import java.util.List;

/**
 * 商品管理Controller
 * @author martins
 * @version 2018-11-19
 */
@Controller
@RequestMapping(value = "${adminPath}/goods/goods")
public class GoodsController extends BaseController {

	@Autowired
	private GoodsService goodsService;
	@Autowired
	private BrandService brandService;
	@ModelAttribute
	public Goods get(@RequestParam(required=false) String id) {
		Goods entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = goodsService.get(id);
		}
		if (entity == null){
			entity = new Goods();
		}
		return entity;
	}
	
	@RequiresPermissions("goods:goods:view")
	@RequestMapping(value = {"list", ""})
	public String list(Goods goods, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Goods> page = goodsService.findPage(new Page<Goods>(request, response), goods);
		StringBuffer categoryName=new StringBuffer();
		for (Goods g:page.getList()
			 ) {
			List<GoodsCategory> goodsCategoryList=goodsService.findGoodsCategoryList(new GoodsCategory(g));
		    categoryName.setLength(0);
			for (GoodsCategory c:goodsCategoryList
			) {
				if(null!=c.getCategory()) {
					categoryName.append(c.getCategory().getName()).append(",");
				}
			}
			g.setCategoryName(categoryName.toString());
		}
		model.addAttribute("page", page);
		return "sgss/goods/goodsList";
	}

	@RequiresPermissions("goods:goods:view")
	@RequestMapping(value = "form")
	public String form(Goods goods, Model model) {
		if(StringUtils.isBlank(goods.getSpec1())){
			goods.setSpec1("颜色");
		}
		if(StringUtils.isBlank(goods.getSpec2())){
			goods.setSpec2("尺码");
		}
		List<Brand> brands= brandService.findList(new Brand());
		model.addAttribute("brands", brands);
		model.addAttribute("goods", goods);
		return "sgss/goods/goodsForm";
	}

	@RequiresPermissions("goods:goods:edit")
	@RequestMapping(value = "save")
	public String save(Goods goods, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, goods)){
			return form(goods, model);
		}
		goodsService.save(goods);
		addMessage(redirectAttributes, "保存商品管理成功");
		return "redirect:"+Global.getAdminPath()+"/goods/goods/?repage";
	}
	
	@RequiresPermissions("goods:goods:edit")
	@RequestMapping(value = "delete")
	public String delete(Goods goods, RedirectAttributes redirectAttributes) {
		goodsService.delete(goods);
		addMessage(redirectAttributes, "删除商品管理成功");
		return "redirect:"+Global.getAdminPath()+"/goods/goods/?repage";
	}

}