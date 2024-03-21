package com.kog.Account.controller;

import java.util.List;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kog.Account.dto.Account;
import com.kog.Account.service.AccountService;

@RestController
public class AccountController {
	
	private AccountService accountService;
	
	public AccountController(AccountService accountService)
	{
		this.accountService=accountService;
	}
	
	@PostMapping("/insert")
	public Account addAccount(@RequestBody Account account)
	{
		return accountService.addAccount(account);
	}
	
	@GetMapping("/getAll")
	public List<Account> getAll()
	{
		return accountService.getAll();
	}
	
	@GetMapping("/getById")
	public Account getById(@RequestParam Long id)
	{
		return accountService.getById(id);
	}
	
	@PutMapping("/update")
	public Account updateAccount(@RequestParam Long id,@RequestParam String branch)
	{
		return accountService.updateAccount(id, branch);
	}
	
	@DeleteMapping("/delete")
	public String deleteAccount(@RequestParam Long id)
	{
		return accountService.deleteAccount(id);
	}
	

}
