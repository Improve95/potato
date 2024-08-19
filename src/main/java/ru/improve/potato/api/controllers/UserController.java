package ru.improve.potato.api.controllers;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import ru.improve.potato.api.dto.user.UserPostRequest;
import ru.improve.potato.api.dto.user.UserPostResponse;
import ru.improve.potato.api.dto.user.UserGetResponse;
import ru.improve.potato.api.dto.user.UserPatchRequest;
import ru.improve.potato.core.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.improve.potato.core.validators.user.UserValidator;

@RestController(value = "userController")
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserValidator userValidator;

    @PostMapping
    public UserPostResponse addUser(@RequestBody @Valid UserPostRequest userPostRequest,
                                    BindingResult bindingResult) {

        userValidator.validate(userPostRequest, bindingResult);

        log.info("request arrived - add user: {}", userPostRequest);
        return userService.createUser(userPostRequest);
    }

    @GetMapping("/{id}")
    public UserGetResponse getUserById(@PathVariable int id) {
        log.info("request arrived - get user by id: {}", id);
        return userService.getById(id);
    }

    @PatchMapping("/{id}")
    public void patchUserById(@RequestBody @Valid UserPatchRequest userPatchRequest, BindingResult bindingResult,
                              @PathVariable("id") int id) {

        userValidator.validate(userPatchRequest, bindingResult);

        log.info("request arrived - request patch user by id: {}", id);
        userService.patchUserById(userPatchRequest, id);
    }
}
