package com.onlineshop.validator;

import com.onlineshop.entity.Account;
import com.onlineshop.model.AccountInfo;
import com.onlineshop.service.AccountService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Set;



@Component
public class AccountInfoValidator implements Validator {

    @Autowired
    private AccountService accountService;

    private EmailValidator emailValidator = EmailValidator.getInstance();


    @Override
    public boolean supports(Class<?> clazz) {
        return AccountInfo.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountInfo accountInfo = (AccountInfo) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.registerForm.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.registerForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.registerForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.registerForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.registerForm.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.registerForm.phone");

        String userName = accountInfo.getUserName();
        Account account = accountService.getAccountByUserName(userName);
        if(userName != null && account != null)
            errors.rejectValue("userName", "Duplicate.registerForm.userName");

        if(!emailValidator.isValid(accountInfo.getEmail()))
            errors.rejectValue("email", "Pattern.customerForm.email");

        String phone = accountInfo.getPhone();
        if(!phone.matches("[0-9]{10}$"))
            errors.rejectValue("phone", "Pattern.customerForm.phone");
    }
}
