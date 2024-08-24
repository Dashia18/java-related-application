package org.dashia18.storage.model.audit;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.dashia18.util.Constants;
import org.dashia18.util.ObjectToJsonConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldNameConstants
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "audit_entities")
public class AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar", nullable = false)
    private AuditableEntityType auditableEntityType;

    @Column(columnDefinition = "varchar", nullable = false)
    private Long auditableEntityId;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar", nullable = false)
    private OperationType operationType;

    @Column(nullable = false)
    private OffsetDateTime startOperationTime;

    @CreatedDate
    @Column(nullable = false)
    private OffsetDateTime endOperationTime;

    @Column(nullable = false)
    private String userId;

    @Column(columnDefinition = Constants.JSON_TYPE)
    @Convert(converter = ObjectToJsonConverter.class)
    private Object payload;
}
