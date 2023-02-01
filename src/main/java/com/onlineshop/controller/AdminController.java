package com.onlineshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineshop.model.AccountInfo;
import com.onlineshop.service.AccountService;
import com.onlineshop.validator.AccountInfoValidator;

@Controller
public class AdminController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountInfoValidator accountInfoValidator;
	
	@GetMapping(value = "/login")
	public String login(Model model) {
		return "login";
	}
	
	@GetMapping(value = "/register")
	public String registerAccount(Model model, @RequestParam(value = "id", defaultValue = "") String id) {
		AccountInfo accountInfo = new AccountInfo();
		model.addAttribute("registerForm", accountInfo);
		return "register";
	}
	
	@PostMapping(value = "/register")
	public String saveNewAccount(Model model, HttpServletRequest request, @ModelAttribute("registerForm") @Valid AccountInfo accountInfo, BindingResult result) {
		accountInfoValidator.validate(accountInfo, result);
		if(result.hasErrors())
			return "register";
		try {
			accountService.registerNewAccount(accountInfo);
			request.login(accountInfo.getUserName(), accountInfo.getPassword());
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "register";
		}
		return "redirect:/accountInfo";
	}
}
