package com.onlineshop.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onlineshop.dao.ProductDAO;
import com.onlineshop.entity.Category;
import com.onlineshop.entity.Producer;
import com.onlineshop.entity.Product;
import com.onlineshop.model.PaginationResult;
import com.onlineshop.model.ProductDetail;
import com.onlineshop.model.ProductInfo;



@Repository
@Transactional
public class ProductDaoImpl implements ProductDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product getProductByCode(String code) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT PRO FROM PRODUCT PRO WHERE PRO.CODE = :CODE";
		Query<Product> query = session.createQuery(hql);
		query.setParameter("CODE", code);
		Product product = (Product) query.uniqueResult();
		return product;
	}

	@Override
	public PaginationResult<ProductInfo> getAllProductInfos(int page, int maxResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationResult<ProductInfo> getProductByName(int page, int maxResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationResult<ProductInfo> getProductInfosByCategory(int page, int maxResult, String categoryName,
			String producerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationResult<ProductInfo> getProductInfosByProducer(int page, int maxResult, int producerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductInfo getProductInfoByCode(String code) {
		// TODO Auto-generated method stub
		Product product = this.getProductByCode(code);
		ProductDetail productDetail = new ProductDetail(product.getDetail().getId(), product.getDetail().getInsurance(),
				product.getDetail().getWeight(), product.getDetail().getColor(),
				product.getDetail().getSpecification());

		ProductInfo productInfo = new ProductInfo(product.getCode(), product.getName(), product.getPrice(),
				product.getQuantity(), productDetail, product.getCategory().getName() , product.getProducer().getName(),
				null);
		return productInfo;
	}

	@Override
	public void saveProductInfo(ProductInfo productInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteProduct(String code) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producer> getAllProducer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
