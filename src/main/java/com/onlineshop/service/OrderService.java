package com.onlineshop.service;

import com.onlineshop.model.CartInfo;
import com.onlineshop.model.OrderDetailInfo;
import com.onlineshop.model.OrderInfo;
import com.onlineshop.model.PaginationResult;

import java.util.List;

public interface OrderService {
    public List<OrderInfo> getOrderByCustomer(int id);

    public void saveOrder(CartInfo cartInfo);

    public OrderInfo getOrderInfoById(String orderId);

    public PaginationResult<OrderInfo> getAllOrderInfo(int page, int maxResult);

    public List<OrderDetailInfo> getAllOrderDetail(String orderId);
}
