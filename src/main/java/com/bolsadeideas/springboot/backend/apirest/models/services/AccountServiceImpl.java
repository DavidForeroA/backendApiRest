package com.bolsadeideas.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.backend.apirest.models.dao.IAccountDao;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Account;

@Service
public class AccountServiceImpl implements IAccountService{

    @Autowired
    private IAccountDao accountDao;

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
       return (List<Account>) accountDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Account findById(Long id) {
        return accountDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Account save(Account account) {
        return accountDao.save(account);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        accountDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findByCustomerId(Long id) {
        return accountDao.findByCustomerId(id);
    }

}
