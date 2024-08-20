package ru.improve.potato.core.services.userDetails;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {

    UserDetails loadByPhone(String phone);
}
