package com.bolsadeideas.springboot.backend.apirest.models.services;

import java.util.List;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Account;

public interface IAccountService {

    public List<Account> findAll();
	
	public Account findById(Long id);
	
	public Account save(Account account);
	
	public void delete(Long id);

    public List<Account> findByCustomerId(Long id);

}
