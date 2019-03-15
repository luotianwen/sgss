/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.order.service;

import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.modules.sgss.order.entity.OrderAfterSales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sgss.order.entity.Order;
import com.thinkgem.jeesite.modules.sgss.order.dao.OrderDao;
import com.thinkgem.jeesite.modules.sgss.order.entity.OrderDetail;
import com.thinkgem.jeesite.modules.sgss.order.dao.OrderDetailDao;

/**
 * 订单管理Service
 * @author martins
 * @version 2018-11-22
 */
@Service
@Transactional(readOnly = true)
public class OrderService extends CrudService<OrderDao, Order> {

	@Autowired
	private OrderDetailDao orderDetailDao;
	
	public Order get(String id) {
		Order order = super.get(id);
		order.setOrderDetailList(orderDetailDao.findList(new OrderDetail(order)));
		return order;
	}
	
	public List<Order> findList(Order order) {
		return super.findList(order);
	}
	
	public Page<Order> findPage(Page<Order> page, Order order) {
		return super.findPage(page, order);
	}
	
	@Transactional(readOnly = false)
	public void save(Order order) {
		super.save(order);
		for (OrderDetail orderDetail : order.getOrderDetailList()){
			if (orderDetail.getId() == null){
				continue;
			}
			if (OrderDetail.DEL_FLAG_NORMAL.equals(orderDetail.getDelFlag())){
				if (StringUtils.isBlank(orderDetail.getId())){
					orderDetail.setOrder(order);
					orderDetail.preInsert();
					orderDetailDao.insert(orderDetail);
				}else{
					orderDetail.preUpdate();
					orderDetailDao.update(orderDetail);
				}
			}else{
				orderDetailDao.delete(orderDetail);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Order order) {
		super.delete(order);
		orderDetailDao.delete(new OrderDetail(order));
	}
	@Transactional(readOnly = false)
    public void fast(Order order) {
		dao.fast(order);
    }
    @Transactional(readOnly = false)
    public void after(Order order) {
        dao.after(order);
        Order order2=this.get(order.getId());

        OrderAfterSales orderAfterSales=new OrderAfterSales();
        orderAfterSales.setOrdernumber(order2.getOrdernumber());
        orderAfterSales.setApplyTime(new Date());
        orderAfterSales.setType("1");
        orderAfterSales.setUser(order2.getUser());
        orderAfterSales.setState("10");
        orderAfterSalesService.save(orderAfterSales);
    }

    @Autowired
    private OrderAfterSalesService orderAfterSalesService;

    public Order getByOrderNumber(String ordernumber) {
		Order order =dao.getByOrderNumber(ordernumber);
		order.setOrderDetailList(orderDetailDao.findList(new OrderDetail(order)));
		return order;
    }
}