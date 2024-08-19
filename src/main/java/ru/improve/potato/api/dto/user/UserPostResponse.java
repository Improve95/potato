package ru.improve.potato.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Jacksonized
public class UserPostResponse {

    private int id;
}
