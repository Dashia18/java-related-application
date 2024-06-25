package org.daria.serebriakova.confiig;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    public static final Duration TIMEOUT = Duration.ofSeconds(120);

    @Bean
    public RestTemplate restTemplate(ObjectMapper objectMapper, RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(TIMEOUT)
                .setReadTimeout(TIMEOUT)
                .additionalMessageConverters(List.of(
                        new MappingJackson2HttpMessageConverter(objectMapper),
                        new FormHttpMessageConverter()))
                .build();
    }
}