package com.bolsadeideas.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Customer;

public interface IClienteDao extends JpaRepository<Customer, Long>{

}
