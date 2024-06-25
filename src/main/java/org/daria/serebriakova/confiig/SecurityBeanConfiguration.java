package org.daria.serebriakova.confiig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
public class SecurityBeanConfiguration {

    @Bean
    ConsumerAuthenticationFilter authenticationFilter() {
        return new ConsumerAuthenticationFilter();
    }
}
