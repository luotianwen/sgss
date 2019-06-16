/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.goods.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.modules.sgss.brand.entity.Brand;
import com.thinkgem.jeesite.modules.sgss.brand.service.BrandService;
import com.thinkgem.jeesite.modules.sgss.goods.entity.GoodsCategory;
import com.thinkgem.jeesite.modules.sgss.goods.entity.GoodsSku;
import com.thinkgem.jeesite.modules.sgss.supplier.entity.Supplier;
import com.thinkgem.jeesite.modules.sgss.supplier.service.SupplierService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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
        User user = UserUtils.getUser();
        for(Role r:user.getRoleList()) {
            if ("客服".equals(r.getName())) {
                goods.setCreateBy(user);
                break;
            }
        }
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
		List<Brand> brandss=(List<Brand>)s.getAttribute("brands");
		if(brandss==null){
			 brandss= brandService.findList(new Brand());
			s.setAttribute("brands",brandss);
		}

		List<Supplier> supplierss=(List<Supplier>)s.getAttribute("supplierss");
		if(supplierss==null){
			supplierss= supplierService.findList(new Supplier());
			s.setAttribute("supplierss",supplierss);
		}
		model.addAttribute("suppliers", supplierss);
		model.addAttribute("brands", brandss);

		model.addAttribute("page", page);
		return "sgss/goods/goodsList";
	}

	@RequiresPermissions("goods:goods:view")
	@RequestMapping(value = "view")
	public String view(Goods goods, RedirectAttributes redirectAttributes) {

		return "sgss/goods/goodsView";
	}


	@RequiresPermissions("goods:goods:view")
	@RequestMapping(value = "syncform")
	public String syncform(Goods goods, Model model,HttpSession session) {
		String www="http://image.yoyound.com";
		Document containerDoc = Jsoup.parse(goods.getDetail().getDetails());
		Elements e=containerDoc.select("img");
		List<String> imgs= Lists.newArrayList();
		for(Element ee:e){
			imgs.add(ee.attr("src").replaceAll(www,""));
		}
		goods.getDetail().setDetails("");
		model.addAttribute("imgs", imgs);
		model.addAttribute("goods", goods);
		return "sgss/goods/goodsSyncform";
	}

	@RequiresPermissions("goods:goods:edit")
	@RequestMapping(value = "saveSync")
	public String saveSync(Goods goods, Model model, RedirectAttributes redirectAttributes,HttpSession session) throws Exception {
		for (GoodsSku goodsSku : goods.getGoodsSkuList()) {
			goodsSku.setId("");
		}
		goodsService.saveSync(goods);

		addMessage(redirectAttributes, "同步商品管理成功");
		return "redirect:"+Global.getAdminPath()+"/goods/goods/?repage";
	}
	@RequiresPermissions("goods:goods:view")
	@RequestMapping(value = "syncbyform")
	public String syncbyform(Goods goods, Model model,HttpSession session) {
		String www="http://image.yoyound.com";
		Document containerDoc = Jsoup.parse(goods.getDetail().getDetails());
		Elements e=containerDoc.select("img");
		List<String> imgs= Lists.newArrayList();
		for(Element ee:e){
			imgs.add(ee.attr("src").replaceAll(www,""));
		}
		goods.getDetail().setDetails("");
		model.addAttribute("imgs", imgs);
		model.addAttribute("goods", goods);
		return "sgss/goods/goodsSyncbyform";
	}
	@RequiresPermissions("goods:goods:edit")
	@RequestMapping(value = "saveSyncby")
	public String saveSyncby(Goods goods, Model model, RedirectAttributes redirectAttributes,HttpSession session) throws Exception {
		for (GoodsSku goodsSku : goods.getGoodsSkuList()) {
			goodsSku.setId("");
		}
		goodsService.saveSyncby(goods);

		addMessage(redirectAttributes, "同步商品管理成功");
		return "redirect:"+Global.getAdminPath()+"/goods/goods/?repage";
	}
	@RequiresPermissions("goods:goods:view")
    @RequestMapping(value = "form")
    public String form(Goods goods, Model model,HttpSession session) {

        List<Brand> brands= brandService.findList(new Brand());
        List<Supplier> suppliers= supplierService.findList(new Supplier());
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("brands", brands);
        if(StringUtils.isBlank(goods.getId())) {
			if(StringUtils.isBlank(goods.getSpec1())){
				goods.setSpec1("尺码");
			}

			Supplier s = (Supplier) session.getAttribute("saveSupplier");
			if (null != s) {
				goods.setSupplier(s);
			}
			Brand b = (Brand) session.getAttribute("saveBrand");
			if (null != b) {
				goods.setBrand(b);
			}
			String cid = (String) session.getAttribute("saveCategoryId");
			if (null != cid) {
				goods.setCategoryId(cid);
			}
			String cname = (String) session.getAttribute("saveCategoryName");
			if (null != cname) {
				goods.setCategoryName(cname);
			}
		}
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
	public String save(Goods goods, Model model, RedirectAttributes redirectAttributes,HttpSession session) {
		if (!beanValidator(model, goods)){
			return form(goods, model,session);
		}

		if(null==goods.getGoodsSkuList()||goods.getGoodsSkuList().size()==0){
			addMessage(model, "sku必须填");
			return form(goods, model,session);
		}
		if(StringUtils.isBlank(goods.getDetail().getDetails())){
			addMessage(model, "详情必须填");
			return form(goods, model,session);
		}
		if(StringUtils.isBlank(goods.getBrand().getId())){
			addMessage(model, "品牌必须填");
			return form(goods, model,session);
		}
		goodsService.savePass(goods);

		session.setAttribute("saveSupplier",goods.getSupplier());
		session.setAttribute("saveBrand",goods.getBrand());
		session.setAttribute("saveCategoryId",goods.getCategoryId());
		session.setAttribute("saveCategoryName",goods.getCategoryName());
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