package ru.improve.potato.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.improve.potato.error.exceptions.IncorrectJwtTokenException;
import ru.improve.potato.services.security.JwtService;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final String HEADER_NAME = "Authorization";
    private final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHead = request.getHeader(HEADER_NAME);
        if (authHead == null || authHead.isBlank() || !authHead.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHead.substring(BEARER_PREFIX.length());

        if (jwtService.verifyToken(token)) {
            try {
                Authentication authToken = jwtService.getAuthentication(token);

                if (authToken != null) {
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception ex) {
                throw new IncorrectJwtTokenException("incorrect jwt token", List.of("accessToken"));
            }
        }
        filterChain.doFilter(request, response);
    }
}
