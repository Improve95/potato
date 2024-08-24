package ru.improve.potato.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.improve.potato.dto.session.CreateSessionRequest;
import ru.improve.potato.dto.session.SessionResponseDto;
import ru.improve.potato.dto.session.RefreshSessionRequest;
import ru.improve.potato.models.Session;
import ru.improve.potato.security.SessionUserDetails;
import ru.improve.potato.services.session.SessionService;
import ru.improve.potato.services.sessionAuth.SessionAuthService;
import ru.improve.potato.validators.auth.AuthValidator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class SessionController {

    private final SessionService sessionService;
    private final SessionAuthService sessionAuthService;

    private final AuthValidator authValidator;

    @GetMapping()
    public Session getSession(@AuthenticationPrincipal SessionUserDetails sessionUserDetails) {
        return sessionService.getSessionByAccessToken(sessionUserDetails.getSession().getId());
    }

    @PostMapping("/login")
    public SessionResponseDto login(@RequestBody @Valid CreateSessionRequest createSessionRequest,
                                    BindingResult bindingResult) {

        authValidator.validate(createSessionRequest, bindingResult);

        return sessionAuthService.login(createSessionRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<HttpStatus> logout(@AuthenticationPrincipal SessionUserDetails sessionUserDetails) {

        sessionAuthService.logout(sessionUserDetails);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/refresh")
    public SessionResponseDto refresh(@RequestBody RefreshSessionRequest refreshSessionRequest,
                                              BindingResult bindingResult) {

        authValidator.validate(refreshSessionRequest, bindingResult);

        return sessionAuthService.refreshSession(refreshSessionRequest);
    }
}
