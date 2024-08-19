package ru.improve.potato.core.repository.implementations;

import ru.improve.potato.core.repository.PaymentRepository;
import ru.improve.potato.model.payment.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
public class PaymentRepositoryImp implements PaymentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Payment getById(UUID paymentId) {
        return em.find(Payment.class, paymentId);
    }

    @Override
    public UUID addNewPayment(Payment payment) {
        em.persist(payment);
        return payment.getPaymentId();
    }

    @Override
    public List<Payment> getPaymentsByCreatorId(int creatorId) {
        Query query = em.createQuery("select p from Payment p where p.creatorId = :id")
                .setParameter("id", creatorId);
        return query.getResultList();
    }

    @Override
    public List<Payment> getPaymentsByPayerId(int payerId) {
        Query query = em.createQuery("select p from Payment p where p.payerId = :id")
                .setParameter("id", payerId);
        return query.getResultList();
    }
}
