package ru.improve.potato.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.improve.potato.models.Session;
import ru.improve.potato.models.User;
import ru.improve.potato.services.security.JwtService;
import ru.improve.potato.services.session.SessionService;
import ru.improve.potato.services.user.UserService;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthProvider implements AuthenticationProvider {

    private final UserService userService;

    private final SessionService sessionService;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("authProvider: authenticate");

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user;
        try {
            user = userService.getByEmail(email);
        }  catch (Exception ex) {
            log.error(ex.getMessage());
            throw new BadCredentialsException("incorrect email or password");
        }

        if (passwordEncoder.matches(password, user.getPassword())) {
            String accessToken = jwtService.generateAccessToken(user.getId(), user.getEmail());
            String refreshToken = jwtService.generateRefreshToken(user.getId(), user.getEmail());

            Session session = new Session(accessToken, refreshToken, true, user);
            sessionService.save(session);

            SessionUserDetails sessionUser = SessionUserDetailsFactory.createSessionUser(session.getUser(), session);

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
