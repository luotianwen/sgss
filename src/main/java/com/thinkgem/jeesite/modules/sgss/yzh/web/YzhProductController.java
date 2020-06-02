/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.yzh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.modules.sgss.goods.entity.Goods;
import com.thinkgem.jeesite.modules.sgss.goods.entity.GoodsDetail;
import com.thinkgem.jeesite.modules.sgss.goods.entity.GoodsSku;
import com.thinkgem.jeesite.modules.sgss.goods.service.SyncGoods;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
import com.thinkgem.jeesite.modules.sgss.yzh.entity.YzhProduct;
import com.thinkgem.jeesite.modules.sgss.yzh.service.YzhProductService;

import java.util.List;

/**
 * 云中鹤商品管理Controller
 * @author martins
 * @version 2020-06-02
 */
@Controller
@RequestMapping(value = "${adminPath}/yzh/yzhProduct")
public class YzhProductController extends BaseController {

	@Autowired
	private YzhProductService yzhProductService;
	
	@ModelAttribute
	public YzhProduct get(@RequestParam(required=false) String id) {
		YzhProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = yzhProductService.get(id);
		}
		if (entity == null){
			entity = new YzhProduct();
		}
		return entity;
	}
	@RequiresPermissions("goods:goods:view")
	@RequestMapping(value = "syncform")
	public String syncform(YzhProduct yzhProduct, Model model,HttpSession session) throws Exception {
		Goods goods=new Goods();
		goods.setArtno(yzhProduct.getProductcode());
		goods.setName(yzhProduct.getName());
		goods.setLogo(yzhProduct.getThumbnailimage());
		GoodsDetail gd=new GoodsDetail();
		gd.setDetails(yzhProduct.getProductDescription());
		goods.setDetail(gd);
	    List<GoodsSku> goodsSkuList = Lists.newArrayList();		// 子表列表
		GoodsSku gs=new GoodsSku();
		gs.setMarketPrice(yzhProduct.getMarketprice());
		gs.setSettlementPrice(yzhProduct.getRetailprice());
		goodsSkuList.add(gs);
		goods.setGoodsSkuList(goodsSkuList);
		model.addAttribute("goods", goods);
		return "sgss/yzh/goodsSyncform";
	}
	@RequiresPermissions("yzh:yzhProduct:edit")
	@RequestMapping(value = "tb")
	public String tb(YzhProduct yzhProduct, Model model, RedirectAttributes redirectAttributes) {
		yzhProductService.tb(yzhProduct);
		addMessage(redirectAttributes, "同步云中鹤商品管理成功");
		return "redirect:"+Global.getAdminPath()+"/yzh/yzhProduct/?repage";
	}
	@RequiresPermissions("yzh:yzhProduct:edit")
	@RequestMapping(value = "saveSync")
	public String saveSync(Goods goods, Model model, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {

		yzhProductService.saveSync(goods);

		addMessage(redirectAttributes, "同步商品管理成功");
		return "redirect:"+Global.getAdminPath()+"/goods/goods/?repage";
	}
	@RequiresPermissions("yzh:yzhProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(YzhProduct yzhProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YzhProduct> page = yzhProductService.findPage(new Page<YzhProduct>(request, response), yzhProduct); 
		model.addAttribute("page", page);
		return "sgss/yzh/yzhProductList";
	}

	@RequiresPermissions("yzh:yzhProduct:view")
	@RequestMapping(value = "form")
	public String form(YzhProduct yzhProduct, Model model) {
		model.addAttribute("yzhProduct", yzhProduct);
		return "sgss/yzh/yzhProductForm";
	}

	@RequiresPermissions("yzh:yzhProduct:edit")
	@RequestMapping(value = "save")
	public String save(YzhProduct yzhProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, yzhProduct)){
			return form(yzhProduct, model);
		}
		yzhProductService.save(yzhProduct);
		addMessage(redirectAttributes, "保存云中鹤商品管理成功");
		return "redirect:"+Global.getAdminPath()+"/yzh/yzhProduct/?repage";
	}
	
	@RequiresPermissions("yzh:yzhProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(YzhProduct yzhProduct, RedirectAttributes redirectAttributes) {
		yzhProductService.delete(yzhProduct);
		addMessage(redirectAttributes, "删除云中鹤商品管理成功");
		return "redirect:"+Global.getAdminPath()+"/yzh/yzhProduct/?repage";
	}

}