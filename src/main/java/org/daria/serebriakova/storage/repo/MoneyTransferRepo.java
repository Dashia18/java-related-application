package org.daria.serebriakova.storage.repo;

import org.daria.serebriakova.storage.model.MoneyTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyTransferRepo extends JpaRepository<MoneyTransfer, Long> {
}
