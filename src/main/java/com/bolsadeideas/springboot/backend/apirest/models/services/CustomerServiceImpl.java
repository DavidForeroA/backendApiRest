package com.bolsadeideas.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.backend.apirest.models.dao.IClienteDao;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Customer;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private IClienteDao clienteDao;

	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAll() {
		return (List<Customer>) clienteDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Customer findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Customer save(Customer cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

}
