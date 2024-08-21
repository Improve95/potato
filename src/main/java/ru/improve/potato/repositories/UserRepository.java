package ru.improve.potato.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.improve.potato.models.user.User;
import ru.improve.potato.models.Wallet;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsUserByPhone(String phone);

    Optional<User> findByPhone(String Phone);

    Optional<User> findByEmail(String email);

    User findByWallet(Wallet wallet);
}
