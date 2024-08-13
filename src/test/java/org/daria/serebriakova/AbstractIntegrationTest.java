package org.daria.serebriakova;

import org.daria.serebriakova.config.JavaRelatedIntegrationTest;
import org.daria.serebriakova.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;

@JavaRelatedIntegrationTest
public class AbstractIntegrationTest {
    @Autowired
    protected ConverterService.Formatter formatter;

    @Autowired
    protected ConverterService converterService;
}
