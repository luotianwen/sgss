/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.order.service;

import java.io.IOException;
import java.util.List;

import com.thinkgem.jeesite.common.config.Global;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sgss.order.entity.OrderAfterSales;
import com.thinkgem.jeesite.modules.sgss.order.dao.OrderAfterSalesDao;

/**
 * 订单售后Service
 * @author martins
 * @version 2018-11-22
 */
@Service
@Transactional(readOnly = true)
public class OrderAfterSalesService extends CrudService<OrderAfterSalesDao, OrderAfterSales> {

	public OrderAfterSales get(String id) {
		return super.get(id);
	}
	
	public List<OrderAfterSales> findList(OrderAfterSales orderAfterSales) {
		return super.findList(orderAfterSales);
	}
	
	public Page<OrderAfterSales> findPage(Page<OrderAfterSales> page, OrderAfterSales orderAfterSales) {
		return super.findPage(page, orderAfterSales);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderAfterSales orderAfterSales) {
		super.save(orderAfterSales);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderAfterSales orderAfterSales) {
		super.delete(orderAfterSales);
	}
	@Transactional(readOnly = false)
    public void backaddress(OrderAfterSales simpleOrderAfter) {
		OrderAfterSales orderAfterSales2=this.get(simpleOrderAfter.getId());
		if(orderAfterSales2.getType().equals("1"))
		{
			orderAfterSales2.setState("20");
		}
		if(orderAfterSales2.getType().equals("2"))
		{
			orderAfterSales2.setState("30");
		}
		dao.updateState(orderAfterSales2);
		dao.backaddress(simpleOrderAfter);
    }
	@Transactional(readOnly = false)
	public void fast(OrderAfterSales simpleOrderAfter) {
		dao.fast(simpleOrderAfter);
	}

	public void returnMoney(OrderAfterSales simpleOrderAfter) {
		//simpleOrderAfter.setReturnAmount(simpleOrderAfter.getReturnAmount()*100);
		OrderAfterSales simpleOrderAfter2=this.get(simpleOrderAfter.getId());
		OkHttpClient okHttpClient = new OkHttpClient();
		RequestBody requestBody = new FormBody.Builder()
				.add("flag", "yoyound123")
				.add("orderNumber",simpleOrderAfter2.getOrdernumber())
				.add("refundFee",simpleOrderAfter.getReturnAmount()+"")
				.build();

		Request request = new Request.Builder()
				.url(Global.getConfig("refundUrl"))
				.post(requestBody)
				.build();

		okHttpClient.newCall(request).enqueue(new Callback() {
			public void onFailure(Call call, IOException e) {
			}

			public void onResponse(Call call, Response response) throws IOException {

			}
		});
	}
}