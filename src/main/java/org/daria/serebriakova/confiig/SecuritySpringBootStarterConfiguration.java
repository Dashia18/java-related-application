package org.daria.serebriakova.confiig;


import static org.daria.serebriakova.controller.BankAccountController.BANK_ACCOUNT_API_URI;
import static org.daria.serebriakova.util.Constants.BANK_API_URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecuritySpringBootStarterConfiguration {
    private static final String ACTUATOR_HEALTH_CHECK_ENDPOINT_ID = "health";
    private static final String ACTUATOR_LOGGERS_ENDPOINT_ID = "loggers";
    private static final String ACTUATOR_INFO_ENDPOINT_ID = "info";
    private static final String ALLOWED_API_PATTERN = BANK_API_URI + "**";

    private static final RequestMatcher PROTECTED_ADMIN_ACTUATOR_URLS_MATCHERS =
            EndpointRequest.to(
                    ACTUATOR_LOGGERS_ENDPOINT_ID,
                    ACTUATOR_INFO_ENDPOINT_ID);
    private static final RequestMatcher MATCHER = new AntPathRequestMatcher(ALLOWED_API_PATTERN);

    @Autowired
    private ConsumerAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(authenticationFilter, AnonymousAuthenticationFilter.class)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(EndpointRequest.to(ACTUATOR_HEALTH_CHECK_ENDPOINT_ID))
                        .permitAll()

                        .requestMatchers(MATCHER)
                        .permitAll()

                        .requestMatchers(PROTECTED_ADMIN_ACTUATOR_URLS_MATCHERS)
                        .permitAll()

                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> cors.configure(http));
        return http.build();
    }
}
