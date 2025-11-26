package com.alexki.authapi.configuration;

import com.alexki.authapi.exception.ExpiredJwtToken;
import com.alexki.authapi.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) {

        String authHeader = request.getHeader("Authorization");
        String userEmail;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);

            try {
                userEmail = jwtService.getUserEmail(jwt);
            } catch (ExpiredJwtException e) {
                throw new ExpiredJwtToken();
            }

            if (userEmail != null) {
                List<SimpleGrantedAuthority> authorities = jwtService.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).toList();

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        new org.springframework.security.core.userdetails.User(
                                userEmail,
                                "",
                                authorities
                        ),
                        null,
                        authorities

                );

                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }

        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getRequestURI().equals("/api/process");
    }
}
