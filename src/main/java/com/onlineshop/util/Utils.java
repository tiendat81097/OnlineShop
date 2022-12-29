package com.onlineshop.util;

import javax.servlet.http.HttpServletRequest;

import com.onlineshop.model.CartInfo;

public class Utils {
	public static CartInfo getCartInfoInSession(HttpServletRequest request) {
		CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("myCartInfo");
		if(cartInfo == null) {
			cartInfo = new CartInfo();
			request.getSession().setAttribute("myCartInfo", cartInfo);
		}
		return cartInfo;
	}
	
	public static void removeCartInfoInSession(HttpServletRequest request) {
		request.getSession().removeAttribute("myCartInfo");
	}
	
	public static void storeLastOrderedCartInfoSession(HttpServletRequest request, CartInfo cartInfo) {
		request.getSession().setAttribute("lastOrderedCartInfo", cartInfo);
	}
	
	public static CartInfo getLastOrderedCartInfoSession(HttpServletRequest request) {
		return (CartInfo) request.getSession().getAttribute("lastOrderedCartInfo");
	}
}
