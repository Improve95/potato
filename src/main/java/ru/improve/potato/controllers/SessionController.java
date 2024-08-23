package ru.improve.potato.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.improve.potato.dto.session.LoginRequest;
import ru.improve.potato.dto.session.LoginResponse;
import ru.improve.potato.services.sessionAuth.SessionAuthService;
import ru.improve.potato.validators.auth.AuthValidator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class SessionController {

    private final SessionAuthService sessionAuthService;

    private final AuthValidator authValidator;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest,
                               BindingResult bindingResult) {

        authValidator.validate(loginRequest, bindingResult);

        return sessionAuthService.login(loginRequest);
    }

    /*@PostMapping("/logout")
    public ResponseEntity<HttpStatus> logout(@RequestBody LogoutRequest logoutRequest) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }*/

    @PostMapping("/refresh")
    public ResponseEntity<HttpStatus> refresh() {
        return null;
    }
}
