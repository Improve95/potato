package cft.intensive.potato.api.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
@Jacksonized
public class UserPostRequest {

    private final String firstName;

    private final String secondName;

    private final String telephoneNumber;

    private final String email;

    private final LocalDateTime birthdate;

    private final String password;
}
