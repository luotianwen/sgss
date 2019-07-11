/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dl.tuan.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.modules.dl.tuan.entity.DlTuanGoodsSku;
import com.thinkgem.jeesite.modules.sgss.goods.entity.Goods;
import com.thinkgem.jeesite.modules.sgss.goods.entity.GoodsSku;
import com.thinkgem.jeesite.modules.sgss.goods.service.GoodsService;
import org.apache.shiro.authz.annotation.Logical;
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
import com.thinkgem.jeesite.modules.dl.tuan.entity.DlGoodsTuan;
import com.thinkgem.jeesite.modules.dl.tuan.service.DlGoodsTuanService;

import java.util.ArrayList;
import java.util.List;

/**
 * 团购商品Controller
 * @author martins
 * @version 2019-07-11
 */
@Controller
@RequestMapping(value = "${adminPath}/tuan/dlGoodsTuan")
public class DlGoodsTuanController extends BaseController {

	@Autowired
	private DlGoodsTuanService dlGoodsTuanService;
	@Autowired
	private GoodsService goodsService;
	@ModelAttribute
	public DlGoodsTuan get(@RequestParam(required=false) String id) {
		DlGoodsTuan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dlGoodsTuanService.get(id);
		}
		if (entity == null){
			entity = new DlGoodsTuan();
		}
		return entity;
	}
	
	@RequiresPermissions("tuan:dlGoodsTuan:view")
	@RequestMapping(value = {"list", ""})
	public String list(DlGoodsTuan dlGoodsTuan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DlGoodsTuan> page = dlGoodsTuanService.findPage(new Page<DlGoodsTuan>(request, response), dlGoodsTuan); 
		model.addAttribute("page", page);
		return "dl/tuan/dlGoodsTuanList";
	}

	@RequiresPermissions("tuan:dlGoodsTuan:view")
	@RequestMapping(value = "copyForm")
	public String copyForm(String goodsId, Model model ) {
		DlGoodsTuan dlGoodsTuan=new DlGoodsTuan();
		Goods goods=goodsService.get(goodsId);
		dlGoodsTuan.setArtno(goods.getArtno());
		dlGoodsTuan.setName(goods.getName());
		dlGoodsTuan.setLogo(goods.getLogo());
		 dlGoodsTuan.setMarketPrice(goods.getMarketPrice());
		dlGoodsTuan.setCostPrice(goods.getPrice());
		dlGoodsTuan.setSpec1(goods.getSpec1());
		dlGoodsTuan.setSpec2(goods.getSpec2());
		dlGoodsTuan.setDetails(goods.getDetail().getDetails());
		dlGoodsTuan.setImgs(goods.getImgs());
		List<DlTuanGoodsSku> dlTuanGoodsSkuList =new ArrayList<>();
		List<GoodsSku> gss=goods.getGoodsSkuList();
		for (GoodsSku s:gss){
			DlTuanGoodsSku d=new DlTuanGoodsSku();
			d.setSpec1(s.getSpec1());
			d.setSpec2(s.getSpec2());
			d.setStock(s.getStock());
			dlTuanGoodsSkuList.add(d);
		}
		dlGoodsTuan.setDlTuanGoodsSkuList(dlTuanGoodsSkuList);
		model.addAttribute("dlGoodsTuan", dlGoodsTuan);
		return "dl/tuan/dlGoodsTuanForm";
	}
	@RequiresPermissions("tuan:dlGoodsTuan:view")
	@RequestMapping(value = "form")
	public String form(DlGoodsTuan dlGoodsTuan, Model model) {
		model.addAttribute("dlGoodsTuan", dlGoodsTuan);
		return "dl/tuan/dlGoodsTuanForm";
	}
	@RequiresPermissions("tuan:dlGoodsTuan:tuanForm")
	@RequestMapping(value = "tuanForm")
	public String tuanForm(DlGoodsTuan dlGoodsTuan, Model model) {
		model.addAttribute("dlGoodsTuan", dlGoodsTuan);
		return "dl/tuan/dlGoodsTuanTuanForm";
	}

	@RequiresPermissions(value = {"tuan:dlGoodsTuan:edit", "tuan:dlGoodsTuan:tuanForm"}, logical = Logical.OR)
	@RequestMapping(value = "save")
	public String save(DlGoodsTuan dlGoodsTuan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dlGoodsTuan)){
			return form(dlGoodsTuan, model);
		}
		dlGoodsTuanService.save(dlGoodsTuan);
		addMessage(redirectAttributes, "保存团购商品成功");
		return "redirect:"+Global.getAdminPath()+"/tuan/dlGoodsTuan/?repage";
	}
	
	@RequiresPermissions("tuan:dlGoodsTuan:edit")
	@RequestMapping(value = "delete")
	public String delete(DlGoodsTuan dlGoodsTuan, RedirectAttributes redirectAttributes) {
		dlGoodsTuanService.delete(dlGoodsTuan);
		addMessage(redirectAttributes, "删除团购商品成功");
		return "redirect:"+Global.getAdminPath()+"/tuan/dlGoodsTuan/?repage";
	}

}