package com.onlineshop.dao;

import java.util.List;

import com.onlineshop.model.CartInfo;
import com.onlineshop.model.OrderDetailInfo;
import com.onlineshop.model.OrderInfo;
import com.onlineshop.model.PaginationResult;

public interface OrderDAO {
	public void saveOrder(CartInfo cartInfo);
	
	public Order getOrderInfoByI(String orderId);
	
	public PaginationResult<OrderInfo> getAllOrderInfo(int page, int maxResult);
	
	public List<OrderDetailInfo> getAllOrderDetail(String orderId);
	
	public List<OrderInfo> getOrderByCustomer(int id);
}
