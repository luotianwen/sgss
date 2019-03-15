/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.agent.web;

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
import com.thinkgem.jeesite.modules.sgss.agent.entity.AgentOrder;
import com.thinkgem.jeesite.modules.sgss.agent.service.AgentOrderService;

/**
 * 提现申请Controller
 * @author martins
 * @version 2019-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/agent/agentOrder")
public class AgentOrderController extends BaseController {

	@Autowired
	private AgentOrderService agentOrderService;
	
	@ModelAttribute
	public AgentOrder get(@RequestParam(required=false) String id) {
		AgentOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = agentOrderService.get(id);
		}
		if (entity == null){
			entity = new AgentOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("agent:agentOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(AgentOrder agentOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AgentOrder> page = agentOrderService.findPage(new Page<AgentOrder>(request, response), agentOrder); 
		model.addAttribute("page", page);
		return "sgss/agent/agentOrderList";
	}

	@RequiresPermissions("agent:agentOrder:view")
	@RequestMapping(value = "form")
	public String form(AgentOrder agentOrder, Model model) {
		model.addAttribute("agentOrder", agentOrder);
		return "sgss/agent/agentOrderForm";
	}
	@RequiresPermissions("agent:agentOrder:view")
	@RequestMapping(value = "view")
	public String view(AgentOrder agentOrder, Model model) {
		model.addAttribute("agentOrder", agentOrder);
		return "sgss/agent/agentOrderView";
	}
	@RequiresPermissions("agent:agentOrder:edit")
	@RequestMapping(value = "save")
	public String save(AgentOrder agentOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, agentOrder)){
			return form(agentOrder, model);
		}
		agentOrderService.save(agentOrder);
		addMessage(redirectAttributes, "保存提现申请成功");
		return "redirect:"+Global.getAdminPath()+"/agent/agentOrder/?repage";
	}
	
	@RequiresPermissions("agent:agentOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(AgentOrder agentOrder, RedirectAttributes redirectAttributes) {
		agentOrderService.delete(agentOrder);
		addMessage(redirectAttributes, "删除提现申请成功");
		return "redirect:"+Global.getAdminPath()+"/agent/agentOrder/?repage";
	}

}