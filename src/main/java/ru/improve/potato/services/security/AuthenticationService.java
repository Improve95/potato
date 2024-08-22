package ru.improve.potato.services.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.improve.potato.dto.auth.LoginRequest;
import ru.improve.potato.dto.auth.LoginResponse;
import ru.improve.potato.dto.auth.SignUpResponse;
import ru.improve.potato.dto.auth.SingUpRequest;
import ru.improve.potato.mappers.UserMapper;
import ru.improve.potato.models.user.User;
import ru.improve.potato.services.user.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final UserMapper userMapper;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public SignUpResponse signUp(SingUpRequest singUpRequest) {
        User user = userMapper.toUser(singUpRequest);
        user.setPassword(passwordEncoder.encode(singUpRequest.getPassword()));

        SignUpResponse signUpResponse = userService.save(user);
        signUpResponse.setAccessToken(jwtService.generateAccessToken(user.getId(), user.getEmail()));
        signUpResponse.setRefreshToken(jwtService.generateRefreshToken(user.getId(), user.getEmail()));

        return signUpResponse;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));

        User user = userService.getByEmail(loginRequest.getEmail());

        return LoginResponse.builder()
                .sessionId(UUID.randomUUID())
                .accessToken(jwtService.generateAccessToken(user.getId(), user.getEmail()))
                .refreshToken(jwtService.generateRefreshToken(user.getId(), user.getEmail()))
                .build();
    }

}
