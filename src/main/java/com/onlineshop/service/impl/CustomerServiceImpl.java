package com.onlineshop.service.impl;

import com.onlineshop.dao.CustomerDAO;
import com.onlineshop.dao.impl.CustomerDaoImpl;
import com.onlineshop.entity.Customer;
import com.onlineshop.model.CustomerInfo;
import com.onlineshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public void saveCustomerInfo(CustomerInfo customerInfo) {
        customerDAO.saveCustomerInfo(customerInfo);
    }

    @Override
    public CustomerInfo getCustomerInfoById(int id) {
        return customerDAO.getCustomerInfoById(id);
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }
}
