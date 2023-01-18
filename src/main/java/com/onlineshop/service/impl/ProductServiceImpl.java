package com.onlineshop.service.impl;

import com.onlineshop.dao.ProductDAO;
import com.onlineshop.entity.Category;
import com.onlineshop.entity.Producer;
import com.onlineshop.entity.Product;
import com.onlineshop.model.PaginationResult;
import com.onlineshop.model.ProductInfo;
import com.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public PaginationResult<ProductInfo> getAllProductInfos(int page, int maxResult) {
        return productDAO.getAllProductInfos(page, maxResult);
    }

    @Override
    public PaginationResult<ProductInfo> getProductInfosByCategory(int page, int maxResult, String categoryName, String producerName) {
        return productDAO.getProductInfosByCategory(page, maxResult, categoryName, producerName);
    }

    @Override
    public PaginationResult<ProductInfo> getProductByName(int page, int maxResult, String name) {
        return productDAO.getProductByName(page, maxResult, name);
    }

    @Override
    public ProductInfo getProductInfoByCode(String code) {
        return productDAO.getProductInfoByCode(code);
    }

    @Override
    public Product getProductByCode(String code) {
        return productDAO.getProductByCode(code);
    }

    @Override
    public void saveProductInfo(ProductInfo productInfo) {
        productDAO.saveProductInfo(productInfo);
    }

    @Override
    public boolean deleteProduct(String code) {
        return productDAO.deleteProduct(code);
    }

    @Override
    public List<Category> getAllCategory() {
        return productDAO.getAllCategory();
    }

    @Override
    public List<Producer> getAllProducer() {
        return productDAO.getAllProducer();
    }
}
