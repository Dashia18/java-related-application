package org.dashia18.storage.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.dashia18.dto.BankAccountPlan;
import org.dashia18.storage.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepo extends JpaRepository<BankAccount, Long>, BankAccountRepoCustom {
    Optional<BankAccount> findBankAccountByAccountNumber(UUID accountNumber);

    List<BankAccountPlan> findAllAccountIdsMappedToPlan();
}
