package com.onlineshop.validator;

import com.onlineshop.entity.Product;
import com.onlineshop.model.ProductInfo;
import com.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductInfoValidator implements Validator {

    @Autowired
    private ProductService productService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductInfo.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductInfo productInfo = (ProductInfo) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty.productForm.code");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "NotEmpty.productForm.quantity");

        String code = productInfo.getCode();
        if(code != null && code.length() > 0){
            if(!code.matches("^S[0-9]{3}$"))
                errors.rejectValue("code", "Pattern.productForm.code");
            else if (!productInfo.getOldCode().equals(code)){
                Product product = productService.getProductByCode(code);
                if(product != null)
                    errors.rejectValue("code", "Duplicate.productForm.code");
            }
        }

        if(productInfo.getQuantity() <= 0)
            errors.rejectValue("quantity", "0.productForm.quantity");
        if(productInfo.getPrice() <= 0)
            errors.rejectValue("price", "0.productForm.price");


    }
}
