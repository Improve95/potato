package ru.improve.potato.error.responseBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class CustomErrorResponse {

    private String message;

    private Set<String> fieldsWithError;

    private Instant time;
}
