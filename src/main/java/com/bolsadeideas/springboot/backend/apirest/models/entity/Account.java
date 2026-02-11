package com.bolsadeideas.springboot.backend.apirest.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.ForeignKey;
import lombok.Data;

@Entity
@Table(name="account")
@Data
public class Account {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false,
                foreignKey = @ForeignKey(name = "FK_account_customer"))
    private Customer customer;    

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @NotNull(message ="Debe estar informado")
    private Boolean status;

    @PrePersist
    public void generateAccountNumber() {
        if (this.accountNumber == null) {
            this.accountNumber = String.format("%06d", (int)(Math.random() * 1_000_000));
        }
    }


}
