package ru.improve.potato.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.improve.potato.models.Session;
import ru.improve.potato.models.User;
import ru.improve.potato.services.security.JwtService;
import ru.improve.potato.services.session.SessionService;
import ru.improve.potato.services.user.UserService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

    private final UserService userService;
    private final UserDetailsService userDetailsService;

    private final SessionService sessionService;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        SessionUserDetails sessionUser;
        User user;
        try {
            sessionUser = (SessionUserDetails) userDetailsService.loadUserByUsername(email);
            user = userService.getByEmail(email);
        }  catch (Exception ex) {
            throw new BadCredentialsException("incorrect email or password");
        }

        if (passwordEncoder.matches(password, sessionUser.getPassword())) {
            String accessToken = jwtService.generateAccessToken(sessionUser.getId(), sessionUser.getEmail());
            String refreshToken = jwtService.generateRefreshToken(sessionUser.getId(), sessionUser.getEmail());

            Session session = new Session(accessToken, refreshToken, true, user);

            UUID sessionId = sessionService.save(session);

            return new UsernamePasswordAuthenticationToken(sessionUser, password, sessionUser.getAuthorities());
        } else {
            throw new BadCredentialsException("incorrect email or password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
