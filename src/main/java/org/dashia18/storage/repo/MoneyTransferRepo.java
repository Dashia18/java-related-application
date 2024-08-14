package org.dashia18.storage.repo;

import org.dashia18.storage.model.MoneyTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyTransferRepo extends JpaRepository<MoneyTransfer, Long> {
}
