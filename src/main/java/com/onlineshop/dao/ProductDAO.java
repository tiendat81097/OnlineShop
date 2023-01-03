package com.onlineshop.dao;

import java.util.List;

import com.onlineshop.entity.Category;
import com.onlineshop.entity.Producer;
import com.onlineshop.entity.Product;
import com.onlineshop.model.PaginationResult;
import com.onlineshop.model.ProductInfo;

public interface ProductDAO {
	
	public Product getProductByCode(String code);
	
	public PaginationResult<ProductInfo> getAllProductInfos(int page, int maxResult);
	
	public PaginationResult<ProductInfo> getProductByName(int page, int maxResult);
	
	public PaginationResult<ProductInfo> getProductInfosByCategory(int page, int maxResult,String categoryName, String producerName);
	
	public PaginationResult<ProductInfo> getProductInfosByProducer(int page, int maxResult, int producerId);

	public ProductInfo getProductInfoByCode(String code);
	
	public void saveProductInfo(ProductInfo productInfo);
	
	public boolean deleteProduct(String code);
	
	public List<Category> getAllCategory();
	
	public List<Producer> getAllProducer();
}
