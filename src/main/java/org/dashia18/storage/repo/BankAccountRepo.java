package org.dashia18.storage.repo;

import java.util.Optional;
import java.util.UUID;
import org.dashia18.storage.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepo extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findBankAccountByAccountNumber(UUID accountNumber);
}
