package org.daria.serebriakova.confiig;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class ConsumerAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        val requestedToken = ofNullable(httpServletRequest.getHeader(AUTHORIZATION)).orElse(Strings.EMPTY);

        try {
            val authorities = List.of(requestedToken.replace("Bearer ", "").split(","));
            val authority = authorities.stream()
                    .map(String::trim)
                    .map(el -> "ROLE_" + el)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
            UserDetails userDetails = new User("user@email.com", requestedToken, authority);
            val authentication = new PreAuthenticatedAuthenticationToken(userDetails, requestedToken,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationServiceException e) {
            // Token is not provided or has wrong value.
            // proceed with other authorization options
        }

        filterChain.doFilter(httpServletRequest, response);
    }
}
