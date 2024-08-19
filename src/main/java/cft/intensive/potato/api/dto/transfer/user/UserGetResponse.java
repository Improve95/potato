package cft.intensive.potato.api.dto.transfer.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Builder
@Getter
@Jacksonized
@ToString
public class UserGetResponse {

    private final String firstName;

    private final String secondName;

    private final String telephoneNumber;

    private final String email;

    private final LocalDateTime birthdate;
}
