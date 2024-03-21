package com.kog.Account.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kog.Account.dto.Account;
import com.kog.Account.repo.AccountRepo;

@Service
public class AccountService {
	
	private AccountRepo accountRepo;
	public AccountService(AccountRepo accountRepo)
	{
		this.accountRepo=accountRepo;
	}
	
	public Account addAccount(Account ac)
	{
		return accountRepo.save(ac);
	}
	
	public List<Account> getAll()
	{
		return accountRepo.findAll();
	}
	
	public Account getById(Long id)
	{
		Optional<Account> opt=accountRepo.findById(id);
		if(opt.isPresent())
		{
			return opt.get();
		}
		else
		{
			return null;
		}
		
	}
	
	public Account updateAccount(Long id,String branchName)
	{
		Account acc=getById(id);
		if(acc!=null)
		{
			acc.setBranchName(branchName);
			accountRepo.save(acc);
			return acc;
		}
		else
		{
			return null;
		}
		
	}
	
	public String deleteAccount(Long id)
	{
		Account acc=getById(id);
		if(acc!=null)
		{
			accountRepo.delete(acc);
			return "Account with accountNo "+ id +" got deleted";
		}
		else
		{
			return "Account with accountNo "+id +" is not found";
		}
	}

}
