package ru.improve.potato.services.sessionAuth;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.improve.potato.dto.session.CreateSessionRequest;
import ru.improve.potato.dto.session.SessionResponseDto;
import ru.improve.potato.dto.session.RefreshSessionRequest;
import ru.improve.potato.error.exceptions.IncorrectJwtTokenException;
import ru.improve.potato.mappers.SessionMapper;
import ru.improve.potato.models.Session;
import ru.improve.potato.security.SessionUserDetails;
import ru.improve.potato.security.TokenRefresh;
import ru.improve.potato.services.security.JwtService;
import ru.improve.potato.services.session.SessionService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionAuthServiceImp implements SessionAuthService {

    private final SessionService sessionService;

    private final AuthenticationManager authManager;
    private final SessionMapper sessionMapper;

    private final JwtService jwtService;

    @Override
    public SessionResponseDto login(CreateSessionRequest createSessionRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        createSessionRequest.getEmail(),
                        createSessionRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        SessionUserDetails sessionUser = (SessionUserDetails) authentication.getPrincipal();

        return sessionMapper.toSessionResponseDto(sessionUser.getSession(), jwtService.extractExpirationTime(sessionUser.getSession().getAccessToken()));
    }

    @Transactional
    @Override
    public void logout(SessionUserDetails sessionUserDetails) {
        Session session = sessionService.getSessionByAccessToken(sessionUserDetails.getSession().getId());
        session.setEnabled(false);
        SecurityContextHolder.clearContext();
    }

    @Transactional
    @Override
    public SessionResponseDto refreshSession(RefreshSessionRequest refreshSessionRequest) {
        Session session = Optional.ofNullable(sessionService.getSessionByRefreshToken(refreshSessionRequest.getRefreshToken()))
                .orElseThrow(() -> new IncorrectJwtTokenException("incorrect jwt token", List.of("refreshToken")));

        TokenRefresh tokenRefresh = jwtService.refreshUserToken(refreshSessionRequest.getRefreshToken(), session);

        session.setAccessToken(tokenRefresh.getAccessToken());
        session.setRefreshToken(tokenRefresh.getRefreshToken());
        session.setEnabled(true);

        SessionResponseDto sessionResponseDto = sessionMapper.toSessionResponseDto(session,
                jwtService.extractExpirationTime(tokenRefresh.getAccessToken()));

        return sessionResponseDto;
    }
}
