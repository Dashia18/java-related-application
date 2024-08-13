package org.daria.serebriakova;

import org.daria.serebriakova.config.JavaRelatedIntegrationTest;
import org.daria.serebriakova.service.converter.ConverterService;
import org.daria.serebriakova.service.converter.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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
}
