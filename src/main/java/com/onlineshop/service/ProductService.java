package com.onlineshop.service;

import com.onlineshop.entity.Category;
import com.onlineshop.entity.Producer;
import com.onlineshop.entity.Product;
import com.onlineshop.model.PaginationResult;
import com.onlineshop.model.ProductInfo;

import java.util.List;

public interface ProductService {
    public PaginationResult<ProductInfo> getAllProductInfos(int page, int maxResult);

    public PaginationResult<ProductInfo> getProductInfosByCategory(int page, int maxResult, String categoryName, String producerName);

    public PaginationResult<ProductInfo> getProductByName(int page, int maxResult, String name);

    public ProductInfo getProductInfoByCode(String code);

    public Product getProductByCode(String code);

    public void saveProductInfo(ProductInfo productInfo);

    public boolean deleteProduct(String code);

    public List<Category> getAllCategory();

    public List<Producer> getAllProducer();
}
