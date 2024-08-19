package ru.improve.potato.error;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;

@Getter
@Setter
public class DefaultPotatoException extends RuntimeException {

    public DefaultPotatoException(String message, Set<String> fieldsWithError) {
        this.message = message;
        this.fieldsWithError = fieldsWithError;
        this.time = LocalDateTime.now().toInstant(ZoneOffset.UTC);
    }

    private String message;

    private Set<String> fieldsWithError;

    private Instant time;
}
