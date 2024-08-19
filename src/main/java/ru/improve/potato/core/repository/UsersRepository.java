package ru.improve.potato.core.repository;

import ru.improve.potato.model.User;

public interface UsersRepository {

    boolean userIsExist(int id);

    boolean userIsExist(String TelephoneNumber);

    int addUser(User user);

    User getById(int id);

    User getByTelephone(String telephoneNumber);

    User getByWalletId(int id);
}
