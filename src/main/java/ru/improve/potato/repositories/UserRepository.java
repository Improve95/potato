package ru.improve.potato.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.improve.potato.models.User;
import ru.improve.potato.models.Wallet;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsUserByPhone(String phone);

    Optional<User> findByPhone(String Phone);

    Optional<User> findByEmail(String email);

    User findByWallet(Wallet wallet);
}
