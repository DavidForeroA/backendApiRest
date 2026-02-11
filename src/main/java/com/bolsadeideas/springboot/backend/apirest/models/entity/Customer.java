package com.bolsadeideas.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;



@Entity
@Table(name="customer",
	 uniqueConstraints = {
        @UniqueConstraint(columnNames = {"documetnType", "documentNumber"})}
)
@Data
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(nullable=false)
	private String documetnType;
	
	@NotEmpty(message ="no puede estar vacio")
	private String documentNumber;

	@Column(name="FullName")
	private String fullName;
	
	@NotEmpty(message ="no puede estar vacio")
	@Email(message="no es una dirección de correo bien formada")
	@Column(nullable=false, unique=false)
	private String email;

	private static final long serialVersionUID = 1L;
}
