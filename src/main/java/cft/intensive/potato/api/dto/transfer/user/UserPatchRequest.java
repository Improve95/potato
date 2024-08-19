package cft.intensive.potato.api.dto.transfer.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Builder
@ToString
@Jacksonized
@Getter
public class UserPatchRequest {

    private final String firstName;

    private final String secondName;

    private final LocalDateTime birthdate;
}
