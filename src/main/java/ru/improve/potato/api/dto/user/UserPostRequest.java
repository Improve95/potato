package ru.improve.potato.api.dto.user;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Jacksonized
public class UserPostRequest {

    @NotEmpty(message = "firstName cannot be empty")
    @NotBlank(message = "firstName cannot be include blank")
    @Size(min = 2, max = 50)
    private String firstName;

    @NotEmpty(message = "secondName cannot not be empty")
    @NotBlank(message = "secondName cannot include blank")
    @Size(min = 2, max = 50)
    private String secondName;

    @NotEmpty(message = "telephoneNumber cannot be empty")
    @Pattern(regexp = "^[7-9]\\d{10}$")
    private String telephoneNumber;

    @NotEmpty(message = "email cannot be empty")
    @Email(message = "bad input email")
    private String email;

    @NotNull(message = "birthdate cannot be null")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private LocalDate birthdate;

    @NotEmpty(message = "password cannot be empty")
    @Size(min = 8, message = "password size can be more 8")
    private String password;
}
