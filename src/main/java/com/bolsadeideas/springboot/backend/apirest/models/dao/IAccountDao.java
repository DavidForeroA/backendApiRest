package com.bolsadeideas.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Account;

public interface IAccountDao extends JpaRepository<Account, Long>{
    List<Account> findByCustomerId(Long customerId);
}
