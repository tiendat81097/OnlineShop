package com.onlineshop.model;

import java.util.ArrayList;
import java.util.List;

public class CartInfo {
	private int orderNum;
	
	private CustomerInfo customerInfo;
	
	private List<CartLineInfo> cartLineInfos = new ArrayList<CartLineInfo>();

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public List<CartLineInfo> getCartLineInfos() {
		return cartLineInfos;
	}	

	public void setCartLineInfos(List<CartLineInfo> cartLineInfos) {
		this.cartLineInfos = cartLineInfos;
	}
	
	private CartLineInfo getCartLineInfoByCode(String code)	{
		for(CartLineInfo cartLineInfo : this.cartLineInfos) {
			if(cartLineInfo.getProductInfo().getCode().equals(code))
				return cartLineInfo;
		}
		return null;
	}
	
	public void addProduct(ProductInfo productInfo, int quantity) {
		CartLineInfo cartLineInfo = getCartLineInfoByCode(productInfo.getCode());
		
		if(cartLineInfo == null) {
			cartLineInfo = new CartLineInfo();
			cartLineInfo.setQuantity(0);
			cartLineInfo.setProductInfo(productInfo);
			this.cartLineInfos.add(cartLineInfo);
		}
		
		int newQuantity = cartLineInfo.getQuantity() + quantity;
		if(newQuantity <= 00) {
			this.cartLineInfos.remove(cartLineInfo);
		} else {
			cartLineInfo.setQuantity(newQuantity);
		}
	}
	
	public void removeProduct(ProductInfo productInfo) {
		CartLineInfo cartLineInfo = getCartLineInfoByCode(productInfo.getCode());
		if(cartLineInfo != null)
			this.cartLineInfos.remove(cartLineInfo);
	}
}
