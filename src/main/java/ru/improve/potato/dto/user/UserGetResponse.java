package ru.improve.potato.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Jacksonized
public class UserGetResponse {

    private String firstName;

    private String secondName;

    private String phone;

    private String email;

    private LocalDate birthdate;
}
