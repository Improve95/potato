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
import ru.improve.potato.api.dto.auth.SignUpResponse;
import ru.improve.potato.api.dto.auth.SingUpRequest;
import ru.improve.potato.api.dtoMappers.UserMapper;
import ru.improve.potato.core.services.user.UserService;
import ru.improve.potato.core.validators.auth.AuthValidator;
import ru.improve.potato.core.validators.user.UserValidator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final UserMapper userMapper;

    private final AuthValidator authValidator;

    @PostMapping("/signup")
    public SignUpResponse addUser(@RequestBody @Valid SingUpRequest singUpRequest,
                                  BindingResult bindingResult) {

        userValidator.validate(singUpRequest, bindingResult);

        log.info("request arrived - add user: {}", singUpRequest);
        return userService.save(userMapper.toUser(singUpRequest));
    }

    @PostMapping("/login")
    public void login(@RequestBody @Valid LoginRequest loginRequest, BindingResult bindingResult) {

        authValidator.validate(loginRequest, bindingResult);
    }
}
