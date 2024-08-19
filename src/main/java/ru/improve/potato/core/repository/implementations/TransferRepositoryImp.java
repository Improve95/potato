package ru.improve.potato.core.repository.implementations;

import ru.improve.potato.core.repository.TransferRepository;
import ru.improve.potato.model.transfer.Transfer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransferRepositoryImp implements TransferRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean transactionIsExist(int id) {
        if (getById(id) == null) return false;
        return true;
    }

    @Override
    public Transfer getById(int id) {
        return em.find(Transfer.class, id);
    }

    @Override
    public int addTransfer(Transfer transfer) {
        em.persist(transfer);
        return transfer.getId();
    }

    @Override
    public void changeTransferStatus(int id, boolean status) {
        Transfer transfer = em.find(Transfer.class, id);
        transfer.setStatus(status);
    }

    @Override
    public List<Transfer> getAllTransfersByWalletId(int id) {
        Query query = em.createQuery(
                        "select t from Transfer t " +
                        "where t.senderWalletId = :id or t.receiverWalletId = :id ")
                .setParameter("id", id);
        return (List<Transfer>) query.getResultList();
    }
}
