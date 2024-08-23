package ru.improve.potato.services.sessionAuth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.improve.potato.dto.session.CreateSessionRequest;
import ru.improve.potato.dto.session.CreateSessionResponse;
import ru.improve.potato.mappers.AuthMapper;
import ru.improve.potato.security.SessionUserDetails;
import ru.improve.potato.services.security.JwtService;

@Service
@RequiredArgsConstructor
public class SessionAuthServiceImp implements SessionAuthService {

    private final AuthenticationManager authManager;
    private final AuthMapper authMapper;

    private final JwtService jwtService;

    @Override
    public CreateSessionResponse login(CreateSessionRequest createSessionRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        createSessionRequest.getEmail(),
                        createSessionRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        SessionUserDetails sessionUser = (SessionUserDetails) authentication.getPrincipal();

        return authMapper.toLoginResponse(sessionUser.getSession(), jwtService.extractExpirationTime(sessionUser.getSession().getAccessToken()));
    }
}
