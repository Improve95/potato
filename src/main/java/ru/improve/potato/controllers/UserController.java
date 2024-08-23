package ru.improve.potato.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.improve.potato.dto.user.UserGetRequest;
import ru.improve.potato.dto.user.UserGetResponse;
import ru.improve.potato.dto.user.UserPatchRequest;
import ru.improve.potato.dto.user.UserPostRequest;
import ru.improve.potato.dto.user.UserPostResponse;
import ru.improve.potato.mappers.UserMapper;
import ru.improve.potato.models.User;
import ru.improve.potato.security.SessionUserDetails;
import ru.improve.potato.services.user.UserService;
import ru.improve.potato.validators.user.UserValidator;

import java.util.UUID;

@RestController(value = "userController")
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final UserMapper userMapper;

    @GetMapping()
    public UserGetResponse getUserById(@AuthenticationPrincipal SessionUserDetails sessionUserDetails,
                                       @RequestBody UserGetRequest userGetRequest,
                                       BindingResult bindingResult) {

        userValidator.validate(userGetRequest, bindingResult);

        User user = userService.getById(userGetRequest.getId());
        return userMapper.toUserGetResponse(user);
    }

    @PostMapping()
    public UserPostResponse createUser(@RequestBody UserPostRequest userPostRequest,
                                       BindingResult bindingResult) {

        userValidator.validate(userPostRequest, bindingResult);

        return userService.save(userMapper.toUser(userPostRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> patchUserById(@RequestBody @Valid UserPatchRequest userPatchRequest,
                                                    BindingResult bindingResult,
                                                    @PathVariable("id") UUID id) {

        userValidator.validate(userPatchRequest, bindingResult);

        userService.patchById(userPatchRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
