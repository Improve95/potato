package cft.intensive.potato.core.repository.implementations;

import cft.intensive.potato.core.repository.UsersRepository;
import cft.intensive.potato.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class UsersRepositoryImp implements UsersRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean userIsExist(int id) {
        return getById(id) == null ? false : true;
    }

    @Override
    public boolean userIsExist(String telephoneNumber) {
        return getByTelephone(telephoneNumber) == null ? false : true;
    }

    @Override
    public int addUser(User user) {
        em.persist(user);
        return user.getId();
    }

    @Override
    public User getById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByTelephone(String telephoneNumber) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.phone = :phone")
                .setParameter("phone", telephoneNumber);

        List<User> usersList = query.getResultList();

        if (usersList.size() == 0) return null;
        return usersList.get(0);
    }

    @Override
    public User getByWalletId(int id) {
        Query query = em.createQuery("select u from User u where u.wallet.id = :id")
                .setParameter("id", id);

        List<User> usersList = query.getResultList();

        if (usersList.size() == 0) return null;
        return usersList.get(0);
    }
}
