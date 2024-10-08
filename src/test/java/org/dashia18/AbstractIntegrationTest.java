package org.dashia18;

import org.dashia18.config.JavaRelatedIntegrationTest;
import org.dashia18.service.AuditService;
import org.dashia18.service.MoneyTransferService;
import org.dashia18.service.converter.ConverterService;
import org.dashia18.service.converter.Formatter;
import org.dashia18.storage.repo.bank.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;

@JavaRelatedIntegrationTest
public class AbstractIntegrationTest {
    @Autowired
    protected Formatter formatter;

    /*
     If there are no other hints for Spring, it uses the name of the field to inject
     */
    @Autowired
    protected Formatter slashDateFormatter;

    @Autowired
    protected ConverterService converterService;

    @Autowired
    protected BankAccountRepo bankAccountRepo;

    @Autowired
    protected MoneyTransferService moneyTransferService;

    @Autowired
    protected AuditService auditService;
}
