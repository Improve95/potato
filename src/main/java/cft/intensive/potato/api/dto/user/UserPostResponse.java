package cft.intensive.potato.api.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Builder
@Getter
@Jacksonized
@ToString
public class UserPostResponse {

    private final int id;
}
