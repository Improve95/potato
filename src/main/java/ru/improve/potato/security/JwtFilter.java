package ru.improve.potato.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.improve.potato.core.services.jwt.JwtService;
import ru.improve.potato.core.services.userDetails.UserDetailsService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
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
        if (authHead.isBlank() || !authHead.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
        }

        String token = authHead.substring(BEARER_PREFIX.length());
        if (token.isEmpty() || token.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid JWT Token in Bearer Header");
            filterChain.doFilter(request, response);
        }

        try {
            String phone = jwtService.verifyTokenAndGetSubject(token);
            UserDetails userDetails = userDetailsService.loadByPhone(phone);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    userDetails.getPassword(),
                    userDetails.getAuthorities());

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (JWTVerificationException ex) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid JWT Token");
        }

        filterChain.doFilter(request, response);
    }
}
