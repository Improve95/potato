package ru.improve.potato.core.services.userDetails;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.improve.potato.core.exceptions.NotFoundException;
import ru.improve.potato.core.repositories.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadByPhone(String phone) {
        return Optional.ofNullable(userRepository.findByPhone(phone))
                .orElseThrow(() -> new NotFoundException("user not found", Collections.singletonList("phone")));
    }
}
