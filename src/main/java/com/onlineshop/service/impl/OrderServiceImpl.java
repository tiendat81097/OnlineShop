package com.onlineshop.service.impl;

import com.onlineshop.dao.OrderDAO;
import com.onlineshop.model.CartInfo;
import com.onlineshop.model.OrderDetailInfo;
import com.onlineshop.model.OrderInfo;
import com.onlineshop.model.PaginationResult;
import com.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public List<OrderInfo> getOrderByCustomer(int id) {
        return orderDAO.getOrderByCustomer(id);
    }

    @Override
    public void saveOrder(CartInfo cartInfo) {
        orderDAO.saveOrder(cartInfo);
    }

    @Override
    public OrderInfo getOrderInfoById(String orderId) {
        return orderDAO.getOrderInfoById(orderId);
    }

    @Override
    public PaginationResult<OrderInfo> getAllOrderInfo(int page, int maxResult) {
        return orderDAO.getAllOrderInfo(page, maxResult);
    }

    @Override
    public List<OrderDetailInfo> getAllOrderDetail(String orderId) {
        return orderDAO.getAllOrderDetail(orderId);
    }
}
