package ru.improve.potato.dto.auth;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SingUpRequest {

    @NotEmpty(message = "firstName cannot be empty")
    @NotBlank(message = "firstName cannot be include blank")
    @Size(min = 2, max = 50)
    private String firstName;

    @NotEmpty(message = "secondName cannot not be empty")
    @NotBlank(message = "secondName cannot include blank")
    @Size(min = 2, max = 50)
    private String secondName;

    @NotEmpty(message = "telephoneNumber cannot be empty")
    @Pattern(regexp = "^[7-9]\\d{10}$", message = "phone bad input")
    private String phone;

    @NotEmpty(message = "email cannot be empty")
    @Email(message = "bad input email")
    private String email;

    @NotNull(message = "birthdate cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @NotEmpty(message = "password cannot be empty")
    @Size(min = 8, message = "password size can be more 8")
    private String password;
}
