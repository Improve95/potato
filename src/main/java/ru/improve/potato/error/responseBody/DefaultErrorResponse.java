package ru.improve.potato.error.responseBody;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
public class DefaultErrorResponse {

    public DefaultErrorResponse(String message) {
        this.message = message;
        this.time = LocalDateTime.now().toInstant(ZoneOffset.UTC);
    }

    private String message;

    private Instant time;
}
