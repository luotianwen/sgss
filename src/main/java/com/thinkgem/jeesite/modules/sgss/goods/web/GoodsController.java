/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.goods.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thinkgem.jeesite.modules.sgss.brand.entity.Brand;
import com.thinkgem.jeesite.modules.sgss.brand.service.BrandService;
import com.thinkgem.jeesite.modules.sgss.goods.entity.GoodsCategory;
import com.thinkgem.jeesite.modules.sgss.goods.entity.GoodsSku;
import com.thinkgem.jeesite.modules.sgss.supplier.entity.Supplier;
import com.thinkgem.jeesite.modules.sgss.supplier.service.SupplierService;
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
	@Autowired
	private SupplierService supplierService;

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
		HttpSession s=request.getSession();
		StringBuffer categoryName=new StringBuffer();
		for (Goods g:page.getList()
			 ) {
			Brand b=(Brand)s.getAttribute("b"+g.getBrand().getId());
			if(b==null){
				b=brandService.get(g.getBrand().getId());
				s.setAttribute("b"+g.getBrand().getId(),b);
			}
			g.setBrand(b);
			Supplier su=(Supplier)s.getAttribute("s"+g.getSupplier().getId());
			if(su==null){
				su=supplierService.get(g.getSupplier().getId());
				s.setAttribute("s"+g.getSupplier().getId(),su);
			}
			g.setBrand(b);
			g.setSupplier(su);
			String cn=(String)s.getAttribute("cs"+g.getId());
            if(StringUtils.isEmpty(cn)) {
				List<GoodsCategory> goodsCategoryList = goodsService.findGoodsCategoryList(new GoodsCategory(g));
				categoryName.setLength(0);
				for (GoodsCategory c : goodsCategoryList
				) {
					if (null != c.getCategory()) {
						categoryName.append(c.getCategory().getName()).append(",");
					}
				}
				cn=categoryName.toString();
				s.setAttribute("cs"+g.getId(),cn);
			}
			g.setCategoryName(cn);
		}
		model.addAttribute("page", page);
		return "sgss/goods/goodsList";
	}

	@RequiresPermissions("goods:goods:view")
	@RequestMapping(value = "view")
	public String view(Goods goods, RedirectAttributes redirectAttributes) {

		return "sgss/goods/goodsView";
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
        List<Supplier> suppliers= supplierService.findList(new Supplier());
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("brands", brands);
        model.addAttribute("goods", goods);
        return "sgss/goods/goodsForm";
    }
    @RequiresPermissions("goods:goods:view")
    @RequestMapping(value = "copy")
    public String copy(Goods goods, Model model) {
        if(StringUtils.isBlank(goods.getSpec1())){
            goods.setSpec1("颜色");
        }
        if(StringUtils.isBlank(goods.getSpec2())){
            goods.setSpec2("尺码");
        }
        List<Brand> brands= brandService.findList(new Brand());
        List<Supplier> suppliers= supplierService.findList(new Supplier());
        goods.setId(null);
        for(GoodsSku sku:goods.getGoodsSkuList()){
            sku.setId(null);
        }
        model.addAttribute("suppliers", suppliers);
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
		if(null==goods.getGoodsSkuList()||goods.getGoodsSkuList().size()==0){
			addMessage(model,  new String[]{"sku必须有"} );
			return form(goods, model);
		}
		goodsService.savePass(goods);
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
    @RequiresPermissions("goods:goods:edit")
    @RequestMapping(value = "upordown")
    public String upordown(String remarks,String tstate,RedirectAttributes redirectAttributes) {
	    Goods goods=new Goods();
	    goods.setRemarks(remarks);
        goods.setState(tstate);
        goodsService.upordown(goods);
        addMessage(redirectAttributes, "操作商品管理成功");
        return "redirect:"+Global.getAdminPath()+"/goods/goods/?repage";
    }
    @RequiresPermissions("goods:goods:edit")
    @RequestMapping(value = "passornot")
    public String passornot(String remarks,String tpass, RedirectAttributes redirectAttributes) {
        Goods goods=new Goods();
        goods.setRemarks(remarks);
        goods.setPass(tpass);
        goodsService.passornot(goods);
        addMessage(redirectAttributes, "操作商品管理成功");
        return "redirect:"+Global.getAdminPath()+"/goods/goods/?repage";
    }
}