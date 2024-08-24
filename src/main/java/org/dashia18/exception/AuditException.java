package org.dashia18.exception;

import lombok.Getter;
import org.dashia18.storage.model.audit.AuditableEntityType;

@Getter
public class AuditException extends RuntimeException {

    private final Long auditableEntityId;
    private final AuditableEntityType type;
    private final String message;

    public AuditException(String message, Long auditableEntityId, AuditableEntityType type, Exception e) {
        super(message, e);
        this.auditableEntityId = auditableEntityId;
        this.type = type;
        this.message = message;
    }
}
