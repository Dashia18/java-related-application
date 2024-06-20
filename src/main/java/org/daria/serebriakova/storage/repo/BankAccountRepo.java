package org.daria.serebriakova.storage.repo;

import org.daria.serebriakova.storage.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepo extends JpaRepository<BankAccount, Long> {
}
