package com.onlineshop.validator;

import com.onlineshop.entity.Product;
import com.onlineshop.model.CartInfo;
import com.onlineshop.model.CartLineInfo;
import com.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CartInfoValidator implements Validator {

    @Autowired
    private ProductService productService;

    @Override
    public boolean supports(Class<?> clazz) {
        return CartInfo.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CartInfo cartInfo = (CartInfo) target;
        for(CartLineInfo cartLineInfo : cartInfo.getCartLineInfos()){
           Product product = productService.getProductByCode(cartLineInfo.getProductInfo().getCode());
           if(cartLineInfo.getQuantity()  > product.getQuantity())
               errors.rejectValue("cartLineInfos", "Quantity.notenough");
        }
    }
}
