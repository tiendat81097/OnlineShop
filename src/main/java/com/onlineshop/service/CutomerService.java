package com.onlineshop.service;

import com.onlineshop.entity.Customer;
import com.onlineshop.model.CustomerInfo;

public interface CutomerService {
    public void saveCustomerInfo(CustomerInfo customerInfo);

    public CustomerInfo getCustomerInfoById(int id);

    public Customer getCustomerById(int id);
}

