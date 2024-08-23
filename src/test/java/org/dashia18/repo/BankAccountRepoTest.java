package org.dashia18.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.dashia18.AbstractIntegrationTest;
import org.dashia18.dto.BankAccountPlan;
import org.junit.jupiter.api.Test;

public class BankAccountRepoTest extends AbstractIntegrationTest {

    @Test
    public void test() {
        List<BankAccountPlan> expected = List.of(
                new BankAccountPlan(1L, "NO"),
                new BankAccountPlan(2L, "YES"),
                new BankAccountPlan(3L, "NO")
        );

        List<BankAccountPlan> actual = bankAccountRepo.findAllAccountIdsMappedToPlan();

        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}
