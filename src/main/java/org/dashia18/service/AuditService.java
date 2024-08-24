package org.dashia18.service;

import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.dashia18.storage.model.Auditable;
import org.dashia18.storage.model.audit.AuditableEntity;
import org.dashia18.storage.model.audit.AuditableEntityType;
import org.dashia18.storage.model.audit.OperationType;
import org.dashia18.storage.repo.AuditableEntityRepo;
import org.dashia18.util.Constants;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditService {
    private final AuditableEntityRepo auditableEntityRepo;

    public AuditableEntity storeChanges(AuditableEntityType auditableEntityType, Long auditableEntityId,
                                        OperationType operationType, OffsetDateTime startTime, Auditable payload) {
        AuditableEntity auditableEntity = AuditableEntity.builder()
                .auditableEntityType(auditableEntityType)
                .auditableEntityId(auditableEntityId)
                .operationType(operationType)
                .startOperationTime(startTime)
                .userId(Constants.ADMIN_USER)
                .payload(payload)
                .build();
        return auditableEntityRepo.save(auditableEntity);
    }
}