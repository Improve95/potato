package cft.intensive.potato.core.repository;

import cft.intensive.potato.model.User;

public interface UsersRepository {

    boolean userIsExist(int id);

    boolean userIsExist(String TelephoneNumber);

    int addUser(User user);

    User getById(int id);

    User getByTelephone(String telephoneNumber);

    User getByWalletId(int id);
}
