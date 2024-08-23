package ru.improve.potato.dto.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSessionResponse {

    private UUID sessionId;

    private String accessToken;

    private String refreshToken;

    private Date expiredAt;

    private boolean enabled;
}
