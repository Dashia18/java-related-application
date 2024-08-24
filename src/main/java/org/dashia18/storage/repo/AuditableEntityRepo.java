package org.dashia18.storage.repo;

import org.dashia18.storage.model.audit.AuditableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditableEntityRepo extends JpaRepository<AuditableEntity, Long> {

}
