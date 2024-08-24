package ru.improve.potato.security;

import ru.improve.potato.models.Session;
import ru.improve.potato.models.User;

public class SessionUserDetailsFactory {

    public static SessionUserDetails createSessionUser(User user, Session session) {
        return SessionUserDetails.builder()
                .userId(user.getId())
                .session(session)
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
