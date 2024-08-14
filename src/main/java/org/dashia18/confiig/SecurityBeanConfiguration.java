package org.dashia18.confiig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SecurityBeanConfiguration {

    @Bean
    ConsumerAuthenticationFilter authenticationFilter() {
        return new ConsumerAuthenticationFilter();
    }
}
