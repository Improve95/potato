package ru.improve.potato.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Jacksonized
public class LoginRequest {

    @NotEmpty(message = "telephoneNumber cannot be empty")
    private String email;

    @NotEmpty(message = "password cannot be empty")
    private String password;
}
