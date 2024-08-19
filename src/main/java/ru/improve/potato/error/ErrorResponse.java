package ru.improve.potato.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private String message;

    private Set<String> fieldsWithError;

    private Instant time;
}
