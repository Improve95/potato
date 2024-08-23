package ru.improve.potato.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGetRequest {

    @NotEmpty(message = "id cannot be empty")
    @NotNull(message = "id cannot be null")
    private UUID id;
}
