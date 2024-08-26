package org.dashia18.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import org.dashia18.AbstractIntegrationTest;
import org.dashia18.dto.bank.MoneyTransferDto;
import org.dashia18.exception.AuditException;
import org.dashia18.storage.model.audit.AuditableEntity;
import org.dashia18.storage.model.audit.AuditableEntityType;
import org.dashia18.storage.model.audit.OperationType;
import org.junit.jupiter.api.Test;

public class MoneyTransferServiceTest extends AbstractIntegrationTest {

    @Test
    public void canBeHandedByAspect() {
        MoneyTransferDto moneyTransferDto = new MoneyTransferDto(null,
                UUID.fromString("447c7c7b-fb2f-4aaa-9900-21d7ed27d01b"),
                UUID.fromString("447c7c7b-fb2f-4aaa-9900-21d7ed27d02b"), 200, false);

        MoneyTransferDto actual = moneyTransferService.transferMoney(moneyTransferDto);


        AuditableEntity lastAuditEntityById = auditService.getLastAuditByEntityId(actual.getId());
        assertThat(lastAuditEntityById.getOperationType()).isEqualTo(OperationType.CREATE);
        assertThat(lastAuditEntityById.getAuditableEntityType()).isEqualTo(AuditableEntityType.MONEY_TRANSFER);
    }

    @Test
    public void canCatchExceptionByAspect() {
        MoneyTransferDto moneyTransferDto = new MoneyTransferDto(null,
                UUID.fromString("447c7c7b-fb2f-4aaa-9900-21d7ed27d01b"),
                UUID.fromString("447c7c7b-fb2f-4aaa-9900-21d7ed27d02b"), 200, true);

        AuditException auditException =
                assertThrows(AuditException.class, () -> moneyTransferService.transferMoney(moneyTransferDto));

        Long auditableEntityId = auditException.getAuditableEntityId();
        AuditableEntity lastAuditEntityById = auditService.getLastAuditByEntityId(auditableEntityId);
        assertThat(lastAuditEntityById.getOperationType()).isEqualTo(OperationType.FAILED);
        assertThat(lastAuditEntityById.getAuditableEntityType()).isEqualTo(AuditableEntityType.MONEY_TRANSFER);

        assertThat(auditException.getMessage())
                .isEqualTo("Error during money transferring ...Perform rollback, transfer is failed");
        assertThat(auditException.getType()).isEqualTo(AuditableEntityType.MONEY_TRANSFER);
        assertThat(auditableEntityId).isEqualTo(lastAuditEntityById.getId());
    }
}
