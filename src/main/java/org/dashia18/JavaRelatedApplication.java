package org.dashia18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})

public class JavaRelatedApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaRelatedApplication.class, args);
    }
}