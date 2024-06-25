package org.daria.serebriakova.storage.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/* Use lombok to generate constructor, setters. getters because: Records cannot be Entities
   Jakarta Persistence (JPA; formerly Java Persistence API) implementations such as Hibernate depend on features
   either forbidden or not recommended by the JEP 395:
   Records spec: no-arg constructors, non-final fields, setters, etc.
   So, no, records cannot be used as JPA Entity.
   https://www.baeldung.com/spring-jpa-java-records
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
/* Allow to call setter in one expression and return object
   as result BankAccount bankAccount = new BankAccount().setAccountNumber(...);
 */
@Accessors(chain = true)
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID accountNumber;

    /*Creating child entity in db together event mandatory field id is null: cascade = CascadeType.ALL */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bank_client_id", nullable = false)
    private BankClient bankClient;

    @Column(nullable = false)
    private long amount;

    @Column(nullable = false)
    private boolean isPremium;

    @CreatedDate
    @Column(nullable = false)
    private OffsetDateTime createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime updatedDate;
}
