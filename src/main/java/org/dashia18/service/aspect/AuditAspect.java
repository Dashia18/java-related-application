package org.dashia18.service.aspect;

import static java.time.OffsetTime.now;
import static org.dashia18.storage.model.audit.OperationType.CREATE;
import static org.dashia18.storage.model.audit.OperationType.FAILED;

import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.dashia18.exception.AuditException;
import org.dashia18.service.AuditService;
import org.dashia18.storage.model.AuditableDto;
import org.dashia18.storage.model.audit.AuditableEntityType;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class AuditAspect {
    private final AuditService auditService;

    @Around("@annotation(Audit)")
    public Object persistCreateOperationTime(ProceedingJoinPoint joinPoint) throws Throwable {
        OffsetDateTime startOperation = OffsetDateTime.now();
        AuditableEntityType type;
        Long auditableId;
        try {
            Object proceed = joinPoint.proceed();
            auditableId = getAuditableId(proceed);
            type = getAuditableType(proceed);
            auditService.storeChanges(type, auditableId, CREATE, startOperation, extracted(joinPoint));
            return proceed;
        } catch (Exception exception) {
            if (exception instanceof AuditException auditException) {
                auditableId = auditException.getAuditableEntityId();
                type = auditException.getType();
                auditService.storeChanges(type, auditableId, FAILED, startOperation, extracted(joinPoint));
            }
            throw exception;
        }
    }

    private AuditableDto extracted(ProceedingJoinPoint joinPoint) {
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof AuditableDto auditableDto) {
                return auditableDto;
            }
        }
        return null;
    }

    private static Long getAuditableId(Object proceed) {
        if (proceed instanceof AuditableDto auditableDto && auditableDto.getId() != null) {
            return auditableDto.getId();
        }
        return null;
    }

    private static AuditableEntityType getAuditableType(Object proceed) {
        if (proceed instanceof AuditableDto auditableDto && auditableDto.getId() != null) {
            return auditableDto.getType();
        }
        return null;
    }
}
