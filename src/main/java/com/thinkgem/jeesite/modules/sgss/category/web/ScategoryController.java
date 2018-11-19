/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.category.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sgss.category.entity.Scategory;
import com.thinkgem.jeesite.modules.sgss.category.service.ScategoryService;

/**
 * 分类管理Controller
 * @author martins
 * @version 2018-11-19
 */
@Controller
@RequestMapping(value = "${adminPath}/category/scategory")
public class ScategoryController extends BaseController {

	@Autowired
	private ScategoryService scategoryService;
	
	@ModelAttribute
	public Scategory get(@RequestParam(required=false) String id) {
		Scategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = scategoryService.get(id);
		}
		if (entity == null){
			entity = new Scategory();
		}
		return entity;
	}
	
	@RequiresPermissions("category:scategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(Scategory scategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Scategory> list = scategoryService.findList(scategory); 
		model.addAttribute("list", list);
		return "sgss/category/scategoryList";
	}

	@RequiresPermissions("category:scategory:view")
	@RequestMapping(value = "form")
	public String form(Scategory scategory, Model model) {
		if (scategory.getParent()!=null && StringUtils.isNotBlank(scategory.getParent().getId())){
			scategory.setParent(scategoryService.get(scategory.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(scategory.getId())){
				Scategory scategoryChild = new Scategory();
				scategoryChild.setParent(new Scategory(scategory.getParent().getId()));
				List<Scategory> list = scategoryService.findList(scategory); 
				if (list.size() > 0){
					scategory.setSort(list.get(list.size()-1).getSort());
					if (scategory.getSort() != null){
						scategory.setSort(scategory.getSort() + 30);
					}
				}
			}
		}
		if (scategory.getSort() == null){
			scategory.setSort(30);
		}
		model.addAttribute("scategory", scategory);
		return "sgss/category/scategoryForm";
	}

	@RequiresPermissions("category:scategory:edit")
	@RequestMapping(value = "save")
	public String save(Scategory scategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scategory)){
			return form(scategory, model);
		}
		scategoryService.save(scategory);
		addMessage(redirectAttributes, "保存分类管理成功");
		return "redirect:"+Global.getAdminPath()+"/category/scategory/?repage";
	}
	
	@RequiresPermissions("category:scategory:edit")
	@RequestMapping(value = "delete")
	public String delete(Scategory scategory, RedirectAttributes redirectAttributes) {
		scategoryService.delete(scategory);
		addMessage(redirectAttributes, "删除分类管理成功");
		return "redirect:"+Global.getAdminPath()+"/category/scategory/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Scategory> list = scategoryService.findList(new Scategory());
		for (int i=0; i<list.size(); i++){
			Scategory e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}