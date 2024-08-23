package ru.improve.potato.services.security.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.improve.potato.dto.session.CreateSessionRequest;
import ru.improve.potato.dto.session.CreateSessionResponse;
import ru.improve.potato.dto.user.UserPostRequest;
import ru.improve.potato.dto.user.UserPostResponse;
import ru.improve.potato.mappers.UserMapper;
import ru.improve.potato.models.User;
import ru.improve.potato.services.security.AuthenticationService;
import ru.improve.potato.services.security.JwtService;
import ru.improve.potato.services.user.UserService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {

    private final UserService userService;
    private final UserMapper userMapper;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserPostResponse signUp(UserPostRequest userPostRequest) {
        User user = userMapper.toUser(userPostRequest);
        user.setPassword(passwordEncoder.encode(userPostRequest.getPassword()));

//        UserPostResponse userPostResponse = userService.save(user);
//        userPostResponse.setAccessToken(jwtService.generateAccessToken(user.getId(), user.getEmail()));
//        userPostResponse.setRefreshToken(jwtService.generateRefreshToken(user.getId(), user.getEmail()));

//        return userPostResponse;
        return null;
    }

    @Override
    public CreateSessionResponse login(CreateSessionRequest createSessionRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                createSessionRequest.getEmail(),
                createSessionRequest.getPassword()
        ));

        User user = userService.getByEmail(createSessionRequest.getEmail());

        /*return LoginResponse.builder()
                .sessionId(UUID.randomUUID())
                .accessToken(jwtService.generateAccessToken(user.getId(), user.getEmail()))
                .refreshToken(jwtService.generateRefreshToken(user.getId(), user.getEmail()))
                .build();*/
        return null;
    }

}
