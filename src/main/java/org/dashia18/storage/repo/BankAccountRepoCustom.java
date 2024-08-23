package org.dashia18.storage.repo;

import java.util.List;
import org.dashia18.dto.BankAccountPlan;

public interface BankAccountRepoCustom {

    List<BankAccountPlan> findAllAccountIdsMappedToPlan();
}
