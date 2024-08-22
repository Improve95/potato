package ru.improve.potato.dto.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private UUID sessionId;

    private String accessToken;

    private String refreshToken;
}
