package org.daria.serebriakova.storage.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

/* Use lombok to generate constructor, setters. getters because: Records cannot be Entities
   Jakarta Persistence (JPA; formerly Java Persistence API) implementations such as Hibernate depend on features
   either forbidden or not recommended by the JEP 395: Records spec: no-arg constructors, non-final fields, setters, etc.
   So, no, records cannot be used as JPA Entity.
   https://www.baeldung.com/spring-jpa-java-records
 */
@Data
@Entity
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private int accountNumber;
    @Column(nullable = false, unique = true)
    @OneToOne
    @JoinColumn(name = "bank_client_id", nullable = false)
    private BankClient bankClient;
    @Column(nullable = false)
    private long amount;
    @Column(nullable = false)
    private boolean isPremium;
}
