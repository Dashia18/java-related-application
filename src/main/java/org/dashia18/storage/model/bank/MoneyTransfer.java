package org.dashia18.storage.model.bank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.dashia18.dto.bank.TransferStatus;
import org.dashia18.storage.model.Auditable;
import org.dashia18.storage.model.audit.AuditableEntityType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
@Table(name = "money_transfers")
public class MoneyTransfer implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID fromAccount;

    @Column(nullable = false)
    private long fromAmount;

    @Column(nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID toAccount;

    @Column(nullable = false)
    private long toAmount;

    @Column(nullable = false)
    private long amount;

    @Column(nullable = false)
    private long updatedFromAmount;

    @Column(nullable = false)
    private long updatedToAmount;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar", nullable = false)
    private TransferStatus status;

    @CreatedDate
    @Column(nullable = false)
    private OffsetDateTime createdDate;

    @Column
    private String error;

    @Override
    public AuditableEntityType getType() {
        return AuditableEntityType.MONEY_TRANSFER;
    }
}
