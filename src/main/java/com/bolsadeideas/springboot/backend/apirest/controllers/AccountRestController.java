package com.bolsadeideas.springboot.backend.apirest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Account;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Customer;
import com.bolsadeideas.springboot.backend.apirest.models.services.IAccountService;
import com.bolsadeideas.springboot.backend.apirest.models.services.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/accounts")
	public List<Account> index() {
		return accountService.findAll();
	}

    @GetMapping("/accounts/customer/{CustomerId}")
	public ResponseEntity<?> show(@PathVariable Long CustomerId) {
		
		Map<String, Object> response = new HashMap<>();
        List<Account> accountList = new ArrayList<Account>();
		
		try {
			accountList = accountService.findByCustomerId(CustomerId);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(accountList.isEmpty()) {
			response.put("mensaje", "El cliente con ID: ".concat(CustomerId.toString().concat(" No tiene cuentas asociadas")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Account>>(accountList, HttpStatus.OK);
	}

    @GetMapping("/accounts/{id}")
	public ResponseEntity<?> showAccountbyCustomer(@PathVariable Long id) {
		
		Account account = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			account = accountService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(account == null) {
			response.put("mensaje", "la cuenta ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

    @PostMapping("/accounts")
	public ResponseEntity<?> create(@Valid @RequestBody Account account, BindingResult result) {
		
		Account accountNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

        Customer customer = customerService.findById(account.getCustomer().getId());

        if (customer == null){
            response.put("errors:", "Cliente no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
		
		try {
			accountNew = accountService.save(account);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		accountNew.setCustomer(customer);
		response.put("mensaje", "La cuenta ha sido creada con éxito!");
		response.put("cuenta", accountNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

    @PutMapping("/accounts/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Account account, BindingResult result, @PathVariable Long id) {

		Account accountActual = accountService.findById(id);

		Account accountUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (accountActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la cuenta ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			accountActual.setCustomer(account.getCustomer());
            accountActual.setStatus(account.getStatus());
            accountActual.setAccountNumber(account.getAccountNumber());

			accountUpdated = accountService.save(accountActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La cuenta ha sido actualizada con éxito!");
		response.put("cuenta", accountUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

    @DeleteMapping("/accounts/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
		    accountService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la cuenta de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cuenta eliminada con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}


}
