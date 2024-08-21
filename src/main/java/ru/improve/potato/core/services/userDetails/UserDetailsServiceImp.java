package ru.improve.potato.core.services.userDetails;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.improve.potato.core.exceptions.NotFoundException;
import ru.improve.potato.core.repositories.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        return Optional.ofNullable(userRepository.findByPhone(phone))
                .orElseThrow(() -> new NotFoundException("user not found", Collections.singletonList("phone")));
    }
}
