package org.dashia18.storage.repo;

import java.util.Optional;
import org.dashia18.storage.model.BankClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankClientRepo extends JpaRepository<BankClient, Long> {
    Optional<BankClient> findByNameAndSurname(String name, String surname);
}
