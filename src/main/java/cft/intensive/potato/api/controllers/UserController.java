package cft.intensive.potato.api.controllers;

import cft.intensive.potato.api.dto.user.UserPostRequest;
import cft.intensive.potato.api.dto.user.UserPostResponse;
import cft.intensive.potato.api.dto.user.UserGetResponse;
import cft.intensive.potato.api.dto.user.UserPatchRequest;
import cft.intensive.potato.core.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "userController")
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserPostResponse addUser(@RequestBody UserPostRequest userPostRequest) {
        log.info("request arrived - add user: {}", userPostRequest.toString());
        return userService.createUser(userPostRequest);
    }

    @GetMapping("/{id}")
    public UserGetResponse getUserById(@PathVariable int id) {
        log.info("request arrived - get user by id: {}", id);
        return userService.getById(id);
    }

    @PatchMapping("/{id}")
    public void patchUserById(@RequestBody UserPatchRequest userPatchRequest, int id) {
        log.info("request arrived - request patch user by id: {}", id);
        userService.patchUserById(userPatchRequest, id);
    }
}
