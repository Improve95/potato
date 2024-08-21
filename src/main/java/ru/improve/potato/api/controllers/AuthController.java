package ru.improve.potato.api.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.improve.potato.api.dto.auth.LoginRequest;
import ru.improve.potato.api.dto.auth.LoginResponse;
import ru.improve.potato.api.dto.auth.SignUpResponse;
import ru.improve.potato.api.dto.auth.SingUpRequest;
import ru.improve.potato.core.services.security.AuthenticationService;
import ru.improve.potato.core.validators.auth.AuthValidator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthenticationService authenticationService;
    private final AuthValidator authValidator;

    @PostMapping("/signup")
    public SignUpResponse signUp(@RequestBody @Valid SingUpRequest singUpRequest,
                                  BindingResult bindingResult) {

        authValidator.validate(singUpRequest, bindingResult);

        return authenticationService.signUp(singUpRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest,
                               BindingResult bindingResult) {

        authValidator.validate(loginRequest, bindingResult);

        return authenticationService.login(loginRequest);
    }
}
