package com.onlineshop.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlineshop.dao.CustomerDAO;
import com.onlineshop.dao.OrderDAO;
import com.onlineshop.dao.ProductDAO;
import com.onlineshop.entity.Customer;
import com.onlineshop.entity.Order;
import com.onlineshop.entity.OrderDetail;
import com.onlineshop.entity.Product;
import com.onlineshop.model.CartInfo;
import com.onlineshop.model.CartLineInfo;
import com.onlineshop.model.CustomerInfo;
import com.onlineshop.model.OrderDetailInfo;
import com.onlineshop.model.OrderInfo;
import com.onlineshop.model.PaginationResult;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	private int getMaxOrderNum() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT MAX(ORD.ORDERNUM) FROM ORDER ORD";
		Query<Integer> query = session.createQuery(hql);
		Integer value = (Integer) query.uniqueResult();
		if(value == null)
			return 0;
		return value;
	}
	
	@Override
	public void saveOrder(CartInfo cartInfo) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		int orderNum = getMaxOrderNum() + 1;
		
		Order order = new Order();
		order.setId(UUID.randomUUID().toString());
		order.setOrderDate(new Date());
		order.setOrderNum(orderNum);
		order.setAmount(cartInfo.getAmountTotal());
		
		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		Customer customer = customerDAO.getCustomerById(customerInfo.getId());
		order.setCustomer(customer);
		session.persist(order);
		
		List<CartLineInfo> cartLineInfos = cartInfo.getCartLineInfos();
		for(CartLineInfo cartLineInfo : cartLineInfos) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setId(UUID.randomUUID().toString());
			orderDetail.setOrder(order);
			orderDetail.setPrice(cartLineInfo.getProductInfo().getPrice());
			orderDetail.setAmount(cartLineInfo.getAmount());
			orderDetail.setQuantity(cartLineInfo.getQuantity());
			
			String code = cartLineInfo.getProductInfo().getCode();
			Product product = productDAO.getProductByCode(code);
			product.setQuantity(product.getQuantity() - cartLineInfo.getQuantity());
			session.persist(product);
			orderDetail.setProduct(product);
			session.persist(orderDetail);
		}
		
		cartInfo.setOrderNum(orderNum);
		session.flush();
	}

	public Order GetOrderById(String orderId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT ORD FROM ORDER ORD WHERE ORD.ID = :ORDERID";
		Query<Order> query = session.createQuery(hql);
		query.setParameter("ORDERID", orderId);
		Order order = (Order) query.uniqueResult();
		return order;
	}

	@Override
	public OrderInfo getOrderInfoById(String orderId) {
		// TODO Auto-generated method stub
		Order order = GetOrderById(orderId);
		if(order == null) {
			return null;
		}

		CustomerInfo customerInfo = new CustomerInfo(order.getCustomer().getFullName(), order.getCustomer().getAddress(), order.getCustomer().getEmail(),

				order.getCustomer().getPhone());
		OrderInfo orderInfo = new OrderInfo(order.getId(), order.getOrderDate(), order.getOrderNum(), order.getAmount(), customerInfo);
		return orderInfo;
	}

	@Override
	public PaginationResult<OrderInfo> getAllOrderInfo(int page, int maxResult) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT NEW " + OrderInfo.class.getName()
				+ " (ORD.ID, ORD.ORDERDATE, ORD.ORDERNUM, ORD.AMOUNT, ORD.CUSTOMER.ACCOUNT.USERNAME) "
				+ " FROM ORDER ORD ORDER BY ORD.ORDERNUM DESC";
		Query<OrderInfo> query = session.createQuery(hql);
		List<OrderInfo> orderInfos = query.list();
		PaginationResult<OrderInfo> paginationResult = new PaginationResult<OrderInfo>(query, page, maxResult);
		return paginationResult;
	}

	@Override
	public List<OrderDetailInfo> getAllOrderDetail(String orderId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT NEW " + OrderDetailInfo.class.getName() + " (ORD.ID, ORD.PRODUCT.CODE, ORD.PRODUCT.NAME, "
				+ " ORD.QUANTITY, ORD.PRICE, ORD.AMOUNT) FROM ORDERDETAIL ORD WHERE ORD.ORDER.ID = :ORDERID";
		Query<OrderDetailInfo> query = session.createQuery(hql);
		query.setParameter("ORDERID", orderId);
		List<OrderDetailInfo> orderDetailInfos = query.list();
		return orderDetailInfos;
	}

	@Override
	public List<OrderInfo> getOrderByCustomer(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT NEW " + OrderInfo.class.getName() + " (ORD.ID, ORD.ORDERDATE, ORD.ORDERNUM, ORD.AMOUNT)"
				+ " FROM ORDER ORD WHERE ORD.CUSTOMER.ID = :ID";
		Query<OrderInfo> query = session.createQuery(hql);
		query.setParameter("ID", id);
		List<OrderInfo> orderInfo = query.list();
		return orderInfo;
	}

}
