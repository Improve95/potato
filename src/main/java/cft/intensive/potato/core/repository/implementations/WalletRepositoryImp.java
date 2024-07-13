package cft.intensive.potato.core.repository.implementations;

import cft.intensive.potato.core.repository.WalletRepository;
import cft.intensive.potato.model.Wallet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class WalletRepositoryImp implements WalletRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean walletIsExist(int id) {
        return getById(id) == null ? false : true;
    }

    @Override
    public Wallet getById(int id) {
        return em.find(Wallet.class, id);
    }

    @Override
    public int addWallet(Wallet wallet) {
        em.persist(wallet);
        return wallet.getId();
    }

    @Override
    public void addAmountOnBalance(int id, int diffAmount) {
        Wallet wallet = em.find(Wallet.class, id);
        wallet.addAmountOnBalance(diffAmount);
    }

    @Override
    public void subAmountFromBalance(int id, int diffAmount) {
        Wallet wallet = em.find(Wallet.class, id);
        wallet.subAmountFromBalance(diffAmount);
    }
}
