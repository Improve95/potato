package ru.improve.potato.dto.session;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshSessionRequest {

    @NotEmpty
    private String refreshToken;
}
