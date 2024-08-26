package org.dashia18.storage.model;

import org.dashia18.storage.model.audit.AuditableEntityType;

public interface AuditableDto {
    Long getId();

    AuditableEntityType getType();
}