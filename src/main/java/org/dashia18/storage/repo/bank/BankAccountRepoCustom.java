package org.dashia18.storage.repo.bank;

import java.util.List;
import org.dashia18.dto.bank.BankAccountPlan;

public interface BankAccountRepoCustom {

    List<BankAccountPlan> findAllAccountIdsMappedToPlan();
}
