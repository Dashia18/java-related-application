package org.dashia18.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.dashia18.dto.bank.MoneyTransferDto;
import org.dashia18.dto.bank.TransferStatus;
import org.dashia18.exception.AuditException;
import org.dashia18.mapper.MoneyTransferMapper;
import org.dashia18.service.aspect.Audit;
import org.dashia18.storage.model.bank.BankAccount;
import org.dashia18.storage.model.bank.MoneyTransfer;
import org.dashia18.storage.repo.bank.MoneyTransferRepo;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MoneyTransferService {
    private final MoneyTransferRepo moneyTransferRepo;
    private final BankAccountService bankAccountService;
    private final MoneyTransferMapper moneyTransferMapper;

    @Audit
    public MoneyTransferDto transferMoney(MoneyTransferDto dto) {
        try {
            BankAccount fromAccount = bankAccountService.getAccount(dto.sourceAccountNumber());
            long fromAmount = fromAccount.getAmount();
            BankAccount toAccount = bankAccountService.getAccount(dto.targetAccountNumber());
            long toAmount = toAccount.getAmount();
            long amount = dto.amount();
            Pair<Long, Long> updateAccounts =
                    bankAccountService.updateAccounts(fromAccount, toAccount, amount, dto.performRollback());

            MoneyTransfer moneyTransfer = successTransfer(fromAccount, fromAmount, toAmount, amount, updateAccounts);
            return moneyTransferMapper.toDto(moneyTransfer);
        } catch (Exception e) {
            String errorMessage = "Error during money transferring ...";
            log.error(errorMessage, e);
            MoneyTransferDto moneyTransfer = failedTransfer(dto, e);
            throw new AuditException(errorMessage + e.getMessage(), moneyTransfer.getId(), moneyTransfer.getType(), e);
        }
    }

    private MoneyTransferDto failedTransfer(MoneyTransferDto dto, Exception e) {
        MoneyTransfer dao = moneyTransferMapper.toDao(dto);
        dao.setStatus(TransferStatus.FAILED);
        dao.setError(e.getMessage());
        MoneyTransfer saved = moneyTransferRepo.save(dao);
        return moneyTransferMapper.toDto(saved);
    }

    private MoneyTransfer successTransfer(BankAccount fromAccount, long fromAmount, long toAmount, long amount,
                                          Pair<Long, Long> updateAccounts) {
        MoneyTransfer moneyTransfer = MoneyTransfer.builder()
                .fromAccount(fromAccount.getAccountNumber())
                .fromAmount(fromAmount)
                .toAccount(fromAccount.getAccountNumber())
                .toAmount(toAmount)
                .amount(amount)
                .updatedFromAmount(updateAccounts.getLeft())
                .updatedToAmount(updateAccounts.getRight())
                .status(TransferStatus.SUCCESS)
                .build();
        return moneyTransferRepo.save(moneyTransfer);
    }
}
