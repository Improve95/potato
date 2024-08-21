package ru.improve.potato.core.services.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.improve.potato.api.dto.auth.LoginRequest;
import ru.improve.potato.api.dto.auth.LoginResponse;
import ru.improve.potato.api.dto.auth.SignUpResponse;
import ru.improve.potato.api.dto.auth.SingUpRequest;
import ru.improve.potato.api.dtoMappers.UserMapper;
import ru.improve.potato.core.services.user.UserService;
import ru.improve.potato.model.Wallet;
import ru.improve.potato.model.user.User;

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

        Wallet wallet = new Wallet(1000, user);
        user.setWallet(wallet);

        SignUpResponse signUpResponse = userService.save(user);
        signUpResponse.setToken(jwtService.generateToken(user));
        return signUpResponse;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getPhone(),
                loginRequest.getPhone()
        ));

        return LoginResponse.builder()
                .sessionId(UUID.randomUUID())
                .build();
    }

}
