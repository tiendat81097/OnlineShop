package com.onlineshop.dao.impl;

import java.util.Date;
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
import com.onlineshop.model.ProductDetail;
import com.onlineshop.model.PaginationResult;
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
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT NEW " + ProductInfo.class.getName() 
			+ "(PRO.CODE, PRO.NAME, PRO.PRICE, PRO.QUANTITIY) FROM PRODUCT PRO";
		hql += " ORDER BY PRO.CREATEDATE DESC";
		Query<ProductInfo> query = session.createQuery(hql);
		PaginationResult<ProductInfo> paginationResult = new PaginationResult<ProductInfo>(query, page, maxResult);
		return paginationResult;
	}

	@Override
	public PaginationResult<ProductInfo> getProductByName(int page, int maxResult, String name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT NEW " + ProductInfo.class.getName()
			+ " (PRO.CODE, PRO.NAME, PRO.PRICE, PRO.QUANTITY) FROM PRODUCT PRO WHERE LOWER(PRO.NAME) "
			+ "LIKE :LIKENAME ORDER BY PRO.CREATEDATE DESC";
		Query<ProductInfo> query = session.createQuery(hql);
		if(name != null && name.length() > 0)
			query.setParameter("LIKENAME", "%" + name.toLowerCase() + "%");
		PaginationResult<ProductInfo> paginationResult = new PaginationResult<ProductInfo>(query, page, maxResult);		
		return paginationResult;
	}

	@Override
	public PaginationResult<ProductInfo> getProductInfosByCategory(int page, int maxResult, String categoryName,
			String producerName) {
		// TODO Auto-generated method stub
		Category category = getCategoryByName(categoryName);
		Producer producer = getProducerByName(producerName);
		if(category == null && producer == null)
			return getAllProductInfos(page, maxResult);
		else if(category != null && producer == null)
			return getAllByCategory(page, maxResult, category.getId());
		else
			return getAllProductByProducer(page, maxResult, category.getId(), producer.getId());
	}

	public PaginationResult<ProductInfo> getAllByCategory(int page, int maxResult, int categoryId){
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT NEW " + ProductInfo.class.getName() 
			+ "(PRO.CODE, PRO.NAME, PRO.QUANTITY) FROM PRODUCT PRO";
		hql += " WHERE PRO.CATEGORY.ID = :ID";
		hql += " ORDER BY PRO.CREATEDATE DESC";
		Query<ProductInfo> query = session.createQuery(hql);
		query.setParameter("ID", categoryId);
		PaginationResult<ProductInfo> paginationResult = new PaginationResult<ProductInfo>(query, page, maxResult);
		return paginationResult;
	}

	public PaginationResult<ProductInfo> getAllProductByProducer(int page, int maxResult, int categoryId, int producerId){
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT NEW " + ProductInfo.class.getTypeName()
			+ "(PRO.CODE, PRO.NAME, PRO.PRICE, PRO.QUANTITY) FROM PRODUCT PRO";
		hql += "WHERE PRO.CATEGORY.ID = :CATEGORYID AND PRO.PRODUCER.ID = :PRODUCERID";
		hql += " ORDER BY PRO.CREATEDATE DESC";
		Query<ProductInfo> query = session.createQuery(hql);
		query.setParameter( "CATEGORYID", categoryId);
		query.setParameter("PRODUCERID", producerId);
		PaginationResult<ProductInfo> paginationResult = new PaginationResult<ProductInfo>(query, page, maxResult);
		return paginationResult;
	}

	@Override
	public PaginationResult<ProductInfo> getProductInfosByProducer(int page, int maxResult, int producerId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT NEW " + ProductInfo.class.getName()
			+ "(PRO.CODE, PRO.NAME, PRO.PRICE, PRO.QUANTITY) FROM PRODUCT PRO";
		hql += " WHERE PRO.PRODUCER.ID = :ID";
		hql += " ORDER BY PRO.CREATEDATE DESC";
		Query<ProductInfo> query = session.createQuery(hql);
		query.setParameter("ID", producerId);
		PaginationResult<ProductInfo> paginationResult = new PaginationResult<ProductInfo>(query, page, maxResult);
		return paginationResult;
	}

	@Override
	public ProductInfo getProductInfoByCode(String code) {
		// TODO Auto-generated method stub
		Product product = this.getProductByCode(code);
		ProductDetail productDetail = new ProductDetail(product.getDetail().getId(), product.getDetail().getInsurance(),
				product.getDetail().getWeight(), product.getDetail().getColor(),
				product.getDetail().getSpecifications());

		ProductInfo productInfo = new ProductInfo(product.getCode(), product.getName(), product.getPrice(),
				product.getQuantity(), productDetail, product.getCategory().getName() , product.getProducer().getName(),
				null);
		return productInfo;
	}

	@Override
	public void saveProductInfo(ProductInfo productInfo) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String code = productInfo.getCode();
		Product product = null;
		boolean isNew = false;

		if(code != null){
			product = getProductByCode(code);
			if(product == null)
				product = new Product();
			isNew = true;
			product.setCreateDate(new Date());
		}

		product.setCode(code);
		product.setName(productInfo.getName());
		product.setQuantity(productInfo.getQuantity());
		product.setPrice(productInfo.getPrice());
		product.setCategory(getCategoryByName(productInfo.getCategory()));
		product.setProducer(getProducerByName(productInfo.getProducer()));
		if(productInfo.getFileData() != null){
			byte[] image = productInfo.getFileData().getBytes();
			if(image != null && image.length > 0)
				product.setImage(image);;
		}

		if(isNew)
			session.persist(product);
		product.setDetail(updateDetail(productInfo));
		session.flush();
	}

	@Override
	public boolean deleteProduct(String code) {
		// TODO Auto-generated method stub
		boolean isDelete = false;
		try{
			Session session = sessionFactory.getCurrentSession();
			Product product = session.get(Product.class, code);
			session.delete(product);
			isDelete = true;
		} catch (Exception e){
			e.getMessage();
		}
		return isDelete;
	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT CATE FROM CATEGORY CATE";
		Query<Category> query = session.createQuery(hql);
		List<Category> categories = query.getResultList();
		return categories;
	}

	@Override
	public List<Producer> getAllProducer() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT PRO FROM PRODUCER PRO";
		Query<Producer> query = session.createQuery(hql);
		List<Producer> categories = query.getResultList();
		return categories;
	}
	
	public Category getCategoryByName(String categoryName){
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT CAT FROM CATEGORY CAT WHERE CAT.NAME = :NAME";
		Query<Category> query = session.createQuery(hql);
		query.setParameter("NAME", categoryName);
		Category category = (Category) query.uniqueResult();
		return category;
	}
	
	public Producer getProducerByName(String producerName){
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT PRO FROM PRODUCER PRO WHERE PRO.NAME = :NAME";
		Query<Producer> query = session.createQuery(hql);
		query.setParameter("NAME", producerName);
		Producer producer = (Producer) query.uniqueResult();
		return producer;
	}

	public com.onlineshop.entity.ProductDetail updateDetail(ProductInfo productInfo){
		Session session = sessionFactory.getCurrentSession();
		com.onlineshop.entity.ProductDetail productDetail = null;
		try{
			productDetail = session.get(com.onlineshop.entity.ProductDetail.class, productInfo.getDetail().getId());
			if(productDetail == null)
				productDetail = new com.onlineshop.entity.ProductDetail();
			productDetail.setColor(productInfo.getDetail().getColor());
			productDetail.setInsurance(productInfo.getDetail().getInsurance());
			productDetail.setSpecifications(productInfo.getDetail().getSpecification());
			productDetail.setWeight(productInfo.getDetail().getWeight());
			productDetail.setProduct(getProductByCode(productInfo.getCode()));
		} catch(Exception e){
			e.getMessage();
		}
		return productDetail;
	}
}
