package org.dashia18.storage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@Table(name = "bank_clients")
public class BankClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private OffsetDateTime dateOfBirth;

    @Column
    private String country;

    @Column
    private String street;

    @Column
    private Integer building;

    @Column
    private Integer flat;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime updatedDate;
}
