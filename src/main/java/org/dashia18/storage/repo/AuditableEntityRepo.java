package org.dashia18.storage.repo;

import java.util.List;
import org.dashia18.storage.model.audit.AuditableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditableEntityRepo extends JpaRepository<AuditableEntity, Long> {
    List<AuditableEntity> findByAuditableEntityId(Long auditableEntityId);

}
