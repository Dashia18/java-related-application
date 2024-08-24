package org.dashia18.storage.repo.bank;

import org.dashia18.storage.model.bank.MoneyTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyTransferRepo extends JpaRepository<MoneyTransfer, Long> {
}
