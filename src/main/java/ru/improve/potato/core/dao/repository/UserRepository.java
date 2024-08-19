package ru.improve.potato.core.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.improve.potato.model.User;
import ru.improve.potato.model.Wallet;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsUserByPhone(String phone);

    User findByPhone(String Phone);

    User findByWallet(Wallet wallet);
}
