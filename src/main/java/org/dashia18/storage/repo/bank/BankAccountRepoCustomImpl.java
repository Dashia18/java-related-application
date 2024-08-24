package org.dashia18.storage.repo.bank;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.dashia18.dto.bank.BankAccountPlan;
import org.springframework.stereotype.Repository;

@Repository
public class BankAccountRepoCustomImpl implements BankAccountRepoCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BankAccountPlan> findAllAccountIdsMappedToPlan() {
        String jpql = "select " +
                "NEW org.dashia18.dto.bank.BankAccountPlan(id, " +
                "CASE WHEN (isPremium = true) THEN 'YES' ELSE 'NO' END) from BankAccount bankAccount";
        TypedQuery<BankAccountPlan> query = entityManager.createQuery(jpql, BankAccountPlan.class);
        return query.getResultList();
    }
}
