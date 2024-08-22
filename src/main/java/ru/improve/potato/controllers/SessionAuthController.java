package ru.improve.potato.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.improve.potato.services.security.AuthenticationService;
import ru.improve.potato.validators.auth.AuthValidator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class SessionAuthController {

    private final AuthenticationService authenticationService;
    private final AuthValidator authValidator;

    /*@PostMapping("/signup")
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
    }*/
}
