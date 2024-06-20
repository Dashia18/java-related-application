package org.daria.serebriakova.storage.repo;

import java.util.Optional;
import org.daria.serebriakova.dto.BankAccountDto;
import org.daria.serebriakova.storage.model.BankAccount;
import org.daria.serebriakova.storage.model.BankClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BankClientRepo extends JpaRepository<BankClient, Long> {
}
