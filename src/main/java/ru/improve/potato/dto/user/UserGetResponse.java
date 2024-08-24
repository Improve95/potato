package ru.improve.potato.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGetResponse {

    private String firstName;

    private String secondName;

    private String phone;

    private String email;

    private LocalDate birthdate;
}
